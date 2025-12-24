package com.bazalbuilder.hbm.api.energy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface EnergyProvider extends Powerable {
	default void provide(World world, BlockPos blockPos) {

	}
}
