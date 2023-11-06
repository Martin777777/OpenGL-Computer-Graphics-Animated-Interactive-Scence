package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.io.IOException;

import static GraphicsObjects.Utils.ConvertForGL;

public class Assistant {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    // primary colours
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    // secondary colours
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
    static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

    // other colours
    static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

    // This value shows the last angle of the arm so we know that whether the arm is moving forward
    static float LastLimbRotation;

    public void DrawHuman(float delta, int mode, Texture[] textures) {
        float theta = (float) (5 * delta * Math.PI);
        float LimbRotation = 0;
        float RightLimbRotation = 0;
        float LeftForearmRotation = 0;
        float RightForearmRotation = 0;
        float LeftHighLegRotation = 0;
        float RightHighLegRotation = 0;
        float LeftLowLegRotation = 0;
        float RightLowLegRotation = 0;

        switch (mode) {
            case 0:
                LimbRotation = 0;
                break;
            case 1:
                LimbRotation = (float) Math.sin(theta) * 45;
                RightLimbRotation = -LimbRotation;


                // Here is the logic to calculate the rotate of arms and legs.
                // The way they move is described above.
                if (LimbRotation >= 0) {
                    LeftForearmRotation = 2 * LimbRotation;
                    RightForearmRotation = 0;
                    LeftHighLegRotation = (float) -Math.sin(theta) * 30;
                    LeftLowLegRotation = 0;
                    if (LimbRotation <= 30 && LimbRotation > LastLimbRotation) {
                        RightHighLegRotation = LimbRotation;
                        RightLowLegRotation = RightHighLegRotation * (-2.0f);
                    } else if (LimbRotation > 30 && LimbRotation > LastLimbRotation) {
                        RightHighLegRotation = 30;
                        RightLowLegRotation = 30 * (-2.0f) + 30 * 2.0f * (LimbRotation - 30) * (1f / 15);
                    } else if (LimbRotation < LastLimbRotation) {
                        RightHighLegRotation = (float) Math.sin(theta) * 30;
                        RightLowLegRotation = 0;
                    }
                } else {
                    RightForearmRotation = -2 * LimbRotation;
                    LeftForearmRotation = 0;
                    RightHighLegRotation = (float) Math.sin(theta) * 30;
                    RightLowLegRotation = 0;
                    if (LimbRotation >= -30 && LimbRotation < LastLimbRotation) {
                        LeftHighLegRotation = -LimbRotation;
                        LeftLowLegRotation = LeftHighLegRotation * (-2.0f);
                    } else if (LimbRotation < -30 && LimbRotation < LastLimbRotation) {
                        LeftHighLegRotation = 30;
                        LeftLowLegRotation = 30 * (-2.0f) + 30 * 2.0f * (-LimbRotation - 30) * (1f / 15);
                    } else if (LimbRotation > LastLimbRotation) {
                        LeftHighLegRotation = (float) -Math.sin(theta) * 30;
                        LeftLowLegRotation = 0;
                    }
                }
                // This value shows the last angle of the arm so we know that whether the arm is moving forward
                LastLimbRotation = LimbRotation;
                break;
            case 3:
                if (Math.sin(theta) >= 0) {
                    RightLimbRotation = - (float) Math.sin(theta) * 120;
                    RightForearmRotation = RightLimbRotation * 0.9f;
                    LeftForearmRotation = - RightForearmRotation;
                    LimbRotation = - RightLimbRotation;
                }
                break;
            case 2:
                if (Math.sin(theta / 1.5f) >= 0) {
                    theta = theta / 1.5f;
                    float f = (float) Math.sin(theta) * 180;
                    if (f <= 120) {
                        LimbRotation = f;
                        LeftForearmRotation = LimbRotation * 0.6f;
                    }
                    else {
                        LimbRotation = 120;
                        LeftForearmRotation = 72 - 70 * (f-130)/50;
                    }
//					RightForearmRotation = -LeftForearmRotation;
//					RightLimbRotation = - LimbRotation;
                }
                break;
            case 4:
                if (Math.sin(theta) >= 0) {
                    float f = (float) Math.sin(theta) * 90;
                    if (f <= 45) {
                        RightLimbRotation = -f;
                        RightForearmRotation = -2 * RightLimbRotation;
                    } else {
                        RightLimbRotation = -45 + 135 * ((f - 45)/45);
                        RightForearmRotation = 90 * ((90 - f) / 45);

                    }
                }
                break;

        }

        Sphere sphere= new Sphere();
        TexSphere texSphere = new TexSphere();
        Cylinder cylinder= new Cylinder();

        StripTexCylinder texCylinder = new StripTexCylinder();
        WholeTexCylinder wholeTexCylinder = new WholeTexCylinder();
        Cone cone = new Cone();


        Color.white.bind();

        GL11.glPushMatrix();

        {
            GL11.glTranslatef(0.0f,0.5f,0.0f);
            texSphere.DrawTexSphere(0.5f, 16, 16, textures[8]);

            // abdomen
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(white));
            GL11.glPushMatrix();{
            GL11.glTranslatef(0.0f, 0.25f, 0.0f);
            GL11.glRotatef(- 90, 1, 0, 0);
            texCylinder.DrawStripTexCylinder(0.3f, 0.5f, 32, textures[4]);

            //  chest
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(white));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.0f, 0.0f, 0.7f);
                GL11.glRotatef(90, 1, 0, 0);

