package com.weatherwise.model;

public class Users_LocationModel {
    private int userId;
    private int locationId;

    public Users_LocationModel() {}

    public Users_LocationModel(int userId, int locationId) {
        this.userId = userId;
        this.locationId = locationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "UsersLocationModel [userId=" + userId + ", locationId=" + locationId + "]";
    }
}
