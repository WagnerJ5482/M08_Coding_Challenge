package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Die Klasse MortalComBot steuert die Aktionen des Bots. Dazu erzeugt diese
 * Felder mit den notwendigen Information.
 * 
 * 
 * 
 * @author MortalKomBot
 *
 */
public class MortalComBot {

	private final int playerId;
	private boolean sbFound = false;
	private int currentY;
	private int currentX;
	private List<Formular> gesammelteFormulare = new ArrayList<Formular>();
	private Feld[] nachbarFelder = new Feld[4];
	private int nextForm = 0;
	private int maxForm;

	private Feld currentFeld;
	private Feld northFeld;
	private Feld eastFeld;
	private Feld southFeld;
	private Feld westFeld;

	private String lastActionsResult;
	private String currentCellStatus;
	private String northCellStatus;
	private String eastCellStatus;
	private String southCellStatus;
	private String westCellStatus;
	private String naechstesFeld;
	private String currentKey;

	private String northKey;
	private String eastKey;
	private String westKey;
	private String southKey;

	private int laenge;
	private int breite;

	/**
	 * Konstruktor der Klasse MortalComBot Es werden die Startposition sowie die
	 * Spieler Identifikationsnummer gesetzt.
	 * 
	 * @param input
	 */
	public MortalComBot(Scanner input) {
		// 2. Zeile: Player Infos
		this.playerId = input.nextInt(); // id dieses Players / Bots
		setCurrentX(input.nextInt());// X-Koordinate der Startposition dieses Player
		setCurrentY(input.nextInt());// Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile
	}

	/**
	 * Die Methode startFeldEinlesen erzeugt das aktuelle Feld zu Beginn des Spiels.
	 * 
	 * @param maze
	 */
	public void startFeldEinlesen(MazeUnknown maze) {
		this.currentFeld = maze.getMaze().get(getCurrentKey());
		this.currentFeld.setTyp("FLOOR");
		this.currentFeld.setHimmelsrichtung("middle");
		this.currentFeld.setBesucht(true);
		this.currentFeld.anzahlBesucheErhoeen(1);
		this.currentFeld.setKoordinaten(getCurrentKey(), maze);
		maze.getMaze().put(getCurrentFeld().getSchluessel(), getCurrentFeld());
		maze.getUnbekannteFelder().remove(this.currentFeld.getSchluessel());
		if (maze.getLevel() == 1)
			setMaxForm(0);
		else {
			setNextForm(1);
			setMaxForm(66);
		}

		this.laenge = maze.getLaenge();
		this.breite = maze.getBreite();
	}

	/**
	 * Die Methode felderEinlesen wird rundenweise ausgeführt und speichert die
	 * letzte Aktion sowie alle Felder, welche der Bot sieht.
	 * 
	 * @param input
	 */
	public void felderEinlesen(Scanner input) {

		setLastActionsResult(input.nextLine());
		setCurrentCellStatus(input.nextLine()); // der aktuelle Feld-Status;
		setNorthCellStatus(input.nextLine());
		setEastCellStatus(input.nextLine());
		setSouthCellStatus(input.nextLine());
		setWestCellStatus(input.nextLine());
	}

