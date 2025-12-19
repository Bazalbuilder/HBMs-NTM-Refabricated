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

package com.bazalbuilder.ntm.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class Registrator {
	public static void registerBlock(
		Identifier name,
		Function<AbstractBlock.Settings, Block> blockFactory,
		AbstractBlock.Settings settings,
		@Nullable Item.Settings blockItemSettings
	) {
		RegistryKey<Block> blockRegistryKey = RegistryKey.of(Registries.BLOCK.getKey(), name);
		Block block = blockFactory.apply(settings);
		Registry.register(Registries.BLOCK, blockRegistryKey, block);

		if (blockItemSettings != null) {
			registerBlockItem(name, block, blockItemSettings);
		}
	}

	private static void registerBlockItem(Identifier name, Block block, Item.Settings settings) {
		RegistryKey<Item> blockitemRegistryKey = RegistryKey.of(Registries.ITEM.getKey(), name);
		BlockItem blockItem = new BlockItem(block, settings);
		Registry.register(Registries.ITEM, blockitemRegistryKey, blockItem);
	}

	public static void registerItem(Identifier name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		RegistryKey<Item> itemRegistryKey = RegistryKey.of(Registries.ITEM.getKey(), name);
		Item item = itemFactory.apply(settings);
		Registry.register(Registries.ITEM, itemRegistryKey, item);
	}
}
