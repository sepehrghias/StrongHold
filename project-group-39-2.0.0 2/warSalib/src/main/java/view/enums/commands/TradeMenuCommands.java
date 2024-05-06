package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
    TRADE( "^\\s*trade\\s+(\\-t\\s+(?<resourceType>\\S+)\\s*()|\\-a\\s+(?<resourceAmount>\\d+)\\s*()|" +
            "\\-p\\s+(?<price>\\d+)\\s*()|\\-m\\s+(?<message>.+)\\s*()){4}\\3\\5\\7\\9$"),
    SHOW_TRADE_LIST("^\\s*trade\\s+list\\s*$"),
    ACCEPT_TRADE("^\\s*trade\\s+(\\-i\\s+(?<id>\\S+)\\s*()|\\-m\\s+(?<message>.+)\\s*()){2}\\3\\5$"),
    SHOW_TRADE_HISTORY("^\\s*trade\\s+history\\s*");

    private String regex;

    TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, TradeMenuCommands tradeMenuCommands) {
        Matcher matcher = Pattern.compile(tradeMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
