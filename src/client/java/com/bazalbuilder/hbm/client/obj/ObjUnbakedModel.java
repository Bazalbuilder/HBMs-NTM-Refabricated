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

import de.javagl.obj.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.AffineTransformation;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class ObjUnbakedModel implements UnbakedModel {
	public static final SpriteIdentifier MISSING = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, MissingSprite.getMissingSpriteId());

	private final Obj obj;
	private final Map<String, Mtl> mtls;
	private final ObjSettings settings;
	private Sprite spriteTexture = null;

	public ObjUnbakedModel(Obj obj, Map<String, Mtl> mtls, ObjSettings settings) {
		this.obj = obj;
		this.mtls = mtls;
		this.settings = settings;
	}

	@Override
	public Collection<Identifier> getModelDependencies() {
		return settings.getDependencies();
	}

	@Override
	public void setParents(Function<Identifier, UnbakedModel> modelLoader) {
	}

	@Override
	public @Nullable BakedModel bake(Baker baker, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings rotationContainer) {
		Renderer renderer = RendererAccess.INSTANCE.getRenderer();
		if (renderer == null) {
			return null;
		}
		MeshBuilder builder = renderer.meshBuilder();
		QuadEmitter emitter = builder.getEmitter();

		Map<String, Obj> matGroups = ObjSplitting.splitByMaterialGroups(obj);

		matGroups.forEach((name, model) -> {
			for (int faceNum = 0; faceNum < model.getNumFaces(); faceNum++) {
				emitFace(emitter, textureGetter, name, model, model.getFace(faceNum), rotationContainer);
			}
		});

		Mesh mesh = builder.build();
		return new ObjBakedModel(mesh, spriteTexture, settings);
	}

	private void emitFace(QuadEmitter emitter, Function<SpriteIdentifier, Sprite> textureGetter, String matName, Obj obj, ObjFace face, ModelBakeSettings rotationContainer) {
		for (int i = 0; i < face.getNumVertices(); i++) {
			emitVertex(i, emitter, obj, face, rotationContainer);
		}

		Mtl mtl = mtls.get(matName);

		int flags = MutableQuadView.BAKE_NORMALIZED | (rotationContainer.isUvLocked() ? MutableQuadView.BAKE_LOCK_UV : 0);

		Identifier texturePath = null;
		String texture;

		if (mtl != null && (texture = mtl.getMapKd()) != null) {
			if (texture.startsWith("#")) {
				texturePath = settings.getTextures().get(texture);
			} else {
				texturePath = Identifier.tryParse(texture);
			}
		}

		if (texturePath != null) {
			SpriteIdentifier spriteIdentifier = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, texturePath);
			this.spriteTexture = textureGetter.apply(spriteIdentifier);
		} else {
			this.spriteTexture = textureGetter.apply(MISSING);
		}

		emitter.spriteBake(spriteTexture, flags);

		emitter.color(-1, -1, -1, -1);

		emitter.emit();
	}

	private void emitVertex(int vertex, QuadEmitter emitter, Obj obj, ObjFace face, ModelBakeSettings rotationContainer) {
		FloatTuple posUnformatted = obj.getVertex(face.getVertexIndex(vertex));
		FloatTuple normalsUnformatted = obj.getNormal(face.getNormalIndex(0));
		Vector3f pos = new Vector3f(posUnformatted.getX(), posUnformatted.getY(), posUnformatted.getZ());
		Vector3f normals = face.containsNormalIndices()
			? new Vector3f(normalsUnformatted.getX(), normalsUnformatted.getY(), normalsUnformatted.getZ())
			: null;

		float u = 0, v = 0;

		if (face.containsTexCoordIndices()) {
			FloatTuple textureCoords = obj.getTexCoord(face.getTexCoordIndex(vertex));
			u = textureCoords.getX();
			v = 1f - textureCoords.getY();

			u = u > 1 || u < 0 ? (((u % 1f) + 1f) % 1f) : u;
			v = v > 1 || v < 0 ? (((v % 1F) + 1F) % 1F) : v;
		}

		rotate(pos, normals, rotationContainer);

		emitter.pos(vertex, pos);
		emitter.normal(vertex, normals);
		emitter.uv(vertex, u, v);

		if (face.getNumVertices() == 3) {
			emitter.pos(vertex, pos);
			emitter.normal(vertex, normals);
			emitter.uv(vertex, u, v);
		}
	}

	private void rotate(Vector3f vertices, Vector3f normals, ModelBakeSettings rotationContainer) {
		if (rotationContainer.getRotation() != AffineTransformation.identity()) {
			vertices.add(-0.5f, -0.5f, -0.5f);
			vertices.rotate(rotationContainer.getRotation().getLeftRotation());
			vertices.add(0.5f, 0.5f, 0.5f);

			normals.rotate(rotationContainer.getRotation().getLeftRotation());
		}
	}
}
