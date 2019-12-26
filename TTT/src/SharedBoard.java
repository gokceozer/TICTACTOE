
//The board object that will be shared among the two clients. Methods named "button$" are used to update the shared board object
public class SharedBoard {
	
	private final Object lock = new Object();
	String[] moves;

	SharedBoard() {
		
		moves = new String[9];
		
		for(int i=0; i<9; i++)
				moves[i] = "";
	}
	
	public void button0(String sign) {
		synchronized (lock) {
			moves[0] = sign;
		}
	}

	public void button1(String sign) {
		synchronized (lock) {
			moves[1] = sign;
		}
	}
	
	public void button2(String sign) {
		synchronized (lock) {
			moves[2] = sign;
		}
	}
	
	public void button3(String sign) {
		synchronized (lock) {
			moves[3] = sign;
		}
	}
	
	public void button4(String sign) {
		synchronized (lock) {
			moves[4] = sign;
		}
	}
	
	public void button5(String sign) {
		synchronized (lock) {
			moves[5] = sign;
		}
	}
	
	public void button6(String sign) {
		synchronized (lock) {
			moves[6] = sign;
		}
	}
	
	public void button7(String sign) {
		synchronized (lock) {
			moves[7] = sign;
		}
	}
	
	public void button8(String sign) {
		synchronized (lock) {
			moves[8] = sign;
		}
	}
	//Winning condition
	public String win() {
		if(moves[0] ==  moves[1] && moves[1] == moves[2] && !moves[0].isEmpty()) {
			System.out.println(moves[1]);
			return moves[0];
		}else if(moves[0] == moves[3] && moves[3] == moves[6]&& !moves[0].isEmpty()){
			return moves[0];
		}else if(moves[2] == moves[5] && moves[5] == moves[8]&& !moves[0].isEmpty()) {
			return moves[2];
		}else if(moves[6] == moves[7] && moves[7] == moves[8]&& !moves[0].isEmpty()) {
			return moves[6];
		}else if(moves[0] == moves[4] && moves[4] == moves[8]&& !moves[0].isEmpty()) {
			return moves[0];
		}else if(moves[2] == moves[4] && moves[4] == moves[6]&& !moves[0].isEmpty()) {
			return moves[2];
		}else if(moves[1] == moves[4] && moves[4] == moves[7]&& !moves[0].isEmpty()) {
			return moves[1];
		}else if(moves[3] == moves[4] && moves[4] == moves[5]&& !moves[0].isEmpty()) {
			return moves[3];
		}
		
		return "";
	
	}
	//Draw condition
	public boolean draw() {
		
		for(int i=0; i<9; i++) {
			if(moves[i] == "")
				return false;
		}
		return true;
	}
	
	

}
