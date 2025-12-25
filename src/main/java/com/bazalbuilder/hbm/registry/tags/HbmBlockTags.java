package com.bazalbuilder.hbm.registry.tags;

import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class HbmBlockTags {
	public static final TagKey<Block> NATURAL_LOGS = of("natural_logs");
	public static final TagKey<Block> NATURAL_WOODS = of("natural_woods");

	private HbmBlockTags() {
	}

	private static TagKey<Block> of(String name) {
		return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TagUtil.C_TAG_NAMESPACE, name));
	}
}
