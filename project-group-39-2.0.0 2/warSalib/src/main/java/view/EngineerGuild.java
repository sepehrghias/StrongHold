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

public class EngineerGuild extends Application {
    public HBox unitSelection;
    private HashMap<Image, String> engineerImage = new HashMap<>();
    private Image selectedImage;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/engineer_guild.fxml"));
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
        for (Image image : getEngineerUnit().keySet()) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);
            imageView.setOnMouseEntered(event -> {
                if (selectedImage == null || !selectedImage.equals(imageView.getImage()))
                    imageView.setOpacity(0.5);
            });

            // Set mouse exited event handler
            imageView.setOnMouseExited(event -> {
                if (selectedImage == null || !selectedImage.equals(imageView.getImage()))
                    imageView.setOpacity(1);
            });
            imageView.setOnMouseClicked(this::clicked);
            Tooltip tooltip = new Tooltip(engineerImage.get(image));
            Tooltip.install(imageView, tooltip);
            unitSelection.getChildren().add(imageView);
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
            BuildingMessage message = BuildingControl.createUnit(engineerImage.get(selectedImage));
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
    private HashMap<Image,String> getEngineerUnit() {
        Image engineerIm = new Image(EngineerGuild.class.getResource("/images/Units/engineer1.png").toExternalForm());
        engineerImage.put(engineerIm,"engineer");
        Image ladderMan = new Image(EngineerGuild.class.getResource("/images/Units/ladderman1.png").toExternalForm());
        engineerImage.put(ladderMan, "ladderman");
        return engineerImage;
    }
}
