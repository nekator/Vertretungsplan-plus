package com.example.vertretungsplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


public class KalenderActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kalender);
		//erzeugt eine Kalender uebersicht
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
