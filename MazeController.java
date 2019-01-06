import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class MazeController {
	private MazeView theview;
	private MazeModel themodel;
	int a;
	int b;
	int startCounter = 0;
	int finishCounter = 0;
	
	public MazeController(MazeView theview,MazeModel themodel){
		this.theview=theview;
		this.themodel=themodel;		
		this.theview.gridButtonListener(new gridButtonListener());
		this.theview.mouseListener(new mouseListener());
		this.theview.buttonsListener(new buttonsListener());
	}
	
	//Listeners for the buttons
	class buttonsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			//Adds a room to the maze
			if(theview.addRoom == e.getSource() || theview.addRoomMenu == e.getSource()){				
				String roomname = JOptionPane.showInputDialog("Enter Room Name");				
				if(themodel.isWhitespace(roomname) == true || roomname == null){
					theview.displayErrorMessage("Please Enter a Valid Room Name");					
				}else{
				MazeRef ref = new MazeRef(a,b,0);
				MazeRef northref = new MazeRef(a-1,b,0);
				MazeRef southref = new MazeRef(a+1,b,0);
				MazeRef eastref = new MazeRef(a,b+1,0);
				MazeRef westref = new MazeRef(a,b-1,0);
			
					if( theview.grid[a][b].getBackground() == Color.WHITE
				    			  && a == ref.getX()
				    			  && b == ref.getY()){
						theview.grid[a][b].setBackground(Color.YELLOW);			
						themodel.createRoom(ref, roomname);
						theview.roomName.setText(themodel.getRoom(ref).getName());
					}				
					if(themodel.getRoom(northref) != null){	
						themodel.addExitPoint(ref,"North",northref);	
						themodel.addExitPoint(northref,"South",southref);
					}
					if(themodel.getRoom(southref) != null){		
						themodel.addExitPoint(ref,"South",southref);	
						themodel.addExitPoint(southref,"North",northref);
					}
					if(themodel.getRoom(eastref) != null){	
						themodel.addExitPoint(ref,"East",eastref);			
						themodel.addExitPoint(eastref,"West",westref);
					}
					if(themodel.getRoom(westref) != null){		
						themodel.addExitPoint(ref,"West",westref);		
						themodel.addExitPoint(westref,"East",eastref);
					}
				theview.grid[a][b].doClick();
				}			
			}
			
			//adds item to the room 
			if(theview.addItem == e.getSource() || theview.addItemMenu == e.getSource()){				
				String itemname = JOptionPane.showInputDialog("Enter Item Name");				
					if(themodel.isWhitespace(itemname) == true || itemname == null){
						theview.displayErrorMessage("Please Enter a Valid Item Name");					
					}else{				
					JOptionPane optionPane = new JOptionPane();
				    JSlider slider = themodel.getSlider(optionPane);
				    optionPane.setMessage(new Object[] { "Select a Health Value: ", slider });
				    optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
				    optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
				    JDialog dialog = optionPane.createDialog(null, "Item Health");
				    dialog.setVisible(true);					
				    int itemHealth = themodel.getSelection(optionPane);				  
				    MazeRef ref = new MazeRef(a,b,0);		
					themodel.addItem(ref, itemname, itemHealth);
					theview.grid[a][b].setIcon(themodel.createImageIcon("src//iconimage.jpg", "Image of the Icon"));	
					if(themodel.getRoom(ref).getItem(itemname) !=null ){
						theview.item.setText(themodel.getRoom(ref).getItem(itemname).getName()+ " " + "(" +  themodel.getRoom(ref).getItem(itemname).getHealth() + ")" );			
					}
				theview.grid[a][b].doClick();
				}			
			}
			
			//removes item from the room 
			if(theview.removeItem == e.getSource() || theview.removeItemMenu == e.getSource()){				
				MazeRef ref = new MazeRef(a,b,0);
				String itemname = themodel.getRoom(ref).items.get(0).getName();
				themodel.removeItem(ref,itemname);
				theview.grid[a][b].setIcon(null);
				theview.item.setText("");
				theview.grid[a][b].doClick();
		
			}
			
			//adds exit points from the room 
			if(theview.addExit == e.getSource() || theview.addExitMenu == e.getSource()){				
			    MazeRef ref = new MazeRef(a,b,0);
			    MazeRef northref = new MazeRef(a-1,b,0);
				MazeRef southref = new MazeRef(a+1,b,0);
				MazeRef eastref = new MazeRef(a,b+1,0);
				MazeRef westref = new MazeRef(a,b-1,0);			
				ArrayList<String> exit2s = new ArrayList<String>();
				exit2s.add("North");
				exit2s.add("South");
				exit2s.add("East");
				exit2s.add("West");
				for(int i = 0; i < themodel.getRoom(ref).exits.size();i++){
					exit2s.remove(themodel.getRoom(ref).exits.get(i).getName());			
				}
				String [] array = new String[exit2s.size()];
				array = exit2s.toArray(array);				
				if(array.length == 0){
					theview.displayErrorMessage("There are no avaiable Exit Points for this Room");									
				}else{
					JFrame frame = new JFrame("Exit Input");
				    String exit1 = (String) JOptionPane.showInputDialog(frame, 
				    		"Choose an Exit Point", "Exit Point", JOptionPane.QUESTION_MESSAGE,null,array,array[0]);
				    
				if(exit1 == "North"){	
					 MazeRef ref1 = new MazeRef(a-1,b,0);
					 MazeRef northref1 = new MazeRef(a-2,b,0);
				 	 MazeRef eastref1 = new MazeRef(a-1,b+1,0);
					 MazeRef westref1 = new MazeRef(a-1,b-1,0);
					if(themodel.mazespace.isValidReference(northref) == true ){
						if(themodel.getRoom(northref)==null){
						String roomname = JOptionPane.showInputDialog("Enter Room Name");	
							if(themodel.isWhitespace(roomname) == true || roomname == null){
								theview.displayErrorMessage("Please Enter a Valid Room Name");									
							}else{							
								themodel.addExitPoint(ref,"North",northref);									
								themodel.createRoom(northref,roomname);
								theview.grid[a-1][b].setBackground(Color.YELLOW);									
								themodel.addExitPoint(northref, "South", southref);							 							 
								 if(themodel.getRoom(northref1) != null){											
									 themodel.addExitPoint(ref1,"North",northref1);											
									 themodel.addExitPoint(northref1,"South",southref);									
								 }							
								if(themodel.getRoom(eastref1) != null){									
									themodel.addExitPoint(ref1,"East",eastref1);										
									themodel.addExitPoint(eastref1,"West",westref);									
								 }
								if(themodel.getRoom(westref1) != null){										
									themodel.addExitPoint(ref1,"West",westref1);										
									themodel.addExitPoint(westref1,"East",eastref);
								 }						
						}}else{
							themodel.addExitPoint(ref,"North",northref);
							 if(themodel.getRoom(northref) != null){																					
								 themodel.addExitPoint(northref,"South",southref);	
							 }							
					}}else{
					theview.displayErrorMessage("Room Out of bounds of MazeSpace");
					}
					 theview.grid[a][b].doClick();
			    }			
				
			    if(exit1 == "South"){
			    	MazeRef ref1 = new MazeRef(a+1,b,0);
					MazeRef southref1 = new MazeRef(a+2,b,0);
					MazeRef eastref1 = new MazeRef(a+1,b+1,0);
					MazeRef westref1 = new MazeRef(a+1,b-1,0);
			    	if(themodel.mazespace.isValidReference(southref) ==true ){	
			    		if(themodel.getRoom(southref)==null){
							String roomname = JOptionPane.showInputDialog("Enter Room Name");								
							if(themodel.isWhitespace(roomname) == true || roomname == null){
								theview.displayErrorMessage("Please Enter a Valid Room Name");							
							}else{					
								themodel.addExitPoint(ref,"South",southref);						
								themodel.createRoom(southref, roomname);
								theview.grid[a+1][b].setBackground(Color.YELLOW);						
								themodel.addExitPoint(southref,"North", northref);							 					
								if(themodel.getRoom(southref1) != null){							
									themodel.addExitPoint(ref1, "South", southref1);									
									themodel.addExitPoint(southref1,"North",northref);							
								}
								if(themodel.getRoom(eastref1) != null){								
									themodel.addExitPoint(ref1,"East",eastref1);							
									themodel.addExitPoint(eastref1,"West",westref);							
								}
								if(themodel.getRoom(westref1) != null){							
									 themodel.addExitPoint(ref1, "West", westref1);							
									 themodel.addExitPoint(westref1, "East", eastref);
								}								
						}}else{
							themodel.addExitPoint(ref,"South",southref);
							if(themodel.getRoom(southref) != null){										
								themodel.addExitPoint(southref,"North",northref);										
							 }
					}}else{
						theview.displayErrorMessage("Room Out of bounds of MazeSpace");
				    }
			    	theview.grid[a][b].doClick();
			    }
			    
			    if(exit1 == "East"){
			    	MazeRef ref1 = new MazeRef(a,b+1,0);
				 	MazeRef northref1 = new MazeRef(a-1,b+1,0);
					MazeRef southref1 = new MazeRef(a+1,b+1,0);
					MazeRef eastref1 = new MazeRef(a,b+2,0);
			    	if(themodel.mazespace.isValidReference(eastref) == true ){
			    		if(themodel.getRoom(eastref)==null){
							String roomname = JOptionPane.showInputDialog("Enter Room Name");							
								if(themodel.isWhitespace(roomname) == true || roomname == null){
									theview.displayErrorMessage("Please Enter a Valid Room Name");								
								}else{					
								themodel.addExitPoint(ref, "East", eastref);						
								themodel.createRoom(eastref, roomname);
								theview.grid[a][b+1].setBackground(Color.YELLOW);								
								themodel.addExitPoint(eastref, "West", westref);						 	
														 
							 if(themodel.getRoom(northref1) != null){							
								 themodel.addExitPoint(ref1, "North", northref1);							
								 themodel.addExitPoint(northref1, "South", southref);								
							 }
							 if(themodel.getRoom(southref1) != null){								
								 themodel.addExitPoint(ref1, "South", southref1);								
								 themodel.addExitPoint(southref1, "North", northref);								
							 }
							 if(themodel.getRoom(eastref1) != null){							
								 themodel.addExitPoint(ref1, "East", eastref1);						
								 themodel.addExitPoint(eastref1,"West", westref);								
							 }						
						}}else{
							themodel.addExitPoint(ref,"East",eastref);
							if(themodel.getRoom(eastref) != null){									
								themodel.addExitPoint(eastref,"West",westref);							
							 }							
					}}else{					
			    	theview.displayErrorMessage("Room Out of bounds of MazeSpace");
			    	}
			    	theview.grid[a][b].doClick();
			    }
			    
			    if(exit1 == "West"){
			    	MazeRef ref1 = new MazeRef(a,b-1,0);		 
			 		MazeRef northref1 = new MazeRef(a-1,b-1,0);
					MazeRef southref1 = new MazeRef(a+1,b-1,0);								
					MazeRef westref1 = new MazeRef(a,b-2,0);
			    	if(themodel.mazespace.isValidReference(westref) == true ){		
			    		if(themodel.getRoom(westref)==null){
							String roomname = JOptionPane.showInputDialog("Enter Room Name");	
								if(themodel.isWhitespace(roomname) == true || roomname == null){
									theview.displayErrorMessage("Please Enter a Valid Room Name");								
								}else{						
									themodel.addExitPoint(ref,"West",westref);					
									themodel.createRoom(westref, roomname);
									theview.grid[a][b-1].setBackground(Color.YELLOW);						
									themodel.addExitPoint(westref, "East", eastref);								 
									 if(themodel.getRoom(northref1) != null){								 
										 themodel.addExitPoint(ref1, "North",northref1);										
										 themodel.addExitPoint(northref1,"South", southref);
									 }
									 if(themodel.getRoom(southref1) != null){
										 themodel.addExitPoint(ref1, "South", southref1);										
										 themodel.addExitPoint(southref1, "North", northref);
									 }						
									 if(themodel.getRoom(westref1) != null){									
										 themodel.addExitPoint(ref1, "West", westref1);										
										 themodel.addExitPoint(westref1, "East", eastref);
									 }						
						}}else{
						themodel.addExitPoint(ref,"West",westref);
						if(themodel.getRoom(westref) != null){										
							themodel.addExitPoint(westref,"East",eastref);						
						 }
					}}else{			    		
						theview.displayErrorMessage("Room Out of bounds of MazeSpace");
			    	 }
			    	theview.grid[a][b].doClick();
				    }	
					}
				}
			
			//removes exit points from a room
			if(theview.removeExit == e.getSource() || theview.removeExitMenu == e.getSource()){
			    MazeRef ref = new MazeRef(a,b,0);
			    MazeRef northref = new MazeRef(a-1,b,0);
				MazeRef southref = new MazeRef(a+1,b,0);
				MazeRef eastref = new MazeRef(a,b+1,0);
				MazeRef westref = new MazeRef(a,b-1,0);
			ArrayList<String> exit1s = new ArrayList<String>();
			for(int i = 0; i < themodel.getRoom(ref).exits.size();i++){				
				exit1s.add(themodel.getRoom(ref).exits.get(i).getName());					
			}			
			String [] array = new String[exit1s.size()];
			array = exit1s.toArray(array);			
			JFrame frame = new JFrame("Exit Input");
			String exit1 = (String) JOptionPane.showInputDialog(frame,
					"Choose an Exit Point", "Exit Point", JOptionPane.QUESTION_MESSAGE,null,array,array[0]);				  
				  if(exit1 == "North"){				
						themodel.removeExitPoint(ref, "North");
						themodel.removeExitPoint(northref, "South");
						 theview.grid[a][b].doClick();
				   }			    
				  if(exit1 == "South"){			   
			    	themodel.removeExitPoint(ref, "South");
			    	themodel.removeExitPoint(southref, "North");
			    	theview.grid[a][b].doClick();
				  }
				  if(exit1 == "East"){			  
			    	themodel.removeExitPoint(ref, "East");
			    	themodel.removeExitPoint(eastref, "West");
			    	theview.grid[a][b].doClick();
				  }
				  if(exit1 == "West"){			    
			    	themodel.removeExitPoint(ref, "West");
			    	themodel.removeExitPoint(westref, "East");
			    	theview.grid[a][b].doClick();
				  }		
				}	
			//closes Java application from the Menu bar
			if(theview.close == e.getSource()){
				System.exit(0);		
			}
		}	
	}	
	
	//listener for the grid
	class gridButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 7; i++){
				 for (int j = 0; j < 12; j++)
				   if (theview.grid[i][j] == e.getSource()){
				       a = i;
				       b = j;				        
				       MazeRef ref = new MazeRef(a,b,0);
				       if(themodel.getRoom(ref) != null){
							theview.roomName.setText(themodel.getRoom(ref).getName());
							if(themodel.getRoom(ref).exits.isEmpty() == false){
								theview.removeExit.setEnabled(true);
								theview.removeExitMenu.setEnabled(true);									
								StringBuilder exitstring = new StringBuilder();
								if( themodel.getRoom(ref).exits.size()>0 ){
									exitstring.append(themodel.getRoom(ref).exits.get(0).getName());	
									for(int i1 = 1; i1 < themodel.getRoom(ref).exits.size(); i1++){
										 exitstring.append(",");
										 exitstring.append(themodel.getRoom(ref).exits.get(i1).getName());	 				
									}
								}
								String exitstring1 = exitstring.toString();
								theview.exit.setText(exitstring1);
								}else{
									theview.exit.setText("");
									theview.removeExit.setEnabled(false);
									theview.removeExitMenu.setEnabled(false);
								}								
							if(themodel.getRoom(ref).items.isEmpty() == false){
								String itemname = themodel.getRoom(ref).items.get(0).getName();
								theview.item.setText(themodel.getRoom(ref).getItem(itemname).getName()+ " " + "(" +  themodel.getRoom(ref).getItem(itemname).getHealth() + ")" );
								theview.removeItem.setEnabled(true);
								theview.removeItemMenu.setEnabled(true);
								theview.addItem.setEnabled(false);
								theview.addItemMenu.setEnabled(false);
							}else{									
								theview.item.setText("");
								theview.removeItem.setEnabled(false);
								theview.removeItemMenu.setEnabled(false);
								theview.addItem.setEnabled(true);
								theview.addItemMenu.setEnabled(true);
							}
							}				
							if(theview.grid[i][j].getBackground()==Color.WHITE){
								   theview.roomName.setText("");
							   	   theview.item.setText("");
								   theview.exit.setText("");
							}
							theview.coord.setText(String.format("%d,%d",j ,i));					
							if(theview.grid[i][j].getBackground() == Color.WHITE){
								  theview.addRoom.setEnabled(true);								 
								  theview.addItem.setEnabled(false);								  
								  theview.removeItem.setEnabled(false);
								  theview.addExit.setEnabled(false);
								  theview.removeExit.setEnabled(false);
								  theview.addRoomMenu.setEnabled(true);
								  theview.addItemMenu.setEnabled(false);
								  theview.removeItemMenu.setEnabled(false);
								  theview.addExitMenu.setEnabled(false);
								  theview.removeExitMenu.setEnabled(false);
							}else if(theview.grid[i][j].getBackground() == Color.YELLOW){
								  theview.addRoom.setEnabled(false);
								  theview.addRoomMenu.setEnabled(false);
								  theview.addExit.setEnabled(true);
								  theview.addExitMenu.setEnabled(true);
								  theview.addItem.setEnabled(true);
								  theview.addItemMenu.setEnabled(true);
							}				
				        }
				     }			
		          }			
				}			
		
		//mouse listener for the grid
		class mouseListener implements MouseListener{
			//adds start and finish points to the maze space
			public void mouseClicked(MouseEvent e){				
				if(e.getClickCount()==2 && !e.isConsumed()){
					e.consume();
					Object[] options = {"Start","Finish","Cancel"};
					int n = JOptionPane.showOptionDialog(null, "Please choose to enter Start or Finish Point ",
							"Setting Start or Finish Point",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
					MazeRef ref = new MazeRef(a,b,0);					
					if(n == 0){
						   if(theview.grid[a][b].getText() == "F"){
								theview.displayErrorMessage("Cant add Start and Finish Point in the same Room. Please select a different Room");							
							}else if(startCounter == 0){
								themodel.setStartPoint(ref);
								theview.grid[a][b].setText("S");
								startCounter = 1;								
							}else{
								theview.displayErrorMessage("Maze already has a Start Point");							
							}
					}
					if(n == 1){
						  if(theview.grid[a][b].getText() == "S"){
								theview.displayErrorMessage("Cant add Start and Finish Point in the same Room. Please select a different Room");							
							}else if(finishCounter == 0){
							themodel.setFinishPoint(ref);
							theview.grid[a][b].setText("F");
							finishCounter = 1;						
						}else{
							theview.displayErrorMessage("Maze already has a Finish Point");							
						}
					}		
					if(n == JOptionPane.CANCEL_OPTION){						
					}
				}	
			}

			public void mouseEntered(MouseEvent e){		
				//can show where mouse is over buttons
				for(int i = 0; i < 7; i++){
				    for (int j = 0; j < 12; j++)
				        if (theview.grid[i][j] == e.getSource()){
				        		theview.grid[i][j].setBackground(Color.WHITE);
				        }
				}
				
			}

			public void mouseExited(MouseEvent e){		
				for(int i = 0; i < 7; i++){
				    for (int j = 0; j < 12; j++)
				        if (theview.grid[i][j] == e.getSource()){
				        		theview.grid[i][j].setBackground(Color.WHITE);
				        }
				}
				
			}
			
			//refreshers the text fields which displays the required information of the room selected
			public void mousePressed(MouseEvent e){
				for(int i = 0; i < 7; i++){
				    for (int j = 0; j < 12; j++)
				        if (theview.grid[i][j] == e.getSource()){
				        	a = i;
				        	b = j;
				        	MazeRef ref = new MazeRef(a,b,0);
							if ( themodel.getRoom(ref) != null){
								theview.roomName.setText(themodel.getRoom(ref).getName());								
								if(themodel.getRoom(ref).exits.isEmpty() == false){
									theview.removeExit.setEnabled(true);
									theview.removeExitMenu.setEnabled(true);									
									StringBuilder exitstring = new StringBuilder();
									if( themodel.getRoom(ref).exits.size()>0 ){
										exitstring.append(themodel.getRoom(ref).exits.get(0).getName());	
										for(int i1 = 1; i1 < themodel.getRoom(ref).exits.size(); i1++) {
											exitstring.append(",");
											exitstring.append(themodel.getRoom(ref).exits.get(i1).getName());	 				
									 }
								}
								String exitstring1 = exitstring.toString();
								theview.exit.setText(exitstring1);
								}
								else{
									theview.exit.setText("");
									theview.removeExit.setEnabled(false);
									theview.removeExitMenu.setEnabled(false);
								}								
								if(themodel.getRoom(ref).items.isEmpty() == false){
									String itemname = themodel.getRoom(ref).items.get(0).getName();
									theview.item.setText(themodel.getRoom(ref).getItem(itemname).getName()+ " " + "(" +  themodel.getRoom(ref).getItem(itemname).getHealth() + ")" );
									 theview.removeItem.setEnabled(true);
									 theview.removeItemMenu.setEnabled(true);
									 theview.addItem.setEnabled(false);
									 theview.addItemMenu.setEnabled(false);
								}else{									
									theview.item.setText("");
									theview.removeItem.setEnabled(false);
									theview.removeItemMenu.setEnabled(false);
									theview.addItem.setEnabled(true);
									theview.addItemMenu.setEnabled(true);
								}
							}				
							if(theview.grid[i][j].getBackground()==Color.WHITE){
								theview.roomName.setText("");
								theview.item.setText("");
								theview.exit.setText("");
							}
						    theview.coord.setText(String.format("%d,%d",j ,i));					
						   if(theview.grid[i][j].getBackground() == Color.WHITE){
							  theview.addRoom.setEnabled(true);								 
							  theview.addItem.setEnabled(false);								  
							  theview.removeItem.setEnabled(false);
							  theview.addExit.setEnabled(false);
							  theview.removeExit.setEnabled(false);
							  theview.addRoomMenu.setEnabled(true);
							  theview.addItemMenu.setEnabled(false);
							  theview.removeItemMenu.setEnabled(false);
							  theview.addExitMenu.setEnabled(false);
							  theview.removeExitMenu.setEnabled(false);
					        }else if(theview.grid[i][j].getBackground() == Color.YELLOW){
							  theview.addRoom.setEnabled(false);
							  theview.addRoomMenu.setEnabled(false);
							  theview.addExit.setEnabled(true);
							  theview.addExitMenu.setEnabled(true);
							  theview.addItem.setEnabled(true);
							  theview.addItemMenu.setEnabled(true);
						  }	
				
				        }
				   }	
			}
			public void mouseReleased(MouseEvent e) {				
			}			
		}
	}

				
			
			
		

		
	
		
	

