package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;
import model.government.resource.Resource;

public class MarketMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/market.fxml"));
        Image image = new Image(StartGame.class.getResource("/images/mailBackground.jpeg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void archer(MouseEvent mouseEvent) throws Exception {
        Game.setResourceInMarket(Resource.ARCHER);
        Game.setResourceMarket(new Image(MarketMenu.class.getResource("/images/arcger.png").toExternalForm()));
        DetailResource detailResource = new DetailResource();
        detailResource.start(StartGame.stage);
    }

    public void hop(MouseEvent mouseEvent) throws Exception {
        Game.setResourceInMarket(Resource.HOP);
        Game.setResourceMarket(new Image(MarketMenu.class.getResource("/images/hop.png").toExternalForm()));
        DetailResource detailResource = new DetailResource();
        detailResource.start(StartGame.stage);
    }

    public void armour(MouseEvent mouseEvent) throws Exception {
        Game.setResourceInMarket(Resource.ARMOUR);
        Game.setResourceMarket(new Image(MarketMenu.class.getResource("/images/armour.jpeg").toExternalForm()));
        DetailResource detailResource = new DetailResource();
        detailResource.start(StartGame.stage);
    }

    public void iron(MouseEvent mouseEvent) throws Exception {
        Game.setResourceInMarket(Resource.IRON);
        Game.setResourceMarket(new Image(MarketMenu.class.getResource("/images/iron.png").toExternalForm()));
        DetailResource detailResource = new DetailResource();
        detailResource.start(StartGame.stage);
    }

    public void stone(MouseEvent mouseEvent) throws Exception {
        Game.setResourceInMarket(Resource.STONE);
        Game.setResourceMarket(new Image(MarketMenu.class.getResource("/images/stone2.png").toExternalForm()));
        DetailResource detailResource = new DetailResource();
        detailResource.start(StartGame.stage);
    }

    public void spear(MouseEvent mouseEvent) throws Exception {
        Game.setResourceInMarket(Resource.SPEAR);
        Game.setResourceMarket(new Image(MarketMenu.class.getResource("/images/spearIcon.png").toExternalForm()));
        DetailResource detailResource = new DetailResource();
        detailResource.start(StartGame.stage);
    }

    public void wood(MouseEvent mouseEvent) throws Exception {
        Game.setResourceInMarket(Resource.WOOD);
        Game.setResourceMarket(new Image(MarketMenu.class.getResource("/images/wood.png").toExternalForm()));
        DetailResource detailResource = new DetailResource();
        detailResource.start(StartGame.stage);
    }

    public void quit(MouseEvent mouseEvent) throws Exception {
        Game.getMapMenu().start(StartGame.stage);
    }

    public void goTradeView(MouseEvent mouseEvent) throws Exception {
        tradeView tradeView = new tradeView();
        tradeView.start(StartGame.stage);
    }
}
