/**
 * 
 */
package game.scene.group;

import game.control.group.TopPickBehavior;


import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


/**
 * @author yousry
 * 
 */
public class TopGroup extends BranchGroup implements AppGroup {

	private Canvas3D canvas3D;
	
	private GroupUtilities utilities = new GroupUtilities();
	
	/**
	 * 
	 */
	public TopGroup(Canvas3D canvas3D) {
		super();
		this.canvas3D = canvas3D;
		initialize();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see game.scene.group.AppGroup#initialize()
	 */
	//@Override
	public void initialize() {

		Group actualGroup = this;
		
		// test global Rotation
//		TransformGroup objTrans = new TransformGroup();
//		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
//		BoundingSphere boundR = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
//		Transform3D spin = new Transform3D();
//		spin.rotX(Math.PI * 0.4);
//		Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 3000, 0, 0, 0, 0, 0);
//		RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, objTrans, spin, 0.0f,
//				(float) Math.PI * 2.0f);
//		rotator.setSchedulingBounds(boundR);
//		objTrans.addChild(rotator);
//		actualGroup.addChild(objTrans);
//		actualGroup = objTrans;
		

		// A: Position
		actualGroup = utilities.initPosition(actualGroup, new Vector3f(0f, 0f, 0.25f));

		// B: Rotation
		actualGroup = utilities.initRotation(actualGroup, new Vector3d(Math.PI / 2, 0d, 0d));

		// C: Scale
		actualGroup = utilities.initScale(actualGroup, 0.28);

		
		
		// Behavior Pick
		TransformGroup transformGroup = new TransformGroup();
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transformGroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		
		TopPickBehavior pickBehavior = new TopPickBehavior(canvas3D,this,new BoundingSphere()); 
		pickBehavior.setSchedulingBounds(new BoundingSphere());
		actualGroup.addChild(pickBehavior);
		actualGroup.addChild(transformGroup);
		actualGroup = transformGroup;
		
		
		// D: Load Object
		actualGroup = utilities.loadObject(actualGroup, "/obj/top.obj");
		

		
		compile();
	}

}
