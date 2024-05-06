package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Game;
import model.government.resource.Resource;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import control.BuildingControl;
import control.GameControl;
import control.MapControl;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Game;
import model.government.Government;
import model.government.building.group.BuildingImages;
import model.government.people.People;
import model.government.people.units.*;
import model.government.people.units.UnitButton;
import javafx.scene.image.Image;
import model.government.building.Building;
import model.government.resource.Resource;
import model.government.trade.Donate;
import model.government.trade.Request;
import model.map.GameMap;
import model.map.Tile;
import model.user.User;
import view.enums.commands.MapMenuCommands;
import javafx.scene.layout.*;
import view.enums.messages.BuildingMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;


public class SendTrades extends Application {
    public static Pane sendTrade;

    public static Stage sendStage;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/sendTrades.fxml"));
        sendTrade = pane;
        sendStage = stage;
        Image image = new Image(StartGame.class.getResource("/images/mailBackground.jpeg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane, 840, 720);
        stage.setTitle("Send Trades");
        stage.setScene(scene);
        stage.show();
        show();
    }

    private void show() {
        HBox showSends = new HBox();
        VBox showVbox = new VBox();
        showVbox.setSpacing(30);
        showSends.setSpacing(15);
        for (Donate donate : Donate.donates) {
            if (donate.getSenderDonate().equals(Game.getCurrentUser())) {
                String res = String.valueOf(donate.getResource());
                String num = String.valueOf(donate.getNumber());
                String getter = donate.getGetterDonate().getUsername();
                String message = donate.getMessage();
                String status = null;
                if (donate.isAccept()) {
                    status = "confirm";
                } else {
                    status = "dont confirm";

                }
                Label label = new Label("Donate:  " + res + "  " + num + "  " + getter + "  " + message + "  " + status);
                label.setLayoutX(20);
                label.setFont(Font.font(15));
                label.setTextFill(Color.WHITE);
                showVbox.getChildren().add(label);
            }
        }
        for (Request request : Request.requests) {
            if (request.getSenderRequest().equals(Game.getCurrentUser())) {
                String res = String.valueOf(request.getResource());
                String num = String.valueOf(request.getNumber());
                String getter = request.getGetterRequest().getUsername();
                String message = request.getMessage();
                String status = null;
                if (request.isAccept()) {
                    status = "confirm";
                } else {
                    status = "dont confirm";

                }
                Label label = new Label("Request:  " + res + "  " + num + "  " + getter + "  " + message + "  " + status);
                label.setLayoutX(20);
                label.setFont(Font.font(15));
                label.setTextFill(Color.WHITE);
                showVbox.getChildren().add(label);
            }
        }
        sendTrade.getChildren().add(showVbox);

    }

    @FXML
    private void back() throws Exception {
        MarketMenu marketMenu = new MarketMenu();
        marketMenu.start(StartGame.stage);
    }
}
