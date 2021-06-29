package juniebyte.javadungeons.biome;

import juniebyte.javadungeons.content.CactiCanyonBlocks;
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
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.DoublePlantPlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;

import static juniebyte.javadungeons.JavaDungeons.MOD_ID;
import static juniebyte.javadungeons.content.Biomes.calcSkyColor;

public final class CactiCanyonDesertBiome extends Biome {
	static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = JDConfiguredSurfaceBuilders.CACTI_CANYON_DESERT;
	static final Biome.Weather WEATHER = new Biome.Weather(
			Precipitation.NONE, 2.0F,
			TemperatureModifier.NONE, 0.0F
	);
	static final BiomeEffects.Builder BIOME_EFFECTS = new BiomeEffects.Builder()
			.waterColor(4159204)
			.waterFogColor(329011)
			.grassColor(0x197862)
			.foliageColor(0x197862)
			.fogColor(12638463)
			.skyColor(calcSkyColor(1.3F))
			.moodSound(BiomeMoodSound.CAVE);
	static final GenerationSettings.Builder GENERATION_SETTINGS = (new GenerationSettings.Builder()).surfaceBuilder(SURFACE_BUILDER);
	static final SpawnSettings.Builder SPAWN_SETTINGS = new SpawnSettings.Builder();

	public CactiCanyonDesertBiome(float depth, float scale) {
		super(WEATHER, Biome.Category.DESERT, depth, scale, BIOME_EFFECTS.build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
	}

	static {
		GENERATION_SETTINGS.structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS);
		GENERATION_SETTINGS.structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);
		GENERATION_SETTINGS.structureFeature(ConfiguredStructureFeatures.DESERT_PYRAMID);
		GENERATION_SETTINGS.structureFeature(Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(MOD_ID, "cc_mineshaft"), StructureFeature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004F, MineshaftFeature.Type.NORMAL))));
		GENERATION_SETTINGS.structureFeature(ConfiguredStructureFeatures.STRONGHOLD);
		GENERATION_SETTINGS.structureFeature(Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(MOD_ID, "cc_desert_ruined_portal"), StructureFeature.RUINED_PORTAL.configure(new RuinedPortalFeatureConfig(RuinedPortalFeature.Type.DESERT))));

		DefaultBiomeFeatures.addLandCarvers(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultUndergroundStructures(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDesertLakes(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDungeons(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addMineables(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultOres(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultDisks(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultFlowers(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultGrass(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDesertDeadBushes(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultMushrooms(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDesertVegetation(GENERATION_SETTINGS);

		// add cacti canyon desert vegatation

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("desert_cactus", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_CACTUS.getDefaultState()), new SimpleBlockPlacer())).tries(2).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("desert_small_cacti", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_SMALL_CACTUS.getDefaultState()), new SimpleBlockPlacer())).tries(1).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("desert_yucca", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_YUCCA.getDefaultState()), new DoublePlantPlacer())).tries(1).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("desert_tall_cacti", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_TALL_CACTUS.getDefaultState()), new DoublePlantPlacer())).tries(1).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		DefaultBiomeFeatures.addSprings(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDesertFeatures(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addFrozenTopLayer(GENERATION_SETTINGS);
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
		SPAWN_SETTINGS.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 19, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 1, 1, 1));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 80, 4, 4));
	}
}
