package Pattichis_Alexandrou;

import edu.princeton.cs.introcs.StdDraw; // Library to import the StdDraw to the package

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simulation Class represents the simulation that will connect all the other
 * class together and run the simulation.
 * 
 * Jar file should be in the workspace so that it locates it automatically.
 * 
 * @author apatti01
 * @author aalexa02
 */
public class Simulation {

	/**
	 * delay() method is used for better view of the simulation and the process of
	 * infection
	 */
	public static void delay() {
		try {
			Thread.sleep(1 * 2000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // Using Scanner to read from the console
		String time; // Indicates the time that the simulation will run (in minutes)
		String height; // Indicates the height of the board
		String width; // Indicates the width of the grid
		String crowd; // Indicates the number of people that will be used for the simulation
		String duration; // Indicates that each place will be affected

		// The user enters manually the health of the people
		String Infected;
		String Immune;
		String option; // The user is asked if he/she wants to add manually the probabilities of
		String option1; // infection
		String ppVirus; // Person-to-Person probability of infection without mask
		String ppMask; // Person-to-Person probability of infection with mask
		String plVirus; // Place-to-Person probability of infection without mask
		String plMask; // Place-to-Person probability of infection with mask
		String places; // Number of areas
		String borders; // Number of borders
		String points; // Coordinates

		int peopleVirus = 0;
		int peopleMask = 0;
		int placeVirus = 0;
		int placeMask = 0;
		int opt = 0;
		int opt1 = 0;
		int userTime = 0;
		int userDuration = 0;
		int sumAreas = 0;
		int sumBorders = 0;
		Area[] areas = null;

		boolean done = true;

		int max = 0;

		// Prints a message to inform the user about the simulation
		System.out.println("                   SIMULATION OF THE PROCESS                  ");
		System.out.println("         OF TRANSMITING A VIRUS TO A SET OF INDIVIDUALS        ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("       The virus is spread from person to person through       ");
		System.out.println("         personal contact with an infected person or by        ");
		System.out.println("            contact with an infected area of the site.       \n");
		System.out.println("A person can be: 1) healthy  (wearing a GREEN shirt)           ");
		System.out.println("                 2) infected (wearing a RED shirt)             ");
		System.out.println("                 3) immune   (wearing a BLUE shirt)            ");
		System.out.println("                 && wearing a mask                           \n");
		System.out.println("When floor is colored: 1) ORANGE, it just got infected         ");
		System.out.println("                       2) YELLOW, it will stop being infected\n");
		System.out.println("The borders are indicated with a circle.                       ");
		System.out.println("The borders have 3 different colors depending on the area they ");
		System.out.println("lead to:               1) PINK                                 ");
		System.out.println("                       2) WHITE                                ");
		System.out.println("                       3) LIGHT BLUE                           ");
		System.out.println("\nRemember that borders can only be placed on the perimeter of the");
		System.out.println("areas.");
		System.out.println("*If there are more than 3 areas, different borders can have the ");
		System.out.println("same colors.                                                 \n");
		System.out.println("*Every person stays at the place for 1 minute.                 ");
		System.out.println("*For each minute, the simulation shows the movement in each area.");

		// While loop to read all the inputs, that stops whenever none of the exception
		// are caught
		int cnt = 0;
		int sub = 0;
		while (done) {
			try {
				System.out.println("---------------------------------------------------------------");
				System.out.println("Now you get to decide the specifications of the simulation: \n");
				// Reads the number of people
				System.out.println("Would you like to set your own propabilities for the affection "
						+ "\nof the Virus to the people?");
				System.out.print("\nPress 0 for NO or 1 for YES: ");
				option = input.nextLine();
				opt = Integer.parseInt(option);
				if (opt > 1 || opt < 0)
					throw new ProbabilitiesOptionException("Insert a number that is either 0 or 1");

				if (opt == 0) {
					System.out.print("\nDEFAULT PROBABILITIES:\n");
					System.out.println("\n(1) Person-to-Person probability of infection without mask:(%) 70");
					System.out.println("\n(2) Person-to-Person probability of infection with mask:(%) 40");
					System.out.println("\n(3) Place-to-Person probability of infection without mask:(%) 60");
					System.out.println("\n(4) Place-to-Person probability of infection with mask:(%) 30");
				}

				if (opt == 1) {
					System.out.print("\n\nGive the percantages about:\n");

					System.out.print("\n(1) Person-to-Person probability of infection without mask:(%) ");
					ppVirus = input.nextLine();
					peopleVirus = Integer.parseInt(ppVirus);
					if (peopleVirus < 0 || peopleVirus > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");

					System.out.print("\n(2) Person-to-Person probability of infection with mask:(%) ");
					ppMask = input.nextLine();
					peopleMask = Integer.parseInt(ppMask);
					if (peopleMask < 0 || peopleMask > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");

					System.out.print("\n(3) Place-to-Person probability of infection without mask:(%) ");
					plVirus = input.nextLine();
					placeVirus = Integer.parseInt(plVirus);
					if (placeVirus < 0 || placeVirus > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");

					System.out.print("\n(4) Place-to-Person probability of infection with mask:(%) ");
					plMask = input.nextLine();
					placeMask = Integer.parseInt(plMask);
					if (placeMask < 0 || placeMask > 100)
						throw new ProbabilitiesOptionException("\nGive a persentage between 0 to 100");
				}
				System.out.println("---------------------------------------------------------------");
				System.out.println("Also please tell us:");
				System.out.print("The number of areas that people will interact: ");
				places = input.nextLine();
				sumAreas = Integer.parseInt(places);
				if (sumAreas < 0)
					throw new NegativeNumberException();
				if (sumAreas > 26)
					throw new NegativeNumberException("\nThe areas cannot be more than 26.\n");

				areas = new Area[sumAreas];

				for (int i = 0; i < sumAreas; i++) {
					areas[i] = new Area((char) ('A' + i));

					// Reads the number of people
					areas[i].readCrowd();

					// Reads the number of infected people
					areas[i].readNumInfected();

					// Reads the number of immune people
					areas[i].readNumImmune();

					// Reads the number of healthy people
					areas[i].readNumHealthy();

					// Reads the height of the grid
					areas[i].readHeight();

					// Reads the width of the grid
					areas[i].readWidth();

					max = Math.max(areas[i].getWidth(), areas[i].getHeight());

					if (areas[i].getCrowd() > areas[i].getWidth() * areas[i].getWidth())
						throw new OvercrowdedException();

					if (sumAreas > 1)
						areas[i].setBorders(sumAreas);
				}
				// Reads the time of the program
				System.out.print("\nGive the time of the program (in minutes): ");
				time = input.nextLine();
				userTime = Integer.parseInt(time);
				if (userTime < 0)
					throw new NegativeNumberException();

				// Reads the duration that each place will be affected
				System.out.print("\nGive the duration that each place will be affected: ");
				duration = input.nextLine();
				userDuration = Integer.parseInt(duration);
				if (userDuration < 0)
					throw new NegativeNumberException();
				done = false;
			} catch (ProbabilitiesOptionException error) {
				System.out.println(error.getMessage());
			} catch (OvercrowdedException error) {
				System.out.println(error.getMessage());
			} catch (InfectedLessThanOneException error) {
				System.out.println(error.getMessage());
			} catch (NegativeNumberException error) {
				System.out.println(error.getMessage());
			} catch (GridSizeException error) {
				System.out.println(error.getMessage());
			} catch (NumberFormatException error) {
				System.out.println("\nSorry, something went wrong.");
				System.out.println("Put a number to the input" + "\n");
			}
		}

		for (int i = 0; i < sumAreas; i++) {
			areas[i].setPeople();
			if (areas[i].getHeight() >= 20 && areas[i].getHeight() >= 20)
				System.out.println("\nPlease wait a few seconds...\n");

			areas[i].setGrid();
			areas[i].setMovement();
			areas[i].setPlaces(userDuration);
			StdDraw.enableDoubleBuffering();
			areas[i].drawInitialArea();

			delay();
			StdDraw.show();
			StdDraw.pause(6);
		}

		for (int i = 0; i < userTime - 1; i++) {
			ArrayList<Person>[] allTransportedPeople = new ArrayList[sumAreas];
			for (int k = 0; k < sumAreas; k++)
				allTransportedPeople[k] = new ArrayList<Person>();

			ArrayList<Character>[] allNames = new ArrayList[sumAreas];
			for (int k = 0; k < sumAreas; k++)
				allNames[k] = new ArrayList<Character>();

			for (int j = 0; j < sumAreas; j++) {
				ArrayList<Person>[] transportedPeopleOfArea;
				if (opt == 1)
					transportedPeopleOfArea = areas[j].drawEachStep(peopleVirus, placeVirus, peopleMask, placeMask,
							areas);
				else
					transportedPeopleOfArea = areas[j].drawEachStep(-1, -1, -1, -1, areas);

				ArrayList<Character>[] namesOfTheBorders = areas[j].getNamesOfTheBorders();

				for (int k = 0; k < transportedPeopleOfArea.length; k++)
					for (int z = 0; z < transportedPeopleOfArea[k].size(); z++) {
						allTransportedPeople[j].add(transportedPeopleOfArea[k].get(z));
						allNames[j].add(namesOfTheBorders[k].get(z));
					}

			}
			for (int k = 0; k < allTransportedPeople.length; k++)
				for (int z = 0; z < allTransportedPeople[k].size(); z++) {
					for (int j = 0; j < sumAreas; j++) {
						if (areas[j].getName() == allNames[k].get(z)) {
							areas[j].addPersonToArea(allTransportedPeople[k].get(z));
							areas[j].addCrowd();
						}
					}
				}
		}
		for (int i = 0; i < sumAreas; i++) {

			areas[i].printFinalStaticsforArea();
		}

	}
}