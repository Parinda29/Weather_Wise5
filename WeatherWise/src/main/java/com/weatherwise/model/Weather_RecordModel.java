package com.weatherwise.model;

public class Weather_RecordModel {
    private int weatherRecordId;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private int pressure;

    public Weather_RecordModel() {}

    public Weather_RecordModel(int weatherRecordId, double temperature, int humidity, double windSpeed, int pressure) {
        this.weatherRecordId = weatherRecordId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }

    public Weather_RecordModel(double temperature, int humidity, double windSpeed, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }

    public int getWeatherRecordId() {
        return weatherRecordId;
    }

    public void setWeatherRecordId(int weatherRecordId) {
        this.weatherRecordId = weatherRecordId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
