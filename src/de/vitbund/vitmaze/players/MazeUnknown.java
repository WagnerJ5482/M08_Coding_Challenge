package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
	
	public void setzeBotAufStartfeld(MortalComBot bot) {
		int[] startFeld = {bot.getCurrentX(),bot.getCurrentY()};
		maze.put(startFeld,"FLOOR");
		setzeNachbarFelderInListe(bot);
	}
	

	public void setzeNachbarFelderInListe(MortalComBot bot) {
		int[] east = {bot.getCurrentX()-1,bot.getCurrentY()};
		if(("FLOOR").equals(bot.getEastCellStatus())) {
			maze.put(east,"FLOOR");
		} else maze.put(east,"WALL");
		int[] west = {bot.getCurrentX()+1,bot.getCurrentY()};
		if(("FLOOR").equals(bot.getWestCellStatus())) {
			maze.put(west,"FLOOR");
		} else maze.put(west,"WALL");
		int[] south = {bot.getCurrentX(),bot.getCurrentY()+1};
		if(("FLOOR").equals(bot.getSouthCellStatus())) {
			maze.put(south,"FLOOR");
		}else maze.put(south,"WALL");
		int[] north = {bot.getCurrentX(),bot.getCurrentY()-1};
		if(("FLOOR").equals(bot.getNorthCellStatus())) {
			maze.put(north,"FLOOR");
		}else maze.put(north,"WALL");
	}

	public String bewegeBot(MortalComBot bot) {
		Map<int[], String> floorMaze = new HashMap<int[], String>();
		floorMaze.remove(floorMaze.containsValue("WALL"));
		for(String kachelArt : floorMaze.values()) {
			if(("FLOOR").equals(kachelArt)) {
				
			}
		}
		return " ";
		
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
