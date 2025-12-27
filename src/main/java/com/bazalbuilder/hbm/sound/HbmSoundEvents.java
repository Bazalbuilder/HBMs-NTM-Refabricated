package com.bazalbuilder.hbm.sound;

import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class HbmSoundEvents {
	public static final String TECH_BOOP_PATH = "tool/tech_boop";
	public static final String NUCLEAR_EXPLOSION_PATH = "weapon/nuclear_explosion";

	public static final SoundEvent TECH_BOOP;
	public static final SoundEvent NUCLEAR_EXPLOSION;

	public static void initialize() {
		LOGGER.info("Registering sounds for mod \"{}\"", MOD_ID);
	}

	private static void registerAll() {
		RegistryUtils.registerSoundEvent(TECH_BOOP_PATH, TECH_BOOP);
		RegistryUtils.registerSoundEvent(NUCLEAR_EXPLOSION_PATH, NUCLEAR_EXPLOSION);
	}

	static {
		TECH_BOOP = SoundEvent.of(Identifier.of(MOD_ID, TECH_BOOP_PATH));
		NUCLEAR_EXPLOSION = SoundEvent.of(Identifier.of(MOD_ID, NUCLEAR_EXPLOSION_PATH));

		registerAll();
	}
}
