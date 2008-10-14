/**
 * 
 */
package game.scene.group;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import game.control.ControlService;
import game.control.group.ScoreBehaviorPosition;
import game.control.group.ScoreBehaviorUpdate;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * @author Yousry Abdallah
 * 
 */
public class ScoreGroup extends BranchGroup implements AppGroup {

	private final String TEXT_SCORE = "Score:";

	private GroupUtilities utilities = new GroupUtilities();

	/**
	 * 
	 */
	public ScoreGroup() {
		super();
		initialize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.scene.group.AppGroup#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

		Group actualGroup = this;

		actualGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		actualGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		actualGroup = utilities.initPosition(actualGroup, new Vector3f(0.49f, 0.7f, 0.25f)); // 0.5

		// Brehavior Aspect Ratio
		TransformGroup transformGroup = new TransformGroup();
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		ScoreBehaviorPosition scoreBehaviorPosition = new ScoreBehaviorPosition(transformGroup);
		scoreBehaviorPosition.setSchedulingBounds(new BoundingSphere());
		actualGroup.addChild(scoreBehaviorPosition);
		actualGroup.addChild(transformGroup);
		actualGroup = transformGroup;
		
		
		// B: Rotation
		actualGroup = utilities.initRotation(actualGroup, new Vector3d(0d, 0d, 0d));

		// C: Scale
		actualGroup = utilities.initScale(actualGroup, 0.15);

		Appearance textAppear = new Appearance();
		ColoringAttributes textColor = new ColoringAttributes();
		textColor.setColor(1f, 1f, 1f);
		textAppear.setColoringAttributes(textColor);
		Material material = new Material();
		material.setEmissiveColor(new Color3f(.2f,.2f,.2f) );
		material.setAmbientColor(new Color3f(1f,1f,1f) );

		textAppear.setMaterial(material);

		int score = ControlService.getService().getScore();
		NumberFormat formatter = new DecimalFormat("0000");
		String scoreString = formatter.format(score);

		Font3D font3D = new Font3D(new Font("ARIAL", Font.BOLD, 1), new FontExtrusion());

		
		Text3D textGeom = new Text3D(font3D, new String(TEXT_SCORE + scoreString));

		textGeom.setCapability(Text3D.ALLOW_STRING_WRITE);

		ScoreBehaviorUpdate scoreBehaviorUpdate = new ScoreBehaviorUpdate(textGeom);
		scoreBehaviorUpdate.setSchedulingBounds(new BoundingSphere());
		actualGroup.addChild(scoreBehaviorUpdate);
		
		textGeom.setAlignment(Text3D.ALIGN_CENTER);
		Shape3D textShape = new Shape3D();
		textShape.setGeometry(textGeom);
		textShape.setAppearance(textAppear);
		
		actualGroup.addChild(textShape);

	}

}
