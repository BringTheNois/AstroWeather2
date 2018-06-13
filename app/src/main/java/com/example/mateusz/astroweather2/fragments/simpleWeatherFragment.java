package com.example.mateusz.astroweather2.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.astroweather2.R;

public class simpleWeatherFragment extends Fragment {
    private ViewGroup rootView;
    private TextView city, longitude, latitude, temperature, pressure, condition;
    ImageView weatherIcon;

    public simpleWeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_simple_weather, container, false);
        setupViews();
        setData();
        return rootView;
    }

    private void setData() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("weather.xml", 0);

    }

    private void setupViews() {
        city = rootView.findViewById(R.id.city);
        longitude = rootView.findViewById(R.id.longitude);
        latitude = rootView.findViewById(R.id.latitude);
        temperature = rootView.findViewById(R.id.temperature);
        pressure = rootView.findViewById(R.id.pressure);
        condition = rootView.findViewById(R.id.condition);
    }

}
