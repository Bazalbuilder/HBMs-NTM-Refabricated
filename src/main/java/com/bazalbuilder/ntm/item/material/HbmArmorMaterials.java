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

package com.bazalbuilder.ntm.item.material;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.bazalbuilder.ntm.HbmMain.MOD_ID;

public class HbmArmorMaterials {
	public static final RegistryEntry<ArmorMaterial> SCHRABIDIUM;
	public static final RegistryEntry<ArmorMaterial> EUPHEMIUM;
	public static final RegistryEntry<ArmorMaterial> HAZMAT;
	public static final RegistryEntry<ArmorMaterial> ADV_HAZMAT;
	public static final RegistryEntry<ArmorMaterial> HP_HAZMAT;
	public static final RegistryEntry<ArmorMaterial> STEEL;
	public static final RegistryEntry<ArmorMaterial> TITANIUM;
	public static final RegistryEntry<ArmorMaterial> ADVANCED_ALLOY;
	public static final RegistryEntry<ArmorMaterial> PAA_HAZMAT;
	public static final RegistryEntry<ArmorMaterial> CMB_STEEL;
	public static final RegistryEntry<ArmorMaterial> SECURITY;
	public static final RegistryEntry<ArmorMaterial> COBALT;
	public static final RegistryEntry<ArmorMaterial> STARMETAL;
	public static final RegistryEntry<ArmorMaterial> BISMUTH;

	public static RegistryEntry<ArmorMaterial> register(String name, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> soundEvent, Supplier<Ingredient> repairIngredient, float toughness, float knockbackResistance){
		List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Identifier.of(MOD_ID, name)));

		return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(MOD_ID, name), new ArmorMaterial(defensePoints, enchantability, soundEvent, repairIngredient, layers, toughness, knockbackResistance));
	}

	static {
		// TODO: replace Ingredient.EMPTY with ingot types
		SCHRABIDIUM = register("schrabidium", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 50, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		EUPHEMIUM = register("euphemium", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 100, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		HAZMAT = register("hazmat", Map.of(
			ArmorItem.Type.HELMET, 2,
			ArmorItem.Type.CHESTPLATE, 5,
			ArmorItem.Type.LEGGINGS, 4,
			ArmorItem.Type.BOOTS, 1
		), 5, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		ADV_HAZMAT = register("adv_hazmat", Map.of(
			ArmorItem.Type.HELMET, 2,
			ArmorItem.Type.CHESTPLATE, 5,
			ArmorItem.Type.LEGGINGS, 4,
			ArmorItem.Type.BOOTS, 1
		), 5, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		HP_HAZMAT = register("hp_hazmat", Map.of(
			ArmorItem.Type.HELMET, 2,
			ArmorItem.Type.CHESTPLATE, 5,
			ArmorItem.Type.LEGGINGS, 4,
			ArmorItem.Type.BOOTS, 1
		), 5, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		STEEL = register("steel", Map.of(
			ArmorItem.Type.HELMET, 2,
			ArmorItem.Type.CHESTPLATE, 6,
			ArmorItem.Type.LEGGINGS, 5,
			ArmorItem.Type.BOOTS, 2
		), 5, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		TITANIUM = register("titanium", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		ADVANCED_ALLOY = register("advanced_alloy", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		PAA_HAZMAT = register("paa_hazmat", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 25, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		CMB_STEEL = register("cmb_steel", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 50, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		SECURITY = register("security", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		COBALT = register("cobalt", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 60, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		STARMETAL = register("starmetal", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 100, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);
		BISMUTH = register("bismuth", Map.of(
			ArmorItem.Type.HELMET, 3,
			ArmorItem.Type.CHESTPLATE, 8,
			ArmorItem.Type.LEGGINGS, 6,
			ArmorItem.Type.BOOTS, 3
		), 100, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, () -> Ingredient.EMPTY, 0.0f, 0.0f);

	}
}
