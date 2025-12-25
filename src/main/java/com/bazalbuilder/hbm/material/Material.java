package com.bazalbuilder.hbm.material;


import com.bazalbuilder.hbm.util.RegistryUtils;

import java.util.HashSet;
import java.util.Set;

public class Material {
	public Settings settings;

	public Material(Settings settings) {
		this.settings = settings;
	}

	public static class Settings {
		public Set<MaterialShapes> materialsToRegister;
		public final String name;

		public Settings(String name) {
			this.name = name;
			this.materialsToRegister = new HashSet<>();
		}

		public Settings nugget() {
			this.materialsToRegister.add(MaterialShapes.NUGGET);
			return this;
		}
		public Settings tiny() {
			this.materialsToRegister.add(MaterialShapes.TINY);
			return this;
		}
		public Settings bolt() {
			this.materialsToRegister.add(MaterialShapes.BOLT);
			return this;
		}
		public Settings ingot() {
			this.materialsToRegister.add(MaterialShapes.INGOT);
			return this;
		}
		public Settings dustTiny() {
			this.materialsToRegister.add(MaterialShapes.DUST_TINY);
			return this;
		}
		public Settings dust() {
			this.materialsToRegister.add(MaterialShapes.DUST);
			return this;
		}
		public Settings gem() {
			this.materialsToRegister.add(MaterialShapes.GEM);
			return this;
		}
		public Settings crystal() {
			this.materialsToRegister.add(MaterialShapes.CRYSTAL);
			return this;
		}
		public Settings plate() {
			this.materialsToRegister.add(MaterialShapes.PLATE);
			return this;
		}
		public Settings castedPlate() {
			this.materialsToRegister.add(MaterialShapes.CAST_PLATE);
			return this;
		}
		public Settings weldedPlate() {
			this.materialsToRegister.add(MaterialShapes.WELDED_PLATE);
			return this;
		}
		public Settings wire() {
			this.materialsToRegister.add(MaterialShapes.WIRE);
			return this;
		}
		public Settings denseWire() {
			this.materialsToRegister.add(MaterialShapes.DENSE_WIRE);
			return this;
		}
		public Settings billet() {
			this.materialsToRegister.add(MaterialShapes.BILLET);
			return this;
		}
		public Settings block() {
			this.materialsToRegister.add(MaterialShapes.BLOCK);
			return this;
		}
		public Settings ore() {
			this.materialsToRegister.add(MaterialShapes.ORE);
			return this;
		}
		public Settings lightBarrel() {
			this.materialsToRegister.add(MaterialShapes.LIGHT_BARREL);
			return this;
		}
		public Settings heavyBarrel() {
			this.materialsToRegister.add(MaterialShapes.HEAVY_BARREL);
			return this;
		}
		public Settings lightReceiver() {
			this.materialsToRegister.add(MaterialShapes.LIGHT_RECEIVER);
			return this;
		}
		public Settings heavyReceiver() {
			this.materialsToRegister.add(MaterialShapes.HEAVY_RECEIVER);
			return this;
		}
		public Settings mechanism() {
			this.materialsToRegister.add(MaterialShapes.MECHANISM);
			return this;
		}
		public Settings stock() {
			this.materialsToRegister.add(MaterialShapes.STOCK);
			return this;
		}
		public Settings grip() {
			this.materialsToRegister.add(MaterialShapes.GRIP);
			return this;
		}
	}
}
