package naughtsAndCrosses;

import java.util.Scanner;

public class GameBoard 
{
	Scanner keyboard = new Scanner(System.in);
	
	private int rowLength = 5;
	private int colLength = 9;
	private int turnCount = 0;
	private String coordInput;
	private boolean isValid;
	private String rowInput;
	private String colInput;
	private int rowPos;
	private int colPos;
	private String boardInput;
	private char boardConversion;
	
	char[][] board = new char[rowLength][colLength];
	
	public void setInitialBoard()
	{
		board[0][0] = ' ';
		board[0][2] = 'a';
		board[0][4] = 'b';
		board[0][6] = 'c';
		board[1][0] = '3';
		board[2][0] = '2';
		board[3][0] = '1';
		board[4][2] = 'a';
		board[4][4] = 'b';
		board[4][6] = 'c';
		board[1][8] = '3';
		board[2][8] = '2';
		board[3][8] = '1';
		
		board[1][1] = '|';
		board[2][1] = '|';
		board[3][1] = '|';
		board[1][3] = '|';
		board[1][5] = '|';
		board[1][7] = '|';
		board[2][3] = '|';
		board[2][5] = '|';
		board[2][7] = '|';
		board[3][3] = '|';
		board[3][5] = '|';
		board[3][7] = '|';
	}
	
	/* ***no longer relevant***
	 * public void setDummyBoard() { board[1][2] = ' '; board[1][4] = ' ';
	 * board[1][6] = ' '; board[2][2] = ' '; board[2][4] = ' '; board[2][6] = ' ';
	 * board[3][2] = ' '; board[3][4] = ' '; board[3][6] = ' '; }
	 */
	
	public void printBoard()
	{
		System.out.println("Printing game board...");
		for(int i = 0; i < rowLength; i++)
		{
			for(int j = 0; j < colLength; j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println("Board printed.");
		
	}
	
	public void nextTurn()
	{
		while(turnCount < 9)
		{
			turnCount += 1;
			takePlayerInput();
			printBoard();
			checkWinCondition();
		}
		System.out.println("Game over man, game over.");
	}
	
	public void takePlayerInput()
	{
			isValid = false;
			System.out.println("It's your turn. Please type in a legal move in the form '[abc][123]', "
								+ "e.g. 'a1' then press enter.");
			while(isValid == false)	
			{
				coordInput = keyboard.nextLine();
				if(coordInput.matches("[abc][123]"))
				{
					System.out.println("Input confirmed.");
					isValid = true;
					
				}
				else
				{
					System.out.println("Try again:");
				}
			}
			checkBoard();
	}
	
	public void checkBoard()
	{
		colInput = coordInput.substring(0,1);
		rowInput = coordInput.substring(1);
		switch(rowInput)
		{
			case "1":
				rowPos = 3;
				break;
			case "2":
				rowPos = 2;
				break;
			case "3":
				rowPos = 1;
				break;
			default:
				rowPos = -1;
				System.out.println("Something went wrong in check board");
		}
		
		switch(colInput)
		{
			case "a":
				colPos = 2;
				break;
			case "b":
				colPos = 4;
				break;
			case "c":
				colPos = 6;
				break;
			default:
				colPos = -1;
				System.out.println("Something went wrong in check board");
		}
		System.out.println("Input checked: " + rowInput + colInput);
		
		if(board[rowPos][colPos] != 'X' || board[rowPos][colPos] != 'O')
		{
			System.out.println("Please enter either X or O, then press enter.");
			boardInput = keyboard.nextLine();
			boardConversion = boardInput.charAt(0);
			if(boardInput.matches("[XO]"))
			{
				board[rowPos][colPos] = boardConversion;
				System.out.println("Bomb has been planted.");
			}
		}
		else
		{
			System.out.println(board[rowPos][colPos]);
			System.out.println("That position is taken. Try again.");
			takePlayerInput();
		}
	}
	public void checkWinCondition()
	{
		boolean winCondition = false;
		int rowPos_1 = 3;
		int rowPos_2 = 2;
		int rowPos_3 = 1;
		int colPos_a = 2;
		int colPos_b = 4;
		int colPos_c = 6;

		char[] winArray1 = {board[rowPos_1][colPos_a], board[rowPos_2][colPos_a], board[rowPos_3][colPos_a]};
		char[] winArray2 = {board[rowPos_1][colPos_a], board[rowPos_2][colPos_b], board[rowPos_3][colPos_c]};
		char[] winArray3 = {board[rowPos_2][colPos_a], board[rowPos_2][colPos_b], board[rowPos_2][colPos_c]};
		char[] winArray4 = {board[rowPos_3][colPos_a], board[rowPos_2][colPos_b], board[rowPos_1][colPos_c]};
		char[] winArray5 = {board[rowPos_3][colPos_a], board[rowPos_3][colPos_b], board[rowPos_3][colPos_c]};
		char[] winArray6 = {board[rowPos_3][colPos_a], board[rowPos_3][colPos_b], board[rowPos_3][colPos_c]};
		char[] winArray7 = {board[rowPos_3][colPos_b], board[rowPos_2][colPos_b], board[rowPos_1][colPos_b]};
		char[] winArray8 = {board[rowPos_3][colPos_c], board[rowPos_2][colPos_c], board[rowPos_1][colPos_c]};
		
		char[][] winArrays = {winArray1, winArray2, winArray3, winArray4, winArray5, winArray6, winArray7,
								winArray8};
		
		for(char[] array : winArrays)
		{
			int XCount = 0;
			int OCount = 0;
			for(char move : array)
			{
				if(move == 'X')
				{
					XCount += 1;
				}
				if(move == 'O')
				{
					OCount += 1;
				}
			}
			if(XCount == 3 || OCount == 3)
			{
				winCondition = true;
			}
		}
		if(winCondition == true)
		{
			turnCount = 9;
			System.out.println("You WON!");
		}
	}
	
	
	public void run()
	{
		setInitialBoard();
		printBoard();
		nextTurn();
	}
}
