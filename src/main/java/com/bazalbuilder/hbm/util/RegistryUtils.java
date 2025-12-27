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

package com.bazalbuilder.hbm.util;

import com.bazalbuilder.hbm.material.Material;
import com.bazalbuilder.hbm.registry.HbmRegistries;
import com.bazalbuilder.hbm.registry.HbmRegistryKeys;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

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

	public static <T extends BlockEntity> void registerBlockEntityType(String name, BlockEntityType<? extends T> blockEntityType) {
		RegistryKey<BlockEntityType<?>> blockEntityRegistryKey = RegistryKey.of(RegistryKeys.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, name));
		Registry.register(Registries.BLOCK_ENTITY_TYPE, blockEntityRegistryKey, blockEntityType);
	}

	public static void registerItem(String name, Item item) {
		RegistryKey<Item> itemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));

		Registry.register(Registries.ITEM, itemRegistryKey, item);
	}

	public static void registerMaterial(String name, Material material) {
		RegistryKey<Material> materialRegistryKey = RegistryKey.of(HbmRegistryKeys.MATERIAL, Identifier.of(MOD_ID, name));
		Registry.register(HbmRegistries.MATERIALS, materialRegistryKey, material);
	}

	public static void registerSoundEvent(String name, SoundEvent event) {
		RegistryKey<SoundEvent> soundEventRegistryKey = RegistryKey.of(RegistryKeys.SOUND_EVENT, Identifier.of(MOD_ID, name));
		LOGGER.warn(name);
		Registry.register(Registries.SOUND_EVENT, soundEventRegistryKey, event);
	}

	public static void registerEntityType(String name, EntityType<?> entityType) {
		RegistryKey<EntityType<?>> entityTypeRegistryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MOD_ID, name));
		Registry.register(Registries.ENTITY_TYPE, entityTypeRegistryKey, entityType);
	}

	public static void registerParticleType(Identifier id, ParticleType<?> particleType) {
		RegistryKey<ParticleType<?>> particleTypeRegistryKey = RegistryKey.of(RegistryKeys.PARTICLE_TYPE, id);
		Registry.register(Registries.PARTICLE_TYPE, particleTypeRegistryKey, particleType);
	}

	public static void registerEntityAttribute(Identifier id, EntityAttribute entityAttribute) {
		RegistryKey<EntityAttribute> entityAttributeRegistryKey = RegistryKey.of(RegistryKeys.ATTRIBUTE, id);
		Registry.register(Registries.ATTRIBUTE, entityAttributeRegistryKey, entityAttribute);
	}
}
