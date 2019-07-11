package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

public class MazeUnknown {
	private int laenge;
	private int breite;
	private int level;
	private Collection<Feld> maze = new ArrayList<Feld>();
	private ArrayList<Feld> freieFelder = new ArrayList<Feld>();

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

	public String fuegeFreieFelderInListe(MortalComBot bot) {
		for (Feld feld : bot.fuelleNachbarFelder()) {
			if (bot.pruefeSB().equals(feld.getTyp())) {
				return bot.bewegeBot(feld.getHimmelsrichtung());
			} else if (("FLOOR").equals(feld.getTyp())) {
				this.freieFelder.add(feld);
				return "";
			}else if (("FORM").equals(feld.getTyp())){
				return bot.bewegeBot(feld.getHimmelsrichtung());
			}
		}
		return "";

	}
	
	public String bewegeBot(MortalComBot bot) {
		Random zufall = new Random();
		Feld zufallFeld = (freieFelder).get(zufall.nextInt(freieFelder.size()));// ToDO: Erklärung!!!
		return bot.bewegeBot(zufallFeld.getHimmelsrichtung());
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
	public void setFreieFelder(ArrayList<Feld> freieFelder) {
		this.freieFelder = freieFelder;
	}

}
