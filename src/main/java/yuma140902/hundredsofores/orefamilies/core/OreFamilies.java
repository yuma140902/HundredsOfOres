package yuma140902.hundredsofores.orefamilies.core;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.util.ListUtil;

public final class OreFamilies {
	
	private OreFamilies() {};
	
	public static int[] globalDimensionBlackList;

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
			ruby.loadToolConfig(toolConfig);
		}
		finally {
			toolConfig.save();
		}
	}
	
	public static void init() {
		tin.init();
		copper.init();
		silver.init();
		lead.init();
		rainbow.init();
		ruby.init();
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
	
	public static final OreFamilyWithGemOrIngot tin = new OreFamilyWithIngot(TIN_NAME);
	public static final OreFamilyWithGemOrIngot copper = new OreFamilyWithIngot(COPPER_NAME);
	public static final OreFamilyWithGemOrIngot silver = new OreFamilyWithIngot(SILVER_NAME) {{
		blockOre.setOreGenConfigDefaultSpawnTries(10);
	}};
	public static final OreFamilyWithGemOrIngot lead = new OreFamilyWithIngot(LEAD_NAME);
	public static final OreFamily rainbow = new OreFamily(RAINBOW_NAME) {{
		blockOre.setOreGemConfigDefaultIsOrdinaryGenEnabled(false);
	}};
	public static final OreFamilyWithGemOrIngot ruby = new OreFamilyWithGem(RUBY_NAME) {{
		blockOre.setOreGenConfigDefaultSpawnTries(10);
	}};
}
