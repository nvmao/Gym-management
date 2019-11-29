package com.mao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

        //SendEmail.sendMail(Data.getInstance().getUser().getEmail(),randomCode);
        textLabel.setText(textLabel.getText() + Data.getInstance().getUser().getEmail());

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
}
