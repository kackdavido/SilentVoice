package com.fhswf.silentvoice.components;

import com.fhswf.silentvoice.listener.MyOnClickListener;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 
 * @author Dario
 *
 */
public class MyButton extends Button{

	private String message;
	private Button button;
	
	public MyButton(Context context) {
		super(context);		
		button = new Button(context);
		
		message = "";
		setOnClickListener(new MyOnClickListener(context));
	}
	
	public MyButton(Context context, AttributeSet set) {
		super(context,set);

		button = new Button(context, set);
		message = "";
		setOnClickListener(new MyOnClickListener(context));
	}
	
	public MyButton(Context context, AttributeSet set, int defStyle) {
		super(context, set, defStyle);
		button = new Button(context, set, defStyle);
		message = "";
		setOnClickListener(new MyOnClickListener(context));
	}
	

	public String getMessage() {
		if(message == "")
		{
			throw new IllegalArgumentException("Invaild Text");
		}
		
		return message;
	}

	public void setMessage(String message) {
		
		this.message = message;
	}

	public Button getButton() {
		return button;
	}
	
	
}
