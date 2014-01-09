package com.fhswf.silentvoice.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.Display;

public class Utilities {
	
	public static void changeView(Context parent, Class<?> child)
	{
		Intent intent = new Intent(parent, child);
		parent.startActivity(intent);
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static Point getDisplaySize(final Display display) {
		final Point point = new Point();
		
		try{
			display.getSize(point);
		} catch(java.lang.NoSuchMethodError ignore) { // Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		
		return point;
	}
}
