package yuma140902.hundredsofores.orefamilies;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.features.BlockOre;
import yuma140902.hundredsofores.util.ListUtil;

public final class OreFamilies {
	
	private OreFamilies() {};
	
	public static int[] globalDimensionBlackList;

	public static void register() {
		tin.setValues();
		copper.setValues();
		silver.setValues();
		lead.setValues();
		rainbow.setValues();
		ruby.setValues();
		
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
	
	public static final OreFamily tin = new OreFamily(TIN_NAME);
	public static final OreFamily copper = new OreFamily(COPPER_NAME);
	public static final OreFamily silver = new OreFamily(SILVER_NAME) {{
		((BlockOre)getFeature(FeatureType.ORE)).setOreGenConfigDefaultSpawnTries(10);
	}};
	public static final OreFamily lead = new OreFamily(LEAD_NAME);
	public static final OreFamily rainbow = new OreFamily(RAINBOW_NAME) {{
		((BlockOre)getFeature(FeatureType.ORE)).setOreGenConfigDefaultIsOrdinaryGenEnabled(false);
	}};
	public static final OreFamily ruby = new OreFamily(RUBY_NAME) {{
		((BlockOre)getFeature(FeatureType.ORE)).setOreGenConfigDefaultSpawnTries(10);
	}};
}
