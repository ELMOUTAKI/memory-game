/**
 * 
 */
package game.logic.tile;

import game.control.PusherState;
import game.control.action.PusherAction;

/**
 * @author Yousry Abdallah
 *
 */
public class PusherLogic {

	
	private PusherAction action = new PusherAction();
	private PusherState state = new PusherState();
	/**
	 * @return the action
	 */
	public PusherAction getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(PusherAction action) {
		this.action = action;
	}
	/**
	 * @return the state
	 */
	public PusherState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(PusherState state) {
		this.state = state;
	}

	
	
}
