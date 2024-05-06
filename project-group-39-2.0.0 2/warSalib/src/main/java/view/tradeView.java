package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;

public class tradeView extends Application {

    public static Pane tradePane;

    public static Stage tradeStage;


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/TradeView.fxml"));
        tradePane = pane;
        tradeStage=stage;
        Image image = new Image(StartGame.class.getResource("/images/TradeBack.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane, 840, 720);
        stage.setTitle("Trade View");
        stage.setScene(scene);
        stage.show();
    }
    public void createNewTrade(MouseEvent mouseEvent) throws Exception {
        TradeUsersList tradeUsersList=new TradeUsersList();
        tradeUsersList.start(StartGame.stage);
    }

    public void showSendsHistory(MouseEvent mouseEvent) throws Exception {
     SendTrades sendTrades=new SendTrades();
     sendTrades.start(StartGame.stage);
    }

    public void showGetsHistory(MouseEvent mouseEvent) throws Exception {
        GetTrades getTrades=new GetTrades();
        getTrades.start(StartGame.stage);
    }
}
