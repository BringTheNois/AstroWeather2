package com.example.mateusz.astroweather2.yahoo.service;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.mateusz.astroweather2.yahoo.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private Exception exception;
    private int searchOption;
    private SharedPreferences sharedPreferences;

    public YahooWeatherService(WeatherServiceCallback callback, int searchOption, SharedPreferences sharedPreferences) {
        this.callback = callback;
        this.searchOption = searchOption;
        this.sharedPreferences = sharedPreferences;
    }


    @SuppressLint("StaticFieldLeak")
    public void refreshWeather(){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String YQL;
                if (sharedPreferences.getInt("celcius",0)  == 0) {
                    if (searchOption == 0) {
                        String cityToFind = sharedPreferences.getString("city", "Lodz, PL");
                        YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", cityToFind);
                    } else {
                        String coordinates = sharedPreferences.getString("latitude", "51.761742") + "," + sharedPreferences.getString("longitude", "19.46801");
                        YQL = String.format("select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text=\"(%s)\") and u='c'", coordinates);
                    }
                }else{
                    if (searchOption == 0) {
                        String cityToFind = sharedPreferences.getString("city", "Lodz, PL");
                        YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", cityToFind);
                    } else {
                        String coordinates = sharedPreferences.getString("latitude", "51.761742") + "," + sharedPreferences.getString("longitude", "19.46801");
                        YQL = String.format("select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text=\"(%s)\") ", coordinates);
                    }
                }
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s == null && exception != null){
                    callback.serviceFailure(exception);
                    return;
                }


                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");
                    int count = queryResults.getInt("count");

                    if(count == 0){
                        callback.serviceFailure(new WeatherException("Cannot find weather information"));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }
            }
        }.execute();
    }
    public class WeatherException extends Exception {
        public WeatherException(String message) {
            super(message);
        }
    }
}
