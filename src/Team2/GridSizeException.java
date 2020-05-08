package Team2;

/**
 * GridSizeException is an exception that is thrown when the user enters either
 * a height or a width value for the board that is too big and it will take a
 * lot of time for the program to draw the simulation. So we ask for the user to
 * add a value that is below 50.
 * 
 * @author apatti01
 * @author aalexa02
 */
public class GridSizeException extends Exception {

	/**
	 * OvercrowdedException Constructor that stores the appropriate message.
	 */
	public GridSizeException() {
		super("\nBoth height and width have to be under 50.\nPease try again!\n");
	}
}