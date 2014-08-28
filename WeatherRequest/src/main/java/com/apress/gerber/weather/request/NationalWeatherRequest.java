package com.apress.gerber.weather.request;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Clifton
 * Copyright 8/28/2014.
 */
public class NationalWeatherRequest {

    public static final String NATIONAL_WEATHER_SERVICE =
            "http://forecast.weather.gov/MapClick.php?lat=37.368830&lon=-122.036350&FcstType=dwml";

    public NationalWeatherRequest() {
        URL url;
        try {
            url = new URL(NATIONAL_WEATHER_SERVICE);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL for National Weather Service: " +
            NATIONAL_WEATHER_SERVICE);
        }
        InputStream inputStream;
        try {
            inputStream = url.openStream();
        } catch (IOException e) {
            log("Exception opening Nat'l weather URL " + e);
            e.printStackTrace();
            return;
        }
        log("Dumping weather data...");
        BufferedReader weatherReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            for(String eachLine = weatherReader.readLine(); eachLine!=null; eachLine = weatherReader.readLine()) {
                log(eachLine);
            }
        } catch (IOException e) {
            log("Exception reading data from Nat'l weather site " + e);
            e.printStackTrace();
        }
    }

    private int log(String eachLine) {
        return Log.d(getClass().getName(), eachLine);
    }
}
