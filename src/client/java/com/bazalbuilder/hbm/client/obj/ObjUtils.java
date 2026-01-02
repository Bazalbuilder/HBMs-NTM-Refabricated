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

package com.bazalbuilder.hbm.client.obj;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.data.client.ModelIds;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

@Environment(EnvType.CLIENT)
public class ObjUtils {
	private static final Identifier OBJ_PARENT = Identifier.of(MOD_ID, "block/obj_model");

	public static Identifier getParentLocation(JsonObject jsonObject) {
		if (jsonObject == null)
			return null;

		if (jsonObject.has("parent"))
			return Identifier.tryParse(JsonHelper.getString(jsonObject, "parent"));

		return null;
	}

	public static Identifier getIdentifier(JsonObject jsonObject, String name) {
		if (jsonObject.has(name) && jsonObject.get(name).isJsonPrimitive() && jsonObject.getAsJsonPrimitive(name).isString()) {
			return Identifier.tryParse(jsonObject.get(name).getAsString());
		}
		return null;
	}

	public static void generateObjJson(Identifier path, Identifier objLocation, Map<String, Identifier> textures, BiConsumer<Identifier, Supplier<JsonElement>> output) {
		output.accept(path, () -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("parent", OBJ_PARENT.toString());
			jsonObject.addProperty("model", objLocation.toString());

			if (!textures.isEmpty()) {
				JsonObject texturesJsonObject = new JsonObject();
				textures.forEach((name, location) -> texturesJsonObject.addProperty(name, location.toString()));
				jsonObject.add("textures", texturesJsonObject);
			}

			return jsonObject;
		});
	}

	public static void generateObjJson(Block block, Identifier objLocation, Map<String, Identifier> textures, BiConsumer<Identifier, Supplier<JsonElement>> output) {
		generateObjJson(ModelIds.getBlockModelId(block), objLocation, textures, output);
	}

	public static void generateObjJson(Item item, Identifier objLocation, Map<String, Identifier> textures, BiConsumer<Identifier, Supplier<JsonElement>> output) {
		generateObjJson(ModelIds.getItemModelId(item), objLocation, textures, output);
	}
}
