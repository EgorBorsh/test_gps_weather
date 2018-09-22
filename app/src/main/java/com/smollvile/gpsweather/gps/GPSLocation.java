package com.smollvile.gpsweather.gps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSLocation implements LocationListener {

    private Context context;
    TextView country, cor,city, street, house;

    boolean isGPSEnable, isNetworkEnable;

    private LocationManager locationManager;

    public GPSLocation(Context context, TextView country, TextView cor ,TextView city, TextView street, TextView house) {
        this.context = context;
        this.country = country;
        this.cor = cor;
        this.city = city;
        this.street = street;
        this.house = house;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    //проверка разрешений в манифесте и запрос на разрешения у пользователя
    public boolean checkPermission()
    {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return false;
        }else {
            return true;
        }
    }

    public void providerChoice()
    {
        //при наличии разрешения смотрим какой провайдер включен
        if (checkPermission()) {

            isGPSEnable =locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isNetworkEnable) {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
                        this);
            } else if (isGPSEnable){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        1000 * 10, 10, this);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //вызываем метод геокодинга координат
        showLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        if (checkPermission()) {
            showLocation(locationManager.getLastKnownLocation(provider));
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    //проводим геокодинг координат и выводим на экран данные
    private void showLocation(android.location.Location location) {
        if (location == null)
            return;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        country.setText(formatGeo(location, addresses,1));
        cor.setText(formatLocation(location));
        city.setText(formatGeo(location, addresses,2));
        street.setText(formatGeo(location, addresses,3));
        house.setText(formatGeo(location, addresses,4));

    }

    private String formatLocation(android.location.Location location) {
        if (location == null)
            return "";
        //формат отображения координат
        return String.format("lat = %1$.4f, lon = %2$.4f", location.getLatitude(), location.getLongitude());
    }

    private String formatGeo(android.location.Location location, List<Address> addresses, int key) {
        Address address = null;
        if (location == null)
            return "";

        if (addresses != null && addresses.size() >= 0) {
            address = addresses.get(0);
        }

        String str = null;

        // форматы отображения
        // 1 - страны
        // 2 - города
        // 3 - улицы
        // 4 - дома
        switch (key){
            case 1:
                str = String.format("%s", address.getCountryName());
                break;
            case 2:
                str = String.format("%s", address.getLocality());
                break;
            case 3:
                str = String.format("%s", address.getThoroughfare());
                break;
            case 4:
                str = String.format("%s", address.getFeatureName());
                break;
        }
        return str;
    }

}
