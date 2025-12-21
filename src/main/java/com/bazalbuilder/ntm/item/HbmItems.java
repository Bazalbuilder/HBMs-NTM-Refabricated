/*
 * Copyright (c) 2025 Bazalbuilder
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.bazalbuilder.ntm.item;

import com.bazalbuilder.ntm.util.RegistryUtils;
import net.minecraft.item.Item;

import java.util.function.Function;

import static com.bazalbuilder.ntm.HbmMain.LOGGER;
import static com.bazalbuilder.ntm.HbmMain.MOD_ID;

public class HbmItems {
	public static final Item GUN_SPRAY;

	private static Item createItem(Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		return itemFactory.apply(settings);
	}

	private static void registerItems() {
		RegistryUtils.registerItem("gun_spray", GUN_SPRAY);
	}

	public static void initialize() {
		LOGGER.info("Initializing items for mod \"{}\"", MOD_ID);
	}

	static {
		GUN_SPRAY = createItem(Item::new, new Item.Settings());

		registerItems();
	}
}
