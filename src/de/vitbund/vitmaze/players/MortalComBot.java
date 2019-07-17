package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class MortalComBot {

	private final int playerId;
	private boolean sbFound = false;
	private int currentY;
	private int currentX;
	private int[] formulare = new int[100];
	private Collection<Feld> nachbarFelder;

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
		System.err.println(getCurrentKey());
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
		System.err.println(getCurrentKey());

		setNorthFeld(maze.getMaze().get(getNorthKey()));
		this.northFeld.setTyp(getNorthCellStatus());
		this.northFeld.setHimmelsrichtung("north");
		this.northFeld.setKoordinaten(getNorthKey());
		maze.getMaze().put(getNorthFeld().getSchluessel(), getNorthFeld());

		setEastFeld(maze.getMaze().get(getEastKey()));
		this.eastFeld.setTyp(getEastCellStatus());
		this.eastFeld.setHimmelsrichtung("east");
		this.eastFeld.setKoordinaten(getEastKey());
		maze.getMaze().put(getEastFeld().getSchluessel(), getEastFeld());

		setSouthFeld(maze.getMaze().get(getSouthKey()));
		this.southFeld.setTyp(getSouthCellStatus());
		this.southFeld.setHimmelsrichtung("south");
		this.southFeld.setKoordinaten(getSouthKey());
		maze.getMaze().put(getSouthFeld().getSchluessel(), getSouthFeld());

		setWestFeld(maze.getMaze().get(getWestKey()));
		this.westFeld.setTyp(getWestCellStatus());
		this.westFeld.setHimmelsrichtung("west");
		this.westFeld.setHimmelsrichtung(getWestKey());
		maze.getMaze().put(getWestFeld().getSchluessel(), getWestFeld());

//		if (maze.getFreieFelder().containsKey(northKey)==true) setNorthFeld(maze.getFreieFelder().get(northKey));
//		else setNorthFeld(new Feld((getCurrentX()),(getCurrentY()-1),getNorthCellStatus(),"north",1,false));
//		
//		if (maze.getFreieFelder().containsKey(eastKey)==true) setEastFeld(maze.getFreieFelder().get(eastKey));
//		else setEastFeld(new Feld((getCurrentX()+1),getCurrentY(),getEastCellStatus(),"east",1,false));
//		
//		if (maze.getFreieFelder().containsKey(southKey)==true)setSouthFeld(maze.getFreieFelder().get(southKey));
//		else setSouthFeld(new Feld((getCurrentX()),(getCurrentY()+1),getSouthCellStatus(),"south",1,false));
//		
//		if (maze.getFreieFelder().containsKey(westKey)==true) setWestFeld(maze.getFreieFelder().get(westKey));
//		else setWestFeld(new Feld((getCurrentX()-1),getCurrentY(),getWestCellStatus(),"west",1,false));

		fuelleNachbarFelder(maze);
	}

	public void setzeBot(MazeUnknown maze) {
		switch (getLastActionsResult()) {
		case "OK":
			setCurrentFeld(maze.getMaze().get(getCurrentKey()));
			getCurrentFeld().setTyp(currentCellStatus);
			getCurrentFeld().setKoordinaten(getCurrentKey());
			maze.getMaze().put(getCurrentKey(), getCurrentFeld());
			erzeugeBenachbarteFelder(maze);
			break;
		case "OK NORTH":
			getSouthFeld().setBesucht(true);
//				northFeld.setWegeKosten((northFeld.getWegeKosten()-1));
			maze.getMaze().put(getSouthFeld().getSchluessel(), getSouthFeld());
			erzeugeBenachbarteFelder(maze);
			break;
		case "OK WEST":
//				setCurrentX(eastFeld.getxKoordinate());
//				setCurrentY(eastFeld.getyKoordinate());
			getEastFeld().setBesucht(true);
//				westFeld.setWegeKosten((westFeld.getWegeKosten()-1));
//				setCurrentFeld(westFeld);
			maze.getMaze().put(getEastFeld().getSchluessel(), getEastFeld());
			erzeugeBenachbarteFelder(maze);
			break;
		case "OK SOUTH":
//				setCurrentX(northFeld.getxKoordinate());
//				setCurrentY(northFeld.getyKoordinate());
			getNorthFeld().setBesucht(true);
//				southFeld.setWegeKosten((southFeld.getWegeKosten()-1));
//				setCurrentFeld(southFeld);
			maze.getMaze().put(getNorthFeld().getSchluessel(), getNorthFeld());
			erzeugeBenachbarteFelder(maze);
			break;
		case "OK EAST":
//				setCurrentX(westFeld.getxKoordinate());
//				setCurrentY(westFeld.getyKoordinate());
			getWestFeld().setBesucht(true);
//				eastFeld.setWegeKosten((eastFeld.getWegeKosten()-1));
//				setCurrentFeld(westFeld);
			maze.getMaze().put(getWestFeld().getSchluessel(), getWestFeld());
			erzeugeBenachbarteFelder(maze);
			break;
		default:
			break;
		}
	}

	public void fuelleNachbarFelder(MazeUnknown maze) {
		this.nachbarFelder = new ArrayList<Feld>();
		nachbarFelder.add(getNorthFeld());
		nachbarFelder.add(getEastFeld());
		nachbarFelder.add(getSouthFeld());
		nachbarFelder.add(getWestFeld());
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
		if (sucheSB() == true) {
			System.out.println(geheZuSachbearbeiter());
		} else {
			switch (getNaechstesFeld()) {
			case "north":
				setCurrentFeld(getNorthFeld());
//				System.out.println("go " + getNaechstesFeld());
				break;
			case "east":
				setCurrentFeld(getEastFeld());
//				System.out.println("go " + getNaechstesFeld());
				break;
			case "south":
				setCurrentFeld(getSouthFeld());
//				System.out.println("go " + getNaechstesFeld());
				break;
			case "west":
				setCurrentFeld(getWestFeld());
//				System.out.println("go " + getNaechstesFeld());
				break;
			default:
				System.out.println("go " + getNaechstesFeld());
			}
			System.out.println("go "+ getNaechstesFeld());
		}
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
		this.currentX = currentFeld.getxKoordinate();
		this.currentY = currentFeld.getyKoordinate();
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

	public Collection<Feld> getNachbarFelder() {
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
