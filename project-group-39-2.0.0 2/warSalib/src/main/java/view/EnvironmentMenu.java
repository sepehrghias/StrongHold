package view;

import control.EnvironmentControl;
import model.Game;
import view.enums.commands.EnvironmentMenuCommands;
import view.enums.messages.EnvironmentMenuMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class EnvironmentMenu {
    public static void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("^\\s*generate\\s+map\\s*$")) {
                System.out.println("generated map and go to the main game menu");
                Game.setTurnedUserForGame(Game.getCurrentUser());
                GameMenu.run();
                break;
            }
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.SET_TEXTURE)) != null)
                setTexture(matcher);
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.SET_TEXTURE_RECTANGLE)) != null)
                setTextureRectangle(matcher);
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.CLEAR)) != null)
                clearTile(matcher);
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.DROP_ROCK)) != null)
                dropRock(matcher);
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.DROP_TREE)) != null)
                dropTree(matcher);
            else if ((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.DROP_BUILDING)) != null)
                dropBuilding(matcher);
            else if((matcher = EnvironmentMenuCommands.getMatcher(input, EnvironmentMenuCommands.DROP_UNIT)) != null)
                dropUnit(matcher);
            else System.out.println("invalid");
        }
    }

    public static void chooseMap() {

    }

    private static void setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = getTypeWithoutDoubleQuotation(matcher.group("type"));
        EnvironmentMenuMessage message = EnvironmentControl.setTexture(x, y, type);
        switch (message) {
            case WRONG_AMOUNT :
                System.out.println("you enter wrong x and y");
                break;
            case EXIST_BUILDING:
                System.out.println("you can't set texture for building");
                break;
            case WRONG_TYPE:
                System.out.println("yoe enter wrong type");
                break;
            case SUCCESS:
                System.out.println("set successfully");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }

    private static void setTextureRectangle(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x"));
        int y1 = Integer.parseInt(matcher.group("y"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        String type = getTypeWithoutDoubleQuotation(matcher.group("type"));
        EnvironmentMenuMessage message = EnvironmentControl.setTextureWithRectangle(x1, y1, x2, y2, type);
        switch (message) {
            case WRONG_AMOUNT :
                System.out.println("you enter wrong x and y");
                break;
            case EXIST_BUILDING:
                System.out.println("you can't set texture these tiles for building");
                break;
            case WRONG_TYPE:
                System.out.println("yoe enter wrong type");
                break;
            case SUCCESS:
                System.out.println("set texture successfully");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }

    private static void clearTile(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        EnvironmentMenuMessage message = EnvironmentControl.clearTile(x, y);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong x and y");
                break;
            case SUCCESS:
                System.out.println("back this tile to first of map");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }

    private static void dropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String direction = matcher.group("direction");
        EnvironmentMenuMessage message = EnvironmentControl.dropRock(x, y, direction);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong x and y");
                break;
            case WRONG_TYPE:
                System.out.println("you can't drop rock in this type");
                break;
            case WRONG_DIRECTION:
                System.out.println("you enter wrong direction");
                break;
            case SUCCESS:
                System.out.println("drop rock successfully");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }

    private static void dropTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = getTypeWithoutDoubleQuotation(matcher.group("type"));
        EnvironmentMenuMessage message = EnvironmentControl.dropTree(x, y, type);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong x and y");
                break;
            case WRONG_TYPE:
                System.out.println("you enter wrong type");
                break;
            case NOT_APPROPRIATE_GROUND:
                System.out.println("it's not good ground for trees");
                break;
            case SUCCESS:
                System.out.println("drop tree successfully");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }

    private static void dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = getTypeWithoutDoubleQuotation(matcher.group("type"));
        EnvironmentMenuMessage message = EnvironmentControl.dropBuilding(x, y, type);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong x and y");
                break;
            case WRONG_TYPE:
                System.out.println("you enter wrong type");
                break;
            case NOT_APPROPRIATE_GROUND:
                System.out.println("it's not good ground for this building");
                break;
            case NOT_HAVE_GOVERNMENT:
                System.out.println("in this tile hasn't government");
                break;
            case EXIST_BUILDING:
                System.out.println("is another building in this tile");
                break;
            case SUCCESS:
                System.out.println("drop building successfully");
                break;
            default:
                System.out.println("invalid");
                break;
        }

    }

    private static void dropUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = getTypeWithoutDoubleQuotation(matcher.group("type"));
        int count = Integer.parseInt(matcher.group("count"));
        EnvironmentMenuMessage message = EnvironmentControl.dropUnit(x, y, type, count);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong x and y");
                break;
            case WRONG_TYPE:
                System.out.println("you enter wrong type");
                break;
            case WRONG_COUNT:
                System.out.println("you enter wrong count");
                break;
            case NOT_HAVE_GOVERNMENT:
                System.out.println("in this tile hasn't government");
                break;
            case NOT_APPROPRIATE_GROUND:
                System.out.println("can't stay units in this ground");
                break;
            case SUCCESS:
                System.out.println("drop units successfully");
                break;
            default:
                System.out.println("invalid");
                break;
        }

    }

    private static void chooseColor(Matcher matcher) {

    }

    private static void setKeep(Matcher matcher) {

    }

    private static String getTypeWithoutDoubleQuotation(String type) {
        if (type.startsWith("\"")) {
            return type.substring(1, type.length() - 1);
        }
        return type;
    }
}
