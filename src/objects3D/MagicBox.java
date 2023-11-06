package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class MagicBox {
    OpenBox openBox = new OpenBox();

    public void drawBox(Texture texture, boolean toMove, float angle, float convex) {

        GL11.glPushMatrix(); {
            openBox.drawBox(texture, toMove? 0:1, angle);
            GL11.glPushMatrix(); {
                GL11.glTranslatef(0, 0, convex);
                GL11.glTranslatef(0, 1, 0);
                openBox.drawBox(texture, toMove? 0:3, angle);
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0, 0, -convex);
                    GL11.glTranslatef(0, 1, 0);
                    openBox.drawBox(texture, toMove? 0:2, angle);
                }GL11.glPopMatrix();
            }GL11.glPopMatrix();
        }GL11.glPopMatrix();

    }
}
