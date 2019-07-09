package de.vitbund.vitmaze.players;

import java.util.Scanner;

public class MortalComBot {
	
	int playerId;
	int startX;
	int startY;
	
	
	public MortalComBot(Scanner input) {
		// 2. Zeile: Player Infos
		this.setPlayerId(input.nextInt()); // id dieses Players / Bots
		this.setStartX(input.nextInt()); // X-Koordinate der Startposition dieses Player
		this.setStartY(input.nextInt()); // Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	
	

}
