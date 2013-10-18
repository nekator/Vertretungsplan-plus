package com.example.vertretungsplan;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;



public class DisplayTimeTable extends Activity {

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_displaytimetable);
	
	String date = (String)getIntent().getExtras().getString("date");//speichern der vom intent uebergebenen daten.
	Integer klasse =  (Integer) getIntent().getExtras().get("class");//speichern der vom intent uebergebenen daten.
	String klassenstring = klasse.toString();// schreibt die Zahlen aus klasse in den string klassenstring
	String urltrailer = builtPath(date,klassenstring);//aufrufen der methode builtPath mit den Parametern date und klassenstring
	WebView wv = (WebView) findViewById(R.id.webview1);
	wv.loadUrl("http://btineuss.rhein-kreis-neuss.de/UNTIS_HTML_Schueler/"+urltrailer);//darstellung des vertretungsplans im webview
	}
	public String builtPath(String date, String klassestring){
		// erstellt den Pfad zum gewählten Vertretungsplan
		char[] zahl= new char[2];
		char[] datumarray = date.toCharArray();//date string in char array schreiben
		String file ="";
		for(int i=0;i<datumarray.length;i++){
			if(i==datumarray.length-2){
				zahl[0]= datumarray[i];//speichert die vorletze stelle aus datumarray in dem array zahl
			}
			if(i==datumarray.length-1){
				zahl[1]= datumarray[i];//speichert die letzte stelle aus datumarray in dem array zahl
			}
		}
		String datum ="";
		for(char str:zahl){
			datum=datum+str;//schreibt die werte aus dem array zahl in den string datum
		}
		while(klassestring.length()<5){
			klassestring="0"+klassestring;//aufuellen des klassenstrings mit "0" bis dieser eine laenge von 5 hat
		}
		System.out.println(file);
		String urltrailer = datum+"/c/c"+klassestring+".htm";

		return urltrailer;
	}

	
 }

