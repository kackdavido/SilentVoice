package com.fhswf.silentvoice;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String APP_TAG = "com.fhswf.silentvoice";
	private File outFile = null;
	private MediaRecorder recorder;
	private MediaPlayer player;
	
	private boolean recording =  false;
	private boolean playing = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try
		{
			File storageDir = new File(Environment.getExternalStorageDirectory(), "com.silentvoice.recorder");
			storageDir.mkdir();
			Log.d(APP_TAG, "Storage Directory set to" + storageDir);
			outFile = File.createTempFile("silentvoice", ".3gp", storageDir);
			
			//Inizialisieren des Recorders
			
//			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//			recorder.setOutputFile(outFile.getAbsolutePath());
//			recorder.prepare();
			
			//Inizialisiseren des Players
//			player = MediaPlayer.create(this, R.raw.baby);
//			player.prepare();

//			player.setDataSource(R.raw.baby);
			
		}
		catch (IOException e)
		{
			Log.d(APP_TAG, "File not accessible", e);
		}
		catch (IllegalArgumentException e)
		{
			Log.w(APP_TAG, "Illegal Argument ", e);
		}
		catch (IllegalStateException e)
		{
			Log.w(APP_TAG, "Illegal state, call reset/restore", e);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onPlayClicked(View view)
	{
		if(!playing)
			startPlay();
		else
			stopPlay();
	}

	public void onRecordClicked(View view)
	{
		if(!recording)
			startRecording();
		else
			stopRecording();
	}
	
	private void startRecording()
	{
		Log.d(APP_TAG, "start recording ...");
		printResult("Recording ...");
		
		recorder = new MediaRecorder();
		
		try {
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			recorder.setOutputFile(outFile.getAbsolutePath());
			recorder.prepare();
			recorder.start();
			recording = true;
		} 
		catch (IllegalStateException e) 
		{
			Log.w(APP_TAG, "Invalid recorder state .. reset/release should have been called");
		} 
//		catch (IOException e) 
//		{
//			Log.w(APP_TAG, "Could not write to sd card");
//		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void stopRecording()
	{
		Log.d(APP_TAG, "stopping recording..");
		printResult("Stopped Recordin ... ");
		
		recorder.stop();
		recorder.release();
		recorder = null;
		recording = false;
	}
	
	private void startPlay()
	{
		player = new MediaPlayer();
		Log.d(APP_TAG, "starting playback ...");
		printResult("Start playing ...");
		
		try {
			player.setDataSource(outFile.getAbsolutePath());
			playing = true;
			player.prepare();
			player.start();
			
		} 
		catch (IllegalStateException e) 
		{
			Log.w(APP_TAG, "illegal state..player should be reset");
		} 
		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void stopPlay()
	{
		Log.d(APP_TAG, "stopping playback...");
		printResult("Stop playing");
		
		try {
			player.stop();
			player.release(); 
			player = null;
			playing = false;
		} 
		catch (IllegalStateException e) 
		{
			e.printStackTrace();
		} 
		
		
	}
	
	private void printResult(String s)
	{
//		TextView text = (TextView) findViewById(R.id.txtResult);
//		text.setText(s);
	}
}

