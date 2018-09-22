package yuma140902.hundredsofores.orefamilies.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.orefamilies.BlockCompressedIngot;
import yuma140902.hundredsofores.orefamilies.BlockOre;
import yuma140902.hundredsofores.orefamilies.ItemDust;
import yuma140902.hundredsofores.orefamilies.ItemGear;
import yuma140902.hundredsofores.orefamilies.ItemIngot;
import yuma140902.hundredsofores.orefamilies.ItemNugget;
import yuma140902.hundredsofores.worldGen.WorldGenerators;

/**
 * 金属は、鉱石、インゴット、粉、ブロックなどさまざまな状態になるので、
 * それらをまとめて生成、登録、レシピの追加などを行うためのクラスです。
 * 
 * @author yuma1
 *
 */
public class OreFamily {
	
	private static final float DEFAULT_FURANCE_EXP = 10.0F; // TODO 10.0Fは適当
	
	private OreID _oreName;
	
	protected BlockOre blockOre;
	protected ItemDust itemDust;
	protected ItemIngot itemIngot;
	protected BlockCompressedIngot blockCompressedIngot;
	protected ItemGear itemGear;
	protected ItemNugget itemNugget;
	
	public OreFamily(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamily(OreID oreName) {
		this._oreName = oreName;
		this.blockOre = new BlockOre(oreName);
		this.itemDust = new ItemDust(oreName);
		this.itemIngot = new ItemIngot(oreName);
		this.blockCompressedIngot = new BlockCompressedIngot(oreName);
		this.itemGear = new ItemGear(oreName);
		this.itemNugget = new ItemNugget(oreName);
	}
	
	public void init() {
		// レシピの追加など
		BlockOre ore = this.getBlockOre();
		ItemIngot ingot = this.getItemIngot();
		ItemDust dust = this.getItemDust();
		BlockCompressedIngot block = this.getBlockCompressedIngot();
		ItemGear gear = this.getItemGear();
		ItemNugget nugget = this.getItemNugget();
		
		String oreOredict = ore.getOreDictionaryKey();
		String ingotOredict = ingot.getOreDictionaryKey();
		String dustOredict = dust.getOreDictionaryKey();
		String blockOredict = block.getOreDictionaryKey();
		String gearOredict = gear.getOreDictionaryKey();
		String nuggetOredict = nugget.getOreDictionaryKey();
		
		GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingot));
		if (ingotOredict != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(block, "###", "###", "###", '#', ingotOredict));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ingot, 9), block));
		if (blockOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ingot, 9), blockOredict));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dust, 2), ore));
		if (oreOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dust, 2), oreOredict));
		
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
	
	public void register() {
		this.getBlockOre().register();
		this.getItemDust().register();
		this.getItemIngot().register();
		this.getBlockCompressedIngot().register();
		this.getItemGear().register();
		this.getItemNugget().register();
	}
	
	public void loadConfig(Configuration cfg) {
		this.getBlockOre().loadConfig(cfg);
	}
	
	public void registerToWorldGenerators() {
		BlockOre ore = this.getBlockOre();
		WorldGenerators.overWorldOreGenerator.addOreGenerator(ore, ore.getOreGeneratorConfig());
	}
	
	public OreID getOreId() {
		return this._oreName;
	}

	public BlockOre getBlockOre() {
		return blockOre;
	}

	public ItemDust getItemDust() {
		return itemDust;
	}

	public ItemIngot getItemIngot() {
		return itemIngot;
	}
	
	public BlockCompressedIngot getBlockCompressedIngot() {
		return blockCompressedIngot;
	}

	public ItemGear getItemGear() {
		return itemGear;
	}

	public ItemNugget getItemNugget() {
		return itemNugget;
	}
	
}
