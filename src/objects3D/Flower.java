package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import sun.applet.Main;

public class Flower {

    public void drawFlower(Texture[] textures, float delta) {

        float theta = (float) (0.0003 * delta * Math.PI);


        WholeTexCylinder cylinder = new WholeTexCylinder();
        TexSphere sphere = new TexSphere();
        Circle circle = new Circle();

        Cylinder cylinder1 = new Cylinder();

        GL11.glPushMatrix();{
            GL11.glRotatef(-90, 1, 0, 0);
            cylinder.DrawWholeTexCylinder(2, 2, 16, textures[0]);
            GL11.glPushMatrix();{
                GL11.glTranslatef(0, 0, 2);
                cylinder.DrawWholeTexCylinder(2.3f, 0.3f, 16, textures[0]);
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0, 0, 0.2f);
                    GL11.glRotatef((float) (Math.sin(theta)*30), 0, 1, 0);
                    Color.green.bind();
                    cylinder1.DrawCylinder(0.2f, 5f, 16);
                    Color.white.bind();
                    GL11.glPushMatrix();{
                        GL11.glTranslatef(0, 0, 5);
                        GL11.glRotatef(90, 1, 0, 0);
                        sphere.DrawTexSphere(2, 16, 16, textures[1]);
                        GL11.glPushMatrix();{
                            for (float f=0; f < Math.PI * 2; f += 2*Math.PI/6) {
                                GL11.glPushMatrix();{
                                    GL11.glTranslatef((float) Math.cos(f)*2f, (float) Math.sin(f)*2f, 0);
                                    Color.red.bind();
                                    circle.drawCircle();
                                    Color.white.bind();
                                }GL11.glPopMatrix();
                            }
                        }GL11.glPopMatrix();
                    }GL11.glPopMatrix();
                }GL11.glPopMatrix();
            }GL11.glPopMatrix();


        }GL11.glPopMatrix();

    }

}
