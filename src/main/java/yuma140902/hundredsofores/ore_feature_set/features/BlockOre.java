package yuma140902.hundredsofores.ore_feature_set.features;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.ore_feature_set.OreFeatureBlockBase;
import yuma140902.hundredsofores.ore_feature_set.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class BlockOre extends OreFeatureBlockBase {
	
	private static final int DEFAULT_HARVEST_LEVEL = 0;
	
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
