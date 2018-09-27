package yuma140902.hundredsofores.ore_feature_set;

import net.minecraftforge.common.config.Configuration;
import yuma140902.hundredsofores.util.ListUtil;

public class OreGenConfig {
	public OreGenConfig() {
		setDefaultValues();
	}
	
	public OreGenConfig(int spawnTries, int spawnSize, int maxHeight, int minHeight, int... dimensionBlackList) {
		this.enabled = true;
		this.spawnTries = spawnTries;
		this.spawnSize = spawnSize;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.dimensionBlackList = dimensionBlackList;
	}
	
	public boolean enabled;
	public int spawnTries;
	public int spawnSize;
	public int maxHeight;
	public int minHeight;
	public int[] dimensionBlackList;
	
	public int heightDiff; //この項目はキャッシュとして使用されます。
	
	
	
	protected boolean defaultIsOrdinaryGenEnabled = true;
	protected int defaultSpawnTries = 20;
	protected int defaultSpawnSize = 8;
	protected int defaultMaxHeight = 64;
	protected int defaultMinHeight = 0;
	protected String[] defaultDimensionBlackListStr = new String[] {};
	
	public void setDefaultValues() {
		this.enabled = defaultIsOrdinaryGenEnabled;
		this.spawnTries = defaultSpawnTries;
		this.spawnSize = defaultSpawnSize;
		this.maxHeight = defaultMaxHeight;
		this.minHeight = defaultMinHeight;
		this.dimensionBlackList = ListUtil.ToIntList(defaultDimensionBlackListStr);
	}
	
	public void setDefaultIsOrdinaryGenEnabled(boolean value) {
		this.defaultIsOrdinaryGenEnabled = value;
	}
	
	public void setDefaultSpawnTries(int value) {
		this.defaultSpawnTries = value;
	}
	
	public void setDefaultSpawnSize(int value) {
		this.defaultSpawnSize = value;
	}
	
	public void setDefaultMaxHeight(int value) {
		this.defaultMaxHeight = value;
	}
	
	public void setDefaultMinHeight(int value) {
		this.defaultMinHeight = value;
	}
	
	public void setDefaultDimensionBlackListStr(String[] value) {
		this.defaultDimensionBlackListStr = value;
	}
	
	public void loadFromConfigFile(Configuration cfg, String oreNameLiteral) {
		this.enabled = cfg.getBoolean(
				oreNameLiteral + "_isDefaultGenEnabled", "gen_" + oreNameLiteral, this.defaultIsOrdinaryGenEnabled,
				oreNameLiteral + "の標準の生成が有効になっているかどうか");
		this.spawnTries = cfg.getInt(
				oreNameLiteral + "_SpawnTries", "gen_" + oreNameLiteral, this.defaultSpawnTries, 0, 1024,
				oreNameLiteral + "の生成確率");
		this.spawnSize = cfg.getInt(
				oreNameLiteral + "_SpawnSize", "gen_" + oreNameLiteral, this.defaultSpawnSize, 0, 1024,
				oreNameLiteral + "が一度に生成される数");
		this.maxHeight = cfg.getInt(
				oreNameLiteral + "_MaxHeight", "gen_" + oreNameLiteral, this.defaultMaxHeight, 0, 256,
				oreNameLiteral + "が生成される最高の高さ");
		this.minHeight = cfg.getInt(
				oreNameLiteral + "_MinHeight", "gen_" + oreNameLiteral, this.defaultMinHeight, 0, 256,
				oreNameLiteral + "が生成される最低の高さ");
		
		String[] dimensionBlackListStr = cfg.getStringList(
				oreNameLiteral + "_dimensionBlackList", "gen_" + oreNameLiteral, this.defaultDimensionBlackListStr,
				oreNameLiteral + "を生成しないディメンションID");
		
		if (this.enabled) {
			this.dimensionBlackList = ListUtil.ToIntList(dimensionBlackListStr);
		}
	}
}
