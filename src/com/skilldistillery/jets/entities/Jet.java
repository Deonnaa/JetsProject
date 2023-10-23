package com.skilldistillery.jets.entities;

public abstract class Jet {
	private String model;
	private double speedMph;
	private int rangeMiles;
	private long price;
	private double flightTime;

	public Jet() {

	}

	public Jet(String model, double speedMph, int rangeMiles, long price) {
		this.model = model;
		this.speedMph = speedMph;
		this.rangeMiles = rangeMiles;
		this.price = price;
	}

	public abstract void fly();

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speedMph;
	}

	public void setSpeed(double speedMph) {
		this.speedMph = speedMph;
	}

	public int getRange() {
		return rangeMiles;
	}

	public void setRange(int rangeMiles) {
		this.rangeMiles = rangeMiles;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[Jet Model: " + model + ", Speed: " + speedMph + "mph, Range: " + rangeMiles + "miles, Price: $" + price
				+ "]";
	}

}
