package com.fhswf.silentvoice.data;

import java.io.File;
import java.io.IOException;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.fhswf.silentvoice.utility.MyAppContext;

@Root
public class DataStorage {
	// Funktioniert nicht
	private static DataStorage instance = null;
	private Serializer serializer;
	private String filePath = "Android/data/com.fhswf.silentvoice";

	@ElementList
	private SpeakList speakList;

	private DataStorage() throws Exception {
		speakList = new SpeakList();
		speakList = initSpeakList();
	}	

	public synchronized static DataStorage getInstance() throws Exception {
		if (instance == null)
			instance = new DataStorage();

		return instance;
	}
	
	public boolean isDirectoryAvailable(final File destDir) {
		File f = destDir;
		Log.d("INFO", "isDirectoryAvailable");
		if(f.exists()) {
			Log.d("INFO", "Directory already exsist.");
			return true;
		} 
		return false;
	}
	
	public boolean isDirCreated(final File destDir) {		
		Log.d("FILE PATH", destDir.getAbsolutePath());		
		if(destDir.mkdirs()) {
			return true;
		}
		
		return false;
	}
		
	public void writeData(String fileName, ITransferObject list) throws Exception
	{
//		serializer = new Persister();
////		list.add(new DataEntry("name","message"));
//		DataEntry entry = new DataEntry("name","message");
//		File source = getXmlFile("data.xml");
//		serializer.write(entry, source);
		Log.d("INFO", "writeData");
		try {				
				File f = new File(Environment.getExternalStorageDirectory(), filePath);			
				
				if(f.canWrite()) {
					Log.d("WRITE PATH", "Path to write " + f.getAbsolutePath());
					File writeFile = new File(f.getAbsoluteFile(), fileName);
					
					//File writeFile = getXmlFile(f.getAbsolutePath()+"/speak.xml");
//					speakList.addSpeakEntry(entry)
					
					serializer = new Persister();					
					serializer.write(list, writeFile);					
					
				} else {
					Log.e("WRITE ERROR", "Could not write file to " + f.getAbsolutePath());
				}
		} catch(IOException e) {
			Log.e("WRITE ERROR", "Could not write file " + e.getMessage());
		}
		
		
	}	
	
	// Hier exception handling
	private SpeakList initSpeakList() throws Exception {
		SpeakList data = new SpeakList();
		serializer = new Persister();
		File fileDir = new File(Environment.getExternalStorageDirectory(), filePath);
		
		if(!isDirectoryAvailable(fileDir)) {
			if(!isDirCreated(fileDir)) {
				Log.e("ERROR", "Couldn´t create directory: \"" + fileDir.getAbsolutePath() + "\".");
				// PROGRAMM ABBRUCH + FEHLERMELDUNG
			}
		}		
		
		File source = new File(fileDir.getAbsolutePath(), "speak.xml");	
		
		if(!fileDir.canWrite()) {
			Log.e("ERROR", "File: " + fileDir.getAbsolutePath() + "speak.xml not writeable");
		} 		
		
		try {		
			if(!source.canWrite()) {
				Log.e("ERROR", "File: " + fileDir.getAbsolutePath() + "/speak.xml not writeable");
				serializer.write(speakList, source);		
				Log.d("INFO", "Empty data file \"" + fileDir + "/speak.xml" + "\" successfull loaded");
			} else {
				data = serializer.read(SpeakList.class, source);
			}
		}
		catch(IOException e) {
			Log.e("ERROR", "Ich habs gewusst " + e.getMessage());
		}
		
		if( data != null ) {
			Log.d("INFO", "Data from File \"" + fileDir + "/speak.xml" + "\" successfull loaded");
		} else {
			Log.e("ERROR", "Can´t load data fromFile \"" + fileDir + "speak.xml" + "\"");
		}
		
		return data;
	}
	
	public void addSpeakEntry(SpeakEntry entry) throws Exception
	{		
		Log.d("INFO", "addSpeakEntry");
		speakList.getList().add(entry);
		writeData("speak.xml", speakList);
	}
	
	public SpeakList getSpeakList() {
		return speakList;
	}
}
