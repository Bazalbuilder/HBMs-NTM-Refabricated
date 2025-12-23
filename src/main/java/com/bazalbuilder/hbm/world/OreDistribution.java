package com.bazalbuilder.hbm.world;

import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.YOffset;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public enum OreDistribution {
	URANIUM(6, YOffset.aboveBottom(0), 32),
	THORIUM(7, YOffset.aboveBottom(0), 32),
	TITANIUM(8, YOffset.aboveBottom(0), 32),
	SULFUR(5, YOffset.aboveBottom(0), 32),
	NITER(6, YOffset.aboveBottom(0), 32),
	TUNGSTEN(10, YOffset.aboveBottom(0), 32),
	ALUMINIUM_BEARING(7, YOffset.aboveBottom(0), 32),
	FLUORITE(6, YOffset.aboveBottom(0), 32),
	BERYLLIUM(6, YOffset.aboveBottom(0), 32),
	LEAD(6, YOffset.aboveBottom(0), 32),
	LIGNITE(2, YOffset.aboveBottom(0), 32),
	ASBESTOS(4, YOffset.aboveBottom(0), 32),
	SCHRABIDIUM(2, YOffset.aboveBottom(0), 32),
	RARE_EARTH(6, YOffset.aboveBottom(0), 32),
	COBALT(2, YOffset.aboveBottom(0), 32),
	CINNABAR(1, YOffset.aboveBottom(0), 32),

	PLUTONIUM(1, YOffset.aboveBottom(0), 32),
	RED_PHOSPHOROUS(1, YOffset.aboveBottom(0), 32),

	LITHIUM(1, YOffset.aboveBottom(0), 32),
	ZIRCONIUM(1, YOffset.aboveBottom(0), 32),
	BORAX(1, YOffset.aboveBottom(0), 32),

	ALEXANDRITE(1, YOffset.aboveBottom(0), 32),
	NEODYMIUM(1, YOffset.aboveBottom(0), 32),

	MOLYSITE(1, YOffset.aboveBottom(0), 32),
	TRIXITE(1, YOffset.aboveBottom(0), 32),
	TRINITITE(1, YOffset.aboveBottom(0), 32);

	public final int veinSize;
	public final YOffset minOffset;
	public final int maxY;

	OreDistribution(int veinSize, YOffset minOffset, int maxY) {
		this.veinSize = veinSize;
		this.minOffset = minOffset;
		this.maxY = maxY;
	}
}
