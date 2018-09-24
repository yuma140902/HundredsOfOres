package yuma140902.hundredsofores;

import yuma140902.hundredsofores.items.Hummer;
import yuma140902.hundredsofores.items.ItemDustElectrum;
import yuma140902.hundredsofores.items.ItemDustGold;
import yuma140902.hundredsofores.items.ItemDustSulfur;

public final class MyItems {
	public static void register() {
		hummer.register();
		goldDust.register();
		sulferDust.register();
		electrumDust.register();
	}
	
	public static final Hummer hummer = new Hummer();
	public static final ItemDustGold goldDust = new ItemDustGold();
	public static final ItemDustSulfur sulferDust = new ItemDustSulfur();
	public static final ItemDustElectrum electrumDust = new ItemDustElectrum();
}
