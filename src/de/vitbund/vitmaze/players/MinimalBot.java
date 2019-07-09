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
			bot.benachbarteFelderErkennen(input);
			System.out.println(bot.bewegeBot());
	
			// Debug Information ausgeben (optional möglich)
			System.err.println("Ergebnis Vorrunde: " + bot.getLastActionsResult());
//			
//			// Rundenaktion ausgeben
//			if(currentCellStatus.contains("FINISH " + bot.getPlayerId() + " 0")) {
//				System.out.println("FINISH 1 0");
//			}
//			System.out.println("go west");
			
		}
		
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
