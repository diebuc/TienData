package com.diebuc.tiendata.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.helper.SimpleCustomArrayAdapter;

public class ShopCommentsFragment extends Fragment {


	private ArrayList<String> commentsList;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_shop_comment, container,
				false);
		
		ListView listViewComments = (ListView) view.findViewById(R.id.listViewComments);

	   commentsList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(
				R.array.shops_comments)));

		final SimpleCustomArrayAdapter arrayAdapter = new SimpleCustomArrayAdapter(getActivity(),commentsList);
		
		listViewComments.setAdapter(arrayAdapter);
		
		final EditText editTextComment = (EditText) view.findViewById(R.id.editTextComment);
		Button buttonAddComment = (Button) view.findViewById(R.id.buttonAddComment);
		
		buttonAddComment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!(editTextComment.getText().toString().length()==0)){
					commentsList.add(editTextComment.getText().toString());
					Collections.reverse(commentsList);
					arrayAdapter.notifyDataSetChanged();
					editTextComment.setText("");
				}
			}
		});

		return view;
		
	}

}
