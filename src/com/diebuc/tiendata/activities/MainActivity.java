package com.diebuc.tiendata.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.fragments.CommunityFragment;
import com.diebuc.tiendata.fragments.ShoppingCenterPhotosFragment;
import com.diebuc.tiendata.fragments.ShopsContentFragment;

public class MainActivity extends ActionBarActivity {

	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private String[] drawerOptions;
	private Fragment[] fragments = new Fragment[] {
			new ShoppingCenterPhotosFragment(), new ShopsContentFragment(),
			new CommunityFragment() };

	private ActionBarDrawerToggle actionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		drawerList = (ListView) findViewById(R.id.leftDrawer);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerOptions = getResources().getStringArray(R.array.drawer_options);

		drawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, drawerOptions));

		drawerList.setItemChecked(0, true);
		drawerList.setOnItemClickListener(new DrawerItemClickListener());

		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerClosed(View drawerView) {
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}
		};
		drawerLayout.setDrawerListener(actionBarDrawerToggle);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setTitle(drawerOptions[0]);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		FragmentManager manager = getSupportFragmentManager();

		manager.beginTransaction().add(R.id.contentFrame, fragments[0])
				.add(R.id.contentFrame, fragments[1])
				.add(R.id.contentFrame, fragments[2],"community").hide(fragments[1])
				.hide(fragments[2]).commit();

	}

	public void setContent(int index) {

		Fragment[] toHide = null;
		Fragment[] toShow = null;
		ActionBar actionBar = getSupportActionBar();

		switch (index) {
		case 0:
			toHide = new Fragment[] { fragments[1], fragments[2] };
			toShow = new Fragment[] { fragments[0] };
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			break;
		case 1:
			toHide = new Fragment[] { fragments[0], fragments[2] };
			toShow = new Fragment[] { fragments[1] };
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			break;
		case 2:
			toHide = new Fragment[] { fragments[0], fragments[1] };
			toShow = new Fragment[] { fragments[2] };
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			break;
		}

		actionBar.setTitle(drawerOptions[index]);

		FragmentManager manager = getSupportFragmentManager();

		FragmentTransaction transaction = manager.beginTransaction();
		for (Fragment fragment : toShow) {
			transaction.show(fragment);
		}
		for (Fragment fragment : toHide) {
			transaction.hide(fragment);
		}
		transaction.commit();

		drawerList.setItemChecked(index, true);
		drawerLayout.closeDrawer(drawerList);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (drawerLayout.isDrawerOpen(drawerList)) {
				drawerLayout.closeDrawer(drawerList);
			} else {
				drawerLayout.openDrawer(drawerList);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}

	class DrawerItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {

			setContent(position);

		}
	}

}
