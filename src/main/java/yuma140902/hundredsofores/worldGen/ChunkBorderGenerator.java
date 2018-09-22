package yuma140902.hundredsofores.worldGen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

@Deprecated
public class ChunkBorderGenerator extends WorldGeneratorBase {

	@Override
	protected void generate(World world, Random random, int chunkX, int chunkZ) {
		// TODO 自動生成されたメソッド・スタブ
		int x = chunkX * 16;
		int y = 80;
		int z = chunkZ * 16;
		setBlockAt(world, random, Blocks.wool, x, y, z);
	}
	
}
