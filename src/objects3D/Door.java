package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Door {

    public void drawDoor(Texture[] textures, float angle) {

        TexFace face = new TexFace();


        GL11.glPushMatrix();{
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glRotatef(180, 0, 0, 1);
            GL11.glScalef(1, 1, 2);
            GL11.glPushMatrix();
            {
                face.drawTexFace(textures[0], 1);
            }
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            {
                GL11.glRotatef(angle+0.1f, 0, 0, 1);
                face.drawTexFace(textures[1], 1);
            } GL11.glPopMatrix();
        }GL11.glPopMatrix();
    }

}
