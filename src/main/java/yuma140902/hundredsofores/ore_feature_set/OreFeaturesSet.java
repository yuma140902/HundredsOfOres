package yuma140902.hundredsofores.ore_feature_set;

import java.util.EnumMap;
import java.util.Map;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.ore_feature_set.features.BlockCompressedBlock;
import yuma140902.hundredsofores.ore_feature_set.features.BlockOre;
import yuma140902.hundredsofores.ore_feature_set.features.ItemDust;
import yuma140902.hundredsofores.ore_feature_set.features.ItemGear;
import yuma140902.hundredsofores.ore_feature_set.features.ItemGem;
import yuma140902.hundredsofores.ore_feature_set.features.ItemIngot;
import yuma140902.hundredsofores.ore_feature_set.features.ItemNugget;
import yuma140902.hundredsofores.ore_feature_set.features.ItemPickaxe;
import yuma140902.hundredsofores.recipes.RecipeRegisterHelper;
import yuma140902.hundredsofores.util.StringUtil;
import yuma140902.hundredsofores.worldGen.WorldGenerators;

/**
 * 金属は、鉱石、インゴット、粉、ブロックなどさまざまな状態になるので、
 * それらをまとめて生成、登録、レシピの追加などを行うためのクラスです。
 * 
 * @author yuma1
 *
 */
public class OreFeaturesSet {
	
	private OreID _oreName;
	
	protected Map<OreFeatureType, IOreFeature> features = new EnumMap<>(OreFeatureType.class);
	protected Map<OreFeatureType, Boolean> existFeatures = new EnumMap<>(OreFeatureType.class);
	
	protected int toolConfigDefaultHarvestLevel = 2;
	protected int toolConfigDefaultMaxUses = 300;
	protected float toolConfigDefaultEfficiency = 7.0F;
	protected float toolConfigDefaultDamage = 0.0F;
	protected int toolConfigDefaultEnchantability = 14;
	
	protected ToolMaterial toolMaterial;
	
