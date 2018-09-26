package yuma140902.hundredsofores.orefamilies.features;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.core.OreFamilyMemberItemBase;
import yuma140902.hundredsofores.orefamilies.core.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class ItemGear extends OreFamilyMemberItemBase {

	public ItemGear(String oreName) {
		this(new OreID(oreName));
	}
	
	public ItemGear(OreID oreName) {
		super(oreName);
	}
	
	private final @Nonnull String _oreDictKey = "gear" + StringUtil.ToCase_XxxXxx(_oreName); 
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_gear");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_gear");
		GameRegistry.registerItem(this, StringUtil.ToCase_xxx_xxx(_oreName) + "_gear");
		OreDictionary.registerOre(_oreDictKey, this);
	}
	
}
