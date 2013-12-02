package com.diebuc.tiendata.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.diebuc.tiendata.R;
import com.diebuc.tiendata.activities.ShopDetailActivity;

public class ShopListFragment extends Fragment implements OnItemClickListener {

	private ListView listViewShops;
	private String[] shops;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		listViewShops.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, shops));
		listViewShops.setOnItemClickListener(this);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_shop_list, container,false);
		listViewShops = (ListView) view.findViewById(R.id.listViewShops);
		shops = getResources().getStringArray(R.array.shops_array);

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		Intent intent = new Intent(getActivity(), ShopDetailActivity.class);

		Bundle extras = new Bundle();
		extras.putString("shop", ((TextView) view).getText().toString());
		extras.putString("website", "www.winery.com.ar");
		extras.putString("email", "callcenter@winery.com.ar");
		extras.putString("address",
				"Avenida Corrientes 1234. Buenos Aires, Argentina");
		extras.putString("phone", "54 011 5609 2000");
		extras.putString("hour", "Lunes a Sábados de 09:00 a 22:00");
		extras.putString("image", "winery");

		intent.putExtras(extras);
		startActivity(intent);

	}



}
