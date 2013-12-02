package com.diebuc.tiendata.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.helper.PhotoPagerAdapter;
import com.diebuc.tiendata.helper.ZoomOutPageTransformer;

public class ShoppingCenterPhotosFragment extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

		PhotoPagerAdapter pagerAdapter = new PhotoPagerAdapter(
				getChildFragmentManager());

		viewPager.setAdapter(pagerAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_shopping_center_photos,
				container, false);
		viewPager = (ViewPager) view.findViewById(R.id.pager);

		return view;
	}

	ViewPager viewPager;

}
