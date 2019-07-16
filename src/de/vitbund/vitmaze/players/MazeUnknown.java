package de.vitbund.vitmaze.players;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class MazeUnknown {
	private int laenge;
	private int breite;
	private int level;
	
	private Map<String,Feld> maze = new HashMap<String, Feld>();
	private Collection<Feld> moeglicheFelder;
	
	private Map<String, Feld> freieFelder = new HashMap<String, Feld>();

	public MazeUnknown(Scanner input) {
		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		setBreite(input.nextInt()); // X-Groesse des Spielfeldes (Breite)
		setLaenge(input.nextInt()); // Y-Groesse des Spielfeldes (Hoehe)
		setLevel(input.nextInt()); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		fillMaze();
	}

	public void fillMaze() {
		for (int i = 0; i < getBreite(); i++) {
			for (int j = 0; j < getLaenge(); j++) {
				Feld feld = new Feld(i, j, "", "", 99, false);
				String key = i+","+j;
				maze.put(key,feld);

			}
		}
	}

	public void moeglicheFelder(MortalComBot bot) {
		this.moeglicheFelder = new LinkedList<Feld>();
		for (Feld feld : bot.getNachbarFelder()) {
			if (!("WALL").equals(feld.getTyp())) {
				moeglicheFelder.add(feld);
			} else {
				feld.setTyp("WALL");
				maze.put(feld.getSchluessel(),feld);
			}
		}
	}

	public void naechstesFeld(MortalComBot bot) {
		if (moeglicheFelder.size() == 1) {
			for (Feld feld : moeglicheFelder) {
				bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
				bot.bewegeNach();
			}
		
		}
		
			for (Feld feld : moeglicheFelder) {
				if(feld.isBesucht() == false) {
					System.err.println("Feld XY" + feld.getSchluessel());
					bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
					bot.bewegeNach();
			}
			
		}
		
			//toDO:: 
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

	public Collection<Feld> getMoeglicheFelder() {
		return moeglicheFelder;
	}

	public void setMoeglicheFelder(Collection<Feld> moeglicheFelder) {
		this.moeglicheFelder = moeglicheFelder;
	}

	public Map<String, Feld> getFreieFelder() {
		return freieFelder;
	}

	public void setFreieFelder(Map<String, Feld> freieFelder) {
		this.freieFelder = freieFelder;
	}

	public Map<String, Feld> getMaze() {
		return maze;
	}

	public void setMaze(Map<String, Feld> maze) {
		this.maze = maze;
	}

}
