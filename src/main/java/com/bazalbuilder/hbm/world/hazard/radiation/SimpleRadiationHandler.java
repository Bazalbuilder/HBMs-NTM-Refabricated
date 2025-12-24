package com.bazalbuilder.hbm.world.hazard.radiation;

import com.bazalbuilder.hbm.block.HbmBlocks;
import com.bazalbuilder.hbm.world.hazard.RadiationHandler;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ChunkLevelType;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.HashMap;
import java.util.Map;

/**
 * I can't be assed with making a new radiation handler from scratch, so I ported it from the main repo
 */
public class SimpleRadiationHandler extends RadiationHandler {
	private static final String NBT_RADIATION_KEY = "hbm_simple_radiation";
	private static final float MAXIMUM_RADIATION = 100_000.0f;

	private static Map<World, Map<ChunkPos, Float>> radiationPerWorld = new HashMap<>();

	@Override
	public float getRadiation(World world, BlockPos pos) {
		Map<ChunkPos, Float> worldRadiation = radiationPerWorld.get(world);

		if (worldRadiation != null) {
			ChunkPos chunkPos = new ChunkPos(pos);
			Float radiation = worldRadiation.get(chunkPos);
			return radiation != null ? MathHelper.clamp(radiation, 0.0f, MAXIMUM_RADIATION) : 0.0f;
		}

		return 0;
	}

	@Override
	public void setRadiation(World world, BlockPos pos, float radiation) {
		Map<ChunkPos, Float> worldRadiation = radiationPerWorld.get(world);

		if (radiationPerWorld != null && world.getBlockEntity(pos) != null) {
			ChunkPos chunkPos = new ChunkPos(pos);
			worldRadiation.put(chunkPos, MathHelper.clamp(radiation, 0.0f, MAXIMUM_RADIATION));
		}
	}

	@Override
	public void update(World world) {
		Map<ChunkPos, Float> worldRadiation = radiationPerWorld.get(world);
		Map<ChunkPos, Float> buffer = new HashMap<>(worldRadiation);
		worldRadiation.clear();

		for (Map.Entry<ChunkPos, Float> chunk : buffer.entrySet()) {
			if (chunk.getValue() == 0)
				continue;

			ChunkPos chunkPos = chunk.getKey();

			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					int type = Math.abs(i) + Math.abs(j);
					float percentage = type == 0 ? 0.6f : type == 1 ? 0.075f : 0.025f;
					ChunkPos newChunk = new ChunkPos(chunkPos.x + i, chunkPos.z + j);

					if (buffer.containsKey(newChunk)) {
						Float value = worldRadiation.get(newChunk);
						float radiation = value != null ? value : 0;
						float newRadiation = radiation + chunk.getValue() * percentage;
						newRadiation = MathHelper.clamp(0.0f, newRadiation * 0.99f - 0.05f, MAXIMUM_RADIATION);
						worldRadiation.put(newChunk, newRadiation);
					} else {
						worldRadiation.put(newChunk, chunk.getValue() * percentage);
					}

					// TODO: Radioactive Fog
				}
			}
		}
	}

	@Override
	public void onWorldLoad(MinecraftServer server, World world) {
		if (!world.isClient)
			radiationPerWorld.put(world, new HashMap<>());
	}

	@Override
	public void onWorldUnload(MinecraftServer server, World world) {
		if (!world.isClient)
			radiationPerWorld.remove(world);
	}

	@Override
	public void onChunkLoad(World world, Chunk chunk) {
		if (!world.isClient) {
			Map<ChunkPos, Float> worldRadiation = radiationPerWorld.get(world);

			if (worldRadiation != null) {
				worldRadiation.put(chunk.getPos(), chunk.getUpgradeData().toNbt().getFloat(NBT_RADIATION_KEY));
			}
		}
	}

	@Override
	public void onLevelTypeChange(World world, Chunk chunk, ChunkLevelType oldLevelType, ChunkLevelType newLevelType) {
		if (!world.isClient) {
			Map<ChunkPos, Float> worldRadiation = radiationPerWorld.get(world);

			if (worldRadiation != null) {
				Float value = worldRadiation.get(chunk.getPos());
				float radiation = value != null ? value : 0.0f;
				chunk.getUpgradeData().toNbt().putFloat(NBT_RADIATION_KEY, radiation);
			}
		}
	}

	@Override
	public void onChunkUnload(World world, Chunk chunk) {
		if (!world.isClient) {
			Map<ChunkPos, Float> worldRadiation = radiationPerWorld.get(world);

			if (worldRadiation != null) {
				worldRadiation.remove(chunk.getPos());
			}
		}
	}

	@Override
	public void handleRadiationEffects() {
		int count = 10;
		int threshold = 10;
		int chunks = 5;

		for(Map.Entry<World, Map<ChunkPos, Float>> perWorld : radiationPerWorld.entrySet()) {
			World world = perWorld.getKey();
			Map<ChunkPos, Float> worldRadiation = perWorld.getValue();

			Object[] entries = worldRadiation.entrySet().toArray();

			if (entries.length == 0)
				continue;

			for (int c = 0; c < chunks; c++) {
				Map.Entry<ChunkPos, Float> randomEntry = (Map.Entry<ChunkPos, Float>) entries[world.random.nextInt(entries.length)];

				ServerWorld serverWorld = (ServerWorld) world;
				ChunkPos chunkPos = randomEntry.getKey();
				ServerChunkManager manager = serverWorld.getChunkManager();

				for (int i = 0; i < count; i++) {
					if (randomEntry == null || randomEntry.getValue() < threshold)
						continue;

					if (manager.isChunkLoaded(chunkPos.x, chunkPos.z)) {
						for (int a = 0; a < 16; a++) {
							for (int b = 0; b < 16; b++) {
								if (world.random.nextInt(3) != 0)
									continue;

								int x = chunkPos.getCenterX() - 8 + a;
								int z = chunkPos.getCenterZ() - 8 + b;
								int y = world.getTopY(Heightmap.Type.WORLD_SURFACE, x, z);

								BlockPos blockPos = new BlockPos(x, y, z);

								if (serverWorld.getBlockState(blockPos).getBlock() == Blocks.GRASS_BLOCK) {
									world.setBlockState(blockPos, HbmBlocks.GRAPHITIC_SCHIST.getDefaultState());
								}
							}
						}
					}
				}
			}
		}
	}
}
