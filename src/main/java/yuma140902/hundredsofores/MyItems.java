package yuma140902.hundredsofores;

import yuma140902.hundredsofores.items.Hummer;
import yuma140902.hundredsofores.items.ItemDustSulfur;

public final class MyItems {
	public static void register() {
		hummer.register();
		sulferDust.register();
	}
	
	public static final Hummer hummer = new Hummer();
	public static final ItemDustSulfur sulferDust = new ItemDustSulfur();
}