                texSphere.DrawTexSphere(0.5f, 16, 16, textures[3]);


                // neck
                GL11.glColor3f(white[0], white[1], white[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(white));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                    GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    //                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f);
                    wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, 32, textures[10]);


                    // head
                    GL11.glColor3f(white[0], white[1], white[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(red));
                    Color.white.bind();
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f, 0.0f, 1.0f);
                        texSphere.DrawTexSphere(0.5f, 16, 16, textures[0]);

                        GL11.glPushMatrix();{
                            GL11.glTranslatef(0, 0, 0.35f);
                            Color.cyan.bind();
                            cone.drawCone(0.57f, 1.2f);
                            Color.white.bind();
                            GL11.glPushMatrix();{
                                GL11.glTranslatef(0, 0, 1.2f);
                                texSphere.DrawTexSphere(0.2f, 16, 16, textures[3]);
                            }GL11.glPopMatrix();
                        }GL11.glPopMatrix();

                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();


                    // left shoulder
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix();
                    {
//						 GL11.glTranslatef(0.5f, 0.4f, 0.0f);
                        GL11.glTranslatef(0.7f, 0.3f, 0.0f);
                        switch (mode){
                            case 1:
                            case 2:
                                GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                                break;
                            case 3:
                                GL11.glRotatef(LimbRotation, 0.0f, 0.0f, 1.0f);
                                break;
                        }
                        texSphere.DrawTexSphere(0.3f, 16, 16, textures[2]);



                        // left arm
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);



                            //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);
                            wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, 32, textures[5]);


                            // left elbow
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                switch (mode){
                                    case 1:
                                    case 2:
                                        GL11.glRotatef(LeftForearmRotation, 1.0f, 0.0f, 0.0f);
                                        break;
                                    case 3:
                                        GL11.glRotatef(LeftForearmRotation, 0.0f, 1.0f, 0.0f);
                                        break;
                                }
                                texSphere.DrawTexSphere(0.2f, 16, 16, textures[6]);

                                //left forearm
                                GL11.glColor3f(orange[0], orange[1], orange[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(orange));
                                Color.white.bind();
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);

                                    //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.1f, 0.7f, 32, textures[7]);

                                    // left hand
                                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(blue));
                                    Color.white.bind();
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        texSphere.DrawTexSphere(0.2f, 16, 16, textures[9]);


                                    }
                                    GL11.glPopMatrix();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                    // to chest

                    // right shoulder
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(-0.7f, 0.3f, 0.0f);
                        switch (mode){
                            case 1:
                            case 2:
                            case 4:
                                GL11.glRotatef(RightLimbRotation, 1.0f, 0.0f, 0.0f);
                                break;
                            case 3:
                                GL11.glRotatef(RightLimbRotation, 0.0f, 0.0f, 1.0f);
                                break;
                        }
