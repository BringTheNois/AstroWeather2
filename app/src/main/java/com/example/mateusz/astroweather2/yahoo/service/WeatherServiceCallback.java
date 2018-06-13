package com.example.mateusz.astroweather2.yahoo.service;

import com.example.mateusz.astroweather2.yahoo.data.Channel;

public interface WeatherServiceCallback {
    void serviceSucces(Channel channel);

    void serviceFailure(Exception e);
}
