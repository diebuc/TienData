package com.diebuc.tiendata.activities;

import com.db.shoppingcenter.R;
import com.diebuc.tiendata.fragments.ShopListFragment;
import com.diebuc.tiendata.fragments.ShopMapFragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;

public class ShopsActivity extends ActionBarActivity implements TabListener {

	Fragment[] fragments = new Fragment[] { new ShopListFragment(),
			new ShopMapFragment() };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);

		fragments[0].setHasOptionsMenu(true);
		
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().add(R.id.mainContent, fragments[0])
				.add(R.id.mainContent, fragments[1]).commit();

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.addTab(actionBar.newTab().setText("Tiendas")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Mapa")
				.setTabListener(this));

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

		FragmentManager manager = getSupportFragmentManager();

		manager.beginTransaction().hide(toHide).show(toShow).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
