package game.scene;

import java.util.ArrayList;

import game.logic.common.GameLogicException;
import game.logic.stage.Board;
import game.logic.tile.TileLogic;
import game.scene.group.BaseGroup;
import game.scene.group.BoardGroup;
import game.scene.group.PusherGroup;
import game.scene.group.RingGroup;
import game.scene.group.ScoreGroup;
import game.scene.group.ShaftGroup;
import game.scene.group.TileGroup;
import game.scene.group.TopGroup;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class GameUniverse extends SimpleUniverse implements AppUniverse {

	// branch is defined by level
	private int level = 0;

	// TODO: Define lvl group Mapping

	private BranchGroup baseGroup = null;

	private TopGroup topGroup = null;

	private RingGroup ringGroup = null;

	private PusherGroup pusherGroup = null;

	private ArrayList<PusherGroup> pusherGroups = new ArrayList<PusherGroup>();

	private TileGroup tileGroup = null;

	private ScoreGroup scoreGroup = null;
	
	private ArrayList<TileGroup> tileGroups = new ArrayList<TileGroup>();

	private ShaftGroup shaftGroup = null;

	private ArrayList<ShaftGroup> shaftGroups = new ArrayList<ShaftGroup>();
	
	private Canvas3D canvas3D;

	@SuppressWarnings("deprecation")
	public GameUniverse(Canvas3D canvas) {
		super(null, 1, canvas, null);
		if (level < 1)
			level = 1;
		this.canvas3D = canvas;
	}

	// @Override
	public void initialize() throws GameLogicException {

		baseGroup = new BaseGroup();
		addBranchGraph(baseGroup);

		topGroup = new TopGroup(canvas3D);
		addBranchGraph(topGroup);

		ringGroup = new RingGroup();
		addBranchGraph(ringGroup);

		for (Board.TokenPosition position : Board.TokenPosition.values()) {
			pusherGroup = new PusherGroup(position);
			pusherGroups.add(pusherGroup);
			addBranchGraph(pusherGroup);

			shaftGroup = new ShaftGroup(position);
			shaftGroups.add(shaftGroup);
			addBranchGraph(shaftGroup);

		}

		tileGroup = new TileGroup(Board.TokenPosition.NORTH, TileLogic.Value.EARTH, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);
		tileGroup = new TileGroup(Board.TokenPosition.SOUTH, TileLogic.Value.EARTH, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);

		tileGroup = new TileGroup(Board.TokenPosition.NORTHEAST, TileLogic.Value.AIR, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);
		tileGroup = new TileGroup(Board.TokenPosition.SOUTHWEST, TileLogic.Value.AIR, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);

		tileGroup = new TileGroup(Board.TokenPosition.EAST, TileLogic.Value.FIRE, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);
		tileGroup = new TileGroup(Board.TokenPosition.WEST, TileLogic.Value.FIRE, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);

		tileGroup = new TileGroup(Board.TokenPosition.NORTHWEST, TileLogic.Value.WATER, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);
		tileGroup = new TileGroup(Board.TokenPosition.SOUTHEAST, TileLogic.Value.WATER, canvas3D);
		tileGroups.add(tileGroup);
		addBranchGraph(tileGroup);

		scoreGroup = new ScoreGroup();
		addBranchGraph(scoreGroup);
		
		getViewer().getView().setMinimumFrameCycleTime(20);
	}

	public BranchGroup createSceneGraphBoard() {
		BranchGroup objRoot = new BoardGroup();
		objRoot.compile();
		return objRoot;
	}

}
