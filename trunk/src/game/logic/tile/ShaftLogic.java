package game.logic.tile;

import game.control.ShaftState;
import game.control.action.ShaftAction;

public class ShaftLogic {

	
	private ShaftAction action = new ShaftAction();
	private ShaftState state = new ShaftState();
	/**
	 * @return the action
	 */
	public ShaftAction getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(ShaftAction action) {
		this.action = action;
	}
	/**
	 * @return the state
	 */
	public ShaftState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(ShaftState state) {
		this.state = state;
	}


	
	
}
