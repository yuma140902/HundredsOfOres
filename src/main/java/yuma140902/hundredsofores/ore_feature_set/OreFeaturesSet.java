package yuma140902.hundredsofores.ore_feature_set;

import java.util.EnumMap;
import java.util.Map;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
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
	
	protected OreGenConfig oreGenConfig = new OreGenConfig();
	protected ToolConfig toolConfig = new ToolConfig();
	
	protected ToolMaterial toolMaterial;
	
	public OreFeaturesSet(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFeaturesSet(OreID oreName) {
		this._oreName = oreName;
		
		for(OreFeatureType key : OreFeatureType.values()) {
			features.put(key, IOreFeature.EMPTY);
			existFeatures.put(key, false);
		}
		
		includeFeature(OreFeatureType.ORE);
		includeFeature(OreFeatureType.BLOCK);
		includeFeature(OreFeatureType.PICKAXE);
		includeFeature(OreFeatureType.DUST);
		includeFeature(OreFeatureType.GEAR);
	}
	
	// existFeaturesの値に基づいてfeaturesを初期化します。(pickaxe以外)
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
				}
			}
		}
	}
	
	public void setToolFeatureValues() {
		if(!hasFeature(OreFeatureType.PICKAXE)) return;
		features.put(OreFeatureType.PICKAXE, new ItemPickaxe(_oreName, toolMaterial));
	}
	
	public void registerRecipes() {
		RecipeRegisterHelper.addRecipesFromOreFeaturesSet(this);
	}
	
	public void register() {
		for(OreFeatureType key : OreFeatureType.values()) {
			if(hasFeature(key)) {
				getFeature(key).register();
			}
		}
	}
	
	public void loadOreGenConfig(Configuration cfg) {
		if(!hasFeature(OreFeatureType.ORE)) oreGenConfig.setDefaultIsOrdinaryGenEnabled(false);
		oreGenConfig.loadFromConfigFile(cfg, "ore" + StringUtil.ToCase_XxxXxx(_oreName));
		
		//oreが存在しない場合はenabledを強制的にfalseに書き換え
		if(!hasFeature(OreFeatureType.ORE)) {
			oreGenConfig.enabled = false;
		}
	}
	
	public void loadToolConfig(Configuration cfg) {
		if(!hasFeature(OreFeatureType.PICKAXE)) return;
		
		String toolNameLiteral = "pickaxe" + StringUtil.ToCase_XxxXxx(getOreId());
		
		toolConfig.loadFromConfigFile(cfg, toolNameLiteral);
		
		String name = ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(getOreId()) + ".pickaxe";
		
		toolMaterial = EnumHelper.addToolMaterial
				(name, toolConfig.harvestLevel, toolConfig.maxUses, toolConfig.efficiency, toolConfig.damage, toolConfig.enchantability);
		
		
		OreFeatureItemBase gem_ingot;
		if(hasFeature(OreFeatureType.INGOT)) {
			gem_ingot = (OreFeatureItemBase) getFeature(OreFeatureType.INGOT);
		}
		else if(hasFeature(OreFeatureType.GEM)) {
			gem_ingot = (OreFeatureItemBase) getFeature(OreFeatureType.GEM);
		}
		else {
			gem_ingot = null;
		}
		
		if(gem_ingot != null) {
			toolMaterial = toolMaterial.setRepairItem(new ItemStack(gem_ingot));
		}
	}
	
	public void registerToWorldGenerators() {
		if(!hasFeature(OreFeatureType.ORE)) return;
		BlockOre ore = (BlockOre) getFeature(OreFeatureType.ORE);
		WorldGenerators.oreGenerator.addOreGenerator(ore, oreGenConfig);
	}
	
	public OreID getOreId() {
		return this._oreName;
	}
	
	public OreFeaturesSet includeFeature(OreFeatureType featureType) {
		existFeatures.put(featureType, true);
		return this;
	}
	
	public OreFeaturesSet excludeFeature(OreFeatureType featureType) {
		existFeatures.put(featureType, false);
		return this;
	}
	
	public boolean hasFeature(OreFeatureType featureType) {
		return existFeatures.get(featureType);
	}
	
	public IOreFeature getFeature(OreFeatureType featureType) {
		return features.get(featureType);
	}
	
}
