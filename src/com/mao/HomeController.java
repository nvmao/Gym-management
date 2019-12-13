package com.mao;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane menuPane,opacityPane, exercisePane,homePane,exerciseMainPane;

    @FXML
    private ImageView menuBtn;

    @FXML
    private ImageView avatarImageView,addExerciseIcon;

    @FXML
    private JFXButton exercisesBtn,homeBtn;

    private Map<String,AnchorPane> paneTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuPane.setVisible(true);

        paneTable = new Hashtable<String,AnchorPane>();
        paneTable.put("excercisePane", exercisePane);
        paneTable.put("homePane",homePane);

        initAvatar();
        initMenuAnimation();

        exercisesBtn.setOnAction(e->{
            showPane("excercisePane");
        });
        homeBtn.setOnAction(e->{
            showPane("homePane");
        });


        addExerciseIcon.setOnMouseClicked(event -> {
            Data.getInstance().getExercisesListModel().add(null);
        });

        Data.getInstance().setExercisesListModel(new ExercisesListModel(exerciseMainPane));


    }

    private void showPane(String pane){
        paneTable.forEach((k,v)->{
            v.setVisible(false);
            if(k.equals(pane)){
                v.setVisible(true);
            }
        });
    }

    private void initAvatar(){
        Image im = new Image("/com/Image/mao.jpg",false);
        avatarImageView.setImage(im);

        Rectangle clip = new Rectangle(avatarImageView.getFitWidth(),avatarImageView.getFitHeight());
        clip.setArcWidth(200);
        clip.setArcHeight(200);
        avatarImageView.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = avatarImageView.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        avatarImageView.setClip(null);

        // apply a shadow effect.
        avatarImageView.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        avatarImageView.setImage(image);

    }

    private void initMenuAnimation(){
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
