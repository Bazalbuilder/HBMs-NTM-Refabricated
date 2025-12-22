package com.bazalbuilder.hbm.block.entity.machine;

import com.bazalbuilder.hbm.block.HbmBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ShredderBlockEntity extends BlockEntity {
	public ShredderBlockEntity(BlockPos pos, BlockState state) {
		super(HbmBlockEntities.SHREDDER_BLOCK_ENTITY, pos, state);
	}
}
