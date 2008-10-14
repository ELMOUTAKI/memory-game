package game.control.action;

import game.control.action.TileAction.Hight;
import game.control.group.TileBehaviorHight;

public class ShaftAction {


	final float POSITION_DOWN = 0f;
	final float POSITION_HIGH = -0.28f;
	final float POSITION_MID = -0.14f;

	private TileBehaviorHight behaviorHight;
	

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
