package juniebyte.javadungeons.blocks;

import juniebyte.javadungeons.content.Particles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class DungeonsCandle extends Block {

    public DyeColor dyeColor;
    public String type;
    public static final BooleanProperty LIT = Properties.LIT;

    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.5D, 0.0D, 5.5D, 10.5D, 15.0D, 10.5D);

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext entityCtx) {
		return SHAPE;
	}

    public DungeonsCandle(float hardness, float resistance, BlockSoundGroup sounds, DyeColor dyeColor, String type) {
        super(FabricBlockSettings.of(new FabricMaterialBuilder(dyeColor).destroyedByPiston().build()).strength(hardness, resistance).strength(hardness, resistance).sounds(sounds).nonOpaque().luminance(state -> state.get(LIT) ? 15 : 0));
        this.dyeColor = dyeColor;
        this.type = type;
        this.setDefaultState(this.getDefaultState().with(LIT, false));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return facing == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
    }

    public static void extinguish(@Nullable PlayerEntity player, BlockState state, WorldAccess world, BlockPos pos) {
        setLit(world, state, pos, false);
        if (state.getBlock() instanceof AbstractCandleBlock) {
            double posX = (double)pos.getX() + 0.5D;
            double posY = (double)pos.getY() + 1.0D;
            double posZ = (double)pos.getZ() + 0.5D;
            world.addParticle(ParticleTypes.SMOKE, posX + 0.5D, posY + 0.5D, posZ + 0.5D, 0.0D, 0.10000000149011612D, 0.0D);
        }

        world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getAbilities().allowModifyWorld && player.getStackInHand(hand).isEmpty() && state.get(LIT)) {
            extinguish(player, state, world, pos);
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    private static void setLit(WorldAccess world, BlockState state, BlockPos pos, boolean lit) {
        world.setBlockState(pos, state.with(LIT, lit), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
    }

    public static boolean canBeLit(BlockState state) {
        return state.get(LIT);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(state.get(LIT)) {
            double posX = (double)pos.getX() + 0.5D;
            double posY = (double)pos.getY() + 1.0D;
            double posZ = (double)pos.getZ() + 0.5D;
            float f = random.nextFloat();
            if (f < 0.3F) {
                world.addParticle(ParticleTypes.SMOKE, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
                if (f < 0.17F) {
                    world.playSound(posX + 0.5D, posY + 0.5D, posZ + 0.5D, SoundEvents.BLOCK_CANDLE_AMBIENT, SoundCategory.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
                }
            }
            switch (type) {
                case "normal" -> world.addParticle(ParticleTypes.FLAME, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
                case "wildfire" -> world.addParticle(Particles.GREEN_FLAME, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
                case "soul" -> world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}