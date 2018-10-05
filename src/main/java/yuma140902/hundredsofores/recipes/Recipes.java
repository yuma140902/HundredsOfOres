package yuma140902.hundredsofores.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.MyItems;
import yuma140902.hundredsofores.ore_feature_set.OreFeatureType;
import yuma140902.hundredsofores.ore_feature_set.OreFeaturesSets;

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
		
	}
	
	private static void registerAlloyRecipes() {
		Item silverDust = (Item) OreFeaturesSets.silver.getFeature(OreFeatureType.DUST);
		Item electrumDust = (Item) OreFeaturesSets.electrum.getFeature(OreFeatureType.DUST);
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack((Item) electrumDust, 4, 0), 
				(Item) OreFeaturesSets.gold.getFeature(OreFeatureType.DUST), 
				silverDust,
				silverDust,
				silverDust
		));
	}
}
