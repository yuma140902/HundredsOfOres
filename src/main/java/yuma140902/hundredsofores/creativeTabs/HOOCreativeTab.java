package yuma140902.hundredsofores.creativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yuma140902.hundredsofores.ore_feature_set.OreFeatureType;
import yuma140902.hundredsofores.ore_feature_set.OreFeaturesSets;

public class HOOCreativeTab extends CreativeTabs {
	public HOOCreativeTab() {
		super("HundredsOfOres");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		return new ItemStack((Block) OreFeaturesSets.copper.getFeature(OreFeatureType.ORE));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {return null;}
}
