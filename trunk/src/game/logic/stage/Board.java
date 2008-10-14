/**
 * Game 
 * (c)2008 Yousry Abdallah
 */
package game.logic.stage;

import game.logic.tile.TileLogic;

/**
 * @author yousry
 *
 */
public class Board implements Stage{

	public enum TokenPosition {
		NORTH, NORTHWEST, WEST, SOUTHWEST, SOUTH, SOUTHEAST, EAST, NORTHEAST
	}
	
	
	public enum UserSelectionState {
		PRESS_TOP, SELECT_TILE ,CHOSE_TILE, WAIT
	}

	private UserSelectionState userSelectionState = UserSelectionState.WAIT;
	
	
public UserSelectionState getUserSelectionState() {
		return userSelectionState;
	}

	public void setUserSelectionState(UserSelectionState userSelectionState) {
		this.userSelectionState = userSelectionState;
	}

	//	@Override
	public void show(boolean b) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	
	public void switchTiles() {
		
	}
	
	
	private TileLogic[] tokens;
	
	
	/**
	 * changeToken changes the positions between the token a and b
	 * @param a token at position a
	 * @param b token at position b
	 */
	public void changeTokens(TokenPosition a, TokenPosition b){
		
	}
	


	
	
	

}
