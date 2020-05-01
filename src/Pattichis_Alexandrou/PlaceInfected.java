package Pattichis_Alexandrou;

import edu.princeton.cs.introcs.StdDraw; // Library to import the StdDraw to the package

/**
 * PlaceInfected Class is used to check and set if the place is infected and if
 * it will affect the person as well.
 * 
 * PlaceInfected extends to the class Movement
 * 
 * @author apatti01
 * @author aalexa02
 */
public class PlaceInfected extends Movement {
	private static int[][] duration; // Stores the duration that each of the places will be infected
	private Person[] p; // A type Person array that stores all the information for the people
	private int time; // The time that the simulation will be ran

	/**
	 * PlaceInfected constructor that takes four integers and one Person[] as
	 * inputs:h,w,c,t,p. It calls the super constructor for h,w and c super(h,w,c).
	 * It also creates an int array for duration(height = h, width = w) that is
	 * initialized to 0.
	 * 
	 * @param h Represents the width
	 * 
	 * @param w Represents the width
	 * 
	 * @param c Represents the crowd
	 * 
	 * @param t Represents the time that the simulation will be ran
	 * 
	 * @param p Represents a type Person array that stores all the information for
	 *          the people
	 */
	public PlaceInfected(int h, int w, int c, int t, Person[] p) {
		super(h, w, c);
		this.p = p;
		time = t;
		duration = new int[w][h];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				duration[i][j] = 0;
			}
		}
	}

	/**
	 * setDuration() method that takes one Person type array as a parameter: p. It
	 * sets the duration by checking if each of theses persons are infected and not
	 * wearing mask for protection.
	 * 
	 * @param p A type Person array that stores all the information for the people
	 */
	public void setDuration(Person[] p) {
		for (int i = 0; i < crowd; i++) {
			if (p[i].isInfected() && !p[i].wearsProtection())
				duration[p[i].getCoordinates().getX()][p[i].getCoordinates().getY()] = time + 1;
			else if (p[i].isInfected() && p[i].wearsProtection())
				duration[p[i].getCoordinates().getX()][p[i].getCoordinates().getY()] = time;
		}
	}

	/**
	 * placeAffectsPeople() method that takes one Person type array as a parameter:
	 * p. It sets if people are affected by the places
	 * 
	 * @param p A type Person array that stores all the information for the people
	 */
	public void placeAffectsPeople(Person[] p,int noMask,int mask) {
		int x = (int) Math.random() * 100;
		for (int i = 0; i < crowd; i++) {
			if (duration[p[i].getCoordinates().getX()][p[i].getCoordinates().getY()] > 0 && !p[i].isImmune())
				if(noMask==-1) {
				if (p[i].wearsProtection() && x < 30)
					p[i].setInfected(true);
				else if (!p[i].wearsProtection() && x < 60)
					p[i].setInfected(true);}
				else {
					if (p[i].wearsProtection() && x <mask)
						p[i].setInfected(true);
					else if (!p[i].wearsProtection() && x < noMask)
						p[i].setInfected(true);}
		}
	}

	/**
	 * PrintInfection() method that makes the places that are infected ORANGE and
	 * YELLOW
	 */
	public void PrintInfection() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (duration[i][j] > 0) {
					if (duration[i][j] <= 2) {
						StdDraw.setPenColor(StdDraw.YELLOW);
						StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
						duration[i][j]--;
					} else if (duration[i][j] > 2) {
						StdDraw.setPenColor(StdDraw.ORANGE);
						StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
						duration[i][j]--;
					}
				}
			}
		}
	}

}