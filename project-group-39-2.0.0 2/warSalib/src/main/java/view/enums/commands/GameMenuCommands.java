package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SELECTUNIT("select\\s+unit\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()|\\-t\\s+(?<type>[\\S]+)()){3}\\3\\5\\7$"),

    MOVEUNIT("move\\s+unit\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}\\3\\5$"),

    PATROLUNIT("patrol\\s+unit\\s+(\\-x1\\s+(?<x1>[\\d]+)\\s*()|\\-y1\\s+(?<y1>[\\d]+)\\s*()|\\-x2\\s+(?<x2>[\\d]+)\\s*()|\\-y2\\s+(?<y2>[\\d]+)\\s*()){4}\\3\\5\\7\\9$"),

    STOP_PATROL_UNIT("stop\\s+patrol\\s+unit(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s*"),

    SETMOODE("set\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()|\\-s\\s+(?<moode>[\\S]+)\\s*()){3}\\3\\5\\7$"),

    ATTACK("attack\\s+-e\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)()){2}\\3\\5$"),

    AIRATTACK("attack\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}{2}\\3\\5$"),

    POUROIL("pour\\s+oil\\s+-d\\s+(?<direction>[\\S]+)\\s*"),

    DIGTUNNEL("dig\\s+tunnel\\s+(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}\\3\\5$"),

    BUILDEQUIPMENT("build\\s+-q\\s+(?<equipment>.+)"),

    BUILD_GATE("build\\s+-q\\s+(-x\\s+(?<x>[\\d]+)\\s+(-y\\s+(?<y>[\\d]+)\\s+(?<direction>[\\S]+)\\s+(?<gateName>.+)"),
    DISBANDUNIT("disband\\s+unit"),

    MAKE_GATE("make\\s+gate\\s+-x\\s+(?<x>[\\d]+)\\s+-y\\s+(?<y>[\\d]+)\\s+(?<direction>[\\S]+)\\s+(?<gateName>.+)"),

    MAKE_WALL("build\\s+\\s+-q\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s+(?<wallType>[\\S]+\\s+wall)"),

    MAKE_STAIR("build\\s+-q\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s+stair"),

   MAKE_TOWER("build\\s+-q\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s+(?<type>[\\S]+\\s+tower)"),

    MAKETALE("make\\s+tale(\\-x\\s+(?<x>[\\d]+)\\s*()|\\-y\\s+(?<y>[\\d]+)\\s*()){2}\\3\\5$"),

    MAKE_KILLER_TALE("build\\s+-q\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s+killer\\stale"),

    MAKE_OIL_TALE("build\\s+-q\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s+oil\\stale"),

    CAPTURE_GATE("capture\\s+gate\\s+-x\\s+(?<x>[\\d]+)\\s+-y\\s+(?<y>[\\d]+)"),

    MAKE_PROTECTION("make\\s+protection\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s+(<unitsName>.+)\\s*"),

    MAKE_BATTERING_RAM("make\\s+battering\\s+ram(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s*"),

    MAKE_SIEGE_TOWER("make\\s+siege\\s+tower\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s*"),

    MAKE_CATAPULT("make\\s+catapult\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s*"),


    UNSELECT_UNIT("unselect\\s+units"),

    MAKE_FIXED_CATAPULT("make\\s+fixed\\s+catapult\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s*"),

    MAKE_FIERY_STONE_THROWER("make\\s+firey\\s+stone\\s+thrower\\s+(?<x>[\\d]+)\\s+(?<y>[\\d]+)\\s*"),

    MOVE_BATTERING("move\\s+battering\\s+in\\s+-x\\s+(?<x1>[\\d]+)\\s+\\s+-y\\s+(?<y1>[\\d]+)\\s+to\\s+-x\\s+(?<x2>[\\d]+)\\s+-y\\s+(?<y2>[\\d]+)\\s*"),

    USE_BATTERING_TO_ATTACK("attack\\s+with\\s+battering\\s+-x\\s+(?<x>[\\d]+)\\s+-y\\s+(?<y>[\\d]+)\\s*"),

    MOVE_CATAPULT("move\\s+catapult\\s+in\\s+-x\\s+(?<x1>[\\d]+)\\s+\\s+-y\\s+(?<y1>[\\d]+)\\s+to\\s+-x\\s+(?<x2>[\\d]+)\\s+-y\\s+(?<y2>[\\d]+)\\s*"),

    USE_CATAPULT_TO_ATTACK("attack\\s+with\\s+catapult\\s+-x1\\s+(?<x1>[\\d]+)\\s+-y\\s+(?<y1>[\\d]+)\\s+\\-x\\s+(<x2>[\\d]+)\\s+-y\\s+(?<y2>[\\d]+)\\s*"),

    USE_FIXED_CATAPULT_TO_ATTACK("attack\\s+with\\s+fixed\\s+catapult\\s+-x1\\s+(?<x1>[\\d]+)\\s+-y\\s+(?<y1>[\\d]+)\\s+\\-x\\s+(<x2>[\\d]+)\\s+-y\\s+(?<y2>[\\d]+)\\s*"),

    MOVE_FIERY_STONE_THROWER("move\\s+fiery\\s+stone\\s+in\\s+-x\\s+(?<x1>[\\d]+)\\s+\\s+-y\\s+(?<y1>[\\d]+)\\s+to\\s+-x\\s+(?<x2>[\\d]+)\\s+-y\\s+(?<y2>[\\d]+)\\s*"),

    USE_FIERY_STONE_TO_ATTACK("attack\\s+with\\s+battering\\s+-x\\s+(?<x>[\\d]+)\\s+-y\\s+(?<y>[\\d]+)\\s*"),

    ATTACK_TO_BUILDING("attack\\s+to\\s+building\\s+-x\\s+(?<x>[\\d]+)\\s+-y\\s+(?<y>[\\d]+)\\s*"),

    NEXT_TURN("next\\s+turn");



    private String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, GameMenuCommands gameMenuCommands) {
        Matcher matcher = Pattern.compile(gameMenuCommands.regex).matcher(command);
        if (matcher.find())
            return matcher;
        return null;
    }
    }
