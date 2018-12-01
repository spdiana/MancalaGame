package com.example.model;

public enum Player {
	
	PLAYER_1 (7),
	PLAYER_2(0);

	private int pit;
	
	Player(int pit) {
		this.pit = pit;
	}

	public int getPit() {
        return this.pit;
    }
}