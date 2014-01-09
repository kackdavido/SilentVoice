package com.fhswf.silentvoice.utility;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MyAppContext extends Application{
	
	private static Context context;
	
	public void onCreate()
	{
		super.onCreate();
		MyAppContext.context = getApplicationContext();
		
		Log.d("INFO", "Creating Context");
	}
	
	public static Context getAppContext()
	{
		return MyAppContext.context;
	}

}
