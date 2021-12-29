package com.example.appfleet.controller;

import com.example.appfleet.object.Car;
import com.example.appfleet.object.Fleet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

public class AppController implements Initializable{
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDeleteCar;
    @FXML
    private Button btnCalculatingPrc;
    @FXML
    private Button btnInsertCar;
    @FXML
    private Button btnUpdateCar;
    @FXML
    private Button btnParam;
    @FXML
    private Button btnShowAll;

    @FXML
    private TableColumn<Car, Integer> colCarId;
    @FXML
    private TableColumn<Car, String> colColor;
    @FXML
    private TableColumn<Fleet, Integer> colFeelId;
    @FXML
    private TableColumn<Car, Integer> colCarFleetId;
    @FXML
    private TableColumn<Fleet, String> colFeelName;
    @FXML
    private TableColumn<Car, Integer> colMaxSpeed;
    @FXML
    private TableColumn<Car, Integer> colPrice;
    @FXML
    private TableColumn<Car, String> colModel;
    @FXML
    private TableColumn<Car, Integer> colNumberOfSpaces;
    @FXML
    private TableColumn<Car, Integer> colYearOfManufacture;

    @FXML
    private TextField tfCarId;
    @FXML
    private TextField tfCalcPrice;
    @FXML
    private TextField tfColor;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfcarfleetid;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfNumberOfSpaces;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfYearOfManufacture;
    @FXML
    private TextField tfSpeed;
    @FXML
    private TextField tfParam;

    @FXML
    private TableView<Car> tvCars;
    @FXML
    private TableView<Fleet> tvFeels;

    private String selectAllCars = "SELECT * FROM appdb.car";
    private String selectAllFleets = "SELECT * FROM appdb.fleet";

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnInsert)
            insertRecord();
        else if(event.getSource() == btnUpdate)
            updateRecord();
        else if(event.getSource() == btnDelete)
            deleteRecord();
        else if(event.getSource() == btnDeleteCar)
            deleteRecordCar();
        else if(event.getSource() == btnUpdateCar)
            updateRecordCar();
        else if(event.getSource() == btnInsertCar)
            insertRecordCar();
        else if(event.getSource() == btnCalculatingPrc)
            calculatingPrc();
        else if(event.getSource() == btnParam)
            setParam();
        else if(event.getSource() == btnShowAll)
            showCars(selectAllCars);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showFleets(selectAllFleets);
        showCars(selectAllCars);
    }

    public void showFleets(String q){
        ObservableList<Fleet> list = GetObjectList.getFleetList(q);

        colFeelId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colFeelName.setCellValueFactory(data -> data.getValue().nameProperty());

        tvFeels.setItems(list);
    }

    public void showCars(String q){
        ObservableList<Car> list = GetObjectList.getCarList(q);

        colCarId.setCellValueFactory(data -> data.getValue().car_idProperty().asObject());
        colCarFleetId.setCellValueFactory(data -> data.getValue().fleet_idProperty().asObject());
        colModel.setCellValueFactory(data -> data.getValue().modelProperty());
        colColor.setCellValueFactory(data -> data.getValue().colorProperty());
        colYearOfManufacture.setCellValueFactory(data -> data.getValue().year_of_manufactureProperty().asObject());
        colNumberOfSpaces.setCellValueFactory(data -> data.getValue().number_of_spacesProperty().asObject());
        colMaxSpeed.setCellValueFactory(data -> data.getValue().max_speedProperty().asObject());
        colPrice.setCellValueFactory(data -> data.getValue().priceProperty().asObject());

        tvCars.setItems(list);
    }

    private void insertRecord(){
        String query = "INSERT INTO appdb.fleet (id, name) VALUES ('" + tfId.getText() + "','"
                + tfName.getText() + "');";
        executeQuery(query);
        showFleets(selectAllFleets);
    }

    private void updateRecord(){
        String query = "UPDATE  appdb.fleet SET name  = '" + tfName.getText() + "'" + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showFleets(selectAllFleets);
    }

    private void deleteRecord(){
        String query = "DELETE FROM appdb.fleet WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showFleets(selectAllFleets);
    }

    private void insertRecordCar(){
        String query = "INSERT INTO appdb.car (car_id, fleet_id, model, color, year_of_manufacture, number_of_spaces, max_speed, price) VALUES ('" + tfCarId.getText() + "','"
                + tfcarfleetid.getText() + "','" + tfModel.getText() + "','" + tfColor.getText() + "','" + tfYearOfManufacture.getText() + "','" + tfNumberOfSpaces.getText() + "','" + tfSpeed.getText() + "','" + tfPrice.getText() + "');";
        executeQuery(query);
        showCars(selectAllCars);
    }

    private void updateRecordCar(){
        String query = "UPDATE  appdb.car SET fleet_id  = " + tfcarfleetid.getText() + ", model = '" + tfModel.getText() + "'" +
               ", color = '" + tfColor.getText() + "'" + ", year_of_manufacture = " + tfYearOfManufacture.getText() +
                ", number_of_spaces = " + tfNumberOfSpaces.getText() + ", max_speed = " + tfSpeed.getText() +  " WHERE car_id = " + tfCarId.getText() + "";
        executeQuery(query);
        showCars(selectAllCars);
    }

    private void deleteRecordCar(){
        String query = "DELETE FROM appdb.car WHERE car_id = " + tfCarId.getText() + "";
        executeQuery(query);
        showCars(selectAllCars);
    }

    private void calculatingPrc(){
        String query = "SELECT SUM(price) FROM appdb.car WHERE fleet_id = " + tfId.getText() + "";
        Connection connection = DataBaseConnection.getConnection();
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet s = statement.executeQuery(query);
            s.next();
            tfCalcPrice.setText(String.valueOf(s.getInt(1)));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setParam(){
        String setParam = "SELECT * FROM appdb.car WHERE fleet_id = " +  tfId.getText() + " and max_speed > " + tfCalcPrice.getText() + " and max_speed < " + tfParam.getText() + "";
        showCars(setParam);
    }

    private void executeQuery(String query) {
        Connection connection = DataBaseConnection.getConnection();
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
        Fleet fleet = tvFeels.getSelectionModel().getSelectedItem();
        tfId.setText(fleet.getId()+"");
        tfName.setText(fleet.getName());
    }

    public void handleMouseActionCar(javafx.scene.input.MouseEvent mouseEvent) {
        Car car = tvCars.getSelectionModel().getSelectedItem();
        tfCarId.setText(car.getCar_Id()+"");
        tfcarfleetid.setText(car.getFleet_Id()+"");
        tfModel.setText(car.getModel());
        tfColor.setText(car.getColor());
        tfYearOfManufacture.setText(car.getYear_of_manufacture()+"");
        tfNumberOfSpaces.setText(car.getNumber_of_spaces()+"");
        tfSpeed.setText(car.getMax_speed()+"");
        tfPrice.setText(car.getPrice()+"");
    }

}
