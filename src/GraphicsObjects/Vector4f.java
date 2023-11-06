package GraphicsObjects;

/**
 *
 * This class represents a vector
 *
 * The Variables in this class are x, y, z and a which represent the x coordinate, y coordinate and z coordinate of this vector
 *
 * The Methods involved in this class are PlusVector, MinusVector, PLusPoint, byScale, NegateVector, length, Normal, dot, cross
 *
 * @author Ma Zixiao
 *
 *
 */

public class Vector4f {

	public float x=0;
	public float y=0;
	public float z=0;
	public float a=0;
	
	public Vector4f() 
	{  
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	 
	public Vector4f(float x, float y, float z,float a) 
	{ 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}

	//What this method aims to do is to add this vector with an additional vector
	//To realize the functionality, this method returns a new vector whose x, y and z are the sum of this vector's x, y, z and the additional vector's x, y, z
	public Vector4f PlusVector(Vector4f Additonal) 
	{ 
		return new Vector4f(this.x+Additonal.x, this.y+Additonal.y, this.z+Additonal.z,this.a+Additonal.a);
	}

	//What this method aims to show the new vector after this vector minuses another vector
	//To realize the functionality, this method returns a new vector whose x, y and z are the difference between this vector's x, y, z and the additional vector's x, y, z
	public Vector4f MinusVector(Vector4f Minus) 
	{
		return new Vector4f(this.x-Minus.x, this.y-Minus.y, this.z-Minus.z, this.a-Minus.a);
	}

	//What this method aims to do is to calculate the sum of this vector and an additional point
	//To achieve the functionality, this method returns a new vector whose x, y and z are the sum of this vector's x, y, z and the additional point's x, y, z
	public Point4f PlusPoint(Point4f Additonal) 
	{
		return new Point4f(this.x+Additonal.x, this.y+Additonal.y, this.z+Additonal.z, this.a+Additonal.a);
	} 
	//Do not implement Vector minus a Point as it is undefined 

	//This method is to multiply this vector with a scale
	//To achieve that, this method returns a new vector whose x, y and z are the scale times that of this vector
	public Vector4f byScalar(float scale )
	{
		return new Vector4f(this.x*scale, this.y*scale, this.z*scale, this.a*scale);
	}

	//This method is to return a vector which is negative to this vector
	//To do that, this method returns a vector whose x, y and z are negative to this vector's x, y and z
	public Vector4f  NegateVector()
	{
		return new Vector4f( -this.x, -this.y, -this.z, -this.a );
	}

	//This method returns the length of this vector which is (x^2 + y^2 + z^2)^0.5
	public float length()
	{
	    return (float) Math.sqrt(x*x + y*y + z*z+ a*a);
	}

	//This method aims to return the normalized vector of this vector
	//To do that, this method will return a new vector whose x, y, z and a are all this vector's x, y, z and a divided its length
	public Vector4f Normal()
	{
		float LengthOfTheVector=  this.length();
		return this.byScalar(1.0f/ LengthOfTheVector); 
	}

	//This method is to calculate the dot product of this vector and another vector
	//To do that, this method returns a scalar which is the sum of this vector's x * another vector's x, this vector's y * another vector's y, this vector's z * another vector's z
	public float dot(Vector4f v)
	{
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}
	
	// Implemented this for you to avoid confusion 
	// as we will not normally  be using 4 float vector  
	public Vector4f cross(Vector4f v)  
	{ 
    float u0 = (this.y*v.z - z*v.y);
    float u1 = (z*v.x - x*v.z);
    float u2 = (x*v.y - y*v.x);
    float u3 = 0; //ignoring this for now  
    return new Vector4f(u0,u1,u2,u3);
	}
 
}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */