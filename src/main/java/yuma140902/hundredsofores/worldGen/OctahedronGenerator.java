package yuma140902.hundredsofores.worldGen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import yuma140902.hundredsofores.ore_feature_set.OreFeatureType;
import yuma140902.hundredsofores.ore_feature_set.OreFamilies;

public class OctahedronGenerator extends WorldGeneratorBase {

	private static final int DIMENSIONID_THEEND = 1;
	private static final int DIMENSIONID_OVERWORLD = 0;
	private static final int DIMENSIONID_NETHER = -1;

	@Override
	protected void generate(World world, Random random, int chunkX, int chunkZ) {
		 
		int dimensionID = world.provider.dimensionId;
		int rate = 
				(dimensionID == DIMENSIONID_NETHER) ? -1 
			: (dimensionID == DIMENSIONID_OVERWORLD) ? /*10240*/ 10000
			: (dimensionID == DIMENSIONID_THEEND) ? 1000
			: -1; //-1: never
		if(rate < 0) return;
		
		int r = 10;
		
		int minHeight =
				(dimensionID == DIMENSIONID_OVERWORLD) ? 60 + r
			: 0 + r;
		int maxHeight =
				(dimensionID == DIMENSIONID_OVERWORLD) ? 120 - r
			: 255 - r;
		int heightDiff = maxHeight - minHeight + 1;
		
		
		int rand = random.nextInt(rate + 1);
		
		if(rand == 0) {
			setBlockAt(world, random, Blocks.bedrock, chunkX * 16 + 7, 80, chunkZ * 16 + 7);
			
			int centerX = chunkX * 16 + 7;
			int centerY = minHeight + random.nextInt(heightDiff);
			int centerZ = chunkZ * 16 + 7;
			setBlockAt(world, random, Blocks.chest, centerX, centerY, centerZ);
			TileEntityChest tileEntityChest = (TileEntityChest)world.getTileEntity(centerX, centerY, centerZ);
			tileEntityChest.setInventorySlotContents(0, new ItemStack(Items.diamond, 64)); //第一引数はインベントリスロットの場所(0から始まり、26まで(ラージチェストはその二倍))
			tileEntityChest.setInventorySlotContents(1, new ItemStack(Items.gold_ingot, 64));
			tileEntityChest.setInventorySlotContents(2, new ItemStack(Items.golden_apple, 64));
			tileEntityChest.setInventorySlotContents(3, new ItemStack(Items.nether_star, 16));
			tileEntityChest.setInventorySlotContents(4, new ItemStack(Items.blaze_rod, 64));
			tileEntityChest.setInventorySlotContents(9, new ItemStack(Blocks.diamond_block, 64));
			tileEntityChest.setInventorySlotContents(5, new ItemStack(Blocks.obsidian, 64));
			tileEntityChest.setInventorySlotContents(6, new ItemStack(Blocks.packed_ice, 64));
			tileEntityChest.setInventorySlotContents(7, new ItemStack(Blocks.packed_ice, 64));
//			tileEntityChest.setInventorySlotContents(8, new ItemStack(Items.spawn_egg, 64, 28 /*skeleton_horse*/));
//			tileEntityChest.setInventorySlotContents(9, new ItemStack(Items.spawn_egg, 64, 29 /*zombie_horse*/));
			tileEntityChest.setInventorySlotContents(10, new ItemStack(Blocks.bedrock, 64));
			tileEntityChest.setInventorySlotContents(11, new ItemStack(Blocks.bedrock, 64));
			tileEntityChest.setInventorySlotContents(12, new ItemStack(Blocks.dragon_egg));
			tileEntityChest.setInventorySlotContents(13, new ItemStack(Blocks.end_portal_frame, 64));
			tileEntityChest.setInventorySlotContents(14, new ItemStack(Items.ender_eye, 16));
			tileEntityChest.setInventorySlotContents(15, new ItemStack(Items.ender_eye, 16));
			tileEntityChest.setInventorySlotContents(16, new ItemStack(Items.ender_pearl, 16));
			tileEntityChest.setInventorySlotContents(17, new ItemStack(Items.ender_pearl, 16));
			tileEntityChest.setInventorySlotContents(18, new ItemStack(Blocks.mob_spawner, 64, 92/*ウシ*/));
			tileEntityChest.setInventorySlotContents(19, new ItemStack(Blocks.mob_spawner, 64, 54/*ゾンビ*/));
			tileEntityChest.setInventorySlotContents(20, new ItemStack(Items.beef, 64));
			tileEntityChest.setInventorySlotContents(21, new ItemStack(Items.iron_horse_armor));
			tileEntityChest.setInventorySlotContents(22, new ItemStack(Items.golden_horse_armor));
			tileEntityChest.setInventorySlotContents(23, new ItemStack(Items.diamond_horse_armor));
			tileEntityChest.setInventorySlotContents(24, new ItemStack(Items.saddle));
			tileEntityChest.setInventorySlotContents(25, new ItemStack(Items.saddle));
			tileEntityChest.setInventorySlotContents(26, new ItemStack(Items.saddle));
			
			//頂点を生成
			setBlockAt(world, random, Blocks.end_stone, centerX + r, centerY, centerZ);
			setBlockAt(world, random, Blocks.end_stone, centerX - r, centerY, centerZ);
			setBlockAt(world, random, Blocks.end_stone, centerX, centerY + r, centerZ);
			setBlockAt(world, random, Blocks.end_stone, centerX, centerY - r, centerZ);
			setBlockAt(world, random, Blocks.end_stone, centerX, centerY, centerZ + r);
			setBlockAt(world, random, Blocks.end_stone, centerX, centerY, centerZ - r);
			
			//枠を生成
			for(int i = 1; i <= r - 1; ++i) {
					setBlockAt(world, random, Blocks.gold_block,															centerX + i, centerY, centerZ + (r-i), 0);
					setBlockAt(world, random, Blocks.diamond_block,													centerX + i, centerY, centerZ - (r-i), 0);
					setBlockAt(world, random, Blocks.iron_block,															centerX - i, centerY, centerZ + (r-i), 0);
					setBlockAt(world, random, Blocks.emerald_block,													centerX - i, centerY, centerZ - (r-i), 0);
					setBlockAt(world, random, Blocks.lapis_block,														centerX, centerY + i, centerZ + (r-i), 0);
					setBlockAt(world, random, Blocks.coal_block,															centerX, centerY + i, centerZ - (r-i), 0);
					setBlockAt(world, random, Blocks.coal_block,															centerX, centerY - i, centerZ + (r-i), 0);
					setBlockAt(world, random, Blocks.obsidian,																centerX, centerY - i, centerZ - (r-i), 0);
					setBlockAt(world, random, (Block) OreFamilies.copper.getFeature(OreFeatureType.BLOCK),		centerX + i, centerY + (r-i), centerZ, 0);
					setBlockAt(world, random, (Block) OreFamilies.silver.getFeature(OreFeatureType.BLOCK),		centerX + i, centerY - (r-i), centerZ, 0);
					setBlockAt(world, random, Blocks.quartz_block,														centerX - i, centerY + (r-i), centerZ, 0);
					setBlockAt(world, random, (Block) OreFamilies.tin.getFeature(OreFeatureType.BLOCK), centerX - i, centerY - (r-i), centerZ, 0);
			}
			
			//中心から各頂点までの線を生成
			for(int i = 2; i <= r - 1; ++i) {
				setBlockAt(world, random, Blocks.redstone_block, centerX + i, centerY, centerZ);
				setBlockAt(world, random, Blocks.redstone_block, centerX - i, centerY, centerZ);
				setBlockAt(world, random, Blocks.redstone_block, centerX, centerY + i, centerZ);
				setBlockAt(world, random, Blocks.redstone_block, centerX, centerY - i, centerZ);
				setBlockAt(world, random, Blocks.redstone_block, centerX, centerY, centerZ + i);
				setBlockAt(world, random, Blocks.redstone_block, centerX, centerY, centerZ - i);
			}
			
			//チェストをガラスとエンドストーンで囲う
//			setBlockAt(world, random, Blocks.glass, centerX    , centerY    , centerZ    );
			setBlockAt(world, random, Blocks.glass, centerX    , centerY    , centerZ + 1);
			setBlockAt(world, random, Blocks.glass, centerX    , centerY    , centerZ - 1);
			setBlockAt(world, random, Blocks.glass, centerX    , centerY + 1, centerZ    );
			setBlockAt(world, random, Blocks.glass, centerX    , centerY + 1, centerZ + 1);
			setBlockAt(world, random, Blocks.glass, centerX    , centerY + 1, centerZ - 1);
			setBlockAt(world, random, Blocks.end_stone, centerX    , centerY - 1, centerZ    );
			setBlockAt(world, random, Blocks.end_stone, centerX    , centerY - 1, centerZ + 1);
			setBlockAt(world, random, Blocks.end_stone, centerX    , centerY - 1, centerZ - 1);
			
			setBlockAt(world, random, Blocks.glass, centerX + 1, centerY    , centerZ    );
			setBlockAt(world, random, Blocks.glass, centerX + 1, centerY    , centerZ + 1);
			setBlockAt(world, random, Blocks.glass, centerX + 1, centerY    , centerZ - 1);
			setBlockAt(world, random, Blocks.glass, centerX + 1, centerY + 1, centerZ    );
			setBlockAt(world, random, Blocks.glass, centerX + 1, centerY + 1, centerZ + 1);
			setBlockAt(world, random, Blocks.glass, centerX + 1, centerY + 1, centerZ - 1);
			setBlockAt(world, random, Blocks.end_stone, centerX + 1, centerY - 1, centerZ    );
			setBlockAt(world, random, Blocks.end_stone, centerX + 1, centerY - 1, centerZ + 1);
			setBlockAt(world, random, Blocks.end_stone, centerX + 1, centerY - 1, centerZ - 1);
			
			setBlockAt(world, random, Blocks.glass, centerX - 1, centerY    , centerZ    );
			setBlockAt(world, random, Blocks.glass, centerX - 1, centerY    , centerZ + 1);
			setBlockAt(world, random, Blocks.glass, centerX - 1, centerY    , centerZ - 1);
			setBlockAt(world, random, Blocks.glass, centerX - 1, centerY + 1, centerZ    );
			setBlockAt(world, random, Blocks.glass, centerX - 1, centerY + 1, centerZ + 1);
			setBlockAt(world, random, Blocks.glass, centerX - 1, centerY + 1, centerZ - 1);
			setBlockAt(world, random, Blocks.end_stone, centerX - 1, centerY - 1, centerZ    );
			setBlockAt(world, random, Blocks.end_stone, centerX - 1, centerY - 1, centerZ + 1);
			setBlockAt(world, random, Blocks.end_stone, centerX - 1, centerY - 1, centerZ - 1);
		}
	}
	
}
