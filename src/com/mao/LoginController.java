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
        String username = userNameTextField.getText();
        String pass = passTextField.getText();

        errorLabel.setVisible(false);
        User user = Database.getInstance().login(username,pass);
        if(user == null){
            errorLabel.setVisible(true);
            return;
        }

        Data.getInstance().setUser(new User(user.getEmail(),user.getUsername(),user.getConfirm()));
        if(user.getConfirm() == 0){
            createConfirmWindow(e);
        }
        else{
            createHomeWindow(e);
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

    private void createHomeWindow(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));

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

    private void createConfirmWindow(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirm.fxml"));

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
