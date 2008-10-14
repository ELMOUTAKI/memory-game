/**
 * 
 */
package game.control.group;


import game.control.ControlService;

import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
import com.sun.j3d.utils.pickfast.behaviors.PickMouseBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickingCallback;

/**
 * @author Yousry Abdallah
 * 
 */
public class TopPickBehavior extends PickMouseBehavior implements MouseBehaviorCallback {

	boolean isActive = false;

	private TransformGroup transformGroup;

	private PickingCallback callback = null;


	public TopPickBehavior(BranchGroup root, Canvas3D canvas, Bounds bounds, int pickMode) {
		super(canvas, root, bounds);
		this.setSchedulingBounds(bounds);
		this.setMode(pickMode);

	}

	/**
	 * @param canvas
	 * @param root
	 * @param bounds
	 */
	public TopPickBehavior(Canvas3D canvas, BranchGroup root, Bounds bounds) {
		super(canvas, root, bounds);
		this.setMode(PickInfo.PICK_GEOMETRY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sun.j3d.utils.behaviors.picking.PickMouseBehavior#updateScene(int,
	 * int)
	 */
	@Override
	public void updateScene(int xpos, int ypos) {

		TransformGroup tg = null;

		if (!mevent.isMetaDown() && !mevent.isAltDown()) {

			pickCanvas.setFlags(PickInfo.NODE | PickInfo.SCENEGRAPHPATH);

			pickCanvas.setShapeLocation(xpos, ypos);
			PickInfo pickInfo = pickCanvas.pickClosest();
			if (pickInfo != null) {

				ControlService.getService().setTopPressed(true);

			}

			else if (callback != null)
				callback.transformChanged(PickingCallback.NO_PICK, null);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback#transformChanged
	 * (int, javax.media.j3d.Transform3D)
	 */
	@Override
	public void transformChanged(int type, Transform3D transform) {
		callback.transformChanged(PickingCallback.ROTATE, transformGroup);
	}

	public void setupCallback(PickingCallback callback) {
		this.callback = callback;
	}

}
