package com.example.mateusz.astroweather2.yahoo.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item implements JSONPopulator{
    private Condition condition;
    private String longitude;
    private String latitude;
    private JSONArray jsonArray;
    private Forecast[] forecast;

    public Condition getCondition() {
        return condition;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public Forecast getForecast(int day) {
        return forecast[day];
    }

    @Override
    public void populate(JSONObject data) {
        this.condition = new Condition();
        this.condition.populate(data.optJSONObject("condition"));

        this.longitude = data.optString("long");
        this.latitude = data.optString("lat");

        this.jsonArray = data.optJSONArray("forecast");
        this.forecast = new Forecast[6];
        for (int i = 0; i < 6; i++) {
            try {
                forecast[i] = new Forecast();
                forecast[i].populate(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
