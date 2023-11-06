package objects3D;

import org.lwjgl.opengl.GL11;

public class Cone {

    public void drawCone(float radius, float height) {
        GL11.glBegin(GL11.GL_TRIANGLES);

        // using a loop around a tube's circumference
        for ( float i = (float) 0.0; i < 32; i+=1.0 ){
            float angle = (float) (Math.PI * 2.0 * i / 32);
            float nextAngle = (float) (Math.PI  * (i + 1.0) * 2.0 / 32);

            // calculate sin and cosine, multiple by value of radius
            float x1 = (float) Math.sin(angle) * radius;
            float y1 = (float) Math.cos(angle) * radius;
            float x2 = (float) Math.sin(nextAngle) * radius;
            float y2 = (float) Math.cos(nextAngle) * radius;


            // draw top (green) triangle
            GL11.glNormal3f(x1, y1, 0);
            GL11.glVertex3f(x1, y1, 0);//

            GL11.glNormal3f(x2, y2, 0);
            GL11.glVertex3f(x2, y2, 0);//

            GL11.glNormal3f(0, 0, height);
            GL11.glVertex3f(0, 0, height);//


        }

        GL11.glEnd();
    }

}
