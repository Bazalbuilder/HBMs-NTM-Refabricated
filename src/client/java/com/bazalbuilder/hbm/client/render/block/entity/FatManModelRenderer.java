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

package com.bazalbuilder.hbm.client.render.block.entity;

import com.bazalbuilder.hbm.block.entity.bomb.FatManNukeBlockEntity;
import com.bazalbuilder.hbm.mixin.client.BakedModelManagerAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

@Environment(EnvType.CLIENT)
public class FatManModelRenderer implements BlockEntityRenderer<FatManNukeBlockEntity> {
	private final BlockRenderManager renderManager;

	public FatManModelRenderer(BlockEntityRendererFactory.Context context) {
		this.renderManager = context.getRenderManager();

	}

	@Override
	public boolean rendersOutsideBoundingBox(FatManNukeBlockEntity blockEntity) {
		return true;
	}

	@Override
	public void render(FatManNukeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		ModelIdentifier MODEL_ID = ModelIdentifier.ofInventoryVariant(Identifier.of(MOD_ID, "fat_man"));

		BakedModel model = ((BakedModelManagerAccessor) MinecraftClient.getInstance().getBakedModelManager()).getModels().get(MODEL_ID);

		if (model != null) {
			VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getSolid());

			matrices.push();
			matrices.translate(0.5, 0, 0.5);
			renderManager.getModelRenderer().render(
				matrices.peek(),
				consumer,
				entity.getCachedState(),
				model,
				1.0f,
				1.0f,
				1.0f,
				light,
				overlay
			);
			matrices.pop();
		}
	}
}
