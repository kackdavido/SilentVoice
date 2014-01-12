package com.fhswf.silentvoice.fragments;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fhswf.silentvoice.R;
import com.fhswf.silentvoice.data.DataStorage;
import com.fhswf.silentvoice.data.HearEntry;

public class HearMainFragment extends Fragment {

	private ImageButton btnSpeak;
	private TextView hearText;
	protected static final int RESULT_SPEECH = 1;
	protected static final int RESULT_OK = -1;

	public HearMainFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hear_main,
				container, false);
		
		hearText = (TextView) rootView.findViewById(R.id.hearText);
		btnSpeak = (ImageButton) rootView.findViewById(R.id.btnSpeak);
		
		btnSpeak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Speak!", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "de-DE");
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
				
				try
				{
					startActivityForResult(intent, RESULT_SPEECH);
					hearText.setText("");
				}
				catch(ActivityNotFoundException e)
				{
					Toast.makeText(v.getContext(),
		                    "Opps! Your device doesn't support Speech to Text",
		                    Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		
		
		return rootView;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.d("INFO", "Recognizer show Result: " + resultCode);
		
		switch (requestCode) {
			case RESULT_SPEECH: {
				if(resultCode == RESULT_OK )
				{
	                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	                
	                // Add recognized data to textfield
	                Log.d("INFO", "Recognizer Datalength: " + text.size() + " Data: " + text.get(0));
	                hearText.setText(text.get(0));
	                
	                //Add recognized data to xml file for history
	                DataStorage.getInstance().addHearEntry(new HearEntry(new GregorianCalendar(2014,01,12), text.get(0)));
				}
				else
				{
					Log.d("INFO", "Recognizer Datalength zero");
				}
				break;
			}
		
		}
	}	

}
