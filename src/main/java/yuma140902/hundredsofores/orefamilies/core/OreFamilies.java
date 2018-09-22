package yuma140902.hundredsofores.orefamilies.core;

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
	}
	
	public static void loadConfigs() {
		Configuration cfg = ModHundredsOfOres.CONFIGURATION;
		try {
			cfg.load();
			
			String[] globalDimensionBlackListStr = 
					cfg.getStringList("GlobalDimensionBlackList", "ore_gen", 
							new String[] {"1", "-1"}, "鉱石を生成しないディメンションIDを指定します。すべての鉱石に適応されます");
			globalDimensionBlackList = ListUtil.ToIntList(globalDimensionBlackListStr);
			
			tin.loadConfig(cfg);
			copper.loadConfig(cfg);
			silver.loadConfig(cfg);
			lead.loadConfig(cfg);
			rainbow.loadConfig(cfg);
		}
		finally {
			cfg.save();
		}
	}
	
	public static void init() {
		tin.init();
		copper.init();
		silver.init();
		lead.init();
		rainbow.init();
	}
	
	public static void registerToWorldGenerators() {
		tin.registerToWorldGenerators();
		copper.registerToWorldGenerators();
		silver.registerToWorldGenerators();
		lead.registerToWorldGenerators();
		rainbow.registerToWorldGenerators();
	}
	
	private static final String COPPER_NAME = "copper";
	private static final String SILVER_NAME = "silver";
	private static final String TIN_NAME = "tin";
	private static final String LEAD_NAME = "lead";
	private static final String RAINBOW_NAME = "rainbow";
	
	public static final OreFamily tin = new OreFamily(TIN_NAME);
	public static final OreFamily copper = new OreFamily(COPPER_NAME);
	public static final OreFamily silver = new OreFamily(SILVER_NAME);
	public static final OreFamily lead = new OreFamily(LEAD_NAME);
	public static final OreFamily rainbow = new OreFamily(RAINBOW_NAME);
}
