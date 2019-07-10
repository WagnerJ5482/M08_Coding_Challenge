package de.vitbund.vitmaze.players;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MortalComBot {
	
	int playerId;
	int startX;
	int startY;
	int currentX;
	int currentY;
	
	String lastActionsResult;
	String currentCellStatus;
	String northCellStatus;
	String eastCellStatus;
	String southCellStatus;
	String westCellStatus;
	
	
	public MortalComBot(Scanner input) {
		// 2. Zeile: Player Infos
		this.setPlayerId(input.nextInt()); // id dieses Players / Bots
		this.setStartX(input.nextInt()); // X-Koordinate der Startposition dieses Player
		this.setStartY(input.nextInt()); // Y-Koordinate der Startposition dieses Players
		setCurrentX(getStartX());
		setCurrentY(getStartY());
		input.nextLine(); // Beenden der zweiten Zeile
	}
	

	public void benachbarteFelderAuslesen(Scanner input) {
	 setLastActionsResult(input.nextLine());
	 setCurrentCellStatus(input.nextLine());
	 setNorthCellStatus(input.nextLine());
	 setEastCellStatus(input.nextLine());
	 setSouthCellStatus(input.nextLine());
	 setWestCellStatus(input.nextLine());
	 }
	
	public Map<String,String> gibBegebareFelderZurück() {
		Map<String, String> begebareFelder = new HashMap<String, String>();
		if (("FLOOR").equals(getNorthCellStatus())){
			
		}
		return begebareFelder;
	}
	
	
	public String bewegeBot(String richtung) {
		return "go "+richtung;	
	}
	
	public String pruefeSB() {
		if (currentCellStatus.equals("FINISH "+this.getPlayerId()+" 0")){
			return "FINISH 1 0";
			}
		return "";
	}
	
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
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

	
	

}
