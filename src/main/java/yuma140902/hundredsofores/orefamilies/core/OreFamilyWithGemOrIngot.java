package yuma140902.hundredsofores.orefamilies.core;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public abstract class OreFamilyWithGemOrIngot extends OreFamily {
	public OreFamilyWithGemOrIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithGemOrIngot(OreID oreName) {
		super(oreName);
	}
	
	@Override
	public void init() {
		super.init();
		
		Block block = blockCompressedBlock;
		Block ore = blockOre;
		Item dust = itemDust;
		Item gem_ingot = getGemOrIngot();
		Item gear = itemGear;
		
		String blockOredict = blockCompressedBlock.getOreDictionaryKey();
		String ingotOredict = getGemOrIngot().getOreDictionaryKey();
		
		// ブロックの解凍
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(gem_ingot, 9), block));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(gem_ingot, 9), blockOredict));
		
		// ブロックへ圧縮
		GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', gem_ingot));
		GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingotOredict));
		
		// ジェムからギア
		GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', gem_ingot));
		GameRegistry.addRecipe(new ShapedOreRecipe(gear, " # ", "# #", " # ", '#', ingotOredict));
		
		// 鉱石を精錬してジェムOrインゴット
		GameRegistry.addSmelting(ore, new ItemStack(gem_ingot), OreFamily.DEFAULT_FURANCE_EXP);
		// 粉を精錬してジェムOrインゴット
		GameRegistry.addSmelting(dust, new ItemStack(gem_ingot), OreFamily.DEFAULT_FURANCE_EXP);
	}
	
	public abstract @Nonnull OreFamilyMemberItemBase getGemOrIngot();
}
