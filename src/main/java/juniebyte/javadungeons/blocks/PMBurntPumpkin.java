package juniebyte.javadungeons.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BlockItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class PMBurntPumpkin extends Block {

	// generic block

	public BlockItem blockItem;

	public PMBurntPumpkin() {
		super(FabricBlockSettings.of(Material.GOURD, MapColor.BROWN).strength(1.0F).sounds(BlockSoundGroup.WOOD));
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(10) == 0) {
			world.addParticle(ParticleTypes.SMALL_FLAME,
					(double) pos.getX() + random.nextDouble(),
					(double) pos.getY() + 1.1D, (double) pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.SMOKE,
					(double) pos.getX() + random.nextDouble(),
					(double) pos.getY() + 1.1D, (double) pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (! entity.isFireImmune() && entity instanceof LivingEntity &&
				! EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
			entity.damage(DamageSource.HOT_FLOOR, 1.0F);
		}

		super.onSteppedOn(world, pos, state, entity);
	}

	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		world.getBlockTickScheduler().schedule(pos, this, 20);
	}

}