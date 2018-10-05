package yuma140902.hundredsofores.recipes;

import javax.annotation.Nullable;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.MyItems;
import yuma140902.hundredsofores.ore_feature_set.OreFeatureItemBase;
import yuma140902.hundredsofores.ore_feature_set.OreFeatureType;
import yuma140902.hundredsofores.ore_feature_set.OreFeaturesSet;
import yuma140902.hundredsofores.ore_feature_set.features.BlockCompressedBlock;
import yuma140902.hundredsofores.ore_feature_set.features.BlockOre;
import yuma140902.hundredsofores.ore_feature_set.features.ItemDust;
import yuma140902.hundredsofores.ore_feature_set.features.ItemGear;
import yuma140902.hundredsofores.ore_feature_set.features.ItemGem;
import yuma140902.hundredsofores.ore_feature_set.features.ItemIngot;
import yuma140902.hundredsofores.ore_feature_set.features.ItemNugget;
import yuma140902.hundredsofores.ore_feature_set.features.ItemPickaxe;

public final class RecipeRegisterHelper {
	private RecipeRegisterHelper() {}
	
	public static final float DEFAULT_FURANCE_EXP = 10.0F; // TODO 10.0Fは適当
	
	private static final ItemStack HUMMER = new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE);
	
	public static void addRecipesFromOreFeaturesSet(OreFeaturesSet ofs) {
		addRecipesFromOreFeaturesSet(
				(BlockOre) ofs.getFeature(OreFeatureType.ORE), 
				(BlockCompressedBlock) ofs.getFeature(OreFeatureType.BLOCK),
				(ItemIngot) ofs.getFeature(OreFeatureType.INGOT), 
				(ItemNugget) ofs.getFeature(OreFeatureType.NUGGET), 
				(ItemGem) ofs.getFeature(OreFeatureType.GEM), 
				(ItemDust) ofs.getFeature(OreFeatureType.DUST), 
				(ItemGear) ofs.getFeature(OreFeatureType.GEAR), 
				(ItemPickaxe) ofs.getFeature(OreFeatureType.PICKAXE)
		);
	}
	
	public static void addRecipesFromOreFeaturesSet(BlockOre ore, BlockCompressedBlock block, ItemIngot ingot, ItemNugget nugget, ItemGem gem, ItemDust dust, ItemGear gear, ItemPickaxe pickaxe) {
		boolean hasGemOrIngot = ingot != null || gem != null;
		OreFeatureItemBase gem_ingot = null;
		String gem_ingotOredict = null;
		if(hasGemOrIngot) {
			gem_ingot = (OreFeatureItemBase) (ingot != null ? ingot : gem);
			gem_ingotOredict = gem_ingot.getOreDictionaryKey();
		}
		//前処理ここまで
		
		if(ore != null && dust != null) {
			// 鉱石から粉2つ TODO: 独自の粉砕機も実装したい
			addRecipeOreToDust(ore, dust);
			addRecipeOreToDust(ore.getOreDictionaryKey(), dust);
		}
		
		if(hasGemOrIngot && block != null) {
			String blockOredict = block.getOreDictionaryKey();
			
			// ブロックの解凍
			addRecipeBlockExpand(block, gem_ingot);
			addRecipeBlockExpand(blockOredict, gem_ingot);
			
			// ブロックへ圧縮
			addRecipeBlockCompress(gem_ingot, block);
			addRecipeBlockCompress(gem_ingotOredict, block);
		}
		
		if(hasGemOrIngot && gear != null) {
		// ジェムOrインゴットからギア
			GameRegistry.addRecipe(new ShapedOreRecipe(
					gear, 
					" # ", 
					"###", 
					" # ", 
					'#', gem_ingot
					));
			GameRegistry.addRecipe(new ShapedOreRecipe(
					gear, 
					" # ", 
					"###", 
					" # ", 
					'#', gem_ingotOredict
					));
		}
		
		if(hasGemOrIngot && pickaxe != null) {
			// ジェムOrインゴットからツルハシ
			GameRegistry.addRecipe(new ShapedOreRecipe(
					pickaxe, 
					"###", 
					" | ", 
					" | ", 
					'#', gem_ingot, 
					'|', Items.stick
					));
			GameRegistry.addRecipe(new ShapedOreRecipe(
					pickaxe, 
					"###", 
					" | ", 
					" | ", 
					'#', gem_ingotOredict, 
					'|', Items.stick
					));
		}
		
		if(hasGemOrIngot && dust != null) {
			// ジェムOrインゴットから粉
			addRecipeIngotToDust(gem_ingot, dust);
			addRecipeIngotToDust(gem_ingotOredict, dust);
		}
		
		if(ingot != null && nugget != null) {
			String nuggetOredict = nugget.getOreDictionaryKey();
			String ingotOredict = ingot.getOreDictionaryKey();
			
			// ナゲットからインゴット
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nugget));
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nuggetOredict));
			
			// インゴットからナゲット
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingot));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingotOredict));
		}
		
		if(hasGemOrIngot && ore != null) {
			// 鉱石を精錬してジェムOrインゴット
			addSmeltingOreToIngot(ore , gem_ingot);
		}
		
		if(hasGemOrIngot && dust != null) {
			// 粉を精錬してジェムOrインゴット
			addSmeltingDustToIngot(dust, gem_ingot);
		}
	}
	
	//鉱石から粉2つに
	private static void addRecipeOreToDust(Block ore, Item dust) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
			new ItemStack(dust, 2), 
			ore, HUMMER));
	}
	
	private static void addRecipeOreToDust(@Nullable String oreOreDictionaryKey, Item dust) {
		if(oreOreDictionaryKey == null) return;
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(dust, 2), 
				oreOreDictionaryKey, HUMMER));
	}
	
	//インゴットから粉1つに
	private static void addRecipeIngotToDust(Item gem_ingot, Item dust) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				dust,
				gem_ingot, HUMMER));
	}
	
	private static void addRecipeIngotToDust(@Nullable String gem_ingotOreDictionaryKey, Item dust) {
		if(gem_ingotOreDictionaryKey == null) return;
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				dust,
				gem_ingotOreDictionaryKey, HUMMER));
	}
	
	//ブロックへ圧縮
	private static void addRecipeBlockCompress(Item gem_ingot, Block block) {
		GameRegistry.addRecipe(new ShapedOreRecipe(
				block, 
				"###", 
				"###", 
				"###", 
				'#', gem_ingot
				));
	}
	
	private static void addRecipeBlockCompress(@Nullable String gem_ingotOreDictionaryKey, Block block) {
		if(gem_ingotOreDictionaryKey == null) return;
		GameRegistry.addRecipe(new ShapedOreRecipe(
				block, 
				"###", 
				"###", 
				"###", 
				'#', gem_ingotOreDictionaryKey
				));
	}
	
	//ブロックを解凍
	private static void addRecipeBlockExpand(Block block, Item gem_ingot) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(gem_ingot, 9), 
				block
				));
	}
	
	private static void addRecipeBlockExpand(@Nullable String blockOreDictionaryKey, Item gem_ingot) {
		if(blockOreDictionaryKey == null) return;
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(gem_ingot, 9), 
				blockOreDictionaryKey
				));
	}
	
	
	//鉱石を精錬してジェムOrインゴット
	private static void addSmeltingOreToIngot(Block ore, Item gem_ingot) {
		GameRegistry.addSmelting(ore, new ItemStack(gem_ingot), DEFAULT_FURANCE_EXP);
	}
	
	//粉を精錬してジェムOrインゴット
	private static void addSmeltingDustToIngot(Item dust, Item gem_ingot) {
		GameRegistry.addSmelting(dust, new ItemStack(gem_ingot), DEFAULT_FURANCE_EXP);
	}
}
