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

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import static GraphicsObjects.Utils.ConvertForGL;


public class Human {

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
	
	
	public Human() {

	}
	
	// This method draws the human and let the human move
	// The human is walking around the cube in the center
	// The human walks in the same way as humans in the real world, which means that
	// when the arms is going in front of the human, the forearm rotates and when
	// it is in back of the human, the whole arm is straight.
	// Furthermore, when the human is walking, the legs of human move just like the legs of humans in the real world
	// When the high leg is moving forward and is in front of the body,
	// the low leg moves in the opposite direction to the high leg and when the
	// high leg is lifted to a certain angle, the low leg moves forward until it is in the same line with the high leg and
	// all the process is completed during the period when the arms move to their highest places
	// Besides that, when the high leg is in back of the body, the low leg is always in the same line with the high leg
	// Some detailed information is under this comment
	public void DrawHuman(float delta, int mode, Texture[] textures, int hold) {
		 float theta = (float) (5 * delta * Math.PI);
		  float LimbRotation = 0;
		  float RightLimbRotation = 0;
		  float LeftForearmRotation = 0;
		  float RightForearmRotation = 0;
		  float LeftHighLegRotation = 0;
		  float RightHighLegRotation = 0;
		  float LeftLowLegRotation = 0;
		  float RightLowLegRotation = 0;
		  float SwordRotation = 0;

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
					SwordRotation = - RightForearmRotation / 2;
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
			case 2:
				RightLimbRotation = (float) Math.sin(theta) * 80;
				if (RightLimbRotation < 60){
					RightForearmRotation = 1.5f * RightLimbRotation;
				}
				else {
					RightForearmRotation = 90 * ((80 - RightLimbRotation) / 20);
				}
				break;
			case 3:
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
			break;
			case 4:
				float f1 = (float) Math.sin(theta) * 90;
				if (f1 <= 45) {
					RightLimbRotation = -f1;
					RightForearmRotation = -2 * RightLimbRotation;
				} else {
					RightLimbRotation = -45 + 135 * ((f1 - 45)/45);
					RightForearmRotation = 90 * ((90 - f1) / 45);

				}
				SwordRotation = - RightForearmRotation - RightLimbRotation;

		}

		Sphere sphere= new Sphere();
		 TexSphere texSphere = new TexSphere();
		Cylinder cylinder= new Cylinder();

		StripTexCylinder texCylinder = new StripTexCylinder();
		WholeTexCylinder wholeTexCylinder = new WholeTexCylinder();
		Sword sword = new Sword();

		Horse horse = new Horse();

		Color.white.bind();

