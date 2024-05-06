package view;

import control.ProfileControl;
import model.user.User;
import view.enums.commands.ProfileMenuCommands;
import view.enums.messages.ProfileMenuMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private User currentUser;
    public static int run() {
        String input;
        Scanner scanner = Scan.getScanner();
        while (true) {
            input = scanner.nextLine();
            Matcher matcher;
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_USERNAME)) != null)
                changeUsername(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_NICKNAME)) != null)
                changeNickname(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD)) != null)
                changePassword(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_EMAIL)) != null)
                changeEmail(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_SLOGAN)) != null)
                changeSlogan(matcher);
            else if (input.matches("^\\s*profile\\s+remove\\s+slogan\\s*$"))
                removeSlogan();
            else if (input.matches("^\\s*profile\\s+display\\s+highscore\\s*$"))
                displayHighScore();
            else if (input.matches("^\\s*profile\\s+display\\s+rank\\s*$"))
                displayRank();
            else if (input.matches("^\\s*profile\\s+display\\s+slogan\\s*$"))
                displaySlogan();
            else if ((input.matches("^\\s*profile\\s+display\\s*$")))
                displayProfile();
            else if (input.matches("^\\s*back\\s*$"))
                return 0;
            else if (input.matches("^\\s*logout\\s*$"))
                return 1;
            else System.out.println("invalid command!");
        }

    }

    private static void changeUsername(Matcher matcher) {
        String username = matcher.group("username");
        ProfileMenuMessage message = ProfileControl.changeUsername(username);
        switch (message) {
            case INVALID_USERNAME_FORMAT:
                System.out.println("invalid username format");
                break;
            case USERNAME_EXISTS:
                System.out.println("username already exists");
                break;
            case SUCCESS:
                System.out.println("username changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }

    private static void changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");
        ProfileMenuMessage message = ProfileControl.changeNickname(nickname);
        switch (message) {
            case INVALID_NICKNAME_FORMAT:
                System.out.println("invalid nickname format");
                break;
            case SUCCESS:
                System.out.println("nickname changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }

    private static void changePassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        ProfileMenuMessage message = ProfileControl.changePassword(oldPassword);
        switch (message) {
            case WRONG_PASSWORD:
                System.out.println("Current password is incorrect");
                break;
            case SAME_PASSWORD:
                System.out.println("Please enter a new password!");
                break;
            case INVALID_PASSWORD_FORMAT:
                System.out.println("invalid password format");
                break;
            case WEAK_PASSWORD:
                System.out.println("password is weak");
                break;
            case SUCCESS:
                System.out.println("password changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }
    private static void changeEmail(Matcher matcher) {
        String email = matcher.group("email");
        ProfileMenuMessage message = ProfileControl.changeEmail(email);
        switch (message) {
            case EMAIL_EXISTS:
                System.out.println("email already exists");
                break;
            case INVALID_EMAIL_FORMAT:
                System.out.println("invalid email format");
                break;
            case SUCCESS:
                System.out.println("email changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }
    private static void changeSlogan(Matcher matcher) {
        String slogan = matcher.group("slogan");
        ProfileMenuMessage message = ProfileControl.changeSlogan(slogan);
        switch (message) {
            case EMPTY_SLOGAN:
                System.out.println("please set down a slogan");
                break;
            case SUCCESS:
                System.out.println("slogan changed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }
    private static void removeSlogan() {
        ProfileMenuMessage message = ProfileControl.removeSlogan();
        switch (message) {
            case EMPTY_SLOGAN:
                System.out.println("you don't have a slogan");
                break;
            case SUCCESS:
                System.out.println("your slogan has been removed successfully");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }

    }
    private static void displayHighScore() {
        System.out.println(ProfileControl.displayHighScore());
    }
    private static void displayRank() {
        System.out.println(ProfileControl.displayRank());
    }
    private static void displaySlogan() {
        System.out.println(ProfileControl.displaySlogan());
    }
    private static void displayProfile() {
        System.out.println(ProfileControl.displayProfile());
    }

    private static void startGame(Matcher matcher) {

    }
}
