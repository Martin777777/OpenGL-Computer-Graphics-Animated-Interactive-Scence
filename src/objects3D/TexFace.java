package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class TexFace {

    public void drawTexFace(Texture texture, float w) {

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        texture.bind();

        GL11.glBegin(GL11.GL_QUADS);

        GL11.glTexCoord2f(0, 0);
        GL11.glNormal3f(0, 1, 0);
        GL11.glVertex3f(0, 0, 0);

        GL11.glTexCoord2f(5, 0);
        GL11.glNormal3f(0, 1, 0);
        GL11.glVertex3f(w, 0, 0);

        GL11.glTexCoord2f(5, 5);
        GL11.glNormal3f(0, 1, 0);
        GL11.glVertex3f(w, 0, w);

        GL11.glTexCoord2f(0, 5);
        GL11.glNormal3f(0, 1, 0);
        GL11.glVertex3f(0, 0, w);

        GL11.glEnd();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

}
