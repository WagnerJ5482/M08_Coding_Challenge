package de.vitbund.vitmaze.players;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MazeUnknown {
	int laenge;
	int breite;
	int level;
	Map<int[], String[]> maze = new HashMap<int[], String[]>();
	
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
				String[] s = {" ? ","","",""}; //1. Typ, 2.Kosten, 3.Nachbarn
				maze.put(k,s);
//				System.err.print(" ? ");
			}
		}
//			System.err.println();
	}
	
	public String[] level1Maze(String string) {
		String[] s = {string,"1","",""};
		return s;
	}


	public void setzeNachbarFelderInListe(MortalComBot bot) {
		int[] east = {bot.getCurrentX()+1,bot.getCurrentY()};
		if(("FLOOR").equals(bot.getEastCellStatus())) {
			maze.put(east,level1Maze("FLOOR"));
		} else maze.put(east,level1Maze("WALL"));
		int[] west = {bot.getCurrentX()-1,bot.getCurrentY()};
		if(("FLOOR").equals(bot.getWestCellStatus())) {
			maze.put(west,level1Maze("FLOOR"));
		} else maze.put(west,level1Maze("WALL"));
		int[] south = {bot.getCurrentX(),bot.getCurrentY()+1};
		if(("FLOOR").equals(bot.getSouthCellStatus())) {
			maze.put(south,level1Maze("FLOOR"));
		}else maze.put(south,level1Maze("WALL"));
		int[] north = {bot.getCurrentX(),bot.getCurrentY()-1};
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
