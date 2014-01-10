package com.fhswf.silentvoice.data;

import java.io.File;
import java.io.IOException;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.Environment;
import android.util.Log;

@Root
public class DataStorage {
	// Funktioniert nicht
	private static DataStorage instance = null;
	private Serializer serializer;
	private String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/com.fhswf.silentvoice";

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
		
		try {
			if(destDir.getParentFile().mkdirs()) {				
				return true;
			}
		} catch (Exception e) {
			Log.e("ERROR", "Exception in isDirCreated");
			e.printStackTrace();
		}		
		
		return false;
	}
		
	public void writeData(String fileName, ITransferObject list) throws Exception
	{
		Log.d("INFO", "writeData");
		try {				
				File f = new File(filePath);			
				
				if(f.canWrite()) {
					Log.d("WRITE PATH", "Path to write " + f.getAbsolutePath());
					File writeFile = new File(f.getAbsoluteFile(), fileName);
					
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
		File source = new File(filePath, "speak.xml");	
		
		if(!isDirectoryAvailable(source)) {
			if(!isDirCreated(source)) {
				Log.e("ERROR", "Couldn´t create directory: \"" + source.getAbsolutePath() + "\".");
				// PROGRAMM ABBRUCH + FEHLERMELDUNG
			}
		}			
		
		try {		
			if(!source.canRead()) {
				Log.d("INFO", "File: " + source.getAbsolutePath() + " not readable. First startup!");				
			} else {
				data = serializer.read(SpeakList.class, source);
			}
		}
		catch(IOException e) {
			Log.e("ERROR", "Ich habs gewusst " + e.getMessage());
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
