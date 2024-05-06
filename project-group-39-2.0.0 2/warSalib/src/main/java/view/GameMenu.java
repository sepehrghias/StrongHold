package view;

import control.GameControl;
import model.Game;
import model.government.people.units.State;
import model.government.people.units.Units;
import model.map.Tile;
import view.enums.commands.GameMenuCommands;
import view.enums.commands.ProfileMenuCommands;
import view.enums.commands.MapMenuCommands;
import view.enums.messages.GameMenuMessage;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    static String type;

    public static void run() {
        String input;
        Scanner scanner = Scan.getScanner();
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECTUNIT)) != null)
                selectUnit(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MOVEUNIT)) != null)
                moveUnit(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.PATROLUNIT)) != null)
                patrolUnit(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SETMOODE)) != null)
                setMode(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.ATTACK)) != null)
                attack(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.AIRATTACK)) != null)
                airAttack(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECTUNIT)) != null)
                selectUnit(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.POUROIL)) != null)
                pourOil(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DIGTUNNEL)) != null)
                digTunnel(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.UNSELECT_UNIT)) != null)
                unselectUnits(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DISBANDUNIT)) != null)
                disbandUnit(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_GATE)) != null)
                makeGate(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_WALL)) != null)
                makeWall(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_TOWER)) != null)
                makeTower(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_STAIR)) != null)
                makeStair(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_KILLER_TALE)) != null)
                makeKillerTale(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_OIL_TALE)) != null)
                makeOilTale(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_PROTECTION)) != null)
                makeProtection(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.CAPTURE_GATE)) != null)
                captureGate(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_BATTERING_RAM)) != null)
                makeBatteringRam(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_CATAPULT)) != null)
                makeCatapult(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MOVE_BATTERING)) != null)
                moveBattering(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.USE_BATTERING_TO_ATTACK)) != null)
                useBatteringToattack(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_FIXED_CATAPULT)) != null)
                makeFixedCatapult(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_SIEGE_TOWER)) != null)
                makeSiegeTower(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MAKE_FIERY_STONE_THROWER)) != null)
                makeFieryStoneThrower(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.NEXT_TURN)) != null) {
                if(nextTurn(matcher)==0){
                    break;
                }

            }
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.STOP_PATROL_UNIT)) != null)
                stopPatrol(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.USE_CATAPULT_TO_ATTACK)) != null)
                attackWithCatapult(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.USE_FIXED_CATAPULT_TO_ATTACK)) != null)
                attackWithFixedCatapult(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MOVE_CATAPULT)) != null)
                moveCatapult(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.MOVE_FIERY_STONE_THROWER)) != null)
                moveFieryStoneThrower(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.USE_FIERY_STONE_TO_ATTACK)) != null)
                attackWithFieryStoneThrower(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.ATTACK_TO_BUILDING)) != null)
                attackToBuilding(matcher);
            else if ((matcher = MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_MAP)) != null)
                MapMenu.run(input, scanner);

            else if (input.matches("^\\s*enter\\s+building\\s+menu\\s*$")) {
                System.out.println("entered building menu");
                BuildingMenu.run(scanner);
            }
            else if (input.matches("^\\s*enter\\s+government\\s+menu\\s*$")) {
                System.out.println("entered government menu");
                GovernmentMenu.run(scanner);
            }
            else if (input.matches("^\\s*enter\\s+trade\\s+menu\\s*$")) {
                System.out.println("entered trade menu");
                TradeMenu.run(scanner);
            } else System.out.println("invalid command");
        }
    }

    private static void unselectUnits(Matcher matcher) {
        GameMenuMessage message=GameControl.unselectunits();
        switch (message){
            case SUCCESS:
                System.out.println("units un selected successfully");
                break;
            case PROBLEM:
                System.out.println("units cant un selected ");
                break;

        }    }

    private static void attackToBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.attackToBuilding(x, y);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("invalid amount for x or y");
                break;
            case INVALIDUNIT:
                System.out.println("you dont have selected archer");
                break;
            case PROBLEM:
                System.out.println("out of range of units board");
                break;
            case WITH_OUT_BUILDING:
                System.out.println("we dont have any building for enemies on this tile");
                break;
            case SUCCESS:
                System.out.println("you attacked successfully to building");
                break;


        }
    }

    private static void attackWithFieryStoneThrower(Matcher matcher) {
        int previousX = Integer.parseInt(matcher.group("x1"));
        int previousY = Integer.parseInt(matcher.group("y1"));
        int moveToX = Integer.parseInt(matcher.group("x2"));
        int moveToY = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.attackWithFieryStone(previousX, previousY, moveToX, moveToY);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("wrong amount of x or y");
                break;
            case PROBLEM:
                System.out.println("you dont have fiery stone on this tile");
                break;
            case OUT_OF_BOARD:
                System.out.println("out of fiery stone board!");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource");
                break;
            case SUCCESS:
                System.out.println("you attacked successfully");
                break;
        }
    }

    private static void moveFieryStoneThrower(Matcher matcher) {
        int previousX = Integer.parseInt(matcher.group("x1"));
        int previousY = Integer.parseInt(matcher.group("y1"));
        int moveToX = Integer.parseInt(matcher.group("x2"));
        int moveToY = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.moveFieryStoneThrower(previousX, previousY, moveToX, moveToY);
        switch (message) {
            case BIGGERTHANSPEED:
                System.out.println("bigger than unit speed");
                break;
            case WRONG_AMOUNT:
                System.out.println("wrong amount of x or y");
                break;
            case PROBLEM:
                System.out.println("you dont have fiery stone on this tile");
                break;
            case SUCCESS:
                System.out.println("fiery stone moved successfully");
                break;

        }
    }

    private static void attackWithFixedCatapult(Matcher matcher) {
        int previousX = Integer.parseInt(matcher.group("x1"));
        int previousY = Integer.parseInt(matcher.group("y1"));
        int moveToX = Integer.parseInt(matcher.group("x2"));
        int moveToY = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.attackWithFixedCatapult(previousX, previousY, moveToX, moveToY);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("wrong amount of x or y");
                break;
            case PROBLEM:
                System.out.println("you dont have fixed catapult on this tile");
                break;
            case OUT_OF_BOARD:
                System.out.println("out of fixed catapult board!");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource");
                break;
            case SUCCESS:
                System.out.println("you attacked successfully");
                break;
        }
    }

    private static void attackWithCatapult(Matcher matcher) {
        int previousX = Integer.parseInt(matcher.group("x1"));
        int previousY = Integer.parseInt(matcher.group("y1"));
        int moveToX = Integer.parseInt(matcher.group("x2"));
        int moveToY = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.attackWithCatapult(previousX, previousY, moveToX, moveToY);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("wrong amount of x or y");
                break;
            case PROBLEM:
                System.out.println("you dont have catapult on this tile");
                break;
            case OUT_OF_BOARD:
                System.out.println("out of catapult board!");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource");
                break;
            case SUCCESS:
                System.out.println("you attacked successfully");
                break;
        }
    }

    private static void moveCatapult(Matcher matcher) {
        int previousX = Integer.parseInt(matcher.group("x1"));
        int previousY = Integer.parseInt(matcher.group("y1"));
        int moveToX = Integer.parseInt(matcher.group("x2"));
        int moveToY = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.moveCatapult(previousX, previousY, moveToX, moveToY);
        switch (message) {
            case BIGGERTHANSPEED:
                System.out.println("bigger than unit speed");
                break;
            case WRONG_AMOUNT:
                System.out.println("wrong amount of x or y");
                break;
            case PROBLEM:
                System.out.println("you dont have catapult on this tile");
                break;
            case SUCCESS:
                System.out.println("catapult moved successfully");
                break;

        }
    }

    private static void useBatteringToattack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.useBatteringToAttack(x, y);
        switch (message) {
            case PROBLEM:
                System.out.println("you dont have battering on this tile or we dont have any building of enemies");
                break;
            case WRONG_AMOUNT:
                System.out.println("wrong amount of x or y");
                break;
            case SUCCESS:
                System.out.println("attacked successfully");
                break;
        }
    }

    private static void moveBattering(Matcher matcher) {
        int previousX = Integer.parseInt(matcher.group("x1"));
        int previousY = Integer.parseInt(matcher.group("y1"));
        int moveToX = Integer.parseInt(matcher.group("x2"));
        int moveToY = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.moveBattering(previousX, previousY, moveToX, moveToY);
        switch (message) {
            case BIGGERTHANSPEED:
                System.out.println("bigger than unit speed");
                break;
            case WRONG_AMOUNT:
                System.out.println("wrong amount of x or y");
                break;
            case PROBLEM:
                System.out.println("you dont have battering on this tile");
                break;
            case SUCCESS:
                System.out.println("battering moved successfully");
                break;

        }
    }

    private static void selectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        type = matcher.group("type");
        GameMenuMessage message = GameControl.selectUnit(x, y, type);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case SUCCESS:
                System.out.println("you select it successfuly");
                break;
            case WITHOUTUNIT:
                System.out.println("we dont have unit on this tile");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }
    }

    private static void moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        //we should handle type with enum
        GameMenuMessage message = GameControl.moveUnit(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("unit moved successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case SEA_HIGHHEIGHT:
                System.out.println("unit cant move on this tiles");
                break;
            case BIGGERTHANSPEED:
                System.out.println("distance was bigger than unit speed");
                break;
            case PROBLEM:
                System.out.println("unit cant move between points");
            default:
                System.out.println("invalid command!!?");
                break;
        }

    }

    private static void patrolUnit(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        GameMenuMessage message = GameControl.patrolUnit(x1, y1, x2, y2);
        switch (message) {
            case SUCCESS:
                break;
            case PROBLEM:
                System.out.println("unit cant patrol between two points");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x1 or x2 or y1 or y2");
                break;
            default:
                System.out.println("invalid command!!?");
                break;
        }
    }

    private static void setMode(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        State state = State.valueOf(matcher.group("moode"));
        GameMenuMessage message = GameControl.setMode(x, y, state);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case SUCCESS:
                System.out.println("moode changed successfully");
                break;
            default:
                System.out.println("invalid command");
                break;
        }
    }

    private static void attack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.attack(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("you attacked successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case BIGGERTHANSPEED:
                System.out.println("enemy is out of unit speed");
                break;
            default:
                System.out.println("invalid command!");
                break;
        }

    }

    private static void airAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.airAttack(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("you started shooting successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("position is out of archer's position");
                break;
            case INVALIDUNIT:
                System.out.println("selected unit isn't archer");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource to air attack");
                break;
            default:
                System.out.println("invalid command!");
                break;

        }
    }

    private static void pourOil(Matcher matcher) {
        String direction = matcher.group("direction");
        GameMenuMessage message = GameControl.pourOil(direction);
        switch (message) {
            case SUCCESS:
                System.out.println("oil poiled successfully");
                break;
            case INVALIDDIRECTION:
                System.out.println("direction is invalid");
                break;
            case INVALIDUNIT:
                System.out.println("unit is invalid");
                break;
            default:
                System.out.println("invalid command!!?");
                break;

        }
    }

    private static void digTunnel(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.digTunnel(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("tunnel dig was successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case CANT_DIG:
                System.out.println("you cant dig tunnel on this location");
                break;
            case INVALIDUNIT:
                System.out.println("unit is invalid");
                break;
            default:
                System.out.println("invalid command!!?");
                break;
        }


    }


    private static void disbandUnit(Matcher matcher) {
        GameMenuMessage message = GameControl.disbandUnit();
        switch (message) {
            case SUCCESS:
                System.out.println("units disband was successfully");
                break;
            default:
                System.out.println("invalid command!");
                break;

        }
    }

    private static void makeGate(Matcher matcher) {
        String direction = matcher.group("direction");
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String name = matcher.group("gateName");
        name = name.trim();
        GameMenuMessage message = GameControl.makeGate(name, direction, x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("  " + name + " with " + direction + "  created successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case INVALIDDIRECTION:
                System.out.println("direction is invalid");
                break;
            default:
                System.out.println("invalid command!");
                break;
        }
    }

    private static void makeWall(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("wallType").trim();
        GameMenuMessage message = GameControl.makeWall(x, y, type);
        switch (message) {
            case SUCCESS:
                System.out.println("wall build successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case INVALID_TYPE:
                System.out.println("we dont have this format for wall");
                break;
            case HAS_BUILDING:
                System.out.println("we have building on this location");
                break;
            default:
                System.out.println("invalid command!!");
                break;

        }

    }

    private static void makeTower(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        GameMenuMessage message = GameControl.makeTower(x, y, type);
        switch (message) {
            case SUCCESS:
                System.out.println("tower build successfully");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("your resource is not enough");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case INVALID_TYPE:
                System.out.println("we dont have this format of tower");
                break;
            case HAS_BUILDING:
                System.out.println("we have building on this location");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }

    }

    private static void makeStair(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeStair(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("tale built successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make stair in this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }

    }

    private static void makeKillerTale(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeKillerTale(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("Killer tale built successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;

        }
    }

    private static void makeOilTale(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeOilTale(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("oil tale built successfully");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case INVALIDPOSITION:
                System.out.println("you cant make tale in this position");
                break;
            default:
                System.out.println("invalid command!!");
                break;

        }

    }

//    private static void diggingDitch(Matcher matcher) {
//        //should complete with type of unit;
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));
//         GameMenuMessage message = GameControl.diggingDitch(x, y);
//        switch (message) {
//            case SUCCESS:
//                System.out.println("Ditch dig was successfully");
//                break;
//            case INVALIDPOSITION:
//                System.out.println("you cant make tale in this position");
//                break;
//            case INVALIDUNIT:
//                System.out.println("This unit cant dig ditch");
//                break;
//            default:
//                System.out.println("invalid command!");
//                break;
//        }
//    }
//
//    private static void removeDitch(Matcher matcher) {
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));
//        GameMenuMessage message = GameControl.removeDitch(x, y);
//        switch (message) {
//            case SUCCESS:
//                System.out.println("ditch removed successfully");
//                break;
//            case INVALIDPOSITION:
//                System.out.println("you cant make tale in this position");
//                break;
//            case INVALIDDITCH:
//                System.out.println("we dont have ditch on this position");
//                break;
//            default:
//                System.out.println("invalid command!!");
//                break;
//        }
//    }
//
//    private static void stopDitch(Matcher matcher) {
//        int x = Integer.parseInt(matcher.group("x"));
//        int y = Integer.parseInt(matcher.group("y"));
//        GameMenuMessage message = GameControl.stopDitch(x, y);
//        switch (message) {
//            case SUCCESS:
//                System.out.println("stop dig successfully");
//                break;
//            case INVALIDPOSITION:
//                System.out.println("you cant make tale in this position");
//                break;
//            default:
//                System.out.println("invalid command!!");
//                break;
//
//        }
//    }

    private static void burningOil(Matcher matcher) {

    }

    private static void captureGate(Matcher matcher) {
        GameMenuMessage message = GameControl.captureGate();
        switch (message) {
            case SUCCESS:
                GameMenuMessage message1 = GameControl.captureGate();
                switch (message1) {
                    case SUCCESS:
                        System.out.println("Gate opened successfully");
                        break;
                    case PROBLEM:
                        System.out.println("now you cant capture gate");
                    default:
                        System.out.println("invalid command!!");
                        break;

                }

        }
    }

    private static void makeProtection(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String unitsname = matcher.group("unitsName");
        //should be complete with units
        GameMenuMessage message = GameControl.makeProtection(x, y, unitsname);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case SUCCESS:
                System.out.println("protection built successfully");
                break;
            case PROBLEM:
                System.out.println("we dont have unemployed engineer to make protection");
                break;
            case INVALIDUNIT:
                System.out.println("we dont have this units is this tile");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource to make protection");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void makeBatteringRam(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        //should be complete
        GameMenuMessage message = GameControl.makeBatteringRam(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("battering was successful");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("we dont have unemployed engineer to make protection");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource to make protection");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void makeCatapult(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeCatapult(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("catapult was successful");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("we dont have unemployed engineer to make catapult");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource to make catapult");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void makeFixedCatapult(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeFixedCatapult(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("fixed catapult was successful");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("we dont have unemployed engineer to make fixed catapult");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource to make fixed catapult");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void stoneTower(Matcher matcher) {

    }

    private static void makeSiegeTower(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeSiegeTower(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("makeSiege was successful");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("we dont have unemployed engineer to make SiegeTower");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource to make siegeTower");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }
    }

    private static void makeFieryStoneThrower(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.makeFieryStoneThrower(x, y);
        switch (message) {
            case SUCCESS:
                System.out.println("Fiery stone thrower was successful");
                break;
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("we dont have unemployed engineer to make fiery stone thrower");
                break;
            case NOTENOUGHRESOURCE:
                System.out.println("you dont have enough resource to make fiery stone thrower");
                break;
            default:
                System.out.println("invalid command!!");
                break;
        }

    }

    private static int nextTurn(Matcher matcher) {
        GameMenuMessage message = GameControl.nextTurn();
        switch (message) {
            case NEXT_PLAYER:
                System.out.println("Player    :  " + Game.getTurnedUserForGame().getUsername() + "   should be play now!");
                break;
            case NEXT_TURN:
                System.out.println("next turn");
                System.out.println("Player:  " + Game.getTurnedUserForGame().getUsername() + "   should be play now!");
                break;
            case FINISH_GAME:
                System.out.println("Game finished!");
                return 0;
        }
        return 1;
    }

    private static void stopPatrol(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage message = GameControl.stopPatrol(x, y);
        switch (message) {
            case WRONG_AMOUNT:
                System.out.println("you enter wrong amount of x and y");
                break;
            case PROBLEM:
                System.out.println("you dont have patrol unit in this tile to stop it");
                break;
            case SUCCESS:
                System.out.println("patrol unit stopping successfully");
                break;
        }
    }

}
