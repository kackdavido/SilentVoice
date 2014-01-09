package com.fhswf.silentvoice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.fhswf.silentvoice.R;
import com.fhswf.silentvoice.adapter.ButtonAdapter;

public class SpeakMainFragment extends Fragment {
	
	public SpeakMainFragment()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_speak_main,
				container, false);

		
		GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        try {
			gridView.setAdapter(new ButtonAdapter(rootView.getContext()));
			Log.d("INFO", "ButtonAdapter successfull loaded");
		} catch (Exception e) {
			Log.e("ERROR", "Couldn't init ButtonAdapter. \"Exception handling fehlt\"");
			e.printStackTrace();
		}
		return rootView;
	}

}
