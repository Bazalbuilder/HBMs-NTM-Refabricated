package com.bazalbuilder.hbm.world.hazard;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ChunkLevelType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public abstract class RadiationHandler {
	public abstract float getRadiation(World world, BlockPos pos);
	public abstract void setRadiation(World world, BlockPos pos, float radiation);
	public abstract void update(World world);

	public void incrementRadiation(World world, BlockPos pos, float radiation) {
		setRadiation(world, pos, getRadiation(world, pos) + radiation);
	}

	public void decrementRadiation(World world, BlockPos pos, float radiation) {
		setRadiation(world, pos, Math.max(getRadiation(world, pos) - radiation, 0.0f));
	}

	// Proxied events
	public abstract void onWorldLoad(MinecraftServer server, World world);
	public abstract void onWorldUnload(MinecraftServer server, World world);

	public abstract void onChunkLoad(World world, Chunk chunk);
	public abstract void onLevelTypeChange(World world, Chunk chunk, ChunkLevelType oldLevelType, ChunkLevelType newLevelType);
	public abstract void onChunkUnload(World world, Chunk chunk);

	public abstract void handleRadiationEffects();
}
