package com.example.vertretungsplan;


public class Unterrichtsfach {
	String fachbezeichnung;
	public String getFachbezeichnung() {
		return fachbezeichnung;
	}
	public void setFachbezeichnung(String fachbezeichnung) {
		this.fachbezeichnung = fachbezeichnung;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	String beschreibung;
	
	public Unterrichtsfach(String fachbezeichnung,String beschreibung) {
		this.fachbezeichnung=fachbezeichnung;
		this.beschreibung=beschreibung;
	}
	public void writeToGoogle(){
	}
	public void dump(){
	}
}