	/**
	 * Die Methode erzeugeBenachbarteFelder erstellt die sichtbaren Felder als
	 * Instanz der Klasse Feld und speichert diese in dem Labyrinth (maze) ab.
	 * Weiterhin werden die Felder dann in die Liste benachbarteFelder
	 * abgespeichert.
	 * 
	 * @param maze
	 */
	public void erzeugeBenachbarteFelder(MazeUnknown maze) {

		this.northFeld = (maze.getMaze().get(getNorthKey()));
		this.northFeld.setTyp(getNorthCellStatus());
		this.northFeld.setHimmelsrichtung("north");
		this.northFeld.setKoordinaten(getNorthKey(), maze);
		maze.getUnbekannteFelder().remove(this.northFeld.getSchluessel());
		maze.getMaze().put(getNorthFeld().getSchluessel(), getNorthFeld());

		this.eastFeld = (maze.getMaze().get(getEastKey()));
		this.eastFeld.setTyp(getEastCellStatus());
		this.eastFeld.setHimmelsrichtung("east");
		this.eastFeld.setKoordinaten(getEastKey(), maze);
		maze.getUnbekannteFelder().remove(this.eastFeld.getSchluessel());
		maze.getMaze().put(getEastFeld().getSchluessel(), getEastFeld());

		this.southFeld = (maze.getMaze().get(getSouthKey()));
		this.southFeld.setTyp(getSouthCellStatus());
		this.southFeld.setHimmelsrichtung("south");
		this.southFeld.setKoordinaten(getSouthKey(), maze);
		maze.getUnbekannteFelder().remove(this.southFeld.getSchluessel());
		maze.getMaze().put(getSouthFeld().getSchluessel(), getSouthFeld());

		this.westFeld = (maze.getMaze().get(getWestKey()));
		this.westFeld.setTyp(getWestCellStatus());
		this.westFeld.setHimmelsrichtung("west");
		this.westFeld.setKoordinaten(getWestKey(), maze);
		maze.getUnbekannteFelder().remove(this.westFeld.getSchluessel());
		maze.getMaze().put(getWestFeld().getSchluessel(), getWestFeld());

		fuelleNachbarFelder();
	}

	/**
	 * Die Methode setzeBot setzt in der nächsten Runde das Feld auf besucht bzw.
	 * erhöht die Anzahl der Besuche, von dem Feld der Bot kam. In der ersten Runde
	 * werden nur die benachbarten Felder erzeugt.
	 * 
	 * @param maze
	 */
	public void setzeBot(MazeUnknown maze) {
		switch (getLastActionsResult()) {
		case "OK":
			erzeugeBenachbarteFelder(maze);
			break;
		case "OK NORTH":
			getSouthFeld().setBesucht(true);
			getSouthFeld().anzahlBesucheErhoeen(1);
			break;
		case "OK WEST":
			getEastFeld().setBesucht(true);
			getEastFeld().anzahlBesucheErhoeen(1);
			break;
		case "OK SOUTH":
			getNorthFeld().setBesucht(true);
			getNorthFeld().anzahlBesucheErhoeen(1);
			break;
		case "OK EAST":
			getWestFeld().setBesucht(true);
			getWestFeld().anzahlBesucheErhoeen(1);
			break;
		default:
			break;
		}
	}

	/**
	 * Die Methode aktuellesFeldPruefen prüft, ob der Bot auf einem Formularfeld
	 * oder bei der*dem SB steht und gibt entsprechende Befehle (take zum Aufnehmen
	 * des Formulars oder finish zum Beenden des Spiels) aus.
	 * 
	 * @param maze
	 * @return boolean
	 */
	public boolean aktuellesFeldPruefen(MazeUnknown maze) {
		System.err.println("nextForm: " + getNextForm());
		System.err.println("maxForm: " + getMaxForm());
		System.err.println("currentCS: " + getCurrentCellStatus());

		if (getCurrentCellStatus().contains("FINISH " + getPlayerId() + " ")) {
			setMaxForm(ermittleMaxAnzahlFormulare(getCurrentCellStatus()));
			if (getNextForm() == getMaxForm()) {
				System.out.println("finish");
				return true;
			}
		}
		String formularFuerMich = "FORM " + getPlayerId() + " " + getNextForm();
		if (this.currentFeld.getTyp().equals(formularFuerMich)) {
			System.out.println("take");
			setNextForm((getNextForm() + 1));
			return false;
		}
		return false;
	}

	/**
	 * Die Methode ermittelt aus dem aktuellen CellStatus die maximale Anzahl der
	 * Formulare (keine Fehlerbehandlung bei Status "FLOOR").
	 * 
	 * @param currentCellStatus
	 * @return
	 */
	private int ermittleMaxAnzahlFormulare(String currentCellStatus) {
		String[] split = currentCellStatus.split(" ");
		return Integer.parseInt(split[2]);

	}

//	private void setzeAlleFelderAufUnbesucht(MazeUnknown maze) {
//		for (String key: maze.getFreieFelder().keySet()) {
//			Feld feld = maze.getFreieFelder().get(key);
//			feld.setBesucht(false);
//		}
//	}

