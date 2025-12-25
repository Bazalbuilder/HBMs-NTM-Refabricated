package com.bazalbuilder.hbm.registry;

import com.bazalbuilder.hbm.material.Material;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;

public class HbmRegistries {
	public static final Registry<Material> MATERIALS;

	static {
		MATERIALS = FabricRegistryBuilder.createSimple(HbmRegistryKeys.MATERIAL).attribute(RegistryAttribute.MODDED).buildAndRegister();
	}
}
