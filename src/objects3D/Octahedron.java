package objects3D;

/**
 *
 * This class represents a octahedron
 *
 * The Method involved in this class is DrawOctahedron
 *
 * @author Ma Zixiao
 *
 *
 */

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

import java.util.Arrays;

public class Octahedron {


    public Octahedron()
    {

    }


    //This method is to draw the octahedron on the screen
    //To do that, this method first declares and initializes the points of this octahedron and
    //the faces of this octahedron which is represented by the index of the points of them
    //After that, this method draw the faces one by one by first calculating the normal vector of the face and
    //then draw the face by describe its points
    public void DrawOctahedron(){

        //The points
        Point4f[] vertices = new Point4f[]{
                new Point4f(0, 0, 1, 0),
                new Point4f(1, 0, 0, 0),
                new Point4f(0, 1, 0, 0),
                new Point4f(-1, 0, 0, 0),
                new Point4f(0, 0, -1, 0),
                new Point4f(0, -1, 0, 0)
        };

        //The faces
        int[][] faces = new int[][]{
                {0, 1, 2},
                {0, 2, 3},
                {0, 3, 5},
                {0, 1, 5},
                {2, 3, 4},
                {1, 2, 4},
                {1, 4, 5},
                {3, 4, 5}
        };

        GL11.glBegin(GL11.GL_TRIANGLES);

        Arrays.stream(faces).forEach(face -> {

            //Define the normal vector of each face
            Vector4f v = vertices[face[0]].MinusPoint(vertices[face[1]]);
            Vector4f w = vertices[face[0]].MinusPoint(vertices[face[2]]);
            Vector4f n = v.cross(w).Normal();

            GL11.glNormal3f(n.x, n.y, n.z);

            //Draw each face by describing its points
            for ( int i = 0; i < 3; i++ ){
                GL11.glVertex3f(vertices[face[i]].x, vertices[face[i]].y, vertices[face[i]].z);
            }
        });

        GL11.glEnd();

    }

}
