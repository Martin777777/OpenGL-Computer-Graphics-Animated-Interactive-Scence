package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class OpenBox {

    TexFace face = new TexFace();
    WholeTexCylinder cylinder = new WholeTexCylinder();

    public void drawBox(Texture texture, int mode, float angle) {
//        y
        if (mode == 0 || mode == 1){
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 0, 0);
                face.drawTexFace(texture, 1);
            }
            GL11.glPopMatrix();
        }

//        y
        if (mode == 0 || mode == 2){
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 1, 0);
                face.drawTexFace(texture, 1);
            }
            GL11.glPopMatrix();
        }

//        z
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glTranslatef(0, 0, -1);
            face.drawTexFace(texture, 1);
        }GL11.glPopMatrix();

//        z
        GL11.glPushMatrix();{
            GL11.glTranslatef(0, 0, 1);
            GL11.glPushMatrix();{
                GL11.glRotatef(90, 1, 0, 0);
                GL11.glTranslatef(0, 0, -1);
                face.drawTexFace(texture, 1);
            }GL11.glPopMatrix();

        }GL11.glPopMatrix();

//        x
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 0, 0, 1);
//            GL11.glTranslatef(0, 0, -1);
            face.drawTexFace(texture, 1);
        }GL11.glPopMatrix();

        GL11.glPushMatrix();{

//            x
            GL11.glTranslatef(1, 0, 0);
            GL11.glPushMatrix();{
                GL11.glRotatef(angle, 0, 1, 0);
                GL11.glRotatef(90, 0, 0, 1);
//                GL11.glTranslatef(0, 0, -1);
                face.drawTexFace(texture, 1);
                GL11.glPushMatrix();{

                    GL11.glTranslatef(0.3f, 0, 0);
                    GL11.glRotatef(90, 0, 1, 0);
                    cylinder.DrawWholeTexCylinder(0.05f, 0.5f, 32, texture);

                }GL11.glPopMatrix();
            }GL11.glPopMatrix();

        }GL11.glPopMatrix();
    }

}