/**
 * 
 */
package game.control.group;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnElapsedFrames;

/**
 * @author Yousry Abdallah
 * 
 */
public class TileBehaviorFace extends Behavior {

	final float ROTATION_STEP = 0.08f;

	private TransformGroup transformGroup;

	private WakeupCriterion wakeUpNextFrame;

	double rotation_actual = 0f;

	double rotation_dest = 0f;

	public void setRotation(double rotation) {
		rotation_dest = rotation;
	}

	boolean atWork = false;

	public boolean atWork() {
		return atWork;
	}

	public TileBehaviorFace(TransformGroup transformGroup) {
		this.transformGroup = transformGroup;
		wakeUpNextFrame = new WakeupOnElapsedFrames(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.media.j3d.Behavior#initialize()
	 */
	@Override
	public void initialize() {
		this.wakeupOn(wakeUpNextFrame);
		rotation_actual = 0f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.media.j3d.Behavior#processStimulus(java.util.Enumeration)
	 */
	@Override
	public void processStimulus(Enumeration criteria) {

		if (rotation_actual == rotation_dest) {
			atWork = false;
			this.wakeupOn(wakeUpNextFrame);
			return;
		}

		atWork = true;
		double threshold = 0f;
		Transform3D spin = new Transform3D();
		
		if (rotation_actual <= rotation_dest) {
			rotation_actual += ROTATION_STEP;
			threshold = rotation_dest - rotation_actual;
		} else if (rotation_actual >= rotation_dest) {
			rotation_actual -= ROTATION_STEP;
			threshold = rotation_actual - rotation_dest;
		}

		if(threshold <= 0){
			rotation_actual = rotation_dest;
		}
		

		spin.rotZ(rotation_actual);
		transformGroup.setTransform(spin);
		this.wakeupOn(wakeUpNextFrame);
		
	}

}