//					 sphere.DrawSphere(0.25f, 32, 32);
                        texSphere.DrawTexSphere(0.3f, 16, 16, textures[2]);


                        // right arm
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);



                            //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);
                            wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, 32, textures[5]);

                            // right elbow
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                switch (mode){
                                    case 1:
                                    case 2:
                                    case 4:
                                        GL11.glRotatef(RightForearmRotation, 1.0f, 0.0f, 0.0f);
                                        break;
                                    case 3:
                                        GL11.glRotatef(RightForearmRotation, 0.0f, 1.0f, 0.0f);
                                        break;
                                }
                                texSphere.DrawTexSphere(0.2f, 16, 16, textures[6]);

                                //right forearm
                                GL11.glColor3f(orange[0], orange[1], orange[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(orange));
                                Color.white.bind();
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);

                                    //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.1f, 0.7f, 32, textures[7]);

                                    // right hand
                                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(blue));
                                    Color.white.bind();
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        texSphere.DrawTexSphere(0.2f, 16, 16, textures[9]);

                                    }
                                    GL11.glPopMatrix();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();


                    //chest


                }
                GL11.glPopMatrix();
            }GL11.glPopMatrix();


            // pelvis

            // right hip
            GL11.glColor3f(blue[0], blue[1], blue[2]);
            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
            Color.white.bind();
            GL11.glPushMatrix(); {
//		                GL11.glTranslatef(-0.5f,-0.2f,0.0f);
                GL11.glTranslatef(-0.32f,-0.5f,0.0f);
                GL11.glRotatef(90.0f, 1, 0, 0);
                GL11.glRotatef(RightHighLegRotation,1.0f,0.0f,0.0f);

                texSphere.DrawTexSphere(0.25f, 16, 16, textures[6]);


                // right high leg
                GL11.glColor3f(orange[0], orange[1], orange[2]);
                GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
                Color.white.bind();
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f,0.0f);
                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);



                    //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                    texCylinder.DrawStripTexCylinder(0.15f,0.7f,32, textures[10]);


                    // right knee
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,0.75f);
                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                        GL11.glRotatef(RightLowLegRotation, 1, 0, 0);
                        texSphere.DrawTexSphere(0.25f, 16, 16, textures[9]);

                        //right low leg
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,0.0f);
                            // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
                            //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);

                            texCylinder.DrawStripTexCylinder(0.15f,0.7f,32, textures[10]);

                            // right foot
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,0.75f);
                                texSphere.DrawTexSphere(0.3f, 16, 16, textures[11]);
                                GL11.glColor3f(blue[0], blue[1], blue[2]);
                                GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
                                Color.white.bind();
                                GL11.glPushMatrix();{
                                    GL11.glTranslatef(0.0f, 0.0f, 0.15f);
                                    GL11.glRotatef(90, 1, 0, 0);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.5f, 32, textures[11]);
                                } GL11.glPopMatrix();

                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            } GL11.glPopMatrix();

            // pelvis


            // left hip
            GL11.glColor3f(blue[0], blue[1], blue[2]);
            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
            Color.white.bind();
            GL11.glPushMatrix(); {
//				 GL11.glTranslatef(0.5f,-0.2f,0.0f);
                GL11.glTranslatef(0.32f,-0.5f,0.0f);
                GL11.glRotatef(90.0f, 1, 0, 0);
                GL11.glRotatef(LeftHighLegRotation,1.0f,0.0f,0.0f);

                texSphere.DrawTexSphere(0.25f, 16, 16, textures[6]);


                // left high leg
                GL11.glColor3f(orange[0], orange[1], orange[2]);
                GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
                Color.white.bind();
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f,0.0f);
                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);



                    //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                    texCylinder.DrawStripTexCylinder(0.15f,0.7f,32, textures[10]);


                    // left knee
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,0.75f);
                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                        GL11.glRotatef(LeftLowLegRotation, 1, 0, 0);
                        texSphere.DrawTexSphere(0.25f, 16, 16, textures[9]);

                        //left low leg
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,0.0f);

                            // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
                            //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                            texCylinder.DrawStripTexCylinder(0.15f,0.7f,32, textures[10]);

                            // left foot
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,0.75f);
                                texSphere.DrawTexSphere(0.3f, 16, 16, textures[11]);
                                GL11.glPushMatrix();{
                                    GL11.glTranslatef(0.0f, 0.0f, 0.15f);
                                    GL11.glRotatef(90, 1, 0, 0);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.5f, 32, textures[11]);
                                } GL11.glPopMatrix();


                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            } GL11.glPopMatrix();

        } GL11.glPopMatrix();

        }


    }

}
