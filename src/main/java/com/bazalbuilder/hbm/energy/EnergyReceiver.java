package com.bazalbuilder.hbm.energy;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface EnergyReceiver extends Powerable {
	default void subscribe(World world, BlockPos pos) {

	}
}
