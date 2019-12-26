
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//GUI object that will be initialized for two clients as two different instances
public class makeGUI{
	
	JFrame frame;
	
	JButton button10;
	JTextField field1;
	JTextField field2;
	
	JButton button0;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	
	
	String terminated = "terminated";
	
	JButton[] buttonList;
	
	makeGUI(){
		this.initialise();
		 
	}
	//disabling buttons
	public void disableButtons() {
		button0.setEnabled(false);
		button1.setEnabled(false);
		button2.setEnabled(false);
		button3.setEnabled(false);
		button4.setEnabled(false);
		button5.setEnabled(false);
		button6.setEnabled(false);
		button7.setEnabled(false);
		button8.setEnabled(false);
		
	}
	//enabling buttons
	public void enableButtons() {
		button0.setEnabled(true);
		button1.setEnabled(true);
		button2.setEnabled(true);
		button3.setEnabled(true);
		button4.setEnabled(true);
		button5.setEnabled(true);
		button6.setEnabled(true);
		button7.setEnabled(true);
		button8.setEnabled(true);
		
	}
	
	//displaying the dialogframe for the winner
	public void winnerDisplay() {
		JFrame DialogFrame = new JFrame();
		int val = JOptionPane.showOptionDialog(DialogFrame, "Congradulations. You win.",null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if(val == 0) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	//displaying the dialogframe for the loser
	public void loserDisplay() {
		JFrame DialogFrame = new JFrame();
		int val = JOptionPane.showOptionDialog(DialogFrame, "You lose.",null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if(val == 0) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	//displaying the dialogframe for the draw case
	public void draw() {
		JFrame DialogFrame = new JFrame();
		int val = JOptionPane.showOptionDialog(DialogFrame, "Congradulations. You win.",null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if(val == 0) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	//initialising the board, buttons, fields
	public void initialise() {
		
		
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
		        
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(500, 500));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BorderLayout());
		
		
		JPanel playPanel = new JPanel();
		playPanel.setBackground(Color.WHITE);
		playPanel.setLayout(new GridLayout(3,3));
		
		
		button0 = new JButton("");
		playPanel.add(button0);
		
		button1 = new JButton("");
		playPanel.add(button1);
		
		button2 = new JButton("");
		playPanel.add(button2);
		
		button3 = new JButton("");
		playPanel.add(button3);
		
		button4 = new JButton("");
		playPanel.add(button4);
		
		button5 = new JButton("");
		playPanel.add(button5);
		
		button6 = new JButton("");
		playPanel.add(button6);
		
		button7 = new JButton("");
		playPanel.add(button7);
		
		button8 = new JButton("");
		playPanel.add(button8);
		
		
	    buttonList = new JButton[9];
	    buttonList[0] = button0;
		buttonList[1] = button1;
		buttonList[2] = button2;
		buttonList[3] = button3;
		buttonList[4] = button4;
		buttonList[5] = button5;
		buttonList[6] = button6;
		buttonList[7] = button7;
		buttonList[8] = button8;
		
		
		JPanel inputPanel = new JPanel();
		
		inputPanel.setLayout(new FlowLayout());
		
		field2 = new JTextField(30);
		inputPanel.add(field2);
		
		button10 = new JButton("Submit");
		
		inputPanel.add(button10);
		
		field1 = new JTextField("Enter your player name...");
		field1.setEditable(false);
	
		
		mainPanel.add(field1, BorderLayout.PAGE_START);
		mainPanel.add(playPanel, BorderLayout.CENTER);
		mainPanel.add(inputPanel, BorderLayout.PAGE_END);
		
		Menu menu = new Menu();
		menu.menuBar(this);
		
		frame.add(mainPanel);
		frame.setTitle("Tic Tac Toe");
		frame.setSize(400, 700);
		frame.pack();
		frame.setVisible(true);
		
	}
	

	
	
	//the getters and setters for the buttons
	public JButton getButton0() {
		return button0;
	}
	
	public JButton getButton1() {
		return button1;
	}
	
	public JButton getButton2() {
		return button2;
	}
	
	public JButton getButton3() {
		return button3;
	}
	
	public JButton getButton4() {
		return button4;
	}
	
	public JButton getButton5() {
		return button5;
	}
	
	public JButton getButton6() {
		return button6;
	}
	
	public JButton getButton7() {
		return button7;
	}
	
	public JButton getButton8() {
		return button8;
	}
	
	public JButton getSubmitButton() {
		return button10;
	}
	
	public JButton[] getTable() {
		return buttonList;
	}
	
	
	//shows that the other player has left the game
	public void otherPlayerLeftTheGame() {
		System.out.println("Closing");
		JFrame DialogFrame = new JFrame();
		int val = JOptionPane.showOptionDialog(DialogFrame, "Game ends. One of the players left.",null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if(val == 0) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		//JOptionPane.showMessageDialog(DialogFrame, "Game ends. One of the players left.");
	}
	
	public JFrame getFrame() {
		return frame;
	}
	

}
