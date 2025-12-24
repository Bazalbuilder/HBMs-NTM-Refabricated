package com.bazalbuilder.hbm.item.tool;

import com.bazalbuilder.hbm.sound.HbmSounds;
import com.bazalbuilder.hbm.util.HazardUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GeigerCounterItem extends Item {
	public GeigerCounterItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient) {
			world.playSound(user, user.getX(), user.getY(), user.getZ(), HbmSounds.ITEM_TECH_BOOP, SoundCategory.PLAYERS, 1.0f, 1.0f);
			HazardUtils.printGeigerData(user);
			return TypedActionResult.success(this.asItem().getDefaultStack());
		}

		return TypedActionResult.pass(this.asItem().getDefaultStack());
	}
}
