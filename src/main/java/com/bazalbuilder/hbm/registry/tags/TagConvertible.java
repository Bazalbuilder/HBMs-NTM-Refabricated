package com.bazalbuilder.hbm.registry.tags;

import net.minecraft.registry.tag.TagKey;

public interface TagConvertible<T> {
	String getName();
	TagKey<T> asTag();
}
