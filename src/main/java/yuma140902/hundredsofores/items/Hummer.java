package yuma140902.hundredsofores.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.core.IRegisterable;

public class Hummer extends Item implements IRegisterable {

	public Hummer() {
		super();
		setMaxStackSize(1);
		setNoRepair();
		setMaxDamage(127);
	}
	
	@Override
	public void register() {
		this.setCreativeTab(ModHundredsOfOres.MOD_CREATIVETAB);
		this.setUnlocalizedName(ModHundredsOfOres.MOD_ID + ".hummer");
		this.setTextureName(ModHundredsOfOres.MOD_ID + ":hummer");
		GameRegistry.registerItem(this, "hummer");
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_) {
		return false;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		if(itemStack != null && itemStack.getItem() == this) {
			itemStack.setItemDamage(itemStack.getItemDamage() + 1);
		}
		return itemStack;
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
}
