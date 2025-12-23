package com.bazalbuilder.hbm.block.entity;

import com.bazalbuilder.hbm.block.HbmBlocks;
import com.bazalbuilder.hbm.block.entity.machine.RTGBlockEntity;
import com.bazalbuilder.hbm.block.entity.machine.ShredderBlockEntity;
import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.block.entity.BlockEntityType;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmBlockEntities {
	public static final BlockEntityType<ShredderBlockEntity> SHREDDER_ENTITY;
	public static final BlockEntityType<RTGBlockEntity> RTG_ENTITY;

	private static void registerBlockEntityTypes() {
		RegistryUtils.registerBlockEntityType("shredder", SHREDDER_ENTITY);
		RegistryUtils.registerBlockEntityType("rtg", RTG_ENTITY);
	}

	public static void initialize() {
		// Since we're using static initializers, we'll leave this as a no-op.
		LOGGER.info("Initializing block entities for mod \"{}\"", MOD_ID);
	}

	static {
		SHREDDER_ENTITY = BlockEntityType.Builder.create(ShredderBlockEntity::new, HbmBlocks.SHREDDER).build();
		RTG_ENTITY = BlockEntityType.Builder.create(RTGBlockEntity::new, HbmBlocks.RTG).build();

		registerBlockEntityTypes();
	}
}
