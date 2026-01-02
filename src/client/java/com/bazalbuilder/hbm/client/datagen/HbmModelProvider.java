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

package com.bazalbuilder.hbm.client.datagen;

import com.bazalbuilder.hbm.block.HbmBlocks;
import com.bazalbuilder.hbm.client.obj.ObjUtils;
import com.bazalbuilder.hbm.item.HbmItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmModelProvider extends FabricModelProvider {
	public HbmModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		blockStateModelGenerator.registerSimpleCubeAll(HbmBlocks.OBJ_MODEL);
		blockStateModelGenerator.registerSingleton(HbmBlocks.SHREDDER, TexturedModel.ORIENTABLE_WITH_BOTTOM);

		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(HbmBlocks.FACING_OBJ_MODEL, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(HbmBlocks.OBJ_MODEL, "")))
			.coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		ObjUtils.generateObjJson(HbmBlocks.FAT_MAN.asItem(), Identifier.of("hbm:models/obj/fat_man.obj"), Map.of("texture0", Identifier.of("textures/obj/fat_man.png")), itemModelGenerator.writer);

		itemModelGenerator.register(HbmItems.GUN_SPRAY, Models.GENERATED);
		itemModelGenerator.register(HbmItems.GEIGER_COUNTER, Models.GENERATED);
	}
}
