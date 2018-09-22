package yuma140902.hundredsofores.orefamilies;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.core.OreFamilyMemberItemBase;
import yuma140902.hundredsofores.orefamilies.core.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class ItemGem extends OreFamilyMemberItemBase {
	public ItemGem(String oreName) {
		this(new OreID(oreName));
	}
	
	public ItemGem(OreID oreName) {
		super(oreName);
	}
	
	private final String _oreDictKey = "gem" + StringUtil.ToCase_XxxXxx(_oreName); 
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_gem");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_gem");
		GameRegistry.registerItem(this, StringUtil.ToCase_xxx_xxx(_oreName) + "_gem");
		OreDictionary.registerOre(_oreDictKey, this);
	}
}
