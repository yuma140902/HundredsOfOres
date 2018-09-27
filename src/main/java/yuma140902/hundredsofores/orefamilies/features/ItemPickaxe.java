package yuma140902.hundredsofores.orefamilies.features;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.orefamilies.IOreFamilyFeature;
import yuma140902.hundredsofores.orefamilies.OreID;
import yuma140902.hundredsofores.util.StringUtil;

public class ItemPickaxe extends net.minecraft.item.ItemPickaxe implements IOreFamilyFeature {
	
	private OreID _oreName;
	private final @Nonnull String _oreDictKey;
	
	public ItemPickaxe(String oreName, ToolMaterial material) {
		this(new OreID(oreName), material);
	}
	
	public ItemPickaxe(OreID oreName, ToolMaterial material) {
		super(material);
		_oreName = oreName;
		_oreDictKey = "pickaxe" + StringUtil.ToCase_XxxXxx(_oreName);
	}

	@Override
	public void register() {
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