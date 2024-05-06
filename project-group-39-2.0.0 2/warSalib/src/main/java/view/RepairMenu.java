package view;

import control.BuildingControl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;
import model.government.building.Building;
import view.enums.messages.BuildingMessage;

public class RepairMenu extends Application {
    public Label buildingName;
    public Label hp;
    public Label maxHp;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/repairMenu.fxml"));
        Image image = new Image(StartGame.class.getResource("/images/mailBackground.jpeg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        buildingName.setText(Game.getSelectedBuilding().getName());
        String hp1 = String.valueOf(Game.getSelectedBuilding().getHp());
        String maxHP = String.valueOf(Game.getSelectedBuilding().getMaxHP());
        hp.setText(hp1);
        maxHp.setText(maxHP);
    }
    public void quit(MouseEvent mouseEvent) throws Exception {
        Game.getMapMenu().start(StartGame.stage);
    }

    public void increaseHp(MouseEvent mouseEvent) throws Exception {
        BuildingMessage message = BuildingControl.repair();
        if (message.equals(BuildingMessage.SUCCESS)) {
            Game.getMapMenu().start(StartGame.stage);
        } else if (message.equals(BuildingMessage.HAS_FULL_HP)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("this building has full hp");
            alert.showAndWait();
        } else if (message.equals(BuildingMessage.NOT_ENOUGH_STONE)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you don't have enough stone");
            alert.showAndWait();
        } else if (message.equals(BuildingMessage.NEAR_ENEMY)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("enemy attack to this building");
            alert.showAndWait();
        }
    }
}
