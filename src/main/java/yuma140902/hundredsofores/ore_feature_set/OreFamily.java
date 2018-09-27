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
public class OreFamily {
	
	private OreID _oreName;
	
	protected Map<FeatureType, IOreFamilyFeature> features = new EnumMap<>(FeatureType.class);
	protected Map<FeatureType, Boolean> existFeatures = new EnumMap<>(FeatureType.class);
	
	protected int toolConfigDefaultHarvestLevel = 2;
	protected int toolConfigDefaultMaxUses = 300;
	protected float toolConfigDefaultEfficiency = 7.0F;
	protected float toolConfigDefaultDamage = 0.0F;
	protected int toolConfigDefaultEnchantability = 14;
	
	protected ToolMaterial toolMaterial;
	
	public OreFamily(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamily(OreID oreName) {
		this._oreName = oreName;
		
		for(FeatureType key : FeatureType.values()) {
			features.put(key, IOreFamilyFeature.EMPTY);
		}
	}
	
	// existFeaturesの値に基づいてfeaturesを初期化します。
	public void setValues() {
		for(Map.Entry<FeatureType, Boolean> entry : existFeatures.entrySet()) {
			if(entry.getValue() == true) {
				switch (entry.getKey()) {
					case ORE:
						features.put(FeatureType.ORE, new BlockOre(_oreName));
						break;
					case BLOCK:
						features.put(FeatureType.BLOCK, new BlockCompressedBlock(_oreName));
						break;
					case INGOT:
						features.put(FeatureType.INGOT, new ItemIngot(_oreName));
						break;
					case NUGGET:
						features.put(FeatureType.NUGGET, new ItemNugget(_oreName));
						break;
					case GEM:
						features.put(FeatureType.GEM, new ItemGem(_oreName));
						break;
					case DUST:
						features.put(FeatureType.DUST, new ItemDust(_oreName));
						break;
					case GEAR:
						features.put(FeatureType.GEAR, new ItemGear(_oreName));
						break;
					case PICKAXE:
						features.put(FeatureType.PICKAXE, new ItemPickaxe(_oreName, toolMaterial));
						break;
				}
			}
		}
	}
	
	public void registerRecipes() {
		
		boolean hasGemOrIngot = hasFeature(FeatureType.INGOT) || hasFeature(FeatureType.GEM);
		OreFamilyFeatureItemBase gem_ingot = null;
		String gem_ingotOredict = null;
		if(hasGemOrIngot) {
			gem_ingot = (OreFamilyFeatureItemBase) (hasFeature(FeatureType.INGOT) ? getFeature(FeatureType.INGOT) : getFeature(FeatureType.GEM));
			gem_ingotOredict = gem_ingot.getOreDictionaryKey();
		}
		//前処理ここまで
		
		if(hasFeature(FeatureType.ORE) && hasFeature(FeatureType.DUST)) {
			// 鉱石から粉2つ TODO: 独自の粉砕機も実装したい
			RecipeRegisterHelper.addRecipeOreToDust((Block)getFeature(FeatureType.ORE), (Item)getFeature(FeatureType.DUST));
			RecipeRegisterHelper.addRecipeOreToDust(getFeature(FeatureType.ORE).getOreDictionaryKey(), (Item)getFeature(FeatureType.DUST));
		}
		
		if(hasGemOrIngot && hasFeature(FeatureType.BLOCK)) {
			OreFamilyFeatureBlockBase block = (OreFamilyFeatureBlockBase) getFeature(FeatureType.BLOCK);
			String blockOredict = block.getOreDictionaryKey();
			
			// ブロックの解凍
			RecipeRegisterHelper.addRecipeBlockExpand(block, gem_ingot);
			RecipeRegisterHelper.addRecipeBlockExpand(blockOredict, gem_ingot);
			
			// ブロックへ圧縮
			RecipeRegisterHelper.addRecipeBlockCompress(gem_ingot, block);
			RecipeRegisterHelper.addRecipeBlockCompress(gem_ingotOredict, block);
		}
		
		if(hasGemOrIngot && hasFeature(FeatureType.GEAR)) {
			Item gear = (Item) getFeature(FeatureType.GEAR);
			
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
		
		if(hasGemOrIngot && hasFeature(FeatureType.PICKAXE)) {
			Item pickaxe = (Item) getFeature(FeatureType.PICKAXE);
			
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
		
		if(hasGemOrIngot && hasFeature(FeatureType.DUST)) {
			Item dust = (Item) getFeature(FeatureType.DUST);
			
			
			// ジェムOrインゴットから粉
			RecipeRegisterHelper.addRecipeIngotToDust(gem_ingot, dust);
			RecipeRegisterHelper.addRecipeIngotToDust(gem_ingotOredict, dust);
		}
		
		if(hasFeature(FeatureType.INGOT) && hasFeature(FeatureType.NUGGET)) {
			OreFamilyFeatureItemBase nugget = (OreFamilyFeatureItemBase) getFeature(FeatureType.NUGGET);
			OreFamilyFeatureItemBase ingot = (OreFamilyFeatureItemBase) getFeature(FeatureType.INGOT);
			String nuggetOredict = nugget.getOreDictionaryKey();
			String ingotOredict = ingot.getOreDictionaryKey();
			
			// ナゲットからインゴット
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nugget));
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nuggetOredict));
			
			// インゴットからナゲット
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingot));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingotOredict));
		}
		
		if(hasGemOrIngot && hasFeature(FeatureType.ORE)) {
			// 鉱石を精錬してジェムOrインゴット
			RecipeRegisterHelper.addSmeltingOreToIngot((Block)getFeature(FeatureType.ORE) , gem_ingot);
		}
		
		if(hasGemOrIngot && hasFeature(FeatureType.DUST)) {
			// 粉を精錬してジェムOrインゴット
			RecipeRegisterHelper.addSmeltingDustToIngot((Item)getFeature(FeatureType.DUST), gem_ingot);
		}
		
	}
	
	public void register() {
		getFeature(FeatureType.ORE).register();
		getFeature(FeatureType.DUST).register();
		getFeature(FeatureType.BLOCK).register();
		getFeature(FeatureType.GEAR).register();
	}
	
	public void loadOreGenConfig(Configuration cfg) {
		((BlockOre) getFeature(FeatureType.ORE)).loadConfig(cfg);
	}
	
	public void loadToolConfig(Configuration cfg) {
		OreFamilyFeatureItemBase gem_ingot;
		if(hasFeature(FeatureType.INGOT)) {
			gem_ingot = (OreFamilyFeatureItemBase) getFeature(FeatureType.INGOT);
		}
		else if(hasFeature(FeatureType.GEM)) {
			gem_ingot = (OreFamilyFeatureItemBase) getFeature(FeatureType.GEM);
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
		if(!hasFeature(FeatureType.ORE)) return;
		BlockOre ore = (BlockOre) getFeature(FeatureType.ORE);
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
	
	public void includeFeature(FeatureType featureType) {
		existFeatures.put(featureType, true);
	}
	
	public void excludeFeature(FeatureType featureType) {
		existFeatures.put(featureType, false);
	}
	
	public boolean hasFeature(FeatureType featureType) {
		return existFeatures.get(featureType);
	}
	
	public IOreFamilyFeature getFeature(FeatureType featureType) {
		return features.get(featureType);
	}
	
}
