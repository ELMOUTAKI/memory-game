/**
 * 
 */
package game.logic.common;

import static org.junit.Assert.*;

import java.util.Calendar;

import game.logic.common.GameLogicException;
import game.logic.common.HighscoreList;
import game.logic.common.HighscoreList.HighscoreEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Yousry Abdallah
 * 
 */
public class HighscoreListTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link game.logic.common.HighscoreList#HighscoreList()}.
	 */
	@Test
	public void testHighscoreList() {
		
		System.out.println("testHighscoreList()");

		
		try {

			HighscoreList highscoreList = new HighscoreList();

			for (int i = 0; i < 50; i++) {
				HighscoreEntry entry = highscoreList.getEntry(i);

				System.out.println("N:" + entry.name + "S:" + entry.score + "D:" + entry.date);

			}

		} catch (GameLogicException e) {
			fail("Could not create new Highscorelist." + e);
		}

	}

	/**
	 * Test method for {@link game.logic.common.HighscoreList#getEntry(int)}.
	 */
	@Test
	public void testGetEntry() {
		
		System.out.println("testGetEntry()");

		
		try {

			HighscoreList highscoreList = new HighscoreList();

			HighscoreEntry entry = highscoreList.getEntry(49);

			if (entry.score != 1)
				fail("wrong id");

			System.out.println("N:" + entry.name + "S:" + entry.score + "D:" + entry.date);

			HighscoreEntry entryHighscore = highscoreList.new HighscoreEntry();

			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			
			
			entryHighscore.date = Calendar.getInstance().getTime();
			entryHighscore.name = "CheckHigh";
			entryHighscore.score = 4711;

			highscoreList.setEntry(entryHighscore);

			entry = highscoreList.getEntry(49);
			System.out.println("N:" + entry.name + "S:" + entry.score + "D:" + entry.date);

			if (entry.score != 2)
				fail("wrong id");

		} catch (GameLogicException e) {
			fail("Could not generate List" + e);
		}

	}

	/**
	 * Test method for
	 * {@link game.logic.common.HighscoreList#setEntry(game.logic.common.HighscoreList.HighscoreEntry)}.
	 */
	@Test
	public void testSetEntry() {

		
		System.out.println("testSetEntry()");
		
		try {

			HighscoreList highscoreList = new HighscoreList();

			HighscoreEntry entry = highscoreList.getEntry(0);

			if (entry.score != 50)
				fail("wrong id");

			System.out.println("N:" + entry.name + "S:" + entry.score + "D:" + entry.date);

			// Insert a new Highscore and reread it.
			HighscoreEntry entryHighscore = highscoreList.new HighscoreEntry();

			entryHighscore.date = Calendar.getInstance().getTime();
			entryHighscore.name = "CheckHigh";
			entryHighscore.score = 4711;

			highscoreList.setEntry(entryHighscore);

			entry = highscoreList.getEntry(0);
			System.out.println("N:" + entry.name + "S:" + entry.score + "D:" + entry.date);

			if (entry.score != 4711)
				fail("wrong id");

		} catch (GameLogicException e) {
			fail("Could not generate List" + e);
		}

		
		
	}

	@Test
	public void testSetEntryB() {

		
		System.out.println("testSetEntryB()");
		
		try {

			HighscoreList highscoreList = new HighscoreList();


			// Insert a new Highscore and reread it.
			HighscoreEntry entryHighscore = highscoreList.new HighscoreEntry();
			entryHighscore.date = Calendar.getInstance().getTime();
			entryHighscore.date.setMonth(8);
			entryHighscore.name = "CheckLow";
			entryHighscore.score = 2;
			highscoreList.setEntry(entryHighscore);

			System.out.println("N:" + entryHighscore.name + "S:" + entryHighscore.score + "D:" + entryHighscore.date);


				for (int i = 0; i < 50; i++) {
					 HighscoreEntry entry = highscoreList.getEntry(i);
					System.out.println("N: " + entry.name + " S: " + entry.score + " D: " + entry.date);

				}
			
		} catch (GameLogicException e) {
			fail("Could not generate List" + e);
		}

		
		
	}

	
	
}
