

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;

//Controller to start client instances and receive messages from the server
public class Controller{

	private makeGUI view;
	private ActionListener button0Listener;
	private ActionListener button1Listener;
	private ActionListener button2Listener;
	private ActionListener button3Listener;
	private ActionListener button4Listener;
	private ActionListener button5Listener;
	private ActionListener button6Listener;
	private ActionListener button7Listener;
	private ActionListener button8Listener;
	private ActionListener submitButtonListener;

	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	
	
	
	
	public Controller(makeGUI view) {
		this.view = view;
		
		
	}
	//Set the socket information and printwriter, set all the actionlisteners for the buttons on the board
	public void start() {
		try {
			this.socket = new Socket("127.0.0.1", 58901);
			this.in = new Scanner(socket.getInputStream());
			this.out = new PrintWriter(socket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		submitButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("Submit Button Pressed");
				view.button10.setEnabled(false);
				view.field2.setEditable(false);
				String name = view.field2.getText();
				view.field1.setText("WELCOME " + name);
				view.frame.setTitle("Tic Tac Toe-Player: " + name);
				out.println("initialEnable");
			}
		};
		
		view.button10.addActionListener(submitButtonListener);
		
		button0Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button0");
				System.out.println("Client Sent: Button0 Pressed");
			}
		};
		view.getButton0().addActionListener(button0Listener);
		
		button1Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button1");
				System.out.println("Client Sent: Button1 Pressed");
			}
		};
		view.getButton1().addActionListener(button1Listener);

		button2Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button2");
				System.out.println("Client Sent: Button2 Pressed");
			}
		};
		view.getButton2().addActionListener(button2Listener);
		
		button3Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button3");
				System.out.println("Client Sent: Button3 Pressed");
			}
		};
		view.getButton3().addActionListener(button3Listener);
		
		button4Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button4");
				System.out.println("Client Sent: Button4 Pressed");
			}
		};
		view.getButton4().addActionListener(button4Listener);
		
		button5Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button5");
				System.out.println("Client Sent: Button5 Pressed");
			}
		};
		view.getButton5().addActionListener(button5Listener);
		
		button6Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button6");
				System.out.println("Client Sent: Button6 Pressed");
			}
		};
		view.getButton6().addActionListener(button6Listener);
		
		button7Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button7");
				System.out.println("Client Sent: Button7 Pressed");
			}
		};
		view.getButton7().addActionListener(button7Listener);
		
		button8Listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				out.println("Button8");
				System.out.println("Client Sent: Button8 Pressed");
			}
		};
		view.getButton8().addActionListener(button8Listener);

		// Creates a new Thread for reading server messages
		Thread handler = new ClientHandler(socket);
		handler.start();
	}
	
	//Thread child class to handle clients
	class ClientHandler extends Thread{
		private Socket socket;

		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				readFromServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Receiving input from the server and updating GUI accordingly
		public void readFromServer() throws Exception {
			try {
				
				view.frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						out.println("left");
					}});
				
				while (in.hasNextLine()) {
					String command = in.nextLine();
					System.out.println("Client Received: " + command);
					out.flush();
					
					if(command.equals("enable"))
						view.enableButtons();
					else if(command.equals("disable"))
						view.disableButtons();
					
					if(command.equals("lose")) {
						System.out.println("Lost");		
						view.loserDisplay();
					}else if(command.equals("draw")) {
						view.draw();
					}else if(command.equals("win")) {
						System.out.println("Won");
						view.winnerDisplay();
					}else if(command.startsWith("player")) {
						System.out.println("This terminal is: " + command);
					}else if(command.startsWith("left")){
						view.otherPlayerLeftTheGame();
					}else if(command.equals("val")) {
						view.field1.setText("Valid move, wait for your opponent");
					}else if(command.equals("you")) {
						view.field1.setText(" Your opponent has moved, now is your turn.");
					}
				
					String mark = Character.toString(command.charAt(command.length()-1));
					command = command.substring(0,command.length()-1);
					
					if(command.equals("Button0"))
						view.getButton0().setText(mark);
					else if(command.equals("Button1"))
						view.getButton1().setText(mark);
					else if(command.equals("Button2"))
						view.getButton2().setText(mark);
					else if(command.equals("Button3"))
						view.getButton3().setText(mark);
					else if(command.equals("Button4"))
						view.getButton4().setText(mark);
					else if(command.equals("Button5"))
						view.getButton5().setText(mark);
					else if(command.equals("Button6"))
						view.getButton6().setText(mark);
					else if(command.equals("Button7"))
						view.getButton7().setText(mark);
					else if(command.equals("Button8"))
						view.getButton8().setText(mark);
					
					
					
					
					
					
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				socket.close();
			}
			
			
			
			
		}
	}
	
}
