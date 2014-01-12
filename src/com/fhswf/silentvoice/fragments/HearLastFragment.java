package com.fhswf.silentvoice.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.fhswf.silentvoice.R;
import com.fhswf.silentvoice.data.DataStorage;
import com.fhswf.silentvoice.data.HearEntry;
import com.fhswf.silentvoice.data.HearList;

public class HearLastFragment extends Fragment {
	

	
	public HearLastFragment()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hear_last,
				container, false);

		ArrayList<HearEntry> hearList = DataStorage.getInstance().getHearList().getList();
		
		List valueList = new ArrayList<String>();
		for(int i=0; i < hearList.size(); i++)
		{
			valueList.add(hearList.get(i).getMessage().toString());
		}
		
		ListAdapter adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, valueList);
		final ListView lv = (ListView) rootView.findViewById(R.id.hearHistoryList);
		
		lv.setAdapter(adapter);
		
		return rootView;
	}

}
