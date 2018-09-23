package yuma140902.hundredsofores.orefamilies.core;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import yuma140902.hundredsofores.ModHundredsOfOres;
import yuma140902.hundredsofores.MyItems;
import yuma140902.hundredsofores.orefamilies.ItemPickaxe;
import yuma140902.hundredsofores.util.StringUtil;

public abstract class OreFamilyWithGemOrIngot extends OreFamily {
	public OreFamilyWithGemOrIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithGemOrIngot(OreID oreName) {
		super(oreName);
	}
	
	protected int toolConfigDefaultHarvestLevel = 2;
	protected int toolConfigDefaultMaxUses = 300;
	protected float toolConfigDefaultEfficiency = 7.0F;
	protected float toolConfigDefaultDamage = 0.0F;
	protected int toolConfigDefaultEnchantability = 14;
	
	protected ItemPickaxe itemPickaxe;
	protected ToolMaterial material;
	
	@Override
	public void register() {
		super.register();
		getGemOrIngot().register();
		itemPickaxe = new ItemPickaxe(getOreId(), material);
		itemPickaxe.register();
	}
	
	public void loadToolConfig(Configuration cfg) {
		String toolNameLiteral = "pickaxe" + StringUtil.ToCase_XxxXxx(getOreId());
		String categoryName = toolNameLiteral;
		
		String name = ModHundredsOfOres.MOD_ID + "." + StringUtil.ToCase_xxx_xxx(getOreId()) + ".pickaxe";
		
		int harvestLevel = cfg.getInt(
				toolNameLiteral + "_HarvestLevel", categoryName, toolConfigDefaultHarvestLevel, 0, 10,
				toolNameLiteral + "のハーベストレベル");
		int maxUses = cfg.getInt(
				toolNameLiteral + "_MaxUses", categoryName, toolConfigDefaultMaxUses, 0, 4096, toolNameLiteral + "の使用可能回数(?)");
		float efficiency = cfg.getFloat(
				toolNameLiteral + "_Efficiency", categoryName, toolConfigDefaultEfficiency, 0, 4096, toolNameLiteral + "の採掘効率");
		float damage = cfg.getFloat(
				toolNameLiteral + "_Damage", categoryName, toolConfigDefaultDamage, 0, 4096, toolNameLiteral + "のダメージ量");
		int enchantability = cfg.getInt(
				toolNameLiteral + "_Enchantability", categoryName, toolConfigDefaultEnchantability, 0, 4096,
				toolNameLiteral + "のエンチャントの付きやすさ");
		
		material = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability)
				.setRepairItem(new ItemStack(getGemOrIngot()));
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
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(gem_ingot, 9), 
				block
				));
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				new ItemStack(gem_ingot, 9), 
				blockOredict
				));
		
		// ブロックへ圧縮
		GameRegistry.addRecipe(new ShapedOreRecipe(
				block, 
				"###", 
				"###", 
				"###", 
				'#', gem_ingot
				));
		GameRegistry.addRecipe(new ShapedOreRecipe(
				block, 
				"###", 
				"###", 
				"###", 
				'#', gem_ingotOredict
				));
		
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
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				dust,
				gem_ingot, new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(
				dust,
				gem_ingotOredict, new ItemStack(MyItems.hummer, 1, OreDictionary.WILDCARD_VALUE)));
		
		// 鉱石を精錬してジェムOrインゴット
		GameRegistry.addSmelting(ore, new ItemStack(gem_ingot), OreFamily.DEFAULT_FURANCE_EXP);
		// 粉を精錬してジェムOrインゴット
		GameRegistry.addSmelting(dust, new ItemStack(gem_ingot), OreFamily.DEFAULT_FURANCE_EXP);
	}
	
	public abstract @Nonnull OreFamilyMemberItemBase getGemOrIngot();
	
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
