package de.vitbund.vitmaze.players;

public class Dokument {
	private int dokumentNummer;
	private Feld feld;

	/**
	 * 
	 * @param dokumentNummer
	 * @param feld
	 */
	public Dokument(int dokumentNummer, Feld feld) {
		setDokumentNummer(dokumentNummer);// welche Nummer hat das Dokument;
		setFeld(feld); // wo liegt ein Dokument
	}

	public int getDokumentNummer() {
		return dokumentNummer;
	}

	public void setDokumentNummer(int dokumentNummer) {
		this.dokumentNummer = dokumentNummer;
	}

	public Feld getFeld() {
		return feld;
	}

	public void setFeld(Feld feld) {
		this.feld = feld;
	}
	

}
