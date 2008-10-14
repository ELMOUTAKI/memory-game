/**
 * Game 
 * (c)2008 Yousry Abdallah
 */
package game.logic.stage;

/** The game shows three different stages. The title, the board, and the highscorelist.
 * @author yousry
 *
 */
public interface Stage {

	/** Show or hide the stage
	 * @param b
	 */
	public void show( boolean b);
	
	
	/**
	 * restarts the current stage.
	 */
	public void restart();
	
	
}
