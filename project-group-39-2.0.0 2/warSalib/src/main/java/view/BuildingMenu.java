package view;

import control.BuildingControl;
import model.Game;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BuildingMenu {
    public static void run(Scanner scanner) {
        String input;
        while (true) {
            input = Scan.getScanner().nextLine();
            Matcher matcher;
            if (input.matches("^\\s*repair\\s*$")) {
                repair();
            } else if (input.matches("^\\s*back\\s*$")) {
                System.out.println("back to game menu");
                break;
//            } else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.DROP_BUILDING)) != null) {
//                dropBuilding(matcher);
            } else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.SELECT_BUILDING)) != null) {
                selectBuilding(matcher, scanner);
            } else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.CREATE_UNIT)) != null) {
                createUnit(matcher);
            } else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.OPEN_CAGE_DOG)) != null)
                openCagedDog(matcher);
            else if ((matcher = BuildingCommands.getMatcher(input, BuildingCommands.CHANGE_TAX_RATE)) != null)
                changeTaxRate(matcher);
            else System.out.println("invalid command!");
        }
    }

//    private static void dropBuilding(Matcher matcher) {
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));
//        String type = matcher.group("type");
//        if (type.startsWith("\"")) {
//            type = type.substring(1, type.length() - 1);
//        }
//        BuildingMessage message = BuildingControl.dropBuilding(x, y, type);
//       switch (message) {
//            case WRONG_AMOUNT:
//                System.out.println("you enter wrong amount of x and y");
//                break;
//            case BAD_GROUND:
//                System.out.println("you can't build this building in this ground");
//                break;
//            case EXIST:
//                System.out.println("has building in this tile");
//                break;
//           case WRONG_TYPE:
//               System.out.println("you enter wrong type");
//               break;
//           case NOT_ENOUGH_SOURCE:
//               System.out.println("not enough resource");
//               break;
//            case SUCCESS:
//                System.out.println("you drop it successfully");
//                break;
//            default:
//                System.out.println("invalid!!?");
//                break;
//        }
//    }

    private static void changeTaxRate (Matcher matcher) {
        int taxRate = Integer.parseInt(matcher.group("rate"));
        BuildingMessage message = BuildingControl.changeTaxRate(taxRate);
        switch (message) {
            case WRONG_AMOUNT :
                System.out.println("you enter wrong amount");
                break;
            case NOT_GOOD_BUILDING:
                System.out.println("it's not gatehouse");
                break;
            case NOT_SELECT_BUILDING:
                System.out.println("please select building");
                break;
            case SUCCESS:
                System.out.println("you chang rate successfully");
                break;
            default:
                System.out.println("invalid!");
                break;
        }
    }

    private static void selectBuilding(Matcher matcher, Scanner scanner) {
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));
//        BuildingMessage message = BuildingControl.selectBuilding(x, y);
//        switch (message) {
//            case WRONG_AMOUNT:
//                System.out.println("you enter wrong amount of x and y");
//                break;
//            case NOT_EXIST:
//                System.out.println("not exist any building in this tile");
//                break;
//            case NOT_BELONG_TO_YOU:
//                System.out.println("this tile not belong to you!");
//                break;
//            case SELECT_MARKET:
//                System.out.println("you enter shop Menu");
//                StoreMenu.run(scanner);
//                break;
//            case SUCCESS:
//                System.out.println("select successfully");
//                break;
//            default:
//                System.out.println("invalid!!?");
//                break;
//        }
    }

    private static void createUnit(Matcher matcher) {
        String type = matcher.group("type");
        if (type.startsWith("\"")) {
            type = type.substring(1, type.length() - 1);
        }
        BuildingMessage message = BuildingControl.createUnit(type);
        switch (message) {
            case NOT_ENOUGH_SOURCE:
                System.out.println("you don't have enough sources to create unit");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case NOT_EXIST_UNIT:
                System.out.println("you enter wrong type of units");
                break;
            case NOT_SELECT_BUILDING:
                System.out.println("please select building");
                break;
            case NOT_ENOUGH_POPULATION:
                System.out.println("you don't have enough population");
                break;
            case NOT_APPROPRIATE_UNIT:
                System.out.println("chose wrong type of units");
                break;
            case SUCCESS:
                System.out.println("create units successfully");
                break;
            default:
                System.out.println("invalid!");
                break;
        }
    }

    private static void openCagedDog (Matcher matcher) {
        String state = matcher.group("open");
        BuildingMessage message = BuildingControl.openCagedDog(state);
        switch (message) {
            case WRONG_AMOUNT :
                System.out.println("you enter wrong amount");
                break;
            case NOT_GOOD_BUILDING:
                System.out.println("it's not caged war dog");
                break;
            case NOT_SELECT_BUILDING:
                System.out.println("please select building");
                break;
            case OPEN:
                System.out.println("succeed opened");
                break;
            case CLOSE:
                System.out.println("succeed closed");
                break;
            default:
                System.out.println("invalid!");
                break;
        }
    }

    private static void repair() {
        BuildingMessage message = BuildingControl.repair();
        switch (message) {
            case NOT_ENOUGH_STONE:
                System.out.println("not enough stones");
                break;
            case NOT_SELECT_BUILDING:
                System.out.println("please select building");
                break;
            case NEAR_ENEMY:
                System.out.println("enemy near your town and can't repair");
                break;
            case HAS_FULL_HP:
                System.out.println("your building has max hp and don't need to repair it");
                break;
            case NOT_GOOD_BUILDING:
                System.out.println("not castle building");
                break;
            case SUCCESS:
                System.out.println("repair successfully");
                break;
            default:
                System.out.println("invalid!");
                break;
        }
    }
}
