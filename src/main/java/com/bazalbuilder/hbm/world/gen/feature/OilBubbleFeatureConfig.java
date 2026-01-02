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

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public class OilBubbleFeatureConfig implements FeatureConfig {
//	public static final Codec<OilBubbleFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
//		instance.group(
//			IntProvider.createValidatingCodec(8, 64).fieldOf("size").forGetter((config) -> config.size)
//		).apply(instance, OilBubbleFeatureConfig::new);
//	});
	private final IntProvider size;

	public OilBubbleFeatureConfig(IntProvider frequency, IntProvider size) {
		this.size = size;
	}

	public IntProvider getSize() {
		return this.size;
	}
}
