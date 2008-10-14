/**
 * 
 */
package game.control.group;

import game.control.ControlService;
import game.logic.common.GameLogicException;
import game.logic.common.HighscoreList;
import game.logic.common.HighscoreList.HighscoreEntry;

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
public class ScorePositionBehaviorUpdate extends Behavior {

	private final String TEXT_SCORE = "Score:";

	HighscoreList highscoreList;

	int lastScore = 10;

	int scorePositionA, scorePositionB;

	int positionA, positionB;

	private WakeupCriterion wakeUpNextFrame;

	private Text3D text3D;

	/**
	 * 
	 */
	public ScorePositionBehaviorUpdate(Text3D text3D, int positionA, int positionB) {
		this.positionA = positionA;
		this.positionB = positionB;

		scorePositionA = 0;
		scorePositionB = 0;

		this.text3D = text3D;
		wakeUpNextFrame = new WakeupOnElapsedFrames(0);
	}

	public void initialize() {
		this.wakeupOn(wakeUpNextFrame);
		this.highscoreList = ControlService.getService().getHighscoreList();
	}

	@Override
	public void processStimulus(Enumeration criteria) {

		try {
			
			HighscoreEntry entryLeft = highscoreList.getEntry(positionA);
			HighscoreEntry entryRight = highscoreList.getEntry(positionB);

			scorePositionA = entryLeft.score;
			scorePositionB = entryRight.score;

			NumberFormat formatter = new DecimalFormat("0000");
			String scoreStringLeft = formatter.format(scorePositionA);
			String scoreStringRight = formatter.format(scorePositionB);

			NumberFormat positionFormat = new DecimalFormat("00");
			String positionLeft = positionFormat.format(positionA + 1);
			String positionRight = positionFormat.format(positionB + 1);

		
			String nameLeft = String.format("%-10.8s", entryLeft.name);
			String nameRight = String.format("%-10.8s", entryRight.name);
		
			
			String entryString = "PL: " + (positionLeft) + " " + nameLeft + " Score: " + scoreStringLeft;
			entryString += "    PL: " + (positionRight) + " " + nameRight + " Score: " + scoreStringRight;

			text3D.setString(entryString);
			this.wakeupOn(wakeUpNextFrame);

		} catch (GameLogicException e) {
			e.printStackTrace();
		}

	}

}
