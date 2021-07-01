package juniebyte.javadungeons.content;

import juniebyte.javadungeons.biome.*;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static juniebyte.javadungeons.JavaDungeons.MOD_ID;

public class Biomes {

	public static CreeperWoodsBiome CREEPER_WOODS_BIOME;
	public static CreeperFieldsBiome CREEPER_FIELDS_BIOME;
	public static PumpkinPasturesBiome PUMPKIN_PASTURES_BIOME;
	public static DungeonsPlainsBiome DUNGEONS_PLAINS_BIOME;
	public static CactiCanyonBiome CACTI_CANYON_BIOME;
	public static CactiCanyonDesertBiome CACTI_CANYON_DESERT_BIOME;
	public static CactiCanyonDesertBiome CACTI_CANYON_DESERT_HILLS_BIOME;
	public static CactiCanyonDesertBiome CACTI_CANYON_DESERT_LAKES_BIOME;
	public static SoggySwampBiome SOGGY_SWAMP_BIOME;
	public static SoggySwampBiome SOGGY_SWAMP_HILLS_BIOME;
	public static Biome DINGY_JUNGLE;
	public static Biome MODIFIED_DINGY_JUNGLE;
	public static Biome DINGY_JUNGLE_EDGE;
	public static Biome MODIFIED_DINGY_JUNGLE_EDGE;
	public static Biome DINGY_JUNGLE_HILLS;

