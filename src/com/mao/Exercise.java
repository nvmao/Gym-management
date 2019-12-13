package com.mao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Exercise {
    SimpleIntegerProperty id;
    SimpleStringProperty name,description,imageDemo;

    public Exercise(int id, String name, String description, String imageDemo) {
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
        this.imageDemo.set(imageDemo);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getImageDemo() {
        return imageDemo.get();
    }

    public SimpleStringProperty imageDemoProperty() {
        return imageDemo;
    }

    public void setImageDemo(String imageDemo) {
        this.imageDemo.set(imageDemo);
    }
}
