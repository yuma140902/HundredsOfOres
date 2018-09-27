package yuma140902.hundredsofores.orefamilies;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.ShapedOreRecipe;
import yuma140902.hundredsofores.orefamilies.features.ItemPickaxe;
import yuma140902.hundredsofores.recipes.RecipeRegisterHelper;

@Deprecated
public abstract class OreFamilyWithGemOrIngot extends OreFamily {
	public OreFamilyWithGemOrIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithGemOrIngot(OreID oreName) {
		super(oreName);
	}
	
	
	protected ItemPickaxe itemPickaxe;

	
	@Override
	public void register() {
		super.register();
		getGemOrIngot().register();
		itemPickaxe = new ItemPickaxe(getOreId(), material);
		itemPickaxe.register();
	}
	
	@Override
	public void init() {
		super.init();
		
		Block block = blockCompressedBlock;
		Block ore = blockOre;
		Item dust = itemDust;
		Item gem_ingot = getGemOrIngot();
		Item gear = itemGear;
		Item pickaxe = itemPickaxe;
		
		String blockOredict = blockCompressedBlock.getOreDictionaryKey();
		String gem_ingotOredict = getGemOrIngot().getOreDictionaryKey();
		
		// ブロックの解凍
		RecipeRegisterHelper.addRecipeBlockExpand(block, gem_ingot);
		RecipeRegisterHelper.addRecipeBlockExpand(blockOredict, gem_ingot);
		
		// ブロックへ圧縮
		RecipeRegisterHelper.addRecipeBlockCompress(gem_ingot, block);
		RecipeRegisterHelper.addRecipeBlockCompress(gem_ingotOredict, block);
		
		// ジェムOrインゴットからギア
		GameRegistry.addRecipe(new ShapedOreRecipe(
				gear, 
				" # ", 
				"###", 
				" # ", 
				'#', gem_ingot
				));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				gear, 
				" # ", 
				"###", 
				" # ", 
				'#', gem_ingotOredict
				));
		
		// ジェムOrインゴットからツルハシ
		GameRegistry.addRecipe(new ShapedOreRecipe(
				pickaxe, 
				"###", 
				" | ", 
				" | ", 
				'#', gem_ingot, 
				'|', Items.stick
				));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				pickaxe, 
				"###", 
				" | ", 
				" | ", 
				'#', gem_ingotOredict, 
				'|', Items.stick
				));
		
		//ジェムOrインゴットから粉
		RecipeRegisterHelper.addRecipeIngotToDust(gem_ingot, dust);
		RecipeRegisterHelper.addRecipeIngotToDust(gem_ingotOredict, dust);
		
		// 鉱石を精錬してジェムOrインゴット
		RecipeRegisterHelper.addSmeltingOreToIngot(ore, gem_ingot);
		// 粉を精錬してジェムOrインゴット
		RecipeRegisterHelper.addSmeltingDustToIngot(dust, gem_ingot);
	}
	
	public abstract @Nonnull OreFamilyFeatureItemBase getGemOrIngot();
	
	public ItemPickaxe getItemPickaxe() {
		return itemPickaxe;
	}

	public void setToolConfigDefaultHarvestLevel(int value) {
		this.toolConfigDefaultHarvestLevel = value;
	}

	public void setToolConfigDefaultMaxUses(int value) {
		this.toolConfigDefaultMaxUses = value;
	}

	public void setToolConfigDefaultEfficiency(float value) {
		this.toolConfigDefaultEfficiency = value;
	}

	public void setToolConfigDefaultDamage(float value) {
		this.toolConfigDefaultDamage = value;
	}

	public void setToolConfigDefaultEnchantability(int value) {
		this.toolConfigDefaultEnchantability = value;
	}
}
