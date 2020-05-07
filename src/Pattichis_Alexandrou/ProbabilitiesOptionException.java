package Pattichis_Alexandrou;

/**
 * class ProbabilitiesOptionException is used as an exception that is thrown to
 * catch different exceptions when the user is making input errors at the task
 * of probabilities option.
 * 
 * @author apatti01
 * @author aalexa02
 */
public class ProbabilitiesOptionException extends Exception {

	/**
	 * ProbabilitiesOptionException Constructor that stores the appropriate message.
	 */
	public ProbabilitiesOptionException(String e) {
		super(e);
	}
}