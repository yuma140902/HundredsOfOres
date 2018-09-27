package yuma140902.hundredsofores.worldGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import yuma140902.hundredsofores.ore_feature_set.OreFamilies;
import yuma140902.hundredsofores.ore_feature_set.OreGenConfig;
import yuma140902.hundredsofores.util.ListUtil;
import yuma140902.hundredsofores.util.Pair;

public class MyOreGenerator implements IWorldGenerator {
	private List<Pair<WorldGenMinable, OreGenConfig>> _worldGenMinabes = new ArrayList<Pair<WorldGenMinable, OreGenConfig>>();
	
	
	public MyOreGenerator() {}
	
	public void addOreGenerator(Block ore, OreGenConfig config) {
		if(config == null) return;
		
		if (config.minHeight < 0 || config.maxHeight > 256 || config.minHeight > config.maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		config.heightDiff = config.maxHeight - config.minHeight + 1;
		
		_worldGenMinabes.add(new Pair<>(new WorldGenMinable(ore, config.spawnSize), config));
	}
	
	
	private void
			runGenerator(WorldGenerator generator, OreGenConfig config, World world, Random rand, int chunk_X, int chunk_Z) {
		for (int i = 0; i < config.spawnTries; ++i) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = config.minHeight + rand.nextInt(config.heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, x, y, z);
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int dimension = world.provider.dimensionId;
		if(ListUtil.contains(OreFamilies.globalDimensionBlackList, dimension)) return;
		
		for (Pair<WorldGenMinable, OreGenConfig> pair : _worldGenMinabes) {
			WorldGenMinable generator = pair.getLeft();
			OreGenConfig config = pair.getRight();
			if(!ListUtil.contains(config.dimensionBlackList, dimension))
				runGenerator(generator, config, world, random, chunkX, chunkZ);
		}
	}
}

