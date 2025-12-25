package com.bazalbuilder.hbm.registry;

import com.bazalbuilder.hbm.material.Material;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmRegistryKeys {
	public static final RegistryKey<Registry<Material>> MATERIAL;

	static {
		MATERIAL = RegistryKey.ofRegistry(Identifier.of(MOD_ID, "materials"));
	}
}
