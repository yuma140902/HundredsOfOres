package yuma140902.hundredsofores.ore_feature_set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class OreFeatureBlockBase extends Block implements IOreFeature {
	
	protected OreID _oreName;
	
	public OreFeatureBlockBase(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFeatureBlockBase(OreID oreName) {
		super(Material.rock);
		this._oreName = oreName;
	}
	
}
