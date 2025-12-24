package com.bazalbuilder.hbm.block.entity.machine;

import com.bazalbuilder.hbm.block.entity.HbmBlockEntities;
import com.bazalbuilder.hbm.api.energy.EnergyProvider;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class RTGBlockEntity extends AbstractMachineBlockEntity implements SidedInventory, EnergyProvider, Nameable {
	public RTGBlockEntity(BlockPos pos, BlockState state) {
		super(HbmBlockEntities.RTG_ENTITY, pos, state, 15);
	}

	@Override
	public long getEnergy() {
		return 0;
	}

	@Override
	public long getMaximumEnergy() {
		return 0;
	}

	@Override
	public void setEnergy(long power) {

	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return new int[0];
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return false;
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return false;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack getStack(int slot) {
		return null;
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		return null;
	}

	@Override
	public ItemStack removeStack(int slot) {
		return null;
	}

	@Override
	public void setStack(int slot, ItemStack stack) {

	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public Text getName() {
		return null;
	}
}
