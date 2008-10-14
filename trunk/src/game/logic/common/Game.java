/**
 * Game 
 * (c)2008 Yousry Abdallah
 */

package game.logic.common;

import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

import game.control.ControlService;
import game.logic.stageLogic;
import game.logic.common.HighscoreList.HighscoreEntry;
import game.logic.stage.Stage;

import game.logic.stage.Board;
import game.logic.stage.Board.TokenPosition;
import game.logic.stage.Board.UserSelectionState;
import game.logic.tile.TileLogic.Value;

/**
 * Logic representation for the game
 * 
 * @author yousry
 * 
 */
public class Game implements stageLogic {

	public enum GameMode {
		TITLE, SCORE, GAME
	}

	public enum StageState {
		Board, Score, Title
	}

	public Game() {
		// initialize the main parameter
	}

	// number of boards
	private int boards = 1;

	// in training mode you can reduce the tilesets beyond 4
	private int TileSets = 4;

	// Level definition

	// ative Stage on screen
	private Stage activeStage;

	private int level;

	public void changeStage(StageState state) {

	}

	public void demo() {

		for (Board.TokenPosition position : Board.TokenPosition.values()) {
			ControlService.getService().showTile(position);
		}

		for (Board.TokenPosition position : Board.TokenPosition.values()) {
			ControlService.getService().hideTile(position);
		}

		for (Board.TokenPosition position : Board.TokenPosition.values()) {
			ControlService.getService().onBelt(position);
		}

		for (Board.TokenPosition position : Board.TokenPosition.values()) {
			ControlService.getService().fromBelt(position);
		}

		Random r = new Random();
		Board.TokenPosition values[] = Board.TokenPosition.values();

		for (int i = 0; i <= 4; i++) {

			ControlService.getService().addScore(1);

			int start = r.nextInt(7);
			int end = start;
			while (end == start)
				end = r.nextInt(7);

			Board.TokenPosition positionStart = values[start];
			Board.TokenPosition positionEnd = values[end];

			ControlService.getService().showTiles(positionStart, positionEnd);
			ControlService.getService().switchTiles(positionStart, positionEnd);
			ControlService.getService().showTiles(positionStart, positionEnd);
			ControlService.getService().hideTiles(positionStart, positionEnd);

		}
	}

	/**
	 * change and start the representing state.
	 * 
	 * @param stageState
	 */
	public void startState(StageState stageState) {

	}

	private void changeBranch() {

	}

	@Override
	public void init() {
		ControlService.getService().clearScore();
		for (Board.TokenPosition position : Board.TokenPosition.values()) {
			ControlService.getService().showTile(position);
		}

		ControlService.getService().getBoard().setUserSelectionState(UserSelectionState.PRESS_TOP);

	}

	@Override
	public void run() {

		// demo();

		ControlService controlService = ControlService.getService();

		UserSelectionState selectionState = controlService.getBoard().getUserSelectionState();

		Board.TokenPosition values[] = Board.TokenPosition.values();
		Random r = new Random();

		switch (selectionState) {
		case PRESS_TOP:

			if (controlService.isTopPressed()) {
				controlService.setTopPressed(false);
				for (Board.TokenPosition position : Board.TokenPosition.values()) {
					ControlService.getService().hideTile(position);
				}
				controlService.getBoard().setUserSelectionState(UserSelectionState.WAIT);
			}

			break;

		case WAIT:

			int start = r.nextInt(7);
			int end = start;
			while (end == start)
				end = r.nextInt(7);

			Board.TokenPosition positionStart = values[start];
			Board.TokenPosition positionEnd = values[end];
			ControlService.getService().switchTiles(positionStart, positionEnd);

			controlService.getBoard().setUserSelectionState(UserSelectionState.SELECT_TILE);

			break;

		case SELECT_TILE:
			int selection = r.nextInt(7);
			Board.TokenPosition randomSelection = values[selection];
			controlService.showTile(randomSelection);
			controlService.getBoard().setUserSelectionState(UserSelectionState.CHOSE_TILE);

			Value tileValue = controlService.getTile(randomSelection).getValue();

			controlService.setMatchingValue(tileValue);
			controlService.setMatchingPosition(randomSelection);

			// reset input
			controlService.setSelectionMade(false);

			// TODO: Remove on Release: Cheat !!
			// System.out.println("");
			// controlService.printCheatTileMap();

			break;

		case CHOSE_TILE:

			if (controlService.isSelectionMade()) {
				controlService.setSelectionMade(false);

				TokenPosition selectedPosition = controlService.getSelectionPosition();
				Value selectedValue = controlService.getSelectionValue();

				TokenPosition matchingPosition = controlService.getMatchingPosition();
				Value matchingValue = controlService.getMatchingValue();

				controlService.showTile(selectedPosition);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// never
				}

				// TODO: clean me up PLEASE!

				// picked the shown element
				if (selectedPosition == matchingPosition) {
					System.out.println("Same tile picked.");
					break;
				}

				// right choice
				if (selectedValue == matchingValue) {
					controlService.hideTiles(selectedPosition, matchingPosition);
					controlService.getBoard().setUserSelectionState(UserSelectionState.WAIT);
					controlService.addScore(1);

					break;

				}

				// wrong choice, back to title
				for (Board.TokenPosition position : Board.TokenPosition.values()) {
					ControlService.getService().showTile(position);
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// never
				}

				HighscoreList highscoreList = ControlService.getService().getHighscoreList();
				int score = ControlService.getService().getScore();

				if (highscoreList.checkScore(score)) {
					String name = JOptionPane.showInputDialog("A New Highscore", ControlService.getService()
							.getLastHighscoreName());

					ControlService.getService().setLastHighscoreName(name);

					HighscoreEntry entryHighscore = highscoreList.new HighscoreEntry();
					entryHighscore.name = name;
					entryHighscore.score = score;
					entryHighscore.date = Calendar.getInstance().getTime();

					highscoreList.setEntry(entryHighscore);

				}

				ControlService.getService().setActualGameMode(GameMode.TITLE);

			}
			break;

		}

	}

}
