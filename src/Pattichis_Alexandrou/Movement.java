package Pattichis_Alexandrou;

import edu.princeton.cs.introcs.StdDraw; // Library to import the StdDraw to the package
import java.awt.Color;
import java.util.ArrayList;

/**
 * Movement Class is used to make the different moves of people during the
 * simulation
 * 
 * Movement extends to the class Grid
 * 
 * @author apatti01
 * @author aalexa02
 */
public class Movement extends Grid {

	protected static int crowd; // Number of people that appear in the room
	private Person[] people; // All the people that have been created
	private ArrayList<Person> ppl = new ArrayList<Person>();

	/**
	 * Movement constructor that takes 3 integers as inputs: h,w and c
	 * 
	 * @param h Represents the width
	 * 
	 * @param w Represents the width
	 * 
	 * @param c Represents the crowd
	 */
	public Movement(int h, int w, int c) {
		super(h, w);
		crowd = c;
	}

	/**
	 * setPeople() method is udes to print people at the beginning of the game with
	 * their starting position by calling the method startingPoint()
	 */
	public void setPeople(Person[] p) {
		people = startingPoint(p);
	}

	/**
	 * startingPoint() method is used to create all the persons, assign them at
	 * their starting points and draw them on the simulation. It makes sure that
	 * each time the simulation is ra, there is always at least one person that is
	 * infected.
	 * 
	 * @return Represents all the people that are used for the simulation
	 */
	public Person[] startingPoint(Person[] p) {
		int x, y, cnt = 0;
		Point point = new Point(1, 1);
		// A table of all the persons with their position
		for (int i = 0; i < crowd; i++) {
			// Random placement of the people in the room
			x = (int) (Math.random() * width);
			y = (int) (Math.random() * height);
			if (i > 0) {
				do {
					cnt = 0;
					for (int j = 0; j < i; j++) {
						// Checks if the coordinates are empty for the person to be placed.
						if (x == p[j].getCoordinates().getX() && y == p[j].getCoordinates().getY()) {
							cnt = 1;
							x = (int) (Math.random() * width);
							y = (int) (Math.random() * height);
							point = new Point(x, y);
							break;
						}
					}

				} while (cnt != 0);

			}
			point = new Point(x, y);
			p[i].updateCoordinates(point);
		}

		// Draws all the people that are previously created on the grid.
		for (int i = 0; i < crowd; i++) {

			p[i].drawPerson();
		}
		for (int i = 0; i < p.length; i++) {
			ppl.add(p[i]);
		}
		return p;
	}

	/**
	 * withinMargins() method takes one Point as a parameter: p. It checks if the
	 * point is within the given margins
	 * 
	 * @param p Represents the point that we want to check whether it is within the
	 *          margins.
	 * 
	 * @return A boolean value that shows if the Point is within the margins
	 */
	public boolean withinMargins(Point p) {
		if (p.getX() >= 0 && p.getY() >= 0)
			if (p.getX() < super.width && p.getY() < super.height)
				return true;
		return false;
	}

	/**
	 * checkEmpty() method takes one Point as a parameter: p. It checks if there is
	 * not someone else at the current time in that position
	 * 
	 * @param p Represents the point that we want to check whether it is Empty
	 *
	 * @return A boolean value that shows if the Point is empty
	 */
	public boolean checkEmpty(Point p) {
		for (int i = 0; i < crowd; i++)
			if (p.equals(ppl.get(i).getCoordinates()))
				return false;
		return true;
	}

	/**
	 * choiceToMove() method gives the probability for the person to move. If the
	 * value returned is true, then the person moves.
	 */
	public boolean choiceToMove() {
		// Both target and choice are set (for now) randomly.
		int choice = (int) (Math.random() * 100); // The person moves only if the choice is above the target
		if (choice < 20)
			return false;
		return true;
	}

