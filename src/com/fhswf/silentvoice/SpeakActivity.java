package com.fhswf.silentvoice;

import java.io.File;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fhswf.silentvoice.adapter.TabAdapter;
import com.fhswf.silentvoice.data.DataStorage;
import com.fhswf.silentvoice.data.SpeakEntry;
import com.fhswf.silentvoice.fragments.SpeakAddFragment;
import com.fhswf.silentvoice.fragments.SpeakMainFragment;
import com.fhswf.silentvoice.fragments.SpeakReadFragment;
import com.fhswf.silentvoice.utility.MyAppContext;

public class SpeakActivity extends FragmentActivity {


	private TabAdapter mTabsAdapter;


	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speak);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mTabsAdapter = new TabAdapter(this, mViewPager);

		mTabsAdapter.addTab(actionBar.newTab().setText(R.string.speak_section_speak), SpeakMainFragment.class, null);
		mTabsAdapter.addTab(actionBar.newTab().setText(R.string.speak_section_read), SpeakReadFragment.class, null);
		mTabsAdapter.addTab(actionBar.newTab().setText(R.string.speak_section_add), SpeakAddFragment.class, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.speak, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onAddClicked(View v)
	{
		EditText editTextDescription = (EditText) findViewById(R.id.editDescription);
		EditText editTextMessage = (EditText) findViewById(R.id.editMessage);
		
				
		try {
			DataStorage.getInstance().addSpeakEntry(new SpeakEntry(editTextDescription.getText().toString(), editTextMessage.getText().toString()));
			Toast.makeText(getApplicationContext(), "Eintrag erfolgreich hinzugefügt.", Toast.LENGTH_SHORT).show();
			
			editTextDescription.setText("");
			editTextMessage.setText("");
			/**
			 * TODO 	 
			 * - popup: "Weiteren anlegen?"
			 * 		- Ja: 	popup schließen
			 * 				cursor auf editTextDescription setzen
			 * 		- Nein: Neu angelegen Button anzeigen? (Zu Ansicht "Sprechen" wechseln) 
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
