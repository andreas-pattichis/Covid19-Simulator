package Team2;

/**
 * NegativeNumberException is an exception that is thrown when the user enters a
 * negative value as an input.
 * 
 * @author apatti01
 * @author aalexa02
 */
public class NegativeNumberException extends Exception {

	/**
	 * NegativeNumberException default Constructor that stores the appropriate message.
	 */
	public NegativeNumberException() {
		super("\nYou need to give possitive numbers!!\nPlease try again\n");
	}

	/**
	 * NegativeNumberException Constructor that stores the appropriate message.
	 */
	public NegativeNumberException(String x) {
		super(x);
	}
}