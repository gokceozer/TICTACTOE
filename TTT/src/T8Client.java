//Initialise GUI to pass to the controller
import javax.swing.SwingUtilities;

public class T8Client {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				makeGUI view = new makeGUI();
				Controller controller = new Controller(view);
				controller.start();
			}
		});
	}

}
