package de.vitbund.vitmaze.players;

import java.util.Scanner;

/**
 * Klasse eines minimalen Bots fuer das VITMaze
 * 
 * @author Patrick.Stalljohann
 * @version 1.0
 *
 */
public class MinimalBot {

	/**
	 * Hauptmethode zum Ausfuehren des Bots Es werden Maze und Bot deklariert,
	 * initialisiert und instanziiert sowie der Startparameter des Bots gesetzt.
	 * Rundenweise werden die Feldinformationen eingelesen, das aktuelle Feld auf
	 * den Typ geprüft sowie die benachbarten Felder erzeugt. Dies wird so lange
	 * wiederholt bis der Bot alle Formulare eingesammelt hat und bei der*m SB
	 * abgegeben hat.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und
		// Rundendaten liefert
		Scanner input = new Scanner(System.in);
		MazeUnknown maze = new MazeUnknown(input);
		MortalComBot bot = new MortalComBot(input);
		bot.startFeldEinlesen(maze);

		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			do {
				// Rundeninformationen auslesen
				bot.felderEinlesen(input);
				bot.aktuellesFeldPruefen(maze);
				bot.erzeugeBenachbarteFelder(maze);
				bot.setzeBot(maze);

//				if (bot.sucheSB() == true) {
//					bot.bewegeNach();
//				} else 
				if (bot.sucheFormular() == true) {
					bot.bewegeNach();
				} else {
					maze.moeglicheFelder(bot);
					maze.naechstesFeld(bot);
					bot.bewegeNach();
				}
			} while (bot.aktuellesFeldPruefen(maze) == false);

			// Debug Information ausgeben (optional mï¿½glich)
			System.err.println("Ergebnis Vorrunde: " + bot.getLastActionsResult());

			// Rundenaktion ausgeben
//			System.out.println(bot.bewegeNach());
		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
