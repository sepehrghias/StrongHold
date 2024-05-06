package view;

import control.BuildingControl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;
import view.enums.messages.BuildingMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class BarrackMenu extends Application {
    public HBox soldiersSelection;
    private HashMap<Image, String> barrackImage = new HashMap<>();
    private Image selectedImage;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/barrack_menu.fxml"));
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
        initSoldiers();
    }

    private void initSoldiers() {
        for (Image image : getImageBarrack().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            imageView.setOnMouseEntered(event -> {
                // Change the border color when mouse enters the ImageView
                imageView.setOpacity(0.5);
            });

            // Set mouse exited event handler
            imageView.setOnMouseExited(event -> {
                if (selectedImage == null || !selectedImage.equals(imageView.getImage()))
                    imageView.setOpacity(1);
            });
            imageView.setOnMouseClicked(this::clicked);
            Tooltip tooltip = new Tooltip(barrackImage.get(image));
            Tooltip.install(imageView, tooltip);
            soldiersSelection.getChildren().add(imageView);
        }
    }

    private void clicked(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView) mouseEvent.getSource();
        if (selectedImage == null) {
            selectedImage = imageView.getImage();
            imageView.setOpacity(0.3);
        } else {
            if (selectedImage.equals(imageView.getImage())) {
                imageView.setOpacity(1);
            } else {
                imageView.setOpacity(0.2);
                selectedImage = imageView.getImage();
            }
        }
    }

    public void quit(MouseEvent mouseEvent) throws Exception {
        Game.getMapMenu().start(StartGame.stage);
    }

    public void create(MouseEvent mouseEvent) throws Exception {
        if (selectedImage == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("you don't choose nothing");
            alert.showAndWait();
        } else {
            BuildingMessage message = BuildingControl.createUnit(barrackImage.get(selectedImage));
            if (message.equals(BuildingMessage.NOT_ENOUGH_SOURCE)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("you don't have enough resources");
                alert.showAndWait();
            } else if (message.equals(BuildingMessage.NOT_ENOUGH_POPULATION)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("you don't have enough population");
                alert.showAndWait();
            } else
                Game.getMapMenu().start(StartGame.stage);
        }
    }

    private HashMap<Image, String> getImageBarrack() {
        Image archer = new Image(BarrackMenu.class.getResource("/images/Units/archer1.png").toExternalForm());
        barrackImage.put(archer, "archer");
        Image crossbowmen = new Image(BarrackMenu.class.getResource("/images/Units/crossbowmen1.png").toExternalForm());
        barrackImage.put(crossbowmen, "crossbowmen");
        Image spearMan = new Image(BarrackMenu.class.getResource("/images/Units/sperman1.png").toExternalForm());
        barrackImage.put(spearMan, "spear man");
        Image pikeMan = new Image(BarrackMenu.class.getResource("/images/Units/pikeman1.png").toExternalForm());
        barrackImage.put(pikeMan, "pike man");
        Image maceMan = new Image(BarrackMenu.class.getResource("/images/Units/maceman1.png").toExternalForm());
        barrackImage.put(maceMan, "maceman");
        Image swordMen = new Image(BarrackMenu.class.getResource("/images/Units/swordsmen1.png").toExternalForm());
        barrackImage.put(swordMen, "sword man");
        Image knight = new Image(BarrackMenu.class.getResource("/images/Units/knight1.png").toExternalForm());
        barrackImage.put(knight, "knight");
        Image tunneler = new Image(BarrackMenu.class.getResource("/images/Units/tunneler1.png").toExternalForm());
        barrackImage.put(tunneler, "tunneler");
        Image blackMonk = new Image(BarrackMenu.class.getResource("/images/Units/blackmonk1.png").toExternalForm());
        barrackImage.put(blackMonk, "black monk");
        return barrackImage;
    }
}
