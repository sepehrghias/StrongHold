package view;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;
import model.government.resource.Resource;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import model.government.resource.resourcesImage;
import model.government.trade.Donate;
import model.government.trade.Request;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Trade extends Application {
    public static Pane trade;

    public static Stage tradeStage;

    public static ArrayList<resourcesImage> resourcesImages = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/Trade.fxml"));
        trade = pane;
        tradeStage = stage;
        Scene scene = new Scene(pane, 840, 720);
        stage.setTitle("    Trade   ");
        stage.setScene(scene);
        Image image = new Image(StartGame.class.getResource("/images/mailBackground.jpeg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        stage.show();
        resourcesImages.clear();
        showResources();
        Button button = new Button("back");
        button.setLayoutX(10);
        button.setLayoutY(100);
        trade.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    back();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void back() throws Exception {
        MarketMenu marketMenu = new MarketMenu();
        marketMenu.start(StartGame.stage);
    }

    private void showResources() {
        HBox resourcesHBox = new HBox();
        resourcesHBox.setSpacing(20);
        //  resourcesHBox.setAlignment(Pos.CENTER);
        resourcesHBox.setLayoutX(300);
        resourcesHBox.setLayoutY(300);
        for (Resource resource : Game.getCurrentUser().getUserGovernment().getResources().keySet()) {
            resourcesImage resourcesImage1 = new resourcesImage(50, 50);
            String resource1 = String.valueOf(resource);
            resourcesImage1.setResourcePath(resource1);
            String path = "/images/resource/" + resource1 + ".png";
            if (new Image(StartGame.class.getResource(path).toExternalForm()) != null) {
                ImagePattern Image = new ImagePattern(new Image(StartGame.class.getResource(path).toExternalForm()));
                resourcesImage1.setFill(Image);
                resourcesHBox.getChildren().add(resourcesImage1);
                Tooltip tooltip = new Tooltip(resourcesImage1.getResourcePath());
                Tooltip.install(resourcesImage1, tooltip);
                resourcesImages.add(resourcesImage1);
            }
        }
        trade.getChildren().add(resourcesHBox);
        checkClickResource();
    }

    private void checkClickResource() {
        for (resourcesImage resourcesImage : resourcesImages) {
            resourcesImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    makeBoxForChoose(resourcesImage);
                }
            });
        }
    }

    private void makeBoxForChoose(resourcesImage resourcesImage) {
        Button donate = new Button("donate");
        Button request = new Button("request");
        TextField message = new TextField();
        message.setPromptText("message");
        donate.setLayoutX(400);
        donate.setLayoutY(500);
        request.setLayoutX(500);
        request.setLayoutY(500);
        message.setLayoutX(389);
        message.setLayoutY(600);
        trade.getChildren().addAll(donate, request, message);
        Label inventory = new Label();
        inventory.setLayoutX(60);
        inventory.setLayoutY(30);
        inventory.setTextFill(Color.WHITE);
        Resource resource = Resource.valueOf(resourcesImage.getResourcePath());
        String resInventory = String.valueOf(resourcesImage.getResourcePath()+"     :    "+Game.getCurrentUser().getUserGovernment().getResources().get(resource));
        inventory.setText(resInventory);
        javafx.scene.shape.Rectangle plusImage = new javafx.scene.shape.Rectangle(20, 20);
        ImagePattern plus = new ImagePattern(new Image(StartGame.class.getResource("/images/resource/plus.png").toExternalForm()));
        plusImage.setFill(plus);
        plusImage.setLayoutX(400);
        plusImage.setLayoutY(400);
        javafx.scene.shape.Rectangle minusImage = new javafx.scene.shape.Rectangle(20, 20);
        ImagePattern minus = new ImagePattern(new Image(StartGame.class.getResource("/images/resource/minus.png").toExternalForm()));
        minusImage.setFill(minus);
        minusImage.setLayoutX(300);
        Label numberToTrade = new Label("0");
        numberToTrade.setLayoutX(360);
        numberToTrade.setLayoutY(400);
        minusImage.setLayoutY(400);
        trade.getChildren().addAll(inventory, plusImage, minusImage, numberToTrade);
        plusImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                increase(numberToTrade);
            }
        });
        minusImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                decrease(numberToTrade);
            }
        });
        donate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    donating(resourcesImage, numberToTrade, message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        request.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    requesting(resourcesImage, numberToTrade, message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void donating(resourcesImage resourcesImage, Label numberToTrade, TextField message) throws IOException {
        Resource resource = Resource.valueOf(resourcesImage.getResourcePath());
        int number = 0;
        String message1 = null;
        if (numberToTrade != null) {
            number = Integer.parseInt(numberToTrade.getText());
        }
        if (message.getText() != null) {
            message1 = message.getText();
        }
        if (number != 0 && !message1.equals("")) {
            if (Game.getCurrentUser().getUserGovernment().getResources().get(resource) < number) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("this number is bigger than your inventory");
                alert.showAndWait();
            } else {
                Donate donate = new Donate(message1, number, resource, Game.getCurrentUser(), TradeUsersList.userToTrade);
                StringBuilder don = new StringBuilder();
                StringBuilder don2 = new StringBuilder();
                if (Game.getCurrentUser().getSendDonates() != null) {
                    don.append(Game.getCurrentUser().getSendDonates());

                }
                if (TradeUsersList.userToTrade.getGetDonates() != null) {
                    don2.append(TradeUsersList.userToTrade.getGetDonates());

                }
                String res = String.valueOf(resource);
                String num = String.valueOf(number);
                String dons = res + " " + num + " " + message1 + "." + TradeUsersList.userToTrade.getUsername() + "\n";
                String dons2 = res + " " + num + " " + message1 + "." + Game.getCurrentUser().getUsername() + "\n";
                don.append(dons);
                don2.append(dons2);
                Game.getCurrentUser().setSendDonates(don.toString());
                TradeUsersList.userToTrade.setGetDonates(don2.toString());
                for (adam adam : adam.adams) {
                    if (adam.username.equals(Game.getCurrentUser().getUsername())) {
                        adam.sendDonates = Game.getCurrentUser().getSendDonates();
                    }
                }
                for (adam adam : adam.adams) {
                    if (adam.username.equals(TradeUsersList.userToTrade.getUsername())) {
                        adam.getDonates = TradeUsersList.userToTrade.getGetDonates();
                    }
                }
                FileWriter fileWriter = new FileWriter("users.json");
                fileWriter.write(new Gson().toJson(view.adam.adams));
                fileWriter.close();

                Donate.donates.add(donate);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("donate maked successfully");
                alert.showAndWait();
            }
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please fill message");
            alert.showAndWait();

        }    }

    private void requesting(resourcesImage resourcesImage, Label numberToTrade, TextField message) throws IOException {
        Resource resource = Resource.valueOf(resourcesImage.getResourcePath());
        int number = 0;
        String message1 = null;
        if (numberToTrade != null) {
            number = Integer.parseInt(numberToTrade.getText());
        }
        if (message.getText() != null) {
            message1 = message.getText();
        }
        if (number != 0 && !message1.equals("")) {
            Request request = new Request(message1, number, resource, Game.getCurrentUser(), TradeUsersList.userToTrade);
            Request.requests.add(request);
            StringBuilder req = new StringBuilder();
            StringBuilder req2 = new StringBuilder();
            if (Game.getCurrentUser().getSendRequests() != null) {
                req.append(Game.getCurrentUser().getSendRequests());
            }
            if (TradeUsersList.userToTrade.getGetRequests() != null) {
                req2.append(TradeUsersList.userToTrade.getGetRequests());

            }
            String res = String.valueOf(resource);
            String num = String.valueOf(number);
            String reqs = res + " " + num + " " + message1 + "." + TradeUsersList.userToTrade.getUsername() + "\n";
            String reqs2 = res + " " + num + " " + message1 + "." + Game.getCurrentUser().getUsername() + "\n";
            req.append(reqs);
            req2.append(reqs2);
            Game.getCurrentUser().setSendRequests(req.toString());
            TradeUsersList.userToTrade.setGetRequests(req2.toString());
            for (adam adam : adam.adams) {
                if (adam.username.equals(Game.getCurrentUser().getUsername())) {
                    adam.sendRequests = Game.getCurrentUser().getSendRequests();
                }
            }
            for (adam adam : adam.adams) {
                if (adam.username.equals(TradeUsersList.userToTrade.getUsername())) {
                    adam.getRequests = TradeUsersList.userToTrade.getGetRequests();
                }
            }
            FileWriter fileWriter = new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(view.adam.adams));
            fileWriter.close();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("request maked successfully");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please fill message box");
            alert.showAndWait();
        }
    }

    private void increase(Label numberToTrade) {
        int prevNumber = Integer.parseInt(numberToTrade.getText());
        int nexNumber = prevNumber + 1;
        numberToTrade.setText(String.valueOf(nexNumber));
    }

    private void decrease(Label numberToTrade) {
        int prevNumber = Integer.parseInt(numberToTrade.getText());
        int nexNumber = prevNumber - 1;
        if (nexNumber < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you cant put it under 0");
            alert.showAndWait();
        } else {
            numberToTrade.setText(String.valueOf(nexNumber));
        }
    }
}
