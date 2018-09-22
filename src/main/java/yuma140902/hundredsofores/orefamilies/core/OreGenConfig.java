package yuma140902.hundredsofores.orefamilies.core;

public class OreGenConfig {
	public OreGenConfig(int spawnTries, int spawnSize, int maxHeight, int minHeight, int... dimensionBlackList) {
		this.spawnTries = spawnTries;
		this.spawnSize = spawnSize;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.dimensionBlackList = dimensionBlackList;
	}
	
	public int spawnTries;
	public int spawnSize;
	public int maxHeight;
	public int minHeight;
	public int[] dimensionBlackList;
	
	public int heightDiff; //この項目はキャッシュとして使用されます。
}
