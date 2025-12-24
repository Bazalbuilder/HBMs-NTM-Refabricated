package com.bazalbuilder.hbm.event;

import com.bazalbuilder.hbm.config.Configuration;
import com.bazalbuilder.hbm.world.hazard.RadiationHandler;
import com.bazalbuilder.hbm.world.hazard.radiation.SimpleRadiationHandler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

public class HazardEvents {
	public static RadiationHandler radiationProxy = new SimpleRadiationHandler();

	public static void initialize() {
		if (Configuration.DEBUG_ENABLE_CHUNK_RADIATION) {
			// Radiation
			ServerTickEvents.END_WORLD_TICK.register(world -> radiationProxy.update(world));
			ServerWorldEvents.LOAD.register((server, world) -> radiationProxy.onWorldLoad(server, world));
			ServerWorldEvents.UNLOAD.register((server, world) -> radiationProxy.onWorldUnload(server, world));
			ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> radiationProxy.onChunkLoad(world, chunk));
			ServerChunkEvents.CHUNK_LEVEL_TYPE_CHANGE.register((world, chunk, oldLevelType, newLevelType) -> radiationProxy.onLevelTypeChange(world, chunk, oldLevelType, newLevelType));
			ServerChunkEvents.CHUNK_UNLOAD.register((world, chunk) -> radiationProxy.onChunkUnload(world, chunk));
		}
	}
}
