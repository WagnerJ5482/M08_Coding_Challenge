package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * Die Klasse MazeUnknown repr�sentiert das Spielfeld auf dem der Bot entlang
 * laufen kann Dieses hat L�nge(H�he) , Breite und ein Level sowie Listen f�r
 * alle Felder, unbekannte Felder, m�gliche Felder und freie Felder. Die Liste
 * anzahlBesuche dient zur Ermittlung des n�chsten optimalen Feldes. Das
 * Formular repr�sentiert ein Objekt welches der Bot aufsammeln muss.
 * 
 * @author Mortal KomBot
 *
 */
public class MazeUnknown {

	private int laenge;
	private int breite;
	private int level;
	private List<Integer> anzahlBesuche;
	private Formular formular;

	private Map<String, Feld> maze = new HashMap<String, Feld>();
	private Map<String, Feld> unbekannteFelder = new HashMap<String, Feld>();
	private Collection<Feld> moeglicheFelder;

	private Map<String, Feld> freieFelder = new HashMap<String, Feld>();

	/**
	 * Konstruktor zum Erstellen des Spielfelde. Zus�tzlich wird die Map mit allen
	 * Feldern und den dazugeh�renden Schl�sseln initial gef�llt.
	 * 
	 * @param input Eingabe aus Spielumgebung zu Beginn
	 */
	public MazeUnknown(Scanner input) {
		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		setBreite(input.nextInt()); // X-Groesse des Spielfeldes (Breite)
		setLaenge(input.nextInt()); // Y-Groesse des Spielfeldes (Hoehe)
		setLevel(input.nextInt()); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		fillMaze();
	}

	/**
	 * Die Methode fillMaze f�llt die HashMap mit einem Feld und den dazu passenden
	 * Schl�ssel
	 */
	public void fillMaze() {
		for (int i = 0; i < getBreite(); i++) {
			for (int j = 0; j < getLaenge(); j++) {
				Feld feld = new Feld(i, j, "", "", 99, false, 0);
				maze.put(feld.getSchluessel(), feld);

			}
		}
		unbekannteFelder.putAll(maze);
	}

	/**
	 * Gibt ein Feld aus der HashMap durch Angabe von x und y Koordinaten zur�ck
	 * 
	 * @param x xKoordinate
	 * @param y yKoordinate
	 * @return feld
	 */
	public Feld getFeld(int x, int y) {
		String key = x + "," + y;
		return maze.get(key);
	}

	/**
	 * Die Methode moeglicheFelder ermittelt aus den Nachbarfeldern des Bots (N, S,
	 * O, W) alle Felder die nicht Wall sind (begehbar) und speichert diese in der
	 * ArrayList moeglicheFelder ab. Bei Feldern mit Typ Wall wird keine
	 * Himmelsrichtung und der Typ auf Wall gesetzt. Wall Felder werden in der
	 * Gesamtliste (maze) gespeichert.
	 * 
	 * @param bot
	 */
	public void moeglicheFelder(MortalComBot bot) {
		this.moeglicheFelder = new ArrayList<Feld>();
		for (Feld feld : bot.getNachbarFelder()) {
			if (!("WALL").equals(feld.getTyp())) {
				moeglicheFelder.add(maze.get(feld.getSchluessel()));
				if (feld.getTyp().contains("FORM " + bot.getPlayerId() + " ")) {
					String[] nrVomDokument = feld.getTyp().split(" ");
					setDokument(new Formular(Integer.parseInt(nrVomDokument[2]), feld));
					bot.getAnzahlDokumente().add(getDokument());
				}
			} else {
				feld.setHimmelsrichtung("");
				feld.setTyp("WALL");
				maze.put(feld.getSchluessel(), feld);
			}
		}
	}

