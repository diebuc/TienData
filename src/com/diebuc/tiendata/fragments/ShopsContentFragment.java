package com.diebuc.tiendata.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.activities.MainActivity;

public class ShopsContentFragment extends Fragment implements TabListener {

	Fragment[] fragments = new Fragment[] { new ShopListFragment(),
			new ShopMapFragment() };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
				
		ActionBar actionBar = ((MainActivity) getActivity())
				.getSupportActionBar();
				
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.addTab(actionBar.newTab().setText("Tiendas")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Mapa")
				.setTabListener(this));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_content_shops,
				container, false);

		fragments[0].setHasOptionsMenu(true);

		FragmentManager manager = getActivity().getSupportFragmentManager();
		manager.beginTransaction().add(R.id.mainContent, fragments[0])
				.add(R.id.mainContent, fragments[1]).commit();

		return view;
	}

	public void setContent(int tab) {
		Fragment toHide = null;
		Fragment toShow = null;
		switch (tab) {
		case 0:
			toHide = fragments[1];
			toShow = fragments[0];
			break;
		case 1:
			toHide = fragments[0];
			toShow = fragments[1];
			break;
		}

		FragmentManager manager = getChildFragmentManager();

		manager.beginTransaction().hide(toHide).show(toShow).commit();
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		setContent(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

}
