/**
 * 
 */
package game.scene.group;

import game.control.ControlService;
import game.control.group.TileBehaviorFace;
import game.control.group.TileBehaviorHight;
import game.logic.stage.Board;
import game.logic.tile.PusherLogic;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * @author Yousry Abdallah
 * 
 */
public class PusherGroup extends BranchGroup implements AppGroup {

	private GroupUtilities utilities = new GroupUtilities();

	Board.TokenPosition position;

	private double boardPositionRad = 0;

	/**
	 * 
	 */
	public PusherGroup(Board.TokenPosition position) {
		super();
		this.position = position;
		boardPositionRad = utilities.translateBoardPosition(position);
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
//		TransformGroup objTransR = new TransformGroup();
//		objTransR.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//		objTransR.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
//		BoundingSphere boundR = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
//		Transform3D spinR = new Transform3D();
//		spinR.rotX(Math.PI * 0.4);
//		Alpha rotationAlphaR = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, 3000, 0, 0, 0, 0, 0);
//		RotationInterpolator rotatorR = new RotationInterpolator(rotationAlphaR, objTransR, spinR, 0.0f,
//				(float) Math.PI * 2.0f);
//		rotatorR.setSchedulingBounds(boundR);
//		objTransR.addChild(rotatorR);
//		actualGroup.addChild(objTransR);
//		actualGroup = objTransR;

		
		// A: Position
		actualGroup = utilities.initPosition(actualGroup, new Vector3f(0f, 0f, 0.2f));

		// B: Rotation
		actualGroup = utilities.initRotation(actualGroup, new Vector3d(Math.PI / 2d, boardPositionRad, 0d));

		// A*: Position correction
		actualGroup = utilities.initPosition(actualGroup, new Vector3f(0f, 0f, -0.13f));

		// Behavior Height
		TransformGroup transformGroup = new TransformGroup();
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		TileBehaviorHight behaviorHight = new TileBehaviorHight( transformGroup);
		behaviorHight.setSchedulingBounds(new BoundingSphere());
		actualGroup.addChild(behaviorHight);
		actualGroup.addChild(transformGroup);
		actualGroup = transformGroup;

		PusherLogic logic = ControlService.getService().getPusher(position);
		logic.getAction().setBehaviorHight(behaviorHight);
		
		
		// C: Scale
		actualGroup = utilities.initScale(actualGroup, 0.2);


		// Behavior Face
		transformGroup = new TransformGroup();
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		TileBehaviorFace behaviorFace = new TileBehaviorFace(transformGroup);
		behaviorFace.setSchedulingBounds(new BoundingSphere());
		actualGroup.addChild(behaviorFace);
		actualGroup.addChild(transformGroup);
		actualGroup = transformGroup;

		logic = ControlService.getService().getPusher(position);
		logic.getAction().setBehaviorFace(behaviorFace);
		
		

		// D: Load Object
		actualGroup = utilities.loadObject(actualGroup, "/obj/pusher.obj");

		compile();

	}

}
