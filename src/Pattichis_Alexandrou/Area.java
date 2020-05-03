package Pattichis_Alexandrou;

import java.util.Scanner;

public class Area {
	private String name;
	private String bordersWith;
	private int crowd;
	private int numInfected;
	private int numImmune;
	private int numHealthy;
	private int height;
	private int width;
	private int numBorders;
	private Point[] borders;

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

	public Area(String n) {
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

	public void readNumInfected() throws NegativeNumberException, InfectedLessThanOneException, OvercrowdedException {
		// Reads the number of infected people
		System.out.print("\nThe number of infected people " + "for area " + name + ": ");
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
		System.out.print("\nThe number of infected people " + "for area " + name + ": ");
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
		System.out.print("\nThe number of infected people " + "for area " + name + ": ");
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

	public void setWidth() throws NegativeNumberException, GridSizeException {
		// Reads the width of the grid
		System.out.print("\nThe number of infected people " + "for area " + name + ": ");
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

	public void setBorders() throws ProbabilitiesOptionException {
		System.out.print("\nDo you want area " + " to have borders?\nPress 0 for NO or 1 for YES:");
		String o = input.nextLine();
		int option = Integer.parseInt(o);

		if (option > 1 || option < 0)
			throw new ProbabilitiesOptionException("Insert a number that is either 0 or 1");

		if (option == 1) {
			System.out.print("\nWith which area do you want area " + name + " to be bordered with");
			bordersWith = input.nextLine();
			// if (towns.length() > 1 || towns.charAt(0) - 65 > sumAreas - 1 ||
			// towns.charAt(0) - 65 < 0
			// || towns.charAt(0) - 65 > 91 - 65 || towns.charAt(0) - 65 - i == 0)
			// throw new ProbabilitiesOptionException("Give the name of the area that exist
			// e.g 'A' ");
			// town[i] = towns.charAt(0) - 65 + 1;
			// if (userCrowd < 0)
			// throw new NegativeNumberException();

			System.out.println("How many borders do you want for the area?");
			String b = input.nextLine();
			int nBorders = Integer.parseInt(b);
			if (nBorders <= 0)
				throw new ProbabilitiesOptionException("The borders sould be more than 1 or more!");
			if (nBorders > (this.height * 2 + this.height * 2))
				throw new ProbabilitiesOptionException(
						"Insert a number that is equal or less than the perimeter of the border!");
			numBorders = nBorders;

			System.out.println("Give the area on the grid for the borders in points(e.g 2,0)"
					+ "\n(The points should be on the border of the grid, successively and you should give them in order!");
			borders = new Point[numBorders];
			for (int i = 0; i < numBorders; i++) {
				System.out.println(i + 1 + ") ");
				String coordinates = input.nextLine();
				borders[i] = Input(coordinates);
				if (borders[i].getX() == -1 || borders[i].getY() == -1)
					throw new ProbabilitiesOptionException(
							"Give the points correctly..\ne.g. Give a point like (2,1)!!");
				if (borders[i].getX() > this.height || borders[i].getY() > this.width)
					throw new ProbabilitiesOptionException(
							"Give the points that are in the grid..\ne.g. Give a point like (2,1)!!");
				if (!(borders[i].getX() <= this.height && borders[i].getY() == this.width
						|| borders[i].getX() == this.height && borders[i].getY() <= this.width
						|| borders[i].getX() == 0 && borders[i].getY() <= this.width
						|| borders[i].getX() <= this.height && borders[i].getY() == 0))
					throw new ProbabilitiesOptionException(
							"Give the points that are on the border of the grid..\ne.g. Give a point like (height,0)!!");
				if (i != 0) {
					if (!(borders[i].getX() == borders[i - 1].getX() + 1 && borders[i].getY() == borders[i - 1].getY()
							|| borders[i].getX() == borders[i - 1].getX() - 1
									&& borders[i].getY() == borders[i - 1].getY()
							|| borders[i].getX() == borders[i - 1].getX()
									&& borders[i].getY() == borders[i - 1].getY() + 1
							|| borders[i].getX() == borders[i - 1].getX()
									&& borders[i].getY() == borders[i - 1].getY() - 1))
						throw new ProbabilitiesOptionException(
								"Give the points successively and you should give them in order!..\ne.g. Give points like (2,0)->(3,0)->(4,0)!!");
				}

			}
		}
	}

	public int getNumBorders() {
		return numBorders;
	}

	public Point[] getBorders() {
		return borders;
	}

}
