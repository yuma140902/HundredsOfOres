package yuma140902.hundredsofores.ore_feature_set;

import net.minecraft.item.Item;

public abstract class OreFeatureItemBase extends Item implements IOreFeature {
	
	protected OreID _oreName;
	
	public OreFeatureItemBase(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFeatureItemBase(OreID oreName) {
		super();
		this._oreName = oreName;
	}
}
