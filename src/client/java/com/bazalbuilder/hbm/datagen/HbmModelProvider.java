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

package com.bazalbuilder.hbm.datagen;

import com.bazalbuilder.hbm.block.HbmBlocks;
import com.bazalbuilder.hbm.item.HbmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

import java.util.Arrays;

public class HbmModelProvider extends FabricModelProvider {
	public HbmModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.GRAPHITIC_SCHIST);
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.DEPTH_STONE);
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.NETHER_DEPTH_STONE);
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.LIGHTSTONE);
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.LIMESTONE);
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.SLAKED_SELLAFITE);
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.SELLAFITE);
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.ASPHALT);
		blockStateModelGenerator.registerSingleton(HbmBlocks.SHREDDER, TexturedModel.ORIENTABLE_WITH_BOTTOM);

		Arrays.stream(HbmBlocks.Ores.values()).forEach(
			value -> blockStateModelGenerator.registerSimpleCubeAll(value.getBlock())
		);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(HbmItems.GUN_SPRAY, Models.GENERATED);
	}
}
