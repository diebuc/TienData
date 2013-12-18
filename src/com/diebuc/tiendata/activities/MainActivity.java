package com.diebuc.tiendata.activities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.fragments.CommunityFragment;
import com.diebuc.tiendata.fragments.PhotoDialogFragment.NoticeDialogListener;
import com.diebuc.tiendata.fragments.ShoppingCenterPhotosFragment;
import com.diebuc.tiendata.fragments.ShopsContentFragment;

public class MainActivity extends ActionBarActivity implements
		NoticeDialogListener {

	private static final int LOAD_IMAGE = 1;
	private static final int CAMERA = 2;

	String photoPath;
	
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
				.add(R.id.contentFrame, fragments[2]).hide(fragments[1])
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

	@Override
	public void onDialogPositiveClick() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, LOAD_IMAGE);
	}

	@Override
	public void onDialogNegativeClick() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File photo = setupFile();
		String photoPath = photo.getAbsolutePath();
		intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,Uri.fromFile(photo));
		startActivityForResult(intent, CAMERA);
	}

    public Bitmap resizeBitmap(int targetW, int targetH) {
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(photoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
		}

		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		return BitmapFactory.decodeFile(photoPath, bmOptions);    	
    }
	
	private File setupFile() {
		File albumDir;
		String albumName ="ejemplo";
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.FROYO){
			albumDir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),albumName);
			
		}else{
			albumDir=new File(Environment.getExternalStorageDirectory()+"/dcim/"+albumName);
		}
		albumDir.mkdirs();
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Calendar.getInstance().getTime());
		
		String imageFileName = "IMG_" +timeStamp +".jpg";
		File image = new File(albumDir + "/" + imageFileName);
		
		return image;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case LOAD_IMAGE:
			if (resultCode == RESULT_OK) {
				fromGallery(data);
			}
			break;
		case CAMERA:
			if (resultCode == RESULT_OK) {
				fromCamera(data);
			}
			break;
		}

	}

	private void fromCamera(Intent data) {
		if (data != null) {
			Bundle extras = data.getExtras();
			if (extras != null) {

				
				//Bitmap bitmap = resizeBitmap(img.getWidth(),img.getHeight());
				//img.setImageBitmap(bitmap);
				
				Intent mediaScan = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
				File file = new File(photoPath);
				Uri contentUri = Uri.fromFile(file);
				mediaScan.setData(contentUri);
				this.sendBroadcast(mediaScan);
				
				// ImageView img = (ImageView)findViewById();
				// img.setImageBitmap((Bitmap)extras.get("data"));
			}
		}
	}

	private void fromGallery(Intent data) {
		if (data != null) {

			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			if (cursor.moveToFirst()) {
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();

				// ImageView img = (ImageView)findViewById();
				// img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			}
		}
	}

}
