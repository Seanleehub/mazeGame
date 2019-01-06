import java.util.*;

public class MazeEngine implements Buildable,Traversable{
	private Player player;
	private MazeSpace maze;
	private int initialHealth;
	
	//Maze Engine Constructor
	public MazeEngine(MazeRef size){
		this.maze = new MazeSpace(size);
		player = new Player("Unknown",50);
		initialHealth = 50;	
	}

	//Resetting Game
	@Override
	public boolean reset() {
		
		if(maze.getStartPoint() == null && maze.getFinishPoint() == null){
			return false;
		} 
		else
		{
			initialHealth = 50;
			player.setLocation(maze.getStartPoint());
			return true;		
		}
	}
	
	//Get Player Of the Game
	@Override
	public Player getPlayer() {
		return player;
	}

	//Move Player in the Maze
	@Override
	public boolean move(String exitPointName) throws GameException {
		ExitPoint[] eArray = getRoom(player.getLocation()).getExitList();	
		if(getRoom(player.getLocation()).getExitList() == null){
			return false;
		}
		else
		{
			for(int i=0;i < eArray.length; i++)
			{
				if(eArray[i].getName().equals(exitPointName))
				{
					player.setLocation(eArray[i].getDestination());
					Item [] iArray = getRoom(player.getLocation()).getItemList();
					Arrays.sort(iArray);
					for(int j = 0;j < iArray.length;j++)
					{	
						player.applyHealth(iArray[j].getHealth()); 
						if(player.getHealth() <= 0)
						{	
							throw new DeathException();
						}
					}
					if(player.getHealth() > 0 && player.getLocation().equals(maze.getFinishPoint()))
					{	
						 throw new SolvedException();
					}		
					return true;
				}
			}
		return false;			
		}
	}
	
	//Get Maze Size of the Maze
	@Override
	public MazeRef getMazeSize() {
		return maze.getMazeSize();
	}
	
	//Getting a Room in a Maze
	@Override
	public Room getRoom(MazeRef location) {
		return maze.getRoom(location);
	}
	
	//Getting List of all Rooms in the Maze
	@Override
	public Room[] getAllRooms() {	
		return maze.getAllRooms();
	}

	//Setting Start Point of the Maze
	@Override
	public boolean setStartPoint(MazeRef location) {
		maze.setStartPoint(location);
		return true;
	}

	//Setting Finish Point of the Maze
	@Override
	public boolean setFinishPoint(MazeRef location) {
		maze.setFinishPoint(location);
		return true;
	}
	
	//Creating a room in the Maze
	@Override
	public boolean createRoom(MazeRef location, String name) {
		Room tempRoom = new Room(location, name);
		if(maze.roomExists(location) == false && maze.isValidReference(location) == true)
		{	
			this.maze.addRoom(tempRoom);
			return true;
		}
		return false;
	}
	
	//Adding Item to a Room in the Maze
	@Override
	public boolean addItem(MazeRef location, String name, int health) {
		Item tempItem = new Item(name,health);
		Room tempRoom = getRoom(location);
		if(tempRoom == null)
		{
			return false;
		}
		else
		{				
			tempRoom.addItem(tempItem);
			return true;
		}			
	}
	
	//Removing Item in the Room
	@Override
	public boolean removeItem(MazeRef location, String name) {
		Room tempRoom = getRoom(location);
		if(tempRoom == null)
		{
			return false;
		}
		else
		{			
			if(tempRoom.getItem(name) == null)
			{
				return false;
			}
			else
			{		
			tempRoom.removeItem(name);	
			return true;
			}
		}						
	}
	
	//Adding Exit Point to the Room
	@Override
	public boolean addExitPoint(MazeRef location, String name,MazeRef destination) {
		Room tempRoom = getRoom(location);
		ExitPoint tempExitPoint = new ExitPoint(name,destination);
		if(tempRoom == null)
		{
			return false;
		}
		else
		{				
			tempRoom.addExit(tempExitPoint);	
			return true;
		}					
	}

	//Removing an Exit Point of a Room
	@Override
	public boolean removeExitPoint(MazeRef location, String name) {
		Room tempRoom = getRoom(location);
		if(tempRoom == null)
		{
			return false;
		}
		else
		{			
			if(tempRoom.getExit(name) == null)
			{
				return false;
			}
			else
			{		
			tempRoom.removeExit(name);	
			return true;
			}
		}
	}	
}
