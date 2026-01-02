/*
 * Copyright (c) 2025 Bazalbuilder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.bazalbuilder.hbm.block;

import com.bazalbuilder.hbm.util.MultiblockUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Mojmap: Turtle.isGoingHome();
// Turtle: Well I'm going home, to the place where I belong, where your love has always been enough for me
public abstract class AbstractMultiblock extends BlockWithEntity {
	public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.values());
	public static final EnumProperty<MultiblockType> TYPE = EnumProperty.of("type", MultiblockType.class);

	public static boolean safeRem = false;
	public List<BlockPos> positions = new ArrayList<>();
	public List<Box> boxes = new ArrayList<>();

	public AbstractMultiblock(Settings settings) {
		super(settings.ticksRandomly());
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(TYPE, MultiblockType.CENTER));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, TYPE);
	}

	public BlockState getState(Direction direction) {
		return this.getDefaultState().with(FACING, direction);
	}

	public BlockState getState(MultiblockType type) {
		return this.getDefaultState().with(TYPE, type);
	}

	public static boolean isCenter(BlockState state) {
		return state.get(TYPE).equals(MultiblockType.CENTER);
	}

	public static Direction getDirection(BlockState state) {
		return state.get(FACING);
	}

	@Override
	protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

		if (!safeRem)
			removeIfOrphan(world, pos, state);
	}

	private void removeIfOrphan(World world, BlockPos pos, BlockState state) {
		if (!world.isClient) {
			MultiblockType type = state.get(TYPE);

			if (type != MultiblockType.CENTER) {
				Direction direction = state.get(FACING).getOpposite();
				BlockPos neighborPos = pos.offset(direction);
				Block neighborBlock = world.getBlockState(neighborPos).getBlock();

				if (!(neighborBlock instanceof AbstractMultiblock) && world.isChunkLoaded((int) pos.getSquaredDistance(-1, -1, -1), (int) pos.getSquaredDistance(1, 1, 1))) {
					world.removeBlock(pos, false);
				}
			}
		}
	}

	public @Nullable BlockPos findCenter(World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);

		if (!(state.getBlock() instanceof AbstractMultiblock))
			return null;

		if (isCenter(state))
			return pos;

		if (positions.contains(pos))
			return null;

		positions.add(pos);

		Direction direction = getDirection(state).getOpposite();
		BlockPos relativePos = pos.offset(direction);

		if (!(world.getBlockState(relativePos).getBlock() instanceof AbstractMultiblock))
			return null;

		return findCenter(world, relativePos);
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		if (!(placer instanceof PlayerEntity player))
			return;

		safeRem = true;
		world.removeBlock(pos, false);
		safeRem = false;

		int rotation = MathHelper.floor((placer.getY() * 4.0f / 360.0f) + 0.5d) & 3;
		int offset = -getOffset();
		BlockPos adjusted = pos.up(getHeightOffset());

		Direction direction = switch (rotation) {
			case 1 -> Direction.EAST;
			case 2 -> Direction.SOUTH;
			case 3 -> Direction.WEST;
			default -> Direction.NORTH;
		};

		direction = getModifiedDirection(direction);

		if (MultiblockUtils.isRequirementsMet(world, adjusted, adjusted.offset(direction, offset), direction, getDimensions())) {
			if (!player.isCreative() && player.getInventory().insertStack(new ItemStack(this))) {
				player.dropItem(new ItemStack(this), false);
			}
			return;
		}

		if (!world.isClient) {
			BlockPos centerPos = adjusted.offset(direction, offset);
			BlockState centerState = getState(direction);

			world.setBlockState(centerPos, centerState, 3);

			//IPersistentNBT.restoreData(level, corePos, stack);

			MultiblockUtils.assembleSpace(world, adjusted.offset(direction, offset), state, direction, getDimensions());
		}

		world.scheduleBlockTick(pos, this, 1);
		world.scheduleBlockTick(pos, this, 2);

		super.onPlaced(world, pos, state, placer, itemStack);
	}

	protected Direction getModifiedDirection(Direction direction) {
		return direction;
	}

	@Override
	protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (!state.getBlock().equals(newState.getBlock())) {
			MultiblockType type = state.get(TYPE);

			if (!safeRem && type != MultiblockType.CENTER) {
				Direction direction = state.get(FACING);
				BlockPos neighborPos = pos.offset(direction.getOpposite());

				if (world.getBlockState(neighborPos).getBlock() instanceof AbstractMultiblock) {
					world.removeBlock(neighborPos, false);
				}
			}

			BlockEntity entity = world.getBlockEntity(pos);
			if (entity instanceof Inventory inventory) {

				ItemScatterer.spawn(world, pos, inventory);
				world.updateNeighbors(pos, this);
			}
		}

		super.onStateReplaced(state, world, pos, newState, moved);
	}

	@Override
	public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
		player.increaseStat(Stats.MINED.getOrCreateStat(this), 1);
		player.addExhaustion(0.005f);
	}

	@Override
	public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!player.isCreative() && isCenter(state)) {
			dropStacks(state, world, pos, world.getBlockEntity(pos), player, player.getMainHandStack());
		}
		return super.onBreak(world, pos, state, player);
	}

	public boolean usesDetailedHitbox() {
		return !boxes.isEmpty();
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		if (!this.usesDetailedHitbox() || !(view instanceof World world))
			return VoxelShapes.fullCube();

		BlockPos centerPos = findCenter(world, pos);
		if (centerPos == null)
			return VoxelShapes.fullCube();

		BlockState centerState = view.getBlockState(centerPos);
		Direction facing = centerState.get(FACING);
		Direction rotation = facing.rotateYClockwise();

		VoxelShape combinedShape = VoxelShapes.empty();
		Vec3d offset = Vec3d.ofBottomCenter(centerPos.subtract(pos));

		for (Box box : boxes) {
			Box rotatedBox = getBoxRotation(box, offset.x + 0.5, offset.y, offset.z + 0.5, rotation);
			combinedShape = VoxelShapes.union(combinedShape, VoxelShapes.cuboid(rotatedBox));
		}

		return combinedShape;
	}

	public static Box getBoxRotation(Box box, double x, double y, double z, Direction direction) {
		Box newBox = switch (direction) {
			case NORTH -> new Box(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
			case SOUTH -> new Box(-box.minX, box.minY, -box.minZ, -box.maxX, box.maxY, -box.maxZ);
			case EAST -> new Box(-box.maxZ, box.minY, box.minX, -box.minZ, box.maxY, box.maxX);
			case WEST -> new Box(box.minZ, box.minY, -box.maxX, box.maxZ, box.maxY, -box.minX);
			default -> box;
		};
		return newBox.expand(x, y, z);
	}

	public abstract int[] getDimensions();

	public abstract int getOffset();

	public int getHeightOffset() {
		return 0;
	}

	public enum MultiblockType implements StringIdentifiable {
		INSTANCE,
		ADDON,
		CENTER;

		private final String name;

		MultiblockType() {
			this.name = this.toString().toLowerCase(Locale.ROOT);
		}


		@Override
		public String asString() {
			return name;
		}
	}
}
