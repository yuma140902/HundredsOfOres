package yuma140902.hundredsofores.orefamilies;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class OreFamilyMemberBlockBase extends Block implements IOreFamilyFeature {
	
	protected OreID _oreName;
	
	public OreFamilyMemberBlockBase(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyMemberBlockBase(OreID oreName) {
		super(Material.rock);
		this._oreName = oreName;
	}
	
}
