package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GovernmentMenuCommands {
    SHOW_POPULARITY_FACTORS("^\\s*show\\s+popularity\\s+factors\\s*$"),
    SHOW_POPULARITY("^\\s*show\\s+popularity\\s*$"),

    ADD_FOOD("^add\\s+food\\s+(\\-n\\s+(?<foodName>[\\S]+)\\s*()|\\-a\\s+(<amount>[\\d]+)\\s*())\\s*{2}\\3\\5$"),
    SHOW_FOOD_LIST("^\\s*show\\s+food\\s+list\\s*$"),
    CHANGE_FOOD_RATE("^\\s*food\\s+rate\\s+\\-r\\s+(?<rate>(\\-)?\\d+)\\s*$"),
    SHOW_FOOD_RATE("^\\s*food\\s+rate\\s+show\\s*$"),
    CHANGE_TAX_RATE("^\\s*tax\\s+rate\\s+\\-r\\s+(?<rate>(\\-)?\\d+)\\s*$"),
    SHOW_TAX_RATE("^\\s*tax\\s+rate\\s+show\\s*$"),
    CHANGE_FEAR_RATE("^\\s*fear\\s+rate\\s+\\-r\\s+(?<rate>(\\-)?\\d+)\\s*$");

    private String regex;

    GovernmentMenuCommands(String regex) { this.regex = regex; }

    public static Matcher getMatcher(String command, GovernmentMenuCommands governmentMenuCommands) {
        Matcher matcher = Pattern.compile(governmentMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
