package yuma140902.hundredsofores.orefamilies;

import net.minecraft.item.Item;

public abstract class OreFamilyMemberItemBase extends Item implements IOreFamilyMember {
	
	protected OreID _oreName;
	
	public OreFamilyMemberItemBase(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyMemberItemBase(OreID oreName) {
		super();
		this._oreName = oreName;
	}
}
