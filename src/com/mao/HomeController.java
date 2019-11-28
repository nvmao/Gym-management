package com.mao;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane menuPane,opacityPane;

    @FXML
    private ImageView menuBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        opacityPane.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.4),opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setByValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.4),menuPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        menuBtn.setOnMouseClicked(event -> {

            opacityPane.setVisible(true);
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.4),opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setByValue(0.3);
            fadeTransition1.play();

            translateTransition.setByX(+600);
            translateTransition.play();
        });

        opacityPane.setOnMouseClicked(event -> {

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.4),opacityPane);
            fadeTransition1.setFromValue(0.3);
            fadeTransition1.setByValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });

            translateTransition.setByX(-600);
            translateTransition.play();
        });


    }
}
