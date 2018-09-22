package yuma140902.hundredsofores.orefamilies;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.core.OreFamilyMemberItemBase;
import yuma140902.hundredsofores.orefamilies.core.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class ItemNugget extends OreFamilyMemberItemBase {

	public ItemNugget(String oreName) {
		this(new OreID(oreName));
	}
	
	public ItemNugget(OreID oreName) {
		super(oreName);
	}
	
	private final String _oreDictKey = "nugget" + StringUtil.ToCase_XxxXxx(_oreName); 
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_nugget");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_nugget");
		GameRegistry.registerItem(this, ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_nugget");
		OreDictionary.registerOre(_oreDictKey, this);		
	}
	
}
