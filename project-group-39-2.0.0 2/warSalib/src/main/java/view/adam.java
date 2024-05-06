package view;

import java.util.ArrayList;

public class adam {
    public String username;
    public String nickname;
    public String password;
    public String email;
    public String avatarImageAddress;

    public String sendDonates;

    public String getDonates;

    public String getRequests;

    public String sendRequests;

    public String chooseImageAddress;
    public int score;
    public String slogan;
    public Boolean loggedIn;

    public String securityQuestionAnswer;

    public static ArrayList<adam> adams=new ArrayList<>();

    public adam(String username,  String password, String email, String nickname,String slogan,String securityQuestionAnswer) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.slogan = slogan;
        this.loggedIn = loggedIn;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public static void setAdams(ArrayList<adam> adams) {
        adam.adams = adams;
    }

    public void setAvatarImageAddress(String avatarImageAddress) {
        this.avatarImageAddress = avatarImageAddress;
    }

    public String getAvatarImageAddress() {
        return avatarImageAddress;
    }
}
