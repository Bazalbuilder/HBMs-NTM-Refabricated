package com.bazalbuilder.hbm.entity;

import com.bazalbuilder.hbm.util.RegistryUtils;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;

import static com.bazalbuilder.hbm.HbmMain.LOGGER;
import static com.bazalbuilder.hbm.HbmMain.MOD_ID;

public class LivingEntityAttributes {
	private static final Identifier RADIATION_IDENTIFIER = Identifier.of(MOD_ID, "attribute_radiation");
	private static final Identifier DIGAMMA_IDENTIFIER = Identifier.of(MOD_ID, "attribute_digamma");
	private static final Identifier ASBESTOS_IDENTIFIER = Identifier.of(MOD_ID, "attribute_asbestos");
	private static final Identifier BLACK_LUNG_IDENTIFIER = Identifier.of(MOD_ID, "attribute_blacklung");
	private static final Identifier RAD_ENV_IDENTIFIER = Identifier.of(MOD_ID, "attribute_radenv");
	private static final Identifier RAD_BUF_IDENTIFIER = Identifier.of(MOD_ID, "attribute_radbuf");
//	private static final Identifier BOMB_TIMER_IDENTIFIER = Identifier.of(MOD_ID, "attribute_bombtimer");
//	private static final Identifier CONTAGION_IDENTIFIER = Identifier.of(MOD_ID, "attribute_contagion");
//	private static final Identifier OIL_IDENTIFIER = Identifier.of(MOD_ID, "attribute_oil");
//	private static final Identifier FIRE_IDENTIFIER = Identifier.of(MOD_ID, "attribute_fire");
//	private static final Identifier PHOSPHOROUS_IDENTIFIER = Identifier.of(MOD_ID, "attribute_phosphorous");
//	private static final Identifier BALEFIRE_IDENTIFIER = Identifier.of(MOD_ID, "attribute_balefire");
//	private static final Identifier BLACK_FIRE_IDENTIFIER = Identifier.of(MOD_ID, "attribute_blackfire");

	private static final EntityAttribute RADIATION;
	private static final EntityAttribute DIGAMMA;
	private static final EntityAttribute ASBESTOS;
	private static final EntityAttribute BLACK_LUNG;
	private static final EntityAttribute RAD_ENV;
	private static final EntityAttribute RAD_BUF;
//	private static final EntityAttribute BOMB_TIMER;
//	private static final EntityAttribute CONTAGION;
//	private static final EntityAttribute OIL;
//	public static final EntityAttribute FIRE;
//	public static final EntityAttribute PHOSPHOROUS;
//	public static final EntityAttribute BALEFIRE;
//	public static final EntityAttribute BLACK_FIRE;

	public static void initilaize() {
		LOGGER.info("Initializing living entity attributes for mod \"{}\"", MOD_ID);
	}

	public static void register() {
		RegistryUtils.registerEntityAttribute(RADIATION_IDENTIFIER, RADIATION);
		RegistryUtils.registerEntityAttribute(DIGAMMA_IDENTIFIER, DIGAMMA);
		RegistryUtils.registerEntityAttribute(ASBESTOS_IDENTIFIER, ASBESTOS);
		RegistryUtils.registerEntityAttribute(BLACK_LUNG_IDENTIFIER, BLACK_LUNG);
		RegistryUtils.registerEntityAttribute(RAD_ENV_IDENTIFIER, RAD_ENV);
		RegistryUtils.registerEntityAttribute(RAD_BUF_IDENTIFIER, RAD_BUF);
//		RegistryUtils.registerEntityAttribute(BOMB_TIMER_IDENTIFIER, BOMB_TIMER);
//		RegistryUtils.registerEntityAttribute(CONTAGION_IDENTIFIER, CONTAGION);
//		RegistryUtils.registerEntityAttribute(OIL_IDENTIFIER, OIL);
//		RegistryUtils.registerEntityAttribute(FIRE_IDENTIFIER, FIRE);
//		RegistryUtils.registerEntityAttribute(PHOSPHOROUS_IDENTIFIER, PHOSPHOROUS);
//		RegistryUtils.registerEntityAttribute(BALEFIRE_IDENTIFIER, BALEFIRE);
//		RegistryUtils.registerEntityAttribute(BLACK_FIRE_IDENTIFIER, BLACK_FIRE);
	}

	static {
		RADIATION = new ClampedEntityAttribute(RADIATION_IDENTIFIER.toTranslationKey(), 0.0, 0.0, 2500.0);
		DIGAMMA = new ClampedEntityAttribute(DIGAMMA_IDENTIFIER.toTranslationKey(), 0.0, 0.0, 10.0);
		ASBESTOS = new ClampedEntityAttribute(ASBESTOS_IDENTIFIER.toTranslationKey(), 0.0, 0.0, 72000.0);
		BLACK_LUNG = new ClampedEntityAttribute(BLACK_LUNG_IDENTIFIER.toTranslationKey(), 0.0, 0.0, 144000.0);
		RAD_ENV = new ClampedEntityAttribute(RAD_ENV_IDENTIFIER.toTranslationKey(), 0.0, 0.0, 2500.0);
		RAD_BUF = new ClampedEntityAttribute(RAD_BUF_IDENTIFIER.toTranslationKey(), 0.0, 0.0, 2500.0);
//		BOMB_TIMER = new ClampedEntityAttribute(BOMB_TIMER_IDENTIFIER.toTranslationKey(), 0.0, );
//		CONTAGION = new ClampedEntityAttribute(CONTAGION_IDENTIFIER.toTranslationKey(), 0.0);
//		OIL = new ClampedEntityAttribute(OIL_IDENTIFIER.toTranslationKey(), 0.0);
//		FIRE = new ClampedEntityAttribute(FIRE_IDENTIFIER.toTranslationKey(), 0.0);
//		PHOSPHOROUS = new ClampedEntityAttribute(PHOSPHOROUS_IDENTIFIER.toTranslationKey(), 0.0);
//		BALEFIRE = new ClampedEntityAttribute(BALEFIRE_IDENTIFIER.toTranslationKey(), 0.0);
//		BLACK_FIRE = new ClampedEntityAttribute(BLACK_FIRE_IDENTIFIER.toTranslationKey(), 0.0);

		register();
	}
}
