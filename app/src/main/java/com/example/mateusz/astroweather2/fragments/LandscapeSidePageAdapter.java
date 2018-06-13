package com.example.mateusz.astroweather2.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class LandscapeSidePageAdapter extends FragmentStatePagerAdapter {
    public static final int NUM_PAGES = 2;

    public LandscapeSidePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new comboWeatherFragment();
            }
            case 1: {
                return new SunMoonFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
