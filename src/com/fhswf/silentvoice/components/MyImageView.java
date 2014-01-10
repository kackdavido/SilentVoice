package com.fhswf.silentvoice.components;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.fhswf.silentvoice.listener.MyOnClickListener;

public class MyImageView extends View {
	private String text;
	private String message;
	private ImageView imageView;
		
	public MyImageView(final Context context) {
		super(context);
		imageView = new ImageView(context);		
		this.message = "";
		setOnClickListener(new MyOnClickListener(context));			
	}
	
	public ImageView getImage() {
		if(imageView == null) {
			throw new IllegalArgumentException("Invalid image");
		}
		return imageView;
	}
	
	public void setImage(final ImageView image) {
		this.imageView = image;
	}
	
	public String getMessage() {
		if(message == "")
		{
			throw new IllegalArgumentException("Invaild text");
		}		
		return message;
	}

	public void setMessage(final String message) {		
		this.message = message;
	}
	
	public String getText() {
		if(text == "") {
			throw new IllegalArgumentException("Invalid text");
		}
		return text;
	}
	
	public void setText(final String text) {
		this.text = text;
	}
}
