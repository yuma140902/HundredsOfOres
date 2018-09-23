package yuma140902.hundredsofores;

import net.minecraft.item.Item;
import yuma140902.hundredsofores.core.IRegisterable;
import yuma140902.hundredsofores.items.Hummer;
import yuma140902.hundredsofores.items.ItemDustGold;

public final class MyItems {
	public static void register() {
		((IRegisterable) hummer).register();
		((IRegisterable) goldDust).register();
	}
	
	public static final Item hummer = new Hummer();
	public static final Item goldDust = new ItemDustGold();
}
