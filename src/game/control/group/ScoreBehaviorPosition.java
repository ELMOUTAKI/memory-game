/**
 * 
 */
package game.control.group;

import game.control.ControlService;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.vecmath.Vector3f;

/**
 * @author Yousry Abdallah
 * 
 */
public class ScoreBehaviorPosition extends Behavior {

	private ControlService controlService = null;

	private TransformGroup transformGroup = null;

	private WakeupCriterion wakeUpNextFrame;

	public ScoreBehaviorPosition(TransformGroup transformGroup) {
		assert (transformGroup != null);
		this.transformGroup = transformGroup;
		wakeUpNextFrame = new WakeupOnElapsedFrames(50);
		controlService = ControlService.getService();
	}

	@Override
	public void initialize() {
		this.wakeupOn(wakeUpNextFrame);

	}

	@Override
	public void processStimulus(Enumeration criteria) {

		int width = controlService.getConfiguration().getWidth();
		int hight = controlService.getService().getConfiguration().getHeight();

		float aspect = new Float(width) / new Float(hight);

		Transform3D translation = new Transform3D();
		translation.setTranslation(new Vector3f(0f, (aspect - 1f) * -0.5f, 0f));
		transformGroup.setTransform(translation);

		this.wakeupOn(wakeUpNextFrame);

	}

}
