package com.example.mateusz.astroweather2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mateusz.astroweather2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class comboWeatherFragment extends Fragment {


    public comboWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_combo_weather, container, false);
    }

}
