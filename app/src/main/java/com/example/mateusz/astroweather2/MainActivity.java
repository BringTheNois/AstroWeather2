package com.example.mateusz.astroweather2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.mateusz.astroweather2.fragments.LandscapeSidePageAdapter;
import com.example.mateusz.astroweather2.fragments.ScreenSlidePagerAdapter;
import com.example.mateusz.astroweather2.fragments.TabletPageAdapter;
import com.example.mateusz.astroweather2.yahoo.data.Atmosphere;
import com.example.mateusz.astroweather2.yahoo.data.Channel;
import com.example.mateusz.astroweather2.yahoo.data.Item;
import com.example.mateusz.astroweather2.yahoo.data.Location;
import com.example.mateusz.astroweather2.yahoo.data.Wind;
import com.example.mateusz.astroweather2.yahoo.service.WeatherServiceCallback;
import com.example.mateusz.astroweather2.yahoo.service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ViewPager portraitPhonePager;
    private PagerAdapter portraitPhoneAdapter;
    private ViewPager landscapePhonePager;
    private PagerAdapter landscapePhoneAdapter;
    private YahooWeatherService service;
    private SharedPreferences sharedPreferences;
    private ViewPager portraitTabletPager;
    private PagerAdapter portraitTabletAdapter;
    private ViewPager portraitTabletLPager;
    private PagerAdapter portraitTabletLAdapter;

    public boolean checkScreen(){
        Configuration config = getResources().getConfiguration();
        if((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    private void setupView(){
        Configuration newConfig = getResources().getConfiguration();
        if (!checkScreen()){
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setContentView(R.layout.activity_phone_land);
                landscapePhonePager = findViewById(R.id.viewPagerHorizontal);
                landscapePhoneAdapter = new LandscapeSidePageAdapter(getSupportFragmentManager());
                landscapePhonePager.setAdapter(landscapePhoneAdapter);

            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                setContentView(R.layout.activity_main);
                portraitPhonePager = findViewById(R.id.viewPagerPortrait);
                portraitPhoneAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                portraitPhonePager.setAdapter(portraitPhoneAdapter);
            }
        }else {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setContentView(R.layout.activity_tablet_land);
                portraitTabletLPager = findViewById(R.id.viewTabletL);
                portraitTabletLAdapter = new TabletPageAdapter(getSupportFragmentManager());
                portraitTabletLPager.setAdapter(portraitTabletLAdapter);
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                setContentView(R.layout.activity_tablet_portrait);
                portraitTabletPager = findViewById(R.id.viewTablet);
                portraitTabletAdapter = new TabletPageAdapter(getSupportFragmentManager());
                portraitTabletPager.setAdapter(portraitTabletAdapter);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.settings){
            startActivity(new Intent(this, Settings.class));
            return true;
        }else if(id == R.id.refresh){
            service.refreshWeather();
            setupView();
            return true;
        }else if(id == R.id.citySet){
            startActivity(new Intent(this,SetCity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!checkScreen() ){
            if (portraitPhonePager.getCurrentItem() == 0) {
                System.exit(1);
            } else {
                portraitPhonePager.setCurrentItem(portraitPhonePager.getCurrentItem() - 1);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("yahoo.xml", 0);
        service = new YahooWeatherService(this, sharedPreferences.getInt("option", 0),sharedPreferences);
        service.refreshWeather();
        setupView();
    }

    @Override
    public void serviceSuccess(Channel channel) {
        Toast.makeText(this, "Data refreshed", Toast.LENGTH_SHORT).show();
        Item item = channel.getItem();
        Atmosphere atmosphere = channel.getAtmosphere();
        Wind wind = channel.getWind();
        Location location = channel.getLocation();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.getInt("option",0)== 0){
            editor.putString("longitude", item.getLongitude());
            editor.putString("latitude", item.getLatitude());
            editor.putString("country", location.getCountry());
            editor.putString("current_image", item.getCondition().getCode());
            editor.putString("current_date", item.getCondition().getDate());
            editor.putString("current_temperature", item.getCondition().getTemperature());
            editor.putString("current_description", item.getCondition().getCondition());
            editor.putString("pressure", atmosphere.getPressure());
            editor.putString("humidity", atmosphere.getHumidity());
            editor.putString("visibility", atmosphere.getVisibility());
            editor.putString("direction", wind.getDirection());
            editor.putString("speed", wind.getSpeed());
            editor.putString("unit", channel.getUnits().getTemperature());
            editor.putString("speedUnit", channel.getUnits().getSpeed());
            editor.putString("pressureUnit", channel.getUnits().getBars());
            for (int i = 1; i < 6; i++) {
                editor.putString("image" + i, item.getForecast(i).getImageCode());
                editor.putString("day" + i, item.getForecast(i).getDay());
                editor.putString("high" + i, item.getForecast(i).getHigh());
                editor.putString("low" + i, item.getForecast(i).getLow());
                editor.putString("description" + i, item.getForecast(i).getCondition());
            }
        }else {
            editor.putString("city", location.getCity());
            editor.putString("country", location.getCountry());
            editor.putString("current_image", item.getCondition().getCode());
            editor.putString("current_date", item.getCondition().getDate());
            editor.putString("current_temperature", item.getCondition().getTemperature());
            editor.putString("current_description", item.getCondition().getCondition());
            editor.putString("pressure", atmosphere.getPressure());
            editor.putString("humidity", atmosphere.getHumidity());
            editor.putString("visibility", atmosphere.getVisibility());
            editor.putString("direction", wind.getDirection());
            editor.putString("speed", wind.getSpeed());
            editor.putString("unit", channel.getUnits().getTemperature());
            editor.putString("speedUnit", channel.getUnits().getSpeed());
            editor.putString("pressureUnit", channel.getUnits().getBars());
            for (int i = 1; i < 6; i++) {
                editor.putString("image" + i, item.getForecast(i).getImageCode());
                editor.putString("day" + i, item.getForecast(i).getDay());
                editor.putString("high" + i, item.getForecast(i).getHigh());
                editor.putString("low" + i, item.getForecast(i).getLow());
                editor.putString("description" + i, item.getForecast(i).getCondition());
            }
        }
        editor.apply();
        setupView();
    }

    @Override
    public void serviceFailure(Exception e) {
        Toast.makeText(this, "No internet connection! Cannot load data!" , Toast.LENGTH_LONG).show();
        System.out.println(e.getMessage());
    }
    @Override
    public void onRestart() {
        service = new YahooWeatherService(this, sharedPreferences.getInt("option", 0),sharedPreferences);
        service.refreshWeather();
        setupView();
        super.onRestart();
    }
}
