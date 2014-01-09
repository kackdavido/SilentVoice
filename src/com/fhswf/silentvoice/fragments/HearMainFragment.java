package com.fhswf.silentvoice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fhswf.silentvoice.R;

public class HearMainFragment extends Fragment {
	
	public HearMainFragment()
	{
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hear_main,
				container, false);

		
		return rootView;
	}
	


}
