/**
 * 
 */
package game.control;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yousry Abdallah
 * 
 */
public class LevelDescriptionTest {

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
	 * Test method for {@link game.control.LevelDescription#LevelDescription()}.
	 */
	@Test
	public void testLevelDescription() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link game.control.LevelDescription#LevelDescription(int)}.
	 */
	@Test
	public void testLevelDescriptionInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.control.LevelDescription#init()}.
	 */
	@Test
	public void testInit() {
		try {
			LevelDescription levelDescription = new LevelDescription();

			levelDescription.toString();

		} catch (GameControlException e) {
			fail("Could not initialize LvlDescription: " + e);
		}

	}

	/**
	 * Test method for {@link game.control.LevelDescription#setLevel(int)}.
	 */
	@Test
	public void testSetLevel() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.control.LevelDescription#getBoards()}.
	 */
	@Test
	public void testGetBoards() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link game.control.LevelDescription#getMovementsPerBoard()}.
	 */
	@Test
	public void testGetMovementsPerBoard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link game.control.LevelDescription#isMovedTilesVisibility()}.
	 */
	@Test
	public void testIsMovedTilesVisibility() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.control.LevelDescription#isBoardSpin()}.
	 */
	@Test
	public void testIsBoardSpin() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link game.control.LevelDescription#getLevel()}.
	 */
	@Test
	public void testGetLevel() {
		fail("Not yet implemented");
	}

}
