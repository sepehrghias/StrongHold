package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    PROFILE_MENU("^\\s*enter\\s+profile\\s+menu\\s*$"),
    START_GAME("^\\s*start\\s+game\\s+(?<turn>\\d+)\\s+turn$"),

    ADD_PLAYER("^\\s*add\\s+player\\s+-u\\s+(?<username>.+)");

    private String regex;

    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, MainMenuCommands mainMenuCommands) {
        Matcher matcher = Pattern.compile(mainMenuCommands.regex).matcher(command);
        if (matcher.find())
            return matcher;
        return null;
    }
}
