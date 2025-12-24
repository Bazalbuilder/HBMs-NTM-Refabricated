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

package com.bazalbuilder.hbm.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmItemGroups {
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_PARTS = getRegistryKey("parts");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_CONTROLS = getRegistryKey("controls");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_TEMPLATES = getRegistryKey("templates");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_BLOCKS = getRegistryKey("blocks");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_MACHINES = getRegistryKey("machines");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_NUKES = getRegistryKey("nukes");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_MISSILES = getRegistryKey("missiles");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_WEAPONS = getRegistryKey("weapons");
	private static final RegistryKey<ItemGroup> REGISTRY_KEY_CONSUMABLES = getRegistryKey("consumables");

	public static final ItemGroup GROUP_PARTS;
	public static final ItemGroup GROUP_CONTROLS;
	public static final ItemGroup GROUP_TEMPLATES;
	public static final ItemGroup GROUP_BLOCKS;
	public static final ItemGroup GROUP_MACHINES;
	public static final ItemGroup GROUP_NUKES;
	public static final ItemGroup GROUP_MISSILES;
	public static final ItemGroup GROUP_WEAPONS;
	public static final ItemGroup GROUP_CONSUMABLES;

	public static RegistryKey<ItemGroup> getRegistryKey(String name) {
		return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MOD_ID, name));
	}

	public static void initialize() {
		// Since we're using static initializers, we'll leave this as a no-op.
		LOGGER.info("Initializing item groups for mod \"{}\"", MOD_ID);
	}

	public static void assignItemGroups() {

	}

	static {
		GROUP_PARTS = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_PARTS, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Parts"))
			.build());
		GROUP_CONTROLS = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_CONTROLS,  FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Controls"))
			.build());
		GROUP_TEMPLATES = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_TEMPLATES, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Templates"))
			.build());
		GROUP_BLOCKS = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_BLOCKS, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Blocks"))
			.build());
		GROUP_MACHINES = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_MACHINES, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Machines"))
			.build());
		GROUP_NUKES = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_NUKES, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Nukes"))
			.build());
		GROUP_MISSILES = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_MISSILES, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Missiles"))
			.build());
		GROUP_WEAPONS = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_WEAPONS, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Weapons"))
			.build());
		GROUP_CONSUMABLES = Registry.register(Registries.ITEM_GROUP, REGISTRY_KEY_CONSUMABLES, FabricItemGroup.builder()
			.icon(() -> new ItemStack(Items.ACACIA_BOAT))
			.displayName(Text.literal("HBM Consumables"))
			.build());

		assignItemGroups();
	}
}
