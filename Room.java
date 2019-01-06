import java.util.*;

public class Room {
	private String name;
	private MazeRef location;
	List<Item> items = new ArrayList<Item>();
	List<ExitPoint> exits = new ArrayList<ExitPoint>();
	
	//Room Constructor
	public Room(MazeRef location,String name)
	{
		this.location = location;
		this.name = name;	
	}
	
	//Getting Room Location
	public MazeRef getLocation(){
		return location;
	}
	
	//Getting Room Name
	public String getName(){
		return name;
	}
	
	//Adding an Exit Point to Room
	public void addExit(ExitPoint in){
		exits.add(in);	
	}
	
	//Getting an Exit Point from Room
	public ExitPoint getExit(String name){
		for(ExitPoint exitpoints : exits){
			if(exitpoints.getName().equals(name))
			{
				return exitpoints;
			}
		}	
		return null;	
	}
	
	//Removing an Exit Point from Room
	public boolean removeExit(String name){
		for(int i=0;i < exits.size() ; i++){
			if(exits.get(i).getName().equals(name))
			{
				exits.remove(i);
				return true;
			}	
		}
		return false;
	}
	
	//Adding an Item to the Room
	public void addItem(Item in){
		items.add(in);
	}
	
	//Getting an Item from the Room
	public Item getItem(String name){
		  for (int i = 0; i < items.size(); i++)
	      {
	         Item temp = items.get(i);
	         if ( temp.getName().equals(name) )
	         {
	            return temp;
	         }
	      }

	      return null;
	}
	
	//Removing an Item from Item List
	public boolean removeItem(String itemName){
	    for (int i = 0; i < items.size(); i++)
	      {
	         Item temp = items.get(i);
	         if ( temp.getName().equals(itemName) )
	         {
	            items.remove(i);
	            return true;
	         }
	      }

	      return false;
	}
	
	//Getting List of Exit Points
	public ExitPoint[] getExitList(){
		return exits.toArray(new ExitPoint[0]);
	}
	
	//Getting List of Items
	public Item[] getItemList(){
		return items.toArray(new Item[0]);
	}
	
	//String representation of a Room
	public String toString() {
		 String ii = items.toString();
		 String iii = ii.replace("]", "").replace("[", "").replace(" ", "");
		 String ee = exits.toString();
		 String eee = ee.replace("]", "").replace("[", "").replace(" ", "");
		 return (location + ":"+ name + ":" + iii +  ":" + eee);
	} 
}

