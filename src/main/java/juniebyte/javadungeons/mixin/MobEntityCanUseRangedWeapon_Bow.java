package juniebyte.javadungeons.mixin;

import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.RangedWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ AbstractSkeletonEntity.class })
public class MobEntityCanUseRangedWeapon_Bow {
	@Inject(method = "canUseRangedWeapon", at = @At("HEAD"), cancellable = true)
	public void canUseRangedWeapon(RangedWeaponItem weapon, CallbackInfoReturnable<Boolean> cir) {
		if (weapon instanceof BowItem) {
			cir.setReturnValue(true);
		}
	}
}