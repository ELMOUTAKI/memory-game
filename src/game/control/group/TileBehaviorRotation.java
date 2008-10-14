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
 * @author yousry
 * 
 */
public class TileBehaviorRotation extends Behavior {

	final double ROTATION_STEP = 0.05d;

	private TransformGroup transformGroup;

	private WakeupCriterion wakeUpNextFrame;

	double rotation_actual = 0f;

	double rotationOffset = 0f;
	
	double rotation_dest = 0f;

	public void setRotation(double rotation) {
		rotation_dest = rotation - rotationOffset;
		if(rotation_dest < 0)
			rotation_dest += 2 * Math.PI;
	}

	boolean atWork = false;

	public boolean atWork() {
		return atWork;
	}

	public TileBehaviorRotation(TransformGroup transformGroup, double rotationOffset) {
		this.transformGroup = transformGroup;
		wakeUpNextFrame = new WakeupOnElapsedFrames(0);
		this.rotationOffset=rotationOffset;
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

		
		Transform3D spin = new Transform3D();
		
		rotation_actual -= ROTATION_STEP;
		
		if(rotation_actual < 0)
			rotation_actual += 2 * Math.PI;
		
		
		double threshold = Math.max(rotation_actual, rotation_dest) - Math.min(rotation_actual, rotation_dest);
		
		if(threshold <= ROTATION_STEP )
			rotation_actual = rotation_dest;
		
		// --

		spin.rotY(rotation_actual);
		transformGroup.setTransform(spin);
		this.wakeupOn(wakeUpNextFrame);

	}

}
