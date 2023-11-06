package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Fan {

    public void drawFan(Texture[] textures, float delta){
        WholeTexCylinder cylinder = new WholeTexCylinder();
        TexSphere sphere = new TexSphere();
        Circle circle = new Circle();

        float theta = (float) (5 * delta * Math.PI);

        GL11.glPushMatrix();
        {
            cylinder.DrawWholeTexCylinder(0.2f, 2f, 16, textures[0]);
            GL11.glPushMatrix();{
            GL11.glTranslatef(0, 0, 3.5f);
            GL11.glRotatef(theta, 0, 0, 1);
            sphere.DrawTexSphere(1.5f, 16, 16, textures[1]);
            GL11.glPushMatrix();{
                for (float f=0; f < Math.PI * 2; f += 2*Math.PI/3) {
                    GL11.glPushMatrix();{
                        GL11.glTranslatef((float) Math.cos(f)*2f, (float) Math.sin(f)*2f, 0);
                        GL11.glRotatef(10, 0, 1, 0);
                        Color.red.bind();
                        GL11.glScalef(2, 2, 2);
                        circle.drawCircle();
                        Color.white.bind();
                    }GL11.glPopMatrix();
                }
            }GL11.glPopMatrix();
        }GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}
