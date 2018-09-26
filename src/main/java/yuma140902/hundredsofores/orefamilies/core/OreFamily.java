package yuma140902.hundredsofores.orefamilies.core;

import net.minecraftforge.common.config.Configuration;
import yuma140902.hundredsofores.orefamilies.features.BlockCompressedBlock;
import yuma140902.hundredsofores.orefamilies.features.BlockOre;
import yuma140902.hundredsofores.orefamilies.features.ItemDust;
import yuma140902.hundredsofores.orefamilies.features.ItemGear;
import yuma140902.hundredsofores.recipes.RecipeRegisterHelper;
import yuma140902.hundredsofores.worldGen.WorldGenerators;

/**
 * 金属は、鉱石、インゴット、粉、ブロックなどさまざまな状態になるので、
 * それらをまとめて生成、登録、レシピの追加などを行うためのクラスです。
 * 
 * @author yuma1
 *
 */
public class OreFamily {
	
	private OreID _oreName;
	
	protected BlockOre blockOre;
	protected ItemDust itemDust;
	protected BlockCompressedBlock blockCompressedBlock;
	protected ItemGear itemGear;
	
	public OreFamily(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamily(OreID oreName) {
		this._oreName = oreName;
		this.blockOre = new BlockOre(oreName);
		this.itemDust = new ItemDust(oreName);
		this.blockCompressedBlock = new BlockCompressedBlock(oreName);
		this.itemGear = new ItemGear(oreName);
	}
	
	public void init() {
		// レシピの追加など
		BlockOre ore = this.getBlockOre();
		ItemDust dust = this.getItemDust();
		
		String oreOredict = ore.getOreDictionaryKey();
		
		// 鉱石から粉2つ TODO: 独自の粉砕機も実装したい
		RecipeRegisterHelper.addRecipeOreToDust(ore, dust);
		RecipeRegisterHelper.addRecipeOreToDust(oreOredict, dust);
	}
	
	public void register() {
		this.getBlockOre().register();
		this.getItemDust().register();
		this.getBlockCompressedIngot().register();
		this.getItemGear().register();
	}
	
	public void loadOreGenConfig(Configuration cfg) {
		this.getBlockOre().loadConfig(cfg);
	}
	
	public void registerToWorldGenerators() {
		BlockOre ore = this.getBlockOre();
		WorldGenerators.oreGenerator.addOreGenerator(ore, ore.getOreGeneratorConfig());
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
	
	public BlockCompressedBlock getBlockCompressedIngot() {
		return blockCompressedBlock;
	}
	
	public ItemGear getItemGear() {
		return itemGear;
	}
	
}
