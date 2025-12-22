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

import com.bazalbuilder.hbm.block.machine.ShredderBlock;
import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import java.util.function.Function;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmBlocks {

	// Rocks
	public static final Block GRAPHITIC_SCHIST;
	public static final Block DEPTH_STONE;
	public static final Block NETHER_DEPTH_STONE;
	public static final Block LIGHTSTONE;
	public static final Block LIMESTONE;
	public static final Block SLAKED_SELLAFITE;
	public static final Block SELLAFITE;
	public static final Block ASPHALT;

	// Machines
	public static final Block SHREDDER;

	private static Block createBlock(Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
		return blockFactory.apply(settings);
	}

	private static void registerBlocks() {
		// Rocks
		RegistryUtils.registerBlock("graphitic_schist", GRAPHITIC_SCHIST, true);
		RegistryUtils.registerBlock("depth_stone", DEPTH_STONE, true);
		RegistryUtils.registerBlock("nether_depth_stone", NETHER_DEPTH_STONE, true);
		RegistryUtils.registerBlock("lightstone", LIGHTSTONE, true);
		RegistryUtils.registerBlock("limestone", LIMESTONE, true);
		RegistryUtils.registerBlock("slaked_sellafite", SLAKED_SELLAFITE, true);
		RegistryUtils.registerBlock("sellafite", SELLAFITE, true);
		RegistryUtils.registerBlock("asphalt", ASPHALT, true);

		// Machines
		RegistryUtils.registerBlock("shredder", SHREDDER, true);
	}

	public static void initialize() {
		// Since we're using static initializers, we'll leave this as a no-op.
		LOGGER.info("Initializing blocks for mod \"{}\"", MOD_ID);
	}

	static {
		// Rocks
		GRAPHITIC_SCHIST = createBlock(Block::new, AbstractBlock.Settings.create());
		DEPTH_STONE = createBlock(Block::new, AbstractBlock.Settings.create());
		NETHER_DEPTH_STONE = createBlock(Block::new, AbstractBlock.Settings.create());
		LIGHTSTONE = createBlock(Block::new, AbstractBlock.Settings.create());
		LIMESTONE = createBlock(Block::new, AbstractBlock.Settings.create());
		SLAKED_SELLAFITE = createBlock(Block::new, AbstractBlock.Settings.create());
		SELLAFITE = createBlock(Block::new, AbstractBlock.Settings.create());
		ASPHALT = createBlock(Block::new, AbstractBlock.Settings.create());

		// Machines
		SHREDDER = createBlock(ShredderBlock::new, AbstractBlock.Settings.create());

		registerBlocks();
	}
}
