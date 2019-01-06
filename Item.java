
public class Item implements Comparable{
	private String name;
	private int health;
	
	//Item Constructor
	public Item(String name,int health)
	{
		this.name = name;
		this.health = health;
	}
	
	//Getting Item Name
	public String getName()
	{
		return name;
	}
	
	//Getting Item Health
	public int getHealth()
	{
		return health;
	}
	
	//CompareTo method for Item Health
	public int compareTo(Object in)
	{	
		Item temp = (Item) in;
		return this.health - temp.getHealth();
	}

	//String Representation of Item
	public String toString()
	{
		return name;
	}
}

