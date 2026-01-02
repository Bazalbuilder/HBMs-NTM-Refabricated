package com.bazalbuilder.hbm.block.entity.machine;

import com.bazalbuilder.hbm.block.entity.AbstractMachineBlockEntity;
import com.bazalbuilder.hbm.block.entity.HbmBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;

public class ShredderBlockEntity extends AbstractMachineBlockEntity implements Nameable {
	public ShredderBlockEntity(BlockPos pos, BlockState state) {
		super(HbmBlockEntityTypes.SHREDDER, pos, state);
	}

	@Override
	public Text getName() {
		return null;
	}
}
