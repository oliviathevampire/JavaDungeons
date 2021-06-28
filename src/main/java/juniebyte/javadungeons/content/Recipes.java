package juniebyte.javadungeons.content;

import juniebyte.javadungeons.JavaDungeons;
import juniebyte.javadungeons.recipe.DungeonsTransformerRecipe;
import juniebyte.javadungeons.recipe.DungeonsTransformerRecipe.Serializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Recipes {

	public static Serializer DUNGEONS_TRANSFORMER_SERIALIZER;

	public static void init() {
		DUNGEONS_TRANSFORMER_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, DungeonsTransformerRecipe.ID, DungeonsTransformerRecipe.Serializer.INSTANCE);
		Registry.register(Registry.RECIPE_TYPE, new Identifier(JavaDungeons.MOD_ID, "dungeons_transformer"), DungeonsTransformerRecipe.TYPE);
	}
}