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
		bot.startFeldEinlesen(maze);

	
		// TURN (Wiederholung je Runde notwendig)
		while(input.hasNext()) {
			
			// Rundeninformationen auslesen
			bot.felderEinlesen(input);
			bot.erzeugeBenachbarteFelder(maze);
			bot.setzeBot(maze);
			if (bot.sucheSB() == true) {
				bot.bewegeNach();
				System.out.println(bot.geheZuSachbearbeiter());
			} else {
				maze.moeglicheFelder(bot);
				maze.naechstesFeld(bot);
				bot.bewegeNach();
			}
			
	
			// Debug Information ausgeben (optional m�glich)
			System.err.println("Ergebnis Vorrunde: " + bot.getLastActionsResult());
//			for(int i=0; i<=maze.getBreite();i++) {
//				for (int j=0;j<=maze.getLaenge();j++) {
//					System.err.println(maze.getFeld(i,j).getAnzahlBesuche());
//				}
//			}

			// Rundenaktion ausgeben
//			System.out.println(bot.bewegeNach());
		}
		
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
