package yuma140902.hundredsofores.worldGen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MeteorGenerator extends WorldGeneratorBase {

	@Override
	protected void generate(World world, Random random, int chunkX, int chunkZ) {
		for(int i = 0; i < 14; ++i) {
			setBlockAt(world, random, Blocks.glass, chunkX * 16 + i, 85, chunkZ * 16 + i);
		}
	}
	
}
