package control;

import com.google.gson.Gson;
import model.Game;
import model.user.User;
import view.GameMenu;
import view.LoginSignupMenu;
import view.adam;
import view.enums.messages.LoginMenuMessage;
import view.enums.messages.ProfileMenuMessage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ProfileControl {
    public static ProfileMenuMessage changeUsername(String username) {
        for (User player : Game.getPlayers())
            if (player.getUsername().equals(username))
                return ProfileMenuMessage.USERNAME_EXISTS;

        if (LoginSignupControl.checkUsername(username).equals(LoginMenuMessage.INVALIDUSERNAME))
            return ProfileMenuMessage.INVALID_USERNAME_FORMAT;
        for (adam adam:adam.adams){
            if(adam.username.equals(Game.getCurrentUser().getUsername())){
                adam.username=username;
            }

        }
        Game.getCurrentUser().setUsername(username);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(adam.adams));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeNickname(String nickname) {
        for (adam adam:adam.adams){
            if(adam.username.equals(Game.getCurrentUser().getUsername())){
                adam.nickname=nickname;
            }

        }
        Game.getCurrentUser().setNickname(nickname);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(adam.adams));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Game.getCurrentUser().setNickname(nickname);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePassword(String newPassword) {
        for (adam adam:adam.adams){
            if(adam.username.equals(Game.getCurrentUser().getUsername())){
                adam.password=newPassword;
            }
        }
        Game.getCurrentUser().setPassword(newPassword);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(adam.adams));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeEmail(String email) {
        for (adam adam:adam.adams){
            if(adam.username.equals(Game.getCurrentUser().getUsername())){
                adam.email=email;
            }
        }

        Game.getCurrentUser().setEmail(email);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(adam.adams));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeSlogan(String slogan) {
        if (slogan.equals(""))
            return ProfileMenuMessage.EMPTY_SLOGAN;
        for (adam adam:adam.adams){
            if(adam.username.equals(Game.getCurrentUser().getUsername())){
                adam.slogan=slogan;
            }
        }
        Game.getCurrentUser().setSlogan(slogan);
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(adam.adams));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage removeSlogan() {
        for (adam adam:adam.adams){
            if(adam.username.equals(Game.getCurrentUser().getUsername())){
                adam.slogan="";
            }
        }

        Game.getCurrentUser().setSlogan("");
        try {
            FileWriter fileWriter=new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(adam.adams));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static String displayHighScore() {
        return String.valueOf(Game.getCurrentUser().getScore()); //need to check this function
    }

    public static String displayRank() {
        return String.valueOf(Game.getCurrentUser().getRank());
    }

    public static String displaySlogan() {
        if (Game.getCurrentUser().getSlogan().equals(""))
            return "Slogan is empty!";
        return Game.getCurrentUser().getSlogan();
    }

    public static String displayProfile() {
        StringBuilder result = new StringBuilder();
        User user = Game.getCurrentUser();
        result.append("username: ")
                .append(user.getUsername());

        if (!user.getSlogan().equals(""))
            result.append("\nslogan: ")
                    .append(user.getSlogan());

        result.append("\nHighScore: ")
                .append(user.getScore())
                .append("\nrank: ")
                .append(user.getRank());

        return result.toString();
    }

}
