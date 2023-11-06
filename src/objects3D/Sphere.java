package objects3D;

/**
 *
 * This class represents a sphere
 *
 * The Method involved in this class is DrawSphere
 *
 * @author Ma Zixiao
 *
 *
 */

import org.lwjgl.opengl.GL11;

public class Sphere {

	
	public Sphere() {

	}

	//The aim of this method is to draw this sphere
	//To do that, this method draws all the quadrilaterals that make up this sphere
	//The normal vectors of the points in each quadrilateral is out of the center of this sphere so
	//this sphere will look smooth although it is made up of quadrilaterals
	//The points of each quadrilaterals is found by dividing the sphere into a few slices and segments
	public void DrawSphere(float radius,float nSlices,float nSegments) {
		float inctheta = (float) (Math.PI * 2 / nSlices);
		float incphi = (float) (Math.PI / nSegments);

		GL11.glBegin(GL11.GL_QUADS);

		for (float theta = - (float) Math.PI; theta <= Math.PI; theta += inctheta ){
			for (float phi = - (float) Math.PI / 2; phi <= Math.PI / 2; phi += incphi ){
				float nextTheta = theta + inctheta;
				float nextPhi = phi + incphi;

				float x1 = (float) (Math.cos(phi) * Math.cos(theta) * radius);
				float y1 = (float) (Math.cos(phi) * Math.sin(theta) * radius);
				float z1 = (float) Math.sin(phi) * radius;
				GL11.glNormal3f(x1, y1, z1);
				GL11.glVertex3f(x1, y1, z1);

				float x2 = (float) (Math.cos(phi) * Math.cos(nextTheta) * radius);
				float y2 = (float) (Math.cos(phi) * Math.sin(nextTheta) * radius);
				float z2 = (float) Math.sin(phi) * radius;
				GL11.glNormal3f(x2, y2, z2);
				GL11.glVertex3f(x2, y2, z2);

				float x3 = (float) (Math.cos(nextPhi) * Math.cos(nextTheta) * radius);
				float y3 = (float) (Math.cos(nextPhi) * Math.sin(nextTheta) * radius);
				float z3 = (float) Math.sin(nextPhi) * radius;
				GL11.glNormal3f(x3, y3, z3);
				GL11.glVertex3f(x3, y3, z3);

				float x4 = (float) (Math.cos(nextPhi) * Math.cos(theta) * radius);
				float y4 = (float) (Math.cos(nextPhi) * Math.sin(theta) * radius);
				float z4 = (float) Math.sin(nextPhi) * radius;
				GL11.glNormal3f(x4, y4, z4);
				GL11.glVertex3f(x4, y4, z4);

//				Vector4f v = new Point4f(x1, y1, z1, 0).MinusPoint(new Point4f(x2, y2, z2, 0));
//				Vector4f w = new Point4f(x3, y3, z3, 0).MinusPoint(new Point4f(x2, y2, z2, 0));
//				Vector4f n = v.cross(w).Normal();
//				GL11.glNormal3f(n.x, n.y, n.z);
//				GL11.glVertex3f(x1, y1, z1);
//				GL11.glVertex3f(x2, y2, z2);
//				GL11.glVertex3f(x3, y3, z3);
//				GL11.glVertex3f(x4, y4, z4);


			}
		}

		GL11.glEnd();
	}
}

 