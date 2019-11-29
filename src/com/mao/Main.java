package com.mao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        mainWindow();
    }


    private void mainWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

            AnchorPane root = loader.load();
            Scene scene =  new Scene(root);

            scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Sign in");
            stage.setScene(scene);

            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
