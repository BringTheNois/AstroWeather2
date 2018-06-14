package com.example.mateusz.astroweather2.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.mateusz.astroweather2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class sunFragment extends Fragment {
    private Date date;
    private DateFormat yearFormat;
    private DateFormat monthFormat;
    private DateFormat dayFormat;
    private DateFormat hourFormat;
    private DateFormat minuteFormat;
    private TextView sun;
    private int refreshTime;
    private String longitude;
    private String latitude;
    AstroCalculator astroCalculator;
    AstroCalculator.Location location;
    AstroDateTime astroDateTime;
    private Thread thread;
    private static final int MINUTE_IN_MILISECONDS = 60000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
        thread = new Thread() {
            @Override
            public void run() {
                runThread(refreshTime);
            }
        };
        thread.start();
    }

    private void runThread(int refreshTime) {
        try {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setAstroCalculator();
                    setData();
                }
            });
            Thread.sleep(MINUTE_IN_MILISECONDS * refreshTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        thread.interrupt();
        super.onStop();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sun, container, false);
        sun = rootView.findViewById(R.id.sun);

        initTime();
        setAstroCalculator();
        setData();

        return rootView;
    }
    private void initTime() {
        date = new Date();
        yearFormat = new SimpleDateFormat("yyyy");
        monthFormat = new SimpleDateFormat("MM");
        dayFormat = new SimpleDateFormat("dd");
        hourFormat = new SimpleDateFormat("hh");
        minuteFormat = new SimpleDateFormat("mm");
    }
    @SuppressLint("SetTextI18n")
    private void setData() {
        sun.setText("Longitude: " + longitude + "\nLatitude :" + latitude
                + "\nSunrise: " + String.valueOf(astroCalculator.getSunInfo().getSunrise())
                + "\n Sunrise Azimut:" + String.valueOf(astroCalculator.getSunInfo().getAzimuthRise())
                + "\n Sunset:" + String.valueOf(astroCalculator.getSunInfo().getSunset())
                + "\n Sunset Azimut:" + String.valueOf(astroCalculator.getSunInfo().getAzimuthSet())
                + "\n Twilight morning:" + String.valueOf(astroCalculator.getSunInfo().getTwilightMorning())
                + "\n Twilight eavning:" + String.valueOf(astroCalculator.getSunInfo().getTwilightEvening())
        );
    }

    private void setAstroCalculator() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("yahoo.xml", 0);
        longitude = sharedPref.getString("longitude", String.valueOf(getResources().getString(R.string.longitude)));
        latitude = sharedPref.getString("latitude", String.valueOf(getResources().getString(R.string.latitude)));
        astroDateTime = new AstroDateTime(
                Integer.valueOf(yearFormat.format(date)),
                Integer.valueOf(monthFormat.format(date)),
                Integer.valueOf(dayFormat.format(date)),
                Integer.valueOf(hourFormat.format(date)),
                Integer.valueOf(minuteFormat.format(date)),
                Integer.valueOf(yearFormat.format(date)),
                getTimeZone(),
                false
        );

        location = new AstroCalculator.Location(
                Double.valueOf(latitude),
                Double.valueOf(longitude)
        );

        astroCalculator = new AstroCalculator(
                astroDateTime,
                location
        );

        refreshTime = Integer.valueOf(sharedPref.getString("current_refresh", String.valueOf(getResources().getString(R.string.refresh))));
    }
    private int getTimeZone() {
        Calendar c = Calendar.getInstance();

        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if (z.inDaylightTime(new Date())) {
            offset = offset + z.getDSTSavings();
        }
        return offset / 1000 / 60 / 60;
    }
}
