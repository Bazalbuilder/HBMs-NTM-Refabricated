package com.bazalbuilder.hbm.registry.tags;

import com.bazalbuilder.hbm.material.Material;
import com.bazalbuilder.hbm.registry.HbmRegistryKeys;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class HbmMaterialTags {
	public static final TagKey<Material> VANILLA = of("vanilla");

	private HbmMaterialTags() {
	}

	private static TagKey<Material> of(String name) {
		return TagKey.of(HbmRegistryKeys.MATERIAL, Identifier.of(TagUtil.C_TAG_NAMESPACE, name));
	}
}
