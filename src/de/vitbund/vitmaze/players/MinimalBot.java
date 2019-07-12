package de.vitbund.vitmaze.players;

import java.util.Scanner;

/**
 * Klasse eines minimalen Bots für das VITMaze
 * @author Patrick.Stalljohann
 * @version 1.0
 *
 */
public class MinimalBot {

	/**
	 * Hauptmethode zum Ausführen des Bots
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und Rundendaten liefert
		Scanner input = new Scanner(System.in);
		MazeUnknown maze = new MazeUnknown(input);
		MortalComBot bot = new MortalComBot(input);


	
		// TURN (Wiederholung je Runde notwendig)
		while(input.hasNext()) {
		
			
			// Rundeninformationen auslesen
			bot.felderEinlesen(input);
			bot.setzeBot();
			bot.fuelleNachbarFelder();
			maze.fuegeFreieFelderInListe(bot);
			maze.sucheSB(bot);
			bot.loeschecollNachbarFelder();
	
			// Debug Information ausgeben (optional möglich)
			System.err.println("Ergebnis Vorrunde: " + bot.getLastActionsResult());
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
			System.err.println("Freie Felder Anzahl: "+maze.getFreieFelder().size());// geht noch besser!!!
			System.err.println("was ist dein SB?: "+bot.pruefeSB());
			System.err.println("SB gefunden? :"+bot.getWestCellStatus());
			System.err.println("SB gefunden? :"+bot.getEastCellStatus());
			System.err.println("SB gefunden? :"+bot.getNorthCellStatus());
			System.err.println("SB gefunden? :"+bot.getSouthCellStatus());
			// Rundenaktion ausgeben
			System.out.println(bot.bewegeBot());
			
		}
		
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
