package com.example.mateusz.astroweather2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Settings extends AppCompatActivity {

    private EditText longitude, latitude, refresh;
    private Button save, defaultButton;
    private String longitudeText, latitudeText, refreshText;
    private int positionTemp;
    private final static String VALUE_REGEX = "^-?\\d*\\.\\d+$|^-?\\d+$";
    private final static String TIME_REGEX = "\\d+";
    private SharedPreferences sharedPreferences;
    Spinner spinnerTemperature;
    ArrayAdapter<String> tempAdapter;

    private void loadValues(){
        longitude.setText(longitudeText);
        latitude.setText(latitudeText);
        refresh.setText(refreshText);
    }
    private void saveCurrentValues(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("longitude", longitude.getText().toString());
        editor.putString("latitude", latitude.getText().toString());
        editor.putString("refresh", refresh.getText().toString());
        editor.putInt("celcius",positionTemp);
        editor.putInt("option", 0);
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
        spinnerTemperature = findViewById(R.id.spinnerTemperature);
        tempAdapter = new ArrayAdapter<String>(Settings.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.units));
        tempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTemperature.setAdapter(tempAdapter);
        sharedPreferences = getSharedPreferences("yahoo.xml", 0);

        spinnerTemperature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionTemp = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setSelection(positionTemp);
            }
        });
        loadConfig();

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
                loadDefaultConfig("default_");
                saveCurrentValues();
            }
        });
    }

    private void loadConfig() {
        longitudeText = sharedPreferences.getString("longitude", String.valueOf(getResources().getString(R.string.longitude)));
        latitudeText = sharedPreferences.getString("latitude", String.valueOf(getResources().getString(R.string.latitude)));
        refreshText = sharedPreferences.getString("refresh", String.valueOf(getResources().getString(R.string.refresh)));
        positionTemp = sharedPreferences.getInt("celcius", 0);
        loadValues();
    }

    private void loadDefaultConfig(String configType) {
        longitudeText = sharedPreferences.getString(configType + "longitude", String.valueOf(getResources().getString(R.string.default_longitude)));
        latitudeText = sharedPreferences.getString(configType + "latitude", String.valueOf(getResources().getString(R.string.default_latitude)));
        refreshText = sharedPreferences.getString(configType + "refresh", String.valueOf(getResources().getString(R.string.default_refresh)));
        positionTemp = sharedPreferences.getInt("celcius", 0);
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
