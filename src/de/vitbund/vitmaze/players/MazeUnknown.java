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
	private Collection<Feld> freieFelder = new ArrayList<Feld>();
	
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
				Feld k = new Feld(i,j," ? ");
				maze.add(k);
			}
		}
	}


	public void fuegeFreieFelderInListe(MortalComBot bot) {
		String s = "FINISH "+bot.getPlayerId()+ " "+bot.getFormulare();
		if(("FLOOR").equals(bot.getNorthFeld().getTyp())) {
			freieFelder.add(bot.getNorthFeld());
		}
		if(("FLOOR").equals(bot.getWestFeld().getTyp())) {
			freieFelder.add(bot.getWestFeld());
		} 
		if(("FLOOR").equals(bot.getSouthFeld().getTyp())) {
			freieFelder.add(bot.getSouthFeld());
		}
		if(("FLOOR").equals(bot.getEastFeld().getTyp())) {
			freieFelder.add(bot.getEastFeld());
		}
		if(s.equals(bot.getNorthFeld().getTyp())) {
			freieFelder.add(bot.getNorthFeld());
		}
		if(("FLOOR").equals(bot.getWestFeld().getTyp())) {
			freieFelder.add(bot.getWestFeld());
		} 
		if(("FLOOR").equals(bot.getSouthFeld().getTyp())) {
			freieFelder.add(bot.getSouthFeld());
		}
		if(("FLOOR").equals(bot.getEastFeld().getTyp())) {
			freieFelder.add(bot.getEastFeld());
		}
		if()
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

	public Collection<Feld> getFreieFelder() {
		return freieFelder;
	}

	public void setFreieFelder(Collection<Feld> freieFelder) {
		this.freieFelder = freieFelder;
	}

}
