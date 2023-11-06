package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;

public class Circle {

    public void drawCircle() {
        GL11.glBegin(GL11.GL_TRIANGLES);

        for ( float i = 0; i <= 32; i++ ){
            float angle = (float) (Math.PI * 2 * i / 32);
            float nextAngle = (float) (Math.PI * 2 * (i+1) / 32);

            float x1 = (float) Math.cos(angle), y1 = (float) Math.sin(angle);
            float x2 = (float) Math.cos(nextAngle), y2 = (float) Math.sin(nextAngle);


            Vector4f v1 = new Point4f(x1, y1, 0, 0).MinusPoint(new Point4f(0, 0, 0, 0));
            Vector4f w1 = new Point4f(x2, y2, 0, 0).MinusPoint(new Point4f(0, 0, 0, 0));
            Vector4f n = v1.cross(w1).Normal();
            GL11.glNormal3f(n.x, n.y, n.z);
            GL11.glVertex3f(x1, y1, 0);
            GL11.glNormal3f(n.x, n.y, n.z);
            GL11.glVertex3f(x2, y2, 0);
            GL11.glNormal3f(n.x, n.y, n.z);
            GL11.glVertex3f(0, 0, 0);

        }

        GL11.glEnd();

    }

}
