package yuma140902.hundredsofores.ore_feature_set;

import net.minecraft.item.Item;

public abstract class OreFamilyFeatureItemBase extends Item implements IOreFamilyFeature {
	
	protected OreID _oreName;
	
	public OreFamilyFeatureItemBase(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyFeatureItemBase(OreID oreName) {
		super();
		this._oreName = oreName;
	}
}
