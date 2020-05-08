package Team2;

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

	protected int crowd; // Number of people that appear in the room
	private Person[] people; // All the people that have been created
	private ArrayList<Person> ppl = new ArrayList<Person>(); // All the people that are used in the class Movement

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
	 * setPeople() method is used to print people at the beginning of the game with
	 * their starting position by calling the method startingPoint()
	 * 
	 * @param pl All the people that are used in the class Movement
	 * 
	 * @return The updated arraylist after the movement has been done.
	 */
	public ArrayList<Person> setPeople(ArrayList<Person> pl) {
		ppl = startingPoint(pl);
		return ppl;
	}

	/**
	 * startingPoint() method is used to create all the persons, assign them at
	 * their starting points and draw them on the simulation. It makes sure that
	 * each time the simulation is ra, there is always at least one person that is
	 * infected.
	 * 
	 * @param ppl All the people that are used in the class Movement
	 * 
	 * @return Represents all the people that are used for the simulation
	 */
	public ArrayList<Person> startingPoint(ArrayList<Person> ppl) {
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
						if (x == ppl.get(j).getCoordinates().getX() && y == ppl.get(j).getCoordinates().getY()) {
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
			ppl.get(i).updateCoordinates(point);
		}

		// Draws all the people that are previously created on the grid.
		for (int i = 0; i < crowd; i++) {

			ppl.get(i).drawPerson();
		}

		return ppl;
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
	 * addPerson() method takes one Person as a parameter: p. This person is the
	 * person that will move from one area to another area. In this method, it
	 * updates the coordinates of the person to be placed in the new area and adds
	 * it to the Arraylist ppl which stores all the people.
	 * 
	 * @param p the person that will move from one area to another area
	 */
	public void addPerson(Person p) {

		Point nCoordinate = new Point(0, 0);
		if (crowd < super.height * super.width) {
			boolean moved = false;
			while (!moved) {
				nCoordinate = new Point((int) (Math.random() * super.height), (int) (Math.random() * super.height));
				if (checkEmpty(nCoordinate) && withinMargins(nCoordinate)) {
					moved = true;
					p.updateCoordinates(nCoordinate);
				}
			}
			ppl.add(p);
			crowd++;
		}
	}

	/**
	 * move() method takes three input as a parameter: max, noMask, mask. It is used
	 * everytime we want to move people that are used during the simulation for one
	 * time.
	 * 
	 * @param max    Indicates Math.max(height,width)
	 * 
	 * @param noMask Indicates the probabilities of infection when mask is not being
	 *               uses
	 * 
	 * @param mask   Indicates the probabilities of infection when mask is not being
	 *               uses
	 * 
	 * @return A person type Arraylist
	 */
	public ArrayList<Person> move(int max, int noMask, int mask) {
		Point check = new Point(0, 0);

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

		System.out.println(crowd + " people are in this area.\n");
		System.out.println("The coordinates of all the people in this area: ");
		for (int i = 0; i < ppl.size(); i++)
			System.out.println(i+1 + ": " + ppl.get(i).getCoordinates().getX() + "," + ppl.get(i).getCoordinates().getY());

		System.out.println();
		return ppl;
	}

	/**
	 * movePerson() method takes one input as a parameter: p. It is used everytime
	 * we want to check if the person has moved out of the borders to another area.
	 * 
	 * @param p The person that will be check if has moved to another area.
	 * 
	 * @return A boolean value that indicates if the person has moved out of the
	 *         area or not
	 */
	public boolean movePerson(Person p) {
		Point check = new Point(0, 0);

		if (withinMargins(p.getCoordinates()))
			if (choiceToMove()) {
				boolean[] directions = new boolean[9];
				for (int j = 0; j < 9; j++)
					directions[j] = false;

				check = new Point((p.getCoordinates().getX()), ((Person) p).getCoordinates().getY());

				int dir = (int) (Math.random() * 9) + 1;

				check = new Point(((Person) p).getCoordinates().getX() + moveTo(dir).getX(),
						((Person) p).getCoordinates().getY() + moveTo(dir).getY());

			}

		//if (!withinMargins(check))
			//System.out.println("\nPerson has moved out of the area.");
		return withinMargins(check);
	}

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