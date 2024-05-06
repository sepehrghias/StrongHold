package view;

import control.LoginSignupControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.control.Button;
import javafx.util.Duration;

import java.awt.*;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import model.Game;
import model.map.GameMap;

public class StartGame extends Application {
    public static Stage stage;

    public static Pane startPane;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/StartGame.fxml"));
        StartGame.startPane=pane;
        Scene scene = new Scene(pane,840,720);
        StartGame.stage=stage;
        stage.setTitle("Welcome to the Game");
        Image image=new Image(StartGame.class.getResource("/images/thumb-1920-680255.jpg").toExternalForm());
        BackgroundFill backgroundFill=new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background=new Background(backgroundFill);
        pane.setBackground(background);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LoginSignupControl.readUsersData();
        LoginSignupControl.loadTrade();
        launch();
    }

    public void goLoginView(MouseEvent mouseEvent) throws Exception {
        LoginView loginView=new LoginView();
        loginView.start(stage);
    }

    public void goSignupView(MouseEvent mouseEvent) throws IOException {
        signupview signupview=new signupview();
        signupview.start(stage);
    }

    public static void setStartPane(Pane startPane) {
        StartGame.startPane = startPane;
    }

}
