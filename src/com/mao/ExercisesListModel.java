package com.mao;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ExercisesListModel {
    private SimpleListProperty exerciseList;
    private AnchorPane mainPane;
    int x = 20;
    int y = 20;

    public ExercisesListModel(AnchorPane mainPane){
        this.mainPane  = mainPane;
    }

    public void add(ExercisePane exercisePane){
        Pane pane = new ExercisePane(x,y);

        x += 210;
        if(x > 650){
            x = 20;
            y+= 210;
            if(y > mainPane.getPrefHeight()){
                mainPane.setPrefHeight(y);
            }
        }

        mainPane.getChildren().add(pane);
    }

}
