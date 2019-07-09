package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MazeUnknown {
	int laenge;
	int breite;
	int level;
	Map<int[], String> maze = new HashMap<int[], String>();
	
	public MazeUnknown(Scanner input) {
		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		this.setBreite(input.nextInt()); // X-Groesse des Spielfeldes (Breite)
		this.setLaenge(input.nextInt()); // Y-Groesse des Spielfeldes (Hoehe)
		this.setLevel(input.nextInt()); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		fillMaze();
	}
	
	public void fillMaze() {
		for (int i = 0; i < getBreite(); i++) {
			for (int j = 0; j < getLaenge(); j++) {
				int[] k = {i,j};
				maze.put(k, "UNKNOWN");
			}
		}
	}
	
	public void setzeBot(MortalComBot bot) {
		int[] koordinaten = {bot.getStartX(),bot.getStartY()};
		maze.put(koordinaten,"FLOOR");
		setzeNachbarn(bot);
	}
	
	public String setzeNachbarn(MortalComBot bot) {
		if(bot.getEastCellStatus().equals("FLOOR")) {
			int[] east = {bot.getStartX()-1,bot.getStartY()};
		}
		if(bot.getEastCellStatus().equals("FLOOR")) {
			int[] east = {bot.getStartX()-1,bot.getStartY()};
		}
		return "";
	}

	public int getLaenge() {
		return laenge;
	}
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}
	public int getBreite() {
		return breite;
	}
	public void setBreite(int breite) {
		this.breite = breite;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
