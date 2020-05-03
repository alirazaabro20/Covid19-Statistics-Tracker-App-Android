package com.alistudios.covid19tracker.ui.country;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alistudios.covid19tracker.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.CountryAdapter;
import model.Country;

import static com.android.volley.VolleyLog.TAG;
import static com.android.volley.VolleyLog.e;

public class CountryFragment extends Fragment {


    private RecyclerView recyclerView;
    private ProgressBar cProgressBar;

    ArrayList<Country> countries;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_country, container, false);




        recyclerView=root.findViewById(R.id.county_recyclerview);
        cProgressBar=root.findViewById(R.id.country_progress_bar);
        cProgressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        getDataHttps();

        return root;
    }

    private void setRecyclerView(){

        CountryAdapter countryAdapter=new CountryAdapter(countries);
        recyclerView.setAdapter(countryAdapter);
    }

    private void getDataHttps() {

        final String URL = "https://corona.lmao.ninja/v2/countries";

        countries =new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cProgressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.d(TAG, "onResponse: "+response);

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            JSONObject innerObject=object.getJSONObject("countryInfo");
                            String flag=innerObject.getString("flag");
                            countries.add(new Country(object.getString("country"),
                                    object.getString("cases"),
                                    object.getString("deaths"), object.getString("recovered"),
                                   flag
                            ));
                        }
                        setRecyclerView();

                    } catch (JSONException e) {
                        Toast.makeText(getContext(),"Failed to get Data",Toast.LENGTH_SHORT);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cProgressBar.setVisibility(View.GONE);
                Log.e(TAG, "onErrorResponse: "+error );
                Toast.makeText(getContext(),"Error"+error,Toast.LENGTH_SHORT);
            }
        }
        );


        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}
