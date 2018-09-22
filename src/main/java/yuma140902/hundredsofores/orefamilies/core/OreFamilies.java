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
	
	public static void loadToolConfigs() {
		
	}
	
	public static void registerTools() {
		
	}
	
	public static void loadOreGenConfigs() {
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
	
	public static final OreFamily tin = new OreFamilyWithIngot(TIN_NAME);
	public static final OreFamily copper = new OreFamilyWithIngot(COPPER_NAME);
	public static final OreFamily silver = new OreFamilyWithIngot(SILVER_NAME);
	public static final OreFamily lead = new OreFamilyWithIngot(LEAD_NAME);
	public static final OreFamily rainbow = new OreFamily(RAINBOW_NAME) {{
		blockOre.setOreGemConfigDefaultIsOrdinaryGenEnabled(false);
	}};
	public static final OreFamily ruby = new OreFamilyWithGem(RUBY_NAME);
}
