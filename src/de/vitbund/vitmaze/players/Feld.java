package de.vitbund.vitmaze.players;

/**
 * 
 * Die Klasse �Feld� legt Eigenschaften der im Labyrinth dargestellten Felder
 * fest. Diese beinhalten die Koordinaten (x, y), den Typ (FLOOR, WALL, FINISH,
 * FORM), die Himmelsrichtung, die Wegekosten sowie ob das Feld schon betreten
 * wurde und wie oft.
 *
 * @author Mortal KomBot
 *
 */

public class Feld {
	private int xKoordinate;
	private int yKoordinate;
	private String typ;
	private String himmelsrichtung;
	private boolean besucht;
	private int anzahlBesuche;
	private int wegeKosten;

	/*
	 * Konstruktor zum generieren eines Level - Feldes
	 */

	/**
	 * 
	 * Konstruktor zum generieren eines Level - Feldes
	 * 
	 * @param x               Koordinate x-Achse (Breite)
	 * @param y               Koordinate y-Achse (L�nge bzw. H�he)
	 * @param typ             Feldtyp (Wall, Floor, Finish, Form ...)
	 * @param himmelsrichtung (Himmelsrichtung des Feldes)
	 * @param wegeKosten      Kosten eines zu erreichenden Feldes
	 * @param besucht
	 * @param anzahlBesuche
	 */

	public Feld(int x, int y, String typ, String himmelsrichtung, int wegeKosten, boolean besucht, int anzahlBesuche) {
		setxKoordinate(x);
		setyKoordinate(y);
		setTyp(typ);
		setWegeKosten(wegeKosten);
		setBesucht(besucht);
		setHimmelsrichtung(himmelsrichtung);
		setAnzahlBesuche(anzahlBesuche);
	}

	/**
	 * Methode setKoordinaten ohne R�ckgabewert
	 * 
	 * @param schluessel Schluessel besteht aus aus den x, y Koordinaten und wird in
	 *                   der Methode aufgeteilt, da der Schl�ssel aus der
	 *                   Key-Value-Map maze kommt und die x- und y-Koordinate des
	 *                   jeweiligen Feldes enth�lt.. Sofern x und/oder y kleiner 0
	 *                   sind, werden diese auf die Breite bzw. L�nge des
	 *                   Spielfeldes gesetzt. Im Fall, dass x und/oder y �ber die
	 *                   Breite bzw. L�nge des Spielfeldes hinausgehen, werden diese
	 *                   auf 0 gesetzt.
	 * @param maze       {@link maze MazeUnknown}
	 */

	public void setKoordinaten(String schluessel, MazeUnknown maze) {
		String[] key = schluessel.split(",");
		int x = Integer.parseInt(key[0]);
		int y = Integer.parseInt(key[1]);
		if (key[0].equals(null))
			setxKoordinate(((maze.getBreite() - 1)));
		else if (x > maze.getBreite())
			setxKoordinate(0);
		else
			setxKoordinate(x);

		if (key[1].equals(null))
			setyKoordinate(((maze.getLaenge() - 1)));
		else if (y > maze.getLaenge())
			setyKoordinate(0);
		else
			setyKoordinate(y);
	}

	/**
	 * Methode anzahlBesucheErhoeen setzt die Anzahl der Besuche f�r das Feld um die
	 * �bergebene Zahl h�her.
	 * 
	 * @param zahl
	 */
	public void anzahlBesucheErhoeen(int zahl) {
		this.anzahlBesuche += zahl;
	}

	/**
	 * Methode zur R�ckgabe eines Strings, der aus x und y Koordinate einen
	 * Schl�ssel f�r die Map maze baut.
	 * 
	 * @return String schluessel
	 */
	public String getSchluessel() {
		return getxKoordinate() + "," + getyKoordinate();
	}

	/**
	 * Standard getter f�r x-Koordinate
	 * 
	 * @return xKoordinate
	 */
	public int getxKoordinate() {
		return xKoordinate;
	}

	/**
	 * Standard setter f�r x-Koordinate
	 * 
	 * @param xKoordinate
	 */
	public void setxKoordinate(int xKoordinate) {
		this.xKoordinate = xKoordinate;
	}

	/**
	 * Standard getter f�r y-Koordinate
	 * 
	 * @return yKoordinate
	 */
	public int getyKoordinate() {
		return yKoordinate;
	}

	/**
	 * Standard setter f�r y-Koordinate
	 * 
	 * @param yKoordinate
	 */
	public void setyKoordinate(int yKoordinate) {
		this.yKoordinate = yKoordinate;
	}

	/**
	 * Standard getter f�r Typ
	 * 
	 * @return typ
	 */
	public String getTyp() {
		return typ;
	}

	/**
	 * Standard setter f�r Typ
	 * 
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ = typ;
	}

	/**
	 * Standard getter f�r Himmelsrichtung
	 * 
	 * @return himmelsrichtung
	 */
	public String getHimmelsrichtung() {
		return himmelsrichtung;
	}

	/**
	 * Standard setter f�r Himmelsrichtung
	 * 
	 * @param himmelsrichtung
	 */
	public void setHimmelsrichtung(String himmelsrichtung) {
		this.himmelsrichtung = himmelsrichtung;
	}

	/**
	 * Standard getter f�r Besucht
	 * 
	 * @return besucht
	 */
	public boolean isBesucht() {
		return besucht;
	}

	/**
	 * Standard setter f�r Besucht
	 * 
	 * @param besucht
	 */
	public void setBesucht(boolean besucht) {
		this.besucht = besucht;
	}

	/**
	 * Standard getter f�r WegeKosten
	 * 
	 * @return wegeKosten
	 */
	public int getWegeKosten() {
		return wegeKosten;
	}

	/**
	 * Standard setter f�r WegeKosten
	 * 
	 * @param wegeKosten
	 */
	public void setWegeKosten(int wegeKosten) {
		this.wegeKosten = wegeKosten;
	}

	/**
	 * Standard getter f�r AnzahlBesuche
	 * 
	 * @return anzahlBesuche
	 */
	public int getAnzahlBesuche() {
		return anzahlBesuche;
	}

	/**
	 * Standard setter f�r AnzahlBesuche
	 * 
	 * @param anzahlBesuche
	 */
	public void setAnzahlBesuche(int anzahlBesuche) {
		this.anzahlBesuche = anzahlBesuche;
	}

}
