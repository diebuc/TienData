package com.diebuc.tiendata.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.fragments.PhotoFragment;

public class PhotoPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

	private int[] arrayPhotos = new int[] { R.drawable.winery1,R.drawable.winery2, R.drawable.elateneo,
			R.drawable.elmundodeljuguete, R.drawable.zara, };;

	public PhotoPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
		super(fragmentManager);
	}						
	
	@Override
	public Fragment getItem(int position) {

		Fragment fragment = new PhotoFragment();
		Bundle args = new Bundle();
		args.putInt(PhotoFragment.RESOURCE, arrayPhotos[position]);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return arrayPhotos.length;
	}

}
