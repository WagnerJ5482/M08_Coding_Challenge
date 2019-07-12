package de.vitbund.vitmaze.players;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MortalComBot {
	
	private int playerId;
	private int currentY;
	private int currentX;
	private int[] formulare = new int[100];
	private Queue<Feld> collNachbarFelder = new LinkedList<Feld>();
	
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
	
	public MortalComBot(Scanner input) {
		// 2. Zeile: Player Infos
		setPlayerId(input.nextInt()); // id dieses Players / Bots
		setCurrentX(input.nextInt());// X-Koordinate der Startposition dieses Player
		setCurrentY(input.nextInt());// Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile
	}

	public void felderEinlesen(Scanner input) {
	 setLastActionsResult(input.nextLine());
	 setCurrentCellStatus(input.nextLine()); // der aktuelle Feld-Status;
	 setNorthCellStatus(input.nextLine());
	 setEastCellStatus(input.nextLine());
	 setSouthCellStatus(input.nextLine());
	 setWestCellStatus(input.nextLine());
	 erzeugeBenachbarteFelder();
	 }
	
	public void erzeugeBenachbarteFelder() {
		setNorthFeld(new Feld((getCurrentX()),(getCurrentY()-1),getNorthCellStatus(),"north"));
		setEastFeld(new Feld((getCurrentX()+1),(getCurrentY()),getEastCellStatus(),"east"));
		setSouthFeld(new Feld((getCurrentX()),(getCurrentY()+1),getSouthCellStatus(),"south"));
		setWestFeld(new Feld((getCurrentX()-1),(getCurrentY()),getWestCellStatus(),"west"));
		fuelleNachbarFelder();
		
	}
	
	public void fuelleNachbarFelder(){
		collNachbarFelder.add(getNorthFeld());
		collNachbarFelder.add(getEastFeld());
		collNachbarFelder.add(getSouthFeld());
		collNachbarFelder.add(getWestFeld());
	}

	public void setzeBot() {
		switch(getLastActionsResult()) {
			case "OK":
				setCurrentFeld(new Feld (getCurrentX(),getCurrentY(),getCurrentCellStatus(),""));
				erzeugeBenachbarteFelder();
				break;
			case "OK north":
				setCurrentFeld(northFeld);
				erzeugeBenachbarteFelder();
				break;
			case "OK west":
				setCurrentFeld(westFeld);
				erzeugeBenachbarteFelder();
				break;
			case "OK south":
				setCurrentFeld(southFeld);
				erzeugeBenachbarteFelder();
				break;
			case "OK east":
				setCurrentFeld(eastFeld);
				erzeugeBenachbarteFelder();
				break;	
			default: 
				break;
		}
	}
	
	public String bewegeBot() {
		if (getCurrentCellStatus().equals(pruefeSB())) {
			return "finish";
		}else return "go "+getNaechstesFeld();	
	}
	
	public String pruefeSB() {
		String ausgabe = "FINISH "+this.getPlayerId()+" 0";
		return ausgabe;}
	
	public void loeschecollNachbarFelder() {
		this.collNachbarFelder = new LinkedList<Feld>();
	}
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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
	public Queue<Feld> getCollNachbarFelder() {
		return collNachbarFelder;
	}


	
	

}
