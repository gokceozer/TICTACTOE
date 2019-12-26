import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton button = new JButton("click me");
		frame.add(button);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
