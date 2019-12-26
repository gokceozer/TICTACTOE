

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JTextArea;

public class Menu implements ActionListener {
	JTextArea text;
	
	//empty constructor, unnecessary but I like it
	public Menu() {
		
	}
	
	//create a menubar and add it to the frame. It also contains two sub menus: control and help
	public void menuBar(makeGUI frame) {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Control");
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ControllerListener(frame));
		menu.add(menuItem);
		menuBar.add(menu);

		JMenu menu1 = new JMenu("Help");
		JMenuItem menuItem1 = new JMenuItem("Rules");
		menuItem1.addActionListener(new HelpListener(frame));
		menu1.add(menuItem1);
		menuBar.add(menu1);

		frame.frame.setJMenuBar(menuBar);

	}
	//the control button exits the game
	public class ControllerListener implements ActionListener {
		makeGUI frame;

		public ControllerListener(makeGUI frame) {
			this.frame = frame;
		}
		//exits the game
		public void actionPerformed(ActionEvent event) {
			frame.frame.dispatchEvent(new WindowEvent(frame.frame, WindowEvent.WINDOW_CLOSING));
		}

	}
	//the help button shows the rules in a pop up box
	public class HelpListener implements ActionListener {
		JTextArea text;
		makeGUI frame;
		
		public HelpListener(makeGUI frame) {
			this.frame = frame;
			
		}
		
		//the pop up for rules
		public void actionPerformed(ActionEvent event) {
			
			
			JFrame DialogFrame = new JFrame();
			JOptionPane.showMessageDialog(DialogFrame, "Criteria for a valid move:\n" + 
					"- The move is not occupied by any mark.\n" + 
					"- The move is made in the player’s turn.\n" + 
					"- The move is made within the 3 x 3 board.\n" + 
					"The game would continue and switch among the opposite player until it reaches either\n" + 
					"one of the following conditions:\n" + 
					"- Player 1 wins.\n" + 
					"- Player 2 wins.\n" + 
					"- Draw.\n", "Game Rules", JOptionPane.PLAIN_MESSAGE);
			

			}
			
		
	}	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
