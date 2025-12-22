package com.bazalbuilder.hbm.block.machine;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class AbstractMachineBlock extends BlockWithEntity {
//	public static final DirectionProperty FACING;
//	public static final BooleanProperty ACTIVE;

	protected AbstractMachineBlock(Settings settings) {
		super(settings);
//		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(ACTIVE, false));
	}

//	@Override
//	public BlockState getPlacementState(ItemPlacementContext context) {
//		return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
//	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (world.isClient) {
			return ActionResult.SUCCESS;
		} else {
			this.openScreen(world, pos, player);
			return ActionResult.CONSUME;
		}
	}

	protected abstract void openScreen(World world, BlockPos pos, PlayerEntity player);

//	static {
//		FACING = HorizontalFacingBlock.FACING;
//		ACTIVE = BooleanProperty.of("active");
//	}
}
