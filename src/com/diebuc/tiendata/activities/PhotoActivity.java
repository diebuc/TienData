package com.diebuc.tiendata.activities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.fragments.CommunityFragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoActivity extends Activity implements OnClickListener {

	private String photoPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photo);
		
		Intent data = getIntent();
		int requestCode = getIntent().getIntExtra("requestCode", -1);
		int resultCode = getIntent().getIntExtra("resultCode", -1);

		switch (requestCode) {
		case CommunityFragment.LOAD_IMAGE:
			if (resultCode == Activity.RESULT_OK) {
				fromGallery(data);
			}
			break;
		case CommunityFragment.CAMERA:
			if (resultCode == Activity.RESULT_OK) {
				fromCamera(data);
			}
			break;
		}

		Button btnAddPhotoComment = (Button) findViewById(R.id.btnAddPhotoComment);
		btnAddPhotoComment.setOnClickListener(this);

	}

	private void fromCamera(Intent data) {
		if (data != null) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				ImageView imageView = (ImageView) findViewById(R.id.imgSelected);
				Bitmap bitmap = resizeBitmap(imageView.getWidth(),
						imageView.getHeight());
				imageView.setImageBitmap(bitmap);

				Intent mediaScan = new Intent(
						"android.intent.action.MEDIA_SCANNER_SCAN_FILE");
				File file = new File(photoPath);
				Uri contentUri = Uri.fromFile(file);
				mediaScan.setData(contentUri);
				sendBroadcast(mediaScan);
			}
		}
	}

	private void fromGallery(Intent data) {
		if (data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaColumns.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			if (cursor.moveToFirst()) {
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				ImageView imageView = (ImageView) findViewById(R.id.imgSelected);
				Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
				imageView.setImageBitmap(bitmap);
			}
		}
	}

	public Bitmap resizeBitmap(int targetW, int targetH) {
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(photoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW / targetW, photoH / targetH);
		}
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;
		return BitmapFactory.decodeFile(photoPath, bmOptions);
	}

	private File setupFile() {
		File albumDir;
		String albumName = "ejemplo";
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			albumDir = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
					albumName);
		} else {
			albumDir = new File(Environment.getExternalStorageDirectory()
					+ "/dcim/" + albumName);
		}
		albumDir.mkdirs();

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(Calendar.getInstance().getTime());

		String imageFileName = "IMG_" + timeStamp + ".jpg";
		File image = new File(albumDir + "/" + imageFileName);

		return image;
	}

	@Override
	public void onClick(View v) {

	}
}
