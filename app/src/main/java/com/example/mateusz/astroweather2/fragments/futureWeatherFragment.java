package com.example.mateusz.astroweather2.fragments;


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

public class futureWeatherFragment extends Fragment {
    private ViewGroup rootView;
    TextView[] day = new TextView[5];
    TextView[] temperature = new TextView[5];
    TextView[] condition = new TextView[5];
    ImageView[] imageView = new ImageView[5];
    private int resource[] = new int[5];
    private Drawable[] weatherIconDrawable = new Drawable[5];

    public futureWeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_futere_weather, container, false);
        setupViews();
        setData();
        return rootView;
    }

    private void setupViews() {
        for (int i = 0; i < 5; i++) {
            temperature[i] = rootView.findViewById(getResources().getIdentifier("temperature" + (i +1), "id", getContext().getPackageName()));
            day[i] = rootView.findViewById(getResources().getIdentifier("day" + (i + 1), "id", getContext().getPackageName()));
            condition[i] = rootView.findViewById(getResources().getIdentifier("condition" + (i + 1), "id", getContext().getPackageName()));
            imageView[i] = rootView.findViewById(getResources().getIdentifier("img" + (i + 1), "id", getContext().getPackageName()));
        }
    }

    private void setData() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("yahoo.xml", 0);
        for (int i = 0; i < 5; i++) {
            resource[i] = getResources().getIdentifier("i" + sharedPref.getString("image" + (i + 1), "44"), "drawable", Objects.requireNonNull(getContext()).getPackageName());
            weatherIconDrawable[i] = getResources().getDrawable(resource[i], null);
            imageView[i].setImageDrawable(weatherIconDrawable[i]);
            temperature[i].setText(sharedPref.getString("high" + (i + 1), "NULL" )+ "\u00B0" + sharedPref.getString("unit", ""));
            day[i].setText(sharedPref.getString("day" + (i + 1), "NULL"));
            condition[i].setText(sharedPref.getString("description" + (i + 1), "NULL"));
        }
    }

}
