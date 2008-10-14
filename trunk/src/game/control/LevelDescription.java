/**
 * 
 */
package game.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Pattern;

import sun.misc.Regexp;

/**
 * @author Yousry Abdallah
 * 
 */
public class LevelDescription {

	private class OneLVL implements Comparable<OneLVL> {
		public int boards;

		public int movementsPerBoard;

		public boolean movedTilesVisibility = true;

		public boolean boardSpin = false;

		@Override
		public String toString() {
			return "B:" + boards + "M:" + movementsPerBoard;
		}

//		@Override
		public int compareTo(OneLVL o) {
			if (this.boards == o.boards && this.movementsPerBoard == o.movementsPerBoard
					&& this.movedTilesVisibility == o.movedTilesVisibility && this.boardSpin == o.boardSpin)
				return 0;
			else
				return 1;
		}

	}

	private int MAX_LEVEL = 2500;

	private Properties properties = new Properties();

	// LVL description.
	private static final String PROPERTIES_LEVEL_DESCRIPTION = "levelDescription.properties";

	protected String propertyFilename;

	// parameter
	private int level = 1;

	private int boards = 1;

	private int movementsPerBoard = 1;

	private boolean movedTilesVisibility = true;

	private boolean boardSpin = false;

	public LevelDescription() throws GameControlException {
		init();
		setLevel(1);

	}

	public LevelDescription(int level) throws GameControlException {
		init();
		setLevel(level);
	}

	public void init() throws GameControlException {
		try {
			String canonicalName = this.getClass().getCanonicalName();
			propertyFilename = "/" + canonicalName.split("\\.")[0] + "/" + PROPERTIES_LEVEL_DESCRIPTION;

			InputStream resourceAsStream = this.getClass().getResourceAsStream(propertyFilename);
			assert (resourceAsStream != null);
			properties.loadFromXML(resourceAsStream);
			resourceAsStream.close();

			// Set<Integer> scores = new TreeSet<Integer>();
			//			
			// for (Object i : properties.keySet())
			// scores.add(Integer.decode((String) i));
			//			
			//			
			// for(int i : scores) {
			// System.out.println(i);
			// }

			ArrayList<OneLVL> arrayList = new ArrayList<OneLVL>();

			// initial setup of OneLVL

			OneLVL oneLVL = new OneLVL();
			OneLVL newLvl = new OneLVL();

			oneLVL.boards = 1;
			oneLVL.movementsPerBoard = 1;
			oneLVL.movedTilesVisibility = true;
			oneLVL.boardSpin = false;

			for (int i = 0; i <= MAX_LEVEL; i++) {
				String lvlDescriptor = (String) properties.get("" + i);
				if (lvlDescriptor != null) {

					// parse with following regexp:
					// (?i)^Boards\s*=\s*(.*)\s*;\s*movementsPerBoard\s*=\s*(.*)\s*;\s*movedTilesVisibility\s*=\s*(.*)\s*;\s*boardSpin\s*=\s*(.*)$

					String propertyParser = "(?i)^Boards\\s*=\\s*(.*)\\s*;\\s*movementsPerBoard\\s*=\\s*(.*)\\s*;\\s*movedTilesVisibility\\s*=\\s*(.*)\\s*;\\s*boardSpin\\s*=\\s*(.*)$";

					newLvl.boards = Integer.decode(lvlDescriptor.replaceAll(propertyParser, "$1"));
					newLvl.movementsPerBoard = Integer.decode(lvlDescriptor.replaceAll(propertyParser, "$2"));
					newLvl.movedTilesVisibility = Boolean.valueOf(lvlDescriptor.replaceAll(propertyParser, "$3"));
					newLvl.boardSpin = Boolean.valueOf(lvlDescriptor.replaceAll(propertyParser, "$4"));
					
				}

				
				if (oneLVL.compareTo(newLvl) != 0) {
					oneLVL = newLvl;
					System.out.println("new Entity");
				}

				arrayList.add(oneLVL);
				System.out.println( i  );

				
			}


		} catch (IOException e) {
			throw new GameControlException("Could not Read LVL-Description", e);
		}

	}

	public void setLevel(int level) throws GameControlException {
		assert (level > 0);

		if (level <= 0)
			throw new GameControlException("illegal Level specified.");

	}

	/**
	 * @return the boards
	 */
	public int getBoards() {
		return boards;
	}

	/**
	 * @return the movementsPerBoard
	 */
	public int getMovementsPerBoard() {
		return movementsPerBoard;
	}

	/**
	 * @return the movedTilesVisibility
	 */
	public boolean isMovedTilesVisibility() {
		return movedTilesVisibility;
	}

	/**
	 * @return the boardSpin
	 */
	public boolean isBoardSpin() {
		return boardSpin;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

}
