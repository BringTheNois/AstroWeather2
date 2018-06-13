package com.example.mateusz.astroweather2.yahoo.data;

import org.json.JSONObject;

public class Atmosphere implements JSONPopulator{
    private String pressure;
    private String humidity;
    private String visibility;

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    @Override
    public void populate(JSONObject data) {
        this.pressure = data.optString("pressure");
        this.humidity = data.optString("humidity");
        this.visibility = data.optString("visibility");
    }
}
