package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnvironmentMenuCommands {
    SET_TEXTURE("^settexture\\s+(-x\\s+(?<x>\\-?\\d+)\\s*()|-y\\s+(?<y>\\-?\\d+)\\s*()|-t\\s+(?<type>\\w+|\".*\")\\s*()){3}\\3\\5\\7$"),
    SET_TEXTURE_RECTANGLE("^settexture\\s+(-x1\\s+(?<x>\\-?\\d+)\\s*()|-y1\\s+(?<y>\\-?\\d+)\\s*()|-t\\s+(?<type>\\w+|\".*\")\\s*()|"+
            "-y2\\s+(?<y2>\\-?\\d+)\\s*()|-x2\\s+(?<x2>\\-?\\d+)\\s*()){5}\\3\\5\\7\\9$"),
    CLEAR("^clear\\s+(\\-x\\s+(?<x>\\-?\\d+)\\s*()|\\-y\\s+(?<y>\\-?\\d+)\\s*()){2}\\3\\5$"),
    DROP_ROCK("^droprock\\s+(\\-x\\s+(?<x>\\-?\\d+)\\s*()|\\-y\\s+(?<y>\\-?\\d+)\\s*()|\\-d\\s+(?<direction>\\w+)\\s*()){3}\\3\\5\\7$"),
    DROP_TREE("^droptree\\s+(\\-x\\s+(?<x>\\-?\\d+)\\s*()|\\-y\\s+(?<y>\\-?\\d+)\\s*()|\\-t\\s+(?<type>\\w+|\".*\")\\s*()){3}\\3\\5\\7$"),
    DROP_BUILDING("^dropbuilding\\s+(\\-x\\s+(?<x>\\-?\\d+)\\s*()|\\-y\\s+(?<y>\\-?\\d+)\\s*()|\\-t\\s+(?<type>\\w+|\".*\")\\s*()){3}\\3\\5\\7$"),
    DROP_UNIT("^dropunit\\s+(\\-x\\s+(?<x>\\-?\\d+)\\s*()|\\-y\\s+(?<y>\\-?\\d+)\\s*()|\\-c\\s+(?<count>\\-?\\d+)\\s*()|\\-t\\s+(?<type>\\w+|\".*\")\\s*()){4}\\3\\5\\7\\9$");
    private String regex;

    EnvironmentMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, EnvironmentMenuCommands environmentMenuCommands) {
        Matcher matcher = Pattern.compile(environmentMenuCommands.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