		if (mode == 5){
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(0.0f, 1.2f, 0.0f);
				horse.drawHorse(textures[14], (float) (Math.sin(theta) * 10), - (float) (Math.sin(theta) * 10), (float) (Math.sin(theta) * 10), - (float) (Math.sin(theta) * 10), true);

			}
			GL11.glPopMatrix();
		}

		GL11.glPushMatrix();
		 
		 {
		 	if (mode == 5) {
		 		GL11.glTranslatef(0.0f, 2.48f, 0.0f);
			}
			  GL11.glTranslatef(0.0f,0.5f,0.0f);
			 texSphere.DrawTexSphere(0.5f, 16, 16, textures[8]);

			 // abdomen
			 GL11.glColor3f(white[0], white[1], white[2]);
			 GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(white));
			 GL11.glPushMatrix();{
			 	GL11.glTranslatef(0.0f, 0.25f, 0.0f);
			 	GL11.glRotatef(- 90, 1, 0, 0);
			 	texCylinder.DrawStripTexCylinder(0.3f, 0.5f, 16, textures[4]);

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
					 wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, 16, textures[10]);


					 // head
					 GL11.glColor3f(white[0], white[1], white[2]);
					 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(red));
					 Color.white.bind();
					 GL11.glPushMatrix();
					 {
						 GL11.glTranslatef(0.0f, 0.0f, 1.0f);
						 texSphere.DrawTexSphere(0.5f, 16, 16, textures[0]);
						 // hat
						 GL11.glPushMatrix(); {
							 GL11.glTranslatef(0.0f, 0.0f, 0.3f);
							 texCylinder.DrawStripTexCylinder(1.0f, 0.2f, 16, textures[13]);
							 GL11.glPushMatrix(); {
							 	GL11.glTranslatef(0.0f, 0.0f, 0.2f);
							 	wholeTexCylinder.DrawWholeTexCylinder(0.45f, 0.87f, 16, textures[12]);
							 	GL11.glPushMatrix();{
								 	GL11.glTranslatef(0.0f, 0.0f, 2f);
								 	GL11.glScalef(0.25f, 0.25f, 0.5f);

							 }GL11.glPopMatrix();
						 	} GL11.glPopMatrix();
						 } GL11.glPopMatrix();
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
							 case 5:
								 GL11.glRotatef(30, 1.0f, 0.0f, 0.0f);
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
							 wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, 16, textures[5]);


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
									 case 5:
										 GL11.glRotatef(30, 0f, -1.0f, 0f);
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
									 wholeTexCylinder.DrawWholeTexCylinder(0.1f, 0.7f, 16, textures[7]);

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
							 case 5:
								 GL11.glRotatef(30, 1.0f, 0.0f, 0.0f);

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
							 wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.7f, 16, textures[5]);

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
									 case 5:
										 GL11.glRotatef(30, 0f, 1.0f, 0f);
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
									 wholeTexCylinder.DrawWholeTexCylinder(0.1f, 0.7f, 16, textures[7]);

									 // right hand
									 GL11.glColor3f(blue[0], blue[1], blue[2]);
									 GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, ConvertForGL(blue));
									 Color.white.bind();
									 GL11.glPushMatrix();
									 {
										 GL11.glTranslatef(0.0f, 0.0f, 0.75f);
										 texSphere.DrawTexSphere(0.2f, 16, 16, textures[9]);
										 switch (hold){
											 case 1:
												GL11.glPushMatrix();
												 {
													 GL11.glRotatef(90 + SwordRotation, 1, 0, 0);
													 sword.drawSword(textures[3]);
												 }
												 GL11.glPopMatrix();
										 		break;
											 case 2:
												 GL11.glPushMatrix();{
													 GL11.glRotatef(90, 1, 0, 0);
													 wholeTexCylinder.DrawWholeTexCylinder(0.05f, 1f, 16, textures[3]);
											 	}GL11.glPopMatrix();
											 break;
										 }

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
				 		if (mode != 5){
							 GL11.glRotatef(90.0f, 1, 0, 0);
							 GL11.glRotatef(RightHighLegRotation, 1.0f, 0.0f, 0.0f);
						 }
				 		else {
							GL11.glRotatef(30, 0, 1, 0);
							GL11.glRotatef(180, 1, 0, 0);
						}
		               
		                texSphere.DrawTexSphere(0.25f, 16, 16, textures[6]);


		                // right high leg
		           	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		               GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
		               Color.white.bind();
		                GL11.glPushMatrix(); {
		                    GL11.glTranslatef(0.0f,0.0f,0.0f);
		                    GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
		                
		                    

		                            //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f); 
		                    texCylinder.DrawStripTexCylinder(0.15f,0.7f,16, textures[10]);


		                    // right knee
		               	 GL11.glColor3f(blue[0], blue[1], blue[2]);
		                   GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
		                   Color.white.bind();
		                   GL11.glPushMatrix(); {
		                        GL11.glTranslatef(0.0f,0.0f,0.75f);
								 if (mode != 5){
									 GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
									 GL11.glRotatef(RightLowLegRotation, 1, 0, 0);
								 }
								 else {
								 	GL11.glRotatef(- 90, 1, 0, 0);
								 }
		                        texSphere.DrawTexSphere(0.25f, 16, 16, textures[9]);

		                        //right low leg
		                   	 GL11.glColor3f(orange[0], orange[1], orange[2]);
		                       GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
		                       Color.white.bind();
		                        GL11.glPushMatrix(); {
		                            GL11.glTranslatef(0.0f,0.0f,0.0f);
		                           // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
		                          //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);

		                            texCylinder.DrawStripTexCylinder(0.15f,0.7f,16, textures[10]);

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
										 	wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.5f, 16, textures[11]);
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
				 if (mode != 5){
					 GL11.glRotatef(90.0f, 1, 0, 0);
					 GL11.glRotatef(LeftHighLegRotation, 1.0f, 0.0f, 0.0f);
				 }
				 else {
				 	GL11.glRotatef(-30, 0, 1, 0);
				 	GL11.glRotatef(180, 1, 0, 0);
				 }

				 texSphere.DrawTexSphere(0.25f, 16, 16, textures[6]);


				 // left high leg
				 GL11.glColor3f(orange[0], orange[1], orange[2]);
				 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
				 Color.white.bind();
				 GL11.glPushMatrix(); {
					 GL11.glTranslatef(0.0f,0.0f,0.0f);
					 GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);



					 //   GL11.glRotatef(90.0f,1.0f,0.0f,0.0f);
					 texCylinder.DrawStripTexCylinder(0.15f,0.7f,16, textures[10]);


					 // left knee
					 GL11.glColor3f(blue[0], blue[1], blue[2]);
					 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(blue));
					 Color.white.bind();
					 GL11.glPushMatrix(); {
						 GL11.glTranslatef(0.0f,0.0f,0.75f);
						 if (mode != 5){
							 GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
							 GL11.glRotatef(LeftLowLegRotation, 1, 0, 0);
						 }
						 else {
							 GL11.glRotatef(- 90, 1, 0, 0);
						 }
						 texSphere.DrawTexSphere(0.25f, 16, 16, textures[9]);

						 //left low leg
						 GL11.glColor3f(orange[0], orange[1], orange[2]);
						 GL11.glMaterial( GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  ConvertForGL(orange));
						 Color.white.bind();
						 GL11.glPushMatrix(); {
							 GL11.glTranslatef(0.0f,0.0f,0.0f);

							 // GL11.glRotatef(120.0f,1.0f,0.0f,0.0f);
							 //  GL11.glRotatef(0.0f,0.0f,0.0f,0.0f);
							 texCylinder.DrawStripTexCylinder(0.15f,0.7f,16, textures[10]);

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
									 wholeTexCylinder.DrawWholeTexCylinder(0.15f, 0.5f, 16, textures[11]);
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
 
	/*
	 
	 
}

	*/
	 