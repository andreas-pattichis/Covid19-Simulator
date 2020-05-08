package Team2;

/**
 * OvercrowdedException is an exception that is thrown when the user chooses to
 * have too many people in one place.
 * 
 * @author apatti01
 * @author aalexa02
 */
public class OvercrowdedException extends Exception {

	/**
	 * OvercrowdedException default Constructor that stores the appropriate message.
	 */
	public OvercrowdedException() {
		super("\nThe people should be less than the radius of the board.\nPease try again!\n");
	}
	
	/**
	 * OvercrowdedException Constructor that stores the appropriate message.
	 */
	public OvercrowdedException(String x) {
		super(x);
	}
}