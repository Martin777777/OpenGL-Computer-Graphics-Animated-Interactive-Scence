package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Table {

    TexCube cube = new TexCube();

    public void drawTable(Texture texture) {
        GL11.glPushMatrix();{

            //legs

            GL11.glPushMatrix();{
                GL11.glTranslatef(- 1, 0, - 1);
                GL11.glScalef(0.5f, 3, 0.5f);
                cube.DrawTexCube(0.3f, texture);
            }GL11.glPopMatrix();


            GL11.glPushMatrix();{
                GL11.glTranslatef(- 1, 0, 1);
                GL11.glScalef(0.5f, 3, 0.5f);
                cube.DrawTexCube(0.3f, texture);
            }GL11.glPopMatrix();


            GL11.glPushMatrix();{
                GL11.glTranslatef(1, 0, 1);
                GL11.glScalef(0.5f, 3, 0.5f);
                cube.DrawTexCube(0.3f, texture);
            }GL11.glPopMatrix();


            GL11.glPushMatrix();{
                GL11.glTranslatef(1, 0, -1);
                GL11.glScalef(0.5f, 3, 0.5f);
                cube.DrawTexCube(0.3f, texture);
            }GL11.glPopMatrix();

            //face

            GL11.glPushMatrix();{
                GL11.glTranslatef(0, 1, 0);
                GL11.glScalef(1.2f, 0.2f, 1.2f);
                cube.DrawTexCube(1, texture);
            }GL11.glPopMatrix();

        }GL11.glPopMatrix();
    }

}
