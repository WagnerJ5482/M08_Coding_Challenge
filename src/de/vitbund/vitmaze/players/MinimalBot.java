package de.vitbund.vitmaze.players;

import java.util.Scanner;

/**
 * Klasse eines minimalen Bots f�r das VITMaze
 * @author Patrick.Stalljohann
 * @version 1.0
 *
 */
public class MinimalBot {

	/**
	 * Hauptmethode zum Ausf�hren des Bots
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und Rundendaten liefert
		Scanner input = new Scanner(System.in);
		MazeUnknown maze = new MazeUnknown(input);
		MortalComBot bot = new MortalComBot(input);


	
		// TURN (Wiederholung je Runde notwendig)
		while(input.hasNext()) {
			if(bot.isSbFound() == true) System.out.println("finish");
		
			
			// Rundeninformationen auslesen
			bot.felderEinlesen(input);
			bot.setzeBot(maze);
			bot.fuelleNachbarFelder(maze);
			bot.sucheSB();
			maze.moeglicheFelder(bot);
			maze.naechstesFeld(bot);
			
//			bot.bewegeNach();
	
			// Debug Information ausgeben (optional m�glich)
			System.err.println("Ergebnis Vorrunde: " + bot.getLastActionsResult());
			System.err.println("Anzahl freie Felder"+maze.getMoeglicheFelder().size());
			System.err.println("Größe freie Felder" + maze.getFreieFelder().size());
			for(Feld feld : bot.getNachbarFelder()) {
				System.err.println("Felder drumrum: "+ feld.getSchluessel());
			}
//			System.err.println("current: "+bot.getCurrentFeld().getTyp());
//			System.err.println("CX: "+bot.getCurrentFeld().getxKoordinate());
//			System.err.println("CY: "+bot.getCurrentFeld().getyKoordinate());
//			System.err.println("Norden: "+bot.getNorthFeld().getTyp());
//			System.err.println("NX: "+bot.getNorthFeld().getxKoordinate());
//			System.err.println("NY: "+bot.getNorthFeld().getyKoordinate());
//			System.err.println("Westen: "+bot.getWestFeld().getTyp());
//			System.err.println("WX: "+bot.getWestFeld().getxKoordinate());
//			System.err.println("WY: "+bot.getWestFeld().getyKoordinate());
//			System.err.println("Sueden: "+bot.getSouthFeld().getTyp());
//			System.err.println("SX: "+bot.getSouthFeld().getxKoordinate());
//			System.err.println("SY: "+bot.getSouthFeld().getyKoordinate());
//			System.err.println("Osten: "+bot.getEastFeld().getTyp());
//			System.err.println("OX: "+bot.getEastFeld().getxKoordinate());
//			System.err.println("OY: "+bot.getEastFeld().getyKoordinate());
//			for(Feld feld : bot.getCollNachbarFelder()) {
//				System.err.println("ID: "+feld.getSchluessel());
//			}
//			System.err.println("");
//			System.err.println("was ist dein SB?: "+bot.pruefeSB());
//			System.err.println("SB gefunden? :"+bot.getWestCellStatus());
//			System.err.println("SB gefunden? :"+bot.getEastCellStatus());
//			System.err.println("SB gefunden? :"+bot.getNorthCellStatus());
//			System.err.println("SB gefunden? :"+bot.getSouthCellStatus());
			// Rundenaktion ausgeben
//			System.out.println(bot.bewegeNach());
		}
		
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
