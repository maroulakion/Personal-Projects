import java.util.Random;

class Computer extends Opponent {
	
	//Constructor
	Computer(char symbol) {
		super(symbol);
	}

	//Methods
	void make_a_move(Board board) {
		System.out.println("\nCOMPUTER's turn: ");
		int r, c;
		String choice = new String();
		
		boolean played = false;
		boolean res;
		while (!played) {
			choice = "00";
			Random rand = new Random();
		    r = rand.nextInt(board.getROWS()) + '1';  
	        c = rand.nextInt(board.getCOLUMNS()) + 'A';
	        res = board.valid_move(symbol, r, c);
	        if (res)
				played = true;
			
		}
		
	}
		
}
