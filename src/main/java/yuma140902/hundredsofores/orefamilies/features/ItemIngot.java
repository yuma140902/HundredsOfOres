package yuma140902.hundredsofores.orefamilies.features;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.OreFamilyMemberItemBase;
import yuma140902.hundredsofores.orefamilies.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class ItemIngot extends OreFamilyMemberItemBase {

	public ItemIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public ItemIngot(OreID oreName) {
		super(oreName);
	}
	
	private final @Nonnull String _oreDictKey = "ingot" + StringUtil.ToCase_XxxXxx(_oreName); 
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_ingot");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_ingot");
		GameRegistry.registerItem(this, StringUtil.ToCase_xxx_xxx(_oreName) + "_ingot");
		OreDictionary.registerOre(_oreDictKey, this);		
	}
	
}
