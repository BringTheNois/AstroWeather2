package com.example.mateusz.astroweather2.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabletPageAdapter extends FragmentStatePagerAdapter{
    public static final int NUM_PAGES = 1;

    public TabletPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new tabletFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
