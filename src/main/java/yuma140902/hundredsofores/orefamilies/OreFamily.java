package yuma140902.hundredsofores.orefamilies;

import java.util.EnumMap;
import java.util.Map;
import net.minecraftforge.common.config.Configuration;
import yuma140902.hundredsofores.orefamilies.features.BlockCompressedBlock;
import yuma140902.hundredsofores.orefamilies.features.BlockOre;
import yuma140902.hundredsofores.orefamilies.features.ItemDust;
import yuma140902.hundredsofores.orefamilies.features.ItemGear;
import yuma140902.hundredsofores.orefamilies.features.ItemGem;
import yuma140902.hundredsofores.orefamilies.features.ItemIngot;
import yuma140902.hundredsofores.orefamilies.features.ItemNugget;
import yuma140902.hundredsofores.recipes.RecipeRegisterHelper;
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
	
	public OreFamily(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamily(OreID oreName) {
		this._oreName = oreName;
		
		for(FeatureType key : FeatureType.values()) {
			features.put(key, IOreFamilyFeature.EMPTY);
		}
	}
	
	// existFeaturesの値に基づいてfeaturesを初期化します。(ただしpickaxeは除く)
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
//						features.put(FeatureType.PICKAXE, new ItemPickaxe());
						break;
				}
			}
		}
	}
	
	public void init() {
		// レシピの追加など
		BlockOre ore = (BlockOre) getFeature(FeatureType.ORE);
		ItemDust dust = (ItemDust) getFeature(FeatureType.DUST);
		
		String oreOredict = ore.getOreDictionaryKey();
		
		// 鉱石から粉2つ TODO: 独自の粉砕機も実装したい
		RecipeRegisterHelper.addRecipeOreToDust(ore, dust);
		RecipeRegisterHelper.addRecipeOreToDust(oreOredict, dust);
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
	
	public void registerToWorldGenerators() {
		BlockOre ore = (BlockOre) getFeature(FeatureType.ORE);
		WorldGenerators.oreGenerator.addOreGenerator(ore, ore.getOreGeneratorConfig());
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
