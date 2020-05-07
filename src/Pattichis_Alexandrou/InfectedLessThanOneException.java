package Pattichis_Alexandrou;

/**
 * InfectedLessThanOneException is an exception that is thrown when the user
 * enters a value for the infected people that is less than 1.
 * 
 * @author apatti01
 * @author aalexa02
 */

public class InfectedLessThanOneException extends Exception {
	/**
	 * InfectedLessThanOneException Constructor that stores the appropriate message.
	 */
	public InfectedLessThanOneException() {
		super("\nInfected people sould be 1 or more.\n");
	}
}
