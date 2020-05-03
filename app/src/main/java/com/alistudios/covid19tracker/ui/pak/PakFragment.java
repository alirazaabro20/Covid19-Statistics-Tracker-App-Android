package com.alistudios.covid19tracker.ui.pak;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alistudios.covid19tracker.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.android.volley.VolleyLog.TAG;

public class PakFragment extends Fragment {

    private TextView pkTotal,pkDeaths,pkRecovered,pkCrictical,pkTests,pkActive,pkTodayTotal,pkTodayDeaths;
    private ProgressBar mProgressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pak, container, false);
        pkTotal=(TextView) root.findViewById(R.id.pak_total_confirmed);
        pkDeaths=(TextView) root.findViewById(R.id.pak_total_deaths);
        pkActive=(TextView) root.findViewById(R.id.pak_active);

        pkRecovered=(TextView) root.findViewById(R.id.pak_total_recovered);
        pkCrictical=(TextView) root.findViewById(R.id.pak_critical);
        pkTodayTotal=(TextView) root.findViewById(R.id.pak_today);

        pkTests=(TextView) root.findViewById(R.id.pak_tests);
        pkTodayDeaths=(TextView) root.findViewById(R.id.pak_deaths_today);
        mProgressBar=(ProgressBar) root.findViewById(R.id.progressBarID);



        getPkData();


        return root;
    }

    private void getPkData() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        final String URL = "https://corona.lmao.ninja/v2/countries/pakistan";



        StringRequest request=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProgressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());


                    pkTotal.setText(jsonObject.getString("cases"));
                    pkDeaths.setText(jsonObject.getString("deaths"));
                    pkRecovered.setText(jsonObject.getString("recovered"));


                    pkActive.setText(jsonObject.getString("active"));
                    pkCrictical.setText(jsonObject.getString("critical"));

                    pkTodayTotal.setText(jsonObject.getString("todayCases"));
                    pkTodayDeaths.setText(jsonObject.getString("todayDeaths"));

                    pkTests.setText(jsonObject.getString("tests"));








                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressBar.setVisibility(View.GONE);
                Log.d(TAG, "onErrorResponse: ");
            }
        }

        );

        queue.add(request);



    }
}
