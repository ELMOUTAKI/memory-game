/**
 * 
 */
package game.scene;

import java.awt.Font;

import game.control.ControlService;
import game.control.group.MousePressed;
import game.control.group.PickHoverBehavior;
import game.control.group.ScoreBehaviorUpdate;
import game.control.group.ScorePositionBehaviorUpdate;
import game.control.group.PickHoverBehavior.menuEntry;
import game.logic.common.GameLogicException;
import game.logic.common.HighscoreList;
import game.logic.common.HighscoreList.HighscoreEntry;
import game.scene.group.GroupUtilities;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Group;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * @author yousry
 * 
 */
public class ScoreUniverse extends SimpleUniverse implements AppUniverse {

	private GroupUtilities utilities = new GroupUtilities();

	private Canvas3D canvas3D;

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	public ScoreUniverse(Canvas3D canvas) {
		super(null, 1, canvas, null);
		this.canvas3D = canvas;
	}

	// @Override
	public void initialize() {

		BranchGroup branchGroup;

		float INITIAL_POSITION = 0.82f;
		float STEP_POSITION = 0.07f;

		HighscoreList highscoreList = ControlService.getService().getHighscoreList();
		
		for (int i = 0; i < 25; i++) {

			try {
				HighscoreEntry entryLeft, entryRight;
				entryLeft = highscoreList.getEntry(i);
				entryRight = highscoreList.getEntry(24 + i + 1);

				String entryString = "P: " + (i + 1) + " " + entryLeft.name + " Score: " + entryLeft.score;
				entryString += "    P: " + (24 + i + 2) + " " + entryRight.name + " Score: " + entryRight.score;

				branchGroup = createSceneGraphText3D(entryString, INITIAL_POSITION - (i * STEP_POSITION), i, 24 + i + 1  );
				addBranchGraph(branchGroup);

			} catch (GameLogicException e) {
			}

		}

		branchGroup = createSceneLight();
		addBranchGraph(branchGroup);

	}

	public BranchGroup createSceneLight() {

		BranchGroup objRoot = new BranchGroup();

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 1.0), 200.0);

		Color3f bgColor = new Color3f(0.0f, 0.0f, 0.0f);
		Background bgNode = new Background(bgColor);
		bgNode.setApplicationBounds(new BoundingSphere());
		objRoot.addChild(bgNode);

		DirectionalLight d1 = new DirectionalLight();
		d1.setInfluencingBounds(new BoundingSphere());
		d1.setDirection(new Vector3f(0.0f, 0f, -1f));
		d1.setColor(new Color3f(1.0f, 1.0f, 1.0f));
		objRoot.addChild(d1);

		AmbientLight lightA = new AmbientLight();
		lightA.setInfluencingBounds(bounds);
		objRoot.addChild(lightA);

		objRoot.compile();

		return objRoot;
	}

	public BranchGroup createSceneGraphText3D(String text, float yHight, int positionA,int positionB) throws GameLogicException {

		BranchGroup objRoot = new BranchGroup();

		Group actualGroup = new TransformGroup();
		actualGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		actualGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		actualGroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);

		objRoot.addChild(actualGroup);

		// A: Position
		actualGroup = utilities.initPosition(actualGroup, new Vector3f(-0.75f, yHight, 0.25f));

		// B: Rotation
		actualGroup = utilities.initRotation(actualGroup, new Vector3d(0d, 0d, 0d));

		// C: Scale
		actualGroup = utilities.initScale(actualGroup, 0.05);

		MousePressed mousePressed = new MousePressed(canvas3D, objRoot, new BoundingSphere());
		objRoot.addChild(mousePressed);

		
		
		
		Appearance textAppear = new Appearance();
		ColoringAttributes textColor = new ColoringAttributes();
		textColor.setColor(1.0f, 0.0f, 0.0f);
		textAppear.setColoringAttributes(textColor);
		textAppear.setMaterial(new Material());

		Font3D font3D = new Font3D(new Font("Helvetica", Font.PLAIN, 1), new FontExtrusion());

		Text3D textGeom = new Text3D(font3D, new String("---WAIT---"));
		textGeom.setCapability(Text3D.ALLOW_STRING_WRITE);
		
		ScorePositionBehaviorUpdate scoreBehaviorUpdate = new ScorePositionBehaviorUpdate(textGeom,positionA,positionB);
		scoreBehaviorUpdate.setSchedulingBounds(new BoundingSphere());
		actualGroup.addChild(scoreBehaviorUpdate);
		
		
		textGeom.setAlignment(Text3D.ALIGN_FIRST);
		Shape3D textShape = new Shape3D();
		textShape.setGeometry(textGeom);
		textShape.setAppearance(textAppear);

		actualGroup.addChild(textShape);

		objRoot.compile();

		return objRoot;
	}

}
