package yuma140902.hundredsofores.orefamilies.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.orefamilies.ItemGem;

public class OreFamilyWithGem extends OreFamily {
	public OreFamilyWithGem(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithGem(OreID oreName) {
		super(oreName);
		this.itemGem = new ItemGem(oreName);
	}
	
	@Override
	public void register() {
		super.register();
		itemGem.register();
	}

	@Override
	public void init() {
		super.init();
		
		Block block = blockCompressedIngot;
		Block ore = blockOre;
		Item dust = itemDust;
		Item gem = itemGem;
		Item gear = itemGear;
		
		String blockOredict = blockCompressedIngot.getOreDictionaryKey();
		String ingotOredict = itemGem.getOreDictionaryKey();
		
		// ブロックの解凍
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(gem, 9), block));
		if (blockOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(gem, 9), blockOredict));
		
		// ブロックへ圧縮
		GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', gem));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingotOredict));
		
		// ジェムからギア
		GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', gem));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', ingotOredict));
		
		// 鉱石を精錬してジェム
		GameRegistry.addSmelting(ore, new ItemStack(gem), OreFamily.DEFAULT_FURANCE_EXP);
		// 粉を精錬してジェム
		GameRegistry.addSmelting(dust, new ItemStack(gem), OreFamily.DEFAULT_FURANCE_EXP);
	}
	
	protected ItemGem itemGem;
	
	public ItemGem getItemGem() {
		return itemGem;
	}
}
