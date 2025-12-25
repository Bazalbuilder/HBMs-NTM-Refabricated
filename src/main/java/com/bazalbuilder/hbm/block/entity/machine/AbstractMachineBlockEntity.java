package com.bazalbuilder.hbm.block.entity.machine;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractMachineBlockEntity extends BlockEntity {
	protected DefaultedList<ItemStack> inventory;

	public AbstractMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int inventorySize) {
		super(type, pos, state);
		this.inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);
	}
}
