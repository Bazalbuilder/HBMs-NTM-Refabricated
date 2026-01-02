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

import de.javagl.obj.Mtl;
import de.javagl.obj.MtlReader;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.Transformation;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

@Environment(EnvType.CLIENT)
public class ObjModelLoader {

	public static Map<String, Mtl> loadMaterial(ResourceManager manager, List<String> mtlNames) throws IOException {
		Map<String, Mtl> materials = new HashMap<>();

		for (String name : mtlNames) {
			Identifier resourceId = Identifier.of(MOD_ID, "models/" + name);

			if (manager.getResource(resourceId).isEmpty()) {
				resourceId = Identifier.of(MOD_ID, "models/obj/" + name);
			}

			if (manager.getResource(resourceId).isPresent()) {
				Resource resource = manager.getResourceOrThrow(resourceId);

				MtlReader.read(resource.getInputStream()).forEach(mtl -> materials.put(mtl.getName(), mtl));
			} else {
				LOGGER.error("Cannot get model '{}' .mtl file. Is it present in the resources folder?", resourceId);
			}
		}

		return materials;
	}

	public static UnbakedModel loadModel(Identifier identifier, ResourceManager manager, ObjSettings objSettings) {
		if (manager.getResource(identifier).isPresent()) {
			Identifier newId = identifier;

			if (!identifier.getPath().endsWith(".obj")) {
				newId = Identifier.of(newId.getNamespace(), newId.getPath() + ".obj");
			}

			if (!identifier.getPath().startsWith("models/")) {
				newId = Identifier.of(newId.getNamespace(), "models/" + newId.getPath());
			}

			try {
				InputStream inputStream = manager.getResourceOrThrow(newId).getInputStream();
				Obj obj = ObjReader.read(inputStream);
				Map<String, Mtl> materials = loadMaterial(manager, obj.getMtlFileNames());

				return new ObjUnbakedModel(obj, materials, objSettings);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return null;
	}

	public static class ModelTransformDeserializer extends ModelTransformation.Deserializer {
		public ModelTransformDeserializer() {
			super();
		}
	}

	public static class TransformDeserializer extends Transformation.Deserializer {
		public TransformDeserializer() {
			super();
		}
	}
}
