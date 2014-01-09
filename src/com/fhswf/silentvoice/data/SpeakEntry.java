/**
 * 
 */
package com.fhswf.silentvoice.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Dario
 *
 */
@Root
public class SpeakEntry {
	
	@Element
	private String name;
	
	@Element
	private String message;
	
	public SpeakEntry()
	{	
		// default constuctor
	}
	
	public SpeakEntry(String name, String message)
	{
		this.name = name;
		this.message = message;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
		

}
