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

package com.bazalbuilder.ntm.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.ntm.HbmMain.MOD_ID;

public class RegistryUtils {
	public static void registerBlock(String name, Block block, boolean shouldRegisterAsItem) {
		RegistryKey<Block> blockRegistryKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, name));
		Registry.register(Registries.BLOCK, blockRegistryKey, block);

		if (shouldRegisterAsItem) {
			RegistryKey<Item> blockitemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
			BlockItem blockItem = new BlockItem(block, new Item.Settings());
			Registry.register(Registries.ITEM, blockitemRegistryKey, blockItem);
		}
	}

	public static void registerItem(String name, Item item) {
		RegistryKey<Item> itemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
		Registry.register(Registries.ITEM, itemRegistryKey, item);
	}
}
