package yuma140902.hundredsofores.orefamilies;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.core.OreFamilyMemberBlockBase;
import yuma140902.hundredsofores.orefamilies.core.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class BlockCompressedIngot extends OreFamilyMemberBlockBase {

	public BlockCompressedIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public BlockCompressedIngot(OreID oreName) {
		super(oreName);
		this.setStepSound(soundTypeMetal);
	}
	
	private final String _oreDictKey = "block" + StringUtil.ToCase_XxxXxx(_oreName); 
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setBlockName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_block");
		this.setBlockTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_block");
		GameRegistry.registerBlock(this, ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_block");
		OreDictionary.registerOre(_oreDictKey, this);
	}
}
