package objects3D;

/**
 *
 * This class represents a cylinder
 *
 * The Method involved in this class is DrawCylinder
 *
 * @author Ma Zixiao
 *
 *
 */

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import java.math.*;

public class Cylinder {

	
	public Cylinder() { 
	}

	//This method is to draw the cylinder on the screen
	//To do that, this method first draws the side face of the cylinder
	//The side face is made up of triangles so this method describes the points of the triangles to draw the side face
	//The normal vector is out of the center so the cylinder looks smooth
	//After that, this method draws the undersides of the cylinder
	//The undersides are draw by triangles and the normal is out of the center to make the undersides look smooth
	public void DrawCylinder(float radius, float height, int nSegments ) 
	{
		GL11.glBegin(GL11.GL_TRIANGLES);

		for ( float i = 0; i <= nSegments; i++ ){
			float angle = (float) (Math.PI * 2 * i / nSegments);
			float nextAngle = (float) (Math.PI * 2 * (i+1) / nSegments);

			float x1 = (float) Math.cos(angle) * radius, y1 = (float) Math.sin(angle) * radius;
			float x2 = (float) Math.cos(nextAngle) * radius, y2 = (float) Math.sin(nextAngle) * radius;

			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, 0);
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, height);

			GL11.glNormal3f(x1, y1, 0);
			GL11.glVertex3f(x1, y1, 0);
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, 0);
			GL11.glNormal3f(x2, y2, 0);
			GL11.glVertex3f(x2, y2, height);

			Vector4f v1 = new Point4f(x1, y1, 0, 0).MinusPoint(new Point4f(0, 0, 0, 0));
			Vector4f w1 = new Point4f(x2, y2, 0, 0).MinusPoint(new Point4f(0, 0, 0, 0));
			Vector4f n = v1.cross(w1).Normal();
			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(x1, y1, 0);
			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(x2, y2, 0);
			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(0, 0, 0);

			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(x1, y1, height);
			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(x2, y2, height);
			GL11.glNormal3f(n.x, n.y, n.z);
			GL11.glVertex3f(0, 0, height);
		}



		GL11.glEnd();
	}
}
