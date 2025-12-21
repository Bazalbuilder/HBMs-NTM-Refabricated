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

package com.bazalbuilder.ntm;

import com.bazalbuilder.ntm.block.HbmBlocks;
import com.bazalbuilder.ntm.item.HbmItemGroups;
import com.bazalbuilder.ntm.item.HbmItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbmMain implements ModInitializer {
	public static final String MOD_ID = "hbm";
	public static final String NAME = "Hbm's Nuclear Tech Mod - Refabricated";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing mod {}", MOD_ID);
		HbmItems.initialize();
		HbmItemGroups.initialize();
		HbmBlocks.initialize();
	}
}
