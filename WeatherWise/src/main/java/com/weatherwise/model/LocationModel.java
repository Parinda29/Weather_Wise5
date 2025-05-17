package com.weatherwise.model;

public class LocationModel {
    private int locationId;
    private String locationName;
    private int weatherRecordId;

    public LocationModel() {}

    public LocationModel(int locationId, String locationName, int weatherRecordId) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.weatherRecordId = weatherRecordId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getWeatherRecordId() {
        return weatherRecordId;
    }

    public void setWeatherRecordId(int weatherRecordId) {
        this.weatherRecordId = weatherRecordId;
    }
}
