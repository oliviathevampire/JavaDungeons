package juniebyte.javadungeons.content;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import static juniebyte.javadungeons.JavaDungeons.MOD_ID;

public class ConfiguredSurfaceBuilders {

	public static ConfiguredSurfaceBuilder<?> CACTI_CANYON;
	public static ConfiguredSurfaceBuilder<?> CREEPER_WOODS;

	public static void init() {
		CACTI_CANYON = newConfiguredSurfaceBuilder("cacti_canyon", new ConfiguredSurfaceBuilder<>(SurfaceBuilders.CACTI_CANYON,
				new TernarySurfaceConfig(
						CactiCanyonBlocks.CC_GRASS_BLOCK.getDefaultState(),
						CactiCanyonBlocks.CC_SANDSTONE.getDefaultState(),
						CactiCanyonBlocks.CC_DIRT.getDefaultState()
				))
		);
		CREEPER_WOODS = newConfiguredSurfaceBuilder("creeper_woods", new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT,
				new TernarySurfaceConfig(
						CreeperWoodsBlocks.CW_GRASS_BLOCK.getDefaultState(),
						CreeperWoodsBlocks.CW_DIRT.getDefaultState(),
						CreeperWoodsBlocks.CW_DIRT.getDefaultState()
				))
		);
	}

	public static ConfiguredSurfaceBuilder<?> newConfiguredSurfaceBuilder(String id, ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder) {
		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MOD_ID, id), configuredSurfaceBuilder);
		return configuredSurfaceBuilder;
	}

}
