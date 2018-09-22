package com.smollvile.gpsweather.weather;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;


import com.smollvile.gpsweather.R;

import org.json.JSONObject;


public class WeatherOnLocation {

    Context context;
    TextView temp, humidity, description, windSpeed;

    Handler handler;

    public WeatherOnLocation(Context context, TextView temp, TextView humidity, TextView description, TextView windSpeed) {
        this.context = context;
        this.temp = temp;
        this.humidity = humidity;
        this.description = description;
        this.windSpeed = windSpeed;
        handler = new Handler();
    }

    public void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = WeatherData.getJSONData(context, city);
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {
                            Toast.makeText(context, R.string.not_weather,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    //Обработка загруженных данных
    private void renderWeather(JSONObject json) {
        try {

            //достаем json объекты
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");
            JSONObject weahther = json.getJSONArray("weather").getJSONObject(0);

            //формат температуры
            String temp = String.format("%.1f", main.getDouble("temp"));
            //формат влажности
            String humidity = String.format("%.1f", main.getDouble("humidity"));
            //формат видимость
            String description =  String.format("%s", weahther.getString("description"));
            // формать скорости ветра
            String windSpeed =  String.format("%.1f", wind.getDouble("speed"));


            this.temp.setText(temp + "\u2103");
            this.humidity.setText(humidity + "%");
            this.description.setText(description);
            this.windSpeed.setText(windSpeed + "м/с");


        } catch (Exception e) {
        }
    }
}
