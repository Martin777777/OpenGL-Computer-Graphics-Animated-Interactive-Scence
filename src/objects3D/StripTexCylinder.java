package objects3D;

/**
 *
 * This class represents a sphere that has textures as strips on it
 *
 * The Method involved in this class is DrawStripTexCylinder
 *
 * @author Ma Zixiao
 *
 *
 */

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class StripTexCylinder {
    public StripTexCylinder() {
    }

    // This method is to draw the cylinder
    // Every time this method draws a segment of the cylinder,
    // a texture is pasted to the segment
    // Therefore, when the whole process of drawing is completed,
    // the cylinder is drawn with multiple strips
    public void DrawStripTexCylinder(float radius, float height, int nSegments, Texture texture)
    {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        texture.bind();

        GL11.glBegin(GL11.GL_TRIANGLES);

        for ( float i = 0; i <= nSegments; i++ ){
            float angle = (float) (Math.PI * 2 * i / nSegments);
            float nextAngle = (float) (Math.PI * 2 * (i+1) / nSegments);

            float x1 = (float) Math.cos(angle) * radius, y1 = (float) Math.sin(angle) * radius;
            float x2 = (float) Math.cos(nextAngle) * radius, y2 = (float) Math.sin(nextAngle) * radius;

            GL11.glTexCoord2d(0, 0);
            GL11.glNormal3f(x1, y1, 0);
            GL11.glVertex3f(x1, y1, 0);

            GL11.glTexCoord2d(1, 1);
            GL11.glNormal3f(x2, y2, 0);
            GL11.glVertex3f(x2, y2, height);

            GL11.glTexCoord2d(0, 1);
            GL11.glNormal3f(x1, y1, 0);
            GL11.glVertex3f(x1, y1, height);

            GL11.glTexCoord2d(0, 0);
            GL11.glNormal3f(x1, y1, 0);
            GL11.glVertex3f(x1, y1, 0);

            GL11.glTexCoord2d(1, 0);
            GL11.glNormal3f(x2, y2, 0);
            GL11.glVertex3f(x2, y2, 0);

            GL11.glTexCoord2d(1, 1);
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
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
}
