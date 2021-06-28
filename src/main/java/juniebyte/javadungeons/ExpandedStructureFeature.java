package juniebyte.javadungeons;

import com.mojang.serialization.Codec;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;

public abstract class ExpandedStructureFeature<C extends FeatureConfig> extends StructureFeature<C> implements IForgeStructure {
	public ExpandedStructureFeature(Codec<C> codec) {
		super(codec);
	}

	@Override
	public Pool<SpawnSettings.SpawnEntry> getMonsterSpawns() {
		return getDefaultSpawnList();
	}

	@Override
	public Pool<SpawnSettings.SpawnEntry> getCreatureSpawns() {
		return getDefaultCreatureSpawnList();
	}

	@Override
	public Pool<SpawnSettings.SpawnEntry> getUndergroundWaterCreatureSpawns() {
		return getDefaultUnderwaterCreatureSpawnList();
	}

	@Override
	public StructureFeature<?> getStructure() {
		return IForgeStructure.super.getStructure();
	}

	@Override
	public Pool<SpawnSettings.SpawnEntry> getDefaultSpawnList() {
		return IForgeStructure.super.getDefaultSpawnList();
	}

	@Override
	public Pool<SpawnSettings.SpawnEntry> getDefaultCreatureSpawnList() {
		return IForgeStructure.super.getDefaultCreatureSpawnList();
	}

	@Override
	public Pool<SpawnSettings.SpawnEntry> getDefaultUnderwaterCreatureSpawnList() {
		return IForgeStructure.super.getDefaultUnderwaterCreatureSpawnList();
	}

	@Override
	public boolean getDefaultRestrictsSpawnsToInside() {
		return IForgeStructure.super.getDefaultRestrictsSpawnsToInside();
	}

	@Override
	public List<SpawnSettings.SpawnEntry> getSpawnList(SpawnGroup classification) {
		return StructureSpawnManager.getSpawnList(getStructure(), classification);
	}
}
