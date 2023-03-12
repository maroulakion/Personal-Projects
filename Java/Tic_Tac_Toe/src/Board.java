
public class Board {
	//Fields
	private int ROWS, COLUMNS;
	private final char EMPTY = ' ';
	private char[][] BOARD;
	private int WINNING_POINTS;
	
	//Getters
	public int getROWS() {
		return ROWS;
	}
	
	public int getCOLUMNS() {
		return COLUMNS;
	}
	
	public int getEMPTY() {
		return EMPTY;
	}
	
	public char[][] getBOARD() {
		return BOARD;
	}
	
	public char getBOARDvalue(int r, int c) {
		return BOARD[r][c];
	}
	
	public int getWINNING_POINTS() {
		return WINNING_POINTS;
	}
	
	//Setters
	public void setROWS(int r) {
		ROWS = r;
	}

	public void setCOLUMNS(int c) {
		COLUMNS = c;
	}
	
	public void setWINNING_POINTS(int win_at) {
		WINNING_POINTS = win_at;
	}
	
	public void setBOARD() {
		BOARD = new char[ROWS][COLUMNS];
	}
	

	//Constructor(s)	
	Board(int r, int c, int win_at) {
		setROWS(r);
		setCOLUMNS(c);
		setWINNING_POINTS(win_at);
		setBOARD();
		
		for (int i = 0; i<ROWS; i++) 
			for (int j = 0; j < COLUMNS; j++)
				BOARD[i][j] = EMPTY;
	}
	
	//Method(s)
	void print_board() {
		System.out.print("    ");
		int alphabet_letter = 'A'; 
		for (int j = 0; j < COLUMNS; j++) {
			System.out.print((char)alphabet_letter + "  ");
			alphabet_letter = 'A' + j + 1;
		}
		System.out.println("");
		for (int i = 0; i<ROWS; i++) {
			System.out.printf("%2d |", i+1);
			for (int j = 0; j < COLUMNS; j++)
				System.out.print(BOARD[i][j] + " |");
			System.out.println("");
		}
	}
	
	boolean full_board() {
		for (int i = 0; i < ROWS; i++) 
			for (int j = 0; j < COLUMNS; j++)
				if (BOARD[i][j] == EMPTY) 
					return false;
		return true;
	}
	
	
	boolean valid_move(char symbol, int r, int c) {
		//Check column's validity
		int i, j;
		boolean fine = false;
		for (j = 0; j < this.getCOLUMNS(); j++) 
			if (c == 'A' + j) {
				fine = true;
				break;
			}
		if (!fine) return false;
		
		//Check row's validity
		fine = false;
		for (i = 0; i < this.getROWS(); i++) 
			if (r == '1' + i)
				fine = true;
		if (!fine) return false;
		
		//Check BOARD's availability
		if (this.getBOARDvalue(r - '1', c - 'A') != this.getEMPTY()) 
			return false;
		
		//free space and valid move
		submit_move(symbol, r - '1', c - 'A');
		return true; 
		
	}
	
	private void submit_move(char symbol, int r, int c) {
		BOARD[r][c] = symbol;
	}
	
	boolean just_won(char symbol) {
		boolean found_winner;
		
		found_winner = check_sequency(symbol, WINNING_POINTS);
		return found_winner;
	}
	
	private boolean check_sequency(char symbol, int sequen) {
		//checks if {symbol} has {sequen} places in a row
		//e.g. check_sequency(player.symbol, 4) checks if player has a sequence of 4
		//     somewhere on board (horizontally, vertically, diagonally)
		
		boolean makes_a_sequen = false;
		int i, j;
		//check horizontally (right of BOARD[r][c])
		for (i = 0; i<ROWS; i++) 
			for (j = 0; j < COLUMNS - sequen + 1; j++) {
				makes_a_sequen = straight("horizontally", i, j, symbol, sequen);
				if (makes_a_sequen)  return true;
			}
			
			
		//check vertically (bellow of BOARD[r][c])
		if (!makes_a_sequen)
			for (i = 0; i<COLUMNS; i++) 
				for (j = 0; j < ROWS - sequen + 1; j++) {
					makes_a_sequen = straight("vertically", j, i, symbol, sequen);
					if (makes_a_sequen)  return true;
				}
				
		
		//check on a left diagonal (bellow and with columns number decreasing)
		if (!makes_a_sequen) 
			for ( i = 0; i < ROWS - sequen + 1; i++) 
		        for ( j = sequen - 1; j < COLUMNS;  j++) {
					makes_a_sequen = diagonal("on a left diagonal", i, j, symbol, sequen);
					if (makes_a_sequen)  return true;
				}
		        
		//check on a right diagonal (bellow and with columns number increasing)
	    if (!makes_a_sequen) 
	    	for ( i = 0; i < ROWS - sequen + 1; i++) 
		        for ( j = 0; j < COLUMNS - sequen + 1;  j++) {
					makes_a_sequen = diagonal("on a right diagonal", i, j, symbol, sequen);
			    	if (makes_a_sequen)  return true;
		        }
		        
	    return false;
	}
	
	private boolean straight(String direction, int r, int c, char symbol, int sequen) {
		int counter, sign_r, sign_c;
		
		if (direction.equals("horizontally")) {
			sign_r = 0; sign_c = 1;
		}
		else { //direction.equals("vertically") 
			sign_r = 1; sign_c = 0;
		}
		
		if (BOARD[r][c] == symbol) {
			counter = 1;
			while (counter < sequen) 
				if (BOARD[r][c] == BOARD[r + counter * sign_r][c + counter * sign_c])
					counter = counter + 1;
				else
					break;
			
			if (counter >= sequen) {
				return true;
			}
				
		}
		
		return false;
	}
	
	private boolean diagonal(String direction, int r, int c, char symbol, int sequen) {
        int step, counter;
        if (direction == "on a left diagonal")
        	step = -1;
        else  //direction.equals("on a right diagonal")
        	step = 1;
        
    	if (BOARD[r][c] == symbol) {
    		counter = 1;
    		while (counter < sequen) 
    			if (BOARD[r][c] != BOARD[r + counter][c + counter * step])
    				break;
    			else
    				counter = counter + 1;
    		
    		if (counter == sequen)
    			return true;
    	}
    	return false;
    }

	
}
