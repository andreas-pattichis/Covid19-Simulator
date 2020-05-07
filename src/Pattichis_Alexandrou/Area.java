package Pattichis_Alexandrou;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import edu.princeton.cs.introcs.StdDraw;

public class Area {
	private char name;
	private int numOfAreas;
	private char namesThatBorderWith[];
	private int crowd;
	private int numInfected;
	private int numImmune;
	private int numHealthy;
	private int height;
	private int width;
	private int numBorders;
	private ArrayList<Point>[] bordersForEachArea = new ArrayList[1];
	private Person[] people = null;
	private ArrayList<Person> pl = new ArrayList<Person>();
	private Grid a;
	private Movement x;
	private PlaceInfected places;

	Scanner input = new Scanner(System.in); // Using Scanner to read from the console

	public static Point Input(String s) {
		String str = "";
		Point x = new Point(0, 0);
		String newStr = "";
		String newStr2 = "";
		boolean b = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ',')
				str += s.charAt(i);
		}
		if (isNumeric(str) && str.length() == s.length() - 1) {
			for (int i = 0; i < s.length(); i++) {

				if (s.charAt(i) == ',' || s.charAt(i) == '.' || s.charAt(i) == ' ')
					b = true;
				else if (s.charAt(i) == '-') {
					x = new Point(-1, -1);
				} else if ((Integer.parseInt(String.valueOf(s.charAt(i)))) >= 0
						&& (Integer.parseInt(String.valueOf(s.charAt(i)))) <= 9 && b == false)
					newStr += s.charAt(i);
				else
					newStr2 += s.charAt(i);
			}
			if (newStr.length() == 1 && newStr2.length() == 1) {
				x = new Point(Integer.parseInt(String.valueOf(newStr.charAt(0))),
						Integer.parseInt(String.valueOf(newStr2.charAt(0))));
			} else if (newStr.length() == 2 && newStr2.length() == 1) {
				x = new Point(Integer.parseInt(String.valueOf(newStr.charAt(0)) + String.valueOf(newStr.charAt(1))),
						Integer.parseInt(String.valueOf(newStr2.charAt(0))));
			} else if (newStr.length() == 2 && newStr2.length() == 2)
				x = new Point(Integer.parseInt(String.valueOf(newStr.charAt(0)) + String.valueOf(newStr.charAt(1))),
						Integer.parseInt(String.valueOf(newStr2.charAt(0)) + String.valueOf(newStr2.charAt(1))));
			else if (newStr.length() == 1 && newStr2.length() == 2)
				x = new Point(Integer.parseInt(String.valueOf(newStr.charAt(0))),
						Integer.parseInt(String.valueOf(newStr2.charAt(0)) + String.valueOf(newStr2.charAt(1))));
			else if (newStr.length() > 2)
				x = new Point(Integer.parseInt(newStr), 0);
			else if (newStr2.length() > 2)
				x = new Point(Integer.parseInt(newStr2), 0);
			else
				x = new Point(-1, -1);
			return x;
		} else {
			x = new Point(-1, -1);

		}
		return x;
	}

	public Area(char n) {
		name = n;
		crowd = 0;
		numInfected = 0;
		numImmune = 0;
		numHealthy = 0;
		height = 0;
		width = 0;
		numBorders = 0;
	}

	public void readCrowd() throws NegativeNumberException {
		// Reads the number of people
		System.out.print("\nThe number of people " + "for area " + name + ": ");
		String c = input.nextLine();
		this.crowd = Integer.parseInt(c);

		if (this.crowd < 0)
			throw new NegativeNumberException();
	}

	public int getCrowd() {
		return crowd;
	}

	public char getName() {
		return this.name;
	}

	public char[] getNamesOfAreas() {
		return this.namesThatBorderWith;
	}

	public void readNumInfected() throws NegativeNumberException, InfectedLessThanOneException, OvercrowdedException {
		// Reads the number of infected people
		System.out.print("\nThe number of infected people for area " + name + ": ");
		String i = input.nextLine();
		this.numInfected = Integer.parseInt(i);
		if (this.numInfected < 0)
			throw new NegativeNumberException();
		if (this.numInfected < 1)
			throw new InfectedLessThanOneException();
		if (this.numInfected > this.crowd)
			throw new OvercrowdedException("\nThe number of infected is bigger than the crowd.\nTry again!!");
	}

	public int getNumInfected() {
		return numInfected;
	}

	public void readNumImmune() throws NegativeNumberException, OvercrowdedException {
		// Reads the number of immune people
		System.out.print("\nThe number of immune people for area " + name + ": ");
		String i = input.nextLine();
		this.numImmune = Integer.parseInt(i);

		int sub = this.crowd - this.numInfected;
		if (this.numImmune < 0)
			throw new NegativeNumberException();
		if (this.numImmune > sub)
			throw new OvercrowdedException(
					"\nThe number of Immune + the infected is bigger than the crowd. \nTry again!!");
	}

	public int getNumImmune() {
		return numImmune;
	}

	public void readNumHealthy() {
		this.numHealthy = this.crowd - (this.numInfected + this.numImmune);
	}

	public int getNumHealthy() {
		return numHealthy;
	}

	public void readHeight() throws NegativeNumberException, GridSizeException {
		// Reads the height of the grid
		System.out.print("\nThe height " + "for area " + name + ": ");
		String h = input.nextLine();
		this.height = Integer.parseInt(h);
		if (this.height < 0)
			throw new NegativeNumberException();
		if (this.height >= 50)
			throw new GridSizeException();
	}

	public int getHeight() {
		return height;
	}

	public void readWidth() throws NegativeNumberException, GridSizeException {
		// Reads the width of the grid
		System.out.print("\nThe width " + "for area " + name + ": ");
		String w = input.nextLine();
		width = Integer.parseInt(w);
		if (width < 0)
			throw new NegativeNumberException();
		if (width >= 50)
			throw new GridSizeException();
	}

	public int getWidth() {
		return width;
	}

	/**
	 * This is a method that checks if a string is numeric.
	 * 
	 * @param String str.
	 * @return true if it is and false if is not.
	 */
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void setBorders(int sum) throws ProbabilitiesOptionException {
		System.out.print("\nDo you want area " + " to have borders?\nPress 0 for NO or 1 for YES:");
		String o = input.nextLine();
		int option = Integer.parseInt(o);

		if (option > 1 || option < 0)
			throw new ProbabilitiesOptionException("\nInsert a number that is either 0 or 1");

		if (option == 1) {
			System.out.print("\nWith how many areas do you want area " + this.name + " to border with? ");
			String nAreas = input.nextLine();
			numOfAreas = Integer.parseInt(nAreas);
			if (numOfAreas <= 0||numOfAreas>=sum)
				throw new ProbabilitiesOptionException("\nThe number of areas should be 1 or more!");

			namesThatBorderWith = new char[numOfAreas];
			bordersForEachArea = new ArrayList[numOfAreas];

			for (int k = 0; k < numOfAreas; k++)
				bordersForEachArea[k] = new ArrayList<Point>();

			for (int k = 0; k < numOfAreas; k++) {
				System.out.print("\nNo." + (int) (k + 1) + " area you want area " + name + " to be bordered with: ");
				namesThatBorderWith[k] = input.nextLine().charAt(0);
				
				 if ( namesThatBorderWith[k] - 65 > numOfAreas  ||
				 namesThatBorderWith[k] - 65 < 0
				 || namesThatBorderWith[k] - 65 > 91 - 65 )
				 throw new ProbabilitiesOptionException("Give the name of the area that existe.g 'A' ");
				
				System.out.print("\nHow many borders do you want for the area " + namesThatBorderWith[k] + "? ");
				String b = input.nextLine();
				int nBorders = Integer.parseInt(b);
				if (nBorders <= 0)
					throw new ProbabilitiesOptionException("\nThe borders sould be 1 or more!");
				if (nBorders > (this.height * 2 + this.height * 2))
					throw new ProbabilitiesOptionException(
							"\nInsert a number that is equal or less than the perimeter of the border!");
				numBorders = nBorders;

				System.out.println("\nGive the area on the grid for the borders in points(e.g 2,0)"
						+ "\n(The points should be on the border of the grid, successively and you should give them in order!\n");

				Point[] borders = new Point[numBorders];
				for (int i = 0; i < numBorders; i++) {
					System.out.print(i + 1 + ": ");
					String coordinates = input.nextLine();
					borders[i] = Input(coordinates);
					if (borders[i].getX() == -1 || borders[i].getY() == -1)
						throw new ProbabilitiesOptionException(
								"Give the points correctly..\ne.g. Give a point like (2,1)!!");
					if (borders[i].getX() > this.height - 1 || borders[i].getY() > this.width - 1)
						throw new ProbabilitiesOptionException(
								"Give the points that are in the grid(form 0 to (height or width) -1)..\ne.g. Give a point like (2,1)!!");
					if (!(borders[i].getX() <= this.height && borders[i].getY() == this.width
							|| borders[i].getX() == this.height - 1 && borders[i].getY() <= this.width - 1
							|| borders[i].getX() == 0 && borders[i].getY() <= this.width - 1
							|| borders[i].getX() <= this.height - 1 && borders[i].getY() == 0))
						throw new ProbabilitiesOptionException(
								"Give the points that are on the border of the grid..\ne.g. Give a point like (height,0)!!");
					if (i != 0) {
						if (!(borders[i].getX() == borders[i - 1].getX() + 1
								&& borders[i].getY() == borders[i - 1].getY()
								|| borders[i].getX() == borders[i - 1].getX() - 1
										&& borders[i].getY() == borders[i - 1].getY()
								|| borders[i].getX() == borders[i - 1].getX()
										&& borders[i].getY() == borders[i - 1].getY() + 1
								|| borders[i].getX() == borders[i - 1].getX()
										&& borders[i].getY() == borders[i - 1].getY() - 1))
							throw new ProbabilitiesOptionException(
									"\nGive the points successively and you should give them in order!..\ne.g. Give points like (2,0)->(3,0)->(4,0)!!");
					}
					for (int l = 0; l < k; l++)
						for (int z = 0; z < bordersForEachArea[l].size(); z++)
							if (bordersForEachArea[l].get(z).equals(borders[i]))
								throw new ProbabilitiesOptionException(
										"\nThis border already leads to another area.\nDo not repeat coordinates that have already been entered.");

				}

				for (int i = 0; i < numBorders; i++)
					bordersForEachArea[k].add(new Point(borders[i]));
			}
		}

		else
			numBorders = 0;
	}

	public void DrawBorders() {

		for (int k = 0; k < numOfAreas; k++) {
			for (int i = 0; i < bordersForEachArea[k].size(); i++) {
				Point check = new Point(bordersForEachArea[k].get(i));
				// System.out.println(i);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.circle(check.getX() + 0.5, check.getY() + 0.5, 0.4);
				if (k % 2 == 0)
					StdDraw.setPenColor(StdDraw.PINK);
				else if (k % 3 == 0)
					StdDraw.setPenColor(StdDraw.WHITE);
				else if (k % 2 == 1)
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
				StdDraw.filledCircle(check.getX() + 0.5, check.getY() + 0.5, 0.4);
				// StdDraw.filledSquare(check.getX() + 0.5, check.getY() + 0.5, 0.5);

			}
		}
	}

	public int getNumBorders() {
		return numBorders;
	}

	public ArrayList<Point>[] getBorders() {
		return bordersForEachArea;
	}

	public void setPeople() {
		this.people = new Person[this.crowd];

		for (int i = 0; i < this.numImmune; i++) {
			this.people[i] = new Person(0, 0, false, true);
		}
		for (int i = this.numImmune; i < this.numImmune + this.numInfected; i++) {
			this.people[i] = new Person(0, 0, true, false);
		}
		for (int i = this.numImmune + this.numInfected; i < this.crowd; i++) {
			this.people[i] = new Person(0, 0, false, false);
		}
		for (int i = 0; i < people.length; i++) {
			pl.add(people[i]);

		}

	}

	public void setGrid() {
		a = new Grid(this.height, this.width); // Creates the grid
	}

	public void setMovement() {
		x = new Movement(this.height, this.width, this.crowd); // x will be used for the people to be set and
		// return x; // move through the grid
	}

	public void setPlaces(int duration) {
		places = new PlaceInfected(this.height, this.width, this.crowd, duration, x.getPeople());
	}

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

	public void drawInitialArea() {
		StdDraw.clear(StdDraw.LIGHT_GRAY);
		int max = Math.max(this.width, this.height);
		a.createGrid(max); // Draws the grid
		DrawBorders();
		a.createGrid(max);

		pl = x.setPeople(pl); // Draws the people
		/*
		 * for (int i = 0; i < pl.size(); i++) if (numBorders > 0) { for (int k = 0; k <
		 * numOfAreas; k++) for (int j = 0; j < numBorders; j++) { Point check = new
		 * Point(bordersForEachArea[k].get(j)); if (pl.get(i).getCoordinates().getX() ==
		 * check.getX() && pl.get(i).getCoordinates().getY() == check.getY()) { if
		 * (x.move2(pl.get(i)) == false) { pl.remove(i); x.crowd--; places.crowd--; } }
		 * } }
		 */

		// Draws the grid again

		System.out.println("---------------------------------------------------------------");
		// Prints the statistics
		System.out.println("AT THE BEGINNING THERE ARE: \n");
		if (this.numHealthy > 1 || this.numHealthy == 0)
			System.out.println(this.numHealthy + " people that are healthy and not immune.\n");
		if (this.numHealthy == 1)
			System.out.println(this.numHealthy + " person that is healthy and not immune.\n");
		if (this.numInfected > 1 || this.numInfected == 0)
			System.out.println(this.numInfected + " people that are infected.\n");
		if (this.numInfected == 1)
			System.out.println(this.numInfected + " person that is infected.\n");
		if (this.numImmune > 1 || this.numImmune == 0)
			System.out.println(this.numImmune + " people that are immune.\n");
		if (this.numImmune == 1)
			System.out.println(this.numImmune + " person that is immune.\n");

	}

	private ArrayList<Character>[] namesOfTheBorders;

	private void setNamesOfTheBorders(ArrayList<Character>[] c) {
		namesOfTheBorders = c;
	}

	public ArrayList<Character>[] getNamesOfTheBorders() {
		return namesOfTheBorders;
	}

	public ArrayList<Person>[] drawEachStep(int peopleVirus, int placeVirus, int peopleMask, int placeMask) {

		ArrayList<Person>[] transportedPeople = new ArrayList[this.numOfAreas];
		for (int k = 0; k < numOfAreas; k++)
			transportedPeople[k] = new ArrayList<Person>();

		int max = Math.max(this.width, this.height);
		StdDraw.enableDoubleBuffering();
		StdDraw.clear(StdDraw.LIGHT_GRAY);

		System.out.println("\n" + pl.size() + "\n");
		places.setDuration(x.getPeople(), crowd);

		places.placeAffectsPeople(x.getPeople(), placeVirus, placeMask);
		pl = x.move(max, peopleVirus, peopleMask);
	
		places.PrintInfection();
		DrawBorders();
		a.createGrid(max); // Draws the grid again
		for (int i = 0; i < pl.size(); i++)
			pl.get(i).drawPerson();

		ArrayList<Character>[] namesOfTheBorders = new ArrayList[this.numOfAreas];
		for (int k = 0; k < numOfAreas; k++)
			namesOfTheBorders[k] = new ArrayList<Character>();

		for (int i = 0; i < pl.size(); i++) {
			if (numBorders > 0) {
				for (int k = 0; k < numOfAreas; k++) {
					for (int j = 0; j < numBorders; j++) {
						if (!pl.isEmpty()) {
							Point check = new Point(bordersForEachArea[k].get(j));
							if (pl.get(i).getCoordinates().getX() == check.getX()
									&& pl.get(i).getCoordinates().getY() == check.getY()) {
								if (x.move2(pl.get(i)) == false) {
									transportedPeople[k].add(pl.get(i));
									namesOfTheBorders[k].add(namesThatBorderWith[k]);
									pl.remove(i);
									x.crowd--;
									places.crowd--;
									if (i > 0)
										i--;
								}
							}
						}
					}
				}
			}
		}

		delay();
		StdDraw.show();
		StdDraw.pause(6);
		System.out.println("drawEachStep()");

		setNamesOfTheBorders(namesOfTheBorders);
		return transportedPeople;
	}

	public void addPersonToArea(Person p) {
		x.addPerson(p);
		places.crowd--;
	}

	public void printFinalStaticsforArea() {

		int finalSumInfected = 0;
		int finalSumHealthy = 0;
		int finalSumImmune = 0;

		pl = x.getPeople();

		for (int i = 0; i < x.getCrowd(); i++) {
			if (((Person) pl.get(i)).isImmune())
				finalSumImmune++;
			else if (((Person) pl.get(i)).isInfected())
				finalSumInfected++;
			else if (!((Person) pl.get(i)).isInfected() && !((Person) pl.get(i)).isImmune())
				finalSumHealthy++;
		}

		System.out.println("---------------------------------------------------------------");
		// Prints the statistics
		System.out.println("FINALLY THERE ARE:\n");
		if (finalSumHealthy > 1 || finalSumHealthy == 0)
			System.out.println(finalSumHealthy + " people that are healthy and not immune.\n");
		if (finalSumHealthy == 1)
			System.out.println(finalSumHealthy + " person that is healthy and not immune.\n");
		if (finalSumInfected > 1 || finalSumInfected == 0)
			System.out.println(finalSumInfected + " people that are infected.\n");
		if (finalSumInfected == 1)
			System.out.println(finalSumInfected + " person that is infected.\n");
		if (finalSumImmune > 1 || finalSumImmune == 0)
			System.out.println(finalSumImmune + " people that are immune.\n");
		if (finalSumImmune == 1)
			System.out.println(finalSumImmune + " person that is immune.\n");
		if (finalSumInfected - this.numInfected >= 2)
			System.out.println("As you can see, with these results is important to STAY HOME!!\n");
	}

}