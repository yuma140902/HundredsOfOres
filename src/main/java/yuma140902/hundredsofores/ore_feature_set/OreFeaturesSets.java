package yuma140902.hundredsofores.ore_feature_set;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.util.ListUtil;

public final class OreFeaturesSets {
	
	private OreFeaturesSets() {};
	
	public static int[] globalDimensionBlackList;
	
	public static void setFeatureValues() {
		tin.setFeatureValues();
		copper.setFeatureValues();
		silver.setFeatureValues();
		lead.setFeatureValues();
		rainbow.setFeatureValues();
		ruby.setFeatureValues();
	}
	
	public static void setToolFeatureValues() {
		tin.setToolFeatureValues();
		copper.setToolFeatureValues();
		silver.setToolFeatureValues();
		lead.setToolFeatureValues();
		rainbow.setToolFeatureValues();
		ruby.setToolFeatureValues();
	}

	public static void register() {
		tin.register();
		copper.register();
		silver.register();
		lead.register();
		rainbow.register();
		ruby.register();
	}
	
	public static void loadConfigs() {
		Configuration oreGenConfig = new Configuration(new File(ModHundredsOfOres.ORE_GEN_CONFIG_FILE_NAME));
		try {
			oreGenConfig.load();
			
			String[] globalDimensionBlackListStr = 
					oreGenConfig.getStringList("GlobalDimensionBlackList", "gen_common", 
							new String[] {"1", "-1"}, "鉱石を生成しないディメンションIDを指定します。すべての鉱石に適応されます");
			globalDimensionBlackList = ListUtil.ToIntList(globalDimensionBlackListStr);
			
			tin.loadOreGenConfig(oreGenConfig);
			copper.loadOreGenConfig(oreGenConfig);
			silver.loadOreGenConfig(oreGenConfig);
			lead.loadOreGenConfig(oreGenConfig);
			rainbow.loadOreGenConfig(oreGenConfig);
			ruby.loadOreGenConfig(oreGenConfig);
		}
		finally {
			oreGenConfig.save();
		}
		
		Configuration toolConfig = new Configuration(new File(ModHundredsOfOres.TOOL_CONFIG_FILE_NAME));
		try {
			toolConfig.load();
			
			tin.loadToolConfig(toolConfig);
			copper.loadToolConfig(toolConfig);
			silver.loadToolConfig(toolConfig);
			lead.loadToolConfig(toolConfig);
			rainbow.loadToolConfig(toolConfig);
			ruby.loadToolConfig(toolConfig);
		}
		finally {
			toolConfig.save();
		}
	}
	
	public static void init() {
		tin.registerRecipes();
		copper.registerRecipes();
		silver.registerRecipes();
		lead.registerRecipes();
		rainbow.registerRecipes();
		ruby.registerRecipes();
	}
	
	public static void registerToWorldGenerators() {
		tin.registerToWorldGenerators();
		copper.registerToWorldGenerators();
		silver.registerToWorldGenerators();
		lead.registerToWorldGenerators();
		rainbow.registerToWorldGenerators();
		ruby.registerToWorldGenerators();
	}
	
	private static final String COPPER_NAME = "copper";
	private static final String SILVER_NAME = "silver";
	private static final String TIN_NAME = "tin";
	private static final String LEAD_NAME = "lead";
	private static final String RAINBOW_NAME = "rainbow";
	private static final String RUBY_NAME = "ruby";
	
	public static final OreFeaturesSet tin = new OreFeaturesSet(TIN_NAME)
			.includeFeature(OreFeatureType.INGOT)
			.includeFeature(OreFeatureType.NUGGET);
	public static final OreFeaturesSet copper = new OreFeaturesSet(COPPER_NAME)
			.includeFeature(OreFeatureType.INGOT)
			.includeFeature(OreFeatureType.NUGGET);
	//TODO: OreGenConfigのデフォルト値を書き換える方法を確保
	//ToolMaterialのConfigもあるので、ConfigManager的なものを作ってもいいかもしれない
	//しかし、ForgeConfigの再発明になってしまうかもしれない
	public static final OreFeaturesSet silver = new OreFeaturesSet(SILVER_NAME)
			.includeFeature(OreFeatureType.INGOT)
			.includeFeature(OreFeatureType.NUGGET);
//	{{
//		((BlockOre)getFeature(OreFeatureType.ORE)).setOreGenConfigDefaultSpawnTries(10);
//	}};
	public static final OreFeaturesSet lead = new OreFeaturesSet(LEAD_NAME)
			.includeFeature(OreFeatureType.INGOT)
			.includeFeature(OreFeatureType.NUGGET);
	public static final OreFeaturesSet rainbow = new OreFeaturesSet(RAINBOW_NAME)
			.includeFeature(OreFeatureType.GEM);
//	{{
//		((BlockOre)getFeature(OreFeatureType.ORE)).setOreGenConfigDefaultIsOrdinaryGenEnabled(false);
//	}};
	public static final OreFeaturesSet ruby = new OreFeaturesSet(RUBY_NAME)
			.includeFeature(OreFeatureType.GEM);
//	{{
//		((BlockOre)getFeature(OreFeatureType.ORE)).setOreGenConfigDefaultSpawnTries(10);
//	}};
}
