package com.diebuc.tiendata.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.diebuc.tiendata.R;

public class ShopDetailActivity extends FragmentActivity  {

	private TextView shop;
	private TextView address;
	private TextView phone;
	private TextView hour;
	private TextView website;
	private TextView email;
	private String imageSrc;
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_share_comment:
			String msg = getString(R.string.shop_detail_share, shop.getText().toString(),address.getText().toString(),phone.getText().toString());
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, msg);
			intent.setType("text/plain");
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
		setContentView(R.layout.activity_shop_detail);

		shop = (TextView) findViewById(R.id.textViewShopName);
		address = (TextView) findViewById(R.id.textViewAddress);
		website = (TextView) findViewById(R.id.textViewWebSite);
		email = (TextView) findViewById(R.id.textViewEmail);
		phone = (TextView) findViewById(R.id.textViewPhone);
		hour = (TextView) findViewById(R.id.textViewHour);

		final Button photos = (Button) findViewById(R.id.buttonPhotos);
		
		photos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), ShopDetailPhotoActivity.class);
				intent.putExtra("image", imageSrc);
				startActivity(intent);
			}
		});
		
	
		Button buttonCall = (Button) findViewById(R.id.buttonCall);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			imageSrc = extras.getString("image");
			shop.setText(extras.getString("shop"));
			address.setText(extras.getString("address"));
			hour.setText(extras.getString("hour"));
			phone.setText(extras.getString("phone"));
			website.setText(extras.getString("website"));
			email.setText(extras.getString("email"));
			Linkify.addLinks(phone, Linkify.ALL);
			Linkify.addLinks(website, Linkify.ALL);
			Linkify.addLinks(email, Linkify.ALL);
		}

		buttonCall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:"
						+ phone.getText().toString().trim()));
				startActivity(intent);
			}
	});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_detail, menu);
		return true;
	}


}
