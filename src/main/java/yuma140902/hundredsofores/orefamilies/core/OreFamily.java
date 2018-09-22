package yuma140902.hundredsofores.orefamilies.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.orefamilies.BlockCompressedIngot;
import yuma140902.hundredsofores.orefamilies.BlockOre;
import yuma140902.hundredsofores.orefamilies.ItemDust;
import yuma140902.hundredsofores.orefamilies.ItemGear;
import yuma140902.hundredsofores.worldGen.WorldGenerators;

/**
 * 金属は、鉱石、インゴット、粉、ブロックなどさまざまな状態になるので、
 * それらをまとめて生成、登録、レシピの追加などを行うためのクラスです。
 * 
 * @author yuma1
 *
 */
public class OreFamily {
	
	protected static final float DEFAULT_FURANCE_EXP = 10.0F; // TODO 10.0Fは適当
	
	private OreID _oreName;
	
	protected BlockOre blockOre;
	protected ItemDust itemDust;
	protected BlockCompressedIngot blockCompressedIngot;
	protected ItemGear itemGear;
	
	public OreFamily(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamily(OreID oreName) {
		this._oreName = oreName;
		this.blockOre = new BlockOre(oreName);
		this.itemDust = new ItemDust(oreName);
		this.blockCompressedIngot = new BlockCompressedIngot(oreName);
		this.itemGear = new ItemGear(oreName);
	}
	
	public void init() {
		// レシピの追加など
		BlockOre ore = this.getBlockOre();
		ItemDust dust = this.getItemDust();
		BlockCompressedIngot block = this.getBlockCompressedIngot();
		ItemGear gear = this.getItemGear();
		
		String oreOredict = ore.getOreDictionaryKey();
		String dustOredict = dust.getOreDictionaryKey();
		String blockOredict = block.getOreDictionaryKey();
		String gearOredict = gear.getOreDictionaryKey();
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dust, 2), ore));
		if (oreOredict != null)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(dust, 2), oreOredict));
	}
	
	public void register() {
		this.getBlockOre().register();
		this.getItemDust().register();
		this.getBlockCompressedIngot().register();
		this.getItemGear().register();
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
	
	public BlockCompressedIngot getBlockCompressedIngot() {
		return blockCompressedIngot;
	}

	public ItemGear getItemGear() {
		return itemGear;
	}
	
}
