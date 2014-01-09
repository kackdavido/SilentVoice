package com.fhswf.silentvoice.data;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class SpeakList implements ITransferObject {
	
	@ElementList
	private ArrayList<SpeakEntry> list;
	
	public ArrayList<SpeakEntry> getList()
	{
		return list;
	}
}
