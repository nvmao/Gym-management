package com.mao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

class ResendEmail extends Task{
    private String randomCode;
    private JFXProgressBar progressBar;
    private JFXButton confirmBtn;
    private JFXTextField confirmTextField;
    private Label resendLabel;


    public ResendEmail(String randomCode, JFXProgressBar progressBar, JFXButton confirmBtn, JFXTextField confirmTextField, Label resendLabel) {
        this.randomCode = randomCode;
        this.progressBar = progressBar;
        this.confirmBtn = confirmBtn;
        this.confirmTextField = confirmTextField;
        this.resendLabel = resendLabel;
    }

    @Override
    protected Object call() throws Exception {
        progressBar.setVisible(true);
        confirmBtn.setDisable(true);
        confirmTextField.setDisable(true);
        resendLabel.setDisable(true);
        SendEmail.sendMail(Data.getInstance().getUser().getEmail(),randomCode);
        progressBar.setVisible(false);
        confirmBtn.setDisable(false);
        confirmTextField.setDisable(false);
        resendLabel.setDisable(false);
        return null;
    }
}

public class ConfirmController implements Initializable {

    private String randomCode;
    @FXML
    private Label textLabel;
    @FXML
    private JFXTextField codeTextField;
    @FXML
    private Label resendLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXButton confirmBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateCode();

        textLabel.setText(textLabel.getText() + Data.getInstance().getUser().getEmail());
        new Thread(new ResendEmail(randomCode,progressBar,confirmBtn,codeTextField,resendLabel)).start();

        resendLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                generateCode();
                ResendEmail resendEmail = new ResendEmail(randomCode,progressBar,confirmBtn,codeTextField,resendLabel);
                new Thread(resendEmail).start();

            }
        });
    }

    public void submitCode(ActionEvent e){
        if(codeTextField.getText().equals(randomCode)){
            errorLabel.setVisible(false);
            Database.getInstance().confirmEmail(Data.getInstance().getUser().getEmail());
            createHomeWindow(e);
        }
        else{
            errorLabel.setVisible(true);
        }
    }

    private void generateCode(){
        String code = "";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for(int i = 0 ; i < 8;i++){
            int rand = (int)(Math.random() * chars.length());
            code += chars.charAt(rand);
        }

        randomCode = code;
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
}
