package com.weatherwise.service;

import com.weatherwise.config.DbConfig;
import com.weatherwise.model.Weather_RecordModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherRecordService {

    // INSERT
    public void insertWeatherRecord(Weather_RecordModel weatherRecord) throws Exception {
        String sql = "INSERT INTO weather_record (temperature, humidity, wind_speed, pressure) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, weatherRecord.getTemperature());
            stmt.setInt(2, weatherRecord.getHumidity());
            stmt.setDouble(3, weatherRecord.getWindSpeed());
            stmt.setInt(4, weatherRecord.getPressure());

            stmt.executeUpdate();
        }
    }

    // SELECT ALL
    public List<Weather_RecordModel> getAllWeatherRecords() throws Exception {
        List<Weather_RecordModel> records = new ArrayList<>();
        String sql = "SELECT * FROM weather_record";

        try (Connection conn = DbConfig.getdbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Weather_RecordModel record = new Weather_RecordModel();
                record.setWeatherRecordId(rs.getInt("weather_record_id"));
                record.setTemperature(rs.getDouble("temperature"));
                record.setHumidity(rs.getInt("humidity"));
                record.setWindSpeed(rs.getDouble("wind_speed"));
                record.setPressure(rs.getInt("pressure"));
                records.add(record);
            }
        }
        return records;
    }

    // UPDATE
    public void updateWeatherRecord(Weather_RecordModel weatherRecord) throws Exception {
        String sql = "UPDATE weather_record SET temperature = ?, humidity = ?, wind_speed = ?, pressure = ? WHERE weather_record_id = ?";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, weatherRecord.getTemperature());
            stmt.setInt(2, weatherRecord.getHumidity());
            stmt.setDouble(3, weatherRecord.getWindSpeed());
            stmt.setInt(4, weatherRecord.getPressure());
            stmt.setInt(5, weatherRecord.getWeatherRecordId());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deleteWeatherRecord(int recordId) throws Exception {
        String sql = "DELETE FROM weather_record WHERE weather_record_id = ?";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recordId);
            stmt.executeUpdate();
        }
    }

    // GET BY ID
    public Weather_RecordModel getWeatherRecordById(int recordId) throws Exception {
        String sql = "SELECT * FROM weather_record WHERE weather_record_id = ?";
        Weather_RecordModel record = null;

        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recordId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                record = new Weather_RecordModel();
                record.setWeatherRecordId(rs.getInt("weather_record_id"));
                record.setTemperature(rs.getDouble("temperature"));
                record.setHumidity(rs.getInt("humidity"));
                record.setWindSpeed(rs.getDouble("wind_speed"));
                record.setPressure(rs.getInt("pressure"));
            }
        }
        return record;
    }
}
