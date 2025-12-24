package com.bazalbuilder.hbm.util;

import com.bazalbuilder.hbm.event.HazardEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class HazardUtils {
	private static Formatting getFormattingFromRad(double rad) {
		if (rad == 0)
			return Formatting.GREEN;
		else if (rad < 1)
			return Formatting.YELLOW;
		else if (rad < 10)
			return Formatting.GOLD;
		else if (rad < 100)
			return Formatting.RED;
		else if (rad < 500)
			return Formatting.DARK_RED;
		else if (rad < 1000)
			return Formatting.DARK_PURPLE;
		else
			return Formatting.DARK_GRAY;
	}

	public static void printGeigerData(PlayerEntity player) {
		World world = player.getWorld();

		double exposure = 0d;

		double radiation = HazardEvents.radiationProxy.getRadiation(world, player.getBlockPos()) / 10d;
		double envRadiation = 0d;

		double resistance = 0d;
		double resCoefficient = 0d;

		String chunkPrefix = "" + getFormattingFromRad(radiation);
		String envPrefix = "" + getFormattingFromRad(envRadiation);
		String radPrefix = "";
		String resPrefix = "" + Formatting.WHITE;

		if (exposure < 100)
			radPrefix += Formatting.GREEN;
		else if (exposure < 200)
			radPrefix += Formatting.YELLOW;
		else if (exposure < 400)
			radPrefix += Formatting.GOLD;
		else if (exposure < 600)
			radPrefix += Formatting.DARK_RED;
		else if (exposure < 800)
			radPrefix += Formatting.DARK_RED;
		else if (exposure < 1000)
			radPrefix += Formatting.DARK_PURPLE;
		else
			radPrefix += Formatting.DARK_GRAY;

		player.sendMessage(Text.literal("===== ☢ ").append(Text.translatable("geiger.title")).append(" ☢ =====").formatted(Formatting.GOLD));
		player.sendMessage(Text.translatable("geiger.chunkRad").append(Text.literal(" " + chunkPrefix + radiation + " RAD/s")).formatted(Formatting.YELLOW));
		player.sendMessage(Text.translatable("geiger.envRad").append(Text.literal(" " + envPrefix + radiation + " RAD/s")).formatted(Formatting.YELLOW));
		player.sendMessage(Text.translatable("geiger.playerRad").append(Text.literal(" " + radPrefix + exposure + " RAD/s")).formatted(Formatting.YELLOW));
		player.sendMessage(Text.translatable("geiger.playerRes").append(Text.literal(" " + resPrefix + resistance + "% (" + resCoefficient + ")")).formatted(Formatting.YELLOW));
	}
}
