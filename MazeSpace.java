import java.util.*;

public class MazeSpace {
	private Map<MazeRef, Room> rooms;
	private MazeRef limits;
	private MazeRef start;
	private MazeRef finish;
	
	//Maze Space Constructor
	public MazeSpace(MazeRef limits){
		this.limits = limits;
		this.rooms = new HashMap<MazeRef, Room>();	
	}
	
	//Checking if Maze Reference is within bounds of the Maze
	public boolean isValidReference(MazeRef in)   {
	      if ( (in.getX() < 0) || (in.getX() >= limits.getX()) ||
	              (in.getY() < 0) || (in.getY() >= limits.getY()) ||
	              (in.getZ() < 0) || (in.getZ() >= limits.getZ()) )
	            return false;
	         else
	            return true;
	      }
	
	//Checking of room Exists
	public boolean roomExists(MazeRef in){
		if(rooms.containsKey(in)){
			return true;
		}
		return false;	
	}
	
	//Adding a Room to Maze Space
	public boolean addRoom(Room in){
		rooms.put(in.getLocation(), in);
		return true;
	}
	
	//Getting a room from Maze Space
	public Room getRoom(MazeRef in){
		if ( rooms.containsKey(in) )
		{
			return rooms.get(in);
		}
		else
		{
			return null;
		}
	}
	
	//List of all Rooms
	public Room[] getAllRooms(){
		return rooms.values().toArray(new Room[0]);
	}
	
	//Setting Start Point of the Maze
	public boolean setStartPoint(MazeRef location){
		this.start = location;
		return true;
	}
	
	//Setting Finish Point of the Maze
	public boolean setFinishPoint(MazeRef location){
		this.finish = location;
		return true;
	}
	
	//Getting the Start Point of the Maze
	public MazeRef getStartPoint(){
		return start;	
	}
	
	//Getting the Finish Point of the Maze
	public MazeRef getFinishPoint(){
		return finish;
	}
	
	//Getting the Size of the Maze
	public MazeRef getMazeSize(){
		return limits;   
	}
}
