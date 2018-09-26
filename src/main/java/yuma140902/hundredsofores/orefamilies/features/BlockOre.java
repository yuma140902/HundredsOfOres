package yuma140902.hundredsofores.orefamilies.features;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.OreFamilyFeatureBlockBase;
import yuma140902.hundredsofores.orefamilies.OreGenConfig;
import yuma140902.hundredsofores.orefamilies.OreID;
import yuma140902.hundredsofores.util.ListUtil;
import yuma140902.hundredsofores.util.StringUtil;

public class BlockOre extends OreFamilyFeatureBlockBase {
	
	private static final int DEFAULT_HARVEST_LEVEL = 0;
	
	protected @Nullable OreGenConfig oreGenConfig;
	
	protected boolean oreGenConfigDefaultIsOrdinaryGenEnabled = true;
	protected int oreGenConfigDefaultSpawnTries = 20;
	protected int oreGenConfigDefaultSpawnSize = 8;
	protected int oreGenConfigDefaultMaxHeight = 64;
	protected int oreGenConfigDefaultMinHeight = 0;
	protected String[] oreGenConfigDefaultDimensionBlackListStr = new String[] {};
	
	public void setOreGenConfigDefaultIsOrdinaryGenEnabled(boolean value) {
		this.oreGenConfigDefaultIsOrdinaryGenEnabled = value;
	}
	
	public void setOreGenConfigDefaultSpawnTries(int value) {
		this.oreGenConfigDefaultSpawnTries = value;
	}
	
	public void setOreGenConfigDefaultSpawnSize(int value) {
		this.oreGenConfigDefaultSpawnSize = value;
	}
	
	public void setOreGenConfigDefaultMaxHeight(int value) {
		this.oreGenConfigDefaultMaxHeight = value;
	}
	
	public void setOreGenConfigDefaultMinHeight(int value) {
		this.oreGenConfigDefaultMinHeight = value;
	}
	
	public void setOreGenConfigDefaultDimensionBlackListStr(String[] value) {
		this.oreGenConfigDefaultDimensionBlackListStr = value;
	}
	
	public BlockOre(String oreName) {
		this(new OreID(oreName));
	}
	
	public BlockOre(OreID oreName) {
		super(oreName);
		this.setHarvestLevel("pickaxe", DEFAULT_HARVEST_LEVEL);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}
	
	public void loadConfig(Configuration cfg) {
		String oreNameLiteral = "ore" + StringUtil.ToCase_XxxXxx(_oreName);
		boolean isOrdinaryGenEnabled = cfg.getBoolean(
				oreNameLiteral + "_isDefaultGenEnabled", "gen_" + oreNameLiteral, oreGenConfigDefaultIsOrdinaryGenEnabled,
				oreNameLiteral + "の標準の生成が有効になっているかどうか");
		int spawnTries = cfg.getInt(
				oreNameLiteral + "_SpawnTries", "gen_" + oreNameLiteral, oreGenConfigDefaultSpawnTries, 0, 1024,
				oreNameLiteral + "の生成確率");
		int spawnSize = cfg.getInt(
				oreNameLiteral + "_SpawnSize", "gen_" + oreNameLiteral, oreGenConfigDefaultSpawnSize, 0, 1024,
				oreNameLiteral + "が一度に生成される数");
		int maxHeight = cfg.getInt(
				oreNameLiteral + "_MaxHeight", "gen_" + oreNameLiteral, oreGenConfigDefaultMaxHeight, 0, 256,
				oreNameLiteral + "が生成される最高の高さ");
		int minHeight = cfg.getInt(
				oreNameLiteral + "_MinHeight", "gen_" + oreNameLiteral, oreGenConfigDefaultMinHeight, 0, 256,
				oreNameLiteral + "が生成される最低の高さ");
		
		String[] dimensionBlackListStr = cfg.getStringList(
				oreNameLiteral + "_dimensionBlackList", "gen_" + oreNameLiteral, oreGenConfigDefaultDimensionBlackListStr,
				oreNameLiteral + "を生成しないディメンションID");
		
		if (isOrdinaryGenEnabled) {
			int[] dimensionBlackList = ListUtil.ToIntList(dimensionBlackListStr);
			
			oreGenConfig = new OreGenConfig(spawnTries, spawnSize, maxHeight, minHeight, dimensionBlackList);
		}
		else {
			oreGenConfig = null;
		}
	}
	
	public @Nullable OreGenConfig getOreGeneratorConfig() {
		return oreGenConfig;
	}
	
	private final @Nonnull String _oreDictKey = "ore" + StringUtil.ToCase_XxxXxx(_oreName);
	
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setBlockName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_ore");
		this.setBlockTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_ore");
		GameRegistry.registerBlock(this, StringUtil.ToCase_xxx_xxx(_oreName) + "_ore");
		OreDictionary.registerOre(_oreDictKey, this);
	}
}
