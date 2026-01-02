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

package com.bazalbuilder.hbm.util;

import com.bazalbuilder.hbm.block.AbstractMultiblock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;

public class MultiblockUtils {
	public static boolean isRequirementsMet(World world, BlockPos pos, BlockPos offsetPos, Direction direction, int[] dimensions) {
		dimensions = rotate(dimensions, Direction.SOUTH);

		int count = 0;

		for (int a = pos.getX() - dimensions[4]; a <= pos.getX() + dimensions[5]; a++) {
			for (int b = pos.getY() - dimensions[1]; b <= pos.getY() + dimensions[0]; b++) {
				for (int c = pos.getZ() - dimensions[2]; c <= pos.getZ() + dimensions[3]; c++) {
					if (new BlockPos(a, b, c).equals(offsetPos))
						continue;

					if (!world.getBlockState(pos.add(a, b, c)).isReplaceable())
						return false;

					count++;

					if (count > 2000) {
						LOGGER.error("Stack overflow on method isRequirementsMet! {} {} {} {} {} {}", a, b, c, pos.getX(), pos.getY(), pos.getZ());
						return false;
					}
				}
			}
		}

		return true;
	}

	public static void assembleSpace(World world, BlockPos pos, BlockState state, Direction direction, int[] offsets) {
		offsets = rotate(offsets, Direction.SOUTH);

		int count = 0;

		for (int a = pos.getX() - offsets[4]; a <= pos.getX() + offsets[5]; a++) {
			for (int b = pos.getY() - offsets[1]; b <= pos.getY() + offsets[0]; b++) {
				for (int c = pos.getZ() - offsets[2]; c <= pos.getZ() + offsets[3]; c++) {
					Direction placeDirection = null;

					if (b < pos.getY())
						placeDirection = Direction.DOWN;
					else if (b > pos.getY())
						placeDirection = Direction.UP;
					else if (a < pos.getX())
						placeDirection = Direction.WEST;
					else if (a > pos.getX())
						placeDirection = Direction.EAST;
					else if (c < pos.getZ())
						placeDirection = Direction.NORTH;
					else if (c > pos.getZ())
						placeDirection = Direction.SOUTH;
					else
						continue;

					world.setBlockState(pos.add(a, b, c), state.with(AbstractMultiblock.FACING, placeDirection), 3);

					count++;

					if (count > 2000) {
						LOGGER.error("Stack overflow on method assembleSpace! {} {} {} {} {} {}", a, b, c, pos.getX(), pos.getY(), pos.getZ());
						return;
					}
				}
			}
		}
	}

	public static int[] rotate(int[] dimensions, Direction facing) {
		if (dimensions == null) {
			return null;
		}

		return switch (facing) {
			case NORTH -> new int[] { dimensions[0], dimensions[1], dimensions[3], dimensions[2], dimensions[5], dimensions[4]};
			case EAST -> new int[] { dimensions[0], dimensions[1], dimensions[5], dimensions[4], dimensions[2], dimensions[3]};
			case WEST -> new int[] { dimensions[0], dimensions[1], dimensions[4], dimensions[5], dimensions[3], dimensions[2]};
			default -> dimensions;
		};
	}
}
