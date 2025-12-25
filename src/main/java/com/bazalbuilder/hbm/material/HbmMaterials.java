package com.bazalbuilder.hbm.material;

import java.util.HashMap;

public class HbmMaterials {
	public static HashMap<String, MaterialShapes> materialPrefixes = new HashMap<>();

	// Vanilla
	public static final Material WOOD;
	public static final Material BONE;
	public static final Material COAL;
	public static final Material IRON;
	public static final Material COPPER;
	public static final Material GOLD;
	public static final Material LAPIS_LAZULI;
	public static final Material REDSTONE;
	public static final Material QUARTZ;
	public static final Material DIAMOND;
	public static final Material EMERALD;

	// Radioactive
	public static final Material URANIUM;
	public static final Material URANIUM_233;
	public static final Material URANIUM_235;
	public static final Material URANIUM_238;
	public static final Material THORIUM_232;
	public static final Material PLUTONIUM;
	public static final Material PLUTONIUM_REACTOR_GRADE;
	public static final Material PLUTONIUM_238;
	public static final Material PLUTONIUM_239;
	public static final Material PLUTONIUM_240;
	public static final Material PLUTONIUM_241;
	public static final Material AMERICIUM_241;
	public static final Material AMERICIUM_242;
	public static final Material AMERICIUM_REACTOR_GRADE;
	public static final Material NEPTUNIUM_237;
	public static final Material POLONIUM_210;
	public static final Material TECHNETIUM_99;
	public static final Material RADIUM_226;
	public static final Material ACTINIUM_227;
	public static final Material COBALT_60;
	public static final Material GOLD_198;
	public static final Material LEAD_209;
	public static final Material SCHRABIDIUM;
	public static final Material SOLINIUM;
	public static final Material SCHRABIDATE;
	public static final Material SCHRARANIUM;
	public static final Material GHIORSIUM_336;
	public static final Material WATZ_MUD;

	public static final Material TITANIUM;
	public static final Material RED_COPPER;
	public static final Material ADVANCED_ALLOY;
	public static final Material TUNGSTEN;
	public static final Material ALUMINIUM;
	public static final Material STEEL;
	public static final Material TECHNETIUM_STEEL;
	public static final Material CADMIUM_STEEL;
	public static final Material BISMUTH_BRONZE;
	public static final Material ARSENIC_BRONZE;
	public static final Material BSCCO;
	public static final Material LEAD;
	public static final Material BISMUTH;
	public static final Material ARSENIC;
	public static final Material CALCIUM;
	public static final Material CADMIUM;
	public static final Material TANTALUM;
	public static final Material COLTAN;
	public static final Material NIOBIUM;
	public static final Material BERYLLIUM;
	public static final Material COBALT;
	public static final Material BORON;
	public static final Material SILICON;
	public static final Material GRAPHITE;
	public static final Material CARBON;
	public static final Material DURASTEEL;
	public static final Material POLYMER;
	public static final Material BAKELITE;
	public static final Material PET;
	public static final Material POLYCARBONATE;
	public static final Material PVC;
	public static final Material LATEX;
	public static final Material RUBBER;
	public static final Material MAGNETIZED_TUNGSTEN;
	public static final Material CMB_STEEL;
	public static final Material DESH;
	public static final Material STARMETAL;
	public static final Material GUNMETAL;
	public static final Material SATURNITE;
	public static final Material FERROURANIUM;
	public static final Material EUPHEMIUM;
	public static final Material DINEUTRONIUM;
	public static final Material FIBERGLASS;
	public static final Material ASBESTOS;
	public static final Material OSMIRIDIUM;

	public static final Material SULFUR;
	public static final Material SALTPETER;
	public static final Material FLUORITE;
	public static final Material LIGNITE;
	public static final Material COAL_COKE;
	public static final Material PET_COKE;
	public static final Material LIGNITE_COKE;
	public static final Material CINNABAR;
	public static final Material BORAX;
	public static final Material CHLOROCALCITE;
	public static final Material MOLYSITE;
	public static final Material SODALITE;
	public static final Material VOLCANIC;
	public static final Material HEMATITE;
	public static final Material MALACHITE;
	public static final Material LIMESTONE;
	public static final Material SLAG;
	public static final Material BAUXITE;
	public static final Material CRYOLITE;

	public static final Material LITHIUM;
	public static final Material SODIUM;

	public static final Material WHITE_PHOSPHORUS;
	public static final Material RED_PHOSPHORUS;

	public static final Material AUSTRALIUM;

	public static final Material RARE_EARTH;
	public static final Material LANTHANUM;
	public static final Material ZIRCONIUM;
	public static final Material NEODYMIUM;
	public static final Material CERIUM;

