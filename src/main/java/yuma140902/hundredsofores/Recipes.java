package yuma140902.hundredsofores;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
		
		registerAlloyRecipes();
	}
	
	private static void registerAlloyRecipes() {
		
	}
}
