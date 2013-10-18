package com.example.vertretungsplan;



public class Zeitraum {
	String wochentag;
	public String getWochentag() {
		return wochentag;
	}
	public void setWochentag(String wochentag) {
		this.wochentag = wochentag;
	}
	public int getVon() {
		return von;
	}
	public void setVon(int von) {
		this.von = von;
	}
	public int getBis() {
		return bis;
	}
	public void setBis(int bis) {
		this.bis = bis;
	}
	int von;
	int bis;
	public Zeitraum(String wochentag,int von, int bis){
		this.wochentag=wochentag;
		this.von=von;
		this.bis=bis;	
	}
	public void dump(){
	}
}
