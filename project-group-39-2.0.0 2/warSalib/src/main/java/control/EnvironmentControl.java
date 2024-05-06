package control;

import model.Game;
import model.government.Government;
import model.government.building.Building;
import model.government.building.StockPileBuilding;
import model.government.people.units.Units;
import model.government.people.units.UnitsName;
import model.map.GameMap;
import model.map.rock.Rock;
import model.map.tree.Tree;
import model.map.type.Type;
import view.enums.messages.EnvironmentMenuMessage;

import java.util.Random;

public class EnvironmentControl {

    private static boolean validNumberOfMap(int number) {
        return false;
    }

    public static EnvironmentMenuMessage setTexture(int x, int y, String type) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (getTypeByName(type) == null)
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (hasBuilding(x, y, x, y))
            return EnvironmentMenuMessage.EXIST_BUILDING;
        Type typeOfGround = getTypeByName(type);
        Game.getMapInGame().getMap()[y][x].setType(typeOfGround);
        return EnvironmentMenuMessage.SUCCESS;
    }

    public static EnvironmentMenuMessage setTextureWithRectangle(int x, int y, int x1, int y1, String type) {
        if (!validCoordinate(x, y) || !validCoordinate(x1, y1) || x1 < x || y < y1)
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (getTypeByName(type) == null)
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (hasBuilding(x, y, x1, y1))
            return EnvironmentMenuMessage.EXIST_BUILDING;
        Type typeOFGround = getTypeByName(type);
        for (int j = y1; j <= y; j++) {
            for (int i = x; i <= x1; i++) {
                Game.getMapInGame().getMap()[j][i].setType(typeOFGround);
            }
        }
        return EnvironmentMenuMessage.SUCCESS;
    }

    private static Type getTypeByName(String type) {
        for (Type type1 : Type.values()) {
            if (type1.getGround().equals(type))
                return type1;
        }
        return null;
    }

    private static boolean validCoordinate(int x, int y) {
        return x >= 0 && x < 200 && y >= 0 && y < 200;
    }

    //if you want check one tile --> x = x1 , y = y1

    public static EnvironmentMenuMessage clearTile(int x, int y) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        Game.getMapInGame().getMap()[y][x].setType(Type.GROUND);
        return EnvironmentMenuMessage.SUCCESS;
    }

    public static EnvironmentMenuMessage dropRock(int x, int y, String direction) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (!validDirection(direction))
            return EnvironmentMenuMessage.WRONG_DIRECTION;
        Type type = Game.getMapInGame().getMap()[y][x].getType();
        if (type.equals(Type.SEA) || type.equals(Type.BIG_POND) || type.equals(Type.RIVER))
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (direction.equals("r")) {
            if ((direction = getRandomDirection()) == null)
                return EnvironmentMenuMessage.WRONG_DIRECTION;
            Rock rock = new Rock(direction, type);
            Game.getMapInGame().getMap()[y][x].setRock(rock);
        } else {
            Rock rock = new Rock(direction, type);
            Game.getMapInGame().getMap()[y][x].setRock(rock);
        }
        return EnvironmentMenuMessage.SUCCESS;
    }

    private static String getRandomDirection() {
        Random random = new Random();
        int rand = random.nextInt(4);
        if (rand == 0)
            return "n";
        else if (rand == 1)
            return "e";
        else if (rand == 2)
            return "w";
        else if (rand == 3)
            return "s";
        return null;
    }

    public static EnvironmentMenuMessage dropTree(int x, int y, String type) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (getTypeOfTree(type) == null)
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (!isGroundAppropriateForTree(Game.getMapInGame().getMap()[y][x].getType()))
            return EnvironmentMenuMessage.NOT_APPROPRIATE_GROUND;
        Tree tree = getTypeOfTree(type);
        Game.getMapInGame().getMap()[y][x].setTree(tree);
        return EnvironmentMenuMessage.SUCCESS;

    }

    public static EnvironmentMenuMessage dropBuilding(int x, int y, String type) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (Building.getGroupByName(type) == null)
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (!isAppropriateGroundForBuilding(x, y, type))
            return EnvironmentMenuMessage.NOT_APPROPRIATE_GROUND;
        if (Game.getMapInGame().getMap()[y][x].getGovernment() == null)
            return EnvironmentMenuMessage.NOT_HAVE_GOVERNMENT;
        if (hasBuilding(x,y,x,y))
            return EnvironmentMenuMessage.EXIST_BUILDING;
        Building building = Building.makeBuildingByName(type, x, y, Game.getMapInGame().getMap()[y][x].getGovernment(), 1);
        Government government = Game.getMapInGame().getMap()[y][x].getGovernment();
        government.addBuilding(building);
        if (building instanceof StockPileBuilding)
            building.getGovernment().addStockPile((StockPileBuilding) building);
        return EnvironmentMenuMessage.SUCCESS;
    }

    private static boolean isAppropriateGroundForBuilding(int x,int y, String name) {
        return Building.isAppropriateGround(Game.getMapInGame().getMap()[y][x].getType(), name);
    }

    public static EnvironmentMenuMessage dropUnit(int x, int y, String type, int count) {
        if (!validCoordinate(x, y))
            return EnvironmentMenuMessage.WRONG_AMOUNT;
        if (count < 0)
            return EnvironmentMenuMessage.WRONG_COUNT;
        if (!isCorrectType(type))
            return EnvironmentMenuMessage.WRONG_TYPE;
        if (!isGroundAppropriateForUnit(Game.getMapInGame().getMap()[y][x].getType()))
            return EnvironmentMenuMessage.NOT_APPROPRIATE_GROUND;
        Government government = Game.getMapInGame().getMap()[y][x].getGovernment();
        if (government == null)
            return EnvironmentMenuMessage.NOT_HAVE_GOVERNMENT;
        UnitsName unitsName = getUnitNameByType(type);
        for (int i = 0; i < count; i++) {
            Units unit = new Units(x, y, unitsName, government.getUser());
            government.addToPeople(unit);
            Game.getMapInGame().getMap()[y][x].addPeople(unit);
        }
        return EnvironmentMenuMessage.SUCCESS;
    }

    private static UnitsName getUnitNameByType(String type) {
        for (UnitsName unitsName:UnitsName.values()) {
            if (unitsName.getName().equals(type))
                return unitsName;
        }
        return null;
    }

    private static Tree getTypeOfTree(String type) {
        for (Tree tree : Tree.values()) {
            if (tree.getType().equals(type))
                return tree;
        }
        return null;
    }

    private static boolean validDirection(String direction) {
        return direction.equals("n") || direction.equals("e") || direction.equals("w") || direction.equals("s") || direction.equals("r");
    }

    private static boolean isGroundAppropriateForTree(Type typeOfGround) {
        if (typeOfGround.getArea().equals(Type.Area.BLUEAREA) || typeOfGround.equals(Type.STONE) ||
                typeOfGround.equals(Type.IRON_GROUND) || typeOfGround.equals(Type.SLATE))
            return false;
        return true;
    }

    private static boolean isGroundAppropriateForUnit(Type typeOfGround) {
        if (typeOfGround.equals(Type.PLAIN) || typeOfGround.equals(Type.STONE) || typeOfGround.equals(Type.BIG_POND) ||
        typeOfGround.equals(Type.SMALL_POND) || typeOfGround.equals(Type.RIVER))
            return false;
        return true;
    }

    public static EnvironmentMenuMessage chooseColor(String color) {
        return null;
    }

    private static boolean isCorrectType (String type) {
        for (UnitsName unitsName : UnitsName.values()) {
            if (unitsName.getName().equals(type))
                return true;
        }
        return false;
    }
    private static boolean hasBuilding(int x, int y, int x1, int y1) {
        for (int j = y1; j <= y; j++) {
            for (int i = x; i <= x1; i++) {
                if (Game.getMapInGame().getMap()[j][i].getBuilding() != null)
                    return true;
            }
        }
        return false;
    }
}
