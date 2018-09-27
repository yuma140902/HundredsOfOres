package yuma140902.hundredsofores.ore_feature_set;

import yuma140902.hundredsofores.util.ListUtil;

public class OreGenConfig {
	public OreGenConfig() {
		updateDefault();
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
	
	public void updateDefault() {
		this.enabled = defaultIsOrdinaryGenEnabled;
		this.spawnTries = defaultSpawnTries;
		this.spawnSize = defaultSpawnSize;
		this.maxHeight = defaultMaxHeight;
		this.minHeight = defaultMinHeight;
		this.dimensionBlackList = ListUtil.ToIntList(defaultDimensionBlackListStr);
	}
	
	public void setdefaultIsOrdinaryGenEnabled(boolean value) {
		this.defaultIsOrdinaryGenEnabled = value;
	}
	
	public void setdefaultSpawnTries(int value) {
		this.defaultSpawnTries = value;
	}
	
	public void setdefaultSpawnSize(int value) {
		this.defaultSpawnSize = value;
	}
	
	public void setdefaultMaxHeight(int value) {
		this.defaultMaxHeight = value;
	}
	
	public void setdefaultMinHeight(int value) {
		this.defaultMinHeight = value;
	}
	
	public void setdefaultDimensionBlackListStr(String[] value) {
		this.defaultDimensionBlackListStr = value;
	}
}
