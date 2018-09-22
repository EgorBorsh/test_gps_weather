package com.smollvile.gpsweather.dto;


public class ItemDTO {

    private String City;
    private String Coordinates;
    private String Country;
    private String Street;
    private String House;
    private String Temp;
    private String Humidity;
    private String Description;
    private String WindSpeed;
    private String Date;
    private String Time;

    public ItemDTO(String city, String coordinates, String country, String street, String house, String temp, String humidity, String description, String windSpeed, String date, String time) {
        City = city;
        Coordinates = coordinates;
        Country = country;
        Street = street;
        House = house;
        Temp = temp;
        Humidity = humidity;
        Description = description;
        WindSpeed = windSpeed;
        Date = date;
        Time = time;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(String coordinates) {
        Coordinates = coordinates;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
