import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;


//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private  boolean MouseOnepressed = true;
	private boolean  dragMode=false;
	private int moving = 0;
	private int assistantMoving = 0;
	private int status = 0;
	private int magicianHold = 0;
	private boolean audienceMoving = false;
	private boolean boxMoving = false;
	private final boolean Earth= false;
	/** position of pointer */
	float x = 0, y = 0;
	float assistantX = 0;
	float assistantY = 0;
	float boxConvex = 0;
	float process = 0;
	float audienceProcess = 0;
	float assistantProcess = 0;
	float positionX = -9;
	float positionY = 0;
	float positionZ	= 7;
	float perspectiveAdditionY = 0;
	float perspectiveAdditionZ = 0;
	/** angle of rotation */
	float rotation = 0;
	float rotationY = 0;
	float boxRotation1 = 0;
	float boxRotation2 = 0;
	float boxRotation3 = 0;
	float doorRotation = 0;
	boolean box1open = false;
	boolean box2open = false;
	boolean box3open = false;
	boolean firstPerson = false;
	boolean liftDown = false;
	boolean onHorse = false;
	boolean layerOne = true;
	float humanRotation = 10;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;

	private int magicianDirection = 0;
	private int assistantDirection = 0 ;
	
	long  myDelta =0 ; //to use for animation
	float Alpha =0 ; //to use for animation
	long StartTime; // beginAnimiation 

	Arcball MyArcball= new Arcball();
	
	boolean DRAWGRID =false;
	boolean waitForKeyrelease= true;
	boolean assistantExist = true;
	/** Mouse movement */
	int LastMouseX = -1 ;
	int LastMouseY= -1;
	
	 float pullX = 0.0f; // arc ball  X cord. 
	 float pullY = 0.0f; // arc ball  Y cord.

	ArrayList<float[]> collisionX;
	ArrayList<float[]> collisionZ;

	ArrayList<float[]> collisionX2;
	ArrayList<float[]> collisionZ2;

	 
	int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project 
	
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

	// static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

	//support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process 
	

	public void start() throws IOException {
		
		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200, 800));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initCollision();
		initGL(); // init OpenGL
		Mouse.setGrabbed(true);
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
//		MyArcball.startBall(600, 400, 1200, 800);
//		MyArcball.updateBall(600, 430, 1200, 800);
		 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			Display.update();
			Display.sync(120); // cap fps to 120fps
		}

		Display.destroy();
	}

	public void initCollision() {
		//z x x
		collisionX = new ArrayList<>(Arrays.asList(new float[][]{{33, -9, -12}, {36, -9, -12}, {45, -9, -12}, {48, -9, -12}, {57, -9, -12},
				{60, -9, -12}, {22, -4, -5}, {30, -4, -5}, {70, -4, -5}, {78, -4, -5}, {0, 0, -90}, {90, 0, -90},
				{35.5f, -29, -30.5f}, {36.5f, -29, -30.5f}, {41.5f, -29, -30.5f}, {42.5f, -29, -30.5f},
				{47.5f, -29, -30.5f}, {48.5f, -29, -30.5f}}));
		collisionZ = new ArrayList<>(Arrays.asList(new float[][]{{-9, 33, 36}, {-9, 45, 48}, {-9, 57, 60}, {-12, 33, 36}, {-12, 45, 48}, {-12, 57, 60}, {-5, 22, 30}, {-4, 22, 30}, {-5, 70, 78},
				{-4, 70, 78}, {0, 0, 90}, {-90, 0, 90}, {-29, 35.5f, 36.5f}, {-30.5f, 35.5f, 36.5f},
				{-29, 41.5f, 42.5f}, {-30.5f, 41.5f, 42.5f}, {-29, 47.5f, 42.5f}, {-30.5f, 48.5f, 42.5f}}));

		collisionX2 = new ArrayList<>(Arrays.asList(new float[][]{{45, -9, -12}, {48, -9, -12}, {0, 0, -90}, {90, 0, -90}}));
		collisionZ2 = new ArrayList<>(Arrays.asList(new float[][]{{-9, 45, 48}, {0, 0, 90}, {-90, 0, 90}}));
	}

	public void update(int delta) {
		// rotate quad
		//rotation += 0.01f * delta;

//		Mouse.setGrabbed(Mouse.isInsideWindow());

		int MouseX= Mouse.getX();
		  int MouseY= Mouse.getY();
		  int WheelPostion = Mouse.getDWheel();
	  
		  
		  boolean  MouseButonPressed= Mouse.isButtonDown(0);
//		MyArcball.startBall( 600, 400, 1200, 800);
//		MyArcball.updateBall( 605, 400, 1200, 800);
		  
		 
		  
//		  if(MouseButonPressed && !MouseOnepressed )
//		  {
//			  MouseOnepressed =true;
////			  System.out.println("Mouse drag mode");
////			  MyArcball.startBall( MouseX, MouseY, 1200, 800);
////			  MyArcball.startBall( 600, 400, 1200, 800);
////			  MyArcball.updateBall( 600, 405, 1200, 800);
//			  dragMode=true;
//
//
//		  }else if( !MouseButonPressed)
//		  {
////				 System.out.println("Mouse drag mode end ");
//			  MouseOnepressed =false;
//			  dragMode=false;
//		  }
//
//		  if(dragMode)
//		  {
//			  MyArcball.updateBall( MouseX  , MouseY  , 1200, 800);
//			  System.out.println(MouseX + " " + MouseY);
////			  MyArcball.updateBall( 310  , 400  , 1200, 800);
//		  }
		  
		  if(WheelPostion>0)
		  {
			  if (magicianHold != 2) {
			  	magicianHold += 1;
			  }
			  else {
			  	magicianHold = 0;
			  }
			 
		  }
		  
		  if(WheelPostion<0)
		  {
			  if (magicianHold != 0) {
				  magicianHold -= 1;
			  }
			  else {
				  magicianHold = 2;
			  }
			  
			//  System.out.println("Orth nubmer = " +  OrthoNumber);
			  
		  }
		  
//		  /** rest key is R*/
//		  if (Keyboard.isKeyDown(Keyboard.KEY_R))
//			  MyArcball.reset();
		  
		  /* bad animation can be turn on or off using A key)*/
		  

		int c = 50;

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			process += 0.005f * delta / c;
			magicianDirection = 0;
			if (!onHorse){
				moving = 1;
				x = 0.1f;
			}
			else {
				moving = 5;
				x = 0.15f;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			process += 0.005f * delta / c;
			magicianDirection = 3;
			if (!onHorse){
				moving = 1;
				x = - 0.1f;
			}
			else {
				moving = 5;
				x = - 0.15f;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			process += 0.005f * delta / c;
			magicianDirection = 1;
			if (!onHorse){
				moving = 1;
				y = - 0.1f;
			}
			else {
				moving = 5;
				y = - 0.15f;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			process += 0.005f * delta / c;
			magicianDirection = 2;
			if (!onHorse){
				moving = 1;
				y = 0.1f;
			}
			else {
				moving = 5;
				y = 0.15f;
			}
		}


		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			assistantX -= 0.005f * delta;
			assistantProcess += 0.005f * delta / c;
			assistantMoving = 1;
			assistantDirection = 2;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			assistantX += 0.005f * delta;
			assistantProcess += 0.005f * delta / c;
			assistantMoving = 1;
			assistantDirection = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			assistantY += 0.005f * delta;
			assistantProcess += 0.005f * delta / c;
			assistantMoving = 1;
			assistantDirection = 3;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			assistantY -= 0.005f * delta;
			assistantProcess += 0.005f * delta / c;
			assistantMoving = 1;
			assistantDirection = 1;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
			assistantMoving = 3;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_U) && boxConvex < 1) {
			boxConvex += 0.005f * delta;
			boxMoving = true;
			if (boxConvex > 1) {
				boxConvex = 1;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_I) && boxConvex > 0) {
			boxConvex -= 0.005f * delta;
			boxMoving = true;
			if (boxConvex < 0) {
				boxConvex = 0;
			}
		}

		if (!(Keyboard.isKeyDown(Keyboard.KEY_U) || Keyboard.isKeyDown(Keyboard.KEY_I))){
			boxMoving = false;
		}


		if (!(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D))){
			if (moving == 1 || moving == 5){
				process = 0;
				if (moving == 1) {
					moving = 0;
				}
			}
		}

		if (!(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_S))) {
			x = 0;
		}

		if (!(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D))) {
			y = 0;
		}

		if (!(Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_K) || Keyboard.isKeyDown(Keyboard.KEY_1)) && assistantMoving == 1){
			assistantMoving = 0;
			assistantProcess = 0;
		}

