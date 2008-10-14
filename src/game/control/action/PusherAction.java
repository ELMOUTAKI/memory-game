/**
 * 
 */
package game.control.action;

import game.control.action.TileAction.Face;
import game.control.action.TileAction.Hight;
import game.control.group.TileBehaviorFace;
import game.control.group.TileBehaviorHight;

/**
 * @author Yousry Abdallah
 * 
 */
public class PusherAction {

	final float POSITION_DOWN = 0f;

	final float POSITION_HIGH = -0.28f;

	final float POSITION_MID = -0.14f;

	final double FACE_TOP = 0f;

	final double FACE_BOTTOM = Math.PI;

	private TileBehaviorFace behaviorFace;

	private TileBehaviorHight behaviorHight;

	
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

	/**
	 * @return the behaviorFace
	 */
	public TileBehaviorFace getBehaviorFace() {
		return behaviorFace;
	}

	/**
	 * @param behaviorFace the behaviorFace to set
	 */
	public void setBehaviorFace(TileBehaviorFace behaviorFace) {
		this.behaviorFace = behaviorFace;
	}

	/**
	 * @return the behaviorHight
	 */
	public TileBehaviorHight getBehaviorHight() {
		return behaviorHight;
	}

	/**
	 * @param behaviorHight the behaviorHight to set
	 */
	public void setBehaviorHight(TileBehaviorHight behaviorHight) {
		this.behaviorHight = behaviorHight;
	}

	
}
