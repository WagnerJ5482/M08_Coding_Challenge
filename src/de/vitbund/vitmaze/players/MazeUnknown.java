package de.vitbund.vitmaze.players;

import java.util.Scanner;

public class MazeUnknown {
	int laenge;
	int breite;
	int level;
	int[][] flaeche;
	
	String lastActionsResult;
	String currentCellStatus;
	String northCellStatus;
	String eastCellStatus;
	String southCellStatus;
	String westCellStatus;
	
	
	public MazeUnknown(Scanner input) {
		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		this.setBreite(input.nextInt()); // X-Groesse des Spielfeldes (Breite)
		this.setLaenge(input.nextInt()); // Y-Groesse des Spielfeldes (Hoehe)
		this.setLevel(input.nextInt()); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		this.flaeche = new int[breite][laenge];
		befuelleFlaeche();
		
	}
	
	private void befuelleFlaeche() {
		for (int i = 0; i < flaeche.length; i++) {
			for (int j = 0; j < flaeche[i].length; j++) {
				
			}
		}
	}

	public void benachbarteFelderFuellen(Scanner input) {
	 setLastActionsResult(input.nextLine());
	 setCurrentCellStatus(input.nextLine());
	 setNorthCellStatus(input.nextLine());
	 setEastCellStatus(input.nextLine());
	 setSouthCellStatus(input.nextLine());
	 setWestCellStatus(input.nextLine());
	 }
	
	public String bewegeBot(MortalComBot bot) {
			
		if (getNorthCellStatus().equals("FLOOR") && getLastActionsResult().contains("NORTH")) return "go north";
		if (getEastCellStatus().equals("FLOOR")&& getLastActionsResult().contains("EAST")) return "go east";
		if (getSouthCellStatus().equals("FLOOR")&& getLastActionsResult().contains("SOUTH")) return "go south";
		if (getWestCellStatus().equals("FLOOR")&& getLastActionsResult().contains("WEST")) return "go west";
		pruefeSB(bot);
		return "";	
	}
	
	public String pruefeSB(MortalComBot bot) {
		if (currentCellStatus.equals("FINISH "+bot.getPlayerId()+" 0")){
			return "FINISH 1 0";
			}
		return "";
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

	public int[][] getFlaeche() {
		return flaeche;
	}

	public void setFlaeche(int[][] flaeche) {
		this.flaeche = flaeche;
	}

}
