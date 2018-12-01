package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Player;

@Controller
public class WebController {

	@Autowired
	private GameController game;

	@GetMapping("/game")
	public String index(Model model) {
		model.addAttribute("currentPlayer", game.getCurrentPlayer().name());
		model.addAttribute("showBoard", game.getBoardGame());
		return "index";
	}

	@PostMapping("/result")                      
	public String saveDetails(@RequestParam("pitPosition") String pitPosition, ModelMap model) {
		String result = game.getCurrentPlayer().name();
		String message = "";

		if(!game.isGameOver()) {

			if( game.getCurrentPlayer() == Player.PLAYER_1 && !pitPosition.matches("\\b(1|2|3|4|5|6)\\b")) {
				message = "Enter a number between 1-6. :)";

			} else if( game.getCurrentPlayer() == Player.PLAYER_2 && !pitPosition.matches("\\b(8|9|10|11|12|13)\\b")) {
				message = "Enter a number between 8-13. :)";

			} else {
				result = game.playTurn(Integer.valueOf(pitPosition));
			}

		} else {
			result =  "The game Over. The winner is" + game.getWinner().name();
		}
		model.addAttribute("message", message);
		model.addAttribute("currentPlayer", result);
		model.addAttribute("showBoard", game.getBoardGame());
		return "index";       
	}

}
