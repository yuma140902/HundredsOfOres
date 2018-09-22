package yuma140902.hundredsofores.orefamilies.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.orefamilies.ItemIngot;

public class OreFamilyWithIngot extends OreFamily {
	public OreFamilyWithIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithIngot(OreID oreName) {
		super(oreName);
		this.itemIngot = new ItemIngot(oreName);
	}
	
	@Override
	public void register() {
		super.register();
		itemIngot.register();
	}
	
	@Override
	public void init() {
		super.init();
		
		Block block = blockCompressedIngot;
		Item ingot = itemIngot;
		Item gear = itemGear;
		Item nugget = itemNugget;
		
		String blockOredict = blockCompressedIngot.getOreDictionaryKey();
		String ingotOredict = itemIngot.getOreDictionaryKey();
		String nuggetOredict = itemNugget.getOreDictionaryKey();
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ingot, 9), block));
		if (blockOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ingot, 9), blockOredict));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingotOredict));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', ingotOredict));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nugget));
		if (nuggetOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nuggetOredict));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingotOredict));
		
		GameRegistry.addSmelting(ore, new ItemStack(ingot), OreFamily.DEFAULT_FURANCE_EXP);
		GameRegistry.addSmelting(dust, new ItemStack(ingot), OreFamily.DEFAULT_FURANCE_EXP);
	}
	
	protected ItemIngot itemIngot;
	
	public ItemIngot getItemIngot() {
		return itemIngot;
	}
}