	/**
	 * Die Methode das Array Nachbarfelder mit den Felder (N, S, O, W).
	 * 
	 */
	public void fuelleNachbarFelder() {
		nachbarFelder[0] = getNorthFeld();
		nachbarFelder[1] = getEastFeld();
		nachbarFelder[2] = getSouthFeld();
		nachbarFelder[3] = getWestFeld();
	}

	/**
	 * Die Methode sucht in den Nachbarfeldern nach dem SB und setzt die
	 * Himmelsrichtung für die nächste Runde
	 * 
	 * @return boolean
	 */
	public boolean sucheSB() {
		for (Feld nachbarFeld : getNachbarFelder()) {
			if (nachbarFeld.getTyp().contains("FINISH " + getPlayerId() + " ")) {
				setzeNaechstesFeld(nachbarFeld.getHimmelsrichtung());
				return true;
			}
		}
		return false;
	}

	/**
	 * sucht in den Nachbarfeldern nach Formularen und setzt die Himmelsrichtung für
	 * die nächste Runde
	 * 
	 * @return boolean
	 */
	public boolean sucheFormular() {
		for (Feld nachbarFeld : getNachbarFelder()) {
			if (nachbarFeld.getTyp().equals("FORM " + getPlayerId() + " " + getNextForm())) {
				setzeNaechstesFeld(nachbarFeld.getHimmelsrichtung());
				return true;
			}
		}
		return false;
	}

	/**
	 * Die Methode entscheidet anhand der übergebenen Himmelsrichtung
	 * (getNaechstesFeld) welche Koordinaten das aktuelle Feld haben soll. Dadurch
	 * bewegt sich der Bot auf dem Spielfeld.
	 */
	public void bewegeNach() {
		switch (getNaechstesFeld()) {
		case "north":
			setCurrentX(getNorthFeld().getxKoordinate());
			setCurrentY(getNorthFeld().getyKoordinate());
			break;
		case "east":
			setCurrentX(getEastFeld().getxKoordinate());
			setCurrentY(getEastFeld().getyKoordinate());
			break;
		case "south":
			setCurrentX(getSouthFeld().getxKoordinate());
			setCurrentY(getSouthFeld().getyKoordinate());
			break;
		case "west":
			setCurrentX(getWestFeld().getxKoordinate());
			setCurrentY(getWestFeld().getyKoordinate());
			break;
		default:
		}
		System.out.println("go " + getNaechstesFeld());
	}

	/**
	 * Standard getter für PlayerId (kein setter, da final)
	 * 
	 * @return playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * Standard getter für LastActionResult
	 * 
	 * @return lastActionResult
	 */
	public String getLastActionsResult() {
		return lastActionsResult;
	}

	/**
	 * Standard setter für LastActionResult
	 * 
	 * @param lastActionsResult
	 */
	public void setLastActionsResult(String lastActionsResult) {
		this.lastActionsResult = lastActionsResult;
	}

	/**
	 * Standard getter für CurrentCellStatus
	 * 
	 * @return currentCellStatus
	 */
	public String getCurrentCellStatus() {
		return currentCellStatus;
	}

	/**
	 * Standard setter für CurrentCellStatus
	 * 
	 * @param currentCellStatus
	 */
	public void setCurrentCellStatus(String currentCellStatus) {
		this.currentCellStatus = currentCellStatus;
	}

	/**
	 * Standard getter für NorthCellStatus
	 * 
	 * @return northCellStatus
	 */
	public String getNorthCellStatus() {
		return northCellStatus;
	}

	/**
	 * Standard setter für NorthCellStatus
	 * 
	 * @param northCellStatus
	 */
	public void setNorthCellStatus(String northCellStatus) {
		this.northCellStatus = northCellStatus;
	}

	/**
	 * Standard getter für EastCellStatus
	 * 
	 * @return eastCellStatus
	 */
	public String getEastCellStatus() {
		return eastCellStatus;
	}

	/**
	 * Standard setter für EastCellStatus
	 * 
	 * @param eastCellStatus
	 */
	public void setEastCellStatus(String eastCellStatus) {
		this.eastCellStatus = eastCellStatus;
	}

	/**
	 * Standard getter für SouthCellStatus
	 * 
	 * @return southCellStatus
	 */
	public String getSouthCellStatus() {
		return southCellStatus;
	}

