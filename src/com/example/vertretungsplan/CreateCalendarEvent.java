package com.example.vertretungsplan;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;



public class CreateCalendarEvent extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayTimeTable displaytimetable = new DisplayTimeTable();
		Integer klasse =  (Integer) getIntent().getExtras().get("class");//speichern der im intent enthaltenen daten
		String klassenstring = klasse.toString();// Integer klasse in string konvertieren
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();   
		calendar.setTime(trialTime);     
		int i = calendar.get(Calendar.WEEK_OF_YEAR);//aktuelle woche in i speichern
		Integer week = Integer.valueOf(i);// i in integer konvertieren
		String date = week.toString();// week in string konverteiren
		String urltrailer=displaytimetable.builtPath(date, klassenstring);// aufruf der methode builtPath mit den Parametern date und klassenstring
		
		new GetHtml(urltrailer).execute();//aufruf des Asynctask GetHtml mit dem Parameter urltrailer
	}
	
	 class  GetHtml extends AsyncTask <String, Integer , String>{
		
		 CreateCalendarEvent createcalendarevent = new CreateCalendarEvent();
		 private String urltrailer;
		public GetHtml(String urltrailer2) {
			this.urltrailer = urltrailer2;//speichert den uebergebenen string
		}




		protected void onPreExecute(){
	        
	    }

			

			
			protected void onPostExecute(String result) {
				HandleCalendarData handle = new HandleCalendarData();
				handle.ParseHTML(result);
				/*String [] splitresult=handle.splitresult(result);// aufrufen der Methode splitresult mit dem Parameter result
				
				String[] B1split = handle.splitSplitResult(splitresult[1]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[1]
				String[] B2split = handle.splitSplitResult(splitresult[2]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[2]
				String[] B3split = handle.splitSplitResult(splitresult[3]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[3]
				String[] B4split = handle.splitSplitResult(splitresult[4]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[4]
				String[] B5split = handle.splitSplitResult(splitresult[5]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[5]
				String[] B6split = handle.splitSplitResult(splitresult[6]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[6]
				String[] B7split = handle.splitSplitResult(splitresult[7]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[7]
				String[] B8split = handle.splitSplitResult(splitresult[8]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[8]
				String[] B9split = handle.splitSplitResult(splitresult[9]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[1]
				String[] B10split = handle.splitSplitResult(splitresult[10]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[10]
				String[] B11split = handle.splitSplitResult(splitresult[11]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[11]
				String[] B12split = handle.splitSplitResult(splitresult[12]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[12]
				String[] B13split = handle.splitSplitResult(splitresult[13]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[13]
				String[] B14split = handle.splitSplitResult(splitresult[14]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[14]
				String[] B15split = handle.splitSplitResult(splitresult[15]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[15]
				String[] B16split = handle.splitSplitResult(splitresult[16]);//aufrufe der methode splitSplitResult mit dem Parameter splitresult[16]
				String[][] beginnerstestunde = handle.stundenBeginn(B1split);//aufruf der methode stundenBeginn mit dem Parameter B1split
				String[][]beginnzweitestunde = handle.stundenBeginn(B2split);//aufruf der methode stundenBeginn mit dem Parameter B2split
				String[][] beginndrittestunde= handle.stundenBeginn(B3split);//aufruf der methode stundenBeginn mit dem Parameter B3split
				String[][] beginnviertestunde= handle.stundenBeginn(B4split);//aufruf der methode stundenBeginn mit dem Parameter B4split
				String[][] beginnfuenftestunde= handle.stundenBeginn(B5split);//aufruf der methode stundenBeginn mit dem Parameter B5split
				String[][] beginnsechstestunde= handle.stundenBeginn(B6split);//aufruf der methode stundenBeginn mit dem Parameter B6split
				String[][] beginnsiebtestunde= handle.stundenBeginn(B7split);//aufruf der methode stundenBeginn mit dem Parameter B7split
				String[][] beginnachtestunde= handle.stundenBeginn(B8split);//aufruf der methode stundenBeginn mit dem Parameter B8split
				String[][] beginnneuntestunde= handle.stundenBeginn(B9split);//aufruf der methode stundenBeginn mit dem Parameter B9split
				String[][] beginnzehntestunde= handle.stundenBeginn(B10split);//aufruf der methode stundenBeginn mit dem Parameter B10split
				String[][] beginnelftestunde= handle.stundenBeginn(B11split);//aufruf der methode stundenBeginn mit dem Parameter B11split
				String[][] beginnzwoelftestunde= handle.stundenBeginn(B12split);//aufruf der methode stundenBeginn mit dem Parameter B12split
				String[][] beginndreizehntestunde= handle.stundenBeginn(B13split);//aufruf der methode stundenBeginn mit dem Parameter B13split
				String[][] beginnvierzehntestunde= handle.stundenBeginn(B14split);//aufruf der methode stundenBeginn mit dem Parameter B14split
				String[][] beginnfuenfzehntestunde= handle.stundenBeginn(B15split);//aufruf der methode stundenBeginn mit dem Parameter B15split
				String[][] beginnsechzehntestunde= handle.stundenBeginn(B16split);//aufruf der methode stundenBeginn mit dem Parameter B16split
				String[][] montag = handle.createData(beginnerstestunde, beginnzweitestunde, beginndrittestunde, beginnviertestunde, beginnfuenftestunde, beginnsechstestunde, beginnsiebtestunde, beginnachtestunde, beginnneuntestunde, beginnzehntestunde, beginnelftestunde, beginnzwoelftestunde, beginndreizehntestunde, beginnvierzehntestunde, beginnfuenfzehntestunde, beginnsechzehntestunde, 0);
				for(int i=0;i<montag.length;i++){
					for(int j=0;j<2;j++){
						if(montag[i][j]!=null){
							System.out.println(montag[i][j]);
						}
					}
				}
				String[][] dienstag = handle.createData(beginnerstestunde, beginnzweitestunde, beginndrittestunde, beginnviertestunde, beginnfuenftestunde, beginnsechstestunde, beginnsiebtestunde, beginnachtestunde, beginnneuntestunde, beginnzehntestunde, beginnelftestunde, beginnzwoelftestunde, beginndreizehntestunde, beginnvierzehntestunde, beginnfuenfzehntestunde, beginnsechzehntestunde, 1);
				//Aufruf der Methode createData mit den Parametern beginnerstestunde bis beginnsechzehntestunde
				for(int i=0;i<dienstag.length;i++){
					for(int j=0;j<2;j++){
						if(dienstag[i][j]!=null){
							System.out.println(dienstag[i][j]);
						}
					}
				}
				String[][] mittwoch = handle.createData(beginnerstestunde, beginnzweitestunde, beginndrittestunde, beginnviertestunde, beginnfuenftestunde, beginnsechstestunde, beginnsiebtestunde, beginnachtestunde, beginnneuntestunde, beginnzehntestunde, beginnelftestunde, beginnzwoelftestunde, beginndreizehntestunde, beginnvierzehntestunde, beginnfuenfzehntestunde, beginnsechzehntestunde, 2);
				//Aufruf der Methode createData mit den Parametern beginnerstestunde bis beginnsechzehntestunde
				for(int i=0;i<mittwoch.length;i++){
					for(int j=0;j<2;j++){
						if(mittwoch[i][j]!=null){
							System.out.println(mittwoch[i][j]);
						}
					}
				}
				String[][] donnerstag = handle.createData(beginnerstestunde, beginnzweitestunde, beginndrittestunde, beginnviertestunde, beginnfuenftestunde, beginnsechstestunde, beginnsiebtestunde, beginnachtestunde, beginnneuntestunde, beginnzehntestunde, beginnelftestunde, beginnzwoelftestunde, beginndreizehntestunde, beginnvierzehntestunde, beginnfuenfzehntestunde, beginnsechzehntestunde, 3);
				//Aufruf der Methode createData mit den Parametern beginnerstestunde bis beginnsechzehntestunde
				for(int i=0;i<donnerstag.length;i++){
					for(int j=0;j<2;j++){
						if(donnerstag[i][j]!=null){
							System.out.println(donnerstag[i][j]);
						}
					}
				}
				String[][] freitag = handle.createData(beginnerstestunde, beginnzweitestunde, beginndrittestunde, beginnviertestunde, beginnfuenftestunde, beginnsechstestunde, beginnsiebtestunde, beginnachtestunde, beginnneuntestunde, beginnzehntestunde, beginnelftestunde, beginnzwoelftestunde, beginndreizehntestunde, beginnvierzehntestunde, beginnfuenfzehntestunde, beginnsechzehntestunde, 4);
				//Aufruf der Methode createData mit den Parametern beginnerstestunde bis beginnsechzehntestunde
				for(int i=0;i<freitag.length;i++){
					for(int j=0;j<2;j++){
						if(freitag[i][j]!=null){
							System.out.println(freitag[i][j]);
						}
					}
				}*/
			}	
			
			protected String doInBackground(String...params ) {
				//Aufruf des Quellcodes des ausgewaehlten vertretungsplans
				System.out.println(urltrailer);
				String text = ""; 
				HttpURLConnection connection = null;
				boolean test = isOnline();
				if(test==true){
				try {
					String domain = new String("http://btineuss.rhein-kreis-neuss.de/UNTIS_HTML_Schueler/"+urltrailer);
					
					URL url = new URL(domain);
			        connection = (HttpURLConnection) url.openConnection();
			        connection.connect();
			        connection.getInputStream();
			         InputStream in = new BufferedInputStream(connection.getInputStream());
				    text = readStream1(in);//Stream in die methode readstream1 uebergeben.    
			    } 
				catch (MalformedURLException e) {
			    	//Abfangen von Exceptions
			        e.printStackTrace();
			    }
				catch (IOException e) {
					//Abfangen von Exceptions
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
					
					Toast toast = Toast.makeText(createcalendarevent, "No connection", Toast.LENGTH_SHORT);
					toast.show();
					return text;
				}
				
			}
			private String readStream1 (final InputStream in) throws IOException{
				//schreibt den stream in einen String
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
			public boolean isOnline() {
				// prueft ob eine Netzwerkverbindung besteht
			    ConnectivityManager cm = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
			    NetworkInfo netInfo = cm.getActiveNetworkInfo();
			    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			        return true;
			    }
			    return false;
			}

			}
	}

