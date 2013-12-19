package com.diebuc.tiendata.fragments;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.activities.PhotoActivity;
import com.diebuc.tiendata.fragments.PhotoDialogFragment.NoticeDialogListener;
import com.diebuc.tiendata.helper.PhotoListAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class CommunityFragment extends Fragment implements NoticeDialogListener {

	public static final int LOAD_IMAGE = 1;
	public static final int CAMERA = 2;

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

		View view = inflater.inflate(R.layout.fragment_community, container,
				false);

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
	public void onDialogPositiveClick() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, LOAD_IMAGE);
	}

	@Override
	public void onDialogNegativeClick() {
		/*
		 * Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); File
		 * photo = setupFile(); String photoPath = photo.getAbsolutePath();
		 * intent
		 * .putExtra(android.provider.MediaStore.EXTRA_OUTPUT,Uri.fromFile(
		 * photo)); startActivityForResult(intent, CAMERA);
		 */
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Intent intent = new Intent(getActivity(), PhotoActivity.class);
		intent.setData(data.getData()); 
		intent.putExtra("data", data.getExtras());
		intent.putExtra("requestCode", requestCode);
		intent.putExtra("resultCode", resultCode);
		startActivity(intent);

	}

}
