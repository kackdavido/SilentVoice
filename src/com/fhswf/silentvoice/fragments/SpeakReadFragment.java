package com.fhswf.silentvoice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fhswf.silentvoice.R;
import com.fhswf.silentvoice.components.MyButton;

/**
 * 
 * @author Dario
 *
 */
public class SpeakReadFragment extends Fragment{

	private MyButton mButton;
	
	public SpeakReadFragment()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_speak_read,
				container, false);

		mButton = new MyButton(rootView.getContext());
		EditText edit = (EditText) rootView.findViewById(R.id.editRead);
		
		edit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mButton.setMessage(s.toString());
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
//		LinearLayout linear = (LinearLayout) rootView.findViewById(R.id.linearLayout);
		mButton = (MyButton) rootView.findViewById(R.id.myBntRead);
		
//		mButton.setLayoutParams(new LinearLayout.LayoutParams(200, 100));
		mButton.setMessage("Bitte geben Sie einen Text ein");
		mButton.setText("Vorlesen");

//		linear.addView(mButton);
		return rootView;
	}
}


