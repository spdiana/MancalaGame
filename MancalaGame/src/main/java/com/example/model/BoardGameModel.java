package com.example.model;
import org.springframework.stereotype.Component;

@Component
public class BoardGameModel {

	private final int TOTAL_PITS = 14;
	private final int INITIAL_STONES = 6; 
	private Player currentPlayer;
	private Player winner;
	private int[] boardGame;
	boolean isGameOver = false;


	public BoardGameModel() {
		initGame();
	}

	private void initGame() {
		currentPlayer = Player.PLAYER_1;
		boardGame = new int[]{0, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES,//top part of board 
							  0, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES, INITIAL_STONES};//bottom part of board

	}


	public void moveStones(int selectPositionPit) {

		int totalStones = boardGame[selectPositionPit];
		int position = selectPositionPit;

		boardGame[selectPositionPit] = 0;

		while (totalStones > 0)  {
			position++;

			if (position >= TOTAL_PITS) {
				position = 0;
			}


			boardGame[position]++;
			totalStones--;
		}


		//The last stone lands in an own empty pit, the player captures his own stone and all stones in the
		//opposite pit.
		if(position != 0 && position != 7) {
			int oppositePit = TOTAL_PITS - position; 

			if (getPlayerDirection(position) == getCurrentPlayer() && boardGame[position] == 1 && boardGame[oppositePit] > 0 ) {
				int sumStones = boardGame[position] + boardGame[oppositePit];
				boardGame[position] = boardGame[oppositePit] = 0;
				boardGame[currentPlayer.getPit()] += sumStones;
			}
		} 
		
		if(position == Player.PLAYER_1.getPit()) {
			currentPlayer = Player.PLAYER_1;
		} else if (position == Player.PLAYER_2.getPit()) {
			currentPlayer = Player.PLAYER_2;
		} else {
			if (getCurrentPlayer() == Player.PLAYER_1) {
				currentPlayer = Player.PLAYER_2;
			} else {
				currentPlayer = Player.PLAYER_1;
			}
		}

		if(gameOver(boardGame)) {
			winner = checkWinner(getBorder());
		}
	}



	private Player getPlayerDirection(int position) {
		if (position >= 1 && position < Player.PLAYER_1.getPit()) {
			return Player.PLAYER_1;
		} else {
			return Player.PLAYER_2;
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public int[] getBorder() {
		return boardGame;
	}
	
	//Added to Unit Test
	public void setBoardGame(int[] boardGame) {
		this.boardGame = boardGame;
	}

	public boolean gameOver(int[] board) {
		int numStonesPlayerOne = 0;
		int numStonesPlayerTwo = 0;
		isGameOver = false;

		for (int i = 0; i < TOTAL_PITS; i++) {
			if(i != Player.PLAYER_1.getPit() && i != Player.PLAYER_2.getPit()) {
				if (i <= 6 ) {
					numStonesPlayerOne += board[i];
				} else {
					numStonesPlayerTwo += board[i];
				}
			}
		}
		if (numStonesPlayerOne == 0 || numStonesPlayerTwo == 0) {
			isGameOver = true;
		}
		return isGameOver;
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}

	public Player checkWinner(int[] board) {
		Player winner;

		for (int i = 0; i < TOTAL_PITS; i++) {
			if(i != Player.PLAYER_1.getPit() && i != Player.PLAYER_2.getPit()) {
				if (i <= 6 ) {
					boardGame[Player.PLAYER_1.getPit()] += board[i];
					boardGame[i] = 0;
				} else {
					boardGame[Player.PLAYER_2.getPit()] += board[i];
					boardGame[i] = 0;
				}
			}
		}	

		if(board[Player.PLAYER_1.getPit()] > board[Player.PLAYER_2.getPit()]) {
			winner = Player.PLAYER_1;
		} else {
			winner = Player.PLAYER_2;
		}
		return winner;
	}

	public  void printBoard() {
		for(int i = 0; i < 7; i ++) {
			System.out.print( boardGame[i]+ "  ");
		}
		System.out.println();
		for(int i = boardGame.length -1; i > 6; i--) {
			System.out.print("  "+ boardGame[i]+ "");
		}
	}



}
