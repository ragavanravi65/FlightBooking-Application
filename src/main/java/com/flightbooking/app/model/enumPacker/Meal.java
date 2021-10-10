package com.flightbooking.app.model.enumPacker;

public enum Meal {
	VEG("VEG"),
	NONVEG("NON-VEG");

	private final String choice;

	Meal(String choice) {
		this.choice=choice;
	}
	
	@Override
	public String toString() {
		return this.choice;
	}
}
