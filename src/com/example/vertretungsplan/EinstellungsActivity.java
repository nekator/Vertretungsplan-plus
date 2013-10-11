package com.example.vertretungsplan;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ToggleButton;


public class EinstellungsActivity extends Activity {

	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_einstellungen);
		ToggleButton onoff =(ToggleButton) findViewById(R.id.toggleButton1);
		onoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			//fuehrt die methode onCheckedChanged aus wenn onoff button betaetigt wird

		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	//wenn button auf on
		    		GetKlassen getklassen = new GetKlassen();
		    		getklassen.execute();// aufruf des Asynctask GetKlassen
		    		
		        } else {
		        
		        }
		    }
		});
	}
	

	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class GetKlassen extends AsyncTask<String , Integer, String> {
		
		EinstellungsActivity auswahl = new EinstellungsActivity ();
		private boolean isUserSelection = false;
		

		protected void onPreExecute(){
	        
	    }
			protected String doInBackground(String... urls) {
				// Aufbau einer Verbindung zur Vertretunsplanseite.
				String text = ""; 
				HttpURLConnection connection = null;
				boolean test = isOnline();// aufruf der isOnline Methode um zu prüfen ob eine Netzwerkverbindung besteht
				if(test==true){
				try {

					URL url = new URL("http://btineuss.rhein-kreis-neuss.de/UNTIS_HTML_Schueler/frames/navbar.htm");
			        connection = (HttpURLConnection) url.openConnection();
			        connection.connect();
			        connection.getInputStream();
			         InputStream in = new BufferedInputStream(connection.getInputStream());
				    text = readStream1(in);  //aufruf der readStream1 methode mit übergabe der erhaltenen stream daten         
			    } 
				catch (MalformedURLException e) {
			    	//abfangen von Exceptions
			        e.printStackTrace();
			    } 
				catch (IOException e) {
					//abfangen von Exceptions
					e.printStackTrace();
			    } 
				finally {
			        
			    	if(null != connection) { 
			    		connection.disconnect(); 
			    	}
			    }
			    	
				return text;
				}
				else {
					text="No Connection!";
					return text;
				}
				
			}
			private String readStream1 (final InputStream in) throws IOException
			{
				//readStream1 speichert die erhaltenen Daten in einen String
					final BufferedReader reader = new BufferedReader(new InputStreamReader(in), 4096);
				    final StringBuilder result = new StringBuilder();
				    
				    String line;
				    while ((line = reader.readLine()) != null)
				    {
				    	result.append(line);
				    }
				    reader.close();
				    
				    return result.toString();
				     
			}
			
			
			protected void onPostExecute (String result){
				// verarbeitung des Strings result
				final Spinner klassenliste = (Spinner) findViewById(R.id.datespinner);
				int datumstringstart = result.indexOf("<option value=");//untersuchet den string result auf "<option value=" und Speichert die stelle wo sich dieser String befindet 
				int datumstringend = result.indexOf("</select>");// untersucht den string result auf "</select>" und speichert die stelle wo sich dieser String befindet.
				String rohdatum = result.substring(datumstringstart, datumstringend);//Speichert den String der an der stelle datumstringstart beginnt bis zu der stelle wo datumstringend sich befindet.
				char [] rohdatumarray = rohdatum.toCharArray();//Schreibt den String rohdatum in eine char array um jede Stelle des arrays einzelt zu pruefen.
				char[] datum = new char[4] ;
				for(int i=0;i<rohdatumarray.length;i++){
					//sucht aus rohdatumarray die aktuelle und naechste Kalenderwoche herraus und speichert sie in datum[].
					if(i==15){
						datum[0]=rohdatumarray[i];
					}
					if(i==16){
						datum[1]=rohdatumarray[i];	
					}
					if(i==52){
						datum[2]=rohdatumarray[i];	
					}
					if(i==53){
						datum[3]=rohdatumarray[i];	
					}
				}
				char[] woche1array = new char [2];
				System.arraycopy(datum, 0, woche1array, 0, 2);//kopiert die ersten beiden felder des arrays datum[] in ein seperates array
				char[] woche2array = new char [2];
				System.arraycopy(datum,2,woche2array,0,2 );//kopiert die letzten beiden felder des arrays datum[] in ein seperates array
				String woche1 ="";
				for(char str:woche1array){
					
					woche1 = woche1+str;// schreibt jedes feld des arrays woche1array in einen string
				}

				String woche2 ="";
				for(char str:woche2array){
					
					woche2=woche2+str;// schreibt jedes feld des arrays woche1array in einen string
				}

				int klassenstringstart = result.indexOf("BF");// untersucht an welcher stelle sich der string "BF" befindet und speichert diese stelle.
				int klassenstringend   = result.indexOf("\"]");// untersucht an welcher stelle sich der String ""]""befindet und speichert diese stelle.
				String rohklassen = result.substring(klassenstringstart, klassenstringend);// schreibt von der stelle klassenstringstart bis klassenstringend alles aus dem string result in den string rohklassen
				String[] klassen = rohklassen.split("\",\"");// schreibt alle durch "","" getrennten teile des strings in das array klassen
				//aus dem result String werden die Klassennamen in dem Array klassen gespeichert.
				List<String> items = new ArrayList<String>();
				items.add("Bitte Klasse auswählen");
				for(int i=0;i<klassen.length;i++){
					items.add(klassen[i]);
					//hinzufuegen der im Array klassen enthaltenen werte, zu einer Liste.
				}

				
				SpinnerAdapter adapter = new ArrayAdapter<String>(EinstellungsActivity.this, android.R.layout.simple_list_item_1,items);
				((BaseAdapter) adapter).notifyDataSetChanged();
				klassenliste.setAdapter(adapter);
				//Listenwerte in SpinnerAdapter uebergeben und im Spinner darstellen.
				klassenliste.setOnItemSelectedListener(new OnItemSelectedListener() {
				  //wenn ein item im spinner klassenliste ausgewaehlt wird. wird die methode onItemSelected aufgerufen. Sonst wird die methode onNothingSelected aufgerufen.
				    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				        if(isUserSelection){
				        	//wenn die auswahl vom user getroffen wurde
				        	int selectedclass = klassenliste.getSelectedItemPosition();//speichern der position des spinner+1
				        	Intent i = new Intent(EinstellungsActivity.this,CreateCalendarEvent.class); // erstellung eines intent der die classe CreateCalendarEvent aufruft
				        	i.putExtra("class", selectedclass);//Werte der Variable selectedclass dem intent hinzufuegen
				        	startActivity(i); // Start der Activity CreateCalendarEvents
				        }  
				        else{
				        	
				        }
				        
				        isUserSelection =true;// aendert den Wert von isUserSelection auf true
				    }

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}

				
				});
			
				
			
			}
			
			public boolean isOnline() {
				//testet ob eine Netzwerkverbindung besteht
			    ConnectivityManager cm = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
			    NetworkInfo netInfo = cm.getActiveNetworkInfo();
			    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			        return true;
			    }
			    return false;
			}
	}
}

