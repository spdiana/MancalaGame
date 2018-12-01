package com.example.demo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.BoardGameModel;
import com.example.model.Player;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BoardGameModel.class)
public class BolTestBoardGameTests {

	@Autowired
	private BoardGameModel boardGame;
	
	int[] boardGameOver = new int[14];

	@Before
	public void before() {
		boardGameOver[0] = 33; boardGameOver[1] = 0; boardGameOver[2] = 0; boardGameOver[3] = 0; boardGameOver[4] = 0; boardGameOver[5] = 0; boardGameOver[5] = 0;
		boardGameOver[7] = 25; boardGameOver[8] = 1; boardGameOver[9] = 4; boardGameOver[10] = 2; boardGameOver[11] = 4; boardGameOver[12] = 0; boardGameOver[13] = 3;
	}

	@After
	public void after() {
		//reset Board
		boardGameOver[0] = 0; boardGameOver[1] = 6; boardGameOver[2] = 6; boardGameOver[3] = 6; boardGameOver[4] = 6; boardGameOver[5] = 6; boardGameOver[5] = 6;
		boardGameOver[7] = 0; boardGameOver[8] = 6; boardGameOver[9] = 6; boardGameOver[10] = 6; boardGameOver[11] = 6; boardGameOver[12] = 6; boardGameOver[13] = 6;
	}

	@Test
	public void testPlayTurn() {
		int[] turnOne = new int[]{0, 0, 7, 7, 7, 7, 7,//top part of board 
				1, 6, 6, 6, 6, 6, 6};//bottom part of board

		int[] turnTwo = new int[]{0, 0, 0, 8, 8, 8, 8,//top part of board 
				2, 7, 7, 6, 6, 6, 6};//bottom part of board

		int[] turnThree = new int[]{1, 1, 0, 8, 8, 8, 8,//top part of board 
				2, 0, 8, 7, 7, 7, 7};//bottom part of board

		int[] turnFour = new int[]{1, 0, 0, 8, 8, 8, 8,//top part of board 
				10, 0, 8, 7, 7, 0, 7};//bottom part of board

		int[] turnFive = new int[]{2, 1, 1, 9, 8, 8, 8,//top part of board 
				10, 0, 0, 8, 8, 1, 8};//bottom part of board

		Player currentPlayer = Player.PLAYER_1;

		/**Turn One - The player1 select the pit position 1*/
		boardGame.moveStones(1);
		int[] arrayresultOne = boardGame.getBorder();
		currentPlayer = boardGame.getCurrentPlayer();

		assertArrayEquals(turnOne, arrayresultOne);
		//Next player is Player.PLAYER_1
		assertEquals(currentPlayer, Player.PLAYER_1);

		/**Turn Two - The player1 select the pit position 2*/
		boardGame.moveStones(2);
		int[] arrayresultTwo = boardGame.getBorder();
		currentPlayer = boardGame.getCurrentPlayer();

		assertArrayEquals(turnTwo, arrayresultTwo);

		//Next player is Player.PLAYER_2
		assertEquals(currentPlayer, Player.PLAYER_2);

		/**Turn Three - The player2 select the pit position 8 */
		boardGame.moveStones(8);
		int[] arrayresultThree = boardGame.getBorder();
		currentPlayer = boardGame.getCurrentPlayer();

		assertArrayEquals(turnThree, arrayresultThree);

		//Next player is Player.PLAYER_1
		assertEquals(currentPlayer, Player.PLAYER_1);

		/**Turn Four - The player1 select the pit position 1 */
		boardGame.moveStones(1);
		int[] arrayresultFour = boardGame.getBorder();
		currentPlayer = boardGame.getCurrentPlayer();

		assertArrayEquals(turnFour, arrayresultFour);

		//Next player is Player.PLAYER_2
		assertEquals(currentPlayer, Player.PLAYER_2);

		/**Turn Five - The player2 select the pit position 9 */
		boardGame.moveStones(9);
		int[] arrayresultFive = boardGame.getBorder();
		currentPlayer = boardGame.getCurrentPlayer();

		assertArrayEquals(turnFive, arrayresultFive);

		//Next player is Player.PLAYER_1
		assertEquals(currentPlayer, Player.PLAYER_1);
	}

	@Test
	public void testGameOverMethod() {
		boolean gameOver = boardGame.gameOver(boardGameOver);
		assertTrue(gameOver);
	}

	@Test
	public void testWinnerMethod() {
		Player winner = boardGame.checkWinner(boardGameOver);
		assertEquals(winner, Player.PLAYER_2);
	}


}
