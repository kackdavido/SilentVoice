/**
 * 
 */
package com.fhswf.silentvoice.data;

import java.util.Calendar;
import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Dario
 *
 */
@Root
public class HearEntry {
	
	@Element
	private Calendar date;
	
	@Element
	private String message;
	
	public HearEntry()
	{	
		// default constuctor
	}
	
	public HearEntry(Calendar date, String message)
	{
		this.date = date;
		this.message = message;
	}
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
		

}

