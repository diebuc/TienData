package com.diebuc.tiendata.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.diebuc.tiendata.R;

public class PhotoDialogFragment extends DialogFragment{

	NoticeDialogListener listener;
	
	
	/*@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			//listener = (NoticeDialogListener)getActivity();
			listener = (NoticeDialogListener)getTargetFragment();
		}catch(ClassCastException e){
			Log.e("ERROR", Log.getStackTraceString(e));
		}
	}*/

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		try{
			listener = (NoticeDialogListener)getActivity().getSupportFragmentManager().findFragmentByTag("community");
		}catch(ClassCastException e){
			Log.e("ERROR", Log.getStackTraceString(e));
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
        builder.setMessage(R.string.photo_dialog_message)
        .setPositiveButton(R.string.photo_dialog_gallery, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	listener.onDialogPositiveClick();
            }
        })
        .setNegativeButton(R.string.photo_dialog_camera, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	listener.onDialogNegativeClick();
            }
        });
		
		return builder.create();
		
	}
	
	public interface NoticeDialogListener{
		public void onDialogPositiveClick();
		public void onDialogNegativeClick();
	}
	
}