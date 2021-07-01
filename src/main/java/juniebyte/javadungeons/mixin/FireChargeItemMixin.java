package juniebyte.javadungeons.mixin;

import juniebyte.javadungeons.blocks.DungeonsCandle;
import net.minecraft.block.*;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FireChargeItem.class)
public abstract class FireChargeItemMixin {

	/**
	 * @author Olivia
	 * @reason To allow us to light our custom candles
	 */
	@Overwrite
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState blockState = world.getBlockState(blockPos);
		boolean bl = false;
		if (! CampfireBlock.canBeLit(blockState) && ! CandleBlock.canBeLit(blockState) &&
				! CandleCakeBlock.canBeLit(blockState) && !DungeonsCandle.canBeLit(blockState)) {
			blockPos = blockPos.offset(context.getSide());
			if (AbstractFireBlock.canPlaceAt(world, blockPos, context.getPlayerFacing())) {
				this.playUseSound(world, blockPos);
				world.setBlockState(blockPos, AbstractFireBlock.getState(world, blockPos));
				world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, blockPos);
				bl = true;
			}
		} else {
			this.playUseSound(world, blockPos);
			world.setBlockState(blockPos, blockState.with(Properties.LIT, true));
			world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, blockPos);
			bl = true;
		}

		if (bl) {
			context.getStack().decrement(1);
			return ActionResult.success(world.isClient);
		} else {
			return ActionResult.FAIL;
		}
	}

	@Shadow
	protected abstract void playUseSound(World world, BlockPos pos);
}
