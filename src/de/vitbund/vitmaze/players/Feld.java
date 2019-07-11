package de.vitbund.vitmaze.players;

public class Feld {
	private int xKoordinate;
	private int yKoordinate;
	private String typ;
	/*
	 * Standard Konstruktor;
	 */
	public Feld() {
		
	}
	
	/*
	 * Konstruktor zum generieren eines Level - Feldes
	 */
	public Feld (int x, int y, String typ) {
		setxKoordinate(x);
		setyKoordinate(y);
		setTyp(typ);
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

}
