package com.example.mateusz.astroweather2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Settings extends AppCompatActivity {

    private EditText longitude, latitude, refresh;
    private Button save, defaultButton;
    private String longitudeText, latitudeText, refreshText;
    private final static String VALUE_REGEX = "^-?\\d*\\.\\d+$|^-?\\d+$";
    private final static String TIME_REGEX = "\\d+";
    private SharedPreferences sharedPreferences;

    private void loadValues(){
        longitude.setText(longitudeText);
        latitude.setText(latitudeText);
        refresh.setText(refreshText);
    }
    private void saveCurrentValues(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("current_longitude", longitude.getText().toString());
        editor.putString("current_latitude", latitude.getText().toString());
        editor.putString("current_refresh", refresh.getText().toString());
        editor.apply();
    }
    private boolean checkValues(){
        String longitudeTemp = longitude.getText().toString();
        String latitudeTemp = latitude.getText().toString();
        String refreshTemp = refresh.getText().toString();
        if (longitudeTemp.equals("") && latitudeTemp.equals("") && refreshTemp.equals("")){
            Toast.makeText(Settings.this, "Nie wszystkie pola zostały uzupełnione!", Toast.LENGTH_LONG).show();
        }else {
            if (latitudeTemp.matches(VALUE_REGEX) && longitudeTemp.matches(VALUE_REGEX) && refreshTemp.matches(TIME_REGEX)) {
                if ((Double.valueOf(latitudeTemp) >= -90 && Double.valueOf(latitudeTemp) <= 90) &&
                        (Double.valueOf(longitudeTemp) >= -180 && Double.valueOf(longitudeTemp) <= 180) &&
                        (Integer.valueOf(refreshTemp) > 0 && Integer.valueOf(refreshTemp) <= 60)){
                    Toast.makeText(Settings.this, "Udalo sie zapisac wartosci!", Toast.LENGTH_LONG).show();
                    return true;
                }
                else {
                    Toast.makeText(Settings.this, "Któraś zmienna poza zakresem!", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(Settings.this, "Możesz używać tylko cyfr!", Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        longitude = findViewById(R.id.Longitude);
        latitude = findViewById(R.id.Latitude);
        refresh = findViewById(R.id.Refresh);
        save = findViewById(R.id.buttonSave);
        defaultButton = findViewById(R.id.buttonDefault);

        sharedPreferences = getSharedPreferences("config.xml", 0);
        loadConfig("current");


        if (savedInstanceState != null) {
            longitude.setText(savedInstanceState.getString("savedLongitude"));
            latitude.setText(savedInstanceState.getString("savedLatitude"));
            refresh.setText(savedInstanceState.getString("savedRefresh"));
            saveCurrentValues();
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValues()){
                    saveCurrentValues();
                }
            }
        });
        defaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadConfig("default");
            }
        });
    }
    private void loadConfig(String configType) {
        longitudeText = sharedPreferences.getString(configType + "_longitude", String.valueOf(getResources().getString(R.string.default_longitude)));
        latitudeText = sharedPreferences.getString(configType + "_latitude", String.valueOf(getResources().getString(R.string.default_latitude)));
        refreshText = sharedPreferences.getString(configType + "_refresh", String.valueOf(getResources().getString(R.string.default_refresh)));
        loadValues();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("savedLongitude", String.valueOf(longitude.getText()));
        outState.putString("savedLatitude", String.valueOf(latitude.getText()));
        outState.putString("savedRefresh", String.valueOf(refresh.getText()));
        super.onSaveInstanceState(outState);
    }
}
