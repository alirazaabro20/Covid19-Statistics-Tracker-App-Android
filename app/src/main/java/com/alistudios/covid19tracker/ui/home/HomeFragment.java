package com.alistudios.covid19tracker.ui.home;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alistudios.covid19tracker.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {
    private TextView totalConfirmed,totalDeaths,totalRecovered;
    private TextView hTodayDeaths,hActive,hCritical,hCasesperMillion,hNewCases,hAffectedCountries;
    private ProgressBar progressBar;
    private TextView lastUpdated;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        totalConfirmed=(TextView) root.findViewById(R.id.total_confirmed_cases);
        totalDeaths=(TextView) root.findViewById(R.id.total_deaths);
        totalRecovered=(TextView) root.findViewById(R.id.total_recovered);

        hTodayDeaths=(TextView) root.findViewById(R.id.home_todays_death);
        hActive=(TextView) root.findViewById(R.id.home_active_cases);
        hCasesperMillion=(TextView) root.findViewById(R.id.cases_per_one_million);
        hCritical=(TextView) root.findViewById(R.id.home_critical_cases);
        hNewCases=(TextView) root.findViewById(R.id.home_today_cases);
        hAffectedCountries=(TextView) root.findViewById(R.id.affected_countries);



        progressBar=(ProgressBar) root.findViewById(R.id.progress_bar);
progressBar.setVisibility(View.VISIBLE);

        getStringData();

        return root;
    }

    private void getStringData() {

        RequestQueue referenceQueue= Volley.newRequestQueue(getActivity());
      final  String url ="https://corona.lmao.ninja/v2/all";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    totalConfirmed.setText(jsonObject.getString("cases"));
                    totalDeaths.setText(jsonObject.getString("deaths"));
                    totalRecovered.setText(jsonObject.getString("recovered"));
                    hNewCases.setText(jsonObject.getString("todayCases"));
                    hActive.setText(jsonObject.getString("active"));
                    hCritical.setText(jsonObject.getString("critical"));
                    hTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    hCasesperMillion.setText(jsonObject.getString("casesPerOneMillion"));
                    hAffectedCountries.setText(jsonObject.getString("affectedCountries"));




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
               Toast.makeText(getActivity(),"Connection Error",Toast.LENGTH_SHORT);

            }
        }

        );

        referenceQueue.add(stringRequest);
    }
}
