/**
 * 
 */
package game.scene.group;

import game.logic.stage.Board;

import java.io.FileNotFoundException;
import java.net.URL;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

/**
 * @author Yousry Abdallah
 * 
 */
public class GroupUtilities {

	public Group initPosition(Group group, Vector3f vector) {
		Transform3D translation = new Transform3D();
		translation.setTranslation(vector);
		TransformGroup objTranslationGroup = new TransformGroup(translation);
		group.addChild(objTranslationGroup);
		return objTranslationGroup;
	}

	public Group initRotation(Group group, Vector3d rotation) {

		Transform3D rotateX = new Transform3D();
		rotateX.rotX(rotation.x);

		Transform3D rotateY = new Transform3D();
		rotateY.rotY(rotation.y);

		Transform3D rotateZ = new Transform3D();
		rotateZ.rotZ(rotation.z);

		rotateX.mul(rotateY);
		rotateX.mul(rotateZ);

		TransformGroup objRotate = new TransformGroup(rotateX);
		group.addChild(objRotate);

		return objRotate;
	}

	public Group initScale(Group group, double scale) {
		TransformGroup objScale = new TransformGroup();
		Transform3D t3d = new Transform3D();
		t3d.setScale(scale);
		objScale.setTransform(t3d);
		group.addChild(objScale);

		return objScale;
	}

	public Group loadObject(Group group, String resourceName) {

		double creaseAngle = 60.0;

		TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		// objScale.addChild(objTrans);

		group.addChild(objTrans);

		int flags = ObjectFile.RESIZE;
		ObjectFile f = new ObjectFile(flags, (float) (creaseAngle * Math.PI / 180.0));
		Scene s = null;

		// if you know how, it is simple. Should work with jars as well
		URL url = this.getClass().getResource(resourceName);

		try {
			s = f.load(url);
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		} catch (ParsingErrorException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IncorrectFormatException e) {
			System.err.println(e);
			System.exit(1);
		}
		objTrans.addChild(s.getSceneGroup());

		return s.getSceneGroup();

	}

	public Board.TokenPosition nextPosition(Board.TokenPosition position) {
		switch (position) {
		case NORTH:
			return Board.TokenPosition.NORTHEAST;
		case NORTHWEST:
			return Board.TokenPosition.NORTH;
		case WEST:
			return Board.TokenPosition.NORTHWEST;
		case SOUTHWEST:
			return Board.TokenPosition.WEST;
		case SOUTH:
			return Board.TokenPosition.SOUTHWEST;
		case SOUTHEAST:
			return Board.TokenPosition.SOUTH;
		case EAST:
			return Board.TokenPosition.SOUTHEAST;
		case NORTHEAST:
			return Board.TokenPosition.EAST;
		}
		return position;
	}

	public double translateBoardPosition(Board.TokenPosition position) {

		double rPos = 0d;

		switch (position) {
		case NORTH:
			rPos = 0;
			break;
		case NORTHWEST:
			rPos = Math.PI / 4d;
			break;
		case WEST:
			rPos = Math.PI / 2d;
			break;
		case SOUTHWEST:
			rPos = 3.0d * Math.PI / 4d;
			break;
		case SOUTH:
			rPos = Math.PI;
			break;
		case SOUTHEAST:
			rPos = 5.0d * Math.PI / 4d;
			break;
		case EAST:
			rPos = 6.0d * Math.PI / 4d;
			break;
		case NORTHEAST:
			rPos = 7.0d * Math.PI / 4d;
			break;

		}

		return rPos;
	}

}
