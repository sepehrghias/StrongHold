package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Game;
import model.government.resource.Resource;
import model.government.trade.Donate;
import model.government.trade.Request;

import java.util.HashMap;

public class GetTrades extends Application {
    public static Pane getTrades;

    public static Stage getStage;

    public static HashMap<Button, Request> reqButtons = new HashMap<>();
    public static HashMap<Button, Donate> donButtons = new HashMap<>();


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/getTrades.fxml"));
        getTrades = pane;
        getStage = stage;
        Image image = new Image(StartGame.class.getResource("/images/mailBackground.jpeg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane, 840, 720);
        stage.setTitle("Get Trades");
        stage.setScene(scene);
        stage.show();
        show();
        buttonsClick();
    }

    private void buttonsClick() {
        for (Button button : donButtons.keySet()) {
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    acceptDonate(donButtons.get(button));
                }
            });
        }
        for (Button button : reqButtons.keySet()) {
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    acceptRequest(reqButtons.get(button));
                }
            });

        }
    }

    private void acceptRequest(Request request) {
        Resource requestingResource = request.getResource();
            if (Game.getCurrentUser().getUserGovernment().getResources().containsKey(requestingResource)) {
                if (Game.getCurrentUser().getUserGovernment().getResources().get(requestingResource) < request.getNumber()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("requesting resource is more than your inventory");
                    alert.showAndWait();

                }
                else {
                    request.setAccept(true);
                    Game.getCurrentUser().getUserGovernment().getResources().put(requestingResource,Game.getCurrentUser().getUserGovernment().getResources().get(requestingResource)-request.getNumber());
                    request.getSenderRequest().getUserGovernment().getResources().put(requestingResource,request.getSenderRequest().getUserGovernment().getResources().get(requestingResource)+request.getNumber());
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("done successfully");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you dont have this resource to give");
                alert.showAndWait();

            }
        }

    private void acceptDonate(Donate donate) {
        Resource donatingResource = donate.getResource();
        donate.setAccept(true);
        if (Game.getCurrentUser().getUserGovernment().getResources().containsKey(donatingResource)) {
            Game.getCurrentUser().getUserGovernment().getResources().put(donatingResource, Game.getCurrentUser().getUserGovernment().getResources().get(donatingResource) + donate.getNumber());
            donate.getSenderDonate().getUserGovernment().getResources().put(donatingResource,donate.getSenderDonate().getUserGovernment().getResources().get(donatingResource)-donate.getNumber());

        } else {
            Game.getCurrentUser().getUserGovernment().getResources().put(donatingResource, donate.getNumber());
            donate.getSenderDonate().getUserGovernment().getResources().put(donatingResource,donate.getSenderDonate().getUserGovernment().getResources().get(donatingResource)-donate.getNumber());

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("donate get successfully");
        alert.showAndWait();
    }

    private void show() {
        HBox showgets = new HBox();
        VBox showVbox = new VBox();
        showVbox.setSpacing(20);
        showgets.setSpacing(15);
        for (Donate donate : Donate.donates) {
            System.out.println(donate.getSenderDonate().getUsername() + "   " + donate.getMessage() + "   " + donate.getGetterDonate().getUsername());
            if (donate.getGetterDonate().equals(Game.getCurrentUser())) {
                HBox hBox = new HBox();
                Button button1 = null;
                String res = String.valueOf(donate.getResource());
                String num = String.valueOf(donate.getNumber());
                String sender = donate.getSenderDonate().getUsername();
                String message = donate.getMessage();
                String status = null;
                if (donate.isAccept()) {
                    status = "confirm";
                } else {
                    button1 = new Button("accept");
                    status = "dont confirm";

                }
                Label label = new Label("Donate:  " + res + "  " + num + "  " + sender + "  " + message + "  " + status);
                label.setFont(Font.font(16));
                label.setTextFill(Color.WHITE);
                hBox.getChildren().add(label);
                if (button1 != null) {
                    donButtons.put(button1, donate);
                    hBox.getChildren().add(button1);
                }
                showVbox.getChildren().add(hBox);
            }
        }
        for (Request request : Request.requests) {
            System.out.println(request.getSenderRequest().getUsername()+" REQUEST  "+request.getGetterRequest().getUsername());
            if (request.getGetterRequest().equals(Game.getCurrentUser())) {
                HBox hBox = new HBox();
                String res = String.valueOf(request.getResource());
                String num = String.valueOf(request.getNumber());
                String sender = request.getSenderRequest().getUsername();
                String message = request.getMessage();
                String status = null;
                Button button = null;
                if (request.isAccept()) {
                    status = "confirm";
                } else {
                    button = new Button("accept");
                    status = "dont confirm";

                }
                Label label = new Label("Request:  " + res + "  " + num + "  " + sender + "  " + message + "  " + status);
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font(16));
                hBox.getChildren().add(label);
                if (button != null) {
                    reqButtons.put(button, request);
                    hBox.getChildren().add(button);
                }
                showVbox.getChildren().add(hBox);
            }
        }

        getTrades.getChildren().add(showVbox);
    }

    @FXML
    private void back() throws Exception {
        MarketMenu marketMenu = new MarketMenu();
        marketMenu.start(StartGame.stage);
    }
}
