package view;

import control.BuildingControl;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;
import view.enums.messages.BuildingMessage;

import java.util.HashMap;
import java.util.Map;

public class MercenaryPost extends Application {
    public HBox soldierSelection;
    private HashMap<Image, String> mercenaryImage = new HashMap<>();
    private Image selectedImage;
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/mercenary_post.fxml"));
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
        for (Image image : getImageMercenary().keySet()) {
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
            Tooltip tooltip = new Tooltip(mercenaryImage.get(image));
            Tooltip.install(imageView, tooltip);
            soldierSelection.getChildren().add(imageView);
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

    private HashMap<Image, String> getImageMercenary() {
        Image arabianSwordsMan = new Image(MercenaryPost.class.getResource("/images/Units/arabianswordsmen1.png").toExternalForm());
        mercenaryImage.put(arabianSwordsMan, "arabian swords man");
        Image archerBow= new Image(MercenaryPost.class.getResource("/images/Units/archerbow1 .png").toExternalForm());
        mercenaryImage.put(archerBow, "archer bow");
        Image assassin = new Image(MercenaryPost.class.getResource("/images/Units/assassin1.png").toExternalForm());
        mercenaryImage.put(assassin, "assassin");
        Image fireThrower = new Image(MercenaryPost.class.getResource("/images/Units/firethrower1.png").toExternalForm());
        mercenaryImage.put(fireThrower, "fire thrower");
        Image horseArcher = new Image(MercenaryPost.class.getResource("/images/Units/horsearchers1.png").toExternalForm());
        mercenaryImage.put(horseArcher, "horse archer");
        Image slinger= new Image(MercenaryPost.class.getResource("/images/Units/slingers1.png").toExternalForm());
        mercenaryImage.put(slinger, "slinger");
        return mercenaryImage;
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
            BuildingMessage message = BuildingControl.createUnit(mercenaryImage.get(selectedImage));
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
}
