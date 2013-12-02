package com.diebuc.tiendata.fragments;

import com.diebuc.tiendata.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PhotoFragment extends Fragment {

	public static final String RESOURCE = "resource";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_shopping_center_photo,
				container, false);

		ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
		Bundle args = getArguments();

		imageView.setImageResource(args.getInt(RESOURCE));

		return view;
	}
}
