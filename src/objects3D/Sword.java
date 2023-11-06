package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Sword {

    public void drawSword(Texture texture) {
        WholeTexCylinder wholeTexCylinder = new WholeTexCylinder();
        Cone cone = new Cone();

        GL11.glPushMatrix();{
            wholeTexCylinder.DrawWholeTexCylinder(0.1f, 0.7f, 16, texture);
            GL11.glPushMatrix();{
                GL11.glTranslatef(0, 0, 0.7f);
                wholeTexCylinder.DrawWholeTexCylinder(0.5f, 0.1f, 16, texture);
                GL11.glPushMatrix();{
                    cone.drawCone(0.2f, 3);
                }GL11.glPopMatrix();
            }GL11.glPopMatrix();
        }GL11.glPopMatrix();
    }

}
