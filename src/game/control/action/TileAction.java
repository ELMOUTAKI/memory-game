package game.control.action;

import game.control.group.TileBehaviorFace;
import game.control.group.TileBehaviorHight;
import game.control.group.TileBehaviorRotation;
import game.logic.stage.Board;
import game.scene.group.GroupUtilities;

public class TileAction {

	GroupUtilities groupUtilities = new GroupUtilities();

	final float POSITION_DOWN = 0f;

	final float POSITION_HIGH = -0.28f;

	final float POSITION_MID = -0.14f;

	final double FACE_TOP = 0f;

	final double FACE_BOTTOM = Math.PI;

	public enum Hight {
		DOWN, MID, HIGH
	};

	public enum Face {
		TOP, BOTTOM
	}

	private TileBehaviorFace behaviorFace;

	private TileBehaviorHight behaviorHight;

	private TileBehaviorRotation behaviorRotation;

	public void changePosition(Board.TokenPosition position) {
		double radPosition = groupUtilities.translateBoardPosition(position);
		behaviorRotation.setRotation(radPosition);

	}

	public void changeFace(Face face) {
		switch (face) {
		case TOP:
			behaviorFace.setRotation(FACE_TOP);
			break;
		case BOTTOM:
			behaviorFace.setRotation(FACE_BOTTOM);
		}
	}

	public void changeHeight(Hight hight) {

		switch (hight) {
		case DOWN:
			behaviorHight.setZPosition(POSITION_DOWN);
			break;
		case MID:
			behaviorHight.setZPosition(POSITION_MID);
			break;
		case HIGH:
			behaviorHight.setZPosition(POSITION_HIGH);
		}
	}

	public TileBehaviorFace getBehaviorFace() {
		return behaviorFace;
	}

	public void setBehaviorFace(TileBehaviorFace behaviorFace) {
		this.behaviorFace = behaviorFace;
	}

	public TileBehaviorHight getBehaviorHight() {
		return behaviorHight;
	}

	public void setBehaviorHight(TileBehaviorHight behaviorHight) {
		this.behaviorHight = behaviorHight;
	}

	public TileBehaviorRotation getBehaviorRotation() {
		return behaviorRotation;
	}

	public void setBehaviorRotation(TileBehaviorRotation behaviorRotation) {
		this.behaviorRotation = behaviorRotation;
	}

}