	public static final Material IODINE;
	public static final Material ASTATINE;
	public static final Material CAESIUM;
	public static final Material STRONTIUM;
	public static final Material BROMINE;
	public static final Material TENNESSINE;

	public static final Material STRONTIUM_90;
	public static final Material IODINE_131;
	public static final Material XENON_135;
	public static final Material CAESIUM_137;
	public static final Material ASTATINE_209;

	static {
		// Vanilla
		WOOD = new Material(new Material.Settings("wood"));
		BONE = new Material(new Material.Settings("bone"));
		COAL = new Material(new Material.Settings("coal"));
		IRON = new Material(new Material.Settings("iron"));
		COPPER = new Material(new Material.Settings("copper"));
		GOLD = new Material(new Material.Settings("gold"));
		LAPIS_LAZULI = new Material(new Material.Settings("lapis_lazuli"));
		REDSTONE = new Material(new Material.Settings("redstone"));
		QUARTZ = new Material(new Material.Settings("quartz"));
		DIAMOND = new Material(new Material.Settings("diamond"));
		EMERALD = new Material(new Material.Settings("emerald"));

		// Radioactive
		URANIUM = new Material(new Material.Settings("uranium"));
		URANIUM_233 = new Material(new Material.Settings("uranium_233"));
		URANIUM_235 = new Material(new Material.Settings("uranium_235"));
		URANIUM_238 = new Material(new Material.Settings("uranium_238"));
		THORIUM_232 = new Material(new Material.Settings("thorium_232"));
		PLUTONIUM = new Material(new Material.Settings("plutonium"));
		PLUTONIUM_REACTOR_GRADE = new Material(new Material.Settings("reactor_grade_plutonium"));
		PLUTONIUM_238 = new Material(new Material.Settings("plutonium_238"));
		PLUTONIUM_239 = new Material(new Material.Settings("plutonium_239"));
		PLUTONIUM_240 = new Material(new Material.Settings("plutonium_240"));
		PLUTONIUM_241 = new Material(new Material.Settings("plutonium_241"));
		AMERICIUM_241 = new Material(new Material.Settings("americium_241"));
		AMERICIUM_242 = new Material(new Material.Settings("americium_242"));
		AMERICIUM_REACTOR_GRADE = new Material(new Material.Settings("reactor_grade_americium"));
		NEPTUNIUM_237 = new Material(new Material.Settings("neptunium_237"));
		POLONIUM_210 = new Material(new Material.Settings("polonium_210"));
		TECHNETIUM_99 = new Material(new Material.Settings("technetium_99"));
		RADIUM_226 = new Material(new Material.Settings("radium_226"));
		ACTINIUM_227 = new Material(new Material.Settings("actinium_227"));
		COBALT_60 = new Material(new Material.Settings("cobalt_60"));
		GOLD_198 = new Material(new Material.Settings("gold_198"));
		LEAD_209 = new Material(new Material.Settings("lead_209"));
		SCHRABIDIUM = new Material(new Material.Settings("schrabidium"));
		SOLINIUM = new Material(new Material.Settings("solinium"));
		SCHRABIDATE = new Material(new Material.Settings("schrabidate"));
		SCHRARANIUM = new Material(new Material.Settings("schraranium"));
		GHIORSIUM_336 = new Material(new Material.Settings("ghiorsium_336"));
		WATZ_MUD = new Material(new Material.Settings("watz_mud"));

		TITANIUM = new Material(new Material.Settings("titanium"));
		RED_COPPER = new Material(new Material.Settings("red_copper"));
		ADVANCED_ALLOY = new Material(new Material.Settings("advanced_alloy"));
		TUNGSTEN = new Material(new Material.Settings("tungsten"));
		ALUMINIUM = new Material(new Material.Settings("aluminium"));
		STEEL = new Material(new Material.Settings("steel"));
		TECHNETIUM_STEEL = new Material(new Material.Settings("technetium_steel"));
		CADMIUM_STEEL = new Material(new Material.Settings("cadmium_steel"));
		BISMUTH_BRONZE = new Material(new Material.Settings("bismuth_bronze"));
		ARSENIC_BRONZE = new Material(new Material.Settings("arsenic_bronze"));
		BSCCO = new Material(new Material.Settings("bscco"));
		LEAD = new Material(new Material.Settings("lead"));
		BISMUTH = new Material(new Material.Settings("bismuth"));
		ARSENIC = new Material(new Material.Settings("arsenic"));
		CALCIUM = new Material(new Material.Settings("calcium"));
		CADMIUM = new Material(new Material.Settings("cadmium"));
		TANTALUM = new Material(new Material.Settings("tantalum"));
		COLTAN = new Material(new Material.Settings("coltan"));
		NIOBIUM = new Material(new Material.Settings("niobium"));
		BERYLLIUM = new Material(new Material.Settings("beryllium"));
		COBALT = new Material(new Material.Settings("cobalt"));
		BORON = new Material(new Material.Settings("boron"));
		SILICON = new Material(new Material.Settings("silicon"));
		GRAPHITE = new Material(new Material.Settings("graphite"));
		CARBON = new Material(new Material.Settings("carbon"));
		DURASTEEL = new Material(new Material.Settings("durasteel"));
		POLYMER = new Material(new Material.Settings("polymer"));
		BAKELITE = new Material(new Material.Settings("bakelite"));
		PET = new Material(new Material.Settings("pet"));
		POLYCARBONATE = new Material(new Material.Settings("polycarbonate"));
		PVC = new Material(new Material.Settings("pvc"));
		LATEX = new Material(new Material.Settings("latex"));
		RUBBER = new Material(new Material.Settings("rubber"));
		MAGNETIZED_TUNGSTEN = new Material(new Material.Settings("magnetized_tungsten"));
		CMB_STEEL = new Material(new Material.Settings("cmb_steel"));
		DESH = new Material(new Material.Settings("desh"));
		STARMETAL = new Material(new Material.Settings("starmetal"));
		GUNMETAL = new Material(new Material.Settings("gunmetal"));
		SATURNITE = new Material(new Material.Settings("saturnite"));
		FERROURANIUM = new Material(new Material.Settings("ferrouranium"));
		EUPHEMIUM = new Material(new Material.Settings("euphemium"));
		DINEUTRONIUM = new Material(new Material.Settings("dineutronium"));
		FIBERGLASS = new Material(new Material.Settings("fiberglass"));
		ASBESTOS = new Material(new Material.Settings("asbestos"));
		OSMIRIDIUM = new Material(new Material.Settings("osmiridium"));

		SULFUR = new Material(new Material.Settings("sulfur"));
		SALTPETER = new Material(new Material.Settings("saltpeter"));
		FLUORITE = new Material(new Material.Settings("fluorite"));
		LIGNITE = new Material(new Material.Settings("lignite"));
		COAL_COKE = new Material(new Material.Settings("coal_coke"));
		PET_COKE = new Material(new Material.Settings("pet_coke"));
		LIGNITE_COKE = new Material(new Material.Settings("iignite_coke"));
		CINNABAR = new Material(new Material.Settings("cinnabar"));
		BORAX = new Material(new Material.Settings("borax"));
		CHLOROCALCITE = new Material(new Material.Settings("chlorocalcite"));
		MOLYSITE = new Material(new Material.Settings("molysite"));
		SODALITE = new Material(new Material.Settings("sodalite"));
		VOLCANIC = new Material(new Material.Settings("volcanic"));
		HEMATITE = new Material(new Material.Settings("hematite"));
		MALACHITE = new Material(new Material.Settings("malachite"));
		LIMESTONE = new Material(new Material.Settings("limestone"));
		SLAG = new Material(new Material.Settings("slag"));
		BAUXITE = new Material(new Material.Settings("bauxite"));
		CRYOLITE = new Material(new Material.Settings("cryolite"));

		LITHIUM = new Material(new Material.Settings("lithium"));
		SODIUM = new Material(new Material.Settings("sodium"));

		WHITE_PHOSPHORUS = new Material(new Material.Settings("white_phosphorus"));
		RED_PHOSPHORUS = new Material(new Material.Settings("red_phosphorus"));

		AUSTRALIUM = new Material(new Material.Settings("australium"));

		RARE_EARTH = new Material(new Material.Settings("rare_earth"));
		LANTHANUM = new Material(new Material.Settings("lanthanum"));
		ZIRCONIUM = new Material(new Material.Settings("zirconium"));
		NEODYMIUM = new Material(new Material.Settings("neodymium"));
		CERIUM = new Material(new Material.Settings("cerium"));

		IODINE = new Material(new Material.Settings("iodine"));
		ASTATINE = new Material(new Material.Settings("astatine"));
		CAESIUM = new Material(new Material.Settings("caesium"));
		STRONTIUM = new Material(new Material.Settings("strontium"));
		BROMINE = new Material(new Material.Settings("bromine"));
		TENNESSINE = new Material(new Material.Settings("tennessine"));

		STRONTIUM_90 = new Material(new Material.Settings("strontium_90"));
		IODINE_131 = new Material(new Material.Settings("iodine_131"));
		XENON_135 = new Material(new Material.Settings("xenon_135"));
		CAESIUM_137 = new Material(new Material.Settings("caesium_137"));
		ASTATINE_209 = new Material(new Material.Settings("astatine_209"));
	}
}
