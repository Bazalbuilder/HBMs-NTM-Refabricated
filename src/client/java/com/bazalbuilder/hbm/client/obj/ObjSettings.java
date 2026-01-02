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

import com.google.gson.JsonObject;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public interface ObjSettings {
	static ObjSettings of(boolean useAO, JsonUnbakedModel.GuiLight light, boolean emission, Identifier particle, Map<String, Identifier> textures, ModelTransformation transformation, List<Identifier> dependencies) {
		return new ObjSettingsImpl(useAO, light, emission, particle, textures, transformation, dependencies);
	}

	static ObjSettings parse(JsonObject modelJson) {
		return ObjSettingsImpl.parse(modelJson);
	}

	JsonUnbakedModel.GuiLight getGuiLight();

	Map<String, Identifier> getTextures();

	ModelTransformation getTransformation();

	List<Identifier> getDependencies();
}
