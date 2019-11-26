package com.mao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private JFXTextField userNameTextField;
    @FXML
    private JFXPasswordField passTextField;

    @FXML
    private Label errorLabel;

    public void loginBtnClicked(ActionEvent e){
        String user = userNameTextField.getText();
        String pass = passTextField.getText();

        if(Database.getInstance().login(user,pass)){
            errorLabel.setVisible(false);
            System.out.println("Login successful");
        }
        else{
            errorLabel.setVisible(true);
            System.out.println("Wrong");
        }
    }

    public void handleClicked(ActionEvent e){
        createSignUpWindow(e);
    }

    private void createSignUpWindow(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));

            AnchorPane root = loader.load();
            Scene scene =  new Scene(root);

            scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
            //stage.initStyle(StageStyle.UNDECORATED);

            Stage stage = new Stage();

            stage.setTitle("Sign in");
            stage.setScene(scene);
            stage.show();

            ((Node)(e.getSource())).getScene().getWindow().hide();
        }
        catch (IOException err){
            err.printStackTrace();
        }

    }
}
