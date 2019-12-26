//Initialize the server 
import java.io.IOException;
import java.net.ServerSocket;

public class T8Server {
	static Server myServer;
	public static void main(String[] args) throws IOException {
		System.out.println("Server is Running...");
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("Server Stopped.");
			}
		}));

		try (var listener = new ServerSocket(58901)) {
			myServer = new Server(listener);
			myServer.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Server getServer() {
		return myServer;
	}
	

}
