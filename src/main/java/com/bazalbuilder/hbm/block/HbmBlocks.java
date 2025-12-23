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
import com.bazalbuilder.hbm.world.OreDistribution;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmBlocks {

	public interface BlockInfo extends ItemConvertible {
		String getName();
		Block getBlock();
	}

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
	public static final Block RTG;

	// Ores
	private static final Map<Ores, Ores> NON_STONE_ORES = new HashMap<>();

	public enum Ores implements BlockInfo {
		URANIUM(OreDistribution.URANIUM),
		THORIUM(OreDistribution.THORIUM),
		TITANIUM(OreDistribution.TITANIUM),
		SULFUR(OreDistribution.SULFUR),
		NITER(OreDistribution.NITER),
		TUNGSTEN(OreDistribution.TUNGSTEN),
		ALUMINIUM_BEARING(OreDistribution.ALUMINIUM_BEARING),
		FLUORITE(OreDistribution.FLUORITE),
		BERYLLIUM(OreDistribution.BERYLLIUM),
		LEAD(OreDistribution.LEAD),
		LIGNITE(OreDistribution.LIGNITE),
		ASBESTOS(OreDistribution.ASBESTOS),
		SCHRABIDIUM(OreDistribution.SCHRABIDIUM),
		RARE_EARTH(OreDistribution.RARE_EARTH),
		COBALT(OreDistribution.COBALT),
		CINNABAR(OreDistribution.CINNABAR),

		CLUSTER_IRON(Blocks.IRON_ORE),
		CLUSTER_TITANIUM(TITANIUM),
		CLUSTER_ALUMINIUM(ALUMINIUM_BEARING),
		CLUSTER_COPPER(Blocks.COPPER_ORE),

		NETHER_COAL(Blocks.COAL_ORE),
		NETHER_URANIUM(URANIUM),
		NETHER_PLUTONIUM(OreDistribution.PLUTONIUM),
		NETHER_TUNGSTEN(OreDistribution.TUNGSTEN),
		NETHER_SULFUR(OreDistribution.SULFUR),
		NETHER_PHOSPHOROUS(OreDistribution.RED_PHOSPHOROUS),
		NETHER_COBALT(COBALT),
		NETHER_SCHRABIDIUM(SCHRABIDIUM),

		METEOR_IRON(Blocks.IRON_ORE),
		METEOR_COPPER(Blocks.COPPER_ORE),
		METEOR_ALUMINIUM(ALUMINIUM_BEARING),
		METEOR_RARE_EARTH(RARE_EARTH),
		METEOR_COBALT(COBALT),

		SCHIST_IRON(Blocks.IRON_ORE),
		SCHIST_GOLD(Blocks.GOLD_ORE),
		SCHIST_URANIUM(URANIUM),
		SCHIST_COPPER(Blocks.COPPER_ORE),
		SCHIST_ASBESTOS(ASBESTOS),
		SCHIST_LITHIUM(OreDistribution.LITHIUM),
		SCHIST_SCHRABIDIUM(SCHRABIDIUM),
		SCHIST_RARE_EARTH(RARE_EARTH),

		DEPTH_CINNABAR(CINNABAR),
		DEPTH_ZIRCONIUM(OreDistribution.ZIRCONIUM),
		DEPTH_BORAX(OreDistribution.BORAX),
		DEPTH_CLUSTER_IRON(Blocks.IRON_ORE),
		DEPTH_CLUSTER_TITANIUM(TITANIUM),
		DEPTH_CLUSTER_TUNGSTEN(TUNGSTEN),
		DEPTH_ALEXANDRITE(OreDistribution.ALEXANDRITE),

		NETHER_DEPTH_NEODYMIUM(OreDistribution.NEODYMIUM),

		BASALT_SULFUR(SULFUR),
		BASALT_FLUORITE(FLUORITE),
		BASALT_ASBESTOS(ASBESTOS),
		BASALT_MOLYSITE(OreDistribution.MOLYSITE),

		END_TRIXITE(OreDistribution.TRIXITE),

		TRINITITE(OreDistribution.TRINITITE),
		RED_TRINITITE(TRINITITE),

		SELLAFITE_DIAMOND(Blocks.DIAMOND_ORE),
		SELLAFITE_EMERALD(Blocks.EMERALD_ORE),
		SELLAFITE_URANIUM(URANIUM),
		SELLAFITE_SCHRABIDIUM(SCHRABIDIUM);

		private final String name;
		private final Block block;
		private final OreDistribution distribution;

		Ores(OreDistribution distribution) {
			this.name = this.toString().toLowerCase(Locale.ROOT);

			// TODO: bleh
			this.block = new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), PresetBlockSettings.ore(
				name.startsWith("deepslate") ||
					name.startsWith("nether") ||
					name.startsWith("depth") ||
					name.startsWith("nether_depth") ||
					name.startsWith("basalt"),
				name + "_ore"
			));
			this.distribution = distribution;
		}

		Ores(Block vanillaBlock) {
			this.name = this.toString().toLowerCase(Locale.ROOT);
			this.block = new Block(AbstractBlock.Settings.copy(vanillaBlock));
			this.distribution = null;
		}

		Ores(Ores originalOre) {
			this(originalOre.distribution);
			NON_STONE_ORES.put(originalOre, this);
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}

		@Override
		public String getName() {
			return name + "_ore";
		}

		@Override
		public Block getBlock() {
			return block;
		}
	}

	public static void initialize() {
		// Since we're using static initializers, we'll leave this as a no-op.
		LOGGER.info("Initialized blocks for mod \"{}\"", MOD_ID);
	}

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
		RegistryUtils.registerBlock("rtg", RTG, true);

		// Enum Lists
		Arrays.stream(Ores.values()).forEach(value -> RegistryUtils.registerBlock(value.getName(), value.getBlock(), true));
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
		RTG = createBlock(Block::new, AbstractBlock.Settings.create());

		registerBlocks();
	}
}
