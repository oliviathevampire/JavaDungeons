package juniebyte.javadungeons.biome;

import juniebyte.javadungeons.content.JDConfiguredFeatures;
import juniebyte.javadungeons.content.JDConfiguredSurfaceBuilders;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.WaterDepthThresholdDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.MineshaftFeature.Type;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;

import static juniebyte.javadungeons.JavaDungeons.MOD_ID;
import static juniebyte.javadungeons.content.Biomes.calcSkyColor;

public final class SoggySwampBiome extends Biome {

	static final Biome.Weather WEATHER = new Biome.Weather(
			Precipitation.RAIN, 0.8F,
			TemperatureModifier.NONE, 0.9F
	);
	static final BiomeEffects.Builder BIOME_EFFECTS = new BiomeEffects.Builder()
			.waterColor(6388580)
			.waterFogColor(2302743)
			.grassColor(0x6a7565)
			.grassColorModifier(BiomeEffects.GrassColorModifier.SWAMP)
			.foliageColor(0x6c6e4f)
			.fogColor(12638463)
			.skyColor(calcSkyColor(0.25F))
			.moodSound(BiomeMoodSound.CAVE);
	static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = JDConfiguredSurfaceBuilders.SOGGY_SWAMP;
	static final GenerationSettings.Builder GENERATION_SETTINGS = new GenerationSettings.Builder()
			.surfaceBuilder(SURFACE_BUILDER);
	static final SpawnSettings.Builder SPAWN_SETTINGS = new SpawnSettings.Builder();

	public SoggySwampBiome(float depth, float scale) {
		super(WEATHER, Category.SWAMP, depth, scale, BIOME_EFFECTS.build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
	}

	static {
		GENERATION_SETTINGS.structureFeature(StructureFeature.SWAMP_HUT.configure(FeatureConfig.DEFAULT));
		GENERATION_SETTINGS.structureFeature(StructureFeature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004F, Type.NORMAL)));
		GENERATION_SETTINGS.structureFeature(StructureFeature.RUINED_PORTAL.configure(new RuinedPortalFeatureConfig(RuinedPortalFeature.Type.SWAMP)));

		DefaultBiomeFeatures.addLandCarvers(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultUndergroundStructures(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDungeons(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addMineables(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultOres(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addClayDisk(GENERATION_SETTINGS);

		DefaultBiomeFeatures.addSwampVegetation(GENERATION_SETTINGS);
		// add swamp features
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FLOWER_SWAMP);
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_GRASS_NORMAL);
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_DEAD_BUSH);
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_WATERLILLY);
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_SWAMP);
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.RED_MUSHROOM_SWAMP);

		// swamp trees
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MOD_ID, "ss_trees"), JDConfiguredFeatures.SS_SWAMP_TREE
				.decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_OCEAN_FLOOR)
				.decorate(Decorator.WATER_DEPTH_THRESHOLD
						.configure(new WaterDepthThresholdDecoratorConfig(1))).spreadHorizontally()
				.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(2, 0.1F, 1)))
		));

		DefaultBiomeFeatures.addDefaultMushrooms(GENERATION_SETTINGS);

		GENERATION_SETTINGS.feature(GenerationStep.Feature.LAKES, JDConfiguredFeatures.SWAMP_WATER_LAKE);

		GENERATION_SETTINGS.feature(net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SEAGRASS_SWAMP);

		DefaultBiomeFeatures.addFossils(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addFrozenTopLayer(GENERATION_SETTINGS);

		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 1, 1, 1));
	}
}
