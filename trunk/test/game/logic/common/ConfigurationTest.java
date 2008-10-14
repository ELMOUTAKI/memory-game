/**
 * (c) 2008 Yousry Abdallah
 */
package game.logic.common;

import static org.junit.Assert.*;
import game.logic.common.Configuration;
import game.logic.common.GameLogicException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yousry
 * 
 */
public class ConfigurationTest {

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
	 * Test method for {@link game.logic.common.Configuration#init()}.
	 */
	@Test
	public void testInit() {
		try {
			Configuration configuration = new Configuration();
			System.out.println(configuration.propertyFilename);
			System.out.println(configuration.configFilename);

			System.out.println("Hoehe:" + configuration.getHeight());
			System.out.println("Breite:" + configuration.getWidth());
			System.out.println("xposition: " + configuration.getXposition());
			System.out.println("yposition: " + configuration.getYposition());
			System.out.println("Highscores:" + configuration.getHighscoreList());

		} catch (GameLogicException e) {
			fail("Configuration Init failed.");
		}
	}

}
