
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//Server instance 
public class Server {
	private ServerSocket serverSocket;
	private static SharedBoard board;
	int clientCount;
	boolean player1enabled = false;
	boolean player1turn = false;
	PrintWriter player1output = null;
	PrintWriter player2output = null;
	boolean player1left = false;
	boolean player2left = false;
	boolean gameOver = false;
	
	
	AtomicInteger namesEntered = new AtomicInteger();
	
	//Keep the clients in this arraylist
	private ArrayList<PrintWriter> writers=new ArrayList<PrintWriter>();

	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		Server.board = new SharedBoard();
		this.clientCount = 0;
	}
	//Get board
	public static SharedBoard getBoard() {
		for(int i=0; i<9; i++)
			System.out.println(board.moves[i]);
		return board;
	}
	
	//wait for clients to request access
	public void start(){
        var pool = Executors.newFixedThreadPool(200);
		
			
		while (clientCount<2) {
			try {
				if(clientCount == 0) {
					Socket socket1 = serverSocket.accept();
					clientCount++;
					pool.execute(new Handler(socket1, "X"));
				}else if(clientCount == 1) {
					Socket socket2 = serverSocket.accept();
					clientCount++;
					System.out.println("Client count is: " + clientCount );
					pool.execute(new Handler(socket2, "O"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	//Server instance to handle the client connections, updates all the clients, checks for winning/losing/draw cases
	public class Handler implements Runnable {
		private Socket socket;
		private Scanner input;
		private PrintWriter output;
		String mark;
		
		
		public Handler(Socket socket, String mark) {
			this.socket = socket;
			this.mark = mark;
		}

		@Override
		public void run() {
			System.out.println("Connected: " + socket);
			try {
				input = new Scanner(socket.getInputStream());
				output = new PrintWriter(socket.getOutputStream(), true);
		
				writers.add(output);
				
				output.println("disable");
				
				int counter = 0;
				
				if(clientCount == 1)
					for(PrintWriter writer : writers) {
						player1output = writer;
						player1output.println("player1");
						System.out.println(writer);
						break;
					}
				else if(clientCount == 2) {
					for(PrintWriter writer : writers) {
						counter++;
						System.out.println(writer);
						if(counter == 2) {
							player2output = writer;
							player2output.println("player2");
						}
					
					}
				}	
			
					
				

				while (input.hasNextLine()) {
				
					String command = input.nextLine();
					System.out.println("Server Received: " + command);
					boolean valid = false;
					
					if(command.equals("initialEnable"))
						namesEntered.addAndGet(1);
					
					
					if(namesEntered.get() == 2 && player1enabled == false) {
						player1output.println("enable");
						player1enabled = true;
						player1turn = true;
					}
					
					if(command.equals("left")) {
						if(mark.equals("X")) {
							player2output.println("left");
						}else {
							player1output.println("left");
						}
					}
				
					if (command.startsWith("Button0") && board.moves[0].equals("")) {
						board.button0(mark);
						valid = true;
					}else if (command.startsWith("Button1") && board.moves[1].equals("")) {
						board.button1(mark);
						valid = true;
					}else if (command.startsWith("Button2") && board.moves[2].equals("")) {
						board.button2(mark);
						valid = true;
					}else if (command.startsWith("Button3") && board.moves[3].equals("")) {
						board.button3(mark);
						valid = true;
					}else if (command.startsWith("Button4") && board.moves[4].equals("")) {
						board.button4(mark);
						valid = true;
					}else if (command.startsWith("Button5") && board.moves[5].equals("")) {
						board.button5(mark);
						valid = true;
					}else if (command.startsWith("Button6") && board.moves[6].equals("")) {
						board.button6(mark);
						valid = true;
					}else if (command.startsWith("Button7") && board.moves[7].equals("")) {
						board.button7(mark);
						valid = true;
					}else if (command.startsWith("Button8") && board.moves[8].equals("")) {
						board.button8(mark);
						valid = true;
					}
					
					
						
				
					if(command.startsWith("Button") && valid == true) {
						for (PrintWriter writer : writers) {
							writer.println(command+mark);
						}
						if(player1turn == true) {
							player1turn = false;
							player1output.println("disable");
							player1output.println("val");
							player2output.println("enable");
							player2output.println("you");
						}
							
						else {
							player1turn = true;
							player1output.println("enable");
							player1output.println("you");
							player2output.println("disable");
							player2output.println("val");
						}
						if(board.win().equals("X")) {
							gameOver = true;
							player1output.println("win");
							player2output.println("lose");
							
						}else if(board.win().equals("O")) {
							gameOver = true;
							player1output.println("lose");
							player2output.println("win");
							
						}else if(board.draw() == true) {
							gameOver = true;
							for (PrintWriter writer : writers)
								writer.println("draw");
						}
							
					}
					
					
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				// client disconnected
				if (output != null) {
					writers.remove(output);
				}
			}
		}
	}
}
