package juniebyte.javadungeons.content;

import juniebyte.javadungeons.JavaDungeons;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds {

	public static final Identifier CW_GLOW_MUSHROOM_STEP_ID = new Identifier(JavaDungeons.MOD_ID, "cw_glow_mushroom_step");
	public static SoundEvent CW_GLOW_MUSHROOM_STEP;

	public static void init() {
		CW_GLOW_MUSHROOM_STEP = new SoundEvent(CW_GLOW_MUSHROOM_STEP_ID);
		Registry.register(Registry.SOUND_EVENT, CW_GLOW_MUSHROOM_STEP_ID, CW_GLOW_MUSHROOM_STEP);
	}
}