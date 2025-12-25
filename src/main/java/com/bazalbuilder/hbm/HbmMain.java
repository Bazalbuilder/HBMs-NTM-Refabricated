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

package com.bazalbuilder.hbm;

import com.bazalbuilder.hbm.block.entity.HbmBlockEntities;
import com.bazalbuilder.hbm.block.HbmBlocks;
import com.bazalbuilder.hbm.item.HbmItemGroups;
import com.bazalbuilder.hbm.item.HbmItems;
import com.bazalbuilder.hbm.sound.HbmSounds;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbmMain implements ModInitializer {
	public static final String MOD_ID = "hbm";
	public static final String MOD_NAME = "Hbm's Nuclear Tech Mod - Refabricated";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing mod \"{}\"", MOD_ID);
		HbmItems.initialize();
		HbmItemGroups.initialize();

		HbmBlockEntities.initialize();
		HbmBlocks.initialize();

		HbmSounds.initialize();

		LOGGER.info("Finished initialization of mod \"{}\"", MOD_ID);
	}
}
