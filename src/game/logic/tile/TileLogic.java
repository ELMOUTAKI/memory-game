/**
 * Game 
 * (c)2008 Yousry Abdallah
 */
package game.logic.tile;

import game.control.TileState;
import game.control.action.TileAction;
import game.logic.stage.Board.TokenPosition;

/**
 * Definition of the token. the token is defined by its value, visibility and the actual position
 * @author yousry
 *
 */
public class TileLogic {

	/**
	 * the token values represented by the four elements.
	 * @author yousry
	 *
	 */
	public enum Value {
		EARTH, FIRE, WATER, AIR
	}
	
	private boolean visible;
	private Value value;

	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	private TokenPosition position;
	
	public TokenPosition getPosition() {
		return position;
	}
	public void setPosition(TokenPosition position) {
		this.position = position;
	}
	private TileAction action = new TileAction();
	private TileState state = new TileState();

	public TileAction getAction() {
		return action;
	}
	public void setAction(TileAction action) {
		this.action = action;
	}
	
	
}
