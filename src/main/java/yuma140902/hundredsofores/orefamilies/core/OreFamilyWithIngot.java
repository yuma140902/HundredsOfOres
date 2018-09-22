package yuma140902.hundredsofores.orefamilies.core;

import yuma140902.hundredsofores.orefamilies.ItemIngot;

public class OreFamilyWithIngot extends OreFamily {
	public OreFamilyWithIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithIngot(OreID oreName) {
		super(oreName);
		this.itemIngot = new ItemIngot(oreName);
	}
	
	@Override
	public void register() {
		super.register();
		itemIngot.register();
	}
	
	protected ItemIngot itemIngot;
	
	public ItemIngot getItemIngot() {
		return itemIngot;
	}
}
