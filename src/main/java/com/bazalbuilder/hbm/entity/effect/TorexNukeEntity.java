/*
 * Copyright (c) 2025 Bazalbuilder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.bazalbuilder.hbm.entity.effect;

import com.bazalbuilder.hbm.entity.HbmEntityTypes;
import com.bazalbuilder.hbm.sound.HbmSoundEvents;
import com.bazalbuilder.hbm.util.MathUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TorexNukeEntity extends Entity {
	private static final TrackedData<Float> SCALE = DataTracker.registerData(TorexNukeEntity.class, TrackedDataHandlerRegistry.FLOAT);
	private static final TrackedData<Integer> VARIANT = DataTracker.registerData(TorexNukeEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public double coreHeight = 3;
	public double convectionHeight = 3;
	public double torusWidth = 3;
	public double rollerSize = 1;
	public double heat = 1;
	public double lastSpawnY = -65;
	public List<Cloudlet> cloudlets = new ArrayList<>();

	public boolean soundPlayed = false;
	public boolean cameraShook = false;

	public TorexNukeEntity(EntityType<? extends TorexNukeEntity> type, World world) {
		super(type, world);
		this.ignoreCameraFrustum = true;
		this.setInvulnerable(true);
		this.noClip = true;
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
		builder.add(SCALE, 1.0f);
		builder.add(VARIANT, 0);
	}

	@Override
	public void tick() {
		super.tick();

		double scale = 1.5;
		double cloudletScale = 1.5;
		int maxAge = this.getMaxAge();

		if (getWorld().isClient) {
			if (age == 1)
				this.setScale((float) scale);

			if (lastSpawnY == -1)
				lastSpawnY = getY() - 3;

			if (age < 100)
				this.getWorld().setLightningTicksLeft(3);

			int spawnTarget = Math.max(getWorld().getTopY(Heightmap.Type.WORLD_SURFACE, (int) getX(), (int) getZ()) - 3, 1);
			double moveSpeed = 0.5d;

			if (Math.abs(spawnTarget - lastSpawnY) < moveSpeed) {
				lastSpawnY = spawnTarget;
			} else {
				lastSpawnY += moveSpeed * Math.signum(spawnTarget - lastSpawnY);
			}

			// mushroom clouds
			double range = (torusWidth - rollerSize) * 0.25;
			double simSpeed = getSimulationSpeed();
			int toSpawn = (int) Math.ceil(10 * simSpeed * simSpeed);
			int lifecycle = Math.min((age * age) + 200, maxAge - age + 200);

			for (int i = 0; i < toSpawn; i++) {
				double x = getX() + random.nextGaussian() * range;
				double z = getZ() + random.nextGaussian() * range;
				Cloudlet cloud = new Cloudlet(
					x,
					lastSpawnY,
					z,
					(float) (random.nextDouble() * 2.0d * Math.PI),
					0,
					lifecycle
				);
				cloud.setScale(1.0f + this.age * 0.005f * (float) cloudletScale, 5f * (float) cloudletScale);
				cloudlets.add(cloud);
			}

			// shock wave clouds
			if (age < 150) {
				int cloudCount = age * 5;
				int shockLife = Math.max(300 - age * 20, 50);

				for (int i = 0; i < cloudCount; i++) {
					float rotation = (float) (Math.PI * 2 * random.nextDouble());
					Vec3d vec = new Vec3d((age * 1.5 + random.nextDouble()) * 1.5, 0, 0);
					vec = vec.rotateY(rotation);
					Cloudlet cloudlet = new Cloudlet(
						vec.x + getX(),
						getWorld().getTopY(Heightmap.Type.WORLD_SURFACE, (int) (vec.x + getX()), (int) (vec.z + getZ())),
						vec.z + getZ(),
						rotation,
						0,
						shockLife,
						Type.SHOCKWAVE
					).setScale(7.0f, 2.0f).setMotion(age > 15 ? 0.75 : 0);
					cloudlets.add(cloudlet);
				}

				if (!soundPlayed) {
					PlayerEntity playerEntity = getWorld().getClosestPlayer(this, 128d);

					if (playerEntity != null && playerEntity.distanceTo(this) < (age * 1.5 + 1) * 1.5) {
						playerEntity.playSoundToPlayer(HbmSoundEvents.NUCLEAR_EXPLOSION, SoundCategory.AMBIENT, 10_000.0f, 1.0f);
						soundPlayed = true;
					}
				}
			}

			// ring clouds
			if (age < 130 * scale) {
				lifecycle *= (int) scale;
				for (int i = 0; i < 2; i++) {
					Cloudlet cloud = new Cloudlet(
						getX(),
						getY() + coreHeight,
						getZ(),
						(float) (random.nextDouble() * 2d * Math.PI),
						0,
						lifecycle,
						Type.RING
					);
					cloud.setScale(1f + this.age * 0.0025f * (float) (cloudletScale * cloudletScale), 3f * (float) (cloudletScale * cloudletScale));
					cloudlets.add(cloud);
				}
			}

			// condensation clouds
			if (age > 130 * scale && age < 600 * scale) {
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 4; j++) {
						float angle = (float) (Math.PI * 2 * random.nextDouble());
						Vec3d vec = new Vec3d(torusWidth + rollerSize * (5 + random.nextDouble()), 0, 0);
						vec = vec.rotateZ((float) (Math.PI / 45 * j));
						vec = vec.rotateY(angle);

						Cloudlet cloud = new Cloudlet(
							getX() + vec.x,
							getY() + coreHeight - 5 + j * scale,
							getZ() + vec.z,
							angle,
							0,
							(int) ((20 + (double) age / 10) * (1 + random.nextDouble() * 0.1)),
							Type.CONDENSATION
						);
						cloud.setScale(0.125f * (float) (cloudletScale), 3.0f * (float) (cloudletScale));
						cloudlets.add(cloud);
					}
				}
			}

			// more condensation clouds
			if (age > 200 * scale && age < 600 * scale) {
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 4; j++) {
						float angle = (float) (Math.PI * 2 * random.nextDouble());
						Vec3d vec = new Vec3d(torusWidth + rollerSize * (3 + random.nextDouble() * 0.5), 0, 0);
						vec = vec.rotateZ((float) (Math.PI / 45 * j));
						vec = vec.rotateY(angle);

						Cloudlet cloud = new Cloudlet(
							getX() + vec.x,
							getY() + coreHeight + 25 + j * cloudletScale,
							getZ() + vec.z,
							angle,
							0,
							(int) ((20 + (double) age / 10) * (1 + random.nextDouble() * 0.1)),
							Type.CONDENSATION
						);
						cloud.setScale(0.125f * (float) cloudletScale, 3.0f * (float) cloudletScale);
						cloudlets.add(cloud);
					}
				}
			}

			for (Cloudlet cloudlet : cloudlets) {
				cloudlet.update();
			}

			coreHeight += 0.15 / scale;
			torusWidth += 0.05 / scale;
			rollerSize = torusWidth * 0.35;
			convectionHeight = coreHeight + rollerSize;

			int maxHeat = (int) (50 * cloudletScale);
			heat = maxHeat - Math.pow((double) (maxHeat * this.age) / maxAge, 1);

			cloudlets.removeIf(cloudlet -> cloudlet.isDead);
		}

		if (this.age > maxAge) {
			this.discard();
		}
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {
	}

	@Override
	public boolean shouldRender(double distance) {
		return true;
	}

	public TorexNukeEntity setScale(float scale) {
		if (!getWorld().isClient)
			dataTracker.set(SCALE, scale);

		this.coreHeight = this.coreHeight / 1.5d * scale;
		this.convectionHeight = this.convectionHeight / 1.5d * scale;
		this.torusWidth = this.torusWidth /  1.5d * scale;
		this.rollerSize = this.rollerSize / 1.5d * scale;
		return this;
	}

	public void setVariant(int variant) {
		dataTracker.set(VARIANT, variant);
	}

	public double getSimulationSpeed() {
		int lifetime = getMaxAge();
		int simSlow = lifetime / 4;
		int simStop = lifetime / 2;
		int tick = TorexNukeEntity.this.age;

		if (tick > simStop) {
			return 0.0d;
		} else if (tick > simSlow) {
			return 1.0d - ((double) (tick - simSlow) / (simStop - simSlow));
		}

		return 1.0d;
	}

	public double getScale() {
		return dataTracker.get(SCALE);
	}

	public double getGreying() {
		int lifetime = getMaxAge();
		int greying = lifetime * 3 / 4;

		if (age > greying) {
			return 1d + ((double) (age - greying) / (lifetime - greying));
		}

		return 1d;
	}

	public float getAlpha() {
		int lifetime = getMaxAge();
		int fadeOut = lifetime * 3 / 4;
		int tick = this.age;

		if (tick > fadeOut) {
			return 1.0f - ((float) (tick - fadeOut) / (lifetime - fadeOut));
		}

		return 1.0f;
	}

	public int getMaxAge() {
		return (int) (45 * 20 * this.getScale());
	}

	public static void statFacStandard(World world, double x, double y, double z, float scale) {
		statFac(world, x, y, z, scale, 0);
	}

	public static void statFacBalefire(World world, double x, double y, double z, float scale) {
		statFac(world, x, y, z, scale, 1);
	}

	public static void statFac(World world, double x, double y, double z, float scale, int variant) {
		TorexNukeEntity torexEntity = new TorexNukeEntity(HbmEntityTypes.TOREX_ENTITY, world).setScale(MathHelper.clamp((float) MathUtils.squirt(scale * 0.01) * 1.5f, 0.5f, 5f));
		torexEntity.setVariant(variant);
		torexEntity.setPos(x, y, z);
		world.spawnEntity(torexEntity);

	}

	public class Cloudlet {
		private float startingScale = 1.0f;
		private float growingScale = 5.0f;
		private double motionMultiplier = 1.0d;

		public double x, y, z;
		public double prevX, prevY, prevZ;
		public double motionX, motionY, motionZ;
		public int age;
		public int cloudletLife;
		public float angle;
		public boolean isDead = false;
		public float rangeMod;
		public float colorMod;
		public Vec3d color;
		public Vec3d prevColor;
		public Type type;

		public Cloudlet(double posX, double posY, double posZ, float angle, int age, int cloudletLife, Type type) {
			this.x = posX;
			this.y = posY;
			this.z = posZ;
			this.age = age;
			this.cloudletLife = cloudletLife;
			this.angle = angle;
			this.rangeMod = 0.3f + TorexNukeEntity.this.random.nextFloat() * 0.7f;
			this.colorMod = 0.8f + TorexNukeEntity.this.random.nextFloat() * 0.2f;
			this.type = type;

			this.updateColor();
		}

		public Cloudlet(double posX, double posY, double posZ, float angle, int age, int cloudletLife) {
			this(posX, posY, posZ, angle, age, cloudletLife, Type.STANDARD);
		}

		private void update() {
			age++;

			if (age > cloudletLife)
				this.isDead = true;

			this.prevX = this.x;
			this.prevY = this.y;
			this.prevZ = this.z;

			Vec3d simPos = new Vec3d(
				TorexNukeEntity.this.getX() - this.x,
				0,
				TorexNukeEntity.this.getZ() - this.z
			);
			double simPosX = TorexNukeEntity.this.getX() + simPos.length();
			double simPosZ = TorexNukeEntity.this.getZ();

			if (this.type == Type.STANDARD) {
				Vec3d convection = getConvectionMotion(simPosX, simPosZ);
				Vec3d lift = getLiftMotion(simPosX);

				double factor = MathHelper.clamp((this.y - TorexNukeEntity.this.getY()) / TorexNukeEntity.this.coreHeight, 0, 1);
				this.motionX = convection.x * factor + lift.x * (1.0d - factor);
				this.motionY = convection.y * factor + lift.y * (1.0d - factor);
				this.motionZ = convection.z * factor + lift.z * (1.0d - factor);
			} else if (this.type == Type.SHOCKWAVE) {
				double factor = MathHelper.clamp((this.y - TorexNukeEntity.this.getY()) / TorexNukeEntity.this.coreHeight, 0, 1);
				Vec3d motion = new Vec3d(1, 0,0).rotateY(this.angle);

				this.motionX = motion.x * factor;
				this.motionY = motion.y * factor;
				this.motionZ = motion.z * factor;
			} else if (this.type == Type.RING) {
				Vec3d motion = getRingMotion(simPosX, simPosZ);

				this.motionX = motion.x;
				this.motionY = motion.y;
				this.motionZ = motion.z;
			} else if (this.type == Type.CONDENSATION) {
				Vec3d motion = getCondensationMotion();

				this.motionX = motion.x;
				this.motionY = motion.y;
				this.motionZ = motion.z;
			}

			double multiplier = this.motionMultiplier * getSimulationSpeed();

			this.x += this.motionX * multiplier;
			this.y += this.motionY * multiplier;
			this.z += this.motionZ * multiplier;

			this.updateColor();
		}

		private Vec3d getCondensationMotion() {
			Vec3d delta = new Vec3d(x - TorexNukeEntity.this.getX(), 0, z - TorexNukeEntity.this.getZ());
			double speed = 0.00002d * TorexNukeEntity.this.age;
			return delta.multiply(speed);
		}

		private Vec3d getRingMotion(double simPosX, double simPosZ) {
			if (simPosX > TorexNukeEntity.this.getX() + torusWidth * 2)
				return Vec3d.ZERO;

			Vec3d torusPos = new Vec3d(
				TorexNukeEntity.this.getX() + torusWidth,
				TorexNukeEntity.this.getY() + coreHeight * 0.5,
				TorexNukeEntity.this.getZ()
			);

			Vec3d delta = new Vec3d(
				torusPos.x - simPosX,
				torusPos.y - this.y,
				torusPos.z - simPosZ
			);

			double roller = TorexNukeEntity.this.rollerSize * this.rangeMod * 0.25d;
			double distance = delta.length() / roller - 1d;

			double func = 1d - Math.pow(Math.E, -distance); // [0;1]
			float angle = (float) (func * Math.PI * 0.5d); // [0;90*]

			Vec3d rot = new Vec3d(
				-delta.x / distance,
				-delta.y / distance,
				-delta.z / distance
			);
			rot = rot.rotateZ(angle);

			Vec3d motion = new Vec3d(
				torusPos.x + rot.x - simPosX,
				torusPos.y + rot.y - this.y,
				torusPos.z + rot.z - simPosZ
			);

			double speed = 0.001d;
			motion = new Vec3d(motion.x * speed, motion.y * speed, motion.z * speed);
			motion = motion.normalize();

			motion = motion.rotateY(this.angle);

			return motion;
		}

		private Vec3d getConvectionMotion(double simPosX, double simPosZ) {

			Vec3d torusPos = new Vec3d(
				TorexNukeEntity.this.getX() + torusWidth,
				TorexNukeEntity.this.getY() + coreHeight,
				TorexNukeEntity.this.getZ()
			);

			Vec3d delta = new Vec3d(
				torusPos.x - simPosX,
				torusPos.y - this.y,
				torusPos.z - simPosZ
			);

			double roller = TorexNukeEntity.this.rollerSize * this.rangeMod;
			double distance = delta.length() / roller - 1d;

			double func = 1d - Math.pow(Math.E, -distance); // [0;1]
			float angle = (float) (func * Math.PI * 0.5d); // [0;90*]

			Vec3d rot = new Vec3d(-delta.x / distance, -delta.y / distance, -delta.z / distance);
			rot = rot.rotateZ(angle);

			Vec3d motion = new Vec3d(
				torusPos.x + rot.x - simPosX,
				torusPos.y + rot.y - this.y,
				torusPos.z + rot.z - simPosZ
			);

			motion = motion.normalize();
			motion = motion.rotateY(this.angle);

			return motion;
		}

		private Vec3d getLiftMotion(double simPosX) {
			double scale = MathHelper.clamp(1d - (simPosX - (TorexNukeEntity.this.getX() + torusWidth)), 0, 1);

			return new Vec3d(
				TorexNukeEntity.this.getX() - this.x,
				(TorexNukeEntity.this.getY() + convectionHeight) - this.y,
				TorexNukeEntity.this.getZ() - this.z
			).normalize().multiply(scale);
		}

		private void updateColor() {
			this.prevColor = this.color;

			double exX = TorexNukeEntity.this.getX();
			double exY = TorexNukeEntity.this.getY() + TorexNukeEntity.this.coreHeight;
			double exZ = TorexNukeEntity.this.getZ();

			double distX = exX - this.x;
			double distY = exY - this.y;
			double distZ = exZ - this.z;

			double distSq = (distX * distX) + (distY * distY) + (distZ + distZ);
			distSq /= TorexNukeEntity.this.heat;
			double dist = Math.sqrt(distSq);

			dist = Math.max(dist, 1);
			double col = 2d / dist;

			int variant = TorexNukeEntity.this.dataTracker.get(VARIANT);

			if (variant == 1) {
				this.color = new Vec3d(
					Math.max(col * 1, 0.25),
					Math.max(col * 2, 0.25),
					Math.max(col * 0.5, 0.25)
				);
			} else if (variant == 2) {
				Color color = Color.getHSBColor(this.angle / 2.0f / (float) Math.PI, 1f, 1f);
				if (this.type == Type.RING) {
					this.color = new Vec3d(
						Math.max(col * 1, 0.25),
						Math.max(col * 1, 0.25),
						Math.max(col * 1, 0.25)
					);
				} else {
					this.color = new Vec3d(
						color.getRed() / 255d,
						color.getGreen() / 255d,
						color.getBlue() / 255d
					);
				}
			} else {
				this.color = new Vec3d(
					Math.max(col * 2, 0.25),
					Math.max(col * 1.5, 0.25),
					Math.max(col * 0.5, 0.25)
				);
			}
		}

		public Vec3d getLerpPos(float delta) {
			float scale = (float) TorexNukeEntity.this.getScale();
			Vec3d base = new Vec3d(
				prevX + (x - prevX) * delta,
				prevY + (y - prevY) * delta,
				prevZ + (z - prevZ) * delta
			);

			if (this.type != Type.SHOCKWAVE) {
				base = new Vec3d(
					((base.x) - TorexNukeEntity.this.getX()) * scale + TorexNukeEntity.this.getX(),
					((base.y) - TorexNukeEntity.this.getY()) * scale + TorexNukeEntity.this.getY(),
					((base.z) - TorexNukeEntity.this.getZ()) * scale + TorexNukeEntity.this.getZ()
				);
			}

			return base;
		}

		public Vec3d getLerpColor(float delta) {
			if (this.type == Type.CONDENSATION)
				return new Vec3d(1.0f, 1.0f, 1.0f);

			double greying = TorexNukeEntity.this.getGreying();

			if (this.type == Type.RING)
				greying += 1;

			return new Vec3d(
				(prevColor.x + (color.x - prevColor.x) * delta) * greying,
				(prevColor.y + (color.y - prevColor.y) * delta) * greying,
				(prevColor.z + (color.z - prevColor.z) * delta) * greying
			);
		}

		public float getAlpha() {
			float alpha = (1f - ((float) age / (float) cloudletLife)) * TorexNukeEntity.this.getAlpha();

			if (this.type == Type.CONDENSATION)
				alpha *= 0.25f;

			return alpha;
		}

		public float getScale() {
			float base = startingScale + ((float) age / (float) cloudletLife) * growingScale;

			if (this.type != Type.SHOCKWAVE)
				base *= (float) TorexNukeEntity.this.getScale();

			return base;
		}

		public Cloudlet setScale(float start, float grow) {
			this.startingScale = start;
			this.growingScale = grow;
			return this;
		}

		public Cloudlet setMotion(double multiplier) {
			this.motionMultiplier = multiplier;
			return this;
		}
	}

	public enum Type {
		STANDARD,
		SHOCKWAVE,
		RING,
		CONDENSATION,
	}
}
