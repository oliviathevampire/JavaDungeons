package juniebyte.javadungeons.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class DungeonsHangingPlant extends Block {

	// hanging plant block
	protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, - 16.0D, 4.0D, 12.0D, 16.0D, 12.0D);

	public DungeonsHangingPlant(Material material, float hardness, float resistance, BlockSoundGroup sounds) {
		super(FabricBlockSettings.of(material).strength(hardness, resistance).sounds(sounds).nonOpaque().collidable(false));
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return facing == Direction.UP &&
				! this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return sideCoversSmallSquare(world, pos.up(), Direction.DOWN);
	}

}