package view;

import control.LoginSignupControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class securityView extends Application {
    public static Stage securityStage;

    public static Pane securityPane;
    public TextField captchaAnswer;

    public static String path;
    public Text thirdQ;
    public Text secondQ;
    public Text firstQ;

    public static TextField first;

    public static TextField second;

    public static TextField third;

    @Override
    public void start(Stage stage) throws Exception {
        securityStage = stage;
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/Security.fxml"));
        securityPane = pane;
        Scene scene = new Scene(pane);
        securityStage.setScene(scene);
        Image image = new Image(StartGame.class.getResource("/images/16.jpg").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        Rectangle rectangle = new Rectangle(150, 40);
        int random;
        random = LoginSignupControl.makeRandomCaptcha();
        path = String.valueOf(random);
        rectangle.setFill(new ImagePattern(new Image(StartGame.class.getResource("/images/captcha/" + path + ".png").toExternalForm())));
        rectangle.setLayoutY(500);
        rectangle.setLayoutX(200);
        pane.getChildren().add(rectangle);
        Image refresh = new Image(signupview.class.getResource("/images/refresh.png").toExternalForm());
        ImageView refreshView = new ImageView(refresh);
        refreshView.setFitHeight(26);
        refreshView.setFitWidth(26);
        refreshView.setLayoutX(370);
        refreshView.setLayoutY(500);
        pane.getChildren().add(refreshView);
        refreshView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int ran = LoginSignupControl.makeRandomCaptcha();
                String path = String.valueOf(ran);
                rectangle.setFill(new ImagePattern(new Image(StartGame.class.getResource("/images/captcha/" + path + ".png").toExternalForm())));

            }
        });

    }


    public void first(MouseEvent mouseEvent) {
        first = new TextField();
        first.setPromptText("your father's name");
        first.setLayoutX(240);
        first.setLayoutY(142);
        securityPane.getChildren().add(first);
    }

    public void second(MouseEvent mouseEvent) {
        second = new TextField();
        second.setPromptText("your pet's name");
        second.setLayoutX(240);
        second.setLayoutY(242);
        securityPane.getChildren().add(second);
    }

    public void third(MouseEvent mouseEvent) {
        third = new TextField();
        third.setPromptText("your mother's  last name");
        third.setLayoutX(240);
        third.setLayoutY(342);
        securityPane.getChildren().add(third);
    }

    public void submit(MouseEvent mouseEvent) throws Exception {
        if (captchaAnswer.getText().equals("") || (first == null && second == null && third == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("captcha or security answer is incomplete");
            alert.showAndWait();

        } else {
            if (!captchaAnswer.getText().equals(path)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("captcha is invalid");
                alert.showAndWait();
                securityView securityView = new securityView();
                securityView.start(StartGame.stage);
            } else {
                String answer = new String();
                if (first != null) {
                    if (!first.getText().equals("")) {
                        answer = first.getText();
                    }
                }
                if(second!=null) {
                    if (!second.getText().equals("")) {
                        answer = second.getText();

                    }
                }
                if(third!=null) {
                 if (!third.getText().equals("")) {
                        answer = third.getText();

                    }
                }
                LoginSignupControl.createUser(LoginSignupMenu.username, LoginSignupMenu.password, LoginSignupMenu.emailAddress, LoginSignupMenu.nickname, LoginSignupMenu.slogan, answer);
                StartGame startGame = new StartGame();
                startGame.start(StartGame.stage);
            }
        }
    }
}
