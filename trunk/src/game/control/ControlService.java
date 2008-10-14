/**
 * 
 */
package game.control;

import java.util.concurrent.ConcurrentHashMap;

import javax.media.j3d.PositionInterpolator;

import game.control.action.TileAction.Face;
import game.control.action.TileAction.Hight;
import game.logic.common.Configuration;
import game.logic.common.HighscoreList;
import game.logic.common.Game.GameMode;
import game.logic.stage.Board;
import game.logic.stage.Board.TokenPosition;
import game.logic.stage.Board.UserSelectionState;
import game.logic.tile.PusherLogic;
import game.logic.tile.ShaftLogic;
import game.logic.tile.TileLogic;
import game.scene.group.GroupUtilities;

/**
 * @author yousry
 * 
 */
public class ControlService {

	static private ControlService controlService = null;

	private String lastHighscoreName = "unknown";

	Configuration configuration = null;

	public synchronized Configuration getConfiguration() {
		return configuration;
	}

	public synchronized void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public synchronized HighscoreList getHighscoreList() {
		return configuration.getHighscoreList();
	}

	public synchronized void setHighscoreList(HighscoreList highscoreList) {
		configuration.setHighscoreList(highscoreList);
	}

	private ConcurrentHashMap<TokenPosition, TileLogic> tileMap;

	private ConcurrentHashMap<TokenPosition, PusherLogic> pusherMap;

	private ConcurrentHashMap<TokenPosition, ShaftLogic> shaftMap;

	private GameMode actualGameMode = GameMode.TITLE;

	private volatile int score = 0;

	private boolean TopPressed = false;

	private boolean selectionMade = false;

	private TileLogic.Value selectionValue;

	private TokenPosition selectionPosition;

	private TileLogic.Value matchingValue;

	private TokenPosition matchingPosition;

	private Board board = new Board();

	// static

	static synchronized public ControlService getService() {
		if (controlService == null) {
			controlService = new ControlService();
		}
		return controlService;
	}

	private ControlService() {
		board.setUserSelectionState(UserSelectionState.WAIT);
	}

	// non static

	public synchronized GameMode getActualGameMode() {
		return actualGameMode;
	}

	public synchronized void setActualGameMode(GameMode gameMode) {
		this.actualGameMode = gameMode;
	}

	public synchronized void addTile(Board.TokenPosition position, TileLogic logic) {
		if (tileMap == null)
			return;

		tileMap.put(position, logic);

	}

	public synchronized TileLogic removeTile(Board.TokenPosition position) {
		if (tileMap == null)
			tileMap = new ConcurrentHashMap<TokenPosition, TileLogic>();

		TileLogic logic = tileMap.remove(position);

		return logic;

	}

	/**
	 * Get logic representation of universe group.
	 * 
	 * @param position
	 * @return Tilelogic
	 */
	public synchronized TileLogic getTile(Board.TokenPosition position) {

		TileLogic logic = null;

		if (tileMap == null) {
			tileMap = new ConcurrentHashMap<TokenPosition, TileLogic>();
		}

		logic = tileMap.get(position);

		if (logic == null) {
			logic = new TileLogic();
			tileMap.put(position, logic);
		}

		return logic;

	}

	public synchronized PusherLogic getPusher(Board.TokenPosition position) {

		PusherLogic logic = null;

		if (pusherMap == null) {
			pusherMap = new ConcurrentHashMap<TokenPosition, PusherLogic>();
		}

		logic = pusherMap.get(position);

		if (logic == null) {
			logic = new PusherLogic();
			pusherMap.put(position, logic);
		}

		return logic;

	}

	public synchronized ShaftLogic getShaft(Board.TokenPosition position) {

		ShaftLogic logic = null;

		if (shaftMap == null) {
			shaftMap = new ConcurrentHashMap<TokenPosition, ShaftLogic>();
		}

		logic = shaftMap.get(position);

		if (logic == null) {
			logic = new ShaftLogic();
			shaftMap.put(position, logic);
		}

		return logic;

	}

	public void showTiles(Board.TokenPosition positionA, Board.TokenPosition positionB) {

		TileLogic logicTileA = getTile(positionA);
		PusherLogic logicPusherA = getPusher(positionA);
		ShaftLogic logicShaftA = getShaft(positionA);

		TileLogic logicTileB = getTile(positionB);
		PusherLogic logicPusherB = getPusher(positionB);
		ShaftLogic logicShaftB = getShaft(positionB);

		logicPusherA.getAction().changeHeight(Hight.MID);
		logicShaftA.getAction().changeHeight(Hight.MID);
		logicTileA.getAction().changeHeight(Hight.MID);

		logicPusherB.getAction().changeHeight(Hight.MID);
		logicShaftB.getAction().changeHeight(Hight.MID);
		logicTileB.getAction().changeHeight(Hight.MID);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusherA.getAction().getBehaviorHight().atWork()
				|| logicShaftA.getAction().getBehaviorHight().atWork()
				|| logicTileA.getAction().getBehaviorHight().atWork()
				|| logicPusherB.getAction().getBehaviorHight().atWork()
				|| logicShaftB.getAction().getBehaviorHight().atWork()
				|| logicTileB.getAction().getBehaviorHight().atWork());

