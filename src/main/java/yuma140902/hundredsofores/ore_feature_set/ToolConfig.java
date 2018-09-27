package yuma140902.hundredsofores.ore_feature_set;

import net.minecraftforge.common.config.Configuration;

public class ToolConfig {
	public ToolConfig() {
		setDefaultValues();
	}
	
	public int harvestLevel;
	public int maxUses;
	public float efficiency;
	public float damage;
	public int enchantability;
	
	protected int defaultHarvestLevel = 2;
	protected int defaultMaxUses = 300;
	protected float defaultEfficiency = 7.0F;
	protected float defaultDamage = 0.0F;
	protected int defaultEnchantability = 14;
	
	public void setDefaultValues() {
		this.harvestLevel = defaultHarvestLevel;
		this.maxUses = defaultMaxUses;
		this.efficiency = defaultEfficiency;
		this.damage = defaultDamage;
		this.enchantability = defaultEnchantability;
	}
	
	public void setDefaultHarvestLevel(int value) {
		this.defaultHarvestLevel = value;
	}

	public void setDefaultMaxUses(int value) {
		this.defaultMaxUses = value;
	}

	public void setDefaultEfficiency(float value) {
		this.defaultEfficiency = value;
	}

	public void setDefaultDamage(float value) {
		this.defaultDamage = value;
	}

	public void setDefaultEnchantability(int value) {
		this.defaultEnchantability = value;
	}
	
	public void loadFromConfigFile(Configuration cfg, String toolNameLiteral) {
		harvestLevel = cfg.getInt(
				toolNameLiteral + "_HarvestLevel", toolNameLiteral, defaultHarvestLevel, 0, 10,
				toolNameLiteral + "のハーベストレベル");
		maxUses = cfg.getInt(
				toolNameLiteral + "_MaxUses", toolNameLiteral, defaultMaxUses, 0, 4096, toolNameLiteral + "の使用可能回数(?)");
		efficiency = cfg.getFloat(
				toolNameLiteral + "_Efficiency", toolNameLiteral, defaultEfficiency, 0, 4096, toolNameLiteral + "の採掘効率");
		damage = cfg.getFloat(
				toolNameLiteral + "_Damage", toolNameLiteral, defaultDamage, 0, 4096, toolNameLiteral + "のダメージ量");
		enchantability = cfg.getInt(
				toolNameLiteral + "_Enchantability", toolNameLiteral, defaultEnchantability, 0, 4096,
				toolNameLiteral + "のエンチャントの付きやすさ");
	}
}
