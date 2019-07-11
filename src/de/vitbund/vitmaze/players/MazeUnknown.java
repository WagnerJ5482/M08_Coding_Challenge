package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MazeUnknown {
	private int laenge;
	private int breite;
	private int level;
	private Collection<Feld> maze = new ArrayList<Feld>();
	
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
				Feld k = new Feld(i,j," ? ");
				maze.add(k);
			}
		}
	}


	public void setzeNachbarFelderInListe(MortalComBot bot) {
		if(("FLOOR").equals(bot.getEastCellStatus())) {
			maze.put(east,level1Maze("FLOOR"));
		} else maze.put(east,level1Maze("WALL"));
		if(("FLOOR").equals(bot.getWestCellStatus())) {
			maze.put(west,level1Maze("FLOOR"));
		} else maze.put(west,level1Maze("WALL"));
		if(("FLOOR").equals(bot.getSouthCellStatus())) {
			maze.put(south,level1Maze("FLOOR"));
		}else maze.put(south,level1Maze("WALL"));
		if(("FLOOR").equals(bot.getNorthCellStatus())) {
			maze.put(north,level1Maze("FLOOR"));
		}else maze.put(north,level1Maze("WALL"));
	}

	public String bewegeBot(MortalComBot bot) {
//		Map<int[], String> floorMaze = new HashMap<int[], String>();
		
		for(Entry<int[], String[]> entry: maze.entrySet()){
			if(entry.getValue().equals("FLOOR")) {
				System.err.println("X: "+entry.getKey()[0]);
				System.err.println("Y: "+entry.getKey()[1]);
				
			}
		}

		return "go west";
		
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
