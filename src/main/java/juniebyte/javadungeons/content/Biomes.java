package juniebyte.javadungeons.content;

import juniebyte.javadungeons.biome.*;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

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

	//Vanilla uses this for sky color.
	public static int calcSkyColor(float f) {
		float g = f / 3.0F;
		g = MathHelper.clamp(g, - 1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - g * 0.05F, 0.5F + g * 0.1F, 1.0F);
	}
}
