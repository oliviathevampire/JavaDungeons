package juniebyte.javadungeons.items;

import juniebyte.javadungeons.JavaDungeons;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DungeonsSickle extends HoeItem {

	public DungeonsSickle(ToolMaterial material, int attackDamage, float attackSpeed, String id) {
		super(material, attackDamage, attackSpeed, new Item.Settings().group(JavaDungeons.WEAPONS));
		Registry.register(Registry.ITEM, new Identifier(JavaDungeons.MOD_ID, id), this);
	}

}