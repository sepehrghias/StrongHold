package view;

import com.google.gson.Gson;
import control.LoginSignupControl;
import control.ProfileControl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.FileWriter;
import java.io.IOException;
//import java.awt.Rectangle;

public class profileView extends Application {
    public Label profileUsername;
    public Label profilePassword;
    public Label profileNickname;
    public Label profileEmail;
    public static Pane profilePane;

    public static String path;

    public static Stage profileStage;

    public static String newUsername;

    private static Text username = new Text();

    private static Text password = new Text();

    private static Text nickname = new Text();

    private static Text email = new Text();

    private static Text slogan = new Text();
    private static String newPassword;
    private static String newEmail;
    public Label profileSlogan;
    public ImageView myImage;


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(StartGame.class.getResource("/fxml/ProfileView.fxml"));
        profilePane = pane;
        Scene scene = new Scene(pane, 840, 720);
        profileStage = stage;
        stage.setTitle("profile view");
        fillInformation();
        Image image = new Image(StartGame.class.getResource("/images/back2.png").toExternalForm());
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(image), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
        stage.setScene(scene);
        stage.show();
        Button chooseAvatar = new Button("choose avatar");
        chooseAvatar.setLayoutX(30);
        chooseAvatar.setLayoutY(340);
        pane.getChildren().add(chooseAvatar);
        chooseAvatar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ChooseAvatar chooseAvatar1 = new ChooseAvatar();
                try {
                    chooseAvatar1.start(StartGame.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void fillInformation() {
        username.setText(Game.getCurrentUser().getUsername());
        password.setText(Game.getCurrentUser().getPassword());
        nickname.setText(Game.getCurrentUser().getNickname());
        email.setText(Game.getCurrentUser().getEmail());
        if (Game.getCurrentUser().getSlogan() != null) {
            slogan.setText(Game.getCurrentUser().getSlogan());
        } else {
            slogan.setText("Slogan is empty");
            slogan.setFill(Color.RED);
        }
        username.setLayoutX(90);
        username.setLayoutY(45);
        password.setLayoutX(90);
        password.setLayoutY(105);
        nickname.setLayoutX(90);
        nickname.setLayoutY(165);
        email.setLayoutX(90);
        email.setLayoutY(225);
        slogan.setLayoutX(130);
        slogan.setLayoutY(285);

        profilePane.getChildren().addAll(username, password, nickname, email, slogan);
        Label avatar = new Label("Avatar");
        avatar.setLayoutX(30);
        avatar.setLayoutY(550);
        profilePane.getChildren().add(avatar);
    }
    @FXML
    public void initialize(){
        if ((Game.getCurrentUser().getAvatarImageAddress() == null || Game.getCurrentUser().getAvatarImageAddress().equals(""))&&Game.getCurrentUser().getChooseImageAddress()==null) {
            Image IMAGE=new Image(StartGame.class.getResource("/images/Avatar3.png").toExternalForm());
            myImage.setImage(IMAGE);
        }
        else if(Game.getCurrentUser().getChooseImageAddress()!=null){
            Image IMAGE = new Image(Game.getCurrentUser().getChooseImageAddress());
            myImage.setImage(IMAGE);

        }
        else {
            Image IMAGE = new Image(StartGame.class.getResource(Game.getCurrentUser().getAvatarImageAddress()).toExternalForm());
            myImage.setImage(IMAGE);
        }
    }

    public void changeUsername(MouseEvent mouseEvent) {
        TextField textField = new TextField();
        textField.setPromptText("change Username");
        textField.setLayoutX(330);
        textField.setLayoutY(30);
        profilePane.getChildren().add(textField);
        Label invalidUsername = new Label("invalid username or username already exists");
        invalidUsername.setLayoutX(360);
        invalidUsername.setLayoutY(30);
        invalidUsername.setTextFill(Color.RED);
        Button submit = new Button("Submit");
        submit.setLayoutX(500);
        submit.setLayoutY(30);
        profilePane.getChildren().add(submit);
        textField.textProperty().addListener((observable, oldText, newText) -> {
            if (!newText.equals("")) {
                if (LoginSignupMenu.checkUsername(newText).equals("invalid format") || LoginSignupMenu.checkUsername(newText).equals("sameUsername")) {
                    if (profilePane.getChildren().contains(invalidUsername)) {


                    } else {
                        profilePane.getChildren().add(invalidUsername);
                    }
                } else if (LoginSignupMenu.checkUsername(newText).equals("success")) {
                    if (profilePane.getChildren().contains(invalidUsername)) {
                        profilePane.getChildren().remove(invalidUsername);
                    }
                    newUsername = newText;
                }
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (profilePane.getChildren().contains(invalidUsername) || newUsername.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("error! new username is not correct");
                    alert.showAndWait();

                } else {
                    ProfileControl.changeUsername(newUsername);
                    username.setText(newUsername);
                    username.setFill(Color.BLACK);
                    profilePane.getChildren().remove(submit);
                    profilePane.getChildren().remove(textField);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("username changed Successfully");
                    alert.showAndWait();

                }
            }
        });
    }

    public void changePassword(MouseEvent mouseEvent) {
        TextField textField = new TextField();
        textField.setPromptText("change Password");
        textField.setLayoutX(330);
        textField.setLayoutY(90);
        profilePane.getChildren().add(textField);
        Label invalidPassword = new Label("password is not strong");
        invalidPassword.setLayoutX(450);
        invalidPassword.setLayoutY(90);
        invalidPassword.setTextFill(Color.RED);
        Button submit = new Button("Submit");
        TextField captchatextField = new TextField();
        captchatextField.setLayoutX(450);
        captchatextField.setLayoutY(150);
        profilePane.getChildren().add(captchatextField);
        submit.setLayoutX(750);
        submit.setLayoutY(90);
        Rectangle captchaRectangle = new Rectangle(150, 40);
        int random;
        random = LoginSignupControl.makeRandomCaptcha();
        path = String.valueOf(random);
        captchaRectangle.setFill(new ImagePattern(new Image(StartGame.class.getResource("/images/captcha/" + path + ".png").toExternalForm())));
        captchaRectangle.setLayoutY(150);
        captchaRectangle.setLayoutX(500);
        profilePane.getChildren().add(captchaRectangle);
        profilePane.getChildren().add(submit);
        textField.textProperty().addListener((observable, oldText, newText) -> {
            if (!newText.equals("")) {
                if (LoginSignupMenu.validPassword(newText).equals("upper case") ||
                        LoginSignupMenu.validPassword(newText).equals("lower case") ||
                        LoginSignupMenu.validPassword(newText).equals("number") ||
                        LoginSignupMenu.validPassword(newText).equals("length") ||
                        LoginSignupMenu.validPassword(newText).equals("special character")) {
                    if (profilePane.getChildren().contains(invalidPassword)) {


                    } else {
                        profilePane.getChildren().add(invalidPassword);
                    }
                } else if (LoginSignupMenu.validPassword(newText).equals("success")) {
                    if (profilePane.getChildren().contains(invalidPassword)) {
                        profilePane.getChildren().remove(invalidPassword);
                    }

                    newPassword = newText;
                }
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (profilePane.getChildren().contains(invalidPassword) || newPassword.equals("") || newPassword == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("error! new password is not strong");
                    alert.showAndWait();

                } else if (captchatextField.getText().equals(path)) {
                    ProfileControl.changePassword(newPassword);
                    password.setText(newPassword);
                    profilePane.getChildren().remove(textField);
                    profilePane.getChildren().remove(captchatextField);
                    profilePane.getChildren().remove(captchaRectangle);
                    profilePane.getChildren().remove(submit);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("password changed Successfully");
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("captcha is invalid");
                    alert.showAndWait();
                    profileView profileView = new profileView();
                    try {
                        profileView.start(StartGame.stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void changeNickname(MouseEvent mouseEvent) {
        TextField textField = new TextField();
        textField.setPromptText("change nickname");
        textField.setLayoutX(330);
        textField.setLayoutY(150);
        profilePane.getChildren().add(textField);
        Button submit = new Button("Submit");
        submit.setLayoutX(465);
        submit.setLayoutY(150);
        profilePane.getChildren().add(submit);
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField.getText().equals("") || textField == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please complete your new nickname");
                    alert.showAndWait();
                } else {
                    nickname.setText(textField.getText());
                    ProfileControl.changeNickname(textField.getText());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("your nickname changed successfully");
                    alert.showAndWait();
                    profilePane.getChildren().remove(textField);
                    profilePane.getChildren().remove(submit);
                }
            }
        });
    }

    public void changeEmail(MouseEvent mouseEvent) {
        TextField textField = new TextField();
        textField.setPromptText("change Email address");
        textField.setLayoutX(330);
        textField.setLayoutY(210);
        profilePane.getChildren().add(textField);
        Label invalidEmail = new Label("email format is invalid or this email already exists");
        invalidEmail.setLayoutX(360);
        invalidEmail.setLayoutY(210);
        invalidEmail.setTextFill(Color.RED);
        Button submit = new Button("Submit");
        submit.setLayoutX(500);
        submit.setLayoutY(210);
        profilePane.getChildren().add(submit);
        textField.textProperty().addListener((observable, oldText, newText) -> {
            if (!newText.equals("")) {
                if (LoginSignupMenu.validEmail(newText).equals("invalid format") || LoginSignupMenu.validEmail(newText).equals("exists")) {
                    if (profilePane.getChildren().contains(invalidEmail)) {


                    } else {
                        profilePane.getChildren().add(invalidEmail);
                    }
                } else if (LoginSignupMenu.validEmail(newText).equals("success")) {
                    if (profilePane.getChildren().contains(invalidEmail)) {
                        profilePane.getChildren().remove(invalidEmail);
                    }
                    newEmail = newText;
                }
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (profilePane.getChildren().contains(invalidEmail) || newEmail.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("error! new email is not correct");
                    alert.showAndWait();

                } else {
                    ProfileControl.changeEmail(newEmail);
                    email.setText(newEmail);
                    profilePane.getChildren().remove(submit);
                    profilePane.getChildren().remove(textField);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("email changed Successfully");
                    alert.showAndWait();

                }
            }
        });
    }

    public void changeSlogan(MouseEvent mouseEvent) {
        TextField textField = new TextField();
        textField.setPromptText("change slogan");
        textField.setLayoutX(330);
        textField.setLayoutY(270);
        profilePane.getChildren().add(textField);
        Button submit = new Button("Submit");
        submit.setLayoutX(465);
        submit.setLayoutY(270);
        profilePane.getChildren().add(submit);
        Button removeSlogan = new Button("remove Slogan");
        removeSlogan.setLayoutX(550);
        removeSlogan.setLayoutY(270);
        profilePane.getChildren().add(removeSlogan);
        removeSlogan.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ProfileControl.removeSlogan();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("slogan removed successfully");
                alert.showAndWait();
                slogan.setText("Slogan is empty");
                slogan.setFill(Color.RED);
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (textField.getText().equals("") || textField == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please complete your new slogan");
                    alert.showAndWait();
                } else {
                    slogan.setText(textField.getText());
                    ProfileControl.changeSlogan(textField.getText());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("your slogan changed successfully");
                    alert.showAndWait();
                    profilePane.getChildren().remove(textField);
                    profilePane.getChildren().remove(submit);
                    profilePane.getChildren().remove(removeSlogan);
                }
            }
        });
    }

    public void goScoreBoard(MouseEvent mouseEvent) throws Exception {
        scoreBoardView scoreBoardView1 = new scoreBoardView();
        scoreBoardView1.run();
    }

    public void chooseFromFile(MouseEvent mouseEvent) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == jfc.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            String pathFile = file.getPath();
            Game.getCurrentUser().setChooseImageAddress(pathFile);
            for (adam adam : adam.adams) {
                if (adam.username.equals(Game.getCurrentUser().getUsername())) {
                    adam.avatarImageAddress = null;
                    adam.chooseImageAddress=pathFile;
                }

            }
            try {
                FileWriter fileWriter=new FileWriter("users.json");
                fileWriter.write(new Gson().toJson(view.adam.adams));
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Game.getCurrentUser().setAvatarImageAddress(null);
            Image image = new Image((Game.getCurrentUser().getChooseImageAddress()));
            myImage.setImage(image);
        }
    }

    public void onDragCheck(DragEvent dragEvent) {
        Dragboard dragboard=dragEvent.getDragboard();
        if(dragboard.hasFiles()&&checkFormatOfFile(dragboard.getFiles().get(0)))
            dragEvent.acceptTransferModes(TransferMode.COPY);
        dragEvent.consume();
    }

    public void changeImageWithDrag(DragEvent dragEvent) {
        Dragboard dragboard=dragEvent.getDragboard();
        if(dragboard.hasFiles()){
            for (File file:dragboard.getFiles()){
                String absolutePath=file.getAbsolutePath();
                Game.getCurrentUser().setChooseImageAddress(absolutePath);
                Image dragImage=new Image(absolutePath);
                Game.getCurrentUser().setChooseImageAddress(absolutePath);
                File selectedImageFromGallery = new File(absolutePath);
                myImage.setImage(dragImage);
            }
            dragEvent.setDropCompleted(true);

        }
        else {
            dragEvent.setDropCompleted(false);
        }

    }
    private boolean checkFormatOfFile(File file){
        String fileName=file.getName();
        fileName=fileName.substring(fileName.lastIndexOf('.')+1);
        return fileName.equals("png")||fileName.equals("jpg");
    }

    public void backToMainManu(MouseEvent mouseEvent) throws Exception {
        if(Game.getCurrentUser().getChooseImageAddress()!=null) {
            for (adam adam : adam.adams) {
                if (adam.username.equals(Game.getCurrentUser().getUsername())) {
                    adam.avatarImageAddress = null;
                    adam.chooseImageAddress = Game.getCurrentUser().getChooseImageAddress();
                }

            }
            try {
                FileWriter fileWriter = new FileWriter("users.json");
                fileWriter.write(new Gson().toJson(view.adam.adams));
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        MainView mainView=new MainView();
        mainView.start(StartGame.stage);
    }
}