//		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
//			rotation += 0.03f * delta;
//		}
//		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
//			rotation -= 0.03f * delta;
//		}
//		rotation = (90 + (MouseX-400))/2f;
		rotation -= Mouse.getDX()*0.3f;
		rotationY += Mouse.getDY() * 0.3f;

		if (rotationY > 37) {
			rotationY = 37;
		}
		else if (rotationY < -37) {
			rotationY = -37;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			if (positionX-1 <= -8 && positionX >= -17){
				if (positionZ+1 >= 45 && positionZ-1 <= 48){
					box1open = !box1open;
				}
				else if (positionZ+1 >= 33 && positionZ-1 <= 36) {
					box2open = !box2open;
				}
				else if (positionZ+1 >= 57 && positionZ-1 <= 60) {
					box3open = !box3open;
				}
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			onHorse = !onHorse;
			if (onHorse) {
				moving = 5;
			}
			else {
				moving = 0;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
			firstPerson = !firstPerson;
		}

		if (firstPerson) {
			perspectiveAdditionY = 49;
			perspectiveAdditionZ = 230;
		}
		else {
			perspectiveAdditionY = 0;
			perspectiveAdditionZ = 0;
		}

		if (box1open && boxRotation1 < 150) {
			boxRotation1 += 0.06f * delta;
		}
		if (!box1open && boxRotation1 > 0) {
			boxRotation1 -= 0.06f * delta;
		}

		if (box2open && boxRotation2 < 150) {
			boxRotation2 += 0.06f * delta;
		}
		if (!box2open && boxRotation2 > 0) {
			boxRotation2 -= 0.06f * delta;
		}

		if (box3open && boxRotation3 < 150) {
			boxRotation3 += 0.06f * delta;
		}
		if (!box3open && boxRotation3 > 0) {
			boxRotation3 -= 0.06f * delta;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			doorRotation += 0.03f * delta;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_X) && doorRotation > 0) {
			doorRotation -= 0.03f * delta;
			if (doorRotation < 0) {
				doorRotation = 0;
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			if (positionZ - 1>= 45 && positionZ + 1 <= 48 && positionX - 1 >= -12 && positionX + 1 <= -9) {
				liftDown = !liftDown;
			}
		}

		if (liftDown) {
			if (positionY + 0.15 < 58f) {
				positionY += 0.15;
			}
			else {
				positionY = 58f;
				layerOne = false;
			}
		}
		else {
			if (positionY - 0.15 > 0) {
				positionY -= 0.15;
			}
			else {
				positionY = 0;
				layerOne = true;
			}
		}

		if (MouseButonPressed && process == 0) {
			switch (magicianHold){
				case 0:
					moving = 3;
					break;
				case 1:
					moving = 4;
					break;
				case 2:
					moving = 2;
					break;
			}
		}

		if (moving > 1 && moving != 5) {
			process += 0.005f * delta / c;
			if (process > 0.2) {
				moving = 0;
				process = 0;
				if (magicianHold == 2) {
					assistantExist = !assistantExist;
				}
				else if (magicianHold == 0) {
					audienceMoving = true;
				}
			}
		}

		if (audienceMoving) {
			if (audienceProcess < 0.3) {
				audienceProcess += 0.005f * delta / c;
			}
			else {
				audienceProcess = 0;
				audienceMoving = false;
			}
		}

		if (assistantMoving == 3) {
			if (assistantProcess < 0.2) {
				assistantProcess += 0.005f * delta / c;
			}
			else {
				assistantProcess = 0;
				assistantMoving = 0;
				audienceMoving = true;
				if (status < 3) {
					status += 1;
				}
			}
		}



//		 if(waitForKeyrelease) // check done to see if key is released
//		 {
//
//		 }

//		 /** to check if key is released */
//		 if(Keyboard.isKeyDown(Keyboard.KEY_G)==false)
//			{
//				waitForKeyrelease=true;
//			}else
//			{
//				waitForKeyrelease=false;
//
//			}
		 
		 
		 
			

		// keep quad on the screen
//		if (x < 0)
//			x = 0;
//		if (x > 1200)
//			x = 1200;
//		if (y < 0)
//			y = 0;
//		if (y > 800)
//			y = 800;

		updateFPS(); // update FPS Counter
		
		LastMouseX= MouseX;
		LastMouseY= MouseY ;
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		changeOrth();
		MyArcball.startBall(0, 0, 1200,800); 
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		FloatBuffer lightPos = BufferUtils.createFloatBuffer(4);
		lightPos.put(10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
		lightPos2.put(0).put(-1000f).put(0).put(0).flip();

		FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
		lightPos3.put(-10000f).put(1000f).put(1000).put(0).flip();

		FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
		lightPos4.put(1000f).put(1000f).put(1000f).put(0).flip();

//		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos); // specify the
																	// position
																	// of the
																	// light
		 GL11.glEnable(GL11.GL_LIGHT0); // switch light #0 on // I've setup specific materials so in real light it will look abit strange

		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos);
																	// specify the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(spot));

		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4); // specify
																	// the
																	// position
																	// of the
																	// light
		GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
		 GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));

		GL11.glEnable(GL11.GL_LIGHT5); // switch light #0 on
		GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos2);

		GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
											// on
	 	GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
	 	GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		
		   GL11.glEnable(GL11.GL_BLEND);
       GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
          try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //load in texture
          

	}

	 

	public void changeOrth() {
		 GL11.glMatrixMode(GL11.GL_PROJECTION);
		 GL11.glLoadIdentity();
		GLU.gluPerspective(90, 1, 1, 10000);
		GLU.gluLookAt(600, 130-rotationY-perspectiveAdditionY, 200-perspectiveAdditionZ, 600, 130-perspectiveAdditionY, 0-perspectiveAdditionZ, 0, 1, rotationY);
//		  GL11.glOrtho(1200 -  OrthoNumber , OrthoNumber, (800 - (OrthoNumber  * 0.66f)) , (OrthoNumber * 0.66f), 100000, -100000);
		 	GL11.glMatrixMode(GL11.GL_MODELVIEW); 
		 	
		 	FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
		 	GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);

		 //	if(MouseOnepressed)
		 //	{

		 	MyArcball.getMatrix(CurrentMatrix);
		 //	}

		    GL11.glLoadMatrix(CurrentMatrix);
		 	
	}

	/*
	 * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load 
	 * 
	 */
	public void renderGL() throws IOException {
		changeOrth();
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glColor3f(0.5f, 0.5f, 1.0f); 
		 
		 myDelta =   getTime() - StartTime; 
		  float delta =((float) myDelta)/10000;

		  // code to aid in animation 
		 float theta = (float) (delta * 2 * Math.PI);
		 float thetaDeg = delta * 360; 
		  float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
		  float posn_y  = (float) Math.sin(theta);

//		FloatBuffer lightPosfb = BufferUtils.createFloatBuffer(4);
//		lightPosfb.put(posn_x*10).put(10).put(posn_y*10).put(0).flip();
//		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosfb);

		  /*
		   * This code draws a grid to help you view the human models movement 
		   *  You may change this code to move the grid around and change its starting angle as you please 
		   */
//		if(DRAWGRID)
//		{
//		GL11.glPushMatrix();
//		Grid MyGrid = new Grid();
//		GL11.glTranslatef(600, 400, 0);
//		GL11.glScalef(200f, 200f,  200f);
//		 MyGrid.DrawGrid();
//		GL11.glPopMatrix();
//		}

//		Color.white.bind();
//		GL11.glPushMatrix();{
//			TexCube cube = new TexCube();
//			GL11.glTranslatef(600, 400, 2000 - y*20);
////			GL11.glRotatef(60, 0, 1, 0);
//			GL11.glScalef(1000, 1000, 1000);
//			cube.DrawTexCube(1.0f, cubeTexture);
//		} GL11.glPopMatrix();
//
//		Color.white.bind();
//		GL11.glPushMatrix();{
//			TexCube cube = new TexCube();
//			GL11.glTranslatef(-1400, 400, 0 - y*20);
////			GL11.glRotatef(60, 0, 1, 0);
//			GL11.glScalef(1000, 1000, 1000);
//			cube.DrawTexCube(1.0f, cubeTexture);
//		} GL11.glPopMatrix();
//
//		Color.white.bind();
//		GL11.glPushMatrix();{
//			TexFace ground = new TexFace();
////			GL11.glRotatef(-15, 1, 0, 0);
//			GL11.glTranslatef(600, 145, 0 - y*20);
//			GL11.glScalef(1000, 1000, 1000);
//			ground.drawTexFace(groundTexture);
//		} GL11.glPopMatrix();

//		Color.white.bind();
//		GL11.glPushMatrix();{
//			TexCube cube = new TexCube();
//			GL11.glTranslatef(600, 2400, 0 - y*20);
////			GL11.glRotatef(60, 0, 1, 0);
//			GL11.glScalef(1000, 1000, 1000);
//			cube.DrawTexCube(1.0f, cubeTexture);
//		} GL11.glPopMatrix();

//		Color.white.bind();
//		GL11.glPushMatrix();{
//			TexCube cube = new TexCube();
//			GL11.glTranslatef(600, -660, 0 - y*20);
//			GL11.glRotatef(60, 0, 1, 0);
//			GL11.glScalef(1000, 1000, 1000);
//			cube.DrawTexCube(1.0f, cubeTexture);
//		} GL11.glPopMatrix();

		FloatBuffer lightPosMoving = BufferUtils.createFloatBuffer(4);
		lightPosMoving.put(posn_x).put((float) Math.sin(myDelta/1000f)).put(posn_y).put(0).flip();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosMoving);
		
		GL11.glPushMatrix();
		Human MyHuman = new Human();
		Assistant assistant = new Assistant();
		Fan fan = new Fan();
