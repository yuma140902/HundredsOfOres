package yuma140902.hundredsofores.orefamilies.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.orefamilies.ItemIngot;
import yuma140902.hundredsofores.orefamilies.ItemNugget;

public class OreFamilyWithIngot extends OreFamily {
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
		
		Block block = blockCompressedIngot;
		Block ore = blockOre;
		Item dust = itemDust;
		Item ingot = itemIngot;
		Item gear = itemGear;
		Item nugget = itemNugget;
		
		String blockOredict = blockCompressedIngot.getOreDictionaryKey();
		String ingotOredict = itemIngot.getOreDictionaryKey();
		String nuggetOredict = itemNugget.getOreDictionaryKey();
		
		// ブロックの解凍
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ingot, 9), block));
		if (blockOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ingot, 9), blockOredict));
		
		// ブロックへ圧縮
		GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingotOredict));
		
		// インゴットからギア
		GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', ingotOredict));
		
		// ナゲットからインゴット
		GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nugget));
		if (nuggetOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(ingot, "###", "###", "###", '#', nuggetOredict));
		
		// インゴットからナゲット
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(nugget, 9), ingotOredict));
		
		// 鉱石を精錬してインゴット
		GameRegistry.addSmelting(ore, new ItemStack(ingot), OreFamily.DEFAULT_FURANCE_EXP);
		// 粉を精錬してインゴット
		GameRegistry.addSmelting(dust, new ItemStack(ingot), OreFamily.DEFAULT_FURANCE_EXP);
	}
	
	protected ItemIngot itemIngot;
	protected ItemNugget itemNugget;
	
	public ItemIngot getItemIngot() {
		return itemIngot;
	}
	
	public ItemNugget getItemNugget() {
		return itemNugget;
	}
}
