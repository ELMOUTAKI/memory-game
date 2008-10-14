/**
 * Universe desciption
 * 
 */
package game.scene;

import game.control.group.PickHoverBehavior;
import game.control.group.PickHoverBehavior.menuEntry;
import game.logic.common.GameLogicException;
import game.scene.group.GroupUtilities;

import java.awt.Font;

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
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * @author Yousry Abdallah
 * 
 */
public class TitleUniverse extends SimpleUniverse implements AppUniverse {

	private GroupUtilities utilities = new GroupUtilities();

	private final String TEXT_START = "Start";

	private final String TEXT_SCORES = "Scores";

	private final String TEXT_QUIT = "Quit";

	private BranchGroup sceneStartText = null;

	private BranchGroup sceneQuitText = null;

	private BranchGroup sceneScoresText = null;

	private BranchGroup sceneLight = null;

	private Canvas3D canvas3D;

	@SuppressWarnings("deprecation")
	public TitleUniverse(Canvas3D canvas) {
		// call main constructor with default values for everything but
		// the canvas parameter.
		super(null, 1, canvas, null);
		this.canvas3D = canvas;
	}

	// @Override
	public void initialize() throws GameLogicException {

		sceneStartText = createSceneGraphText3D(TEXT_START, 0.4f);
		sceneScoresText = createSceneGraphText3D(TEXT_SCORES, 0.2f);
		sceneQuitText = createSceneGraphText3D(TEXT_QUIT, 0.0f);
		sceneLight = createSceneLight();

		addBranchGraph(sceneStartText);
		addBranchGraph(sceneScoresText);
		addBranchGraph(sceneQuitText);
		addBranchGraph(sceneLight);

		getViewer().getView().setMinimumFrameCycleTime(20);

	}

	public BranchGroup createSceneLight() {

		BranchGroup objRoot = new BranchGroup();

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 200.0);

		Color3f bgColor = new Color3f(0.0f, 0.0f, 0.0f);
		Background bgNode = new Background(bgColor);
		bgNode.setApplicationBounds(bounds);
		objRoot.addChild(bgNode);

		DirectionalLight d1 = new DirectionalLight();
		d1.setInfluencingBounds(new BoundingSphere());
		d1.setDirection(new Vector3f(0.3f, 0f, -1f));
		d1.setColor(new Color3f(1.0f, 1.0f, 1.0f));
		objRoot.addChild(d1);

		AmbientLight lightA = new AmbientLight();
		lightA.setInfluencingBounds(bounds);
		objRoot.addChild(lightA);

		return objRoot;

	}

	public BranchGroup createSceneGraphText3D(String text, float yHight) throws GameLogicException {

		BranchGroup objRoot = new BranchGroup();

		Group actualGroup = new TransformGroup();
		actualGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		actualGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		actualGroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);

		objRoot.addChild(actualGroup);

		PickHoverBehavior pickHover = null;

		PickHoverBehavior.menuEntry entry = menuEntry.QUIT;

		if (text.equals(TEXT_START))
			entry = menuEntry.START;

		if (text.equals(TEXT_SCORES))
			entry = menuEntry.SCORE;

		if (text.equals(TEXT_QUIT))
			entry = menuEntry.QUIT;

		pickHover = new PickHoverBehavior(objRoot, canvas3D, new BoundingSphere(), entry);
		objRoot.addChild(pickHover);

		// A: Position
		actualGroup = utilities.initPosition(actualGroup, new Vector3f(0f, yHight, 0.25f));

		// B: Rotation
		actualGroup = utilities.initRotation(actualGroup, new Vector3d(0d, 0d, 0d));

		// C: Scale
		actualGroup = utilities.initScale(actualGroup, 0.15);

		Appearance textAppear = new Appearance();
		ColoringAttributes textColor = new ColoringAttributes();
		textColor.setColor(1.0f, 0.0f, 0.0f);
		textAppear.setColoringAttributes(textColor);
		textAppear.setMaterial(new Material());

		Font3D font3D = new Font3D(new Font("Helvetica", Font.HANGING_BASELINE, 1), new FontExtrusion());
		Text3D textGeom = new Text3D(font3D, new String(text));
		textGeom.setAlignment(Text3D.ALIGN_CENTER);
		Shape3D textShape = new Shape3D();
		textShape.setGeometry(textGeom);
		textShape.setAppearance(textAppear);

		actualGroup.addChild(textShape);

		objRoot.compile();

		return objRoot;
	}
}
