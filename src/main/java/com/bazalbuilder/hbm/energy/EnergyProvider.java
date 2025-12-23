package com.bazalbuilder.hbm.energy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface EnergyProvider extends Powerable {
	default void provide(World world, BlockPos blockPos) {

	}
}
