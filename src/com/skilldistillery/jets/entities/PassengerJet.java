package com.skilldistillery.jets.entities;

public class PassengerJet extends Jet {

	public PassengerJet(String model, double speedMph, int rangeMiles, long price) {
		super(model, speedMph, rangeMiles, price);
	}

	@Override
	public void fly() {
		System.out.println("PassengerJet is flying...");
	}


}
