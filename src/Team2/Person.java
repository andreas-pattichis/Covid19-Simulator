package Team2;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

/**
 * The class Person represents a person from the crowd that is used in the
 * simulation
 * 
 * @author apatti01
 * @author aalexa02
 */
public class Person {
	private Point coordinates; // Stores the coordinates of the person's current position
	private boolean immune; // Indicates if the person can get the virus
	private boolean infected; // Indicates if the person is infected
	private boolean wearsProtection; // Indicates if the person wears protection
	private Color color; // Green: Person is not infected, Red: Person is infected, Blue: Person is

	/**
	 * Person constructor that takes two integers as an input: x and y. It stores x
	 * and y to coordinates as a new Point(x,y).
	 * 
	 * @param x   The x-coordinate of the point
	 * 
	 * @param y   The y-coordinate of the point
	 * 
	 * @param inf Indicates if the person is infected
	 * 
	 * @param im  Indicates if the person is immutable
	 */
	public Person(int x, int y, boolean inf, boolean im) {
		coordinates = new Point(x, y); // Stores the current position of the person
		int MaskRandom = (int) (Math.random() * 100); // Chooses randomly if the person wears mask

		infected = inf;
		immune = im;

		// Sets the appropriate color
		if (MaskRandom < 60)
			wearsProtection = true;
		else
			wearsProtection = false;

		if (immune)
			color = StdDraw.BLUE;
		if (!immune && !infected)
			color = StdDraw.GREEN.darker().darker();
		else if (!immune && infected)
			color = StdDraw.RED;

	}

	/**
	 * drawPerson() method draws a person every time it is called using StdDraw
	 */
	public void drawPerson() {
		StdDraw.setPenColor(color); // Sets the person's t-shirt the appropriate color
		StdDraw.setPenRadius(0.06);
		// Draws the body of the person
		StdDraw.filledEllipse(coordinates.getX() + 0.5, coordinates.getY() + 0.5, 0.35, 0.1);
		StdDraw.setPenColor(217, 179, 130); // Dark beige color
		// Draws the head and hair of the person
		StdDraw.filledCircle(coordinates.getX() + 0.5, coordinates.getY() + 0.56, 0.15);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledEllipse(coordinates.getX() + 0.5, coordinates.getY() + 0.5, 0.17, 0.13);
		// Draws the mask to the person
		if (wearsProtection) {
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.setPenRadius(0.005);
			StdDraw.line(coordinates.getX() + 0.5 - 0.15, coordinates.getY() + 0.5 + 0.1,
					coordinates.getX() + 0.5 - 0.05, coordinates.getY() + 0.5 + 0.18);
			StdDraw.line(coordinates.getX() + 0.5 + 0.15, coordinates.getY() + 0.5 + 0.1,
					coordinates.getX() + 0.5 + 0.05, coordinates.getY() + 0.5 + 0.18);
			StdDraw.setPenColor(StdDraw.GREEN.brighter());
			StdDraw.filledEllipse(coordinates.getX() + 0.50, coordinates.getY() + 0.68, 0.10, 0.03);
			StdDraw.setPenRadius(0.03);

		}

	}

	/**
	 * Getter method for this.color
	 * 
	 * @return value of this.color
	 * 
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setter method for this.color
	 * 
	 * @param c The value that this.color will get.
	 */
	public void updateColor(Color c) {
		color = c;
	}

	/**
	 * Getter method for this.coordinates
	 * 
	 * @return value of this.color
	 * 
	 */
	public Point getCoordinates() {
		return coordinates;
	}

	/**
	 * Setter method for this.coordinates
	 * 
	 * @param p The point that this.coordinates will get.
	 */
	public void updateCoordinates(Point p) {
		coordinates = new Point(p.getX(), p.getY());
	}

	/**
	 * Getter method for this.wearsProtection
	 * 
	 * @return value of this.wearsProtection
	 * 
	 */
	public boolean wearsProtection() {
		return wearsProtection;
	}

	/**
	 * Setter method for this.wearsProtection
	 * 
	 * @param wearsProtection The value that this.wearsProtection will get.
	 */
	public void setWearsProtection(boolean wearsProtection) {
		this.wearsProtection = wearsProtection;
	}

	/**
	 * Getter method for this.infected
	 * 
	 * @return value of this.infected
	 */
	public boolean isInfected() {
		return infected;
	}

	/**
	 * Setter method for this.infected
	 * 
	 * @param infected The value that this.infected will get.
	 */
	public void setInfected(boolean infected) {
		this.infected = infected;
		if (this.infected)
			color = StdDraw.RED;
	}

	/**
	 * Getter method for this.immune
	 * 
	 * @return value of this.immune
	 */
	public boolean isImmune() {
		return immune;
	}

	/**
	 * Setter method for this.immune
	 * 
	 * @param infected The value that this.immune will get.
	 */
	public void SetImmune(boolean immune) {
		this.immune = immune;
	}
}