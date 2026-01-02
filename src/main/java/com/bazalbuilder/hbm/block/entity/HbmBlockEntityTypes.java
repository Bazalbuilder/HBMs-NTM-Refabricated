package com.bazalbuilder.hbm.block.entity;

import com.bazalbuilder.hbm.block.HbmBlocks;
import com.bazalbuilder.hbm.block.entity.bomb.FatManNukeBlockEntity;
import com.bazalbuilder.hbm.block.entity.debug.FacingObjModelBlockEntity;
import com.bazalbuilder.hbm.block.entity.machine.AssemblyMachineBlockEntity;
import com.bazalbuilder.hbm.block.entity.machine.ShredderBlockEntity;
import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.block.entity.BlockEntityType;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmBlockEntityTypes {
	// Debug
	public static final BlockEntityType<FacingObjModelBlockEntity> FACING_OBJ_MODEL;

	// Machines
	public static final BlockEntityType<ShredderBlockEntity> SHREDDER;
	public static final BlockEntityType<AssemblyMachineBlockEntity> ASSEMBLY_MACHINE;

	// Bombs
	public static final BlockEntityType<FatManNukeBlockEntity> FAT_MAN_NUKE;

	// TODO: sliding seal door, secure access door, round airlock door, qe sliding, qe containment, water door, (large) silo hatch, large vehicle door
	private static void registerBlockEntityTypes() {
		// Debug
		RegistryUtils.registerBlockEntityType("facing_obj_model", FACING_OBJ_MODEL);

		// Machines
		RegistryUtils.registerBlockEntityType("shredder", SHREDDER);
		RegistryUtils.registerBlockEntityType("assembly_machine", ASSEMBLY_MACHINE);

		// Bombs
		RegistryUtils.registerBlockEntityType("fat_man", FAT_MAN_NUKE);
	}

	public static void initialize() {
		// Since we're using static initializers, we'll leave this as a no-op.
		LOGGER.info("Initializing block entities for mod \"{}\"", MOD_ID);
	}

	static {
		// Debug
		FACING_OBJ_MODEL = BlockEntityType.Builder.create(FacingObjModelBlockEntity::new, HbmBlocks.FACING_OBJ_MODEL).build();

		// Machines
		SHREDDER = BlockEntityType.Builder.create(ShredderBlockEntity::new, HbmBlocks.SHREDDER).build();
		ASSEMBLY_MACHINE = BlockEntityType.Builder.create(AssemblyMachineBlockEntity::new, HbmBlocks.ASSEMBLY_MACHINE).build();

		// Bombs
		FAT_MAN_NUKE = BlockEntityType.Builder.create(FatManNukeBlockEntity::new, HbmBlocks.FAT_MAN).build();

		registerBlockEntityTypes();
	}
}
