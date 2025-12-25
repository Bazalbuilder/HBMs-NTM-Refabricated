package com.bazalbuilder.hbm.datagen.tag;

import com.bazalbuilder.hbm.material.Material;
import com.bazalbuilder.hbm.registry.HbmRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class HbmMaterialTagProvider extends FabricTagProvider<Material> {
	public HbmMaterialTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, HbmRegistryKeys.MATERIAL, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
	}
}
