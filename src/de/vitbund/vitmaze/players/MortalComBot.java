package de.vitbund.vitmaze.players;

import java.util.Scanner;

public class MortalComBot {

	private final int playerId;
	private boolean sbFound = false;
	private int currentY;
	private int currentX;
	private int[] formulare = new int[100];
	private Feld[] nachbarFelder = new Feld[4];

	private Feld currentFeld;
	private Feld northFeld;
	private Feld eastFeld;
	private Feld southFeld;
	private Feld westFeld;

	private String lastActionsResult;
	//private String[] letzteHimmelsrichtung;
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

	public MortalComBot(Scanner input) {
		// 2. Zeile: Player Infos
		this.playerId = input.nextInt(); // id dieses Players / Bots
		setCurrentX(input.nextInt());// X-Koordinate der Startposition dieses Player
		setCurrentY(input.nextInt());// Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile
	}
	
	public void startFeldEinlesen(MazeUnknown maze) {
		this.currentFeld =maze.getMaze().get(getCurrentKey());
		this.currentFeld.setTyp(getCurrentCellStatus());
		this.currentFeld.setHimmelsrichtung("middle");
		this.currentFeld.setBesucht(true);
		this.currentFeld.setKoordinaten(getCurrentKey());
		maze.getMaze().put(getCurrentFeld().getSchluessel(), getCurrentFeld());
	}

	public void felderEinlesen(Scanner input) {

		setLastActionsResult(input.nextLine());
		setCurrentCellStatus(input.nextLine()); // der aktuelle Feld-Status;
		setNorthCellStatus(input.nextLine());
		setEastCellStatus(input.nextLine());
		setSouthCellStatus(input.nextLine());
		setWestCellStatus(input.nextLine());
	}

	public void erzeugeBenachbarteFelder(MazeUnknown maze) {

		this.northFeld = (maze.getMaze().get(getNorthKey()));
		this.northFeld.setTyp(getNorthCellStatus());
		this.northFeld.setHimmelsrichtung("north");
		this.northFeld.setKoordinaten(getNorthKey());
		maze.getMaze().put(getNorthFeld().getSchluessel(), getNorthFeld());

		this.eastFeld = (maze.getMaze().get(getEastKey()));
		this.eastFeld.setTyp(getEastCellStatus());
		this.eastFeld.setHimmelsrichtung("east");
		this.eastFeld.setKoordinaten(getEastKey());
		maze.getMaze().put(getEastFeld().getSchluessel(), getEastFeld());

		this.southFeld = (maze.getMaze().get(getSouthKey()));
		this.southFeld.setTyp(getSouthCellStatus());
		this.southFeld.setHimmelsrichtung("south");
		this.southFeld.setKoordinaten(getSouthKey());
		maze.getMaze().put(getSouthFeld().getSchluessel(), getSouthFeld());

		this.westFeld = (maze.getMaze().get(getWestKey()));
		this.westFeld.setTyp(getWestCellStatus());
		this.westFeld.setHimmelsrichtung("west");
		this.westFeld.setKoordinaten(getWestKey());
		maze.getMaze().put(getWestFeld().getSchluessel(), getWestFeld());

		fuelleNachbarFelder();
	}

	public void setzeBot(MazeUnknown maze) {
		switch (getLastActionsResult()) {
		case "OK": lastActionOK(maze);
			break;
		case "OK NORTH":
			getSouthFeld().setBesucht(true);
			break;
		case "OK WEST": 
			getEastFeld().setBesucht(true);
			break;
		case "OK SOUTH": 
			getNorthFeld().setBesucht(true);
			break;
		case "OK EAST":
			getWestFeld().setBesucht(true);
			break;
		default:
			break;
		}
	}
	
	private void lastActionOK(MazeUnknown maze) {
//		setCurrentFeld(maze.getMaze().get(getCurrentKey()));
//		getCurrentFeld().setTyp(currentCellStatus);
//		getCurrentFeld().setKoordinaten(getCurrentKey());
//		maze.getMaze().put(getCurrentKey(), getCurrentFeld());
//		System.err.println("Start: x;y:"+getCurrentKey());
		erzeugeBenachbarteFelder(maze);
	}
	
	private void lastActionNorth(MazeUnknown maze) {
		
//		maze.getMaze().put(getSouthFeld().getSchluessel(), getSouthFeld());
//		setCurrentFeld(maze.getMaze().get(getSouthKey()));
		System.err.println("x;y:"+getCurrentKey());
	}
	
	private void lastActionSouth(MazeUnknown maze) {
		
//		southFeld.setWegeKosten((southFeld.getWegeKosten()-1));
//		maze.getMaze().put(getNorthFeld().getSchluessel(), getNorthFeld());
//		setCurrentFeld(maze.getMaze().get(getNorthKey()));
		System.err.println("x;y:"+getCurrentKey());
	}
	
	private void lastActionWest(MazeUnknown maze) {
		
//		westFeld.setWegeKosten((westFeld.getWegeKosten()-1));
//		maze.getMaze().put(getEastFeld().getSchluessel(), getEastFeld());
//		setCurrentFeld(maze.getMaze().get(getEastKey()));
		System.err.println("x;y:"+getCurrentKey());
	}
	
	private void lastActionEast(MazeUnknown maze) {
		
//		eastFeld.setWegeKosten((eastFeld.getWegeKosten()-1));
//		maze.getMaze().put(getWestFeld().getSchluessel(), getWestFeld());
//		setCurrentFeld(maze.getMaze().get(getWestKey()));
		System.err.println("x;y:"+getCurrentKey());
	}

