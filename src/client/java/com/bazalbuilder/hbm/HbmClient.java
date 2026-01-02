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

package com.bazalbuilder.hbm;

import com.bazalbuilder.hbm.block.entity.HbmBlockEntityTypes;
import com.bazalbuilder.hbm.client.obj.ObjModelLoader;
import com.bazalbuilder.hbm.client.obj.ObjSettings;
import com.bazalbuilder.hbm.client.obj.ObjUtils;
import com.bazalbuilder.hbm.client.render.block.entity.FatManModelRenderer;
import com.bazalbuilder.hbm.client.render.entity.TorexNukeEntityRenderer;
import com.bazalbuilder.hbm.entity.HbmEntityTypes;
import com.google.gson.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.PreparableModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.Transformation;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Environment(EnvType.CLIENT)
public class HbmClient implements ClientModInitializer {
	private static final Gson GSON = new GsonBuilder()
		.registerTypeAdapter(ModelTransformation.class, new ObjModelLoader.ModelTransformDeserializer())
		.registerTypeAdapter(Transformation.class, new ObjModelLoader.TransformDeserializer())
		.create();
	private static final ResourceFinder OBJ_MODEL_FINDER = ResourceFinder.json("models");

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(HbmEntityTypes.TOREX_ENTITY, TorexNukeEntityRenderer::new);
		PreparableModelLoadingPlugin.register(HbmClient::loadData, ((data, pluginContext) -> {
			ObjModelLoader loader = new ObjModelLoader();

			pluginContext.modifyModelOnLoad().register((model, ctx) -> {
				Identifier identifier = ctx.resourceId();
				if (identifier != null) {
					ResourceManager manager = MinecraftClient.getInstance().getResourceManager();
					return preloadModel(model, manager, identifier);
				}
				return model;
			});
		}));
		BlockEntityRendererFactories.register(HbmBlockEntityTypes.FAT_MAN_NUKE, FatManModelRenderer::new);
	}

	private static CompletableFuture<Map<Identifier, Resource>> loadData(ResourceManager resourceManager, Executor executor) {
		return CompletableFuture.supplyAsync(() -> OBJ_MODEL_FINDER.findResources(resourceManager), executor).thenApply(map -> {
			Map<Identifier, Resource> hashSet = new HashMap<>();

			hashSet.putAll(resourceManager.findResources("models", id -> id.getPath().endsWith(".json")));

			return hashSet;
		});
	}

	private static UnbakedModel preloadModel(UnbakedModel model, ResourceManager manager, Identifier identifier) {
		Identifier path = Identifier.of(identifier.getNamespace(), "models/" + identifier.getPath() + ".json");

		Optional<Resource> res = manager.getResource(path);

		if (res.isEmpty()) {
			return model;
		}

		try (Reader reader = res.get().getReader()){
			JsonObject raw = GSON.fromJson(reader, JsonObject.class);

			if (ObjUtils.getParentLocation(raw) == null)
				return model;

			JsonElement jsonModel = raw.get("model");
			if (!(jsonModel instanceof JsonPrimitive) || !((JsonPrimitive) jsonModel).isString()) {
				return model;
			}
			Identifier modelPath = Identifier.of(jsonModel.getAsString());

			ObjSettings objSettings = ObjSettings.parse(raw);

			return ObjModelLoader.loadModel(modelPath, manager, objSettings);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
