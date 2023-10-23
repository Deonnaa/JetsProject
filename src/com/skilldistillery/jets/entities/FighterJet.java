package com.skilldistillery.jets.entities;

public class FighterJet extends Jet implements CombatReady {

	public FighterJet(String model, double speedMph, int rangeMiles, long price) {
		super(model, speedMph, rangeMiles, price);
	}

	@Override
	public void fly() {
		System.out.println("FighterJet is flying...");
	}

	@Override
	public void shootTarget() {
		System.out.println("FighterJet is shooting...");

	}
}
