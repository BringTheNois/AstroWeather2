package com.example.mateusz.astroweather2.yahoo.data;

import org.json.JSONObject;

public class Condition implements JSONPopulator{
    private int code;
    private int temperature;
    private String discription;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDiscription() {
        return discription;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temperature = data.optInt("temp");
        discription = data.optString("text");
    }
}