	public OreFeaturesSet(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFeaturesSet(OreID oreName) {
		this._oreName = oreName;
		
		for(OreFeatureType key : OreFeatureType.values()) {
			features.put(key, IOreFeature.EMPTY);
		}
		includeFeature(OreFeatureType.ORE);
		includeFeature(OreFeatureType.BLOCK);
		includeFeature(OreFeatureType.PICKAXE);
		includeFeature(OreFeatureType.DUST);
		includeFeature(OreFeatureType.GEAR);
	}
	
	// existFeaturesの値に基づいてfeaturesを初期化します。
	public void setFeatureValues() {
		for(Map.Entry<OreFeatureType, Boolean> entry : existFeatures.entrySet()) {
			if(entry.getValue() == true) {
				switch (entry.getKey()) {
					case ORE:
						features.put(OreFeatureType.ORE, new BlockOre(_oreName));
						break;
					case BLOCK:
						features.put(OreFeatureType.BLOCK, new BlockCompressedBlock(_oreName));
						break;
					case INGOT:
						features.put(OreFeatureType.INGOT, new ItemIngot(_oreName));
						break;
					case NUGGET:
						features.put(OreFeatureType.NUGGET, new ItemNugget(_oreName));
						break;
					case GEM:
						features.put(OreFeatureType.GEM, new ItemGem(_oreName));
						break;
					case DUST:
						features.put(OreFeatureType.DUST, new ItemDust(_oreName));
						break;
					case GEAR:
						features.put(OreFeatureType.GEAR, new ItemGear(_oreName));
						break;
					case PICKAXE:
						features.put(OreFeatureType.PICKAXE, new ItemPickaxe(_oreName, toolMaterial));
						break;
				}
			}
		}
	}
	
	public void registerRecipes() {
		
		boolean hasGemOrIngot = hasFeature(OreFeatureType.INGOT) || hasFeature(OreFeatureType.GEM);
		OreFeatureItemBase gem_ingot = null;
		String gem_ingotOredict = null;
		if(hasGemOrIngot) {
			gem_ingot = (OreFeatureItemBase) (hasFeature(OreFeatureType.INGOT) ? getFeature(OreFeatureType.INGOT) : getFeature(OreFeatureType.GEM));
			gem_ingotOredict = gem_ingot.getOreDictionaryKey();
		}
		//前処理ここまで
		
		if(hasFeature(OreFeatureType.ORE) && hasFeature(OreFeatureType.DUST)) {
			// 鉱石から粉2つ TODO: 独自の粉砕機も実装したい
			RecipeRegisterHelper.addRecipeOreToDust((Block)getFeature(OreFeatureType.ORE), (Item)getFeature(OreFeatureType.DUST));
			RecipeRegisterHelper.addRecipeOreToDust(getFeature(OreFeatureType.ORE).getOreDictionaryKey(), (Item)getFeature(OreFeatureType.DUST));
		}
		
		if(hasGemOrIngot && hasFeature(OreFeatureType.BLOCK)) {
			OreFeatureBlockBase block = (OreFeatureBlockBase) getFeature(OreFeatureType.BLOCK);
			String blockOredict = block.getOreDictionaryKey();
			
			// ブロックの解凍
			RecipeRegisterHelper.addRecipeBlockExpand(block, gem_ingot);
			RecipeRegisterHelper.addRecipeBlockExpand(blockOredict, gem_ingot);
			
			// ブロックへ圧縮
			RecipeRegisterHelper.addRecipeBlockCompress(gem_ingot, block);
			RecipeRegisterHelper.addRecipeBlockCompress(gem_ingotOredict, block);
		}
		
		if(hasGemOrIngot && hasFeature(OreFeatureType.GEAR)) {
			Item gear = (Item) getFeature(OreFeatureType.GEAR);
			
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
		
		if(hasGemOrIngot && hasFeature(OreFeatureType.PICKAXE)) {
			Item pickaxe = (Item) getFeature(OreFeatureType.PICKAXE);
			
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
		
		if(hasGemOrIngot && hasFeature(OreFeatureType.DUST)) {
			Item dust = (Item) getFeature(OreFeatureType.DUST);
			
			
			// ジェムOrインゴットから粉
			RecipeRegisterHelper.addRecipeIngotToDust(gem_ingot, dust);
			RecipeRegisterHelper.addRecipeIngotToDust(gem_ingotOredict, dust);
		}
		
		if(hasFeature(OreFeatureType.INGOT) && hasFeature(OreFeatureType.NUGGET)) {
			OreFeatureItemBase nugget = (OreFeatureItemBase) getFeature(OreFeatureType.NUGGET);
			OreFeatureItemBase ingot = (OreFeatureItemBase) getFeature(OreFeatureType.INGOT);
			String nuggetOredict = nugget.getOreDictionaryKey();
			String ingotOredict = ingot.getOreDictionaryKey();
			
			// ナゲットからインゴット
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nugget));
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nuggetOredict));
			
			// インゴットからナゲット
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingot));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingotOredict));
		}
		
		if(hasGemOrIngot && hasFeature(OreFeatureType.ORE)) {
			// 鉱石を精錬してジェムOrインゴット
			RecipeRegisterHelper.addSmeltingOreToIngot((Block)getFeature(OreFeatureType.ORE) , gem_ingot);
		}
		
		if(hasGemOrIngot && hasFeature(OreFeatureType.DUST)) {
			// 粉を精錬してジェムOrインゴット
			RecipeRegisterHelper.addSmeltingDustToIngot((Item)getFeature(OreFeatureType.DUST), gem_ingot);
		}
		
	}
	
	public void register() {
		getFeature(OreFeatureType.ORE).register();
		getFeature(OreFeatureType.DUST).register();
		getFeature(OreFeatureType.BLOCK).register();
		getFeature(OreFeatureType.GEAR).register();
	}
	
	public void loadOreGenConfig(Configuration cfg) {
		if(!hasFeature(OreFeatureType.ORE)) return;
		((BlockOre) getFeature(OreFeatureType.ORE)).loadConfig(cfg);
	}
	
	public void loadToolConfig(Configuration cfg) {
		OreFeatureItemBase gem_ingot;
		if(hasFeature(OreFeatureType.INGOT)) {
			gem_ingot = (OreFeatureItemBase) getFeature(OreFeatureType.INGOT);
		}
		else if(hasFeature(OreFeatureType.GEM)) {
			gem_ingot = (OreFeatureItemBase) getFeature(OreFeatureType.GEM);
		}
		else {
			return;
		}
		
		String toolNameLiteral = "pickaxe" + StringUtil.ToCase_XxxXxx(getOreId());
		String categoryName = toolNameLiteral;
		
		String name = ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(getOreId()) + ".pickaxe";
		
		int harvestLevel = cfg.getInt(
				toolNameLiteral + "_HarvestLevel", categoryName, toolConfigDefaultHarvestLevel, 0, 10,
				toolNameLiteral + "のハーベストレベル");
		int maxUses = cfg.getInt(
				toolNameLiteral + "_MaxUses", categoryName, toolConfigDefaultMaxUses, 0, 4096, toolNameLiteral + "の使用可能回数(?)");
		float efficiency = cfg.getFloat(
				toolNameLiteral + "_Efficiency", categoryName, toolConfigDefaultEfficiency, 0, 4096, toolNameLiteral + "の採掘効率");
		float damage = cfg.getFloat(
				toolNameLiteral + "_Damage", categoryName, toolConfigDefaultDamage, 0, 4096, toolNameLiteral + "のダメージ量");
		int enchantability = cfg.getInt(
				toolNameLiteral + "_Enchantability", categoryName, toolConfigDefaultEnchantability, 0, 4096,
				toolNameLiteral + "のエンチャントの付きやすさ");
		
		toolMaterial = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability)
				.setRepairItem(new ItemStack(gem_ingot));
	}
	
	public void registerToWorldGenerators() {
		if(!hasFeature(OreFeatureType.ORE)) return;
		BlockOre ore = (BlockOre) getFeature(OreFeatureType.ORE);
		WorldGenerators.oreGenerator.addOreGenerator(ore, ore.getOreGeneratorConfig());
	}
	
	public void setToolConfigDefaultHarvestLevel(int value) {
		this.toolConfigDefaultHarvestLevel = value;
	}

	public void setToolConfigDefaultMaxUses(int value) {
		this.toolConfigDefaultMaxUses = value;
	}

	public void setToolConfigDefaultEfficiency(float value) {
		this.toolConfigDefaultEfficiency = value;
	}

	public void setToolConfigDefaultDamage(float value) {
		this.toolConfigDefaultDamage = value;
	}

	public void setToolConfigDefaultEnchantability(int value) {
		this.toolConfigDefaultEnchantability = value;
	}
	
	public OreID getOreId() {
		return this._oreName;
	}
	
	public void includeFeature(OreFeatureType featureType) {
		existFeatures.put(featureType, true);
	}
	
	public void excludeFeature(OreFeatureType featureType) {
		existFeatures.put(featureType, false);
	}
	
	public boolean hasFeature(OreFeatureType featureType) {
		return existFeatures.get(featureType);
	}
	
	public IOreFeature getFeature(OreFeatureType featureType) {
		return features.get(featureType);
	}
	
}