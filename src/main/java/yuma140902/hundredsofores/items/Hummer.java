package yuma140902.hundredsofores.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.core.IRegisterable;

public class Hummer extends Item implements IRegisterable {

	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + ".hummer");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":hummer");
		GameRegistry.registerItem(this, "hummer");
	}
	
}
