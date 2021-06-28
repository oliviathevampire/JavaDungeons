package juniebyte.javadungeons.recipe;

import com.google.gson.JsonObject;
import juniebyte.javadungeons.JavaDungeons;
import juniebyte.javadungeons.content.Recipes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class DungeonsTransformerRecipe implements Recipe<Inventory> {

	public static final Identifier ID = new Identifier(JavaDungeons.MOD_ID, "dungeons_transformer");
	public static final Type TYPE = new Type();

	private final Identifier id;
	private final Ingredient input;
	private final ItemStack output;

	private DungeonsTransformerRecipe(Identifier id, Ingredient input, ItemStack output) {
		this.id = id;
		this.input = input;
		this.output = output;
	}

	@Override
	public boolean matches(Inventory inv, World world) {
		return input.test(inv.getStack(0)) && inv.getStack(1).isEmpty();
	}

	@Override
	public ItemStack craft(Inventory inv) {
		return output.copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return output;
	}

	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Recipes.DUNGEONS_TRANSFORMER_SERIALIZER;
	}

	@Override
	public RecipeType<?> getType() {
		return TYPE;
	}

	@Override
	public DefaultedList<Ingredient> getIngredients() {
		return DefaultedList.copyOf(Ingredient.EMPTY, this.input);
	}

	public static class Type implements RecipeType<DungeonsTransformerRecipe> {
		@Override
		public String toString() {
			return ID.toString();
		}
	}

	public static class Serializer implements RecipeSerializer<DungeonsTransformerRecipe> {

		public static final Serializer INSTANCE = new Serializer();

		@Override
		public DungeonsTransformerRecipe read(Identifier id, JsonObject jsonObject) {
			Ingredient ingredient2;
			if (JsonHelper.hasArray(jsonObject, "ingredient")) {
				ingredient2 = Ingredient.fromJson(JsonHelper.getArray(jsonObject, "ingredient"));
			} else {
				ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "ingredient"));
			}

			String string2 = JsonHelper.getString(jsonObject, "result");
			int i = JsonHelper.getInt(jsonObject, "count");
			ItemStack itemStack = new ItemStack(Registry.ITEM.get(new Identifier(string2)), i);
			return new DungeonsTransformerRecipe(id, ingredient2, itemStack);
		}

		@Override
		public DungeonsTransformerRecipe read(Identifier id, PacketByteBuf packetByteBuf) {
			Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
			ItemStack itemStack = packetByteBuf.readItemStack();
			return new DungeonsTransformerRecipe(id, ingredient, itemStack);
		}

		@Override
		public void write(PacketByteBuf packetByteBuf, DungeonsTransformerRecipe recipe) {
			recipe.input.write(packetByteBuf);
			packetByteBuf.writeItemStack(recipe.output);
		}
	}
}