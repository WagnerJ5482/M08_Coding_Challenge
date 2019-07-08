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


		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		int sizeX = input.nextInt(); // X-Groesse des Spielfeldes (Breite)
		int sizeY = input.nextInt(); // Y-Groesse des Spielfeldes (Hoehe)
		int level = input.nextInt(); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		// 2. Zeile: Player Infos
		int playerId = input.nextInt(); // id dieses Players / Bots
		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile

		
		// TURN (Wiederholung je Runde notwendig)
		while(input.hasNext()) {
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();
	
			// Debug Information ausgeben (optional möglich)
			System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
			
			// Rundenaktion ausgeben
			if(currentCellStatus.contains("FINISH " + playerId + " 0")) {
				System.out.println("FINISH 1 0");
			}
			else if(northCellStatus.equals("FLOOR")) {
				System.out.println("go north");
			}
			else if(eastCellStatus.equals("FLOOR")) {
				System.out.println("go east");
			}
			else if(southCellStatus.equals("FLOOR")) {
				System.out.println("go south");
			}
			else if(westCellStatus.equals("FLOOR")) {
				System.out.println("go west");
			}
			
			System.out.println("go north");
		}
		
		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
