package com.example.vertretungsplan;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button klassenauswahl_button= (Button) findViewById(R.id.klassenauswahl_button);
		Button kalender_button = (Button) findViewById (R.id.kalender_button );
		Button einstellungs_button = (Button) findViewById(R.id.einstellungs_button );
		klassenauswahl_button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//Wenn klassenauswahl_buttoun gedrueckt wird. wird die Activity KlassenauswahlActivity gestartet.
				Intent klassenauswahl = new Intent(MainActivity.this, KlassenauswahlActivity.class);
				startActivity(klassenauswahl);
				
			}
		});
		
		kalender_button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//Wenn kalender_buttoun gedrueckt wird. wird die Activity KlassenauswahlActivity gestartet.
				Intent kalender = new Intent(MainActivity.this, KalenderActivity.class);
				startActivity(kalender);
				
			}
		});
		
		einstellungs_button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//Wenn einstellungs_buttoun gedrueckt wird. wird die Activity KlassenauswahlActivity gestartet.
				Intent einstellungen = new Intent(MainActivity.this, EinstellungsActivity.class);
				startActivity(einstellungen);
				
			}
		});
		
		
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
