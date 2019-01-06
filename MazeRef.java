
public class MazeRef 
{
	   private int x, y, z;
	   
	   //Maze Reference Initialization
	   public MazeRef(){
	      this(-1, -1, -1);
	   }
	   
	   //Maze Reference Constructor
	   public MazeRef(int x, int y, int z){
	      this.x = x;
	      this.y = y;
	      this.z = z;
	   }

	   //Getting Coordinate X of Maze Reference
	   public int getX(){
	      return x;
	   }
	   
	   //Getting Coordinate Y of Maze Reference
	   public int getY(){
	      return y;
	   }

	   //Getting Coordinate Z of Maze Reference
	   public int getZ(){
	      return z;
	   }
	   
	   //Equals Method for Maze Reference
	   public boolean equals(Object in){
	      if ( !(in instanceof MazeRef) )  
	         return false;
	      else
	      {
	         MazeRef temp = (MazeRef) in;
	         if ((this.x == temp.getX()) &&
	              (this.y == temp.getY()) &&
	              (this.z == temp.getZ()))
	         {
	            return true;
	         }
	         else
	         {
	            return false;
	         }
	      }
	   }
	   
	   //Hash Code for Maze Reference
	   public int hashCode()
	   {
	      return this.toString().hashCode();
	   }
	   
	   //String representation of Maze Reference
	   public String toString()
	   {
	      return (x + "," + y + "," + z);
	   }	 
}
