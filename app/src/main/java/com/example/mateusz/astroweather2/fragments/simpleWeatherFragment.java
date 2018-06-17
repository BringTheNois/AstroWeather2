package com.example.mateusz.astroweather2.fragments;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.astroweather2.R;

import java.util.Objects;

public class simpleWeatherFragment extends Fragment {
    private ViewGroup rootView;
    private TextView city, longitude, latitude, temperature, pressure, condition;
    ImageView weatherIcon;
    SharedPreferences sharedPref;

    public simpleWeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_simple_weather, container, false);
        sharedPref = getActivity().getSharedPreferences("yahoo.xml", 0);
        setupViews();
        setData();
        return rootView;
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        city.setText(sharedPref.getString("city", "Lodz"));
        longitude.setText(sharedPref.getString("longitude" ,"51"));
        latitude.setText(sharedPref.getString("latitude" ,"19"));
        temperature.setText(temperature.getText()+ sharedPref.getString("current_temperature", "NULL") + "\u00B0" + sharedPref.getString("unit", ""));
        condition.setText(sharedPref.getString("current_description", "NULL"));
        int resource = getResources().getIdentifier("i" + sharedPref.getString("current_image", "44"), "drawable" , Objects.requireNonNull(getContext()).getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resource,null);
        weatherIcon.setImageDrawable(weatherIconDrawable);
        pressure.setText(pressure.getText()+ String.format("%s %s", sharedPref.getString("pressure", "NULL"), sharedPref.getString("pressureUnit", "NULL")));
    }

    private void setupViews() {
        city = rootView.findViewById(R.id.city);
        longitude = rootView.findViewById(R.id.longitude);
        latitude = rootView.findViewById(R.id.latitude);
        temperature = rootView.findViewById(R.id.temperature);
        pressure = rootView.findViewById(R.id.pressure);
        condition = rootView.findViewById(R.id.condition);
        weatherIcon = rootView.findViewById(R.id.weatherIcon);
    }

}
