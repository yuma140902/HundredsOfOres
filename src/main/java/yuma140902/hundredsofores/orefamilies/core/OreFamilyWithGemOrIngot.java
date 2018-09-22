package yuma140902.hundredsofores.orefamilies.core;

public abstract class OreFamilyWithGemOrIngot extends OreFamily {
	public OreFamilyWithGemOrIngot(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithGemOrIngot(OreID oreName) {
		super(oreName);
	}

	public abstract OreFamilyMemberItemBase getGemOrIngot();
}
