package yuma140902.hundredsofores.worldGen;

import cpw.mods.fml.common.registry.GameRegistry;

public final class WorldGenerators {
	private WorldGenerators() {}
	
	private static final int WORLD_GENERATOR_PRIORITY = 10; //ジェネレーターの優先順位。数字が小さいほど優先順位が高い
	
	public static void register() {
		GameRegistry.registerWorldGenerator(oreGenerator, WORLD_GENERATOR_PRIORITY);
//		GameRegistry.registerWorldGenerator(meteorGenerator, WORLD_GENERATOR_PRIORITY);
//		GameRegistry.registerWorldGenerator(theEndScaffoldGenerator, WORLD_GENERATOR_PRIORITY);
//		GameRegistry.registerWorldGenerator(octahedronGenerator, WORLD_GENERATOR_PRIORITY);
	}
	
	public static final MyOreGenerator oreGenerator = new MyOreGenerator();
//	public static final MeteorGenerator meteorGenerator = new MeteorGenerator();
//	public static final TheEndScaffoldGenerator theEndScaffoldGenerator = new TheEndScaffoldGenerator();
//	public static final OctahedronGenerator octahedronGenerator = new OctahedronGenerator();
}
