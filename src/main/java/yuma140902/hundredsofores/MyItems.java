package yuma140902.hundredsofores;

import yuma140902.hundredsofores.items.Hummer;
import yuma140902.hundredsofores.items.ItemDustGold;

public final class MyItems {
	public static void register() {
		hummer.register();
		goldDust.register();
	}
	
	public static final Hummer hummer = new Hummer();
	public static final ItemDustGold goldDust = new ItemDustGold();
}
