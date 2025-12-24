package com.bazalbuilder.hbm.api.energy;

public interface Powerable {
	long getEnergy();

	long getMaximumEnergy();

	void setEnergy(long power);

	default boolean isDepleted() {
		return (getMaximumEnergy() - getEnergy()) <= 0;
	}
}
