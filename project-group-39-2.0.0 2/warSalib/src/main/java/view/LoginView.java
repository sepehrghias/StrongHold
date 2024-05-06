package view;

import control.LoginSignupControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends Application {
    public static Stage loginStage;
    public static Pane loginPane;
    public PasswordField loginPassword;
    public TextField loginUsername;

    public static String path;
    public TextField loginCaptcha;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/Login.fxml"));
        loginPane = pane;
        Scene scene = new Scene(pane, 840, 720);
        loginStage = stage;
        stage.setTitle("Login View");
        Label username = new Label("username");
        username.setLayoutX(210);
        username.setLayoutY(120);
        username.setFont(Font.font(16));
        pane.getChildren().add(username);
        username.setTextFill(Color.WHITE);
        Label password = new Label("password");
        password.setLayoutX(210);
        password.setLayoutY(200);
        Rectangle rectangle = new Rectangle(150, 40);
        int Random;
        Random = LoginSignupControl.makeRandomCaptcha();
        path = String.valueOf(Random);
        rectangle.setFill(new ImagePattern(new Image(StartGame.class.getResource("/images/captcha/" + path + ".png").toExternalForm())));
        rectangle.setLayoutY(280);
        rectangle.setLayoutX(400);
        pane.getChildren().add(rectangle);
        password.setTextFill(Color.WHITE);
        password.setFont(Font.font(16));
        pane.getChildren().add(password);
        Image image = new Image(StartGame.class.getResource("/images/14.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        stage.setScene(scene);
        stage.show();

    }

    public void forgotPassword(MouseEvent mouseEvent) {
        Text securityQuestions = new Text("Pick your security question:\n" + " 1. What is my father’s name?\n" +
                "What was my first pet's name?\n" + " 3. What is my mother’s last name?");
        securityQuestions.setLayoutX(400);
        securityQuestions.setLayoutY(100);
        securityQuestions.setFill(Color.WHITE);
        securityQuestions.setFont(Font.font(14));
        TextField answerText = new TextField();
        answerText.setPromptText("security answer");
        answerText.setLayoutX(400);
        answerText.setLayoutY(200);
        loginPane.getChildren().add(answerText);
        loginPane.getChildren().add(securityQuestions);
        Button submitAnswer = new Button("submit");
        submitAnswer.setLayoutX(400);
        submitAnswer.setLayoutY(500);
        loginPane.getChildren().add(submitAnswer);
        submitAnswer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    checkSecurityAnswer(answerText);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void checkSecurityAnswer(TextField answerText) throws Exception {
        if (loginCaptcha.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Captcha is empty");
            alert.showAndWait();

        } else if (loginUsername == null || answerText == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username or answer is empty");
            alert.showAndWait();

        } else if (loginUsername.getText().equals("") || answerText.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username or answer is empty");
            alert.showAndWait();
        } else {
            String username = loginUsername.getText();
            String result = LoginSignupMenu.forgotPassword(username, answerText.getText());
            if (result.equals("username is invalid")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username doesn't exists");
                alert.showAndWait();

            } else if (result.equals("answer is invalid")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("answer is invalid");
                alert.showAndWait();

            } else if (result.equals("success")) {
                MainView mainView = new MainView();
                mainView.start(StartGame.stage);
            }
        }
    }

    public void goMainMenu(MouseEvent mouseEvent) throws Exception {
        if (loginUsername.getText().equals("") || loginPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username or password is empty");
            alert.showAndWait();

        } else if (!loginCaptcha.getText().equals(path)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("captcha is invalid");
            alert.showAndWait();

        } else {

            String result = LoginSignupMenu.loginUser(loginUsername.getText(), loginPassword.getText());
            if (result.equals("success")) {
                MainView mainView = new MainView();
                mainView.start(StartGame.stage);
            } else if (result.equals("username not found")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("username not found");
                alert.showAndWait();
                LoginView loginView = new LoginView();
                loginView.start(StartGame.stage);
            } else if (result.equals("password is incorrect")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("password is incorrect");
                alert.showAndWait();
                LoginView loginView = new LoginView();
                loginView.start(StartGame.stage);

            }
        }
    }
}