	public void fuelleNachbarFelder() {
		nachbarFelder[0] = getNorthFeld();
		nachbarFelder[1] = getEastFeld();
		nachbarFelder[2] = getSouthFeld();
		nachbarFelder[3] = getWestFeld();
	}

	public boolean sucheSB() {
		for (Feld nachbarFeld : getNachbarFelder()) {
			if (nachbarFeld.getTyp().equals(geheZuSachbearbeiter())) {
				setzeNaechstesFeld(nachbarFeld.getHimmelsrichtung());
				return true;
			}
		}
		return false;
	}

	public String geheZuSachbearbeiter() {
		String ausgabe = "FINISH " + this.getPlayerId() + " 0";
		return ausgabe;
	}

	public void bewegeNach() {
			switch (getNaechstesFeld()) {
			case "north":
				setCurrentX(getNorthFeld().getxKoordinate());
				setCurrentY(getNorthFeld().getyKoordinate());
//				System.out.println("go " + getNaechstesFeld());
				break;
			case "east":
				setCurrentX(getEastFeld().getxKoordinate());
				setCurrentY(getEastFeld().getyKoordinate());
//				System.out.println("go " + getNaechstesFeld());
				break;
			case "south":
				setCurrentX(getSouthFeld().getxKoordinate());
				setCurrentY(getSouthFeld().getyKoordinate());
//				System.out.println("go " + getNaechstesFeld());
				break;
			case "west":
				setCurrentX(getWestFeld().getxKoordinate());
				setCurrentY(getWestFeld().getyKoordinate());
//				System.out.println("go " + getNaechstesFeld());
				break;
			default:
			}
			System.out.println("go "+ getNaechstesFeld());
		}

	public int getPlayerId() {
		return playerId;
	}

	public String getLastActionsResult() {
		return lastActionsResult;
	}

	public void setLastActionsResult(String lastActionsResult) {
		this.lastActionsResult = lastActionsResult;
	}

	public String getCurrentCellStatus() {
		return currentCellStatus;
	}

	public void setCurrentCellStatus(String currentCellStatus) {
		this.currentCellStatus = currentCellStatus;
	}

	public String getNorthCellStatus() {
		return northCellStatus;
	}

	public void setNorthCellStatus(String northCellStatus) {
		this.northCellStatus = northCellStatus;
	}

	public String getEastCellStatus() {
		return eastCellStatus;
	}

	public void setEastCellStatus(String eastCellStatus) {
		this.eastCellStatus = eastCellStatus;
	}

	public String getSouthCellStatus() {
		return southCellStatus;
	}

	public void setSouthCellStatus(String southCellStatus) {
		this.southCellStatus = southCellStatus;
	}

	public String getWestCellStatus() {
		return westCellStatus;
	}

	public void setWestCellStatus(String westCellStatus) {
		this.westCellStatus = westCellStatus;
	}

	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	public Feld getCurrentFeld() {
		return currentFeld;
	}

	public void setCurrentFeld(Feld currentFeld) {
		this.currentFeld = currentFeld;
	}

	public Feld getNorthFeld() {
		return northFeld;
	}

	public void setNorthFeld(Feld northFeld) {
		this.northFeld = northFeld;
	}

	public Feld getEastFeld() {
		return eastFeld;
	}

	public void setEastFeld(Feld eastFeld) {
		this.eastFeld = eastFeld;
	}

	public Feld getSouthFeld() {
		return southFeld;
	}

	public void setSouthFeld(Feld southFeld) {
		this.southFeld = southFeld;
	}

	public Feld getWestFeld() {
		return westFeld;
	}

	public void setWestFeld(Feld westFeld) {
		this.westFeld = westFeld;
	}

	public int[] getFormulare() {
		return formulare;
	}

	public void setFormulare(int[] formulare) {
		this.formulare = formulare;
	}

	public void setzeNaechstesFeld(String himmelsrichtung) {
		this.naechstesFeld = himmelsrichtung;
	}

	public String getNaechstesFeld() {
		return naechstesFeld;
	}

	public Feld[] getNachbarFelder() {
		return nachbarFelder;
	}

	public void setNaechstesFeld(String naechstesFeld) {
		this.naechstesFeld = naechstesFeld;
	}

//	public String getLetzteHimmelsrichtung() {
//		letzteHimmelsrichtung = getLastActionsResult().split(" ");
//		return letzteHimmelsrichtung[2];
//	}

	public boolean isSbFound() {
		return sbFound;
	}

	public String getCurrentKey() {
		return getCurrentX() + "," + getCurrentY();
	}

	public void setCurrentKey(String currentKey) {
		this.currentKey = currentKey;
	}

	public String getNorthKey() {
		return getCurrentX() + "," + (getCurrentY() - 1);
	}

	public void setNorthKey(String northKey) {
		this.northKey = northKey;
	}

	public String getEastKey() {
		return (getCurrentX() + 1) + "," + getCurrentY();
	}

	public void setEastKey(String eastKey) {
		this.eastKey = eastKey;
	}

	public String getWestKey() {
		return (getCurrentX() - 1) + "," + getCurrentY();
	}

	public void setWestKey(String westKey) {
		this.westKey = westKey;
	}

	public String getSouthKey() {
		return getCurrentX() + "," + (getCurrentY() + 1);
	}

	public void setSouthKey(String southKey) {
		this.southKey = southKey;
	}

//	public void setLetzteHimmelsrichtung(String[] letzteHimmelsrichtung) {
//		this.letzteHimmelsrichtung = letzteHimmelsrichtung;
//	}
//
//	public void setCollNachbarFelder(Collection<Feld> collNachbarFelder) {
//		this.collNachbarFelder = collNachbarFelder;
//	}

}
