package Pattichis_Alexandrou;

import edu.princeton.cs.introcs.StdDraw; // Library to import the StdDraw to the package

/**
 * The class Point represents a point (x,y)
 * 
 * @author apatti01
 * @author aalexa02
 *
 */
public class Point {

	private int x; // The x-coordinate of the point
	private int y; // The y-coordinate of the point

	/**
	 * Point constructor that takes two integers as an input: x and y. It stores x
	 * in this.x and y in this.y
	 * 
	 * @param x The x-coordinate of the point
	 * 
	 * @param y The y-coordinate of the point
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Point constructor is a copy constructor that takes a Point as an input: p. It
	 * stores x-coordinate of p to this.x and y-coordinate of p to this.y using
	 * getX() and getY() methods.
	 */
	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	/**
	 * equals() method has one Object as a parameter: other. It checks if two points
	 * are equal.
	 * 
	 * @return A boolean value that indicates if two Points are the same.
	 */
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (getClass() != other.getClass())
			return false;

		Point point = (Point) other;

		if (x == point.getX() && y == point.getY())
			return true;

		return false;
	}

	/**
	 * Setter method for this.x
	 * 
	 * @param x The value that this.x will get.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter method for this.x
	 * 
	 * @return value of this.x
	 * 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter method for this.y
	 * 
	 * @param y The value that this.y will get.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getter method for this.y
	 * 
	 * @return value of this.y
	 * 
	 */
	public int getY() {
		return y;
	}
}
