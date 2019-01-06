
public class ExitPoint {
	private String name;
	private MazeRef destination;
	
	//Exit Point Constructor
	public ExitPoint(String name,MazeRef destination){
		this.name = name;
		this.destination = destination;  
	}
	  
	//Getting Exit Points Name
	public String getName(){
		return name;		  
	}
	  
	//Getting Exit Point's Destination
	public MazeRef getDestination(){
		return destination;
	}
	  
	//Equals Method for Exit Point
	public boolean equals(Object in){
		if ( !(in instanceof ExitPoint) )
			return false;
		else
	    {
			ExitPoint temp = (ExitPoint) in;
			if ((this.name == temp.getName()) &&
	           (this.destination == temp.getDestination()))
	            return true;
	        else
	            return false;
	    }
	}
	  
	  //String representation of ExitPoint
	  public String toString()
	   {
	      return name;
	   }
	
}
