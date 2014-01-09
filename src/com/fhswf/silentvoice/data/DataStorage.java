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

	private static DataStorage instance = null;
	private Serializer serializer;
	private String filePath = "Android/data/com.fhswf.silentvoice";

	@ElementList
	private SpeakList speakList;

	private DataStorage() throws Exception {
		speakList = new SpeakList();
		speakList = initSpeakList();
	}	

	public static DataStorage getInstance() throws Exception {
		if (instance == null)
			instance = new DataStorage();

		return instance;
	}	
		
	public void writeData(String fileName, ITransferObject list) throws Exception
	{
//		serializer = new Persister();
////		list.add(new DataEntry("name","message"));
//		DataEntry entry = new DataEntry("name","message");
//		File source = getXmlFile("data.xml");
//		serializer.write(entry, source);
		
		try {				
				File f = new File(Environment.getExternalStorageDirectory(), filePath);
				//File f = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), fileName);
				Log.d("FILE PATH", f.getAbsolutePath());
				if(f.exists()) {
					Log.d("INFO", "Directory already exsist.");
				} else {
					f.mkdirs();
					Log.d("INFO", "Directory created.");
				}
				
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

	// Wird glaub ich gar nicht benötigt!!!!!!!!!!!!!!!
	private File getXmlFile(final String name) {
		File sourceDir = MyAppContext.getAppContext().getFilesDir();
		sourceDir.mkdir();	
		Log.d("INFO", sourceDir.getAbsolutePath());
		
		File source = new File(sourceDir, "speak.xml");
		//File source = new File(name);
		
		if(!source.canWrite())
			Toast.makeText(MyAppContext.getAppContext(), "can not Write", Toast.LENGTH_SHORT).show();
		
		
		if(source.mkdirs() || source.isDirectory())
			Toast.makeText(MyAppContext.getAppContext(), MyAppContext.getAppContext().getFilesDir().toString(), Toast.LENGTH_SHORT).show();
		else if(source == null)
			Toast.makeText(MyAppContext.getAppContext(), "arschlecken", Toast.LENGTH_SHORT).show();

			
		return source;
	}
	
	// Hier exception handling
	private SpeakList initSpeakList() throws Exception {
		serializer = new Persister();
		File fileDir = new File(Environment.getExternalStorageDirectory(), filePath);
		File source = new File(fileDir, "speak.xml");
		
		SpeakList data = serializer.read(SpeakList.class, source);
		
		if( data != null ) {
			Log.d("INFO", "Data from File \"" + fileDir + "speak.xml" + "\" successfull loaded");
		} else {
			Log.e("ERROR", "Can´t load data fromFile \"" + fileDir + "speak.xml" + "\"");
		}
		
		return data;
	}
	
	public void addSpeakEntry(SpeakEntry entry) throws Exception
	{		
		speakList.getList().add(entry);
		writeData("speak.xml", speakList);
	}
	
	public SpeakList getSpeakList() {
		return speakList;
	}
}
