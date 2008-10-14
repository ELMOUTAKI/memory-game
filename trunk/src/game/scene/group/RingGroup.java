/**
 * 
 */
package game.scene.group;


import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * @author Yousry Abdallah
 *
 */
public class RingGroup extends BranchGroup implements AppGroup {

	private GroupUtilities utilities = new GroupUtilities();
	
	/**
	 * 
	 */
	public RingGroup() {
		super();
		initialize();
	}

	/* (non-Javadoc)
	 * @see game.scene.group.AppGroup#initialize()
	 */
	@Override
	public void initialize() {

		Group actualGroup = this;

		// test global Rotation
//		TransformGroup objTrans = new TransformGroup();
//		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
//		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
//		Transform3D spin = new Transform3D();
//		spin.rotX(Math.PI * 0.4);
//		Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 3000, 0, 0, 0, 0, 0);
//		RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, objTrans, spin, 0.0f,
//				(float) Math.PI * 2.0f);
//		rotator.setSchedulingBounds(bounds);
//		objTrans.addChild(rotator);
//		actualGroup.addChild(objTrans);
//		actualGroup = objTrans;

		
		
		// A: Position
		actualGroup = utilities.initPosition(actualGroup, new Vector3f(0f, 0f, 0.2f));

		// B: Rotation
		actualGroup = utilities.initRotation(actualGroup, new Vector3d(Math.PI / 2, 0d, 0d));

		// C: Scale
		actualGroup = utilities.initScale(actualGroup, 0.85);

		// D: Load Object
		actualGroup = utilities.loadObject(actualGroup, "/obj/ring.obj");

		compile();
	}

}
