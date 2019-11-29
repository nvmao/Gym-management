package com.mao;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private JFXTextField userNameTextField;
    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXPasswordField passTextField;
    @FXML
    private JFXPasswordField confirmPassTextField;

    @FXML
    private Label userWarnIcon;
    @FXML
    private Label emailWarnIcon;
    @FXML
    private Label passWarnIcon;
    @FXML
    private Label confirmWarnIcon;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userWarnIcon.setTooltip(new MyTooltip("Username must have at least 3 character and only contain these character 'a-z' 'A-Z' '0-9' '_' ."));
        emailWarnIcon.setTooltip(new MyTooltip("Email is invalid"));
        passWarnIcon.setTooltip(new MyTooltip("Password must have at least 6 character, at least a letter, at least a number, at least a special character."));
        confirmWarnIcon.setTooltip(new MyTooltip("Confirm does not match password."));

    }


    public void handelClickedSignUpBtn(ActionEvent e){
        checkValidAndAddUser(e);
    }

    private void checkValidAndAddUser(ActionEvent e){
        String username = userNameTextField.getText();
        String email = emailTextField.getText();
        String pass = passTextField.getText();
        String confirmPass = confirmPassTextField.getText();

        hideWarnIcon();
        if(!HandleError.isValidUsername(username)){
            userWarnIcon.getTooltip().setText("Username must have at least 3 character and only contain these character 'a-z' 'A-Z' '0-9' '_' .");
            userWarnIcon.setVisible(true);
            return;
        }
        if(Database.getInstance().existedUser(username)){
            userWarnIcon.getTooltip().setText("Username already existed");
            userWarnIcon.setVisible(true);
            return;
        }
        if(!HandleError.isValidEmail(email)){
            emailWarnIcon.getTooltip().setText("Email is invalid");
            emailWarnIcon.setVisible(true);
            return;
        }
        if(Database.getInstance().existedEmail(email)){
            emailWarnIcon.getTooltip().setText("This email is already used");
            emailWarnIcon.setVisible(true);
            return;
        }
        if(!HandleError.isValidPassword(pass)){
            passWarnIcon.setVisible(true);
            return;
        }
        if(!pass.equals(confirmPass)){
            confirmWarnIcon.setVisible(true);
            return;
        }

        if(Database.getInstance().addUser(email,username,pass)==1){
            createLoginWindow(e);
        }
    }

    private void hideWarnIcon(){
        userWarnIcon    .setVisible(false);
        emailWarnIcon   .setVisible(false);
        passWarnIcon    .setVisible(false);
        confirmWarnIcon .setVisible(false);
    }

    public void handleClickedLoginBtn(ActionEvent e){
        createLoginWindow(e);
    }

    private void createLoginWindow(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

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
