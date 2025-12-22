package com.bazalbuilder.hbm.block.machine;

import com.bazalbuilder.hbm.block.entity.machine.ShredderBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ShredderBlock extends AbstractMachineBlock {
	public static final MapCodec<ShredderBlock> CODEC = createCodec(ShredderBlock::new);

	public ShredderBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ShredderBlockEntity(pos, state);
	}

	@Override
	protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
		BlockEntity entity = world.getBlockEntity(pos);
		if (entity instanceof ShredderBlockEntity) {
			player.openHandledScreen((NamedScreenHandlerFactory) entity);
		}
	}
}