	public static RegistryKey<Biome> CREEPER_WOODS_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "creeper_woods"));
	public static RegistryKey<Biome> CREEPER_FIELDS_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "creeper_fields"));
	public static RegistryKey<Biome> PUMPKIN_PASTURES_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "pumpkin_pastures"));
	public static RegistryKey<Biome> DUNGEONS_PLAINS_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "dungeons_plains"));
	public static RegistryKey<Biome> CACTI_CANYON_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "cacti_canyon"));
	public static RegistryKey<Biome> CACTI_CANYON_DESERT_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "cacti_canyon_desert"));
	public static RegistryKey<Biome> CACTI_CANYON_DESERT_HILLS_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "cacti_canyon_desert_hills"));
	public static RegistryKey<Biome> CACTI_CANYON_DESERT_LAKES_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "cacti_canyon_desert_lakes"));
	public static RegistryKey<Biome> SOGGY_SWAMP_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "soggy_swamp"));
	public static RegistryKey<Biome> SOGGY_SWAMP_BIOME_HILLS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "soggy_swamp_hills"));

	public static RegistryKey<Biome> DINGY_JUNGLE_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "dingy_jungle"));
	public static RegistryKey<Biome> MODIFIED_DINGY_JUNGLE_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "modified_dingy_jungle"));
	public static RegistryKey<Biome> DINGY_JUNGLE_EDGE_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "dingy_jungle_edge"));
	public static RegistryKey<Biome> MODIFIED_DINGY_JUNGLE_EDGE_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "modified_dingy_jungle_edge"));
	public static RegistryKey<Biome> DINGY_JUNGLE_HILLS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "dingy_jungle_hills"));

	public static void init() {
		// register biomes
		CREEPER_WOODS_BIOME = Registry.register(BuiltinRegistries.BIOME, CREEPER_WOODS_BIOME_KEY.getValue(), new CreeperWoodsBiome());
		CREEPER_FIELDS_BIOME = Registry.register(BuiltinRegistries.BIOME, CREEPER_FIELDS_BIOME_KEY.getValue(), new CreeperFieldsBiome());
		PUMPKIN_PASTURES_BIOME = Registry.register(BuiltinRegistries.BIOME, PUMPKIN_PASTURES_BIOME_KEY.getValue(), new PumpkinPasturesBiome());
		DUNGEONS_PLAINS_BIOME = Registry.register(BuiltinRegistries.BIOME, DUNGEONS_PLAINS_BIOME_KEY.getValue(), new DungeonsPlainsBiome());
		CACTI_CANYON_BIOME = Registry.register(BuiltinRegistries.BIOME, CACTI_CANYON_BIOME_KEY.getValue(), new CactiCanyonBiome());
		CACTI_CANYON_DESERT_BIOME = Registry.register(BuiltinRegistries.BIOME, CACTI_CANYON_DESERT_BIOME_KEY.getValue(), new CactiCanyonDesertBiome(0.125F, 0.05F));
		CACTI_CANYON_DESERT_HILLS_BIOME = Registry.register(BuiltinRegistries.BIOME, CACTI_CANYON_DESERT_HILLS_BIOME_KEY.getValue(), new CactiCanyonDesertBiome(0.45F, 0.3F));
		CACTI_CANYON_DESERT_LAKES_BIOME = Registry.register(BuiltinRegistries.BIOME, CACTI_CANYON_DESERT_LAKES_BIOME_KEY.getValue(), new CactiCanyonDesertBiome(0.225F, 0.25F));
		SOGGY_SWAMP_BIOME = Registry.register(BuiltinRegistries.BIOME, SOGGY_SWAMP_BIOME_KEY.getValue(), new SoggySwampBiome(/*0.05F, 0.1F*/-0.2F, 0.1F));
		SOGGY_SWAMP_HILLS_BIOME = Registry.register(BuiltinRegistries.BIOME, SOGGY_SWAMP_BIOME_HILLS_KEY.getValue(), new SoggySwampBiome(/*0.05F, 0.1F*/-0.1F, 0.3F));

		DINGY_JUNGLE = Registry.register(BuiltinRegistries.BIOME, DINGY_JUNGLE_KEY.getValue(), createJungle());
		MODIFIED_DINGY_JUNGLE = Registry.register(BuiltinRegistries.BIOME, MODIFIED_DINGY_JUNGLE_KEY.getValue(), createModifiedJungle());
		DINGY_JUNGLE_EDGE = Registry.register(BuiltinRegistries.BIOME, DINGY_JUNGLE_EDGE_KEY.getValue(), createJungleEdge());
		MODIFIED_DINGY_JUNGLE_EDGE = Registry.register(BuiltinRegistries.BIOME, MODIFIED_DINGY_JUNGLE_EDGE_KEY.getValue(), createModifiedJungleEdge());
		DINGY_JUNGLE_HILLS = Registry.register(BuiltinRegistries.BIOME, DINGY_JUNGLE_HILLS_KEY.getValue(), createJungleHills());

		// add biomes to worldgen
		OverworldBiomes.addContinentalBiome(CREEPER_WOODS_BIOME_KEY, OverworldClimate.COOL, 2D);
		OverworldBiomes.addHillsBiome(CREEPER_WOODS_BIOME_KEY, CREEPER_FIELDS_BIOME_KEY, 0.1D);
		OverworldBiomes.addContinentalBiome(PUMPKIN_PASTURES_BIOME_KEY, OverworldClimate.TEMPERATE, 2.25D);
		OverworldBiomes.addContinentalBiome(DUNGEONS_PLAINS_BIOME_KEY, OverworldClimate.TEMPERATE, 3D);
		OverworldBiomes.addContinentalBiome(SOGGY_SWAMP_BIOME_KEY, OverworldClimate.TEMPERATE, 2.25D);
		OverworldBiomes.addHillsBiome(SOGGY_SWAMP_BIOME_KEY, SOGGY_SWAMP_BIOME_HILLS_KEY, 0.1D);
		OverworldBiomes.addContinentalBiome(CACTI_CANYON_BIOME_KEY, OverworldClimate.DRY, 2.5D);
		OverworldBiomes.addHillsBiome(CACTI_CANYON_BIOME_KEY, CACTI_CANYON_DESERT_BIOME_KEY, 0.6D);
		OverworldBiomes.addHillsBiome(CACTI_CANYON_BIOME_KEY, CACTI_CANYON_DESERT_HILLS_BIOME_KEY, 0.2D);
		OverworldBiomes.addHillsBiome(CACTI_CANYON_BIOME_KEY, CACTI_CANYON_DESERT_LAKES_BIOME_KEY, 0.1D);
		OverworldBiomes.addHillsBiome(BiomeKeys.DESERT, CACTI_CANYON_DESERT_BIOME_KEY, 0.6D);
		OverworldBiomes.addHillsBiome(BiomeKeys.DESERT_HILLS, CACTI_CANYON_DESERT_HILLS_BIOME_KEY, 0.2D);
		OverworldBiomes.addHillsBiome(BiomeKeys.DESERT_LAKES, CACTI_CANYON_DESERT_LAKES_BIOME_KEY, 0.1D);
	}

	public static Biome createJungle() {
		return createJungle(0.1F, 0.2F, 40, 2, 3);
	}

	public static Biome createJungleEdge() {
		SpawnSettings.Builder builder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addJungleMobs(builder);
		return createJungleFeatures(0.1F, 0.2F, 0.8F, true, false, builder);
	}

	public static Biome createModifiedJungleEdge() {
		SpawnSettings.Builder builder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addJungleMobs(builder);
		return createJungleFeatures(0.2F, 0.4F, 0.8F, true, true, builder);
	}

	public static Biome createModifiedJungle() {
		SpawnSettings.Builder builder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addJungleMobs(builder);
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, 10, 1, 1)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 2, 1, 1));
		return createJungleFeatures(0.2F, 0.4F, 0.9F, false, true, builder);
	}

	public static Biome createJungleHills() {
		return createJungle(0.45F, 0.3F, 10, 1, 1);
	}

	private static Biome createJungle(float depth, float scale, int parrotWeight, int parrotMaxGroupSize, int ocelotMaxGroupSize) {
		SpawnSettings.Builder builder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addJungleMobs(builder);
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PARROT, parrotWeight, 1, parrotMaxGroupSize)).spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 2, 1, ocelotMaxGroupSize)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PANDA, 1, 1, 2));
		builder.playerSpawnFriendly();
		return createJungleFeatures(depth, scale, 0.9F, false, false, builder);
	}

	private static Biome createJungleFeatures(float depth, float scale, float downfall, boolean edge, boolean modified, SpawnSettings.Builder builder) {
		GenerationSettings.Builder builder2 = (new GenerationSettings.Builder()).surfaceBuilder(JDConfiguredSurfaceBuilders.DINGY_JUNGLE);
		if (!edge && !modified) {
			builder2.structureFeature(net.minecraft.world.gen.feature.ConfiguredStructureFeatures.JUNGLE_PYRAMID);
		}

		DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
		builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_JUNGLE);
		DefaultBiomeFeatures.addLandCarvers(builder2);
		DefaultBiomeFeatures.addDefaultLakes(builder2);
		DefaultBiomeFeatures.addAmethystGeodes(builder2);
		DefaultBiomeFeatures.addDungeons(builder2);
		DefaultBiomeFeatures.addMineables(builder2);
		DefaultBiomeFeatures.addDefaultOres(builder2);
		DefaultBiomeFeatures.addDefaultDisks(builder2);

		if (!edge && !modified) {
			DefaultBiomeFeatures.addBamboo(builder2);
		}

		if (edge) {
			builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.DJ_TREES_EDGE);
		} else {
			builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, JDConfiguredFeatures.DJ_TREES);
		}

		DefaultBiomeFeatures.addExtraDefaultFlowers(builder2);
		DefaultBiomeFeatures.addJungleGrass(builder2);
		DefaultBiomeFeatures.addDefaultMushrooms(builder2);
		DefaultBiomeFeatures.addDefaultVegetation(builder2);
		DefaultBiomeFeatures.addSprings(builder2);
		DefaultBiomeFeatures.addJungleVegetation(builder2);
		DefaultBiomeFeatures.addFrozenTopLayer(builder2);
		return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.JUNGLE).depth(depth).scale(scale).temperature(0.95F).downfall(downfall).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).foliageColor(0x849934).grassColor(0x415a22).fogColor(0x7c9370).skyColor(/*calcSkyColor(0.95F)*/0xa9c09b).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
	}

	//Vanilla uses this for sky color.
	public static int calcSkyColor(float f) {
		float g = f / 3.0F;
		g = MathHelper.clamp(g, - 1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - g * 0.05F, 0.5F + g * 0.1F, 1.0F);
	}
}
