import java.util.Scanner;

class Player extends Opponent{
	
	//Constructor
	Player (char symbol) {
		super(symbol);
	}
	
	//Methods
	void make_a_move(Board board) {
		Scanner input = new Scanner(System.in);
        String choice;  
        int r = 0, c = 0;
        
		boolean played = false;
		while (!played) {
			System.out.println("\nPLAYER's turn: ");
			choice = input.nextLine();
			
			if (choice.length() > 2) 
				System.out.println("Too long input.");
			else if (choice.length() == 2) {
				r = (int) choice.charAt(1);
				c = (int) choice.charAt(0);
			}
			else 
				System.out.println("Information missing.");
			
			if (board.valid_move(symbol, r, c)) 
				played = true;
			else
				System.out.println("--> INVALID MOVE. Insert again.");
		}
		
	}

}
