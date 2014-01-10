package com.fhswf.silentvoice.adapter;

import android.R;
import android.content.Context;
import android.database.DataSetObservable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.fhswf.silentvoice.components.MyImageView;
import com.fhswf.silentvoice.data.DataStorage;

public class ImageViewAdapter extends BaseAdapter {
	private final DataSetObservable dataSetObservable = new DataSetObservable();
	private Context mContext;
	private DataStorage dataStorage;
	
	public ImageViewAdapter(final Context c) throws Exception {
		mContext = c;
		dataStorage = DataStorage.getInstance();
	}

	@Override
	public int getCount() {		
		return dataStorage.getSpeakList().getList().size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyImageView imageView;
		if(convertView == null) {
			imageView = new MyImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(256,256));
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (MyImageView) convertView;
		}
		
//		imageView.setImage(R.raw.burger_128);
		imageView.setText(dataStorage.getSpeakList().getList().get(position).getName());
		imageView.setMessage(dataStorage.getSpeakList().getList().get(position).getMessage());
		imageView.setId(position);
		
		return imageView;
	}

}
