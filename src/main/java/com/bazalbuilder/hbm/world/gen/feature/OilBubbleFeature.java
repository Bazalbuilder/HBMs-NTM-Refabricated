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

package com.bazalbuilder.hbm.world.gen.feature;

import com.bazalbuilder.hbm.block.HbmBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class OilBubbleFeature extends Feature<OilBubbleFeatureConfig> {
	public OilBubbleFeature(Codec<OilBubbleFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(FeatureContext<OilBubbleFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();

		BlockState blockState = HbmBlocks.OIL_DEPOSIT.getDefaultState();



		return false;
	}
}
