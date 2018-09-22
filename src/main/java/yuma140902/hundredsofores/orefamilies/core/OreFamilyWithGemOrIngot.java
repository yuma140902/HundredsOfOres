package yuma140902.hundredsofores.orefamilies.core;

import net.minecraft.item.Item;

public abstract class OreFamilyWithGemOrIngot extends OreFamily {
	public OreFamilyWithGemOrIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithGemOrIngot(OreID oreName) {
		super(oreName);
	}

	public abstract Item getGemOrIngot();
}
