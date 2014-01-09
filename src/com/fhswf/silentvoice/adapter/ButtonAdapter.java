package com.fhswf.silentvoice.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.fhswf.silentvoice.components.MyButton;
import com.fhswf.silentvoice.data.DataStorage;

public class ButtonAdapter extends BaseAdapter {
	private final DataSetObservable dataSetObservable = new DataSetObservable();
	private Context mContext;
	private DataStorage dataStorage;
	
	public ButtonAdapter(Context c) throws Exception
	{
		mContext = c;
		dataStorage = DataStorage.getInstance();
	}
	
	@Override
	public int getCount() 
	{
		return dataStorage.getSpeakList().getList().size();
	}

	@Override
	public Object getItem(int position) 
	{
		return null;
	}

	@Override
	public long getItemId(int position) 
	{
		return 0;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
        MyButton button;        
        // Test        
//        Point displaySize = Utilities.getDisplaySize(parent.getDisplay());
        
        if (convertView == null)
        { 
            button = new MyButton(mContext);
            // Test
//            button.setLayoutParams(new GridView.LayoutParams(displaySize.x/text.length, 100));
            button.setLayoutParams(new GridView.LayoutParams(160, 100));
            button.setPadding(8, 8, 8, 8);
            
        } 
        else 
        {
            button = (MyButton) convertView;
            
        }       
       
        button.setText(dataStorage.getSpeakList().getList().get(position).getName());
        button.setMessage(dataStorage.getSpeakList().getList().get(position).getMessage());     
        button.setTextColor(Color.BLACK);  
        button.setBackgroundColor(Color.parseColor("#80000000"));  
        button.setId(position);
//        button.setOnClickListener(new MyOnClickListener(mContext));

        return button;
	}
	
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		dataSetObservable.registerObserver(observer);
	}
	
	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		dataSetObservable.unregisterObserver(observer);
	}
	
	/**
	 * Notifies the attached View that the underlying data has been changed
     * and it should refresh itself.
	 */
	public void notifyDataSetChanged() {
		dataSetObservable.notifyChanged();
	}
	
	public void notifyDataSetInvalidated() {
		dataSetObservable.notifyInvalidated();
	}
}
