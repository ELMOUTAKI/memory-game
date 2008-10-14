package game.control;

import game.logic.stage.Board;
import game.logic.stage.Board.TokenPosition;
import game.logic.tile.TileLogic;
import game.control.action.TileAction.Hight;
import game.logic.tile.TileLogic.Value;

public class TileState {
	
	public enum Side {
		FRONT, BACK
	}

	

	
	private boolean inProcess;

	// position on board
	private TokenPosition tokenPosition;
	// height 
	private Hight level;
	// front or backside
	private Side side; 

	// speed of movement
	private float speed;

	// Tile Value
	private Value tileValue;
	
	
	/**
	 * @return the inProcess
	 */
	public boolean isInProcess() {
		return inProcess;
	}

	/**
	 * @param inProcess the inProcess to set
	 */
	public void setInProcess(boolean inProcess) {
		this.inProcess = inProcess;
	}

	/**
	 * @return the tokenPosition
	 */
	public TokenPosition getTokenPosition() {
		return tokenPosition;
	}

	/**
	 * @param tokenPosition the tokenPosition to set
	 */
	public void setTokenPosition(TokenPosition tokenPosition) {
		this.tokenPosition = tokenPosition;
	}

	/**
	 * @return the level
	 */
	public Hight getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Hight level) {
		this.level = level;
	}

	/**
	 * @return the side
	 */
	public Side getSide() {
		return side;
	}

	/**
	 * @param side the side to set
	 */
	public void setSide(Side side) {
		this.side = side;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @return the tileValue
	 */
	public Value getTileValue() {
		return tileValue;
	}

	/**
	 * @param tileValue the tileValue to set
	 */
	public void setTileValue(Value tileValue) {
		this.tileValue = tileValue;
	}
	
	
	
	
	
}
