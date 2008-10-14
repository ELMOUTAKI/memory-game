/**
 * 
 */
package game.logic.common;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author yousry
 * 
 */

// TODO: we implement serializable for the property entry sigh
// TODO: improve it with signal handler. to generate a smooth update
public class HighscoreList implements Serializable {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 7622130773617422973L;

	public class HighscoreEntry implements Serializable, Comparable<HighscoreEntry> {

		/**
		 * id for highscore entry
		 */
		private static final long serialVersionUID = -3889621985702140002L;

		public int score = 0;

		public String name = "unknown";

		public Date date = Calendar.getInstance().getTime();

		public HighscoreEntry() {

		}

		public HighscoreEntry(int score, String name, Date date) {
			this.score = score;
			this.name = name;
			this.date = date;
		}

		// @Override
		public int compareTo(HighscoreEntry o) {

			int myScore = this.score;
			Date myDate = this.date;
			int compareScore = o.score;
			Date compareDate = o.date;

			int result = 0;

			if (myScore > compareScore)
				result = -1;
			if (myScore == compareScore) {

				if (myDate.compareTo(compareDate) == 0)
					result = 0;

				if (myDate.compareTo(compareDate) < 0)
					result = -1;

				if (myDate.compareTo(compareDate) > 0)
					result = 1;

			}

			if (myScore < compareScore)
				result = 1;

			return result;
		}
	}

	private int SIZE = 50;

	private SortedSet<HighscoreEntry> highscores = Collections.synchronizedSortedSet(new TreeSet<HighscoreEntry>());

	// create a new empty HighscoreList
	public HighscoreList() {
		for (int i = 0; i < SIZE; i++) {
			highscores.add(new HighscoreEntry(SIZE - i, "unknown", Calendar.getInstance().getTime()));
		}
	}

	public HighscoreEntry getEntry(int position) throws GameLogicException {
		assert (position < 1 || position > SIZE);

		HighscoreEntry[] entries = (HighscoreEntry[]) highscores.toArray(new HighscoreEntry[highscores.size()]);
		return entries[position];
	}

	public void setEntry(HighscoreEntry entry) {
		boolean check = highscores.add(entry);

		
		System.out.println("SetEntry: " + check);
		
		// remove small scores
		while (highscores.size() > SIZE) {
			highscores.remove(highscores.last());
		}
	}

	public boolean checkScore(int score) {

		return (highscores.last().score < score);

	}

}
