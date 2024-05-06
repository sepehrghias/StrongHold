package view;

import control.GameControl;
import control.MapControl;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class TradeUsersList extends Application {

    public static Pane tradeList;

    public static Stage tradeListStage;

    public static ArrayList<Button> usersButtons=new ArrayList<>();

    public static User userToTrade;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/TradeUsersList.fxml"));
        tradeList = pane;
        tradeListStage=stage;
        Image image = new Image(StartGame.class.getResource("/images/tradeList.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane, 840, 720);
        stage.setTitle("Trade List");
        stage.setScene(scene);
        stage.show();
        usersButtons.clear();
        showUsers();
        for (Button button:usersButtons){
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        checkUser(button);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }    }

    private void checkUser(Button button) throws Exception {
        if(Game.getCurrentUser().equals(Game.getPlayersInGame().get(usersButtons.indexOf(button)))){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you cant have trade with yours!");
            alert.showAndWait();
        }
        else {
            userToTrade=Game.getPlayersInGame().get(usersButtons.indexOf(button));
            Trade trade=new Trade();
            trade.start(StartGame.stage);
        }
    }

    private void showUsers() {
        VBox UsersVbox=new VBox();
        UsersVbox.setSpacing(60);
        for (int i=0;i< Game.getPlayersInGame().size();i++){
            HBox hBox=new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(30);
            Label label=new Label(Game.getPlayersInGame().get(i).getUsername());
            label.setTextFill(Color.WHITE);
            Button button=new Button("Choose this User");
            hBox.getChildren().add(label);
            hBox.getChildren().add(button);
            usersButtons.add(button);
            UsersVbox.getChildren().add(hBox);
        }
        tradeList.getChildren().add(UsersVbox);
    }
}
