package juniebyte.javadungeons.content;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.impl.structure.FabricStructureImpl;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureFeatures {

	public static void init() {}

	/**
	 * This is where we set the rarity of your structures and determine if land conforms to it.
	 * See the comments in below for more details.
	 */
	public static void setupStructures() {
		/*setupMapSpacingAndLand(
				CW_RUINED_TOWER, *//* The instance of the structure *//*
				new StructureConfig(10 *//* average distance apart in chunks between spawn attempts *//*,
						5 *//* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*//*,
						637198423 *//* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. *//*),
				true);*/
		// Add more structures here and so on
	}

	/**
	 * Adds the provided structure to the registry, and adds the separation settings.
	 * The rarity of the structure is determined based on the values passed into
	 * this method in the structureSeparationSettings argument.
	 * This method is called by setupStructures above.
	 */
	public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(
			F structure,
			StructureConfig structureSeparationSettings,
			boolean transformSurroundingLand) {
		/*
		 * We need to add our structures into the map in Structure class
		 * alongside vanilla structures or else it will cause errors.
		 *
		 * If the registration is setup properly for the structure,
		 * getRegistryName() should never return null.
		 */
		StructureFeature.STRUCTURES.put(Registry.STRUCTURE_FEATURE.getKey(structure).get().getValue().toString(), structure);

		/*
		 * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
		 * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
		 * Doesn't work well on structure that have pieces stacked vertically or change in heights.
		 *
		 * Note: The air space this method will create will be filled with water if the structure is below sealevel.
		 * This means this is best for structure above sealevel so keep that in mind.
		 *
		 * NOISE_AFFECTING_FEATURES requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
		 */
		if (transformSurroundingLand) {
			StructureFeature.JIGSAW_STRUCTURES =
					ImmutableList.<StructureFeature<?>>builder()
							.addAll(StructureFeature.JIGSAW_STRUCTURES)
							.add(structure)
							.build();
		}

		/*
		 * This is the map that holds the default spacing of all structures.
		 * Always add your structure to here so that other mods can utilize it if needed.
		 *
		 * However, while it does propagate the spacing to some correct dimensions from this map,
		 * it seems it doesn't always work for code made dimensions as they read from this list beforehand.
		 *
		 * Instead, we will use the WorldEvent.Load event in StructureTutorialMain to add the structure
		 * spacing from this list into that dimension or to do dimension blacklisting properly.
		 * We also use our entry in DimensionStructuresSettings.DEFAULTS in WorldEvent.Load as well.
		 *
		 * DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
		 */
		StructuresConfig.DEFAULT_STRUCTURES =
				ImmutableMap.<StructureFeature<?>, StructureConfig>builder()
						.putAll(StructuresConfig.DEFAULT_STRUCTURES)
						.put(structure, structureSeparationSettings)
						.build();


		/*
		 * There are very few mods that relies on seeing your structure in the noise settings registry before the world is made.
		 *
		 * You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead of the NOISE_GENERATOR_SETTINGS loop below but
		 * that field only applies for the default overworld and won't add to other worldtypes or dimensions (like amplified or Nether).
		 * So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop below instead if you must.
		 */
		BuiltinRegistries.CHUNK_GENERATOR_SETTINGS.stream().forEach(settings -> FabricStructureImpl.STRUCTURE_TO_CONFIG_MAP.put(structure, structureSeparationSettings));
	}
}
