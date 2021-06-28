package juniebyte.javadungeons.content;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import static juniebyte.javadungeons.JavaDungeons.MOD_ID;

public class JDConfiguredSurfaceBuilders {

	public static ConfiguredSurfaceBuilder<?> CACTI_CANYON;
	public static ConfiguredSurfaceBuilder<?> CACTI_CANYON_DESERT;
	public static ConfiguredSurfaceBuilder<?> CREEPER_WOODS;
	public static ConfiguredSurfaceBuilder<?> DUNGEONS_PLAINS;
	public static ConfiguredSurfaceBuilder<?> PUMPKIN_PASTURES;
	public static ConfiguredSurfaceBuilder<?> SOGGY_SWAMP;

	public static void init() {
		CACTI_CANYON = newConfiguredSurfaceBuilder("cacti_canyon", new ConfiguredSurfaceBuilder<>(JDSurfaceBuilders.CACTI_CANYON,
				new TernarySurfaceConfig(
						CactiCanyonBlocks.CC_GRASS_BLOCK.getDefaultState(),
						CactiCanyonBlocks.CC_SANDSTONE.getDefaultState(),
						CactiCanyonBlocks.CC_DIRT.getDefaultState()
				))
		);
		CACTI_CANYON_DESERT = newConfiguredSurfaceBuilder("cacti_canyon_desert", new ConfiguredSurfaceBuilder<>(JDSurfaceBuilders.CACTI_CANYON,
				new TernarySurfaceConfig(
						CactiCanyonBlocks.CC_SAND.getDefaultState(),
						CactiCanyonBlocks.CC_SANDSTONE.getDefaultState(),
						Blocks.GRAVEL.getDefaultState()
				)
		));
		CREEPER_WOODS = newConfiguredSurfaceBuilder("creeper_woods", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT,
				new TernarySurfaceConfig(
						CreeperWoodsBlocks.CW_GRASS_BLOCK.getDefaultState(),
						CreeperWoodsBlocks.CW_DIRT.getDefaultState(),
						CreeperWoodsBlocks.CW_DIRT.getDefaultState()
				))
		);
		DUNGEONS_PLAINS = newConfiguredSurfaceBuilder("plains", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT,
				new TernarySurfaceConfig(
						GenericBlocks.GRASS_BLOCK.getDefaultState(),
						GenericBlocks.DIRT.getDefaultState(),
						GenericBlocks.DIRT.getDefaultState()
				))
		);
		PUMPKIN_PASTURES = newConfiguredSurfaceBuilder("pumpkin_pastures", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT,
				new TernarySurfaceConfig(
						GenericBlocks.GRASS_BLOCK.getDefaultState(),
						GenericBlocks.DIRT.getDefaultState(),
						GenericBlocks.DIRT.getDefaultState()
				))
		);
		SOGGY_SWAMP = newConfiguredSurfaceBuilder("soggy_swamp", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.SWAMP,
				new TernarySurfaceConfig(
						SoggySwampBlocks.SS_GRASS_BLOCK.getDefaultState(),
						SoggySwampBlocks.SS_DIRT.getDefaultState(),
						SoggySwampBlocks.SS_DIRT.getDefaultState()
				))
		);
	}

	public static ConfiguredSurfaceBuilder<?> newConfiguredSurfaceBuilder(String id, ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder) {
		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MOD_ID, id), configuredSurfaceBuilder);
		return configuredSurfaceBuilder;
	}

}