	/**
	 * moveTo() method takes one input as a parameter: dir. It chooses to which
	 * direction the person will move according to the dir that is given
	 * 
	 * 1: Up, 2: Down, 3: Right, 4: Left, 5: Diagonal Up-Right, 6: Diagonal Up-Left,
	 * 7: Diagonal Down-Right, 8: Diagonal Down-Left, else: Stays still
	 * 
	 * @param dir Represents the direction
	 * 
	 * @return A point that will be added to the current position of the person to
	 *         move to the next one
	 */
	public Point moveTo(int dir) {
		switch (dir) {
		case 1:
			return new Point(0, 1); // Up
		case 2:
			return new Point(0, -1); // Down
		case 3:
			return new Point(1, 0); // Right
		case 4:
			return new Point(-1, 0); // Left
		case 5:
			return new Point(1, 1); // Diagonal Up-Right
		case 6:
			return new Point(-1, 1); // Diagonal Up-Left
		case 7:
			return new Point(1, -1); // Diagonal Down-Right
		case 8:
			return new Point(-1, -1); // Diagonal Down-Left
		}
		return new Point(0, 0); // Stays still
	}

	/**
	 * move() method takes one input as a parameter: max. It used everytime we want
	 * to move people that are used during the simulation for one time
	 * 
	 * @param max Indicates Math.max(height,width)
	 */
	public ArrayList<Person> move(int max, int noMask, int mask) {
		Point check = new Point(0, 0);
		for (int i = 0; i < crowd; i++) {

			if (ppl.get(i).getCoordinates().getX() == 2 && ppl.get(i).getCoordinates().getY() == 2) {

				ppl.remove(i);
				System.out.println("true");
				crowd--;
				break;
			}
		}

		for (int i = 0; i < crowd; i++) {
			if (withinMargins(((Person) ppl.get(i)).getCoordinates()))
				if (choiceToMove()) {
					boolean[] directions = new boolean[9];
					for (int j = 0; j < 9; j++)
						directions[j] = false;

					boolean moved = false;
					while (!moved) {

						check = new Point((ppl.get(i)).getCoordinates().getX(),
								((Person) ppl.get(i)).getCoordinates().getY());

						int dir = (int) (Math.random() * 9) + 1;

						check = new Point(((Person) ppl.get(i)).getCoordinates().getX() + moveTo(dir).getX(),
								((Person) ppl.get(i)).getCoordinates().getY() + moveTo(dir).getY());

						if (checkEmpty(check) && withinMargins(check)
								|| ((Person) ppl.get(i)).getCoordinates().equals(check)) {
							moved = true;
							((Person) ppl.get(i)).updateCoordinates(check);
							PersonInfected inf = new PersonInfected(((Person) ppl.get(i)), this);
							((Person) ppl.get(i))
									.setInfected(inf.checkIfInfected(super.height, super.width, noMask, mask));
							for (int j = 0; j < crowd; j++)
								if (((Person) ppl.get(i)).getCoordinates().equals(check))
									((Person) ppl.get(i))
											.setInfected(inf.checkIfInfected(super.height, super.width, noMask, mask));
						}
					}

				}
		}
		createGrid(max);

		for (int i = 0; i < ppl.size(); i++) {
			System.out.println(ppl.get(i).getCoordinates().getX() + " " + ppl.get(i).getCoordinates().getY());
			((Person) ppl.get(i)).drawPerson();
		}
		System.out.println("\n");
		return ppl;
	}

	/*
	 * public ArrayList<Person> move2 (ArrayList<Person> a) { for (int i = 0; i
	 * <a.size(); i++) { if(((Person) a.get(i)).getCoordinates()==new Point (2,2))
	 * a.remove(i); else ((Person) a.get(i)).drawPerson();} return a; }
	 */
	/**
	 * Getter method for this.people
	 * 
	 * @return this.people
	 */
	public ArrayList<Person> getPeople() {
		return ppl;
	}

	/**
	 * Getter method for this.crowd
	 * 
	 * @return this.crowd
	 */
	public int getCrowd() {
		return crowd;
	}

}