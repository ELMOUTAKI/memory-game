/**
 * 
 */
package game.control.group;

import game.control.ControlService;
import game.logic.common.Game.GameMode;

import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.media.j3d.WakeupOr;

import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
import com.sun.j3d.utils.behaviors.vp.ViewPlatformAWTBehavior;
import com.sun.j3d.utils.pickfast.PickCanvas;
import com.sun.j3d.utils.pickfast.behaviors.PickMouseBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickingCallback;

/**
 * @author Yousry Abdallah
 * 
 */
public class MousePressed extends PickMouseBehavior implements MouseBehaviorCallback {

	private PickingCallback callback = null;
	private TransformGroup currentTG;
	  
	public MousePressed(Canvas3D canvas, BranchGroup root, Bounds bounds) {
		super(canvas, root, bounds);
		this.setSchedulingBounds(bounds);
	}


	@Override
	public void transformChanged(int type, Transform3D transform) {

	}

	@Override
	public void updateScene(int xpos, int ypos) {

		TransformGroup tg = null;

		if (!mevent.isMetaDown() && !mevent.isAltDown()) {
			ControlService.getService().setActualGameMode(GameMode.TITLE);
			}

			else if (callback != null)
				callback.transformChanged(PickingCallback.NO_PICK, null);		
	}

}
