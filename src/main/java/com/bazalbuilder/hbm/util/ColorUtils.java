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

package com.bazalbuilder.hbm.util;

import net.minecraft.util.math.MathHelper;

public class ColorUtils {
	public static int setColorFromRGBA(float red, float green, float blue, float alpha) {
		int iRed = (int) Math.clamp(red * 255, 0, 255);
		int iGreen = (int) Math.clamp(green * 255, 0, 255);
		int iBlue = (int) Math.clamp(blue * 255, 0, 255);
		int iAlpha = (int) Math.clamp(alpha * 255, 0, 255);

		return iAlpha << 24 | iRed << 16 | iGreen << 8 | iBlue;
	}
}
