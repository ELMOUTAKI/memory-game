/**
 * 
 */
package game.control;

import game.control.group.TileBehaviorFace;
import game.control.group.TileBehaviorHight;
/**
 * @author Yousry Abdallah
 *
 */
public class PusherState {

	// Process Status
	private boolean inProcess;

	// height
	private TileBehaviorHight behaviorHight;
	// only for tile Movement
	private TileBehaviorFace behaviorFace;
	// speed
	private float speed;
	/**
	 * @return the inProcess
	 */
	public boolean isInProcess() {
		return inProcess;
	}
	/**
	 * @param inProcess the inProcess to set
	 */
	public void setInProcess(boolean inProcess) {
		this.inProcess = inProcess;
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
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
}
