package yuma140902.hundredsofores.orefamilies;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.core.IOreFamilyMember;
import yuma140902.hundredsofores.orefamilies.core.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class ItemPickaxe extends net.minecraft.item.ItemPickaxe implements IOreFamilyMember {
	
	private OreID _oreName;
	private @Nonnull String _oreDictKey = "pickaxe" + StringUtil.ToCase_XxxXxx(_oreName);
	
	public ItemPickaxe(String oreName, ToolMaterial material) {
		this(new OreID(oreName), material);
	}
	
	public ItemPickaxe(OreID oreName, ToolMaterial material) {
		super(material);
		_oreName = oreName;
	}

	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(_oreName) + "_pickaxe");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":" + StringUtil.ToCase_xxx_xxx(_oreName) + "_pickaxe");
		GameRegistry.registerItem(this, StringUtil.ToCase_xxx_xxx(_oreName) + "_pickaxe");
		OreDictionary.registerOre(_oreDictKey, this);		
	}

	@Override
	public String getOreDictionaryKey() {
		return _oreDictKey;
	}
	
}
