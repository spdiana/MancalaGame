package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.example.controller", "com.example.model"})
@SpringBootApplication
public class BolTestWebApplication /**implements CommandLineRunner*/ {

	//Run using console
	/**
	@Autowired
	private BoardGameModel game;

	@Override
	public void run(String... args) throws Exception {
		while (!game.isGameOver()) {
			 Scanner sc = new Scanner(System.in);
			 System.out.println("\nYour turn: "+ game.getCurrentPlayer().name());
			 System.out.println("Enter the pit position: ");
			 int pitPosition = sc.nextInt();
			 game.moveStones(pitPosition);

			 game.printBoard();
			 System.out.println("\n-----------\n");

		}

	} */

	public static void main(String[] args) {
		SpringApplication.run(BolTestWebApplication.class, args);
	}
}
