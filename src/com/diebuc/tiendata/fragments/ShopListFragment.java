package com.diebuc.tiendata.fragments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.diebuc.tiendata.R;
import com.diebuc.tiendata.activities.ShopDetailActivity;
import com.diebuc.tiendata.data.Store;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShopListFragment extends Fragment implements OnItemClickListener {

	private ListView listViewShops;
	private String[] shops;
	private RequestQueue requestQueue;

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

		View view = inflater.inflate(R.layout.fragment_shop_list, container,
				false);
		listViewShops = (ListView) view.findViewById(R.id.listViewShops);
		shops = getResources().getStringArray(R.array.shops_array);

		requestQueue = Volley.newRequestQueue(getActivity());
		APICall();

		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
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

	public void APICall() {
		String url = "https://tiendatabackend.herokuapp.com/stores.json";

		Response.ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("ERROR", Log.getStackTraceString(error));
			}

		};

		Response.Listener<JSONArray> listenerArray = new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray response) {
				List<Store> storesList = null;
				ObjectMapper mapper = new ObjectMapper();
				try {
					storesList = mapper.readValue(response.toString(),
							new TypeReference<ArrayList<Store>>() {
							});
				} catch (JsonParseException e) {
					Log.e("ERROR", Log.getStackTraceString(e));
				} catch (JsonMappingException e) {
					Log.e("ERROR", Log.getStackTraceString(e));
				} catch (IOException e) {
					Log.e("ERROR", Log.getStackTraceString(e));
				}

			}
		};

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
				listenerArray, errorListener);

		/*
		 * JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
		 * Request.Method.GET, url, null, successListener, errorListener);
		 * 
		 * requestQueue.add(jsonObjectRequest);
		 */

		requestQueue.add(jsonArrayRequest);

	}

}
