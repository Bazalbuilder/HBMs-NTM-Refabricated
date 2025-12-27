package com.bazalbuilder.hbm.entity;

import com.bazalbuilder.hbm.entity.effect.TorexNukeEntity;
import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmEntityTypes {
	public static final EntityType<TorexNukeEntity> TOREX_ENTITY;

	public static void initialize() {
		LOGGER.info("Registering entity types for mod \"{}\"", MOD_ID);

	}

	private static void registerAll() {
		RegistryUtils.registerEntityType("torex_entity", TOREX_ENTITY);
	}

	static {
		TOREX_ENTITY = EntityType.Builder.create(TorexNukeEntity::new, SpawnGroup.MISC).maxTrackingRange(1000).dimensions(1f, 50f).makeFireImmune().build();

		registerAll();
	}
}
