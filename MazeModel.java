import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MazeModel implements Buildable {
	MazeRef mazeref = new MazeRef(7,12,1);	
	MazeSpace mazespace= new MazeSpace(mazeref);	

	//getting the maze size
	public MazeRef getMazeSize() {	
		return mazeref;
	}

	//getting the room from a specific location
	public Room getRoom(MazeRef location) {
		return mazespace.getRoom(location);
	}
	
	//getting all rooms in the maze space
	public Room[] getAllRooms() {
		return mazespace.getAllRooms();
	}
	
	//setting the start point of the maze
	public boolean setStartPoint(MazeRef location) {
			mazespace.setStartPoint(location);
			return true;
	}
	
	//setting the finish point of the maze
	public boolean setFinishPoint(MazeRef location) {	
			mazespace.setFinishPoint(location);
			return true;		
	}
	
	//Creating a room at a specific maze space
	public boolean createRoom(MazeRef location, String name) {
		Room room = new Room(location,name);
		mazespace.addRoom(room);
		return true;
		}

	//Adding an item to a room
	public boolean addItem(MazeRef location, String name, int health) {
		Item item = new Item(name,health);
		mazespace.getRoom(location).addItem(item);
		return true;
	}
	
	//Removing an item from a room
	public boolean removeItem(MazeRef location, String name) {
		mazespace.getRoom(location).removeItem(name);
		return true;
	}
	
	//Adding an exit point to a room
	public boolean addExitPoint(MazeRef location, String name,
			MazeRef destination) {
		ExitPoint exit = new ExitPoint(name,destination);
		mazespace.getRoom(location).addExit(exit);
		return true;
	}
	
	//Removing an exit point from a room
	public boolean removeExitPoint(MazeRef location, String name) {
		mazespace.getRoom(location).removeExit(name);
		return true;
	}
	
     //Function that checks if string is filled with whitespace or null
	 public boolean isWhitespace(String str) {
	      if (str == null) {
	          return false;
	      }
	      int sz = str.length();
	      for (int i = 0; i < sz; i++) {
	          if ((Character.isWhitespace(str.charAt(i)) == false)) {
	              return false;
	          }
	      }
	      return true;
	  }
	 
	 //Function that gets the selected input of the JSlider
	 public int getSelection(JOptionPane optionPane) {		    
		    int returnValue = JOptionPane.CLOSED_OPTION;		   
		    Object selectedValue = optionPane.getInputValue();		   
		    if (selectedValue != null) {
		    	Object options[] = optionPane.getOptions();
		    	if (options == null) {		    
		    		if (selectedValue instanceof Integer) {
		    			returnValue = ((Integer) selectedValue).intValue();
		    		}
		    	}else{		      
		        for (int i = 0, n = options.length; i < n; i++) {
		        	if (options[i].equals(selectedValue)) {
		        		returnValue = i;
		        		break; 
		        	}
		        }
		      }
		    }
		    if(returnValue == -1){
		    	returnValue = 0;
		    }
		  return returnValue;
	 }
	
	 //Function that creates the JSlider
	 JSlider getSlider(final JOptionPane optionPane){
		 JSlider slider = new JSlider(-25,25);
			 slider.setMajorTickSpacing(5);
			 slider.setPaintTicks(true);
			 slider.setPaintLabels(true);
		 ChangeListener changeListener = new ChangeListener() {
		    	public void stateChanged(ChangeEvent changeEvent) {
		    		JSlider theSlider = (JSlider) changeEvent.getSource();
		    			if(!theSlider.getValueIsAdjusting()) {
		    				optionPane.setInputValue(new Integer(theSlider.getValue()));
			     	  }
		    	}};
		    	slider.addChangeListener(changeListener);
		 return slider;
	 }
	 
	 //Function that checks if the location of the Icon Image is valid
	 protected ImageIcon createImageIcon(String path,String description){
				java.net.URL imgURL = getClass().getResource(path);
				if (imgURL != null) {
					return new ImageIcon(imgURL, description);
				}else{
					System.err.println("Couldn't find Icon File: " + path);
					return null;
				}
	 	}	
}
