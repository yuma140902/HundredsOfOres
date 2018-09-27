package yuma140902.hundredsofores.ore_feature_set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class OreFamilyFeatureBlockBase extends Block implements IOreFamilyFeature {
	
	protected OreID _oreName;
	
	public OreFamilyFeatureBlockBase(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyFeatureBlockBase(OreID oreName) {
		super(Material.rock);
		this._oreName = oreName;
	}
	
}
