package com.mao;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class ExercisePane extends Pane {
    private Exercise exercise;
    private ImageView imageDemo;
    private Label nameLabel;


    ExercisePane(int x,int y){
        imageDemo = new ImageView("/com/Image/Exercises/squat.jpg");
        nameLabel = new Label("Squat");

        imageDemo.setLayoutX(0);imageDemo.setLayoutY(0);
        imageDemo.setFitWidth(200);
        imageDemo.setFitHeight(150);
        this.getChildren().add(imageDemo);

        nameLabel.setStyle("-fx-text-fill:#fff;-fx-font-size:14px;");
        nameLabel.layoutXProperty().bind(this.widthProperty().subtract(nameLabel.widthProperty()).divide(2));
        nameLabel.setWrapText(true);
        nameLabel.setLayoutY(160);
        this.getChildren().add(nameLabel);

        this.setStyle("-fx-background-color:#455;");
        this.setPrefSize(200,200);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

}
