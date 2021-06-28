package juniebyte.javadungeons;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.structure.Structure;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;

public interface IForgeStructure {
	default StructureFeature<?> getStructure() {
		return (StructureFeature<?>) this;
	}

	/**
	 * Gets the default list of {@link SpawnGroup#MONSTER} spawns for this structure.
	 *
	 * @apiNote Implement this over {@link StructureFeature#getMonsterSpawns()}
	 */
	default Pool<SpawnSettings.SpawnEntry> getDefaultSpawnList() {
		return Pool.empty();
	}

	/**
	 * Gets the default list of {@link SpawnGroup#CREATURE} spawns for this structure.
	 *
	 * @apiNote Implement this over {@link StructureFeature#getCreatureSpawns()}
	 */
	default Pool<SpawnSettings.SpawnEntry> getDefaultCreatureSpawnList() {
		return Pool.empty();
	}

    /**
     * Gets the default list of {@link SpawnGroup#CREATURE} spawns for this structure.
     *
     * @apiNote Implement this over {@link StructureFeature#getUndergroundWaterCreatureSpawns()}
     */
    default Pool<SpawnSettings.SpawnEntry> getDefaultUnderwaterCreatureSpawnList() {
        return Pool.empty();
    }

	/**
	 * Gets the default for if entity spawns are restricted to being inside of the pieces making up the structure or if being in the bounds of the overall structure
	 * is good enough.
	 *
	 * @return {@code true} if the location to check spawns for has to be inside of the pieces making up the structure, {@code false} otherwise.
	 */
	default boolean getDefaultRestrictsSpawnsToInside() {
		//The Pillager Outpost and Ocean Monument check the full structure by default instead of limiting themselves to being within the structure's bounds
		return getStructure() != StructureFeature.PILLAGER_OUTPOST && getStructure() != StructureFeature.MONUMENT;
	}

	/**
	 * Helper method to get the list of entity spawns for this structure for the given classification.
	 *
	 * @param classification The classification of entities.
	 *
	 * @apiNote This method is marked as final in {@link Structure} so as to not be overridden by modders and breaking support for
	 * {@link net.minecraftforge.event.world.StructureSpawnListGatherEvent}.
	 */
	default List<SpawnSettings.SpawnEntry> getSpawnList(SpawnGroup classification) {
		return StructureSpawnManager.getSpawnList(getStructure(), classification);
	};
}