	/**
	 * Standard setter für SouthCellStatus
	 * 
	 * @return southCellStatus
	 */
	public void setSouthCellStatus(String southCellStatus) {
		this.southCellStatus = southCellStatus;
	}

	/**
	 * Standard getter für WestCellStatus
	 * 
	 * @return westCellStatus
	 */
	public String getWestCellStatus() {
		return westCellStatus;
	}

	/**
	 * Standard setter für WestCellStatus
	 * 
	 * @return westCellStatus
	 */
	public void setWestCellStatus(String westCellStatus) {
		this.westCellStatus = westCellStatus;
	}

	/**
	 * Standard getter für CurrentX
	 * 
	 * @return currentX
	 */
	public int getCurrentX() {
		return currentX;
	}

	/**
	 * Standard setter für CurrentX
	 * 
	 * @param currentX
	 */
	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	/**
	 * Standard getter für CurrrentY
	 * 
	 * @return currentY
	 */
	public int getCurrentY() {
		return currentY;
	}

	/**
	 * Standard setter für CurrentY
	 * 
	 * @param currentY
	 */
	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	/**
	 * Standard getter für CurrentFeld
	 * 
	 * @return currentFeld
	 */
	public Feld getCurrentFeld() {
		return currentFeld;
	}

	/**
	 * Standard setter für CurrentFeld
	 * 
	 * @param currentFeld
	 */
	public void setCurrentFeld(Feld currentFeld) {
		this.currentFeld = currentFeld;
	}

	/**
	 * Standard getter für NorthFeld
	 * 
	 * @return northFeld
	 */
	public Feld getNorthFeld() {
		return northFeld;
	}

	/**
	 * Standard setter für NorthFeld
	 * 
	 * @param northFeld
	 */
	public void setNorthFeld(Feld northFeld) {
		this.northFeld = northFeld;
	}

	/**
	 * Standard getter für EastFeld
	 * 
	 * @return eastFeld
	 */
	public Feld getEastFeld() {
		return eastFeld;
	}

	/**
	 * Standard setter für EastFeld
	 * 
	 * @param eastFeld
	 */
	public void setEastFeld(Feld eastFeld) {
		this.eastFeld = eastFeld;
	}

	/**
	 * Standard getter für SouthFeld
	 * 
	 * @return southFeld
	 */
	public Feld getSouthFeld() {
		return southFeld;
	}

	/**
	 * Standard setter für SouthFeld
	 * 
	 * @param southFeld
	 */
	public void setSouthFeld(Feld southFeld) {
		this.southFeld = southFeld;
	}

	/**
	 * Standard getter für WestFeld
	 * 
	 * @return westFeld
	 */
	public Feld getWestFeld() {
		return westFeld;
	}

	/**
	 * Standard setter für WestFeld
	 * 
	 * @param westFeld
	 */
	public void setWestFeld(Feld westFeld) {
		this.westFeld = westFeld;
	}

	/**
	 * Methode zum setzen der Himmelsrichtung des nächsten Feldes.
	 * 
	 * @param himmelsrichtung
	 */
	public void setzeNaechstesFeld(String himmelsrichtung) {
		this.naechstesFeld = himmelsrichtung;
	}

	/**
	 * Standard getter für NaechtesFeld
	 * 
	 * @return naechstesFeld
	 */
	public String getNaechstesFeld() {
		return naechstesFeld;
	}

	/**
	 * Standard setter für NaechstesFeld
	 * 
	 * @param naechstesFeld
	 */
	public void setNaechstesFeld(String naechstesFeld) {
		this.naechstesFeld = naechstesFeld;
	}

	/**
	 * Standard getter für NachbarFelder
	 * 
	 * @return nachbarFelder
	 */
	public Feld[] getNachbarFelder() {
		return nachbarFelder;
	}

	/**
	 * Standard getter für SBFound
	 * 
	 * @return sbFound
	 */
	public boolean isSbFound() {
		return sbFound;
	}

	/**
	 * Standard getter für CurrentKey
	 * 
	 * @return Schlüssel aus den aktuellen Koordinaten
	 */
	public String getCurrentKey() {
		return getCurrentX() + "," + getCurrentY();
	}

