package com.diebuc.tiendata.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.diebuc.tiendata.R;

public class ShopDetailPhotoActivity extends FragmentActivity  {

	//private String shop = "Winery";
	private String imgSrc ="winery1";
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_share_comment:
			//String msg = getString(R.string.shop_detail_photo_share, shop);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://"+getPackageName()+"/drawable/"+imgSrc));
			intent.setType("image/png");
			startActivity(Intent.createChooser(intent,getString(R.string.action_share_comment)));
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_detail_photo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_detail_photo, menu);
		return true;
	}

}
