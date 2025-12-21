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

package com.bazalbuilder.ntm.block;

import com.bazalbuilder.ntm.util.RegistryUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import java.util.function.Function;

import static com.bazalbuilder.ntm.HbmMain.LOGGER;
import static com.bazalbuilder.ntm.HbmMain.MOD_ID;

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
	public static final Block LIGHT_ASPHALT;

	// Doors
//	public static final Block METAL_DOOR;
//	public static final Block OFFICE_DOOR;
//	public static final Block BUNKER_DOOR;
//	public static final Block RED_ROOM_DOOR;
//
//	// Structure Blocks
//	public static final Block REBAR;
//	public static final Block REINFORCED_STONE;
//	public static final Block ASBESTOS_CONCRETE;
//	public static final Block CONCRETE_AND_REBAR;
//	public static final Block SMOOTH_DUCRETE;
//	public static final Block DUCRETE;
//	public static final Block CONCRETE_PILLAR;
//	public static final Block CONCRETE_BRICKS;
//	public static final Block MOSSY_CONCRETE_BRICKS;
//	public static final Block CRACKED_CONCRETE_BRICKS;
//	public static final Block BROKEN_CONCRETE_BRICKS;
//	public static final Block DUCRETE_BRICKS;
//	public static final Block OBSIDIAN_BRICKS;
//	public static final Block LIGHT_BRICKS;
//	public static final Block COMPOUND_BRICKS;
//	public static final Block ASBESTOS_BRICKS;
//	public static final Block FIRECLAY_BRICKS;
//	public static final Block FORGOTTEN_BRICKS;
//	public static final Block CMB_BRICKS;
//	public static final Block VINYL_TILE;
//	public static final Block LAB_TILE;
//	public static final Block LAB_TILE_CRACKED;
//	public static final Block LAB_TILE_BROKEN;
//
//	// Meteorite Blocks
//	public static final Block METEORITE;
//	public static final Block COBBLED_METEORITE;
//	public static final Block BROKEN_METEORITE;
//	public static final Block MOLTEN_COBBLED_METEORITE;
//	public static final Block TREASURE_METEORITE_BLOCK;
//	public static final Block POLISHED_METEORITE;
//	public static final Block METEORITE_BRICKS;
//	public static final Block MOSSY_METEORITE_BRICKS;
//	public static final Block CRACKED_METEORITE_BRICKS;
//	public static final Block CHISELED_METEORITE_BRICKS;
//	public static final Block METEORITE_PILLAR;
//	public static final Block CYBER_CRAB_ASSEMBLER;
//	public static final Block STARMETAL_ELECTRICITY_GENERATOR;
//
//	// Decoration
//	public static final Block BROKEN_COMPUTER;
//	public static final Block BROKEN_CRT_SCREEN;
//	public static final Block BROKEN_TOASTER;
//	public static final Block TAPE_RECORDER;
//	public static final Block ANTENNA;
//	public static final Block SATELLITE_DISH;
//
//	// Containers
//	public static final Block OLD_FILING_CABINET;
//	public static final Block STEEL_FILING_CABINET;
//	public static final Block IRON_CRATE;
//	public static final Block STEEL_CRATE;
//	public static final Block DESH_CRATE;
//	public static final Block TUNGSTEN_CRATE;
//	public static final Block TEMPLATE_CRATE;
//	public static final Block SAFE;
//
//	// Jungle Dungeon Blocks
//	public static final Block ENARGITE_BRICKS;
//	public static final Block CRACKED_ENARGITE_BRICKS;
//	public static final Block BRITTLE_ENARGITE_BRICKS;
//	public static final Block GLYPHIC_ENARGITE_BRICKS;
//	public static final Block MAGMATIC_ENARGITE_BRICKS;
//	public static final Block ARCANE_ENARGITE_BRICKS;
//	public static final Block RADIOACTIVE_ENARGITE_BRICKS;
//	public static final Block TRAPPED_ENARGITE_BRICKS;
//
//	// Miscellaneous Blocks
//	public static final Block FALLOUT_LAYER;
//	public static final Block FOAM_LAYER;
//	public static final Block BORON_SAND_LAYER;
//	public static final Block LEAVES_LAYER;
//	public static final Block OIL_SPILL;
//	public static final Block MYSTERIOUS_STONE;

	private static Block createBlock(Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
		return blockFactory.apply(settings);
	}

	private static void registerBlocks() {
		RegistryUtils.registerBlock("graphitic_schist", GRAPHITIC_SCHIST, true);
		RegistryUtils.registerBlock("depth_stone", DEPTH_STONE, true);
		RegistryUtils.registerBlock("nether_depth_stone", NETHER_DEPTH_STONE, true);
		RegistryUtils.registerBlock("lightstone", LIGHTSTONE, true);
		RegistryUtils.registerBlock("limestone", LIMESTONE, true);
		RegistryUtils.registerBlock("slaked_sellafite", SLAKED_SELLAFITE, true);
		RegistryUtils.registerBlock("sellafite", SELLAFITE, true);
		RegistryUtils.registerBlock("asphalt", ASPHALT, true);
		RegistryUtils.registerBlock("light_asphalt", LIGHT_ASPHALT, true);
	}

	public static void initialize() {
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
		LIGHT_ASPHALT = createBlock(Block::new, AbstractBlock.Settings.create());

		// Doors
//		METAL_DOOR = createBlock(Block::new, AbstractBlock.Settings.create());
//		OFFICE_DOOR = createBlock(Block::new, AbstractBlock.Settings.create());
//		BUNKER_DOOR = createBlock(Block::new, AbstractBlock.Settings.create());
//		RED_ROOM_DOOR = createBlock(Block::new, AbstractBlock.Settings.create());
//
//		// Structure Blocks
//		REBAR = createBlock(Block::new, AbstractBlock.Settings.create());
//		REINFORCED_STONE = createBlock(Block::new, AbstractBlock.Settings.create());
//		ASBESTOS_CONCRETE = createBlock(Block::new, AbstractBlock.Settings.create());
//		CONCRETE_AND_REBAR = createBlock(Block::new, AbstractBlock.Settings.create());
//		SMOOTH_DUCRETE = createBlock(Block::new, AbstractBlock.Settings.create());
//		DUCRETE = createBlock(Block::new, AbstractBlock.Settings.create());
//		CONCRETE_PILLAR = createBlock(Block::new, AbstractBlock.Settings.create());
//		CONCRETE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		MOSSY_CONCRETE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		CRACKED_CONCRETE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		BROKEN_CONCRETE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		DUCRETE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		OBSIDIAN_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		LIGHT_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		COMPOUND_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		ASBESTOS_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		FIRECLAY_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		FORGOTTEN_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		CMB_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		VINYL_TILE = createBlock(Block::new, AbstractBlock.Settings.create());
//		LAB_TILE = createBlock(Block::new, AbstractBlock.Settings.create());
//		LAB_TILE_CRACKED = createBlock(Block::new, AbstractBlock.Settings.create());
//		LAB_TILE_BROKEN = createBlock(Block::new, AbstractBlock.Settings.create());
//
//		// Meteorite Blocks
//		METEORITE = createBlock(Block::new, AbstractBlock.Settings.create());
//		COBBLED_METEORITE = createBlock(Block::new, AbstractBlock.Settings.create());
//		BROKEN_METEORITE = createBlock(Block::new, AbstractBlock.Settings.create());
//		MOLTEN_COBBLED_METEORITE = createBlock(Block::new, AbstractBlock.Settings.create());
//		TREASURE_METEORITE_BLOCK = createBlock(Block::new, AbstractBlock.Settings.create());
//		POLISHED_METEORITE = createBlock(Block::new, AbstractBlock.Settings.create());
//		METEORITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		MOSSY_METEORITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		CRACKED_METEORITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		CHISELED_METEORITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		METEORITE_PILLAR = createBlock(Block::new, AbstractBlock.Settings.create());
//		CYBER_CRAB_ASSEMBLER = createBlock(Block::new, AbstractBlock.Settings.create());
//		STARMETAL_ELECTRICITY_GENERATOR = createBlock(Block::new, AbstractBlock.Settings.create());
//
//		// Decoration
//		BROKEN_COMPUTER = createBlock(Block::new, AbstractBlock.Settings.create());
//		BROKEN_CRT_SCREEN = createBlock(Block::new, AbstractBlock.Settings.create());
//		BROKEN_TOASTER = createBlock(Block::new, AbstractBlock.Settings.create());
//		TAPE_RECORDER = createBlock(Block::new, AbstractBlock.Settings.create());
//		ANTENNA = createBlock(Block::new, AbstractBlock.Settings.create());
//		SATELLITE_DISH = createBlock(Block::new, AbstractBlock.Settings.create());
//
//		// Containers
//		OLD_FILING_CABINET = createBlock(Block::new, AbstractBlock.Settings.create());
//		STEEL_FILING_CABINET = createBlock(Block::new, AbstractBlock.Settings.create());
//		IRON_CRATE = createBlock(Block::new, AbstractBlock.Settings.create());
//		STEEL_CRATE = createBlock(Block::new, AbstractBlock.Settings.create());
//		DESH_CRATE = createBlock(Block::new, AbstractBlock.Settings.create());
//		TUNGSTEN_CRATE = createBlock(Block::new, AbstractBlock.Settings.create());
//		TEMPLATE_CRATE = createBlock(Block::new, AbstractBlock.Settings.create());
//		SAFE = createBlock(Block::new, AbstractBlock.Settings.create());
//
//		// Jungle Dungeon Blocks
//		ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		CRACKED_ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		BRITTLE_ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		GLYPHIC_ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		MAGMATIC_ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		ARCANE_ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		RADIOACTIVE_ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//		TRAPPED_ENARGITE_BRICKS = createBlock(Block::new, AbstractBlock.Settings.create());
//
//		// Miscellaneous Blocks
//		FALLOUT_LAYER = createBlock(Block::new, AbstractBlock.Settings.create());
//		FOAM_LAYER = createBlock(Block::new, AbstractBlock.Settings.create());
//		BORON_SAND_LAYER = createBlock(Block::new, AbstractBlock.Settings.create());
//		LEAVES_LAYER = createBlock(Block::new, AbstractBlock.Settings.create());
//		OIL_SPILL = createBlock(Block::new, AbstractBlock.Settings.create());
//		MYSTERIOUS_STONE = createBlock(Block::new, AbstractBlock.Settings.create());

		registerBlocks();
	}
}