		logicTileA.getAction().changeFace(Face.TOP);
		logicPusherA.getAction().changeFace(Face.TOP);

		logicTileB.getAction().changeFace(Face.TOP);
		logicPusherB.getAction().changeFace(Face.TOP);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusherA.getAction().getBehaviorFace().atWork()
				|| logicTileA.getAction().getBehaviorFace().atWork()
				|| logicPusherB.getAction().getBehaviorFace().atWork()
				|| logicTileB.getAction().getBehaviorFace().atWork());

	}

	public void showTile(Board.TokenPosition position) {

		TileLogic logicTile = getTile(position);
		PusherLogic logicPusher = getPusher(position);
		ShaftLogic logicShaft = getShaft(position);

		logicPusher.getAction().changeHeight(Hight.MID);
		logicShaft.getAction().changeHeight(Hight.MID);
		logicTile.getAction().changeHeight(Hight.MID);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusher.getAction().getBehaviorHight().atWork() == true
				|| logicShaft.getAction().getBehaviorHight().atWork() == true
				|| logicTile.getAction().getBehaviorHight().atWork());

		logicTile.getAction().changeFace(Face.TOP);
		logicPusher.getAction().changeFace(Face.TOP);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusher.getAction().getBehaviorFace().atWork() || logicTile.getAction().getBehaviorFace().atWork());

	}

	public void hideTiles(Board.TokenPosition positionA, Board.TokenPosition positionB) {

		TileLogic logicTileA = getTile(positionA);
		PusherLogic logicPusherA = getPusher(positionA);
		ShaftLogic logicShaftA = getShaft(positionA);

		TileLogic logicTileB = getTile(positionB);
		PusherLogic logicPusherB = getPusher(positionB);
		ShaftLogic logicShaftB = getShaft(positionB);

		logicTileA.getAction().changeFace(Face.BOTTOM);
		logicPusherA.getAction().changeFace(Face.BOTTOM);

		logicTileB.getAction().changeFace(Face.BOTTOM);
		logicPusherB.getAction().changeFace(Face.BOTTOM);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusherA.getAction().getBehaviorFace().atWork()
				|| logicTileA.getAction().getBehaviorFace().atWork()
				|| logicPusherB.getAction().getBehaviorFace().atWork()
				|| logicTileB.getAction().getBehaviorFace().atWork());

		logicPusherA.getAction().changeHeight(Hight.DOWN);
		logicShaftA.getAction().changeHeight(Hight.DOWN);
		logicTileA.getAction().changeHeight(Hight.DOWN);
		logicPusherB.getAction().changeHeight(Hight.DOWN);
		logicShaftB.getAction().changeHeight(Hight.DOWN);
		logicTileB.getAction().changeHeight(Hight.DOWN);

	}

	public void hideTile(Board.TokenPosition position) {

		TileLogic logicTile = getTile(position);
		PusherLogic logicPusher = getPusher(position);
		ShaftLogic logicShaft = getShaft(position);

		logicTile.getAction().changeFace(Face.BOTTOM);
		logicPusher.getAction().changeFace(Face.BOTTOM);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusher.getAction().getBehaviorFace().atWork() == true
				|| logicTile.getAction().getBehaviorFace().atWork() == true);

		logicPusher.getAction().changeHeight(Hight.DOWN);
		logicShaft.getAction().changeHeight(Hight.DOWN);
		logicTile.getAction().changeHeight(Hight.DOWN);

	}

	public void onBelts(Board.TokenPosition positionA, Board.TokenPosition positionB) {
		TileLogic logicTileA = getTile(positionA);
		PusherLogic logicPusherA = getPusher(positionA);
		ShaftLogic logicShaftA = getShaft(positionA);

		TileLogic logicTileB = getTile(positionB);
		PusherLogic logicPusherB = getPusher(positionB);
		ShaftLogic logicShaftB = getShaft(positionB);

		logicPusherA.getAction().changeHeight(Hight.HIGH);
		logicShaftA.getAction().changeHeight(Hight.HIGH);
		logicTileA.getAction().changeHeight(Hight.HIGH);

		logicPusherB.getAction().changeHeight(Hight.HIGH);
		logicShaftB.getAction().changeHeight(Hight.HIGH);
		logicTileB.getAction().changeHeight(Hight.HIGH);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusherA.getAction().getBehaviorHight().atWork()
				|| logicShaftA.getAction().getBehaviorHight().atWork()
				|| logicTileA.getAction().getBehaviorHight().atWork()
				|| logicPusherB.getAction().getBehaviorHight().atWork()
				|| logicShaftB.getAction().getBehaviorHight().atWork()
				|| logicTileB.getAction().getBehaviorHight().atWork());

		logicPusherA.getAction().changeHeight(Hight.DOWN);
		logicShaftA.getAction().changeHeight(Hight.DOWN);
		logicPusherB.getAction().changeHeight(Hight.DOWN);
		logicShaftB.getAction().changeHeight(Hight.DOWN);

	}

	public TileLogic onBelt(Board.TokenPosition position) {
		TileLogic logicTile = getTile(position);
		PusherLogic logicPusher = getPusher(position);
		ShaftLogic logicShaft = getShaft(position);

		logicPusher.getAction().changeHeight(Hight.HIGH);
		logicShaft.getAction().changeHeight(Hight.HIGH);
		logicTile.getAction().changeHeight(Hight.HIGH);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusher.getAction().getBehaviorHight().atWork() == true
				|| logicShaft.getAction().getBehaviorHight().atWork() == true
				|| logicTile.getAction().getBehaviorHight().atWork());

		logicPusher.getAction().changeHeight(Hight.DOWN);
		logicShaft.getAction().changeHeight(Hight.DOWN);

		return logicTile;
	}

	public void fromBelt(Board.TokenPosition position) {
		TileLogic logicTile = getTile(position);
		PusherLogic logicPusher = getPusher(position);
		ShaftLogic logicShaft = getShaft(position);

		logicPusher.getAction().changeHeight(Hight.HIGH);
		logicShaft.getAction().changeHeight(Hight.HIGH);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		} while (logicPusher.getAction().getBehaviorHight().atWork() == true
				|| logicShaft.getAction().getBehaviorHight().atWork() == true
				|| logicTile.getAction().getBehaviorHight().atWork());

		logicPusher.getAction().changeHeight(Hight.DOWN);
		logicShaft.getAction().changeHeight(Hight.DOWN);
		logicTile.getAction().changeHeight(Hight.DOWN);

	}

	public void switchTiles(Board.TokenPosition positionA, Board.TokenPosition positionB) {

		GroupUtilities utilities = new GroupUtilities();
		TileLogic logicA = ControlService.getService().getTile(positionA);
		TileLogic logicB = ControlService.getService().getTile(positionB);

		// update logic position
		logicA.setPosition(positionB);
		logicB.setPosition(positionA);

		onBelts(positionA, positionB);

		Board.TokenPosition tpA = positionA;
		Board.TokenPosition tpB = positionB;

		while (tpA != positionB && tpB != positionA) {
			tpA = utilities.nextPosition(tpA);
			tpB = utilities.nextPosition(tpB);
		}

		logicA.getAction().changePosition(tpA);
		logicB.getAction().changePosition(tpB);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}

		} while (logicA.getAction().getBehaviorRotation().atWork() == true
				&& logicB.getAction().getBehaviorRotation().atWork() == true);

		// one movement is finisched one to go
		TileLogic logicFinished;
		Board.TokenPosition positionFinished;

		TileLogic logicToGo;
		Board.TokenPosition positionToGo;

		if (tpA == positionB) {
			logicFinished = logicA;
			positionFinished = positionA;
			logicToGo = logicB;
			positionToGo = positionB;

		} else {
			logicFinished = logicB;
			positionFinished = positionB;
			logicToGo = logicA;
			positionToGo = positionA;
		}

		removeTile(positionToGo);
		addTile(positionToGo, logicFinished);
		fromBelt(positionToGo);

		logicToGo.getAction().changePosition(positionFinished);

		do {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}

		} while (logicToGo.getAction().getBehaviorRotation().atWork() == true);

		removeTile(positionFinished);
		addTile(positionFinished, logicToGo);
		fromBelt(positionFinished);

	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public synchronized void clearScore() {
		this.score = 0;
	}

	public synchronized int getScore() {
		return score;
	}

	public synchronized void addScore(int value) {
		this.score += value;
	}

	public synchronized boolean isTopPressed() {
		return TopPressed;
	}

	public synchronized void setTopPressed(boolean topPressed) {
		TopPressed = topPressed;
	}

	public synchronized boolean isSelectionMade() {
		return selectionMade;
	}

	public synchronized void setSelectionMade(boolean selectionMade) {
		this.selectionMade = selectionMade;
	}

	public synchronized TileLogic.Value getSelectionValue() {
		return selectionValue;
	}

	public synchronized void setSelectionValue(TileLogic.Value selectionValue) {
		this.selectionValue = selectionValue;
	}

	public synchronized TokenPosition getSelectionPosition() {
		return selectionPosition;
	}

	public synchronized void setSelectionPosition(TokenPosition selectionPosition) {
		this.selectionPosition = selectionPosition;
	}

	public synchronized TokenPosition getMatchingPosition() {
		return matchingPosition;
	}

	public synchronized void setMatchingPosition(TokenPosition matchingPosition) {
		this.matchingPosition = matchingPosition;
	}

	public synchronized TileLogic.Value getMatchingValue() {
		return matchingValue;
	}

	public synchronized void setMatchingValue(TileLogic.Value matchingValue) {
		this.matchingValue = matchingValue;
	}

	public synchronized void printCheatTileMap() {
		for (TokenPosition position : TokenPosition.values()) {
			System.out.println(tileMap.get(position).getValue() + " " + tileMap.get(position).getPosition());
		}
	}

	public synchronized String getLastHighscoreName() {
		return lastHighscoreName;
	}

	public synchronized void setLastHighscoreName(String lastHighscoreName) {
		this.lastHighscoreName = lastHighscoreName;
	}

}
