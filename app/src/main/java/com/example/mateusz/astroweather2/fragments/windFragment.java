package com.example.mateusz.astroweather2.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mateusz.astroweather2.R;

public class windFragment extends Fragment {
    private ViewGroup rootView;
    private TextView windStrength, windDirection, visibility, humidity;

    private void setupViews(){
        windStrength = rootView.findViewById(R.id.windStrength);
        windDirection = rootView.findViewById(R.id.windDirection);
        visibility = rootView.findViewById(R.id.visibility);
        humidity = rootView.findViewById(R.id.humidity);
    }
    private void setData(){
        SharedPreferences sharedPref = getActivity().getSharedPreferences("weather.xml", 0);
        windStrength.setText(windStrength.getText() + sharedPref.getString("windSpeed", "NULL") + " mph");
        windDirection.setText(windDirection.getText() + sharedPref.getString("windDirection", "NULL"));
        humidity.setText(humidity.getText() + sharedPref.getString("humidity", "NULL"));
        visibility.setText(visibility.getText() + sharedPref.getString("visibility", "NULL"));
    }

    public windFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_wind, container, false);
        setupViews();
        setData();
        return rootView;

    }

}
