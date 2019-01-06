
public interface Traversable
{
   public boolean reset();
   public Player getPlayer();
   public Room[] getAllRooms();
   public MazeRef getMazeSize();
   public Room getRoom(MazeRef location);
   public boolean move(String exitPointName) throws GameException;
}
