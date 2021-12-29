package com.example.appfleet.controller;

import com.example.appfleet.object.Car;
import com.example.appfleet.object.Fleet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetObjectList {
    public static ObservableList<Fleet> getFleetList(String q){
        ObservableList<Fleet> fleetObservableList = FXCollections.observableArrayList();
        Connection connection = DataBaseConnection.getConnection();
        String query = q;
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Fleet fleets;
            while (resultSet.next()){
                fleets = new Fleet(resultSet.getInt("id"), resultSet.getString("name"));
                fleetObservableList.add(fleets);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fleetObservableList;
    }

    public static ObservableList<Car> getCarList(String q){
        ObservableList<Car> carObservableList = FXCollections.observableArrayList();
        Connection connection = DataBaseConnection.getConnection();
        String query = q;
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Car cars;
            while (resultSet.next()){
                cars = new Car(resultSet.getInt("car_id"), resultSet.getInt("fleet_id"),
                        resultSet.getString("model"), resultSet.getString("color"),
                        resultSet.getInt("year_of_manufacture"), resultSet.getInt("number_of_spaces"),
                        resultSet.getInt("max_speed"), resultSet.getInt("price"));
                carObservableList.add(cars);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return carObservableList;
    }
}
