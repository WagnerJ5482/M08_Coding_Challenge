package de.vitbund.vitmaze.players;

/**
 * 
 * Die Klasse Formular legt die Dokumentennnummer und das Feld fest auf dem das
 * Dokument liegt.
 * 
 * @author Mortal KomBot
 *
 */
public class Formular {
	private int dokumentNummer;
	private Feld feld;

	/**
	 * Konstruktor zur Festlegung Dokumentennummer und dey Feldes worauf sich das
	 * Dokument befindet
	 * 
	 * @param dokumentNummer legt Nummer des Formulars fest
	 * @param feld           legt fest auf welchem Feld das Formular liegt
	 */
	public Formular(int dokumentNummer, Feld feld) {
		setDokumentNummer(dokumentNummer);// welche Nummer hat das Dokument;
		setFeld(feld); // wo liegt ein Dokument
	}

	/**
	 * statische Methode, welche aus dem Typen des Feldes die Formularnummer als int
	 * Wert zur�ck liefert.
	 * 
	 * @param feld Hier findet keine �berpr�fung auf den Typen des Feldes statt. Bei
	 *             Floor oder Wall Feldertypen kommt es zu Exceptions.
	 * @return Nummer des Formulares
	 */
	static int extrahiereFormNummer(Feld feld) {
		String[] split = feld.getTyp().split(" ");
		return Integer.parseInt(split[2]);
	}

	/**
	 * Standard getter f�r DokumentNummer
	 * 
	 * @return dokumentNummer
	 */
	public int getDokumentNummer() {
		return dokumentNummer;
	}

	/**
	 * Standard setter f�r DokumentNummer
	 * 
	 * @param dokumentNummer
	 */
	public void setDokumentNummer(int dokumentNummer) {
		this.dokumentNummer = dokumentNummer;
	}

	/**
	 * Standard getter f�r Feld
	 * 
	 * @return feld
	 */
	public Feld getFeld() {
		return feld;
	}

	/**
	 * Standard setter f�r Feld
	 * 
	 * @param feld
	 */
	public void setFeld(Feld feld) {
		this.feld = feld;
	}

}
