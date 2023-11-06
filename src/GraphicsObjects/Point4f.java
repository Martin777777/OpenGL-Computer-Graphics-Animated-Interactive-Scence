package GraphicsObjects;

/**
 *
 * This class represents a point
 *
 * The Variables in this class are x, y, z and a which represent the x coordinate, y coordinate and z coordinate of this point
 *
 * The Methods involved in this class are getPosition, toString, PlusVector, MinusVector, MinusPoint
 *
 * @author Ma Zixiao
 *
 *
 */

public class Point4f {

	public float x;
	public float y;
	public float z;
	public float a;
	
	
	// default constructor
	public Point4f() { 
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	
	//initializing constructor
	public Point4f(float x, float y, float z,float a) { 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	// sometimes for different algorithms we will need to address the point using positions 0 1 2 
	public float getPostion(int postion)
	{
		switch(postion)
		{
		case 0: return x;
		case 1: return y;
		case 2: return z; 
		case 3: return a; 
		default: return Float.NaN;  
		} 
	}
	
	public String toString()
	{
		return ("(" + x +"," + y +"," + z + "," + a +")");
    }

	//What this method aims to do is to return the result point after this point plus a vector
	//To do that, what this method do is to return a new point whose x, y, z are the sum of this point's x and the vector's x
	//this point's y and the vector's y, this point's z and the vector's z
	public Point4f PlusVector(Vector4f Additonal) {
		return new Point4f(this.x+Additonal.x, this.y+Additonal.y, this.z+Additonal.z, this.a+Additonal.a);
	}

	//What this method aims to do is to return the result point after this point minus a vector
	//To do that, what this method do is to return a new point whose x, y, z are the result of this point's x minus the vector's x
	//this point's y minus the vector's y, this point's z minus the vector's z
	public Point4f MinusVector(Vector4f Minus) {
		return new Point4f(this.x-Minus.x, this.y-Minus.y, this.z-Minus.z, this.a-Minus.a);
	}


	//What this method aims to do is to return the result vector after this point minus a point
	//To do that, what this method do is to return a new vector whose x, y, z are the result of this point's x minus the point's x
	//this point's y minus the point's y, this point's z minus the point's z
	public Vector4f MinusPoint(Point4f Minus) {
		return new Vector4f(this.x-Minus.x, this.y-Minus.y, this.z-Minus.z, this.a-Minus.a);
	}
	
	 
	 // Remember point + point  is not defined so we not write a method for it. 
	
	
	 
	  
	
}

/*................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
....................................=?7777+.....................................
.............................,8MMMMMMMMMMMMMMMMM7...............................
...........................$MMMMMMMMMMMMMMMMMMMMMM7.............................
........................IMMMMMMMMMMMMMMMMMMMMMMMMMMMM?..........................
......................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN........................
.....................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$......................
...................ZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8....................
.................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM...................
................IMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$.................
...............=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ................
..............:MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
..............7MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:...............
..............DMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ...............
..............MMMMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMMM8...............
..............MMMMMMMMMMMMMMMMMMMMMMM  MMMMMMMMMMMMMMMMMMMMMMMMMN...............
..............NMMMMMMMMMMMMMMMMMMMM MM MMMMMMMMMMMMMMMMMMMMMMMMMO...............
..............$MMMMMMMMMMMMMMMMMM MMMM MMMMMMMMMMMMMMMMMMMMMMMMMI...............
..............+MMMMMMMMMMMMMMMMM          MMMMMMMMMMMMMMMMMMMMMM=...............
...............8MMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMMM................
................MMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMM8................
................MMMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMMN,................
................=MMMMMMMMMMMMMMMMMMMMM MMMMMMMMMMMMMMMMMMMMMMM..................
.................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ..................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMD...................
...................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
....................8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM......................
.....................,8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM,.......................
........................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMN?.........................
..........................NMMMMMMMMMMMMMMMMMMMMMMMMMI...........................
.............................$MMMMMMMMMMMMMMMMMMM?..............................
.................................,I$NMMMMMN$?...................................
................................................................................
................................................................................
................................................................................
.......................................................................*/
