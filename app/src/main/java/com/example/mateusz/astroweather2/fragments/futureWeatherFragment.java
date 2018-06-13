package com.example.mateusz.astroweather2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mateusz.astroweather2.R;

public class futureWeatherFragment extends Fragment {
    private ViewGroup rootView;
    TextView day1, temperature1, condition1,day2, temperature2, condition2,day3, temperature3, condition3,day4, temperature4, condition4,
            day5, temperature5, condition5;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

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
        day1 = rootView.findViewById(R.id.day1);
        day2 = rootView.findViewById(R.id.day2);
        day3 = rootView.findViewById(R.id.day3);
        day4 = rootView.findViewById(R.id.day4);
        day5 = rootView.findViewById(R.id.day5);
        temperature1 = rootView.findViewById(R.id.temperature1);
        temperature2 = rootView.findViewById(R.id.temperature2);
        temperature3 = rootView.findViewById(R.id.temperature3);
        temperature4 = rootView.findViewById(R.id.temperature4);
        temperature5 = rootView.findViewById(R.id.temperature5);
        condition1 = rootView.findViewById(R.id.condition1);
        condition2 = rootView.findViewById(R.id.condition2);
        condition3 = rootView.findViewById(R.id.condition3);
        condition4 = rootView.findViewById(R.id.condition4);
        condition5 = rootView.findViewById(R.id.condition5);
        imageView1 = rootView.findViewById(R.id.img1);
        imageView2 = rootView.findViewById(R.id.img2);
        imageView3 = rootView.findViewById(R.id.img3);
        imageView4 = rootView.findViewById(R.id.img4);
        imageView5 = rootView.findViewById(R.id.img5);
    }

    private void setData() {

    }

}
