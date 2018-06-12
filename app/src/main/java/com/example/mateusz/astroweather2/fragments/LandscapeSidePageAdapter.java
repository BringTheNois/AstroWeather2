package com.example.mateusz.astroweather2.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mateusz.astroweather2.fragments.SunMoonFragment;
import com.example.mateusz.astroweather2.fragments.comboWeatherFragment;

public class LandscapeSidePageAdapter extends FragmentStatePagerAdapter {
    public static final int NUM_PAGES = 2;

    public LandscapeSidePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new SunMoonFragment();
            }
            case 1: {
                return new comboWeatherFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
