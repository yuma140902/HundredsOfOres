package yuma140902.hundredsofores.ore_feature_set;

import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockOre;
import net.minecraft.item.ItemPickaxe;
import yuma140902.hundredsofores.ore_feature_set.features.ItemDust;
import yuma140902.hundredsofores.ore_feature_set.features.ItemGear;
import yuma140902.hundredsofores.ore_feature_set.features.ItemGem;
import yuma140902.hundredsofores.ore_feature_set.features.ItemIngot;
import yuma140902.hundredsofores.ore_feature_set.features.ItemNugget;

public final class FeatureTypeHelper {
	private FeatureTypeHelper() {}
	
	public static Class<?> getClass(OreFeatureType featureType) {
		switch (featureType) {
			case ORE:
				return BlockOre.class;
			case BLOCK:
				return BlockCommandBlock.class;
			case DUST:
				return ItemDust.class;
			case GEAR:
				return ItemGear.class;
			case GEM:
				return ItemGem.class;
			case INGOT:
				return ItemIngot.class;
			case NUGGET:
				return ItemNugget.class;
			case PICKAXE:
				return ItemPickaxe.class;
				
			default:
				//never
				throw new RuntimeException();
		}
	}
}
