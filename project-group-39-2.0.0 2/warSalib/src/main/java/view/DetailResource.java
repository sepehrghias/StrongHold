package view;

import control.StoreControl;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Game;
import view.enums.messages.StoreMenuMessage;

public class DetailResource extends Application {
    private Label number;
    public ImageView resourceImage;

    public Label name;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/detailResource.fxml"));
        Image image = new Image(StartGame.class.getResource("/images/mailBackground.jpeg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        number = new Label(  "number : " + String.valueOf(StoreControl.getAmountOfResource(Game.getResourceInMarket())));
        number.setLayoutY(330.0);
        number.setLayoutX(309.0);
        Button buy = new Button();
        buy.setLayoutX(257.0);
        buy.setLayoutY(167.0);
        buy.setPrefHeight(26.0);
        buy.setPrefWidth(137.0);
        buy.setText("buy  " + Game.getResourceInMarket().getCost());
        buy.setFont(new Font(19.0));
        buy.setOnMouseClicked(this :: buyResource);
        Button sell = new Button();
        sell.setLayoutX(257.0);
        sell.setLayoutY(234.0);
        sell.setPrefHeight(26.0);
        sell.setPrefWidth(137.0);
        sell.setText("sell  " + Game.getResourceInMarket().getCost());
        sell.setFont(new Font(19.0));
        sell.setOnMouseClicked(this :: sellResource);
        pane.getChildren().add(sell);
        pane.getChildren().add(buy);
        pane.setBackground(background);
        pane.getChildren().add(number);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {
        name.setText(Game.getResourceInMarket().getName());
        resourceImage.setImage(Game.getResourceMarket());
    }

    public void buyResource(MouseEvent mouseEvent) {
        StoreMenuMessage message = StoreControl.buyFromStore(Game.getResourceInMarket());
        if (message.equals(StoreMenuMessage.DONT_HAVE_BUDGET)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you don't have budget");
            alert.showAndWait();
        } else if (message.equals(StoreMenuMessage.SUCCESS)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("you successfully buy this goods");
            alert.showAndWait();
        }
        number.setText("number : " + String.valueOf(StoreControl.getAmountOfResource(Game.getResourceInMarket())));
    }

    public void sellResource(MouseEvent mouseEvent) {
        StoreMenuMessage message = StoreControl.sellFromStore(Game.getResourceInMarket());
        if (message.equals(StoreMenuMessage.NOT_ENOUGH_RESOURCE)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you don't have enough resource");
            alert.showAndWait();
        } else if (message.equals(StoreMenuMessage.SUCCESS)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("you successfully sell this goods");
            alert.showAndWait();
        }
        number.setText("number : " + String.valueOf(StoreControl.getAmountOfResource(Game.getResourceInMarket())));
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        MarketMenu marketMenu = new MarketMenu();
        marketMenu.start(StartGame.stage);
    }
}
