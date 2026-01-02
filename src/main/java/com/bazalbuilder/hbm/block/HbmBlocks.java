/*
 * Copyright (c) 2025 Bazalbuilder
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.bazalbuilder.hbm.block;

import com.bazalbuilder.hbm.block.bomb.FatManNukeBlock;
import com.bazalbuilder.hbm.block.debug.FacingObjModelBlock;
import com.bazalbuilder.hbm.block.machine.AssemblyMachineBlock;
import com.bazalbuilder.hbm.block.machine.ShredderBlock;
import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import java.util.function.Function;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmBlocks {
	// Debug
	public static final Block OBJ_MODEL;
	public static final Block FACING_OBJ_MODEL;

	// Bombs
	public static final Block FAT_MAN;

	// Machines
	public static final Block SHREDDER;
	public static final Block ASSEMBLY_MACHINE;

	// Ores
	public static final Block OIL_DEPOSIT;

	public static void initialize() {
		// Since we're using static initializers, we'll leave this as a no-op.
		LOGGER.info("Initializing blocks for mod \"{}\"", MOD_ID);
	}

	private static Block createBlock(Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
		return blockFactory.apply(settings);
	}

	private static void registerBlocks() {
		// Debug
		RegistryUtils.registerBlock("obj_model", OBJ_MODEL, false);
		RegistryUtils.registerBlock("facing_obj_model", FACING_OBJ_MODEL, false);

		// Bombs
		RegistryUtils.registerBlock("fat_man", FAT_MAN, true);

		// Machines
		RegistryUtils.registerBlock("shredder", SHREDDER, true);
		RegistryUtils.registerBlock("assembly_machine", ASSEMBLY_MACHINE, true);

		// Ores
		RegistryUtils.registerBlock("oil_deposit", OIL_DEPOSIT, true);
	}

	static {
		// Debug
		OBJ_MODEL = createBlock(Block::new, AbstractBlock.Settings.create());
		FACING_OBJ_MODEL = createBlock(FacingObjModelBlock::new, AbstractBlock.Settings.create());

		// Bombs
		FAT_MAN = createBlock(FatManNukeBlock::new, AbstractBlock.Settings.create().nonOpaque());

		// Machines
		SHREDDER = createBlock(ShredderBlock::new, AbstractBlock.Settings.create());
		ASSEMBLY_MACHINE = createBlock(AssemblyMachineBlock::new, AbstractBlock.Settings.create());

		// Ores
		OIL_DEPOSIT = createBlock(Block::new, AbstractBlock.Settings.create());

		registerBlocks();
	}
}
