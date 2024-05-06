package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuCommands {
    BUY("^buy\\s+(\\-i\\s+(?<item>\\w+|\".*\")\\s*()|\\-a\\s+(?<amount>\\-?\\d+)\\s*()){2}\\3\\5$"),
    SELL("^sell\\s+(\\-i\\s+(?<item>\\w+|\".*\")\\s*()|\\-a\\s+(?<amount>\\-?\\d+)\\s*()){2}\\3\\5$");

    private String regex;

    StoreMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, StoreMenuCommands storeMenuCommands) {
        Matcher matcher = Pattern.compile(storeMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
