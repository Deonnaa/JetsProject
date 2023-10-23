package com.skilldistillery.jets.entities;

public class CargoJet extends Jet implements CargoCarrier {

	public CargoJet(String model, double speedMph, int rangeMiles, long price) {
		super(model, speedMph, rangeMiles, price);
	}

	@Override
	public void fly() {
		System.out.println("CargoJet is flying...");
	}

	@Override
	public void loadCargo() {
		System.out.println("CargoJet is loading...");
	}
}
