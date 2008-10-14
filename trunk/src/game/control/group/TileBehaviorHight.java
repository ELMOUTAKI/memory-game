/**
 * 
 */
package game.control.group;

import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.vecmath.Vector3f;

/**
 * @author yousry
 * 
 */
public class TileBehaviorHight extends Behavior {

	final float Z_POSITION_STEP = 0.02f;

	private TransformGroup transformGroup;

	private WakeupCriterion wakeUpNextFrame;

	float zPosition_Acutal = 0f;

	float zPosition_Dest = 0f;

	boolean atWork = false;

	public boolean atWork() {
		return atWork;
	}
	
	public void setZPosition(float position) {
		zPosition_Dest = position;
	}

	public TileBehaviorHight(TransformGroup transformGroup) {
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
		zPosition_Acutal = 0f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.media.j3d.Behavior#processStimulus(java.util.Enumeration)
	 */
	@Override
	public void processStimulus(Enumeration criteria) {

		if (zPosition_Acutal == zPosition_Dest) {
			atWork = false;
			this.wakeupOn(wakeUpNextFrame);

			return; // ugly
		}

		atWork = true;
		Transform3D translation = new Transform3D();
		
		float threshold = 0f;

		if (zPosition_Acutal <= zPosition_Dest) {
			zPosition_Acutal += Z_POSITION_STEP;
			threshold = zPosition_Dest - zPosition_Acutal;
		} else if (zPosition_Acutal >= zPosition_Dest) {
			zPosition_Acutal -= Z_POSITION_STEP;
			threshold = zPosition_Acutal - zPosition_Dest;
		}

		if(threshold <= 0){
			zPosition_Acutal = zPosition_Dest;
		}
		
		translation.setTranslation(new Vector3f(0f, 0f, zPosition_Acutal));
		transformGroup.setTransform(translation);
		this.wakeupOn(wakeUpNextFrame);
	}

}
