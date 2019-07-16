package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class MazeUnknown {
	private int laenge;
	private int breite;
	private int level;
	private Collection<Feld> maze = new ArrayList<Feld>();
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
				Feld k = new Feld(i, j, " ? ", "", 99, false);
				maze.add(k);
			}
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public void moeglicheFelder(MortalComBot bot) {
		this.moeglicheFelder = new LinkedList<Feld>();
		for (Feld feld : bot.getNachbarFelder()) {
			if (!("WALL").equals(feld.getTyp())) {
				moeglicheFelder.add(feld);
			} else
				feld.setTyp("WALL");
				freieFelder.put(feld.getSchluessel(),feld);
		}
	}

	public boolean naechstesFeld(MortalComBot bot) {
		if (moeglicheFelder.size() == 1) {
			for (Feld feld : moeglicheFelder) {
				bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
				return true;	
			}
			
//			System.out.println(bot.bewegeNach());
			break;
		
		List<Integer> minKosten = new ArrayList<Integer>();
		List<Feld> unbekannteFelder = new ArrayList<Feld>();
		Queue<Feld> gelaufenerWeg = new LinkedList<Feld>();

//		String[] randomFeld = new String[moeglicheFelder.size()];
//		int counterRandomFeld = 0;

		for (Feld feld : moeglicheFelder) {
			minKosten.add(feld.getWegeKosten());
		}

		for (Feld feld : moeglicheFelder) {
			System.err.println("Größe: "+moeglicheFelder.size());
			System.err.println("Feld : " + feld.getHimmelsrichtung() + ";" + feld.isBesucht() + ";"+feld.getSchluessel());
			
			}else {
			if ((feld.isBesucht() == false)) { // && (feld.getWegeKosten()==Collections.min(minKosten))
				unbekannteFelder.add(feld);
			}
			if ((feld.isBesucht() == true)) {
				System.err.println("Da war ich schon");
				gelaufenerWeg.add(feld);
			}
			}
		}
//		Collections.shuffle(unbekannteFelder);
//		bot.setzeNaechstesFeld(unbekannteFelder.get(0).getHimmelsrichtung());
//		System.out.println(bot.bewegeNach());
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

}
