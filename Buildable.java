
public interface Buildable
{
   public MazeRef getMazeSize();
   public Room getRoom(MazeRef location);
   public Room[] getAllRooms();
   public boolean setStartPoint(MazeRef location);
   public boolean setFinishPoint(MazeRef location);
   public boolean createRoom(MazeRef location, String name);
   public boolean addItem(MazeRef location, String name, int health);
   public boolean removeItem(MazeRef location, String name);
   public boolean addExitPoint(MazeRef location, String name, MazeRef destination);
   public boolean removeExitPoint(MazeRef location, String name);
}
