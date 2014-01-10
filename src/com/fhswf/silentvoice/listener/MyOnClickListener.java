package com.fhswf.silentvoice.listener;

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.fhswf.silentvoice.components.MyButton;
import com.fhswf.silentvoice.components.MyImageView;

public class MyOnClickListener implements OnClickListener, OnInitListener
{ 
	private Context mContext;
	private TextToSpeech tts;
	
	public MyOnClickListener(Context context)
	{
		this.mContext = context; 
		tts  = new TextToSpeech(mContext, this);
	}
	@Override
	public void onClick(View v) 
	{
		MyImageView button = (MyImageView) v;		
//		MyButton button = (MyButton) v;
		Toast.makeText(mContext, "Clicked on id: " + button.getText(), Toast.LENGTH_SHORT).show();
		Log.d("Debug", "Clicked " + button.getMessage());
		tts.speak(button.getMessage(), TextToSpeech.QUEUE_ADD, null);
		
	}
	@Override
	public void onInit(int status) {
		//TTS is successfully initialized
        if (status == TextToSpeech.SUCCESS) {
                       //Setting speech language
            int result = tts.setLanguage(Locale.GERMAN);
           //If your device doesn't support language you set above
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                           //Cook simple toast message with message
                Toast.makeText(mContext, "Language not supported", Toast.LENGTH_LONG).show();
                Log.e("TTS", "Language is not supported");
            }
                 //Enable the button - It was disabled in main.xml (Go back and Check it)
                        else {
            }
            //TTS is not initialized properly
        } else {
                    Toast.makeText(mContext, "TTS Initilization Failed", Toast.LENGTH_LONG).show();
            Log.e("TTS", "Initilization Failed");
        }
		
	}

}
