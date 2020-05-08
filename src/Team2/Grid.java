package Team2;

import edu.princeton.cs.introcs.StdDraw; // Library to import the StdDraw to the package

/**
 * Grid Class represents the set of locations that people can move in the
 * simulation.
 * 
 * @author apatti01
 * @author aalexa02
 */
public class Grid {
	protected int height; // Indicates the height of the grid
	protected int width; // Indicates the width of the grid
	protected boolean[][] infectedPlaces; // Indicates every coordinate on the grid
											// and shows if the place at the current coordinate is infected by the virus

	/**
	 * Grid constructor that takes two integers as inputs: h and w. It creates a two
	 * dimensional boolean array with this measurements that indicate every place
	 * people can move and if it is infected or not
	 * 
	 * @param h Indicates the height of the grid.
	 * 
	 * @param w Indicates the width of the grid
	 */
	public Grid(int h, int w) {
		height = h;
		width = w;
		// Before the people are created all the places are not infected
		infectedPlaces = new boolean[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				infectedPlaces[i][j] = false;
			}
		}
	}

	/**
	 * Draws the grid using StdDraw.
	 * 
	 * @param max Indicates Math.max(height,width)
	 */
	public void createGrid(int max) {
		StdDraw.setXscale(-0.5, max + 0.5);
		StdDraw.setYscale(-0.5, max + 0.5);
		StdDraw.setPenRadius(0.005); // Pen radius is set to 0.005
		StdDraw.setPenColor(StdDraw.BLACK.brighter()); // Grid's color is black

		// Draws the horizontal lines of the grid
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				StdDraw.line(i, j, i, j + 1);
			}
		}

		// Draws the vertical lines of the grid
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				StdDraw.line(i, j, i + 1, j);
			}
		}

		StdDraw.line(width, 0, width, height);
		StdDraw.line(0, height, width, height);
	}

}