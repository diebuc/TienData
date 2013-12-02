package com.diebuc.tiendata.helper;


import java.util.List;

import com.diebuc.tiendata.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleCustomArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final List<String> values;

	public SimpleCustomArrayAdapter(Context context, List<String> values) {
		super(context, R.layout.row_layout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_layout, parent, false);
		TextView textView = (TextView) rowView
				.findViewById(R.id.textViewComment);
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.imageViewIcon);
		textView.setText(values.get(position));
		//String s = values.get(position);
		imageView.setImageResource(R.drawable.ic_comment);

		return rowView;
	}
}
