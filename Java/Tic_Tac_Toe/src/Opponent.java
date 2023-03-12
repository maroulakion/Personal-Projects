
public class Opponent {
	char symbol;
	
	//Getters
	public char getSymbol( ) {
		return symbol;
	}
	
	//Setters
	public void setSymbol(char symbol){
		this.symbol = symbol;
	}
	
	//Constructor
	Opponent (char symbol) {
		setSymbol(symbol);
	}
	
	//Methods
	public void make_a_move(Board board, String op) {
		Computer computer = new Computer(symbol);
		Player player = new Player(symbol);
		
		if (op.equals("COMPUTER")) 
			computer.make_a_move(board);
		else
			player.make_a_move(board);
	}
}


