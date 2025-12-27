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

package com.bazalbuilder.hbm.client.render.entity;

import com.bazalbuilder.hbm.entity.effect.TorexNukeEntity;
import com.bazalbuilder.hbm.util.ColorUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

@Environment(EnvType.CLIENT)
public class TorexNukeEntityRenderer extends EntityRenderer<TorexNukeEntity> {
	public static final Identifier CLOUDLET_TEXTURE = Identifier.of(MOD_ID, "textures/particle/base_particle_texture.png");
	public static final Identifier FLASH_TEXTURE = Identifier.of(MOD_ID, "textures/particle/flare_texture.png");
	public static long shakeTimestamp;
	public static long flashTimestamp;

	private final Comparator<TorexNukeEntity.Cloudlet> cloudSorter = (first, second) -> {
		PlayerEntity playerEntity = MinecraftClient.getInstance().player;
		if (playerEntity == null)
			return 0;
		double dist1 = playerEntity.squaredDistanceTo(first.x, first.y, first.z);
		double dist2 = playerEntity.squaredDistanceTo(second.x, second.y, second.z);

		return Double.compare(dist2, dist1);
	};

	public TorexNukeEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
	}

	@Override
	public void render(TorexNukeEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();
		cloudletWrapper(entity, tickDelta, matrices, vertexConsumers);

		if (entity.timeUntilRegen <= 100) {
			double age = Math.min(entity.timeUntilRegen + tickDelta, 100);
			float alpha = (float) ((100d - age) / 100f);

			for (int i = 0; i < 3; i++) {
				flashWrapper(entity, matrices, vertexConsumers, alpha);
			}
		}
		if (entity.timeUntilRegen < 10 && System.currentTimeMillis() - flashTimestamp > 1_000)
			flashTimestamp = System.currentTimeMillis();

		if (entity.soundPlayed && !entity.cameraShook && System.currentTimeMillis() - shakeTimestamp > 1_000) {
			shakeTimestamp = System.currentTimeMillis();
			entity.cameraShook = true;

			PlayerEntity playerEntity = MinecraftClient.getInstance().player;

			if (playerEntity != null) {
				playerEntity.hurtTime = 15;
			}
		}
		matrices.pop();
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
	}

	private void cloudletWrapper(TorexNukeEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
		VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntitySmoothCutout(CLOUDLET_TEXTURE));

		List<TorexNukeEntity.Cloudlet> cloudlets = new ArrayList<>(entity.cloudlets);
		cloudlets.sort(cloudSorter);

		for (TorexNukeEntity.Cloudlet cloudlet : cloudlets) {
			Vec3d vec = cloudlet.getLerpPos(tickDelta);

			float x = (float) (vec.x - cloudlet.x);
			float y = (float) (vec.y - cloudlet.y);
			float z = (float) (vec.z - cloudlet.z);

			Matrix4f matrix = matrices.peek().getPositionMatrix();

			renderCloudlet(cloudlet, x, y, z, tickDelta, matrix, consumer);
		}
	}

	private void flashWrapper(TorexNukeEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, float alpha) {
		VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityAlpha(FLASH_TEXTURE));
		Matrix4f matrix = matrices.peek().getPositionMatrix();

		Random random = new Random(entity.getId());

		float x = (float) (random.nextGaussian() * 0.5f * entity.rollerSize);
		float y = (float) (random.nextGaussian() * 0.5f * entity.rollerSize);
		float z = (float) (random.nextGaussian() * 0.5f * entity.rollerSize);

		renderFlash(entity, x, y, z, alpha, matrix, consumer);
	}

	private void renderCloudlet(TorexNukeEntity.Cloudlet cloudlet, float x, float y, float z, float tickDelta, Matrix4f matrix, VertexConsumer consumer) {
		float alpha = cloudlet.getAlpha();
		float scale = cloudlet.getScale();

		Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
		Vector3f diagonal = new Vector3f(camera.getDiagonalPlane()).mul(scale);
		Vector3f vertical = new Vector3f(camera.getVerticalPlane()).mul(scale);

		float brightness = cloudlet.type == TorexNukeEntity.Type.CONDENSATION ? 0.9f : 0.75f * cloudlet.colorMod;
		Vec3d lerpColor = cloudlet.getLerpColor(tickDelta);

		int color = ColorUtils.setColorFromRGBA((float) lerpColor.x * brightness, (float) lerpColor.y * brightness, (float) lerpColor.z * brightness, alpha);
		int overlay = OverlayTexture.DEFAULT_UV;

		consumer.vertex(matrix, x - diagonal.x - vertical.x, y - diagonal.y - vertical.y, z - diagonal.z - vertical.z)
			.texture(1, 1)
			.color(color)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
		consumer.vertex(matrix, x - diagonal.x + vertical.x, y - diagonal.y + vertical.y, z - diagonal.z + vertical.z)
			.texture(1, 0)
			.color(color)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
		consumer.vertex(matrix, x + diagonal.x + vertical.x, y + diagonal.y + vertical.y, z + diagonal.z + vertical.z)
			.texture(0, 0)
			.color(color)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
		consumer.vertex(matrix, x + diagonal.x - vertical.x, y + diagonal.y - vertical.y, z + diagonal.z - vertical.z)
			.texture(0, 1)
			.color(color)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
	}

	private void renderFlash(TorexNukeEntity entity, float x, float y, float z, float alpha, Matrix4f matrix, VertexConsumer consumer) {
		float scale = (float) (25 * entity.rollerSize);

		Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
		Vector3f diagonal = new Vector3f(camera.getDiagonalPlane()).mul(scale);
		Vector3f vertical = new Vector3f(camera.getDiagonalPlane()).mul(scale);

		int color = ColorUtils.setColorFromRGBA(1.0f, 1.0f, 1.0f, alpha);
		int overlay = OverlayTexture.DEFAULT_UV;

		consumer.vertex(matrix, x - diagonal.x - vertical.x, y - diagonal.y - vertical.y, z - diagonal.z - vertical.z)
			.color(color)
			.texture(1, 1)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
		consumer.vertex(matrix, x - diagonal.x + vertical.x, y - diagonal.y + vertical.y, z - diagonal.z + vertical.z)
			.color(color)
			.texture(1, 0)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
		consumer.vertex(matrix, x + diagonal.x + vertical.x, y + diagonal.y + vertical.y, z + diagonal.z + vertical.z)
			.color(color)
			.texture(0, 0)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
		consumer.vertex(matrix, x + diagonal.x - vertical.x, y + diagonal.y - vertical.y, z + diagonal.z - vertical.z)
			.color(color)
			.texture(0, 1)
			.overlay(overlay)
			.normal(0.0f, 1.0f, 0.0f)
			.light(240);
	}

	@Override
	public Identifier getTexture(TorexNukeEntity entity) {
		return CLOUDLET_TEXTURE;
	}
}
