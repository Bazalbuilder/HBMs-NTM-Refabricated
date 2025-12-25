package com.bazalbuilder.hbm.api.energy.v0;

public interface Powerable {
	long getMaximumPower();
	long getPower();
	void setPower(long power);
}
