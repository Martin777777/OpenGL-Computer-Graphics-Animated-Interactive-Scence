package objects3D;

/**
 *
 * This class represents a cube with textures
 *
 * The Method involved in this class is DrawTexCube
 *
 * @author Ma Zixiao
 *
 *
 */

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

import java.util.Arrays;

public class TexCube {

	
	public TexCube() {

	}
	
	// This method is to draw the cube with textures
	// For each face of the cube, a piece of texture is pasted
	// which means that (0, 0), (1, 0), (1, 1), (0, 1)
	// are the four groups of surface parameters for
	// the four points of a face
	public void DrawTexCube(float length, Texture texture)
 {
	 //The points
	 Point4f[] vertices = new Point4f[]{
			 new Point4f(-length, -length, -length, 0),
			 new Point4f(-length, -length, length, 0),
			 new Point4f(-length, length, length, 0),
			 new Point4f(-length, length, -length, 0),
			 new Point4f(length, -length, -length, 0),
			 new Point4f(length, -length, length, 0),
			 new Point4f(length, length, length, 0),
			 new Point4f(length, length, -length, 0)
	 };

	 //The faces
	 int[][] faces = new int[][]{
			 {0, 1, 2, 3},
			 {4, 5, 6, 7},
			 {0, 1, 5, 4},
			 {2, 3, 7, 6},
			 {0, 3, 7, 4},
			 {1, 2, 6, 5}
	 };

	 GL11.glEnable(GL11.GL_TEXTURE_2D);
	 texture.bind();

	 GL11.glBegin(GL11.GL_QUADS);

	 Arrays.stream(faces).forEach(face -> {

		 //To get the normal of each face
		 Vector4f v = vertices[face[0]].MinusPoint(vertices[face[1]]);
		 Vector4f w = vertices[face[0]].MinusPoint(vertices[face[2]]);
		 Vector4f normal = v.cross(w).Normal();

		 GL11.glTexCoord2f(0, 0);
		 GL11.glNormal3f(normal.x, normal.y, normal.z);
		 GL11.glVertex3f(vertices[face[0]].x, vertices[face[0]].y, vertices[face[0]].z);

		 GL11.glTexCoord2f(0, 1);
		 GL11.glNormal3f(normal.x, normal.y, normal.z);
		 GL11.glVertex3f(vertices[face[1]].x, vertices[face[1]].y, vertices[face[1]].z);

		 GL11.glTexCoord2f(1, 1);
		 GL11.glNormal3f(normal.x, normal.y, normal.z);
		 GL11.glVertex3f(vertices[face[2]].x, vertices[face[2]].y, vertices[face[2]].z);

		 GL11.glTexCoord2f(1, 0);
		 GL11.glNormal3f(normal.x, normal.y, normal.z);
		 GL11.glVertex3f(vertices[face[3]].x, vertices[face[3]].y, vertices[face[3]].z);


	 });

	 GL11.glEnd();
	 GL11.glDisable(GL11.GL_TEXTURE_2D);
	 
		
		

	}

}
 
	/*
	 
	 
}

	*/
	 