//		GL11.glTranslatef(x*10, 0.0f, y*10);
		GL11.glTranslatef(600, 0,0 );
//		GL11.glRotatef(60, 0, 1, 0);
		GL11.glScalef(30f, 30f, 30f);

		// Draw the cube with textures in the center
//		Color.white.bind();
//		GL11.glPushMatrix();{
//			GL11.glTranslatef(0, -0.7f, 0);
//			GL11.glRotatef(60, 0, 1, 0);
//			cube.DrawTexCube(1.0f, cubeTexture);
//		} GL11.glPopMatrix();
		 
//		if(!BadAnimation)
//		{
			// Rotate the human to let it face to right direction
//			GL11.glRotatef(180 - thetaDeg, 0, 1, 0);
//
//		}else
//		{
//
//			//bad animation  version
//			 GL11.glTranslatef(posn_x*3.0f, 0.0f, posn_y*3.0f);
//		}

		Octahedron octahedron = new Octahedron();
		Shadow shadow = new Shadow();

		GL11.glPushMatrix();{
//			GL11.glTranslatef(-10, -3, 20);
			GL11.glPushMatrix();{
				switch (magicianDirection) {
					case 0:
						GL11.glRotatef(0, 0, 1, 0);
						break;
					case 1:
						GL11.glRotatef(-90, 0, 1, 0);
						break;
					case 2:
						GL11.glRotatef(+90, 0, 1, 0);
						break;
					case 3:
						GL11.glRotatef(+180, 0, 1, 0);
						break;
				}
					MyHuman.DrawHuman(process, moving, humanTextures, magicianHold);
			} GL11.glPopMatrix();

			GL11.glPushMatrix();{
				GL11.glTranslatef(0, 5f, 0);
				GL11.glRotatef(myDelta/10f, 0, 1, 0);
				GL11.glScalef(0.3f, 0.6f, 0.3f);
				switch (status){
					case 0:
						Color.green.bind();
						break;
					case 1:
						Color.yellow.bind();
						break;
					case 2:
						Color.gray.bind();
						break;
					case 3:
						Color.red.bind();
						break;
				}
				octahedron.DrawOctahedron();
			} GL11.glPopMatrix();

			GL11.glPushMatrix();{
				GL11.glTranslatef(0, -1.79f, 0);
				shadow.drawShadow(1.4f);
			} GL11.glPopMatrix();

			if (onHorse) {
				GL11.glPushMatrix();{
					GL11.glTranslatef(0, -1.79f, 0);
					if (magicianDirection == 0 || magicianDirection == 3){
						GL11.glScalef(0.7f, 1, 1);
					}
					else {
						GL11.glScalef(1f, 1, 0.7f);
					}
					shadow.drawShadow(3f);
				} GL11.glPopMatrix();

			}

			Color.white.bind();
			GL11.glPushMatrix();
			{
//					GL11.glTranslatef(0, 0, -y);

//				GL11.glTranslatef(0, -1.8f, 0);
//				GL11.glRotatef(-rotationY, 1, 0, 0);
//				GL11.glTranslatef(0, 1.8f, 0);
				GL11.glRotatef(-rotation, 0, 1, 0);

				if (moving == 1 || moving == 5) {
					positionX += x*Math.cos(((rotation-90)/180)*(Math.PI)) + y*Math.cos(((rotation)/180)*(Math.PI));
					positionZ -= x*Math.sin(((rotation-90)/180)*(Math.PI)) + y*Math.sin(((rotation)/180)*(Math.PI));
					if (layerOne){
						for (float[] array : collisionX) {
							if (positionX + 1 >= array[2] && positionX - 1 <= array[1]) {
								if (positionZ + 1f > array[0] && positionZ < array[0]) {
									positionZ = array[0] - 1f;
								} else if (positionZ - 1f < array[0] && positionZ > array[0]) {
									positionZ = array[0] + 1f;
								}
							}
						}
						for (int i = 0; i < collisionZ.size(); i++) {
							if ((box1open && i == 4) || (box2open && i == 3) || (box3open && i == 5)) {
								continue;
							}
							if (positionZ + 1 >= collisionZ.get(i)[1] && positionZ - 1 <= collisionZ.get(i)[2]) {
								if (positionX + 1f > collisionZ.get(i)[0] && positionX < collisionZ.get(i)[0]) {
									positionX = collisionZ.get(i)[0] - 1f;
								} else if (positionX - 1f < collisionZ.get(i)[0] && positionX > collisionZ.get(i)[0]) {
									positionX = collisionZ.get(i)[0] + 1f;
								}
							}
						}
					}
					else {
						for (float[] array : collisionX2) {
							if (positionX + 1 >= array[2] && positionX - 1 <= array[1]) {
								if (positionZ + 1f > array[0] && positionZ < array[0]) {
									positionZ = array[0] - 1f;
								} else if (positionZ - 1f < array[0] && positionZ > array[0]) {
									positionZ = array[0] + 1f;
								}
							}
						}
						for (float[] array : collisionZ2) {
							if (positionZ + 1 >= array[1] && positionZ - 1 <= array[2]) {
								if (positionX + 1f > array[0] && positionX < array[0]) {
									positionX = array[0] - 1f;
								} else if (positionX - 1f < array[0] && positionX > array[0]) {
									positionX = array[0] + 1f;
								}
							}
						}
					}
				}
				GL11.glTranslatef(positionX, positionY, positionZ);
//				System.out.println(positionX + " " + positionZ);


				//The world except the human
				GL11.glPushMatrix();
				{

					GL11.glPushMatrix();{
						GL11.glTranslatef(posn_x*10+30, 20, posn_y*10 - 20);
						Sphere sphere = new Sphere();
						Color.yellow.bind();
						sphere.DrawSphere(3, 16, 16);
						Color.white.bind();
					}GL11.glPopMatrix();


					if (assistantExist){
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(2.5f + assistantX, 0, assistantY - 70f);
							GL11.glPushMatrix();{
							GL11.glTranslatef(0, 5f, 0);
							GL11.glRotatef(myDelta/10f, 0, 1, 0);
							GL11.glScalef(0.3f, 0.6f, 0.3f);
							switch (status){
								case 0:
									Color.green.bind();
									break;
								case 1:
									Color.yellow.bind();
									break;
								case 2:
									Color.gray.bind();
									break;
								case 3:
									Color.red.bind();
									break;
							}
							octahedron.DrawOctahedron();
						} GL11.glPopMatrix();
							switch (assistantDirection) {
								case 0:
									GL11.glRotatef(-90, 0, 1, 0);
									break;
								case 1:
									GL11.glRotatef(0, 0, 1, 0);
									break;
								case 2:
									GL11.glRotatef(90, 0, 1, 0);
									break;
								case 3:
									GL11.glRotatef(180, 0, 1, 0);
									break;
							}
							assistant.DrawHuman(assistantProcess, assistantMoving, humanTextures);

							GL11.glPushMatrix();{
								GL11.glTranslatef(0, -1.79f, 0);
								shadow.drawShadow(1.4f);
							}GL11.glPopMatrix();
						}

						GL11.glPopMatrix();
					}

//					the ground
					TexFace ground = new TexFace();
					GL11.glTranslatef(0, -1.8f, 0);
					GL11.glRotatef(180, 1, 0, 0);
					GL11.glScalef(60, 60, 60);
					GL11.glPushMatrix();{
						GL11.glTranslatef(0, -1.5f, 0);
						ground.drawTexFace(ceilingTexture, 1.5f);
					}GL11.glPopMatrix();
					ground.drawTexFace(groundTexture, 1.5f);
					GL11.glPushMatrix();{
						GL11.glTranslatef(0.15f, -0.0002f, 0.6f);
						GL11.glRotatef(180, 1, 0, 0);
						GL11.glScalef(3f/60, 3f/60, 3f/60);

						MagicBox magicBox = new MagicBox();
						magicBox.drawBox(texture, !(boxConvex ==0), boxRotation2, boxConvex);
					}GL11.glPopMatrix();

					GL11.glPushMatrix();{
						Audience audience = new Audience();
						GL11.glTranslatef(0.5f, -0.015f, 0.8f);
						GL11.glRotatef(180, 1, 0, 0);
						GL11.glRotatef(90, 0, 1, 0);
						GL11.glScalef(1f/60, 1f/60, 1f/60);
						audience.DrawAudience(audienceProcess, audienceMoving, audienceTextures);
					} GL11.glPopMatrix();

					GL11.glPushMatrix();{

					GL11.glTranslatef(0.5f, -0.0001f, 0.8f);

					GL11.glScalef(1f/60, 1f/60, 1f/60);

					shadow.drawShadow(1.4f);

				} GL11.glPopMatrix();

					GL11.glPushMatrix();{
					Audience audience = new Audience();
					GL11.glTranslatef(0.5f, -0.015f, 0.7f);
					GL11.glRotatef(180, 1, 0, 0);
					GL11.glRotatef(90, 0, 1, 0);
					GL11.glScalef(1f/60, 1f/60, 1f/60);
					audience.DrawAudience(delta, audienceMoving, audienceTextures);
				} GL11.glPopMatrix();

					GL11.glPushMatrix();{

					GL11.glTranslatef(0.5f, -0.0001f, 0.7f);

					GL11.glScalef(1f/60, 1f/60, 1f/60);

					shadow.drawShadow(1.4f);

				} GL11.glPopMatrix();

					GL11.glPushMatrix();{
					Audience audience = new Audience();
					GL11.glTranslatef(0.5f, -0.015f, 0.6f);
					GL11.glRotatef(180, 1, 0, 0);
					GL11.glRotatef(90, 0, 1, 0);
					GL11.glScalef(1f/60, 1f/60, 1f/60);
					audience.DrawAudience(delta, audienceMoving, audienceTextures);
				} GL11.glPopMatrix();

					GL11.glPushMatrix();{

					GL11.glTranslatef(0.5f, -0.0001f, 0.6f);

					GL11.glScalef(1f/60, 1f/60, 1f/60);

					shadow.drawShadow(1.4f);

				} GL11.glPopMatrix();


					GL11.glPushMatrix();{
						GL11.glTranslatef(0.15f + 0.025f, -0.0001f, 0.6f - 0.025f);
						GL11.glRotatef(180, 1, 0, 0);
						GL11.glScalef(1f/60, 1f/60, 1f/60);
						shadow.drawShadow(3f);
					}GL11.glPopMatrix();

					GL11.glPushMatrix();{
						GL11.glTranslatef(0.15f, -0.0002f, 0.8f);
						GL11.glRotatef(180, 1, 0, 0);
						GL11.glScalef(3f/60, 3f/60, 3f/60);
						MagicBox magicBox = new MagicBox();
						magicBox.drawBox(boxTextures[0], false, boxRotation1, 0);
					}GL11.glPopMatrix();

					GL11.glPushMatrix();{
						GL11.glTranslatef(0.15f, -0.0002f, 0.8f);
						GL11.glRotatef(180, 0, 0, 1);
						GL11.glRotatef(180, 1, 0, 0);
						GL11.glScalef(3f/60, 1f/60, 3f/60);
						GL11.glTranslatef(-1, 0, 0);
						LiftPipe liftPipe = new LiftPipe();
						liftPipe.drawLiftPipe(boxTextures[0], 50, 58.1f);
					}GL11.glPopMatrix();

					GL11.glPushMatrix();{
						GL11.glTranslatef(0.15f + 0.025f, -0.0001f, 0.8f - 0.025f);
						GL11.glRotatef(180, 1, 0, 0);
						GL11.glScalef(1f/60, 1f/60, 1f/60);
						shadow.drawShadow(3f);
					}GL11.glPopMatrix();

					GL11.glPushMatrix();{
					GL11.glTranslatef(0.15f, -0.0002f, 1.0f);
					GL11.glRotatef(180, 1, 0, 0);
					GL11.glScalef(3f/60, 3f/60, 3f/60);
					MagicBox magicBox = new MagicBox();
					magicBox.drawBox(boxTextures[1], false, boxRotation3, 0);
				}GL11.glPopMatrix();

					GL11.glPushMatrix();{
					GL11.glTranslatef(0.15f + 0.025f, -0.0001f, 1.0f - 0.025f);
					GL11.glRotatef(180, 1, 0, 0);
					GL11.glScalef(1f/60, 1f/60, 1f/60);
					shadow.drawShadow(3f);
				}GL11.glPopMatrix();

					for (int i = 0; i < 5; i++){
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.08f, 0f, 0.5f - 0.03f*i);
							GL11.glRotatef(180, 1, 0, 0);
							GL11.glRotatef(90, 0, 1, 0);
							GL11.glScalef(1f / 280, 1f / 280, 1f / 280);
							Flower flower = new Flower();
							flower.drawFlower(flowerTextures, myDelta);
						}
						GL11.glPopMatrix();
						GL11.glPushMatrix();{
							GL11.glTranslatef(0.08f, -0.0001f, 0.5f - 0.03f * i);
							GL11.glScalef(1f / 100, 1f / 100, 1f / 100);
							shadow.drawShadow(1.2f);
						}GL11.glPopMatrix();
					}

					for (int i = 0; i < 5; i++){
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.08f, 0f, 1.3f - 0.03f*i);
							GL11.glRotatef(180, 1, 0, 0);
							GL11.glRotatef(90, 0, 1, 0);
							GL11.glScalef(1f / 280, 1f / 280, 1f / 280);
							Flower flower = new Flower();
							flower.drawFlower(flowerTextures, myDelta);
						}
						GL11.glPopMatrix();
						GL11.glPushMatrix();{
							GL11.glTranslatef(0.08f, -0.0001f, 0.5f - 0.03f * i);
							GL11.glScalef(1f / 100, 1f / 100, 1f / 100);
							shadow.drawShadow(1.2f);
						}GL11.glPopMatrix();
					}

				}
				GL11.glPopMatrix();

				GL11.glPushMatrix(); {
					TexFace ground = new TexFace();
					GL11.glTranslatef(0, -60f, 0);
					GL11.glRotatef(180, 1, 0, 0);
					GL11.glScalef(60, 60, 60);
					ground.drawTexFace(groundTexture, 1.5f);
				} GL11.glPopMatrix();

				GL11.glPushMatrix();
				{
					TexFace wall = new TexFace();
					GL11.glTranslatef(0, -91.8f, 0f);
					GL11.glRotatef(- 90, 1, 0, 0);
					GL11.glScalef(90, 90, 90);
					wall.drawTexFace(wallTexture, 1);
					GL11.glPushMatrix();{
					GL11.glTranslatef(0, 1, 0);
					wall.drawTexFace(wallTexture, 1);
				}GL11.glPopMatrix();
				}
				GL11.glPopMatrix();

				GL11.glPushMatrix();
				{
					TexFace wall = new TexFace();
					GL11.glTranslatef(0f, -91.8f, -90);
					GL11.glRotatef(90, 0, 0, 1);
					GL11.glScalef(90, 90, 90);
//					GL11.glScalef(200, 200, 200);
					wall.drawTexFace(wallTexture2, 1);
					GL11.glPushMatrix();
					{
						GL11.glTranslatef(0, -1, 0);
						wall.drawTexFace(wallTexture2, 1);
					}
					GL11.glPopMatrix();
				} GL11.glPopMatrix();

