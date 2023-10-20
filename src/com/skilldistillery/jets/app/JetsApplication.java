package com.skilldistillery.jets.app;

public class JetsApplication {

	public static void main(String[] args) {
		JetsApplication jA = new JetsApplication();
		jA.launch();
	}

	public void launch() {
		printMenu();
	}

	public void printMenu() {
		System.out.println("************************************");
		System.out.println("*       Pick an Option Below       *");
		System.out.println("************************************");
		System.out.println("*  1: List Fleet                   *");
		System.out.println("*  2: Fly All Jets                 *");
		System.out.println("*  3: View Fastest Jet             *");
		System.out.println("*  4: View Jet with Longest Range  *");
		System.out.println("*  5: Load All Cargo Jets          *");
		System.out.println("*  6: Dogfight!                    *");
		System.out.println("*  7: Add a Jet to Fleet           *");
		System.out.println("*  8: Remove a Jet from Fleet      *");
		System.out.println("*  9: Quit                         *");
		System.out.println("************************************");
	}

}