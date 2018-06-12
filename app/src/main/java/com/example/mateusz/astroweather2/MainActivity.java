package com.example.mateusz.astroweather2;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mateusz.astroweather2.fragments.LandscapeSidePageAdapter;
import com.example.mateusz.astroweather2.fragments.ScreenSlidePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager portraitPhonePager;
    private PagerAdapter portraitPhoneAdapter;
    private ViewPager landscapePhonePager;
    private PagerAdapter landscapePhoneAdapter;

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
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                setContentView(R.layout.activity_tablet_portrait);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setupView();
     }
    @Override
    public void onBackPressed() {
        Configuration config = getResources().getConfiguration();
        if (!checkScreen() && config.orientation == Configuration.ORIENTATION_PORTRAIT ){
            if (portraitPhonePager.getCurrentItem() == 0) {
                System.exit(1);
            } else {
                portraitPhonePager.setCurrentItem(portraitPhonePager.getCurrentItem() - 1);
            }
        } else {
            super.onBackPressed();
        }
    }
}
