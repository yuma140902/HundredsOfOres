package yuma140902.hundredsofores;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.orefamilies.core.OreFamily;

public final class Recipes {
	private Recipes() {}
	
	public static void register() {
		GameRegistry.addRecipe(new ShapedOreRecipe(
				MyItems.hummer, 
				" | ",
				"#|#",
				"vvv",
				'|', Items.stick,
				'#', "gemRuby",
				'v', Items.flint
				));
		
		registerDustRecipes();
		registerAlloyRecipes();
	}
	
	// OreFamilyで登録されない粉(金の粉など)のレシピを追加します。
	private static void registerDustRecipes() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(MyItems.goldDust, 2),
				Blocks.gold_ore, new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(MyItems.goldDust, 2),
				"oreGold", new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				MyItems.goldDust,
				Items.gold_ingot, new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				MyItems.goldDust,
				"ingotGold", new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
		
		GameRegistry.addSmelting(MyItems.goldDust, new ItemStack(Items.gold_ingot), OreFamily.DEFAULT_FURANCE_EXP);
	}
	
	private static void registerAlloyRecipes() {
		
	}
}
