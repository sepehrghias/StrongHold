package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import model.Game;

import java.util.ArrayList;

public class ChooseAvatar extends Application {
    public static Pane chooseAvatar;

    public static Stage chooseAvatarStage;

    public static ArrayList<Button> chooseButtons=new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/chooseAvatar.fxml"));
        chooseAvatar = pane;
        Scene scene = new Scene(pane, 840, 720);
        chooseAvatarStage = stage;
        stage.setTitle("Choose avatar");
        stage.setScene(scene);
        stage.show();
        choose();
    }

    private void choose() {
        for (int i=0;i<15;i++){
            javafx.scene.shape.Rectangle rectangle=new javafx.scene.shape.Rectangle(70,70);
            rectangle.setFill(new ImagePattern(new Image(StartGame.class.getResource("/images/Avatar"+(i+1)+".png").toExternalForm())));
            if(i<=7) {
                rectangle.setLayoutX(140);
                rectangle.setLayoutY(80*i);
            }
            else {
                rectangle.setLayoutX(280);
                rectangle.setLayoutY(80*(i-7));

            }
            chooseAvatar.getChildren().add(rectangle);
        }
        for (int i=0;i<15;i++){
            Button button=new Button("choose");
            if(i<=7) {
                button.setLayoutX(110);
                button.setLayoutY(80*i);
            }
            else {
                button.setLayoutX(250);
                button.setLayoutY(80*(i-7));
            }
            chooseAvatar.getChildren().add(button);
            chooseButtons.add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    int addres=(chooseButtons.indexOf(button)+1);
                    String Address="/images/Avatar"+addres+".png";
                    Game.getCurrentUser().setAvatarImageAddress(Address);
                    Game.getCurrentUser().setChooseImageAddress(null);
                }
            });
        }
    }

    public void backProfile(MouseEvent mouseEvent) throws Exception {
        profileView profileView=new profileView();
        profileView.start(StartGame.stage);
    }
}
