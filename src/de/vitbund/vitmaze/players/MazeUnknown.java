package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MazeUnknown {
	private int laenge;
	private int breite;
	private int level;
	private Collection<Feld> maze = new ArrayList<Feld>();
	private Map<String,Feld> freieFelder = new HashMap<String,Feld>();

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
				Feld k = new Feld(i, j, " ? ", "");
				maze.add(k);
			}
		}
	}

	public void fuegeFreieFelderInListe(MortalComBot bot) {
		for (Feld feld : bot.getCollNachbarFelder()) {
			if (!("WALL").equals(feld.getTyp())) {
					freieFelder.put(feld.getSchluessel(),feld);
			} else if (("FORM").equals(feld.getTyp())) {
					bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
				}
			}
	}

	public void sucheSB(MortalComBot bot) {
		for (Feld feld : bot.getCollNachbarFelder()) {
			if (feld.getTyp().equals(bot.pruefeSB())) {
				bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
				bot.bewegeBot();
			} else
				naechstesFeld(bot);
		}
	}

	public void naechstesFeld(MortalComBot bot) {
		List<String> keys = new ArrayList<String>();
		for (Feld feld : bot.getCollNachbarFelder()) {
			keys.add(feld.getSchluessel());
		}
		Collections.shuffle(keys);
//		bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
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

	public Map<String, Feld> getFreieFelder() {
		return freieFelder;
	}

}
