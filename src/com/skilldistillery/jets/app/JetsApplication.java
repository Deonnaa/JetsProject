package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.skilldistillery.jets.entities.CargoJet;
import com.skilldistillery.jets.entities.FighterJet;
import com.skilldistillery.jets.entities.Jet;
import com.skilldistillery.jets.entities.PassengerJet;

public class JetsApplication {
	Scanner scanner = new Scanner(System.in);
	private List<Jet> fleet = new ArrayList<>();

	public static void main(String[] args) {
		JetsApplication jA = new JetsApplication();
		jA.run();
	}

	public void run() {
		displayMenu(fleet);
	}

	public void displayMenu(List<Jet> fleet) {
		boolean quit = false;

		while (!quit) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃         Choose a Number          ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃  1: List Fleet                   ┃");
			System.out.println("┃  2: Fly All Jets                 ┃");
			System.out.println("┃  3: View Fastest Jet             ┃");
			System.out.println("┃  4: View Jet with Longest Range  ┃");
			System.out.println("┃  5: Load All Cargo Jets          ┃");
			System.out.println("┃  6: Dogfight!                    ┃");
			System.out.println("┃  7: Add a Jet to Fleet           ┃");
			System.out.println("┃  8: Remove a Jet from Fleet      ┃");
			System.out.println("┃  9: Quit                         ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.print("  Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			switch (choice) {
			case 1:
				String fileName = "jetnames.txt";
				readJetsFromFile(fileName);
				printFleet(fleet);
				break;
			case 2:
				flyAllJets(fleet);
				break;
			case 3:
				viewFastestJet(fleet);
				break;
			case 4:
				viewLongestRangeJet(fleet);
				break;
			case 5:
				loadAllCargoJets(fleet);
				break;
			case 6:
				dogfight(fleet);
				break;
			case 7:
				addJetToTheFleet(fleet);
				break;
			case 8:
				removeJetFromTheFleet(fleet);
				break;
			case 9:
				quit = true;
				System.out.println("Thanks for Flying with Us!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public void printFleet(List<Jet> fleet) {
		System.out.println("Fleet:");

		// Create a set to keep track of printed jets
		Set<Jet> printedJets = new HashSet<>();

		for (Jet jet : fleet) {
			// Check if the jet has already been printed
			if (!printedJets.contains(jet)) {
				System.out.println(jet);
				printedJets.add(jet);
			}
		}
	}

	private void readJetsFromFile(String fileName) {
		try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				String[] jetRecord = line.split(",");
				String typeOfJet = jetRecord[0];
				String model = jetRecord[1];
				double speedMPH = Double.parseDouble(jetRecord[2]);
				int range = Integer.parseInt(jetRecord[3]);
				long price = Long.parseLong(jetRecord[4]);

				Jet jet = null;

				// Determine the type of jet and create an instance of the appropriate subclass
				if ("CargoJet".equalsIgnoreCase(typeOfJet)) {
					jet = new CargoJet(model, speedMPH, range, price);
				} else if ("FighterJet".equalsIgnoreCase(typeOfJet)) {
					jet = new FighterJet(model, speedMPH, range, price);
				} else if ("PassengerJet".equalsIgnoreCase(typeOfJet)) {
					jet = new PassengerJet(model, speedMPH, range, price);
				}

				if (jet != null) {
					fleet.add(jet); // Add the jet to your fleet
//					System.out.println(jet);
//					System.out.println(); // Add a line break between jets
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void flyAllJets(List<Jet> fleet) {

		for (Jet jet : fleet) {
			System.out.println("Jet Details: ");
			System.out.println(jet);
			System.out.printf("Flight Time: %.2f%n", (double) jet.getRange() / jet.getSpeed());

			if (jet instanceof CargoJet) {
				((CargoJet) jet).fly(); // Call CargoJet's fly() method
			} else if (jet instanceof FighterJet) {
				((FighterJet) jet).fly(); // Call FighterJet's fly() method
			} else if (jet instanceof PassengerJet) {
				((PassengerJet) jet).fly(); // Call PassengerJet's fly() method
			}
			System.out.println(); // Add a line break between jets
		}
	}

	public void viewFastestJet(List<Jet> fleet) {
		if (fleet.isEmpty()) {
			System.out.println("There are no jets in the fleet.");
			return;
		}

		Jet fastestJet = fleet.get(0); // Initialize with the first jet in the fleet

		// Find the fastest jet by comparing speeds using a for loop
		for (int i = 1; i < fleet.size(); i++) {
			Jet currentJet = fleet.get(i);
			if (currentJet.getSpeed() > fastestJet.getSpeed()) {
				fastestJet = currentJet;
			}
		}
		System.out.println("Fastest Jet Details:");
		System.out.println(fastestJet);
	}

	public void viewLongestRangeJet(List<Jet> fleet) {
		if (fleet.isEmpty()) {
			System.out.println("There are no jets in the fleet.");
			return;
		}

		Jet longestRangeJet = fleet.get(0); // Initialize with the first jet in the fleet

		// Find the longest range jet by comparing speeds using a for loop
		for (int i = 1; i < fleet.size(); i++) {
			Jet currentJet = fleet.get(i);
			if (currentJet.getRange() > longestRangeJet.getRange()) {
				longestRangeJet = currentJet;
			}
		}
		System.out.println("Longest Range Jet Details:");
		System.out.println(longestRangeJet);
	}

	public void loadAllCargoJets(List<Jet> fleet) {
		if (fleet.isEmpty()) {
			System.out.println("No jets in the fleet.");
			return;
		}

		// Iterate through the fleet to find jets that implement CargoCarrier
		for (Jet jet : fleet) {
			if (jet instanceof CargoJet) {
				CargoJet cargoJet = (CargoJet) jet;
				System.out.println("Cargo Jet Details:");
				System.out.println(cargoJet);
				cargoJet.loadCargo();
				System.out.println(" ");
			}
		}
	}

	public void dogfight(List<Jet> fleet) {
		if (fleet.isEmpty()) {
			System.out.println("No jets in the fleet.");
			return;
		}

		// Iterate through the fleet to find jets that implement FighterJet
		for (Jet jet : fleet) {
			if (jet instanceof FighterJet) {
				FighterJet fighterJet = (FighterJet) jet;
				System.out.println("Fighter Jet Details:");
				System.out.println(fighterJet);
				fighterJet.shootTarget();
				System.out.println(" ");
			}
		}
	}

	public void addJetToTheFleet(List<Jet> fleet) {
		System.out.println("Adding a Jet to the Fleet");

		System.out.print("Enter the type of jet (CargoJet, FighterJet, PassengerJet): ");
		String typeOfJet = scanner.nextLine();

		System.out.print("Enter the model of the jet: ");
		String model = scanner.nextLine();

		System.out.print("Enter the speed in mph: ");
		double speedMPH = scanner.nextDouble();

		System.out.print("Enter the range in miles: ");
		int range = scanner.nextInt();

		System.out.print("Enter the price: ");
		long price = scanner.nextLong();

		scanner.nextLine();

		Jet newJet = null;

		// Determine the type of jet and create an instance of the appropriate subclass
		if ("CargoJet".equalsIgnoreCase(typeOfJet)) {
			newJet = new CargoJet(model, speedMPH, range, price);
		} else if ("FighterJet".equalsIgnoreCase(typeOfJet)) {
			newJet = new FighterJet(model, speedMPH, range, price);
		} else if ("PassengerJet".equalsIgnoreCase(typeOfJet)) {
			newJet = new PassengerJet(model, speedMPH, range, price);
		}

		if (newJet != null) {
			fleet.add(newJet); // Add the new jet to your fleet
			System.out.println("Jet added to the fleet:");
			System.out.println(newJet);
		} else {
			System.out.println("Invalid jet type. Jet not added to the fleet.");
		}
	}

	public void removeJetFromTheFleet(List<Jet> fleet) {
		System.out.println("Removing a Jet from the Fleet");

		if (fleet.isEmpty()) {
			System.out.println("The fleet is empty. There are no jets to remove.");
			return;
		}

		// Display the list of jets in the fleet
		System.out.println("List of Jets in the Fleet:");
		for (int i = 0; i < fleet.size(); i++) {
			System.out.println((i + 1) + ": " + fleet.get(i));
		}

		System.out.print("Enter the number of the jet to remove: ");
		int jetNumber = scanner.nextInt();
		scanner.nextLine();

		if (jetNumber < 1 || jetNumber > fleet.size()) {
			System.out.println("Invalid jet number. No jet removed from the fleet.");
		} else {
			Jet removedJet = fleet.remove(jetNumber - 1);
			System.out.println("Jet removed from the fleet:");
			System.out.println(removedJet);
		}
	}

}