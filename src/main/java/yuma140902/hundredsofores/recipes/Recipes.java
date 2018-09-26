package yuma140902.hundredsofores.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.MyItems;
import yuma140902.hundredsofores.orefamilies.OreFamilies;

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
		RecipeRegisterHelper.addRecipeOreToDust(Blocks.gold_ore, MyItems.goldDust);
		RecipeRegisterHelper.addRecipeOreToDust("oreGold", MyItems.goldDust);
		
		RecipeRegisterHelper.addRecipeIngotToDust(Items.gold_ingot, MyItems.goldDust);
		RecipeRegisterHelper.addRecipeIngotToDust("ingotGold", MyItems.goldDust);
		
		RecipeRegisterHelper.addSmeltingDustToIngot(MyItems.goldDust, Items.gold_ingot);
	}
	
	private static void registerAlloyRecipes() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(MyItems.electrumDust, 4, 0), 
				MyItems.goldDust, 
				OreFamilies.silver.getItemDust(), 
				OreFamilies.silver.getItemDust(), 
				OreFamilies.silver.getItemDust()
		));
	}
}
