package yuma140902.hundredsofores.ore_feature_set.features;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.ore_feature_set.OreFamilyFeatureBlockBase;
import yuma140902.hundredsofores.ore_feature_set.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class BlockCompressedBlock extends OreFamilyFeatureBlockBase {

	public BlockCompressedBlock(String oreName) {
		this(new OreID(oreName));
	}
	
	public BlockCompressedBlock(OreID oreName) {
		super(oreName);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
	}
	
	private final @Nonnull String _oreDictKey = "block" + StringUtil.ToCase_XxxXxx(_oreName);
	
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setBlockName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_block");
		this.setBlockTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_block");
		GameRegistry.registerBlock(this, StringUtil.ToCase_xxx_xxx(_oreName) + "_block");
		OreDictionary.registerOre(_oreDictKey, this);
	}
}
