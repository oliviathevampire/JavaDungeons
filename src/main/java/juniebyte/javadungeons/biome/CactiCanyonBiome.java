package juniebyte.javadungeons.biome;

import juniebyte.javadungeons.content.CactiCanyonBlocks;
import juniebyte.javadungeons.content.JDConfiguredFeatures;
import juniebyte.javadungeons.content.JDConfiguredSurfaceBuilders;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;
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

public class CactiCanyonBiome extends Biome {
	static final Biome.Weather WEATHER = new Biome.Weather(
			Precipitation.NONE, 2.0F,
			TemperatureModifier.NONE, 0.0F
	);
	static final BiomeEffects.Builder BIOME_EFFECTS = new BiomeEffects.Builder()
			.waterColor(4159204)
			.waterFogColor(4159204)
			.grassColor(0x197862)
			.foliageColor(0x197862)
			.fogColor(12638463)
			.skyColor(calcSkyColor(2.0F))
			.moodSound(BiomeMoodSound.CAVE);
	static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = JDConfiguredSurfaceBuilders.CACTI_CANYON;
	static final GenerationSettings.Builder GENERATION_SETTINGS = new GenerationSettings.Builder()
			.surfaceBuilder(SURFACE_BUILDER);
	static final SpawnSettings.Builder SPAWN_SETTINGS = new SpawnSettings.Builder();

	public CactiCanyonBiome() {
		super(WEATHER, Biome.Category.MESA, 0.1F, 0.2F, BIOME_EFFECTS.build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
	}

	static {
		GENERATION_SETTINGS.structureFeature(ConfiguredStructureFeatures.MINESHAFT);
		GENERATION_SETTINGS.structureFeature(ConfiguredStructureFeatures.STRONGHOLD);
		GENERATION_SETTINGS.structureFeature(Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(MOD_ID, "cc_ruined_portal"), StructureFeature.RUINED_PORTAL.configure(new RuinedPortalFeatureConfig(RuinedPortalFeature.Type.STANDARD))));

		DefaultBiomeFeatures.addLandCarvers(GENERATION_SETTINGS);

		DefaultBiomeFeatures.addDefaultUndergroundStructures(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addAmethystGeodes(GENERATION_SETTINGS);

		GENERATION_SETTINGS.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, JDConfiguredFeatures.DUNGEONS_WATER_LAKE);

		DefaultBiomeFeatures.addDungeons(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addMineables(GENERATION_SETTINGS);

		GENERATION_SETTINGS.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, Registry.register(
				BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(MOD_ID, "replace_normal_stone"),
				Feature.NETHERRACK_REPLACE_BLOBS.configure(
						new ReplaceBlobsFeatureConfig(
								Blocks.STONE.getDefaultState(),
								Blocks.CALCITE.getDefaultState(),
								ConstantIntProvider.create(60)
						)
				).range(ConfiguredFeatures.Decorators.BOTTOM_TO_TOP).spreadHorizontally().repeat(25)
		));

		// add cacti canyon dirt
		GENERATION_SETTINGS.feature(GenerationStep.Feature.UNDERGROUND_ORES, JDConfiguredFeatures.CC_DIRT);

		DefaultBiomeFeatures.addDefaultOres(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultDisks(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultMushrooms(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addDefaultVegetation(GENERATION_SETTINGS);

		// add cacti canyon vegatation
		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.CC_GRASS);

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("cc_fern", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_FERN.getDefaultState()), new SimpleBlockPlacer())).tries(32).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(2)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("cactus", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_CACTUS.getDefaultState()), new SimpleBlockPlacer())).tries(4).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("small_cacti", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_SMALL_CACTUS.getDefaultState()), new SimpleBlockPlacer())).tries(2).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("flowers", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_FLOWERS.getDefaultState()), new SimpleBlockPlacer())).tries(8).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("yucca", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_YUCCA.getDefaultState()), new DoublePlantPlacer())).tries(2).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		GENERATION_SETTINGS.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.registerConfiguredFeature("tall_cacti", Feature.RANDOM_PATCH.configure(
				(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(CactiCanyonBlocks.CC_TALL_CACTUS.getDefaultState()), new DoublePlantPlacer())).tries(1).build()
		).decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(1)))));

		DefaultBiomeFeatures.addSprings(GENERATION_SETTINGS);
		DefaultBiomeFeatures.addFrozenTopLayer(GENERATION_SETTINGS);

		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		SPAWN_SETTINGS.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
	}
}
