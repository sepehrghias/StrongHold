package view;

import control.LoginSignupControl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class sloganView extends Application {

    public static Pane sloganPane;

    public static Stage sloganStage;
    public TextField sloganText;

    public static String slogan;

    @Override
    public void start(Stage stage) throws Exception {
        sloganStage = stage;
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/Slogan.fxml"));
        sloganPane = pane;
        Scene scene = new Scene(pane);
        sloganStage.setScene(scene);
        sloganStage.setTitle("Slogan   :   ");
        Image image = new Image(StartGame.class.getResource("/images/M1HMsV.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Label sloganLabel = new Label("Slogan");
        sloganLabel.setLayoutY(110);
        sloganLabel.setLayoutX(50);
        sloganLabel.setTextFill(Color.WHITE);
        pane.getChildren().add(sloganLabel);
        CheckBox randomSlogan = new CheckBox("Random slogan");
        randomSlogan.setLayoutX(270);
        randomSlogan.setLayoutY(107);
        pane.getChildren().add(randomSlogan);
        commonSlogans();
        randomSlogan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                makeRandomSlogan();
            }
        });

    }
    @FXML
    public void initialize(){
    }

    private void commonSlogans() {
        ArrayList<CheckBox> slogansCheckBox = new ArrayList<>();
        ArrayList<String> commonSlogans = new ArrayList<>();
        LoginSignupControl.randomSlogan(commonSlogans);
        Label commonSlogansLabel = new Label("Common Slogans in the game are:");
        commonSlogansLabel.setLayoutX(50);
        commonSlogansLabel.setLayoutY(190);
        commonSlogansLabel.setFont(Font.font(16));
        sloganPane.getChildren().add(commonSlogansLabel);
        for (int i = 0; i < commonSlogans.size(); i++) {
            Text text = new Text(commonSlogans.get(i));
            text.setLayoutX(80);
            text.setLayoutY(240 + 30 * i);
            CheckBox checkBox = new CheckBox("choose");
            checkBox.setLayoutX(0);
            checkBox.setLayoutY(232 + 30 * i);
            slogansCheckBox.add(checkBox);
            sloganPane.getChildren().addAll(text, checkBox);
        }
        for (CheckBox checkBox : slogansCheckBox) {
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (checkBox.isSelected()) {
                        slogan = commonSlogans.get(slogansCheckBox.indexOf(checkBox));

                    }
                }
            });

        }
    }

    private static void makeRandomSlogan() {
        slogan = LoginSignupMenu.checkSlogan();
    }

    public void submitSlogan(MouseEvent mouseEvent) throws IOException {
        if(slogan==null&&sloganText.getText()==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("slogan is empty");
            alert.showAndWait();
        }
        else {
            if(!sloganText.getText().equals("")) {
                slogan = sloganText.getText();
            }
            signupview signupview=new signupview();
            signupview.start(StartGame.stage);
        }
    }
}
