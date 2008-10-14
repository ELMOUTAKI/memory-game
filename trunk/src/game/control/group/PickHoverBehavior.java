/**
 * 
 */
package game.control.group;

import game.control.ControlService;
import game.logic.common.GameLogicException;
import game.logic.common.HighscoreList;
import game.logic.common.Game.GameMode;
import game.logic.common.HighscoreList.HighscoreEntry;

import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.pickfast.PickTool;
import com.sun.j3d.utils.pickfast.behaviors.PickMouseBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickingCallback;

/**
 * @author yousry
 * 
 */
public class PickHoverBehavior extends PickMouseBehavior implements MouseBehaviorCallback {
	MouseRotate rotate;

	private PickingCallback callback = null;

	private TransformGroup currentTG;

	public enum menuEntry {
		START, SCORE, QUIT
	};

	menuEntry entry;

	/**
	 * Creates a pick/rotate behavior that waits for user mouse events for the
	 * scene graph. This method has its pickMode set to BOUNDS picking.
	 * 
	 * @param root
	 *            Root of your scene graph.
	 * @param canvas
	 *            Java 3D drawing canvas.
	 * @param bounds
	 *            Bounds of your scene.
	 */

	public PickHoverBehavior(BranchGroup root, Canvas3D canvas, Bounds bounds, menuEntry entry) {
		super(canvas, root, bounds);
		rotate = new MouseRotate(MouseRotate.MANUAL_WAKEUP);
		rotate.setTransformGroup(currGrp);
		currGrp.addChild(rotate);
		rotate.setSchedulingBounds(bounds);
		this.setSchedulingBounds(bounds);
		this.entry = entry;
	}

	/**
	 * Creates a pick/rotate behavior that waits for user mouse events for the
	 * scene graph.
	 * 
	 * @param root
	 *            Root of your scene graph.
	 * @param canvas
	 *            Java 3D drawing canvas.
	 * @param bounds
	 *            Bounds of your scene.
	 * @param pickMode
	 *            specifys PickTool.PICK_BOUNDS or PickTool.PICK_GEOMETRY.
	 * @see PickTool#setMode
	 */

	public PickHoverBehavior(BranchGroup root, Canvas3D canvas, Bounds bounds, int pickMode) {
		super(canvas, root, bounds);
		rotate = new MouseRotate(MouseRotate.MANUAL_WAKEUP);
		rotate.setTransformGroup(currGrp);
		currGrp.addChild(rotate);
		rotate.setSchedulingBounds(bounds);
		this.setSchedulingBounds(bounds);
		this.setMode(pickMode);
	}

	/**
	 * Update the scene to manipulate any nodes. This is not meant to be called
	 * by users. Behavior automatically calls this. You can call this only if
	 * you know what you are doing.
	 * 
	 * @param xpos
	 *            Current mouse X pos.
	 * @param ypos
	 *            Current mouse Y pos.
	 */
	public void updateScene(int xpos, int ypos) {

		TransformGroup tg = null;

		if (!mevent.isMetaDown() && !mevent.isAltDown()) {

			pickCanvas.setFlags(PickInfo.NODE | PickInfo.SCENEGRAPHPATH);

			pickCanvas.setShapeLocation(xpos, ypos);
			PickInfo pickInfo = pickCanvas.pickClosest();
			if (pickInfo != null) {

				switch (entry) {
				case START:
					ControlService.getService().setActualGameMode(GameMode.GAME);
					break;
				case SCORE:
					ControlService.getService().setActualGameMode(GameMode.SCORE);
					break;
				case QUIT:
					System.exit(0);
					break;
				}
			}

			else if (callback != null)
				callback.transformChanged(PickingCallback.NO_PICK, null);
		}
	}

	/**
	 * Callback method from MouseRotate This is used when the Picking callback
	 * is enabled
	 */
	public void transformChanged(int type, Transform3D transform) {

		callback.transformChanged(PickingCallback.ROTATE, currentTG);
	}

	/**
	 * Register the class
	 * 
	 * @param callback
	 *            to be called each time the picked object moves
	 */
	public void setupCallback(PickingCallback callback) {
		this.callback = callback;
		if (callback == null)
			rotate.setupCallback(null);
		else
			rotate.setupCallback(this);
	}

}
