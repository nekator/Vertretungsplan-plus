/*package com.example.vertretungsplan;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.AsyncTask;



public class GetKlassen extends AsyncTask<String , Integer, String> {
	String text = null; 
	
	

	protected void onPreExecute(){
        
    }
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpURLConnection connection = null;
			try {
		        URL url = new URL("www.google.com");
		        connection = (HttpURLConnection) url.openConnection();
		        connection.connect();
		        connection.getInputStream();
		         InputStream in = new BufferedInputStream(connection.getInputStream());
			    text = readStream1(in);          
		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    } 
			finally {
		        
		    	if(null != connection) { 
		    		connection.disconnect(); 
		    	}
		    }
		    	
			return text;
		}
		private String readStream1 (final InputStream in) throws IOException
		{
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
			
		
		}
}*/



