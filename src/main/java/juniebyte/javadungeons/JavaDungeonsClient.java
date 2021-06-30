package juniebyte.javadungeons;

import juniebyte.javadungeons.blocks.DungeonsTransformer;
import juniebyte.javadungeons.content.*;
import juniebyte.javadungeons.gui.DungeonsTransformerScreen;
import juniebyte.javadungeons.gui.DungeonsTransformerScreenHandler;
import juniebyte.javadungeons.particles.GreenFlameParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;

import java.util.function.Function;

public class JavaDungeonsClient implements ClientModInitializer {

	private static final RenderLayer CUTOUT_BLOCK_LAYER = RenderLayer.getCutout();
	private static final RenderLayer TRANSLUCENT_BLOCK_LAYER = RenderLayer.getTranslucent();

	private static final BlockColorProvider GRASS_BLOCK_COLORS = (state, view, pos, tintIndex) -> {
		return view != null && pos != null ? BiomeColors.getGrassColor(view, pos) : GrassColors.getColor(0.5D, 1.0D);
	};

	private static final ItemColorProvider GRASS_ITEM_COLORS = (item, layer) -> GrassColors.getColor(0.5D, 1.0D);

    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register(
            GRASS_BLOCK_COLORS,
            GenericBlocks.SHORT_GRASS,
            GenericBlocks.FERN,
            GenericBlocks.DENSE_GRASSY_DIRT,
            GenericBlocks.GRASSY_DIRT,
            GenericBlocks.GRASS_BLOCK,
            GenericBlocks.ROCKY_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_GRASS_BLOCK,
            CreeperWoodsBlocks.CW_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_DENSE_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_ROCKY_GRASSY_DIRT,
            CactiCanyonBlocks.CC_GRASS_BLOCK,
            CactiCanyonBlocks.CC_GRASSY_DIRT,
            CactiCanyonBlocks.CC_DENSE_GRASSY_DIRT
        );

        ColorProviderRegistry.ITEM.register(
            GRASS_ITEM_COLORS,
            GenericBlocks.SHORT_GRASS,
            GenericBlocks.FERN,
            GenericBlocks.DENSE_GRASSY_DIRT,
            GenericBlocks.GRASSY_DIRT,
            GenericBlocks.GRASS_BLOCK,
            GenericBlocks.ROCKY_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_GRASS_BLOCK,
            CreeperWoodsBlocks.CW_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_DENSE_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_ROCKY_GRASSY_DIRT,
            CactiCanyonBlocks.CC_GRASS_BLOCK,
            CactiCanyonBlocks.CC_GRASSY_DIRT,
            CactiCanyonBlocks.CC_DENSE_GRASSY_DIRT
        );

