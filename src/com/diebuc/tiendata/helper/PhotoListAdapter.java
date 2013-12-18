package com.diebuc.tiendata.helper;

import com.diebuc.tiendata.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	
	public PhotoListAdapter(Context context){
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return 25;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView==null){
			convertView = inflater.inflate(R.layout.photo_list_item,null);
			ImageView photo = (ImageView) convertView.findViewById(R.id.imageViewPhoto);
			TextView comment =(TextView) convertView.findViewById(R.id.textViewComment);
			comment.setText("muy buena foto che");
			photo.setImageResource(R.drawable.zara);			
		}
		/*else{
			
		}*/
		
		return convertView;
	}

}
