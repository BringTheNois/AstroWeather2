package com.example.mateusz.astroweather2.yahoo.data;

import org.json.JSONObject;

public class Units implements JSONPopulator{
    private String temperature;
    private String speed;
    private String bars;
    private String distance;

    public String getSpeed() {
        return speed;
    }

    public String getBars() {
        return bars;
    }

    public String getDistance() {
        return distance;
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
        speed = data.optString("speed");
        bars = data.optString("pressure");
        distance = data.optString("distance");
    }
}
