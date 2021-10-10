package com.flightbooking.app.model.enumPacker;

public enum Gender {
MALE("M"),FEMALE("F");
	
	private final String choice;

	Gender(String choice) {
		this.choice=choice;
	}
	
	@Override
	public String toString() {
		return this.choice;
	}
}
