package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildingCommands {
    DROP_BUILDING("^\\s*dropbuilding\\s+(-x\\s+(?<x>\\-?\\d+)\\s*()|-t\\s+(?<type>\\w+|\".*\")\\s*()|-y\\s+(?<y>\\-?\\d+)\\s*()){3}\\3\\5\\7$"),
    SELECT_BUILDING("^\\s*select\\s+building\\s+(-x\\s+(?<x>\\-?\\d+)\\s*()|-y\\s+(?<y>\\-?\\d+)\\s*()){2}\\3\\5$"),
    CREATE_UNIT("^\\s*createunit\\s+(-t\\s+(?<type>\\w+|\\\".*\\s+.*\\\")\\s*()|-c\\s+(?<count>\\-?\\d+)\\s*()){2}\\3\\5$"),
    OPEN_CAGE_DOG("^\\s*open\\s+cage\\s+dog\\s+\\-s\\s+(?<open>\\w+)\\s*$"),
    CHANGE_TAX_RATE("^\\s*change\\s+tax\\s+rate\\s+\\-r\\s+(?<rate>\\d+)\\s*$");
    private String regex;

    BuildingCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, BuildingCommands buildingCommands) {
        Matcher matcher = Pattern.compile(buildingCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
