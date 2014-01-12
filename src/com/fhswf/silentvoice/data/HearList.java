package com.fhswf.silentvoice.data;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class HearList implements ITransferObject {
	
	@ElementList
	private ArrayList<HearEntry> list;
	
	public HearList() {
		list = new ArrayList<HearEntry>();
	}
	
	public ArrayList<HearEntry> getList()
	{
		return list;
	}
}

