package com.example.mateusz.astroweather2.yahoo.data;

import org.json.JSONObject;

public class Forecast implements JSONPopulator{

    private String imageCode;
    private String day;
    private String low;
    private String high;
    private String condition;

    public String getImageCode() {
        return imageCode;
    }

    public String getDay() {
        return day;
    }

    public String getLow() {
        return low;
    }

    public String getHigh() {
        return high;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        this.imageCode = data.optString("code");
        this.day = data.optString("day");
        this.low = data.optString("low");
        this.high = data.optString("high");
        this.condition = data.optString("text");

    }
}
