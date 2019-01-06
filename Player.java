
public class Player {
	
	private String name;
	private int health;
	private MazeRef location;
	
	//Player Constructor
	public Player(String name,int health)
	{
		this.name = name;
		this.health = health;
	}
	
	//Getting Player Name
	public String getName(){
		return name;
	}
	
	//Getting Player Health
	public int getHealth(){
		return health;
	}
	
	//Getting the Location of  Player
	public MazeRef getLocation(){
		return location;
	}
	
	//Applying Health of the Player
	public int applyHealth(int change){
		return health+=change;	
	}
	
	//Setting Player's Location
	public void setLocation(MazeRef in){
		location = in;	
	}
	
	//String representation of Player
	public String toString(){
		return (name + ":" + health + ":" + location);
	}
}
