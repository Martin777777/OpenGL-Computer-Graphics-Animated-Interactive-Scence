package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class HangingLight {

    public void drawLight(Texture[] textures) {
        WholeTexCylinder cylinder = new WholeTexCylinder();
        Cone cone = new Cone();
        Sphere bulb = new Sphere();

        GL11.glPushMatrix();{
            cylinder.DrawWholeTexCylinder(0.2f, 2f, 32, textures[0]);
            GL11.glPushMatrix();{
                GL11.glTranslatef(0, 0, 1.5f);
                GL11.glRotatef(90, 1, 0, 0);
                cylinder.DrawWholeTexCylinder(0.2f, 0.5f, 32, textures[1]);
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0, 0, 1.3f);
                    GL11.glRotatef(180, 1, 0, 0);
                    cone.drawCone(1, 1);
                    GL11.glPushMatrix();{
                        Color.yellow.bind();
                        bulb.DrawSphere(0.3f, 32, 32);
                        Color.white.bind();
                    }GL11.glPopMatrix();
                }GL11.glPopMatrix();
            }GL11.glPopMatrix();
        }GL11.glPopMatrix();

    }

}
