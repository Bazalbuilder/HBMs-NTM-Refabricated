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

package com.bazalbuilder.hbm.item.material;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum HbmToolMaterials implements ToolMaterial {
	SCHRABIDIUM(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 10000, 50.0f, 100.0f, 200, () -> Ingredient.EMPTY),
	STEEL(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 500, 7.5f, 5.0f,5, () -> Ingredient.EMPTY),
	TITANIUM(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 750, 9.0f, 2.5f, 15, () -> Ingredient.EMPTY),
	ADVANCED_ALLOY(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2000, 15.0f, 5.0f, 5, () -> Ingredient.EMPTY),
	CMB_STEEL(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8500, 40.0f, 55.0f, 100, () -> Ingredient.EMPTY),
	ELECTRUM(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 0, 30.0f, 12.0f, 2, () -> Ingredient.EMPTY),
	DESH(BlockTags.INCORRECT_FOR_GOLD_TOOL, 0, 7.5f, 2.0f, 10, () -> Ingredient.EMPTY),
	COBALT(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 750, 9.0f, 2.5f, 60, () -> Ingredient.EMPTY);

	private final TagKey<Block> inverseTag;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Supplier<Ingredient> repairIngredient;

	HbmToolMaterials(
		final TagKey<Block> inverseTag,
		final int itemDurability,
		final float miningSpeed,
		final float attackDamage,
		final int enchantability,
		final Supplier<Ingredient> supplier
	) {
		this.inverseTag = inverseTag;
		this.itemDurability = itemDurability;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = Suppliers.memoize(supplier::get);
	}

	@Override
	public int getDurability() {
		return this.itemDurability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public TagKey<Block> getInverseTag() {
		return this.inverseTag;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
