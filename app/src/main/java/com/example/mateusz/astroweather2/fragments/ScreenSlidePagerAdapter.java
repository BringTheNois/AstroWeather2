package com.example.mateusz.astroweather2.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_PAGES = 5;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new simpleWeatherFragment();
            }
            case 1: {
                return new windFragment();
            }
            case 2: {
                return new futureWeatherFragment();
            }
            case 3: {
                return new sunFragment();
            }
            case 4: {
                return new moonFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
