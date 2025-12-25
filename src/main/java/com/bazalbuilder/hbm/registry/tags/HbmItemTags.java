package com.bazalbuilder.hbm.registry.tags;

import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class HbmItemTags {
	public static final TagKey<Item> NATURAL_LOGS = of("natural_logs");
	public static final TagKey<Item> NATURAL_WOODS = of("natural_woods");

	private HbmItemTags() {
	}

	private static TagKey<Item> of(String name) {
		return TagKey.of(RegistryKeys.ITEM, Identifier.of(TagUtil.C_TAG_NAMESPACE, name));
	}
}
