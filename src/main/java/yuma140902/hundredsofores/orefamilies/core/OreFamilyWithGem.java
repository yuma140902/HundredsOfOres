package yuma140902.hundredsofores.orefamilies.core;

import javax.annotation.Nonnull;
import yuma140902.hundredsofores.orefamilies.ItemGem;

public class OreFamilyWithGem extends OreFamilyWithGemOrIngot {
	public OreFamilyWithGem(String oreName) {
		this(new OreID(oreName));
	}
	
	public OreFamilyWithGem(OreID oreName) {
		super(oreName);
		this.itemGem = new ItemGem(oreName);
	}
	
	@Override
	public void register() {
		super.register();
		itemGem.register();
	}
	
	protected @Nonnull ItemGem itemGem;
	
	public ItemGem getItemGem() {
		return itemGem;
	}

	@Override
	public OreFamilyMemberItemBase getGemOrIngot() {
		return itemGem;
	}
}