        // register render layers
        BlockRenderLayerMap.INSTANCE.putBlocks(
            CUTOUT_BLOCK_LAYER,
            GenericBlocks.SHORT_GRASS,
            GenericBlocks.FERN,
            GenericBlocks.BERRY_BUSH_BLOCK,
            GenericBlocks.WATER_PLANT,
            GenericBlocks.DENSE_GRASSY_DIRT,
            GenericBlocks.GRASSY_DIRT,
            GenericBlocks.ROCKY_GRASSY_DIRT,
            GenericBlocks.GRASS_BLOCK,
            GenericBlocks.SHRUB,
            GenericBlocks.RIPPED_BANNER,
            GenericBlocks.TENT,
            GenericBlocks.DUNGEONS_LANTERN,
            GenericBlocks.HANGING_ROSES,
            GenericBlocks.YELLOW_TULIP,
            GenericBlocks.UNLIT_BRAZIER,
            GenericBlocks.LIT_BRAZIER,
            GenericBlocks.GREEN_LIT_BRAZIER,
            GenericBlocks.TRAY,
            GenericBlocks.TEAPOT,
            GenericBlocks.CHAINS,
            CreeperWoodsBlocks.CW_GRASS_BLOCK,
            CreeperWoodsBlocks.CW_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_DENSE_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_SHRUB,
            CreeperWoodsBlocks.CW_POP_FLOWER,
            CreeperWoodsBlocks.CW_ROCKY_GRASSY_DIRT,
            CreeperWoodsBlocks.CW_FLOWER_PATCH,
            PumpkinPasturesBlocks.PM_RED_AUTUMNAL_LEAVES,
            PumpkinPasturesBlocks.PM_YELLOW_AUTUMNAL_LEAVES,
            PumpkinPasturesBlocks.PM_CHARRED_GRASS,
            PumpkinPasturesBlocks.PM_SHRUB,
            PumpkinPasturesBlocks.PM_DEAD_SAPLING,
            PumpkinPasturesBlocks.PM_FERN,
            PumpkinPasturesBlocks.PM_RED_AUTUMNAL_SAPLING,
            PumpkinPasturesBlocks.PM_YELLOW_AUTUMNAL_SAPLING,
            CactiCanyonBlocks.CC_CACTUS,
            CactiCanyonBlocks.CC_SMALL_CACTUS,
            CactiCanyonBlocks.CC_FERN,
            CactiCanyonBlocks.CC_FLOWERS,
            CactiCanyonBlocks.CC_YUCCA,
            CactiCanyonBlocks.CC_TALL_CACTUS,
            CactiCanyonBlocks.CC_GRASS_BLOCK,
            CactiCanyonBlocks.CC_GRASSY_DIRT,
            CactiCanyonBlocks.CC_DENSE_GRASSY_DIRT,
            CactiCanyonBlocks.CC_DESERT_GRASS,
            RedstoneMinesBlocks.RM_SHRUB,
            SoggySwampBlocks.SS_SWAMP_SAPLING,
            DingyJungleBlocks.DJ_RED_FERN,
            DingyJungleBlocks.DJ_GRASS,
            DingyJungleBlocks.DJ_SHRUB,
            DingyJungleBlocks.DJ_DEAD_SHRUB,
            DingyJungleBlocks.DJ_BUSH,
            DingyJungleBlocks.DJ_TALL_FERN,
            DingyJungleBlocks.DJ_JUNGLE_LEAVES
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(
            TRANSLUCENT_BLOCK_LAYER,
            GenericBlocks.GLASS,
            GenericBlocks.BLUE_GLASS,
            GenericBlocks.BROWN_GLASS,
            GenericBlocks.BLACK_GLASS,
            GenericBlocks.GREEN_GLASS,
            GenericBlocks.YELLOW_GLASS,
            GenericBlocks.RED_GLASS,
            GenericBlocks.LIME_GLASS,
            GenericBlocks.LIGHT_GRAY_GLASS,
            GenericBlocks.PINK_GLASS,
            GenericBlocks.MAGENTA_GLASS,
            GenericBlocks.LIGHT_BLUE_GLASS,
            GenericBlocks.ORANGE_GLASS,
            GenericBlocks.GRAY_GLASS,
            GenericBlocks.PURPLE_GLASS,
            GenericBlocks.CYAN_GLASS,
            CreeperWoodsBlocks.CW_GLOW_MUSHROOM
        );
        // set up fluid rendering
        BlockRenderLayerMap.INSTANCE.putFluids(
            TRANSLUCENT_BLOCK_LAYER,
            Fluids.DUNGEONS_WATER_FLOWING,
            Fluids.DUNGEONS_WATER_STILL,
            Fluids.SOGGY_SWAMP_WATER_FLOWING,
            Fluids.SOGGY_SWAMP_WATER_STILL
        );

        // set up particles
        ParticleFactoryRegistry.getInstance().register(Particles.GREEN_FLAME, GreenFlameParticle.Factory::new);
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier(JavaDungeons.MOD_ID, "particle/green_flame"));
        });

        setupFluidRendering(
            Fluids.DUNGEONS_WATER_STILL, // still fluid object
            Fluids.DUNGEONS_WATER_FLOWING, // flowing fluid object
            new Identifier(JavaDungeons.MOD_ID, "dungeons_water"), // texture identifier
            0xFFFFFF // tint color (white because water is colored in its file)
        );

        setupFluidRendering(
            Fluids.SOGGY_SWAMP_WATER_STILL, // still fluid objectne
            Fluids.SOGGY_SWAMP_WATER_FLOWING, // flowing fluid object
            new Identifier(JavaDungeons.MOD_ID, "soggy_swamp/soggy_swamp_water"), // texture identifier
            0xFFFFFF // tint color (white because water is colored in its file)
        );

        // register containers to screens
        ScreenProviderRegistry.INSTANCE.<DungeonsTransformerScreenHandler>registerFactory(DungeonsTransformer.ID, screenHandler -> new DungeonsTransformerScreen(
            screenHandler,
            MinecraftClient.getInstance().player.getInventory(),
            DungeonsTransformer.CONTAINER_NAME.setStyle(Style.EMPTY)
        ));

        //TODO
		/*ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.PHANTOM_ARMOR, Armors.PHANTOM_ARMOR_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/phantom_armor.png", Armors.PHANTOM_ARMOR, Armors.PHANTOM_ARMOR_HELMET);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new EvocationRobeModel<>(1.0F, slot, entity), Armors.EVOCATION_ROBE, Armors.EVOCATION_ROBE_HAT);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/evocation_robe.png", Armors.EVOCATION_ROBE, Armors.EVOCATION_ROBE_HAT);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new EvocationRobeModel<>(1.0F, slot, entity), Armors.EMBER_ROBE, Armors.EMBER_ROBE_HAT);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/ember_robe.png", Armors.EMBER_ROBE, Armors.EMBER_ROBE_HAT);*/

		/*ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.SNOW_ARMOR, Armors.SNOW_ARMOR_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/snow_armor.png", Armors.SNOW_ARMOR, Armors.SNOW_ARMOR_HELMET);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.SNOW_ARMOR, Armors.SNOW_ARMOR_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/frost_armor.png", Armors.FROST_ARMOR, Armors.FROST_ARMOR_HELMET);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.FROST_BITE, Armors.FROST_BITE_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/frost_bite.png", Armors.FROST_BITE, Armors.FROST_BITE_HELMET);*/
    }    

    public static void setupFluidRendering(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color) {
		final Identifier stillSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_still");
		final Identifier flowingSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_flow");

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(stillSpriteId);
			registry.register(flowingSpriteId);
		});

		final Identifier fluidId = Registry.FLUID.getId(still);
		final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");

		final Sprite[] fluidSprites = {null, null};

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return listenerId;
			}

			/**
			 * Get the sprites from the block atlas when resources are reloaded
			 */
			@Override
			public void reload(ResourceManager resourceManager) {
				final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
				fluidSprites[0] = atlas.apply(stillSpriteId);
				fluidSprites[1] = atlas.apply(flowingSpriteId);
			}
		});

		// The FluidRenderer gets the sprites and color from a FluidRenderHandler during rendering
		final FluidRenderHandler renderHandler = new FluidRenderHandler() {
			@Override
			public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state) {
				return fluidSprites;
			}

			@Override
			public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
				return color;
			}
		};

		FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);
	}

	@Override
	public void onInitializeClient() {
		// register color providers
		ColorProviderRegistry.BLOCK.register(
				GRASS_BLOCK_COLORS,
				GenericBlocks.SHORT_GRASS,
				GenericBlocks.FERN,
				GenericBlocks.DENSE_GRASSY_DIRT,
				GenericBlocks.GRASSY_DIRT,
				GenericBlocks.GRASS_BLOCK,
				GenericBlocks.ROCKY_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_GRASS_BLOCK,
				CreeperWoodsBlocks.CW_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_DENSE_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_ROCKY_GRASSY_DIRT,
				CactiCanyonBlocks.CC_GRASS_BLOCK,
				CactiCanyonBlocks.CC_GRASSY_DIRT,
				CactiCanyonBlocks.CC_DENSE_GRASSY_DIRT
		);

		ColorProviderRegistry.ITEM.register(
				GRASS_ITEM_COLORS,
				GenericBlocks.SHORT_GRASS,
				GenericBlocks.FERN,
				GenericBlocks.DENSE_GRASSY_DIRT,
				GenericBlocks.GRASSY_DIRT,
				GenericBlocks.GRASS_BLOCK,
				GenericBlocks.ROCKY_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_GRASS_BLOCK,
				CreeperWoodsBlocks.CW_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_DENSE_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_ROCKY_GRASSY_DIRT,
				CactiCanyonBlocks.CC_GRASS_BLOCK,
				CactiCanyonBlocks.CC_GRASSY_DIRT,
				CactiCanyonBlocks.CC_DENSE_GRASSY_DIRT
		);

		// register render layers
		BlockRenderLayerMap.INSTANCE.putBlocks(
				CUTOUT_BLOCK_LAYER,
				GenericBlocks.SHORT_GRASS,
				GenericBlocks.FERN,
				GenericBlocks.BERRY_BUSH_BLOCK,
				GenericBlocks.WATER_PLANT,
				GenericBlocks.DENSE_GRASSY_DIRT,
				GenericBlocks.GRASSY_DIRT,
				GenericBlocks.ROCKY_GRASSY_DIRT,
				GenericBlocks.GRASS_BLOCK,
				GenericBlocks.SHRUB,
				GenericBlocks.RIPPED_BANNER,
				GenericBlocks.TENT,
				GenericBlocks.DUNGEONS_LANTERN,
				GenericBlocks.HANGING_ROSES,
				GenericBlocks.YELLOW_TULIP,
				GenericBlocks.UNLIT_BRAZIER,
				GenericBlocks.LIT_BRAZIER,
				GenericBlocks.LIT_WILDFIRE_BRAZIER,
				GenericBlocks.LIT_SOUL_BRAZIER,
				GenericBlocks.TRAY,
				GenericBlocks.TEAPOT,
				GenericBlocks.CHAINS,
				CreeperWoodsBlocks.CW_GRASS_BLOCK,
				CreeperWoodsBlocks.CW_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_DENSE_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_SHRUB,
				CreeperWoodsBlocks.CW_POP_FLOWER,
				CreeperWoodsBlocks.CW_ROCKY_GRASSY_DIRT,
				CreeperWoodsBlocks.CW_FLOWER_PATCH,
				PumpkinPasturesBlocks.PM_RED_AUTUMNAL_LEAVES,
				PumpkinPasturesBlocks.PM_YELLOW_AUTUMNAL_LEAVES,
				PumpkinPasturesBlocks.PM_CHARRED_GRASS,
				PumpkinPasturesBlocks.PM_SHRUB,
				PumpkinPasturesBlocks.PM_DEAD_SAPLING,
				PumpkinPasturesBlocks.PM_FERN,
				PumpkinPasturesBlocks.PM_RED_AUTUMNAL_SAPLING,
				PumpkinPasturesBlocks.PM_YELLOW_AUTUMNAL_SAPLING,
				CactiCanyonBlocks.CC_CACTUS,
				CactiCanyonBlocks.CC_SMALL_CACTUS,
				CactiCanyonBlocks.CC_FERN,
				CactiCanyonBlocks.CC_FLOWERS,
				CactiCanyonBlocks.CC_YUCCA,
				CactiCanyonBlocks.CC_TALL_CACTUS,
				CactiCanyonBlocks.CC_GRASS_BLOCK,
				CactiCanyonBlocks.CC_GRASSY_DIRT,
				CactiCanyonBlocks.CC_DENSE_GRASSY_DIRT,
				CactiCanyonBlocks.CC_DESERT_GRASS,
				RedstoneMinesBlocks.RM_SHRUB,
				SoggySwampBlocks.SS_SWAMP_SAPLING
		);
		BlockRenderLayerMap.INSTANCE.putBlocks(
				TRANSLUCENT_BLOCK_LAYER,
				GenericBlocks.GLASS,
				GenericBlocks.BLUE_GLASS,
				GenericBlocks.BROWN_GLASS,
				GenericBlocks.BLACK_GLASS,
				GenericBlocks.GREEN_GLASS,
				GenericBlocks.YELLOW_GLASS,
				GenericBlocks.RED_GLASS,
				GenericBlocks.LIME_GLASS,
				GenericBlocks.LIGHT_GRAY_GLASS,
				GenericBlocks.PINK_GLASS,
				GenericBlocks.MAGENTA_GLASS,
				GenericBlocks.LIGHT_BLUE_GLASS,
				GenericBlocks.ORANGE_GLASS,
				GenericBlocks.GRAY_GLASS,
				GenericBlocks.PURPLE_GLASS,
				GenericBlocks.CYAN_GLASS,
				CreeperWoodsBlocks.CW_GLOW_MUSHROOM
		);
		// set up fluid rendering
		BlockRenderLayerMap.INSTANCE.putFluids(
				TRANSLUCENT_BLOCK_LAYER,
				Fluids.DUNGEONS_WATER_FLOWING,
				Fluids.DUNGEONS_WATER_STILL,
				Fluids.SOGGY_SWAMP_WATER_FLOWING,
				Fluids.SOGGY_SWAMP_WATER_STILL
		);

		// set up particles
		ParticleFactoryRegistry.getInstance().register(Particles.GREEN_FLAME, GreenFlameParticle.Factory::new);
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(new Identifier(JavaDungeons.MOD_ID, "particle/green_flame"));
		});

		setupFluidRendering(
				Fluids.DUNGEONS_WATER_STILL, // still fluid object
				Fluids.DUNGEONS_WATER_FLOWING, // flowing fluid object
				new Identifier(JavaDungeons.MOD_ID, "dungeons_water"), // texture identifier
				0xFFFFFF // tint color (white because water is colored in its file)
		);

		setupFluidRendering(
				Fluids.SOGGY_SWAMP_WATER_STILL, // still fluid objectne
				Fluids.SOGGY_SWAMP_WATER_FLOWING, // flowing fluid object
				new Identifier(JavaDungeons.MOD_ID, "soggy_swamp/soggy_swamp_water"), // texture identifier
				0xFFFFFF // tint color (white because water is colored in its file)
		);

		// register containers to screens
		ScreenProviderRegistry.INSTANCE.<DungeonsTransformerScreenHandler>registerFactory(DungeonsTransformer.ID, screenHandler -> new DungeonsTransformerScreen(
				screenHandler,
				MinecraftClient.getInstance().player.getInventory(),
				DungeonsTransformer.CONTAINER_NAME.setStyle(Style.EMPTY)
		));

		//TODO
		/*ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.PHANTOM_ARMOR, Armors.PHANTOM_ARMOR_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/phantom_armor.png", Armors.PHANTOM_ARMOR, Armors.PHANTOM_ARMOR_HELMET);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new EvocationRobeModel<>(1.0F, slot, entity), Armors.EVOCATION_ROBE, Armors.EVOCATION_ROBE_HAT);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/evocation_robe.png", Armors.EVOCATION_ROBE, Armors.EVOCATION_ROBE_HAT);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new EvocationRobeModel<>(1.0F, slot, entity), Armors.EMBER_ROBE, Armors.EMBER_ROBE_HAT);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/ember_robe.png", Armors.EMBER_ROBE, Armors.EMBER_ROBE_HAT);*/

		/*ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.SNOW_ARMOR, Armors.SNOW_ARMOR_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/snow_armor.png", Armors.SNOW_ARMOR, Armors.SNOW_ARMOR_HELMET);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.SNOW_ARMOR, Armors.SNOW_ARMOR_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/frost_armor.png", Armors.FROST_ARMOR, Armors.FROST_ARMOR_HELMET);
		ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) ->
				new PhantomArmorModel<>(1.0F, slot, entity, ((PhantomArmorItem)stack.getItem()).unique), Armors.FROST_BITE, Armors.FROST_BITE_HELMET);
		ArmorRenderingRegistry.registerTexture((entity, stack, slot, defaultTexture) ->
				JavaDungeons.MOD_ID + ":textures/models/armor/frost_bite.png", Armors.FROST_BITE, Armors.FROST_BITE_HELMET);*/
	}
}