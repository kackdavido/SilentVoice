package com.fhswf.silentvoice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.fhswf.silentvoice.data.DataStorage;
import com.fhswf.silentvoice.utility.Utilities;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		try {
			DataStorage.getInstance();
			Log.d("INFO", "Data successfull loaded");
		} catch (Exception e) {
			Log.e("ERROR", "Couldn't load data. \"Exception handling fehlt\"");
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	public void onSpeakClicked(View view) 
	{	
		Utilities.changeView(this, SpeakActivity.class);
	}
	
	public void onHearClicked(View view) 
	{	
		Utilities.changeView(this, HearActivity.class);
	}
	
	public void onOptionClicked(View view) 
	{	
		Utilities.changeView(this, SettingsActivity.class);
	}

}