//				the wall
				GL11.glPushMatrix();
				{
					TexFace wall = new TexFace();
					GL11.glTranslatef(0, -1.8f, 0f);
					GL11.glRotatef(- 90, 1, 0, 0);
					GL11.glScalef(90, 90, 90);
					wall.drawTexFace(wallTexture, 1);
					GL11.glPushMatrix();{
						GL11.glTranslatef(0, 1, 0);
						wall.drawTexFace(wallTexture, 1);
					}GL11.glPopMatrix();
				}
				GL11.glPopMatrix();

//				the wall
				GL11.glPushMatrix();
				{
					TexFace wall = new TexFace();
					GL11.glTranslatef(0f, -1.8f, -90);
					GL11.glRotatef(90, 0, 0, 1);
					GL11.glScalef(90, 90, 90);
//					GL11.glScalef(200, 200, 200);
					wall.drawTexFace(wallTexture2, 1);
					GL11.glPushMatrix();{
						GL11.glTranslatef(0, -1, 0);
						wall.drawTexFace(wallTexture2, 1);
					}GL11.glPopMatrix();
					GL11.glPushMatrix();{
						Door door = new Door();
						GL11.glTranslatef(0f, 0f, 0.85f);
						GL11.glRotatef(-0.1f, 0, 0, 1);
						GL11.glScalef(1/30f, 1/30f, 1/30f);
						door.drawDoor(doorTextures, doorRotation);
					}GL11.glPopMatrix();

					for (int i = 0; i < 5; i++){
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.2f, 0, 0.8f - 0.1f * i);
							GL11.glRotatef(-90f, 0, 0, 1);
							GL11.glRotatef(90f, 0, 1, 0);
							GL11.glScalef(1 / 100f, 1 / 100f, 1 / 100f);
							HangingLight hangingLight = new HangingLight();
							hangingLight.drawLight(lightTextures);
						}
						GL11.glPopMatrix();
					}

					GL11.glPushMatrix();
					for (int i = 0; i < 3; i++){
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.25f, 0, 0.75f - 0.1f * i);
							GL11.glRotatef(-90f, 0, 0, 1);
							GL11.glRotatef(90f, 0, 1, 0);
							GL11.glScalef(1 / 180f, 1 / 180f, 1 / 180f);
							fan.drawFan(lightTextures, myDelta / 1000f);
						}
					}
					GL11.glPopMatrix();

				}
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();

		GL11.glPopMatrix();
		
	}
		  
	public static void main(String[] argv) throws IOException {
		MainWindow hello = new MainWindow();
		hello.start();
	}
	 
	Texture texture;
	Texture wallTexture;
	Texture wallTexture2;
	Texture groundTexture;
	Texture ceilingTexture;
	Texture horseTexture;
	Texture[] humanTextures = new Texture[15];
	Texture[] audienceTextures = new Texture[5];
	Texture[] flowerTextures = new Texture[2];
	Texture[] lightTextures = new Texture[2];
	Texture[] doorTextures = new Texture[2];
	Texture[] boxTextures = new Texture[2];
	 
	/*
	 * Any additional textures for your assignment should be written in here. 
	 * Make a new texture variable for each one so they can be loaded in at the beginning 
	 */
	public void init() throws IOException {

		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2021.png"));
	         
	  wallTexture = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/wall.jpeg"));
	  wallTexture2 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/wall2.jpeg"));
	  groundTexture = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/ground.jpeg"));
	  ceilingTexture = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/ceiling.jpeg"));
	  horseTexture = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/horse_0.jpeg"));
	  for ( int i = 0; i < 14; i++ ){
	  	humanTextures[i] = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/human_" + i +".jpeg"));
	  }
		for ( int i = 0; i < 5; i++ ){
			audienceTextures[i] = TextureLoader.getTexture("BMP", ResourceLoader.getResourceAsStream("res/audience_" + i +".bmp"));
		}
		for ( int i = 0; i < 2; i++ ){
			flowerTextures[i] = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/flower_" + i +".jpeg"));
		}
		for ( int i = 0; i < 2; i++ ){
			lightTextures[i] = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/light_" + i +".jpeg"));
		}
		for ( int i = 0; i < 2; i++ ){
			doorTextures[i] = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/door_" + i +".jpeg"));
		}
		for ( int i = 0; i < 2; i++ ){
			boxTextures[i] = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/box_" + i +".jpeg"));
		}
		humanTextures[14] = horseTexture;
	  System.out.println("Texture loaded okay ");
	}
}
