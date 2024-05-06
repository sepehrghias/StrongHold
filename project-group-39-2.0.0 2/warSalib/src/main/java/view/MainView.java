package view;

import control.LoginSignupControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Game;
import model.government.Government;
import model.map.GameMap;

import java.util.ArrayList;

public class MainView extends Application {
    public Pane mainPane;

    public Stage mainStage;
    public static int tunrsofGame;


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/MainMenu.fxml"));
        mainPane = pane;
        Scene scene = new Scene(pane, 840, 720);
        mainStage = stage;
        stage.setTitle("Main Menu");
        Image image = new Image(StartGame.class.getResource("/images/09.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        stage.setScene(scene);
        stage.show();
    }

    public void StartNewGame(MouseEvent mouseEvent) {
        Game.getPlayersInGame().clear();
        ArrayList<Label> labels=new ArrayList<>();
        ArrayList<TextField> players=new ArrayList<>();
        Label GameTurns=new Label("Turns:   ");
        TextField gameTurns=new TextField();
        gameTurns.setPromptText("Turns of Game:");
        GameTurns.setLayoutX(120);
        GameTurns.setLayoutY(20);
        gameTurns.setLayoutX(200);
        gameTurns.setLayoutY(20);
        mainPane.getChildren().addAll(GameTurns,gameTurns);
        for (int i=2;i<9;i++){
            Label label=new Label("Player  "+ i);
            label.setLayoutX(120);
            label.setLayoutY(20+40*i);
            label.setTextFill(Color.WHITE);
            labels.add(label);
            TextField textField=new TextField();
            textField.setPromptText("Player    "+i);
            textField.setFont(Font.font(String.valueOf(Color.WHITE)));
            textField.setLayoutX(200);
            textField.setLayoutY(20+40*i);
            players.add(textField);
            mainPane.getChildren().addAll(label,textField);

        }
        Button start=new Button("Start Game");
        start.setLayoutX(120);
        start.setLayoutY(500);
        mainPane.getChildren().add(start);
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Game.getPlayersInGame().clear();
                Game.getPlayersInGame().add(Game.getCurrentUser());
                turns(gameTurns);
                checkPlayers(players);
                if(Game.getPlayersInGame().size()!=0&&tunrsofGame>0){
                    Government governmentOfCurrentUser = new Government(0 , 30, Game.getCurrentUser());
                    Game.getCurrentUser().setUserGovernment(governmentOfCurrentUser);
                    Game.addGovernment(governmentOfCurrentUser);
                    Game.setGameStarter(Game.getCurrentUser());
                    Game.setTurnedUserForGame(Game.getCurrentUser());
                    GameMap gameMap = new GameMap();
                    Game.setMapInGame(gameMap);
                    MapMenu mapMenu=new MapMenu();
                    Game.setMapMenu(mapMenu);
                    try {
                        MapMenu.counterTurns=0;
                        mapMenu.start(StartGame.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
    }

    private void turns(TextField gameTurns) {
        if(gameTurns==null||gameTurns.getText().equals("")){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please complete number of turns");
            alert.showAndWait();
        }
        else {
            tunrsofGame= Integer.parseInt(gameTurns.getText());
        }
    }

    private void checkPlayers(ArrayList<TextField> players) {
        if(players.size()==1){
            Label label=new Label("Players are empty");
            label.setLayoutX(120);
            label.setLayoutY(700);
            mainPane.getChildren().add(label);
        }
        else {
            for (int i = 2; i < players.size(); i++) {
                if (LoginSignupControl.findUser(players.get(i - 2).getText())) {
                    Game.getPlayersInGame().add(LoginSignupControl.getUserByName(players.get(i - 2).getText()));
                    Government governmentOfUser = new Government(0, 30, Game.getPlayersInGame().get(i - 1));
                    Game.getPlayersInGame().get(i - 1).setUserGovernment(governmentOfUser);
                    Game.addGovernment(governmentOfUser);
                } else {
                    Label label = new Label("player doesnt find");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(360);
                    label.setLayoutY(20 + 40 * i);
                    mainPane.getChildren().add(label);

                }

            }
        }
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Game.setCurrentUser(null);
        StartGame startGame=new StartGame();
        startGame.start(StartGame.stage);
    }

    public void goProfileMenu(MouseEvent mouseEvent) throws Exception {
        profileView profileView=new profileView();
        profileView.start(StartGame.stage);
    }
}