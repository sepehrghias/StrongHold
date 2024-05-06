package model.user;

import model.government.Government;

import java.util.ArrayList;
import java.util.HashMap;

public class copyUser {
    public String username;
    public String nickname;
    public String password;
    public String email;
    public int score;
    public String slogan;
    public Boolean loggedIn;

    private String securityQuestionAnswer;

    public copyUser(String username,  String password, String email, String nickname,String slogan,String securityQuestionAnswer) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.slogan = slogan;
        this.securityQuestionAnswer=securityQuestionAnswer;
        this.loggedIn=false;

    }

    public String getPassword() {
        return password;
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

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }
}
