package com.example.mateusz.astroweather2.yahoo.data;

import org.json.JSONObject;

public class Units implements JSONPopulator{
    private String temperature;
    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}