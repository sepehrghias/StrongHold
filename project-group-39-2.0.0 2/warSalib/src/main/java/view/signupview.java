package view;

import control.LoginSignupControl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.control.Label;

import javax.swing.*;

public class signupview extends Application {
    public static Stage signUpStage;
    public TextField signUpUsername;
    public PasswordField signUpPassword;
    public TextField signUpNickname;
    public TextField signUpEmail;
    public TextField signUpPasswordRecovery;

    public static Pane signUpPane;

    public static String username;

    public static String password;

    public static Label invalidUsername=new Label("invalid username format");

    public static Label isNotStrong=new Label("password is not strong");

    public static Label sameUsername=new Label("this username already exists");

    public static Label userNameError = new Label("user name is empty");
    public static Label passwordError = new Label("password is empty");
    public static Label nicknameError = new Label("nickname is empty");
    public static Label emailError = new Label("Email is empty");
    public  CheckBox sloganCheckBox;

    public static boolean passFlag;

    public void start(Stage stage) throws IOException {
        signUpStage = stage;
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/SignUp.fxml"));
        signUpPane = pane;
        Scene scene = new Scene(pane);
        signUpStage.setScene(scene);
        stage.setTitle("Sign up");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        Label nicknameLabel = new Label("nickname");
        Label EmailLabel = new Label("Email");
        Label passRecovery = new Label("Password recovery");
        usernameLabel.setLayoutX(20);
        usernameLabel.setLayoutY(125);
        usernameLabel.setTextFill(Color.WHITE);
        passwordLabel.setLayoutX(20);
        passwordLabel.setLayoutY(190);
        passwordLabel.setTextFill(Color.WHITE);
        Image eyeIcon = new Image(signupview.class.getResource("/images/eyeicon.png").toExternalForm());
        ImageView eyeView = new ImageView(eyeIcon);
        eyeView.setFitHeight(16);
        eyeView.setFitWidth(16);
        eyeView.setLayoutX(228);
        eyeView.setLayoutY(205);
        pane.getChildren().add(eyeView);
        Button button = new Button("Generate Random password");
        button.setLayoutX(280);
        button.setLayoutY(200);
        pane.getChildren().add(button);
        sloganCheckBox= (CheckBox) signUpPane.getChildren().get(1);
        sloganCheckBox.setTextFill(Color.WHITE);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                randomPassword();
            }

        });
        TextField passwordText = new TextField();
        passwordText.setLayoutX(100);
        passwordText.setLayoutY(200);
        pane.getChildren().add(passwordText);
        passwordText.setVisible(false);
        eyeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!passwordText.isVisible()) {
                    PasswordField passwordField = (PasswordField) pane.getChildren().get(2);
                    passwordText.setText(passwordField.getText());
                    pane.getChildren().remove(eyeView);
                    passwordText.setVisible(true);
                    pane.getChildren().add(eyeView);
                    passwordField.setVisible(false);
                } else {
                    PasswordField passwordField = (PasswordField) pane.getChildren().get(1);
                    passwordField.setText(passwordText.getText());
                    pane.getChildren().remove(eyeView);
                    passwordField.setVisible(true);
                    pane.getChildren().add(eyeView);
                    passwordText.setVisible(false);
                }

            }
        });
        nicknameLabel.setLayoutX(20);
        nicknameLabel.setLayoutY(270);
        nicknameLabel.setTextFill(Color.WHITE);
        EmailLabel.setLayoutX(20);
        EmailLabel.setLayoutY(350);
        EmailLabel.setTextFill(Color.WHITE);
        passRecovery.setLayoutX(5);
        passRecovery.setLayoutY(415);
        passRecovery.setTextFill(Color.WHITE);
        pane.getChildren().addAll(usernameLabel, passwordLabel, nicknameLabel, EmailLabel, passRecovery);

        Image image = new Image(StartGame.class.getResource("/images/26.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
    }

    @FXML
    public void initialize() {
        if (LoginSignupMenu.username != null) {
            signUpUsername.setText(LoginSignupMenu.username);

        }
        if (LoginSignupMenu.password != null) {
            signUpPassword.setText(LoginSignupMenu.password);

        }
        if(LoginSignupMenu.nickname!=null){
            signUpNickname.setText(LoginSignupMenu.nickname);
        }
        if(LoginSignupMenu.emailAddress!=null){
            signUpEmail.setText(LoginSignupMenu.emailAddress);
        }
        invalidUsername.setLayoutX(261);
        invalidUsername.setLayoutY(120);
        invalidUsername.setTextFill(Color.CRIMSON);
        sameUsername.setLayoutX(261);
        sameUsername.setLayoutY(120);
        sameUsername.setTextFill(Color.CRIMSON);
        isNotStrong.setLayoutX(460);
        isNotStrong.setLayoutY(200);
        isNotStrong.setTextFill(Color.CRIMSON);
        signUpUsername.textProperty().addListener((observable, oldText, newText) -> {
            if (LoginSignupMenu.checkUsername(newText).equals("invalid format")) {
                if (signUpPane.getChildren().contains(invalidUsername)) {

                } else {
                    signUpPane.getChildren().add(invalidUsername);
                }
            } else if (LoginSignupMenu.checkUsername(newText).equals("sameUsername")) {
                if (signUpPane.getChildren().contains(sameUsername)) {

                } else {
                    signUpPane.getChildren().add(sameUsername);
                }
            } else if (LoginSignupMenu.checkUsername(newText).equals("success")) {
                username = newText;
                LoginSignupMenu.username = newText;
                if (signUpPane.getChildren().contains(sameUsername)) {
                    signUpPane.getChildren().remove(sameUsername);

                } else if (signUpPane.getChildren().contains(invalidUsername)) {
                    signUpPane.getChildren().remove(invalidUsername);

                }
            }

        });
        signUpPassword.textProperty().addListener((observable, oldText, newText) -> {
            if (LoginSignupMenu.validPassword(newText).equals("upper case")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("lower case")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("number")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("length")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("special character")) {
                if (signUpPane.getChildren().contains(isNotStrong)) {

                } else {
                    isNotStrong.setText("password is not strong");
                    signUpPane.getChildren().add(isNotStrong);
                }
            } else if (LoginSignupMenu.validPassword(newText).equals("success")) {
                signUpPane.getChildren().remove(isNotStrong);
                LoginSignupMenu.password = newText;

            }
        });
    }

    private void randomPassword() {
        String randomPassword = LoginSignupControl.findRandomPassword();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "your random password is:  " + randomPassword, "Random Password", dialogButton);
        if (dialogButton == JOptionPane.YES_OPTION) {
            password = randomPassword;
            LoginSignupMenu.password = randomPassword;
            passFlag=true;
            if (dialogButton == JOptionPane.NO_OPTION) {
            }
        }


    }


    public void SignUp(MouseEvent mouseEvent) throws Exception {
        LoginSignupMenu.emailAddress=signUpEmail.getText();
        LoginSignupMenu.nickname=signUpNickname.getText();
        if (!signUpUsername.getText().equals("") && signUpPane.getChildren().contains(userNameError)) {
            signUpPane.getChildren().remove(userNameError);

        } else if (signUpUsername.getText().equals("") && !signUpPane.getChildren().contains(userNameError)) {

            userNameError.setTextFill(Color.RED);
            userNameError.setLayoutX(100);
            userNameError.setLayoutY(150);
            signUpPane.getChildren().add(userNameError);

        }
        if (!signUpPassword.getText().equals("") && signUpPane.getChildren().contains(passwordError)) {
            signUpPane.getChildren().remove(passwordError);

        } else if (signUpPassword.getText().equals("") && !signUpPane.getChildren().contains(passwordError)) {
            passwordError.setTextFill(Color.RED);
            passwordError.setLayoutX(100);
            passwordError.setLayoutY(240);
            signUpPane.getChildren().add(passwordError);
        }
        if (!signUpNickname.getText().equals("") && signUpPane.getChildren().contains(nicknameError)) {
            signUpPane.getChildren().remove(nicknameError);

        }
        if (signUpNickname.getText().equals("") && !signUpPane.getChildren().contains(nicknameError)) {
            nicknameError.setTextFill(Color.RED);
            nicknameError.setLayoutX(100);
            nicknameError.setLayoutY(320);
            signUpPane.getChildren().add(nicknameError);
        }
        if (!signUpEmail.getText().equals("") && signUpPane.getChildren().contains(emailError) && LoginSignupMenu.validEmail(signUpEmail.getText()).equals("success")) {
            signUpPane.getChildren().remove(emailError);

        } else {
            if (signUpEmail.getText().equals("") && !signUpPane.getChildren().contains(emailError)) {
                emailError.setTextFill(Color.RED);
                emailError.setLayoutX(100);
                emailError.setLayoutY(390);
                signUpPane.getChildren().add(emailError);
            } else {
                emailError.setLayoutX(100);
                emailError.setLayoutY(390);
                emailError.setTextFill(Color.RED);
                String result = LoginSignupMenu.validEmail(signUpEmail.getText());
                if (result.equals("invalid format")) {
                    emailError.setText("email format is invalid");
                    if (!signUpPane.getChildren().contains(emailError)) {
                        signUpPane.getChildren().add(emailError);
                    }

                } else if (result.equals("exists")) {
                    emailError.setText("This email already exists");
                    if (!signUpPane.getChildren().contains(emailError)) {
                        signUpPane.getChildren().add(emailError);
                    }

                } else if (result.equals("success")) {
                    if (signUpPane.getChildren().contains(emailError)) {
                        signUpPane.getChildren().remove(emailError);
                    }
                }
            }
        }
        if(signUpPane.getChildren().contains(invalidUsername)||signUpPane.getChildren().contains(sameUsername)||(signUpPane.getChildren().contains(isNotStrong)&&!passFlag)||(signUpUsername.getText().equals("")||signUpPassword.getText().equals("")||signUpNickname.getText().equals("")||signUpEmail.getText().equals(""))){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error in sign up");
            alert.showAndWait();
        }
        else {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("go to security question");
                alert.show();
            securityView securityView=new securityView();
            securityView.start(StartGame.stage);

        }
    }

    public void makeSloganTextField(MouseEvent mouseEvent) throws Exception {
        LoginSignupMenu.emailAddress=signUpEmail.getText();
        LoginSignupMenu.nickname=signUpNickname.getText();
        sloganView sloganView = new sloganView();
        sloganView.start(StartGame.stage);
    }
}


