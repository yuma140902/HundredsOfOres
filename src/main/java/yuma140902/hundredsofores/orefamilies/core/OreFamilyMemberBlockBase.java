package yuma140902.hundredsofores.orefamilies.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class OreFamilyMemberBlockBase extends Block implements IOreFamilyMember {
	
	protected OreID _oreName;
	
	public OreFamilyMemberBlockBase(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyMemberBlockBase(OreID oreName) {
		super(Material.rock);
		this._oreName = oreName;
	}
	
}
