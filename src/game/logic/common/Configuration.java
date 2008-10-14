/**
 * Define the application properties
 */
package game.logic.common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author yousry
 * 
 */
public class Configuration {

	// Propertifile tag and default definitions
	static final String STRING_DEFAULT_VALUE = "default value";

	static final String STRING_HEIGHT_PROPERTY = "height";

	static final String STRING_WIDTH_PROPERTY = "width";

	static final String STRING_XPOSITION_PROPERTY = "xposition";

	static final String STRING_YPOSITION_PROPERTY = "yposition";

	protected String propertyFilename;

	final static String CONFIG_FILE_NAME = "game.properties";

	static final String VERSION = "0.1";

	protected String configFilename = "default";

	// Configuration Parameter
	private int width = 320;

	private int height = 200;

	private int xposition = 10;

	private int yposition = 10;

	private HighscoreList highscoreList = null;

	public Configuration() throws GameLogicException {
		init();
	}

	public void update() throws GameLogicException {
		writeProperties();
	}

	protected void init() throws GameLogicException {
		try {

			// identify the configuration filename in the users home directory
			String homedir = System.getProperty("user.home");
			configFilename = homedir + File.separator + CONFIG_FILE_NAME;

			// identify the default configuration from resource-bundle
			String canonicalName = this.getClass().getCanonicalName();
			propertyFilename = "/" + canonicalName.split("\\.")[0] + "/" + CONFIG_FILE_NAME;

			Properties properties = new Properties();

			// if there is no user configuration, load the default configuration
			// from the resource and create a user configuration.
			if (!new File(configFilename).exists()) {

				InputStream resourceAsStream = this.getClass().getResourceAsStream(propertyFilename);
				assert (resourceAsStream != null);
				properties.loadFromXML(resourceAsStream);
				resourceAsStream.close();

				FileOutputStream fileOutputStream = new FileOutputStream(configFilename);
				properties.storeToXML(fileOutputStream, "External Game Configuration Settings");
			} else {
				InputStream inputStream = new FileInputStream(configFilename);
				assert (inputStream != null);
				properties.loadFromXML(inputStream);
				inputStream.close();
			}

			// TODO: reread resource bundle configuration if version is
			// different.

			readProperties(properties);
			writeProperties();

		} catch (IOException e) {
			System.err.println("Could not read property file. Message: " + e);
		}
	}

	private void writeProperties() throws GameLogicException {
		try {

			Properties properties = new Properties();

			properties.put("width", Integer.toString(width));
			properties.put("height", Integer.toString(height));
			properties.put("xposition", Integer.toString(xposition));
			properties.put("yposition", Integer.toString(yposition));

//			highscoreList = new HighscoreList();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(highscoreList);
			objectOutputStream.close();
			outputStream.close();
			byte[] bs = outputStream.toByteArray();
			String highscore64 = new BASE64Encoder().encode(bs);
			properties.put("score", highscore64);
			
			FileOutputStream fileOutputStream;
			fileOutputStream = new FileOutputStream(configFilename);
			properties.storeToXML(fileOutputStream, "External Game Configuration Settings");
		} catch (FileNotFoundException e) {
			throw new GameLogicException("Could not write properties", e);
		} catch (IOException e) {
			throw new GameLogicException("Could not write properties", e);
		}

	}

	private void readProperties(Properties properties) throws GameLogicException {
		try {

			// create read Properties method
			// use the AWT Toolkit to calulate the default resolutions
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();

			String heightString = (String) properties.get(STRING_HEIGHT_PROPERTY);
			if (heightString.equals(STRING_DEFAULT_VALUE)) {
				height = screenSize.height / 2;

			} else {
				// FIX: parsing doesnt`t work.
				Integer heightInteger = Integer.decode(heightString);

				assert (heightInteger != null);
				if (heightInteger != null)
					height = heightInteger;
				else
					throw (new GameLogicException("Could not read parameter \"height\" Config"));

			}

			String widthString = (String) properties.get(STRING_WIDTH_PROPERTY);
			if (widthString.equals(STRING_DEFAULT_VALUE)) {
				width = screenSize.width / 2;
			} else {
				Integer widthInteger = Integer.decode(widthString);
				assert (widthInteger != null);
				if (widthInteger != null)
					width = widthInteger;
				else
					throw (new GameLogicException("Could not read parameter \"width\" from Config "));
			}

			String xpositionString = (String) properties.get(STRING_XPOSITION_PROPERTY);
			if (xpositionString.equals(STRING_DEFAULT_VALUE)) {
				xposition = 10;
			} else {
				Integer xpositionInteger = Integer.decode(xpositionString);
				assert (xpositionString != null);
				if (xpositionString != null)
					xposition = xpositionInteger;
				else
					throw (new GameLogicException("Could not read parameter \"width\" from Config "));
			}

			String ypositionString = (String) properties.get(STRING_YPOSITION_PROPERTY);
			if (ypositionString.equals(STRING_DEFAULT_VALUE)) {
				yposition = 10;
			} else {
				Integer ypositionInteger = Integer.decode(ypositionString);
				assert (ypositionString != null);
				if (ypositionString != null)
					yposition = ypositionInteger;
				else
					throw (new GameLogicException("Could not read parameter \"width\" from Config "));
			}

			byte[] ds;
			ds = new BASE64Decoder().decodeBuffer((String) properties.get("score"));
			ByteArrayInputStream inputStream = new ByteArrayInputStream(ds);
			 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			 highscoreList = (HighscoreList) objectInputStream.readObject();

		} catch (IOException e) {
			throw new GameLogicException("Could not read config parameters.", e);
			 } catch (ClassNotFoundException e) {
			throw new GameLogicException("Could not read config parameters.", e);
		}

	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the xposition
	 */
	public int getXposition() {
		return xposition;
	}

	/**
	 * @param xposition
	 *            the xposition to set
	 */
	public void setXposition(int xposition) {
		this.xposition = xposition;
	}

	/**
	 * @return the yposition
	 */
	public int getYposition() {
		return yposition;
	}

	/**
	 * @param yposition
	 *            the yposition to set
	 */
	public void setYposition(int yposition) {
		this.yposition = yposition;
	}

	/**
	 * @return the highscoreList
	 */
	public HighscoreList getHighscoreList() {
		return highscoreList;
	}

	/**
	 * @param highscoreList
	 *            the highscoreList to set
	 */
	public void setHighscoreList(HighscoreList highscoreList) {
		this.highscoreList = highscoreList;
	}

}
