import java.util.Scanner;
import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		game_intro();
		
		//EXTRA #1: WORKS FOR MULTIPLE ROWS, COLUMN SIZES
		//e.g. create board with 6 rows and 7 columns
		//EXTRA #2: WORKS FOR MULTIPLE WINNING POINTS
		//e.g. end game when 5 winning points are scored
		//EXTRA #3: CHOSE TO RACE AGAINST COMPUTER OR PHYSICAL PLAYER
		//e.g. let computer decide next move, or let a friend play
		
		int ROWS = 3, COLUMNS = 3, WINNING_POINTS = 3;
		Board board = new Board(ROWS, COLUMNS, WINNING_POINTS);
		board = change_default_parameters(board, ROWS, COLUMNS, WINNING_POINTS);
		String op_choice = choose_opponent();
		
		Player player = new Player('X');
		Opponent opponent;
		
		if (op_choice.equals("COMPUTER"))
			opponent = new Computer('O');
		else
			opponent = new Player('O');
		
		String winner_name = "None";
		boolean found_winner = false;
		
		System.out.println("\n- - - - - - - - - - -  ");
		System.out.println("  LET THE GAME BEGIN!  ");
		System.out.println("- - - - - - - - - - -  \n");
		board.print_board();
		while (!board.full_board() && !found_winner) {
			player.make_a_move(board);
			if (board.just_won(player.getSymbol())) {
				winner_name = "PLAYER";
				found_winner = true;
			}
			else 
				if (!board.full_board()) {
					if (op_choice != "COMPUTER")
						board.print_board();
					opponent.make_a_move(board, op_choice);
					board.print_board();
					if (board.just_won(opponent.getSymbol())) {
						winner_name = op_choice;
						found_winner = true;
					}
				}				
		}
		
		board.print_board();
		print_winner(winner_name);
		game_outro();
	}
	
	static void game_outro() {
		System.out.println("\n\n- - - -  ");
		System.out.println("Thanks for playing!\n\n");
		System.out.println("********************");
		System.out.println("* Maroun, Nov.2022 *");
		System.out.println("********************");
	}
	
	static void game_intro() {
		System.out.println("~~~~~~~~~~~~~~");
		System.out.println(" TIC TAC TOE!");
		System.out.println("~~~~~~~~~~~~~~\n");
	}
	
	static void print_winner(String winner_name) {
		System.out.println("\n~~~~~~~~~~~~~~");
		System.out.println(" END OF GAME!");
		System.out.println("~~~~~~~~~~~~~~\n");
		if (winner_name == "None")
			System.out.println("No winner   :(");
		else 
			System.out.println(winner_name + "  WON!!!");
	}
	
	static String choose_opponent() {
		System.out.println("\n- - - - - - - - - - -  ");
		System.out.println("   CHOOSE OPPONENT  ");
		System.out.println("- - - - - - - - - - -  ");
		System.out.println("Default opponent: ");
		System.out.println("-> Computer ");
		System.out.println("Do you wish to race against a physical player? (YES/NO)");
		
		Scanner input = new Scanner(System.in);
		String answer = "Non declared";
		boolean valid = false;
		while (!valid) {
			
			answer = input.nextLine();
			if (!(answer.equals("yes") || answer.equals("YES") || answer.equals("no") || answer.equals("NO")))
				System.out.println("   Invalid answer. Type 'YES' or 'NO'");
			else
				valid = true;
		}
		
		System.out.println("\n");
		if (answer.equals("yes") || answer.equals("YES")) 
			return "2nd PLAYER";
		return "COMPUTER";
	}
	
	static Board change_default_parameters(Board board, int ROWS, int COLUMNS, int WINNING_POINTS){
		//RANGING (GENERAL RULES)
		//MAX_WIN indicates how many symbols in a sequence are required to win
		//     its range of value must be 2 <= MAX_WIN <= MAX_COLUMNS
		//MAX_ROWS, MAX_COLUMNS must be at max a 2-digit positive number 
		//     its range of values must be 2 <= MAX_ROWS <= 99 and 2 <= MAX_COLUMNS <= 99
		
		
		//DEFAULT RANGING (programmer shall change it based on general rules)
		int MIN_ROWS = 2, MIN_COLUMNS = 2, MIN_WIN = 2;
		int MAX_ROWS = 20, MAX_COLUMNS = 15, MAX_WIN = MAX_COLUMNS; 
		System.out.println("\n- - - - - - - - - - -  ");
		System.out.println("   GAME DIMENSIONS  ");
		System.out.println("- - - - - - - - - - -  ");
		System.out.println("Default values: ");
		System.out.println("-> ROWS = " + ROWS + "\n-> COLUMNS = " + COLUMNS + "\n-> WINNING POINTS = " + WINNING_POINTS);
		System.out.println("Do you wish to change any of these parameters? (YES/NO)");
		
		Scanner input = new Scanner(System.in);
		String answer = "Non declared";
		boolean valid = false;
		while (!valid) {
			
			answer = input.nextLine();
			if (!(answer.equals("yes") || answer.equals("YES") || answer.equals("no") || answer.equals("NO")))
				System.out.println("   Invalid answer. Type 'YES' or 'NO'");
			else
				valid = true;
		}
		
		if (answer.equals("yes") || answer.equals("YES")) {
			ROWS = set_parameter("ROWS", MIN_ROWS, MAX_ROWS);
			COLUMNS = set_parameter("COLUMNS", MIN_COLUMNS, MAX_COLUMNS);
			WINNING_POINTS = set_parameter("WINNING POINTS", MIN_WIN, MAX_WIN);
			board = new Board(ROWS, COLUMNS, WINNING_POINTS);
		}
		System.out.println("\n");
		return board;
	}
	
	static int set_parameter(String param, int MIN, int MAX) {
		Scanner input = new Scanner(System.in);
		int num = 2;
		String next;
		boolean valid = false, in_limits = false;
		
		System.out.print("Set number of " + param + ": ");
		while (!in_limits) {
			while (!valid) {
				try {
					num = input.nextInt();
					valid = true;
				}
				catch (Exception e) {
					next = input.nextLine();
					System.out.println("    Invalid integer input. Type a number in range [" + MIN + " , " + MAX + "]");
				}
			}
			if (!(num>=MIN && num<=MAX)) {
				System.out.println("    Out of bounds! Type a number in range [" + MIN + " , " + MAX + "]");
				valid = false;
			}
			else
				in_limits = true;
		}
		return num;
	}

	

}



