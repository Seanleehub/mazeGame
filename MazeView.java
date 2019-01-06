import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MazeView extends JFrame{

	//The Menu items that will be in the North JPanel
	JPanel north = new JPanel();
	private JMenuBar menubar = new JMenuBar();
	private JMenu maze = new JMenu("Maze");
	JMenuItem close = new JMenuItem("Close");
	private JMenu rooms = new JMenu("Room");
	JMenuItem addRoomMenu = new JMenuItem("Add Room");
	private JMenu items = new JMenu("Item");
	JMenuItem addItemMenu = new JMenuItem("Add Item");
	JMenuItem removeItemMenu = new JMenuItem("Remove Item");
	private JMenu exits1 = new JMenu("Exit Point");
	JMenuItem addExitMenu = new JMenuItem("Add Exit");
	JMenuItem removeExitMenu = new JMenuItem("Remove Exit");
	
	//Buttons and labels for the Center JPanel
	JPanel center = new JPanel();	
	JButton[][] grid = new JButton[7][12];
	private JLabel label = new JLabel(" ");
	private JLabel label0 = new JLabel("0", SwingConstants.CENTER);
	private JLabel label1 = new JLabel("1", SwingConstants.CENTER);
	private JLabel label2 = new JLabel("2", SwingConstants.CENTER);
	private JLabel label3 = new JLabel("3", SwingConstants.CENTER);
	private JLabel label4 = new JLabel("4", SwingConstants.CENTER);
	private JLabel label5 = new JLabel("5", SwingConstants.CENTER);
	private JLabel label6 = new JLabel("6", SwingConstants.CENTER);
	private JLabel label7 = new JLabel("7", SwingConstants.CENTER);
	private JLabel label8 = new JLabel("8", SwingConstants.CENTER);
	private JLabel label9 = new JLabel("9", SwingConstants.CENTER);
	private JLabel label10 = new JLabel("10", SwingConstants.CENTER);
	private JLabel label11 = new JLabel("11", SwingConstants.CENTER);	
	private JLabel label01 = new JLabel("0", SwingConstants.CENTER);
	private JLabel label02 = new JLabel("1", SwingConstants.CENTER);
	private JLabel label03 = new JLabel("2", SwingConstants.CENTER);
	private JLabel label04 = new JLabel("3", SwingConstants.CENTER);
	private JLabel label05 = new JLabel("4", SwingConstants.CENTER);
	private JLabel label06 = new JLabel("5", SwingConstants.CENTER);
	private JLabel label07 = new JLabel("6", SwingConstants.CENTER);
	
	//Buttons that will be in the West JPanel
	JPanel west = new JPanel();
	JButton addRoom = new JButton("Add Room");
	JButton addItem = new JButton("Add Item");
	JButton removeItem = new JButton("Remove Item");
	JButton addExit = new JButton("Add Exit");
	JButton removeExit = new JButton("Remove Exit");
	
	//Labels and text boxes that will be in the south Jpanel
	JPanel south = new JPanel();
	private JLabel coordLabel = new JLabel("Selected Coord:", SwingConstants.CENTER);
	JTextField coord = new JTextField(10);
	private JLabel roomNameLabel = new JLabel("Room Name:", SwingConstants.CENTER);
	JTextField roomName = new JTextField(10);
	private JLabel itemLabel = new JLabel("Item:", SwingConstants.CENTER);
	JTextField item = new JTextField(10);
	private JLabel exitLabel = new JLabel("Exit(s):", SwingConstants.CENTER);
	JTextField exit = new JTextField(10);

	MazeView(){
		  JFrame fr= new JFrame("Maze Builder");
		  JPanel p = new JPanel();
		  p.setLayout(new BorderLayout());
	
		  //Adding the Menu items to the Menubar
		  menubar.add(maze);
		  maze.setMnemonic(KeyEvent.VK_M);
		  maze.add(close);
		  close.setMnemonic(KeyEvent.VK_C);
		  menubar.add(rooms);
		  rooms.setMnemonic(KeyEvent.VK_R);
		  rooms.add(addRoomMenu);
		  addRoomMenu.setMnemonic(KeyEvent.VK_O);
		  menubar.add(items);
		  items.setMnemonic(KeyEvent.VK_I);
		  items.add(addItemMenu);
		  addItemMenu.setMnemonic(KeyEvent.VK_T);
		  items.add(removeItemMenu);
		  removeItemMenu.setMnemonic(KeyEvent.VK_V);
		  menubar.add(exits1);
		  exits1.setMnemonic(KeyEvent.VK_E);
		  exits1.add(addExitMenu);
		  addExitMenu.setMnemonic(KeyEvent.VK_D);
		  exits1.add(removeExitMenu);
		  removeExitMenu.setMnemonic(KeyEvent.VK_X);
		  addRoomMenu.setEnabled(false);
		  addItemMenu.setEnabled(false);
		  removeItemMenu.setEnabled(false);
		  addExitMenu.setEnabled(false);
		  removeExitMenu.setEnabled(false);		  
		  p.add(menubar,BorderLayout.NORTH);
		 
		  //Adding the buttons and labels of the center Panel
		  center.setLayout(new GridLayout(8,13));
		  center.setBorder(BorderFactory.createTitledBorder("MazeSpace"));	
		  center.add(label);
		  center.add(label0);
		  center.add(label1);
		  center.add(label2);
		  center.add(label3);
		  center.add(label4);
		  center.add(label5);
		  center.add(label6);
		  center.add(label7);
		  center.add(label8);
		  center.add(label9);
		  center.add(label10);
		  center.add(label11);
		  
		  for(int x=0; x<7; x++){
			 if(x == 0){
				 center.add(label01);
			 }
			 if(x == 1){
				 center.add(label02);
			 }
			 if(x == 2){
				 center.add(label03);
			 }
			 if(x == 3){
				 center.add(label04);
			 }
			 if(x == 4){
				 center.add(label05);
			 }
			 if(x == 5){
				 center.add(label06);
			 }
			 if(x == 6){
				 center.add(label07);
			 }
			 for(int y=0; y<12; y++){			
				      grid[x][y]=new JButton();		
                      grid[x][y].setBackground(Color.WHITE);
                      grid[x][y].setVerticalTextPosition(SwingConstants.BOTTOM);
                      grid[x][y].setHorizontalTextPosition(SwingConstants.LEFT);
                      grid[x][y].setVerticalAlignment(SwingConstants.TOP);
                      grid[x][y].setHorizontalAlignment(SwingConstants.RIGHT);
                      grid[x][y].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                      center.add(grid[x][y]);
              }
		  }		 
		  p.add(center,BorderLayout.CENTER);
		
		  //adding the buttons to the west panel
		  west.setLayout(new GridLayout(7,1));
		  west.setBorder(BorderFactory.createTitledBorder("Instruction"));	
		  west.add(addRoom);
		  west.add(Box.createRigidArea(new Dimension(1,0)));
		  west.add(addItem);
		  west.add(removeItem);
		  west.add(Box.createRigidArea(new Dimension(1,0)));
		  west.add(addExit);
		  west.add(removeExit);
		  addRoom.setEnabled(false);
		  addItem.setEnabled(false);
		  removeItem.setEnabled(false);
		  addExit.setEnabled(false);
		  removeExit.setEnabled(false);
		  p.add(west,BorderLayout.WEST);
		 
		  //Adding the labels and text boxes to the south panel
		  south.setLayout(new GridLayout(2,4));
		  south.setBorder(BorderFactory.createTitledBorder("Room Information"));
		  coord.setEditable(false);
		  roomName.setEditable(false);
		  item.setEditable(false);
		  exit.setEditable(false);
		  south.add(coordLabel);
		  south.add(coord);
		  south.add(itemLabel);
		  south.add(item);
		  south.add(roomNameLabel);
		  south.add(roomName);
		  south.add(exitLabel);
		  south.add(exit);
		  p.add(south,BorderLayout.SOUTH);
		  fr.setSize(900,500);
		  fr.setResizable(true);
		  fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  fr.add(p);
		  fr.setVisible(true); 
		}
	
	  //display error function
	  void displayErrorMessage(String errorMessage){		 	         
		  JOptionPane.showMessageDialog(this, errorMessage,"Error",JOptionPane.ERROR_MESSAGE);		   	         
	  }
	  
	  //Menu bar and West panel button listeners
	  void buttonsListener(ActionListener a){
		addRoom.addActionListener(a);
		addItem.addActionListener(a);	
		removeItem.addActionListener(a);	
		addExit.addActionListener(a);	
		removeExit.addActionListener(a);	
		addRoomMenu.addActionListener(a);	
		addItemMenu.addActionListener(a);	
		removeItemMenu.addActionListener(a);	
		addExitMenu.addActionListener(a);	
		removeExitMenu.addActionListener(a);
		close.addActionListener(a);
	  }

	//action listener for the Grid buttons
	void gridButtonListener(ActionListener ev){
		 for(int x=0; x<7; x++){
			 for(int y=0; y<12; y++){ 
				 grid[x][y].addActionListener(ev);
	         }
		 }
	}
	
	//mouse listener for the Grid buttons
	void mouseListener(MouseListener ev){
		for(int x=0; x<7; x++){
			for(int y=0; y<12; y++){ 		
				grid[x][y].addMouseListener(ev);	
	        }
		}
	}
}
