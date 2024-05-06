package view.enums.commands;

import view.enums.messages.ProfileMenuMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    CHANGE_USERNAME("^\\s*profile\\s+change\\s+\\-u\\s+(?<username>\\S+)\\s*$"),
    CHANGE_NICKNAME("^\\s*profile\\s+change\\s+\\-n\\s+(?<nickname>\\S+)\\s*$"),
    CHANGE_PASSWORD("^\\s*profile\\s+change\\s+password\\s+(\\-o\\s+(?<oldPassword>\\S+)\\s+()|\\-n\\s+(?<newPassword>\\S+)\\s*()){2}\\3\\5$" ),
    CHANGE_EMAIL("^\\s*profile\\s+change\\s+\\-e\\s+(?<email>\\S+)\\s*$"),
    CHANGE_SLOGAN("^\\s*profile\\s+change\\s+slogan\\s+\\-s\\s+(?<slogan>.+)\\s*$"),
    REMOVE_SLOGAN("^\\s*profile\\s+remove\\s+slogan\\s*$"),
    DISPLAY_HIGHSCORE("^\\s*profile\\s+display\\s+highscore\\s*$"),
    DISPLAY_RANK("^\\s*profile\\s+display\\s+rank\\s*$"),
    DISPLAY_SLOGAN("^\\s*profile\\s+display\\s+slogan\\s*$"),
    DISPLAY_PROFILE("^\\s*profile\\s+display\\s*$"),
    START_GAME("^\\s*start\\s+game\\s*$");

    private String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, ProfileMenuCommands profileMenuCommands) {
        Matcher matcher = Pattern.compile(profileMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
