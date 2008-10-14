/**
 * 
 */
package game.control.group;

import game.control.ControlService;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Text3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnElapsedFrames;

/**
 * @author Yousry Abdallah
 * 
 */
public class ScoreBehaviorUpdate extends Behavior {

	private final String TEXT_SCORE = "Score:";

	int lastScore = 0;

	private WakeupCriterion wakeUpNextFrame;

	private Text3D text3D;

	/**
	 * 
	 */
	public ScoreBehaviorUpdate(Text3D text3D) {
		this.text3D = text3D;
		wakeUpNextFrame = new WakeupOnElapsedFrames(0);
	}

	public void initialize() {
		this.wakeupOn(wakeUpNextFrame);
	}

	@Override
	public void processStimulus(Enumeration criteria) {

		if (lastScore == ControlService.getService().getScore()) {
			this.wakeupOn(wakeUpNextFrame);
			return;

		}

		lastScore = ControlService.getService().getScore();

		NumberFormat formatter = new DecimalFormat("0000");
		String scoreString = formatter.format(lastScore);

		text3D.setString(TEXT_SCORE + scoreString);
		this.wakeupOn(wakeUpNextFrame);

	}

}
