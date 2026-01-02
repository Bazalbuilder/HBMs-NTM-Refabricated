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

package com.bazalbuilder.hbm.item;

import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface RemoteControllable {

	RemoteReturnCode trigger(World world, BlockPos pos);

	enum RemoteReturnCode {
		UNDEFINED(false, Text.empty()),
		DETONATED(true, Text.translatable("hbmPopup.remote.detonated")),
		TRIGGERED(true, Text.translatable("hbmPopup.remote.triggered")),
		LAUNCHED(true, Text.translatable("hbmPopup.remote.launched")),
		ERROR_MISSING_COMPONENT(true, Text.translatable("hbmPopup.remote.missingComponent")),
		ERROR_INCOMPATIBLE(true, Text.translatable("hbmPopup.remote.incompatible")),
		ERROR_INVALID(true, Text.translatable("hbmPopup.remote.invalid"));

		private boolean success;
		private Text message;

		RemoteReturnCode(boolean success, Text message) {
			this.success = success;
			this.message = message;
		}

		public String getUnlocalized() {
			return message.getString();
		}

		public boolean isSuccessful() {
			return this.success;
		}
	}
}
