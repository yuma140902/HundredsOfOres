package yuma140902.hundredsofores.orefamilies.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.orefamilies.ItemIngot;
import yuma140902.hundredsofores.orefamilies.ItemNugget;

public class OreFamilyWithIngot extends OreFamilyWithGemOrIngot {
	public OreFamilyWithIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithIngot(OreID oreName) {
		super(oreName);
		this.itemIngot = new ItemIngot(oreName);
		this.itemNugget = new ItemNugget(oreName);
	}
	
	@Override
	public void register() {
		super.register();
		itemIngot.register();
		itemNugget.register();
	}
	
	@Override
	public void init() {
		super.init();
		
		Item ingot = itemIngot;
		Item nugget = itemNugget;
		
		String ingotOredict = itemIngot.getOreDictionaryKey();
		String nuggetOredict = itemNugget.getOreDictionaryKey();
		
		// ナゲットからインゴット
		GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nugget));
		if (nuggetOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nuggetOredict));
		
		// インゴットからナゲット
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingotOredict));
	}
	
	protected ItemIngot itemIngot;
	protected ItemNugget itemNugget;
	
	public ItemIngot getItemIngot() {
		return itemIngot;
	}
	
	public ItemNugget getItemNugget() {
		return itemNugget;
	}
	
	@Override
	public OreFamilyMemberItemBase getGemOrIngot() {
		return itemIngot;
	}
}
