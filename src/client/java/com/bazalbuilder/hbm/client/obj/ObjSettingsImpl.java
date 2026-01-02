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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.Transformation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record ObjSettingsImpl(boolean useAO, JsonUnbakedModel.GuiLight guiLight, boolean emission, Identifier particle,
							  Map<String, Identifier> textures,
							  ModelTransformation transformation,
							  List<Identifier> dependencies) implements ObjSettings {
	private static final Gson GSON = new GsonBuilder()
		.registerTypeAdapter(ModelTransformation.class, new ObjModelLoader.ModelTransformDeserializer())
		.registerTypeAdapter(Transformation.class, new ObjModelLoader.TransformDeserializer())
		.create();

	public static ObjSettingsImpl parse(JsonObject modelJson) {
		ModelTransformation transform = ModelTransformation.NONE;

		if (modelJson.has("display")) {
			String jsonValue = JsonHelper.asString(modelJson, "display");
			transform = GSON.fromJson(jsonValue, ModelTransformation.class);
		}

		JsonUnbakedModel.GuiLight guiLight = null;
		if (modelJson.has("gui_light"))
			guiLight = JsonUnbakedModel.GuiLight.byName(JsonHelper.asString(modelJson, "gui_light"));

		boolean emission = false;
		if (modelJson.has("particle"))
			emission = JsonHelper.getBoolean(modelJson, "emissive", false);

		Identifier particle = null;
		if (modelJson.has("particle"))
			particle = Identifier.tryParse(JsonHelper.asString(modelJson, "particle"));

		List<Identifier> dependencies = new ArrayList<>();
		if (modelJson.has("parent"))
			dependencies.add(Identifier.of(modelJson.get("parent").getAsString()));

		Map<String, Identifier> textures = new HashMap<>();
		if (modelJson.has("textures") && modelJson.get("textures").isJsonObject()) {
			JsonObject texturesObject = modelJson.getAsJsonObject("textures");
			texturesObject.keySet().forEach(key -> {
				Identifier tex = ObjUtils.getIdentifier(texturesObject, key);
				if (tex != null) {
					textures.put(key, tex);
				}
			});
		}

		return new ObjSettingsImpl(JsonHelper.getBoolean(modelJson, "ambientocclusion", true), guiLight, emission, particle, textures, transform, dependencies);
	}

	@Override
	public JsonUnbakedModel.GuiLight getGuiLight() {
		return JsonUnbakedModel.GuiLight.BLOCK;
	}

	@Override
	public Map<String, Identifier> getTextures() {
		return textures;
	}

	@Override
	public ModelTransformation getTransformation() {
		return transformation;
	}

	@Override
	public List<Identifier> getDependencies() {
		return dependencies;
	}
}
