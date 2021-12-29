package com.example.appfleet.object;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;


public class Fleet {

    private IntegerProperty id;

    public int getId() { return this.id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return this.id; }

    private StringProperty name;

    public String getName() { return this.name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() {return this.name; }

    public Fleet(int id,String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

}
