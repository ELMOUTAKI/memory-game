/**
 * 
 */
package game.control;

/**
 * @author Yousry Abdallah
 *
 */
public class GameControlException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -476571445765383666L;

	
	
	/**
	 * 
	 */
	public GameControlException() {
	}

	/**
	 * @param message
	 */
	public GameControlException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public GameControlException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GameControlException(String message, Throwable cause) {
		super(message, cause);
	}

	
}
