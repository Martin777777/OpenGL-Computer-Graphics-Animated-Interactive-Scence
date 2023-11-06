package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Horse {

    TexCube texCube = new TexCube();


    public void drawHorse(Texture texture, float angle1, float angle2, float angle3, float angle4, boolean moving) {
        GL11.glPushMatrix();{

            GL11.glPushMatrix();
            {
                GL11.glScalef(0.7f, 1, 1.5f);
                texCube.DrawTexCube(1, texture);
            }
            GL11.glPopMatrix();


            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.7f - 0.125f, -0.9f, 1.5f - 0.125f);
                GL11.glTranslatef(0, 0.9f, 0);
                GL11.glRotatef(moving?angle1:0, 1, 0, 0);
                GL11.glTranslatef(0, -0.9f, 0);
                GL11.glScalef(0.17f, 0.9f, 0.17f);
                texCube.DrawTexCube(1, texture);
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0, -1.3f, 0);
                    GL11.glScalef(0.7f, 0.9f, 0.7f);
                    texCube.DrawTexCube(1, texture);
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0, -1f, 0);
                        GL11.glScalef(1.5f, 0.35f, 1.5f);
                        texCube.DrawTexCube(1, texture);
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            }
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-0.7f + 0.125f, -0.9f, 1.5f - 0.125f);
                GL11.glTranslatef(0, 0.9f, 0);
                GL11.glRotatef(moving?angle2:0, 1, 0, 0);
                GL11.glTranslatef(0, -0.9f, 0);
                GL11.glScalef(0.17f, 0.9f, 0.17f);
                texCube.DrawTexCube(1, texture);
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0, -1.3f, 0);
                    GL11.glScalef(0.7f, 0.9f, 0.7f);
                    texCube.DrawTexCube(1, texture);
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0, -1f, 0);
                        GL11.glScalef(1.5f, 0.35f, 1.5f);
                        texCube.DrawTexCube(1, texture);
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            }
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-0.7f + 0.125f, -0.9f, -1.5f + 0.125f);
                GL11.glTranslatef(0, 0.9f, 0);
                GL11.glRotatef(moving?angle3:0, 1, 0, 0);
                GL11.glTranslatef(0, -0.9f, 0);
                GL11.glScalef(0.17f, 0.9f, 0.17f);
                texCube.DrawTexCube(1, texture);
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0, -1.3f, 0);
                    GL11.glScalef(0.7f, 0.7f, 0.7f);
                    texCube.DrawTexCube(1, texture);
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0, -1f, 0);
                        GL11.glScalef(1.5f, 0.35f, 1.5f);
                        texCube.DrawTexCube(1, texture);
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            }
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.7f - 0.125f, -0.9f, -1.5f + 0.125f);
                GL11.glTranslatef(0, 0.9f, 0);
                GL11.glRotatef(moving?angle4:0, 1, 0, 0);
                GL11.glTranslatef(0, -0.9f, 0);
                GL11.glScalef(0.17f, 0.9f, 0.17f);
                texCube.DrawTexCube(1, texture);
                GL11.glPushMatrix();{
                    GL11.glTranslatef(0, -1.3f, 0);
                    GL11.glScalef(0.7f, 0.7f, 0.7f);
                    texCube.DrawTexCube(1, texture);
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0, -1f, 0);
                        GL11.glScalef(1.5f, 0.35f, 1.5f);
                        texCube.DrawTexCube(1, texture);
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            }
            GL11.glPopMatrix();

            GL11.glPushMatrix(); {
                GL11.glTranslatef(0, 1.7f, -1);
                GL11.glRotatef(77, 1, 0, 0);
                GL11.glScalef(0.1f, 0.3f, 1.7f);
                texCube.DrawTexCube(1, texture);
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0, -1.5f, 0);
                    GL11.glScalef(3.5f, 1.3f, 1);
                    texCube.DrawTexCube(1, texture);
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.5f, 0.8f, -1.1f);
                        GL11.glScalef(0.3f, 0.07f, 0.15f);
                        texCube.DrawTexCube(1, texture);
                    }GL11.glPopMatrix();
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(- 0.5f, 0.8f, -1.1f);
                        GL11.glScalef(0.3f, 0.07f, 0.15f);
                        texCube.DrawTexCube(1, texture);
                    } GL11.glPopMatrix();
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0f, -1.5f, -0.63f);
                        GL11.glScalef(0.77f, 1f, 0.26f);
                        texCube.DrawTexCube(1, texture);
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            } GL11.glPopMatrix();

            GL11.glPushMatrix(); {
                GL11.glTranslatef(0, -0.7f, 1.7f);
                GL11.glRotatef(80, 1, 0, 0);
                GL11.glScalef(0.1f, 0.15f, 1f);
                texCube.DrawTexCube(1, texture);
            } GL11.glPopMatrix();

        } GL11.glPopMatrix();

    }

}
