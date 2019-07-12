package de.vitbund.vitmaze.players;

public class Feld {
	private int xKoordinate;
	private int yKoordinate;
	private String typ;
	private String himmelsrichtung;
	/*
	 * Standard Konstruktor;
	 */
	public Feld() {
		
	}
	
	/*
	 * Konstruktor zum generieren eines Level - Feldes
	 */
	public Feld (int x, int y, String typ, String himmelsrichtung) {
		setxKoordinate(x);
		setyKoordinate(y);
		setTyp(typ);
		setHimmelsrichtung(himmelsrichtung);
	}


	public int getxKoordinate() {
		return xKoordinate;
	}

	public void setxKoordinate(int xKoordinate) {
		this.xKoordinate = xKoordinate;
	}

	public int getyKoordinate() {
		return yKoordinate;
	}

	public void setyKoordinate(int yKoordinate) {
		this.yKoordinate = yKoordinate;
	}


	public String getTyp() {
		return typ;
	}


	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getHimmelsrichtung() {
		return himmelsrichtung;
	}

	public void setHimmelsrichtung(String himmelsrichtung) {
		this.himmelsrichtung = himmelsrichtung;
	}

	public String getSchluessel() {
		return getxKoordinate()+","+getyKoordinate();
	}

}
