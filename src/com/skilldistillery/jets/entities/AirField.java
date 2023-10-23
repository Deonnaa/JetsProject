package com.skilldistillery.jets.entities;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	private List<Jet> fleet;

	public AirField() {
		fleet = new ArrayList<>();
	}

	public void addJet(Jet jet) {
		fleet.add(jet);
	}

	public List<Jet> getFleet() {
		return fleet;
	}
}
