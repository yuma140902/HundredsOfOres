package yuma140902.hundredsofores.recipes;

import javax.annotation.Nullable;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.MyItems;

public final class RecipeRegisterHelper {
	private RecipeRegisterHelper() {}
	
	public static final float DEFAULT_FURANCE_EXP = 10.0F; // TODO 10.0Fは適当
	
	public static final ItemStack HUMMER = new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE);
	
	//鉱石から粉2つに
	public static void addRecipeOreToDust(Block ore, Item dust) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
			new ItemStack(dust, 2), 
			ore, HUMMER));
	}
	
	public static void addRecipeOreToDust(@Nullable String oreOreDictionaryKey, Item dust) {
		if(oreOreDictionaryKey == null) return;
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(dust, 2), 
				oreOreDictionaryKey, HUMMER));
	}
	
	//インゴットから粉1つに
	public static void addRecipeIngotToDust(Item gem_ingot, Item dust) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				dust,
				gem_ingot, HUMMER));
	}
	
	public static void addRecipeIngotToDust(@Nullable String gem_ingotOreDictionaryKey, Item dust) {
		if(gem_ingotOreDictionaryKey == null) return;
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				dust,
				gem_ingotOreDictionaryKey, HUMMER));
	}
	
	//ブロックへ圧縮
	public static void addRecipeBlockCompress(Item gem_ingot, Block block) {
		GameRegistry.addRecipe(new ShapedOreRecipe(
				block, 
				"###", 
				"###", 
				"###", 
				'#', gem_ingot
				));
	}
	
	public static void addRecipeBlockCompress(@Nullable String gem_ingotOreDictionaryKey, Block block) {
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
	public static void addRecipeBlockExpand(Block block, Item gem_ingot) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(gem_ingot, 9), 
				block
				));
	}
	
	public static void addRecipeBlockExpand(@Nullable String blockOreDictionaryKey, Item gem_ingot) {
		if(blockOreDictionaryKey == null) return;
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(gem_ingot, 9), 
				blockOreDictionaryKey
				));
	}
	
	
	//鉱石を精錬してジェムOrインゴット
	public static void addSmeltingOreToIngot(Block ore, Item gem_ingot) {
		GameRegistry.addSmelting(ore, new ItemStack(gem_ingot), DEFAULT_FURANCE_EXP);
	}
	
	//粉を精錬してジェムOrインゴット
	public static void addSmeltingDustToIngot(Item dust, Item gem_ingot) {
		GameRegistry.addSmelting(dust, new ItemStack(gem_ingot), DEFAULT_FURANCE_EXP);
	}
}
