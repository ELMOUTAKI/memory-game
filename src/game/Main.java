/**
 * Game 
 * (c)2008,2009 Yousry Abdallah
 * Released under GPL V3
 */

package game;

import game.logic.common.Score;
import game.control.ControlService;
import game.logic.stageLogic;
import game.logic.common.Configuration;
import game.logic.common.Game;
import game.logic.common.GameLogicException;
import game.logic.common.HighscoreList;
import game.logic.common.Title;
import game.logic.common.Game.GameMode;
import game.logic.common.HighscoreList.HighscoreEntry;
import game.scene.GameUniverse;
import game.scene.ScoreUniverse;
import game.scene.TitleUniverse;

import java.awt.GraphicsConfiguration;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.reflect.InvocationTargetException;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.View;
import javax.swing.JFrame;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * Main Class for the game
 * 
 * @author yousry
 * 
 */
public class Main implements ComponentListener {

	// enter the nonstatic world
	static Main main = null;

	// 2D components

	private JFrame frame = null;

	private javax.swing.JPanel drawingPanel;

	// 3D Components

	private GameUniverse gameUniverse = null;

	private TitleUniverse titleUniverse = null;

	private ScoreUniverse scoreUniverse = null;

	private Canvas3D gameCanvas = null;

	private Canvas3D titleCanvas = null;

	private Canvas3D scoreCanvas = null;

	// Logic Components

	Configuration configuration = null;

	public Main() {
		try {
			configuration = new Configuration();

			ControlService.getService().setConfiguration(configuration);

		} catch (GameLogicException e) {
			e.printStackTrace();
		}

	}

	private void createUniverses() {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		titleCanvas = new Canvas3D(config);
		titleUniverse = new TitleUniverse(titleCanvas);
		titleUniverse.getViewingPlatform().setNominalViewingTransform();
		titleUniverse.getViewer().getView().setMinimumFrameCycleTime(24);
		titleUniverse.getViewer().getView().setProjectionPolicy(View.PERSPECTIVE_PROJECTION);

		// TODO: remove on release
//		titleUniverse.getViewer().getView().setSceneAntialiasingEnable(true);

		
		config = SimpleUniverse.getPreferredConfiguration();
		gameCanvas = new Canvas3D(config);
		gameUniverse = new GameUniverse(gameCanvas);
		gameUniverse.getViewingPlatform().setNominalViewingTransform();
		gameUniverse.getViewer().getView().setMinimumFrameCycleTime(24);
		gameUniverse.getViewer().getView().setProjectionPolicy(View.PERSPECTIVE_PROJECTION);

		// TODO: remove on release
//		gameUniverse.getViewer().getView().setSceneAntialiasingEnable(true);

		
		config = SimpleUniverse.getPreferredConfiguration();
		scoreCanvas = new Canvas3D(config);
		scoreUniverse = new ScoreUniverse(scoreCanvas);
		scoreUniverse.getViewingPlatform().setNominalViewingTransform();
		scoreUniverse.getViewer().getView().setMinimumFrameCycleTime(24);
		scoreUniverse.getViewer().getView().setProjectionPolicy(View.PERSPECTIVE_PROJECTION);

		// TODO: remove on release
//		scoreUniverse.getViewer().getView().setSceneAntialiasingEnable(true);

		
		
	}

	private void changeStage(Canvas3D canvas3D) {

		drawingPanel.remove(drawingPanel.getComponent(0));
		drawingPanel.add(canvas3D);

		frame.setSize(configuration.getWidth(), configuration.getHeight() + 1);
		frame.setSize(configuration.getWidth(), configuration.getHeight() - 1);

	}

	private void createAndShowGui() {

		frame = new JFrame();
		drawingPanel = new javax.swing.JPanel();

		// frame.setUndecorated(true);

		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("GME");
		drawingPanel.setLayout(new java.awt.BorderLayout());
		drawingPanel.setPreferredSize(new java.awt.Dimension(250, 250));

		createUniverses();

		try {
			titleUniverse.initialize();
			gameUniverse.initialize();
			scoreUniverse.initialize();
		} catch (GameLogicException e) {
			System.out.println("Error while initializing 3d universe:");
			e.printStackTrace();
		}

		drawingPanel.add(titleCanvas, java.awt.BorderLayout.CENTER);
		frame.getContentPane().add(drawingPanel, java.awt.BorderLayout.CENTER);

		frame.pack();

		frame.setSize(configuration.getWidth(), configuration.getHeight());
		frame.setLocation(configuration.getXposition(), configuration.getYposition());
		frame.addComponentListener(this);

		frame.setVisible(true);

	}

	void runOnce() {

		GameMode gameMode = GameMode.TITLE;
		stageLogic logic = new Title();

		while (true) {

			// Mode changed
			if (gameMode != ControlService.getService().getActualGameMode()) {
				gameMode = ControlService.getService().getActualGameMode();

				switch (gameMode) {
				case GAME:
					changeStage(gameCanvas);
					logic = new Game();
					logic.init();
					break;
				case SCORE:
					changeStage(scoreCanvas);
					logic = new Score();
					logic.init();
					break;
				case TITLE:
					changeStage(titleCanvas);
					logic = new Title();
					logic.init();
					break;
				}

			}

			logic.run();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// never
			}
			
			
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	public static void main(String args[]) throws InterruptedException, InvocationTargetException {

		main = new Main();

		javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				main.createAndShowGui();

			}
		});

		main.runOnce();

	}

	// @Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void componentMoved(ComponentEvent e) {

		try {
			configuration.setXposition(frame.getLocationOnScreen().x);
			configuration.setYposition(frame.getLocationOnScreen().y);
			configuration.update();
		} catch (GameLogicException e1) {
			e1.printStackTrace();
		}

	}

	// @Override
	public void componentResized(ComponentEvent e) {

		try {
			configuration.setHeight(frame.getHeight());
			configuration.setWidth(frame.getWidth());
			configuration.update();
		} catch (GameLogicException e1) {
			e1.printStackTrace();
		}
	}

	// @Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}
}
