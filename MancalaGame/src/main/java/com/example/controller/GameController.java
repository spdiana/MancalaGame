package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.BoardGameModel;
import com.example.model.Player;

@Component
public class GameController {

	@Autowired
	private BoardGameModel game;


	public  int[] getBoardGame() {
		return game.getBorder();
	}

	public String playTurn(int selectPositionPit) {
		String result = null;
		if(game.gameOver(getBoardGame() )) {
			result = "Game Over: The winner is" + game.getWinner().name();  
		} else {
			game.moveStones(selectPositionPit);
			result = game.getCurrentPlayer().name();
		}
		return result;
	}

	public Player getCurrentPlayer() {
		return game.getCurrentPlayer();
	}

	public boolean isGameOver() {
		return game.isGameOver();
	}
	
	public Player getWinner() {
		return game.getWinner();
	}
}
