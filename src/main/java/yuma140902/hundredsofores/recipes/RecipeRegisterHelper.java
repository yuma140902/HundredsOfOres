package yuma140902.hundredsofores.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.MyItems;

public final class RecipeRegisterHelper {
	private RecipeRegisterHelper() {}
	
	public static void addRecipeOreToDust(Block ore, Item dust) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
			new ItemStack(dust, 2), 
			ore, new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
	}
	
	public static void addRecipeOreToDust(String oreOreDictionaryKey, Item dust) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(dust, 2), 
				oreOreDictionaryKey, new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
	}
}
