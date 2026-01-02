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

package com.bazalbuilder.hbm.block.bomb;

import com.bazalbuilder.hbm.block.entity.bomb.FatManNukeBlockEntity;
import com.bazalbuilder.hbm.config.TemporaryConstants;
import com.bazalbuilder.hbm.entity.effect.TorexNukeEntity;
import com.bazalbuilder.hbm.item.RemoteControllable;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FatManNukeBlock extends BlockWithEntity implements RemoteControllable {
	public static final MapCodec<FatManNukeBlock> CODEC = createCodec(FatManNukeBlock::new);
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

	public FatManNukeBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new FatManNukeBlockEntity(pos, state);
	}

	@Override
	public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
	}

	@Override
	protected BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	public void igniteBomb(World world, BlockPos pos) {
		if (!world.isClient) {
			TorexNukeEntity.statFacStandard(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ(), TemporaryConstants.FAT_MAN_MAX_RADIUS);
		}
	}

	@Override
	public RemoteReturnCode trigger(World world, BlockPos pos) {
		if (!world.isClient) {
			FatManNukeBlockEntity entity = (FatManNukeBlockEntity) world.getBlockEntity(pos);
			if (entity != null) {
				if (entity.ready()) {
					entity.clear();
					world.removeBlock(pos, false);
					igniteBomb(world, pos);
					return RemoteReturnCode.DETONATED;
				}
				return RemoteReturnCode.ERROR_MISSING_COMPONENT;
			}
		}

		return RemoteReturnCode.UNDEFINED;
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return CODEC;
	}
}
