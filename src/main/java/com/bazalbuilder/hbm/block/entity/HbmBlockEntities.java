package com.bazalbuilder.hbm.block.entity;

import com.bazalbuilder.hbm.block.HbmBlocks;
import com.bazalbuilder.hbm.block.entity.machine.ShredderBlockEntity;
import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.block.entity.BlockEntityType;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmBlockEntities {
	public static final BlockEntityType<ShredderBlockEntity> SHREDDER;

	private static void registerBlockEntityTypes() {
		RegistryUtils.registerBlockEntityType("shredder", SHREDDER);
	}

	public static void initialize() {
		// Since we're using static initializers, we'll leave this as a no-op.
		LOGGER.info("Initializing block entities for mod \"{}\"", MOD_ID);
	}

	static {
		SHREDDER = BlockEntityType.Builder.create(ShredderBlockEntity::new, HbmBlocks.SHREDDER).build();

		registerBlockEntityTypes();
	}
}
