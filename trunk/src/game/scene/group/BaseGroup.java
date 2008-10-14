/**
 * 
 */
package game.scene.group;



import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


/**
 * @author yousry
 * 
 */
public class BaseGroup extends BranchGroup implements AppGroup {

	private GroupUtilities utilities = new GroupUtilities();

	/**
	 * 
	 */
	public BaseGroup() {
		super();
		initialize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.scene.group.AppGroup#initialize()
	 */
	// @Override
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
		actualGroup = utilities.initPosition(actualGroup, new Vector3f(0f, 0f, 0.0f));

		// B: Rotation
		actualGroup = utilities.initRotation(actualGroup, new Vector3d(Math.PI / 2d, 0d, 0d));

		// C: Scale
		actualGroup = utilities.initScale(actualGroup, 0.4);

		// D: Load Object
		actualGroup = utilities.loadObject(actualGroup, "/obj/base.obj");

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 200.0);

		// Set up the background
		Color3f bgColor = new Color3f(0.7f, 0.7f, 0.7f);
		Background bgNode = new Background(bgColor);
		bgNode.setApplicationBounds(bounds);
		addChild(bgNode);
		
		DirectionalLight directionalLight= new DirectionalLight();
		directionalLight.setInfluencingBounds(new BoundingSphere());
		directionalLight.setDirection(new Vector3f(0f, .8f, -1f));
		directionalLight.setColor(new Color3f(1f, 1f, 1f));
		
		addChild(directionalLight);

		compile();
	}

}
