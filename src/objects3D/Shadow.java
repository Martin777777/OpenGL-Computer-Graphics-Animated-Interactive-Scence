package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Shadow {

    public void drawShadow(float radius) {

        Circle circle = new Circle();

        GL11.glPushMatrix();{
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glScalef(radius, radius, radius);
            GL11.glColor4f(0, 0, 0, 0.9f);
            circle.drawCircle();
            Color.white.bind();
        }GL11.glPopMatrix();

    }

}
