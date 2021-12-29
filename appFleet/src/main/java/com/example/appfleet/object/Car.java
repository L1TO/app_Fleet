package com.example.appfleet.object;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Car {

    private IntegerProperty car_id;
    public int getCar_Id() { return this.car_id.get(); }
    public void setCar_Id(int car_id) { this.car_id.set(car_id); }
    public IntegerProperty car_idProperty() { return this.car_id; }

    private IntegerProperty fleet_id;
    public int getFleet_Id() { return this.fleet_id.get(); }
    public void setFleet_id(int fleet_id) { this.fleet_id.set(fleet_id); }
    public IntegerProperty fleet_idProperty() { return this.fleet_id; }

    private StringProperty model;
    public String getModel() { return this.model.get(); }
    public void setModel(String model) { this.model.set(model); }
    public StringProperty modelProperty() { return this.model; }

    private StringProperty color;
    public String getColor() { return this.color.get(); }
    public void setColor(String color) { this.color.set(color); }
    public StringProperty colorProperty() { return this.color; }

    private IntegerProperty year_of_manufacture;
    public int getYear_of_manufacture() { return this.year_of_manufacture.get(); }
    public void setYear_of_manufacture(int year_of_manufacture) { this.year_of_manufacture.set(year_of_manufacture); }
    public IntegerProperty year_of_manufactureProperty() { return this.year_of_manufacture; }

    private IntegerProperty number_of_spaces;
    public int getNumber_of_spaces() { return this.number_of_spaces.get(); }
    public void setNumber_of_spaces(int number_of_spaces) { this.number_of_spaces.set(number_of_spaces); }
    public IntegerProperty number_of_spacesProperty() { return this.number_of_spaces; }

    private IntegerProperty max_speed;
    public int getMax_speed() { return this.max_speed.get(); }
    public void setMax_speed(int max_speed) { this.max_speed.set(max_speed); }
    public IntegerProperty max_speedProperty() { return this.max_speed; }

    private IntegerProperty price;
    public int getPrice() { return this.price.get(); }
    public void setPrice(int price) { this.price.set(price); }
    public IntegerProperty priceProperty() { return this.price; }

    public Car(int car_id, int fleet_id, String model, String color, int year_of_manufacture, int number_of_spaces, int max_speed, int price){
        this.car_id = new SimpleIntegerProperty(car_id);
        this.fleet_id = new SimpleIntegerProperty(fleet_id);
        this.model = new SimpleStringProperty(model);
        this.color = new SimpleStringProperty(color);
        this.year_of_manufacture = new SimpleIntegerProperty(year_of_manufacture);
        this.number_of_spaces = new SimpleIntegerProperty(number_of_spaces);
        this.max_speed = new SimpleIntegerProperty(max_speed);
        this.price = new SimpleIntegerProperty(price);


    }


}
