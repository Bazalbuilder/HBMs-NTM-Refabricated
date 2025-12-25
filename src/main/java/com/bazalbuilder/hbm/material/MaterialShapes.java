package com.bazalbuilder.hbm.material;

import java.util.ArrayList;
import java.util.List;

public class MaterialShapes {
	public static final List<MaterialShapes> allShapes = new ArrayList<>();

	public static final MaterialShapes ORE = new MaterialShapes(0, "ore").disableAutogen();

	public static final MaterialShapes QUANTUM = new MaterialShapes(1);
	public static final MaterialShapes NUGGET = new MaterialShapes(8, "nugget", "tiny");
	public static final MaterialShapes TINY = new MaterialShapes(8, "tiny").disableAutogen();
	public static final MaterialShapes FRAGMENT = new MaterialShapes(8, "bedrock_ore_fragment");
	public static final MaterialShapes DUST_TINY = new MaterialShapes(NUGGET.quantity, "dust_tiny");
	public static final MaterialShapes WIRE = new MaterialShapes(9, "wire");
	public static final MaterialShapes BOLT = new MaterialShapes(9, "bolt");
	public static final MaterialShapes BILLET = new MaterialShapes(NUGGET.quantity * 6, "billet");
	public static final MaterialShapes INGOT = new MaterialShapes(NUGGET.quantity * 9, "ingot");
	public static final MaterialShapes GEM = new MaterialShapes(INGOT.quantity, "gem");
	public static final MaterialShapes CRYSTAL = new MaterialShapes(INGOT.quantity, "crystal");
	public static final MaterialShapes DUST = new MaterialShapes(INGOT.quantity, "dust");
	public static final MaterialShapes DENSE_WIRE = new MaterialShapes(INGOT.quantity, "dense_wire");
	public static final MaterialShapes PLATE = new MaterialShapes(INGOT.quantity, "plate");
	public static final MaterialShapes CAST_PLATE = new MaterialShapes(INGOT.quantity * 3, "cast_plate");
	public static final MaterialShapes WELDED_PLATE = new MaterialShapes(INGOT.quantity * 6, "welded_plate");
	public static final MaterialShapes SHELL = new MaterialShapes(INGOT.quantity * 4, "shell");
	public static final MaterialShapes PIPE = new MaterialShapes(INGOT.quantity * 3, "hbm_pipe");
	public static final MaterialShapes QUART = new MaterialShapes(162);
	public static final MaterialShapes BLOCK = new MaterialShapes(INGOT.quantity * 9, "block");

	public static final MaterialShapes LIGHT_BARREL = new MaterialShapes(INGOT.quantity * 3, "barrel_light");
	public static final MaterialShapes HEAVY_BARREL = new MaterialShapes(INGOT.quantity * 6, "barrel_heavy");
	public static final MaterialShapes LIGHT_RECEIVER = new MaterialShapes(INGOT.quantity * 4, "receiver_light");
	public static final MaterialShapes HEAVY_RECEIVER = new MaterialShapes(INGOT.quantity * 9, "receiver_heavy");
	public static final MaterialShapes MECHANISM = new MaterialShapes(INGOT.quantity * 4, "gun_mechanism");
	public static final MaterialShapes STOCK = new MaterialShapes(INGOT.quantity * 4, "stock");
	public static final MaterialShapes GRIP = new MaterialShapes(INGOT.quantity * 2, "grip");

	public boolean autogen = true;
	private final int quantity;
	public final String[] prefixes;

	private MaterialShapes(int quantity, String... prefixes) {
		this.quantity = quantity;
		this.prefixes = prefixes;

		for (String prefix : prefixes) {
			HbmMaterials.materialPrefixes.put(prefix, this);
		}

		allShapes.add(this);
	}

	public MaterialShapes disableAutogen() {
		this.autogen = false;
		return this;
	}

	public int getQuantity(int amount) {
		return this.quantity * amount;
	}

	public int getQuantity(int unitsUsed, int itemsProduced) {
		return this.quantity * unitsUsed / itemsProduced;
	}

	public String getPrefix() {
		return (prefixes != null && prefixes.length > 0) ? prefixes[0] : "unknown";
	}

	public String formatName(String name) {
		return this.getPrefix() + name;
	}
}
