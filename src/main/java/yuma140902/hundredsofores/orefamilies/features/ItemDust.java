package yuma140902.hundredsofores.orefamilies.features;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.OreFamilyFeatureItemBase;
import yuma140902.hundredsofores.orefamilies.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class ItemDust extends OreFamilyFeatureItemBase {
	
	public ItemDust(String oreName) {
		this(new OreID(oreName));
	}
	
	public ItemDust(OreID oreName) {
		super(oreName);
	}
	
	private final @Nonnull String _oreDictKey = "dust" + StringUtil.ToCase_XxxXxx(_oreName);
	
	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_dust");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_dust");
		GameRegistry.registerItem(this, StringUtil.ToCase_xxx_xxx(_oreName) + "_dust");
		OreDictionary.registerOre(_oreDictKey, this);
	}
}