	/**
	 * Standard setter für CurrentKey
	 * 
	 * @param currentKey
	 */
	public void setCurrentKey(String currentKey) {
		this.currentKey = currentKey;
	}

	/**
	 * berechnet auf Grundlage der aktuellen Koordinaten die Nordposition unter
	 * Berücksichtigung der Spielgrenzen.
	 * 
	 * @return Schlüssel für die Nordposition
	 */
	public String getNorthKey() {
		if (getCurrentY() == 0)
			return (getCurrentX() + "," + (this.laenge - 1));
		else
			return getCurrentX() + "," + (getCurrentY() - 1);
	}

	/**
	 * Standard setter für NorthKey
	 * 
	 * @param northKey
	 */
	public void setNorthKey(String northKey) {
		this.northKey = northKey;
	}

	/**
	 * berechnet auf Grundlage der aktuellen Koordinaten die Ostposition unter
	 * Berücksichtigung der Spielgrenzen.
	 * 
	 * @return Schlüssel für die Ostposition
	 */
	public String getEastKey() {
		if (getCurrentX() == (this.breite - 1))
			return (0 + "," + getCurrentY());
		else
			return (getCurrentX() + 1) + "," + getCurrentY();
	}

	/**
	 * Standard setter für EastKey
	 * 
	 * @param eastKey
	 */
	public void setEastKey(String eastKey) {
		this.eastKey = eastKey;
	}

	/**
	 * berechnet auf Grundlage der aktuellen Koordinaten die Westposition unter
	 * Berücksichtigung der Spielgrenzen.
	 * 
	 * @return Schlüssel für die Westposition
	 */
	public String getWestKey() {
		if (getCurrentX() == 0)
			return (this.breite - 1 + "," + getCurrentY());
		return (getCurrentX() - 1) + "," + getCurrentY();
	}

	/**
	 * Standard setter für WestKey
	 * 
	 * @param westKey
	 */
	public void setWestKey(String westKey) {
		this.westKey = westKey;
	}

	/**
	 * berechnet auf Grundlage der aktuellen Koordinaten die Südposition unter
	 * Berücksichtigung der Spielgrenzen.
	 * 
	 * @return Schlüssel für die Südposition
	 */
	public String getSouthKey() {
		if (getCurrentY() == (this.laenge - 1))
			return (getCurrentX() + "," + 0);
		else
			return getCurrentX() + "," + (getCurrentY() + 1);
	}

	/**
	 * Standard setter für SouthKey
	 * 
	 * @param southKey
	 */
	public void setSouthKey(String southKey) {
		this.southKey = southKey;
	}

	/**
	 * Standard getter für AnzahlDokumente
	 * 
	 * @return gesammelteFormulare
	 */
	public List<Formular> getAnzahlDokumente() {
		return gesammelteFormulare;
	}

	/**
	 * Standard setter für AnzahlDokumente
	 * 
	 * @param anzahlDokumente
	 */
	public void setAnzahlDokumente(List<Formular> anzahlDokumente) {
		this.gesammelteFormulare = anzahlDokumente;
	}

	/**
	 * Standard getter für NextForm
	 * 
	 * @return nextForm
	 */
	public int getNextForm() {
		return nextForm;
	}

	/**
	 * Standard setter für NextForm
	 * 
	 * @param nextForm
	 */
	public void setNextForm(int nextForm) {
		this.nextForm = nextForm;
	}

	/**
	 * Standard getter für GesammlteFormulare
	 * 
	 * @return gesammelteFormulare
	 */
	public List<Formular> getGesammelteFormulare() {
		return gesammelteFormulare;
	}

	/**
	 * Standard setter für GesammelteFormulare
	 * 
	 * @param gesammelteFormulare
	 */
	public void setGesammelteFormulare(List<Formular> gesammelteFormulare) {
		this.gesammelteFormulare = gesammelteFormulare;
	}

	/**
	 * Standard getter für MaxForm
	 * 
	 * @return maxForm
	 */
	public int getMaxForm() {
		return maxForm;
	}

	/**
	 * Standard setter für MaxForm
	 * 
	 * @param maxForm
	 */
	public void setMaxForm(int maxForm) {
		this.maxForm = maxForm;
	}

}
