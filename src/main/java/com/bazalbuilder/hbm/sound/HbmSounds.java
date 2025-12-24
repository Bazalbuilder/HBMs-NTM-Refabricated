package com.bazalbuilder.hbm.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmSounds {
	private static final RegistryKey<SoundEvent> REGISTRY_KEY_ITEM_TECH_BOOP;

	public static final SoundEvent ITEM_TECH_BOOP;

	public static void initialize() {
		LOGGER.info("Registering sounds for mod \"{}\"", MOD_ID);
	}

	static {
		REGISTRY_KEY_ITEM_TECH_BOOP = RegistryKey.of(RegistryKeys.SOUND_EVENT, Identifier.of(MOD_ID, "tool/tech_boop"));

		ITEM_TECH_BOOP = Registry.register(Registries.SOUND_EVENT, REGISTRY_KEY_ITEM_TECH_BOOP, SoundEvent.of(Identifier.of(MOD_ID, "tool/tech_boop")));
	}
}
