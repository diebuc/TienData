package com.diebuc.tiendata.fragments;


import com.diebuc.tiendata.R;
import com.diebuc.tiendata.helper.PhotoListAdapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CommunityFragment extends Fragment {

	private ListView listViewPhotos;
	private ImageButton buttonTakePhoto;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		PhotoListAdapter adapter = new PhotoListAdapter(getActivity());
		listViewPhotos.setAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view  = inflater.inflate(R.layout.fragment_community, container, false);
		
		listViewPhotos = (ListView) view.findViewById(R.id.listViewPhotos);
		buttonTakePhoto = (ImageButton) view.findViewById(R.id.buttonTakePhoto);
		
		buttonTakePhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				new PhotoDialogFragment().show(getChildFragmentManager(), "");
				
			}
		});
		
		return view;
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

}


