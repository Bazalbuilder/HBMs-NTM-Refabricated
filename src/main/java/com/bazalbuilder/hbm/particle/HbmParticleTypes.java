/*
 * Copyright (c) 2025 Bazalbuilder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.bazalbuilder.hbm.particle;

import com.bazalbuilder.hbm.util.RegistryUtils;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmParticleTypes {
	public static final Identifier BASE_PARTICLE_TEXTURE;

	public static final SimpleParticleType BASE_PARTICLE;

	public static void initialize() {
		LOGGER.info("Registering particle types for mod \"{}\"", MOD_ID);
	}

	private static void registerAll() {
		RegistryUtils.registerParticleType(BASE_PARTICLE_TEXTURE, BASE_PARTICLE);
	}

	static {
		BASE_PARTICLE_TEXTURE = Identifier.of(MOD_ID, "particle_base");

		BASE_PARTICLE = FabricParticleTypes.simple();

		registerAll();
	}
}
