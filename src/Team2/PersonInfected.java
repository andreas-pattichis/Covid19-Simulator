package Team2;

import java.util.ArrayList;

/**
 * PersonInfected Class is used to check and set if the people are infected by
 * their contact with other people
 * 
 * @author apatti01
 * @author aalexa02
 */
public class PersonInfected {

	private Person person; // Represents the person that will be checked if is infected
	private ArrayList ppl;
	private Person[] people; // Represents all the people that are used for the stimulation
	private int crowd; // Represents the number of people

	/**
	 * PersonInfected constructor that takes one Person and one Movement as inputs:
	 * p and m.
	 * 
	 * @param p Represents the person that will be checked if is infected
	 * 
	 * @param m Is used to get the details for all the people
	 */
	public PersonInfected(Person p, Movement m) {
		person = p;
		ppl = m.getPeople();
		crowd = m.getCrowd();
	}

	/**
	 * moveTo() method takes one input as a parameter: dir. It chooses to which
	 * direction the person will move according to the dir that is given
	 * 
	 * 1: Up, 2: Down, 3: Right, 4: Left, 5: Diagonal Up-Right, 6: Diagonal Up-Left,
	 * 7: Diagonal Down-Right, else: Diagonal Down-Left
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
		}
		return new Point(-1, -1); // Diagonal Down-Left
	}

	/**
	 * checkIfInfected() method checks if the person is infected and changes the
	 * appropriate values if needed.
	 * 
	 * @param h Represents the height of the board
	 * 
	 * @param w Represents the height of the board
	 * 
	 * @return A boolean value that indicates if the person has been infected
	 */
	public boolean checkIfInfected(int h, int w, int noMask, int mask) {
		if (person.isImmune())
			return false;
		if (person.isInfected())
			return true;
		for (int j = 0; j < 8; j++) {
			Point pNew = new Point(person.getCoordinates().getX() + moveTo(j).getX(),
					person.getCoordinates().getY() + moveTo(j).getY());

			if (person.getCoordinates().getX() >= 0 && person.getCoordinates().getY() >= 0)
				if (person.getCoordinates().getX() < h && person.getCoordinates().getY() < w) {

					for (int i = 0; i < crowd; i++)
						if (pNew.equals(((Person) ppl.get(i)).getCoordinates())
								&& (((Person) ppl.get(i)).isInfected() || person.isInfected())) {
							int random = (int) (Math.random() * 100);

							// Default probabilities
							if (noMask == -1) {
								if (person.wearsProtection() || ((Person) ppl.get(i)).wearsProtection()) {
									if (random < 40 && !person.isImmune())
										return true;
								} else {
									if (random < 70 && !person.isImmune())
										return true;
								}
							}

							// Probabilities that the use has chosen
							else {
								if (person.wearsProtection() || ((Person) ppl.get(i)).wearsProtection()) {
									if (random < mask && !person.isImmune())
										return true;
								} else {
									if (random < noMask && !person.isImmune())
										return true;
								}
							}
						}
				}
		}
		return false;
	}
}