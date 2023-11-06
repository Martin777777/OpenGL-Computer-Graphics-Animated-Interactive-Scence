package objects3D;

/**
 *
 * This class represents a human
 *
 * The Method involved in this class is DrawHuman
 *
 * Detailed information about the human is shown below
 *
 * @author Ma Zixiao
 *
 *
 */

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.lwjgl.opengl.GL11;
import GraphicsObjects.Utils;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


import java.io.IOException;


public class Audience {

    // basic colours
    static float black[] = {0.0f, 0.0f, 0.0f, 1.0f};
    static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

    static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};
    static float spot[] = {0.1f, 0.1f, 0.1f, 0.5f};

    // primary colours
    static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};
    static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

    // secondary colours
    static float yellow[] = {1.0f, 1.0f, 0.0f, 1.0f};
    static float magenta[] = {1.0f, 0.0f, 1.0f, 1.0f};
    static float cyan[] = {0.0f, 1.0f, 1.0f, 1.0f};

    // other colours
    static float orange[] = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float dkgreen[] = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float pink[] = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};

    int cylinderSegment = 16;

    // This value shows the last angle of the arm so we know that whether the arm is moving forward
    static float LastLimbRotation;


    public Audience() {

    }


    public void DrawAudience(float delta,boolean applaud, Texture[] textures) throws IOException {
        float theta = (float) (20 * delta * Math.PI);
        float LimbRotation = 0;
        float LeftForearmRotation = 0;
        float RightForearmRotation = 0;
        float LeftHighLegRotation = 0;
        float RightHighLegRotation = 0;
        float LeftLowLegRotation = 0;
        float RightLowLegRotation = 0;

        LeftHighLegRotation = 180;
        RightHighLegRotation = 180;
        LeftLowLegRotation = - 90;
        RightLowLegRotation = - 90;

        if (!applaud){
            LimbRotation = 30;
            LeftForearmRotation = 30;
            RightForearmRotation = 30;
        }
        else {
            LimbRotation = 30;
            LeftForearmRotation = (float) (Math.sin(theta) * 50);
            RightForearmRotation = LeftForearmRotation;
        }

        Sphere sphere= new Sphere();
        TexSphere texSphere = new TexSphere();
        Cylinder cylinder= new Cylinder();

        StripTexCylinder texCylinder = new StripTexCylinder();
        WholeTexCylinder wholeTexCylinder = new WholeTexCylinder();


        Color.white.bind();
        GL11.glPushMatrix();

        {
            GL11.glTranslatef(0.0f,0.5f,0.0f);
            texSphere.DrawTexSphere(0.5f, 16, 16, textures[0]);

            // abdomen
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
            GL11.glPushMatrix();{
            GL11.glTranslatef(0.0f, 0.25f, 0.0f);
            GL11.glRotatef(- 90, 1, 0, 0);
            texCylinder.DrawStripTexCylinder(0.3f, 0.5f, cylinderSegment, textures[0]);

            //  chest
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.0f, 0.0f, 0.7f);
                GL11.glRotatef(90, 1, 0, 0);

                texSphere.DrawTexSphere(0.5f, 16, 16, textures[0]);


                // neck
                GL11.glColor3f(white[0], white[1], white[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                    GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                    //                    GL11.glRotatef(45.0f,0.0f,1.0f,0.0f);
                    wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, cylinderSegment, textures[1]);


                    // head
                    GL11.glColor3f(white[0], white[1], white[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    Color.white.bind();
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f, 0.0f, 1.0f);
                        texSphere.DrawTexSphere(0.5f, 16, 16, textures[2]);
                        // hat
//                        GL11.glPushMatrix(); {
//                        GL11.glTranslatef(0.0f, 0.0f, 0.3f);
//                        GL11.glPushMatrix(); {
//                            GL11.glTranslatef(0.0f, 0.0f, 0.2f);
//                        } GL11.glPopMatrix();
//                    } GL11.glPopMatrix();
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();


                    // left shoulder
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix();
                    {
//						 GL11.glTranslatef(0.5f, 0.4f, 0.0f);
                        GL11.glTranslatef(0.7f, 0.3f, 0.0f);
                        GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
                        texSphere.DrawTexSphere(0.3f, 16, 16, textures[1]);



                        // left arm
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);



                            //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);
                            wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, cylinderSegment, textures[0]);


                            // left elbow
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                if (applaud){
                                    GL11.glRotatef(130, 1, 0, 0);
                                }
                                GL11.glRotatef(LeftForearmRotation, 0f, -1.0f, 0f);
                                texSphere.DrawTexSphere(0.2f, 16, 16, textures[0]);

                                //left forearm
                                GL11.glColor3f(orange[0], orange[1], orange[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                Color.white.bind();
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);

                                    //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.1f, 0.7f, cylinderSegment, textures[0]);

                                    // left hand
                                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    Color.white.bind();
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        texSphere.DrawTexSphere(0.2f, 16, 16, textures[1]);


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
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(-0.7f, 0.3f, 0.0f);
                        GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);
//					 sphere.DrawSphere(0.25f, 32, 32);
                        texSphere.DrawTexSphere(0.3f, 16, 16, textures[1]);


                        // right arm
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);



                            //   GL11.glRotatef(27.5f,0.0f,1.0f,0.0f);
                            wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, cylinderSegment, textures[1]);

                            // right elbow
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                if (applaud){
                                    GL11.glRotatef(130, 1, 0, 0);
                                }
                                GL11.glRotatef(RightForearmRotation, 0f, 1.0f, 0f);
                                texSphere.DrawTexSphere(0.2f, 16, 16, textures[4]);

                                //right forearm
                                GL11.glColor3f(orange[0], orange[1], orange[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                Color.white.bind();
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);

                                    //   GL11.glRotatef(90.0f,0.0f,1.0f,0.0f);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.1f, 0.7f, cylinderSegment, textures[0]);

                                    // right hand
                                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    Color.white.bind();
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        texSphere.DrawTexSphere(0.2f, 16, 16, textures[4]);


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

            GL11.glPushMatrix();{
                Table table = new Table();
                GL11.glTranslatef(0, -1, 0);
                GL11.glScalef(0.3f, 0.3f, 0.3f);
                table.drawTable(textures[3]);
            }GL11.glPopMatrix();


            // pelvis

            // right hip
            GL11.glColor3f(blue[0], blue[1], blue[2]);
            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
            Color.white.bind();
            GL11.glPushMatrix(); {
//		                GL11.glTranslatef(-0.5f,-0.2f,0.0f);
                GL11.glTranslatef(-0.42f,-0.37f,0.0f);
                GL11.glRotatef(-10.0f, 0, 1, 0);
                GL11.glRotatef(RightHighLegRotation,1.0f,0.0f,0.0f);

                texSphere.DrawTexSphere(0.25f, 16, 16, textures[1]);


                // right high leg
                GL11.glColor3f(orange[0], orange[1], orange[2]);
                GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
                Color.white.bind();
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f,0.0f);
                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);



                    //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                    texCylinder.DrawStripTexCylinder(0.15f,0.7f,cylinderSegment, textures[4]);


                    // right knee
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,0.75f);
                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                        GL11.glRotatef(RightLowLegRotation, 1, 0, 0);
                        texSphere.DrawTexSphere(0.25f, 16, 16, textures[1]);

                        //right low leg
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,0.0f);
                            // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
                            //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);

                            texCylinder.DrawStripTexCylinder(0.15f,0.7f,cylinderSegment, textures[0]);

                            // right foot
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,0.75f);
                                GL11.glRotatef(- 10.0f, 0, 0, 1);
                                texSphere.DrawTexSphere(0.3f, 16, 16, textures[0]);
                                GL11.glColor3f(blue[0], blue[1], blue[2]);
                                GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
                                Color.white.bind();
                                GL11.glPushMatrix();{
                                    GL11.glTranslatef(0.0f, 0.0f, 0.15f);
                                    GL11.glRotatef(90, 1, 0, 0);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.5f, cylinderSegment, textures[0]);
                                } GL11.glPopMatrix();

                            } GL11.glPopMatrix();
                        } GL11.glPopMatrix();
                    } GL11.glPopMatrix();
                } GL11.glPopMatrix();
            } GL11.glPopMatrix();

            // pelvis


            // left hip
            GL11.glColor3f(blue[0], blue[1], blue[2]);
            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
            Color.white.bind();
            GL11.glPushMatrix(); {
//				 GL11.glTranslatef(0.5f,-0.2f,0.0f);
                GL11.glTranslatef(0.42f,-0.37f,0.0f);
                GL11.glRotatef(10f, 0, 1, 0);
                GL11.glRotatef(LeftHighLegRotation,1.0f,0.0f,0.0f);

                texSphere.DrawTexSphere(0.25f, 16, 16, textures[4]);


                // left high leg
                GL11.glColor3f(orange[0], orange[1], orange[2]);
                GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
                Color.white.bind();
                GL11.glPushMatrix(); {
                    GL11.glTranslatef(0.0f,0.0f,0.0f);
                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);



                    //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
                    texCylinder.DrawStripTexCylinder(0.15f,0.7f,cylinderSegment, textures[1]);


                    // left knee
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
                    Color.white.bind();
                    GL11.glPushMatrix(); {
                        GL11.glTranslatef(0.0f,0.0f,0.75f);
                        GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                        GL11.glRotatef(LeftLowLegRotation, 1, 0, 0);
                        texSphere.DrawTexSphere(0.25f, 16, 16, textures[1]);

                        //left low leg
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(orange));
                        Color.white.bind();
                        GL11.glPushMatrix(); {
                            GL11.glTranslatef(0.0f,0.0f,0.0f);

                            // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
                            //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
                            texCylinder.DrawStripTexCylinder(0.15f,0.7f,cylinderSegment, textures[0]);

                            // left foot
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(blue));
                            Color.white.bind();
                            GL11.glPushMatrix(); {
                                GL11.glTranslatef(0.0f,0.0f,0.75f);
                                GL11.glRotatef(10.0f, 0, 0, 1);
                                texSphere.DrawTexSphere(0.3f, 16, 16, textures[0]);
                                GL11.glPushMatrix();{
                                    GL11.glTranslatef(0.0f, 0.0f, 0.15f);
                                    GL11.glRotatef(90, 1, 0, 0);
                                    wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.5f, cylinderSegment, textures[4]);
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