	/**
	 * Die Methode naechstes Feld setzt die Himmelsrichtung f�r den n�chsten
	 * Spielzug des Bots. Sofern nur ein Feld m�glich ist, wird dessen
	 * Himmelsrichtung gesetzt und die Methode beendet. Ansonsten wird die
	 * Himmelsrichtung des Feldes gesetzt, welches entweder noch gar nicht oder am
	 * wenigsten besucht wurde.
	 * 
	 * @param bot
	 */
	public void naechstesFeld(MortalComBot bot) {
		if (moeglicheFelder.size() == 1) {
			for (Feld feld : moeglicheFelder) {
				bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
				return;
			}
		}

		this.anzahlBesuche = new ArrayList<Integer>();
		for (Feld feld : moeglicheFelder) {
			anzahlBesuche.add(feld.getAnzahlBesuche());
		}

		for (Feld feld : moeglicheFelder) {
			if (feld.isBesucht() == false) {
				bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
				return;
			} else if (feld.getAnzahlBesuche() <= Collections.min(anzahlBesuche)) {
				bot.setzeNaechstesFeld(feld.getHimmelsrichtung());
				return;
			}

		}
		return;

	}

	/**
	 * Standard getter f�r Laenge
	 * 
	 * @return laenge
	 */
	public int getLaenge() {
		return laenge;
	}

	/**
	 * Standard setter f�r Laenge
	 * 
	 * @param laenge
	 */
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	/**
	 * Standard getter f�r Breite
	 * 
	 * @return breite
	 */
	public int getBreite() {
		return breite;
	}

	/**
	 * Standard setter f�r Breite
	 * 
	 * @param breite
	 */
	public void setBreite(int breite) {
		this.breite = breite;
	}

	/**
	 * Standard getter f�r Level
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Standard setter f�r Level
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Standard getter f�r MoeglicheFelder
	 * 
	 * @return moeglicheFelder
	 */
	public Collection<Feld> getMoeglicheFelder() {
		return moeglicheFelder;
	}

	/**
	 * Standard setter f�r MoeglicheFelder
	 * 
	 * @param moeglicheFelder
	 */
	public void setMoeglicheFelder(Collection<Feld> moeglicheFelder) {
		this.moeglicheFelder = moeglicheFelder;
	}

	/**
	 * Standard getter f�r FreieFelder
	 * 
	 * @return freieFelder
	 */
	public Map<String, Feld> getFreieFelder() {
		return freieFelder;
	}

	/**
	 * Standard setter f�r FreieFelder
	 * 
	 * @param freieFelder
	 */
	public void setFreieFelder(Map<String, Feld> freieFelder) {
		this.freieFelder = freieFelder;
	}

	/**
	 * Standard getter f�r Maze
	 * 
	 * @return maze
	 */
	public Map<String, Feld> getMaze() {
		return maze;
	}

	/**
	 * Standard setter f�r Maze
	 * 
	 * @param maze
	 */
	public void setMaze(Map<String, Feld> maze) {
		this.maze = maze;
	}

	/**
	 * Standard getter f�r Dokument
	 * 
	 * @return formular
	 */
	public Formular getDokument() {
		return formular;
	}

	/**
	 * Standard setter f�r Dokument
	 * 
	 * @param formular
	 */
	public void setDokument(Formular formular) {
		this.formular = formular;
	}

	/**
	 * Standard getter f�r AnzahlBesuche
	 * 
	 * @return anzahlBesuche
	 */
	public List<Integer> getAnzahlBesuche() {
		return anzahlBesuche;
	}

	/**
	 * Standard setter f�r AnzahlBesuche
	 * 
	 * @param anzahlBesuche
	 */
	public void setAnzahlBesuche(List<Integer> anzahlBesuche) {
		this.anzahlBesuche = anzahlBesuche;
	}

	/**
	 * Standard getter f�r UnbekannteFelder
	 * 
	 * @return unbekannteFelder
	 */
	public Map<String, Feld> getUnbekannteFelder() {
		return unbekannteFelder;
	}

	/**
	 * Standard setter f�r Unbekannte Felder
	 * 
	 * @param unbekannteFelder
	 */
	public void setUnbekannteFelder(Map<String, Feld> unbekannteFelder) {
		this.unbekannteFelder = unbekannteFelder;
	}

}
