package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class LiftPipe {

    TexFace face = new TexFace();

    public void drawLiftPipe(Texture texture, float fontLength, float backLength) {
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(0, 0, 0);
            face.drawTexFace(texture, 1);
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        {
            GL11.glTranslatef(0, backLength, 0);
            face.drawTexFace(texture, 1);
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();{
            GL11.glScalef(1, backLength, 1);
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glTranslatef(0, 0, -1);
            face.drawTexFace(texture, 1);
        }GL11.glPopMatrix();

        GL11.glPushMatrix();{
            GL11.glTranslatef(0, 0, 1);
            GL11.glPushMatrix();{
                GL11.glScalef(1, backLength, 1);
                GL11.glRotatef(90, 1, 0, 0);
                GL11.glTranslatef(0, 0, -1);
                face.drawTexFace(texture, 1);
            }GL11.glPopMatrix();

        }GL11.glPopMatrix();

        GL11.glPushMatrix();{
            GL11.glRotatef(90, 0, 0, 1);
//            GL11.glTranslatef(0, 0, -1);
            GL11.glScalef(fontLength, 1, 1);
            face.drawTexFace(texture, 1);
        }GL11.glPopMatrix();

        GL11.glPushMatrix();{
//            x
            GL11.glTranslatef(1, 0, 0);
            GL11.glRotatef(90, 0, 0, 1);
            GL11.glScalef(backLength, 1, 1);
            face.drawTexFace(texture, 1);
        }GL11.glPopMatrix();
    }

}
