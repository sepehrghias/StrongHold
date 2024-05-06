package control;

import model.Game;
import model.government.building.Building;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.units.Units;
import model.map.GameMap;
import model.map.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class MapControl {

    public static Tile[][] showMap(int x, int y) {
        if (!validCoordinates(x, y)) {
            return null;
        }
        char c;
        Tile[][] smallMap = Game.getMapInGame().getMapAroundCoordinate(x, y);
        return smallMap;
    }

    public static Tile[][] moveMap(int up, int down, int right, int left) {
        int x = Game.getMapInGame().getSelectedX();
        int y = Game.getMapInGame().getSelectedY();
        return showMap(x + left - right , y - down + up);
    }

    public static String showDetails(int x, int y) {
        String result = "";
        if (!validCoordinates(x, y)) {
            return result;
        }
        GameMap map = Game.getMapInGame();
        Tile tile = map.getMap()[y][x];
        map.setSelectedY(y);
        map.setSelectedX(x);
        result += "ground : ";
        result += tile.getType() + "\n";
        if (tile.getResource() != null) {
            result += "resource : ";
            result += tile.getResource() + "\n";
        }
        if (hasUnits(tile.getPeopleOnTile())) {
            result += "units : ";
            HashMap <People, Integer> unitsPeople = countUnitsInHashMap(tile.getPeopleOnTile());
            for (People people : unitsPeople.keySet()) {
                result += ((Units)people).getUnitsName().getName() + "   "  + "user name  "
                        +people.getOwnerPerson().getUsername() +"\n";
            }
        }
        if (tile.getBuilding() != null) {
            result += "building : ";
            result += tile.getBuilding().getName() + "  user :" + Game.getTurnedUserForGame().getUsername() +"\n";
        }
        if (tile.getTree() != null) {
            result += "tree : ";
            result += tile.getTree().getType() + "\n";
        }
        return result;
    }

    private static boolean validCoordinates(int x, int y) {
        if (x >= 0 && x < 200 && y >= 0 && y < 200)
            return true;
        return false;
    }

    private static char hasBuilding(Tile[][] smallMap, int x, int y) {
        if (smallMap[y][x] == null)
            return 'N';
        if (smallMap[y][x].getBuilding() != null) {
            Building building = smallMap[y][x].getBuilding();
            GroupOfBuilding group = Building.getGroupByName(building.getName());
            if (group == GroupOfBuilding.WALL || group == GroupOfBuilding.GATEHOUSE ||
                    group == GroupOfBuilding.TOWER)
                return 'W';
            return 'B';
        }
        return 'N';
    }

    private static HashMap<People, Integer> countUnitsInHashMap (ArrayList<People> people) {
        HashMap <People,Integer> units = new HashMap<>();
        int num;
        for (People people1:people) {
            if (people1 instanceof Units) {
                if (units.containsKey(people1)) {
                    num = units.get(people1) + 1;
                    units.replace(people1, num);
                }
                else units.put(people1, 1);
            }
        }
        return units;
    }

    private static boolean hasUnits(ArrayList<People> people) {
        if (people.size() == 0)
            return false;
        for (People subPeople: people) {
            if (subPeople instanceof Units)
                return true;
        }
        return false;
    }
    private static boolean validDirection(String[] direction) {
        return false;
    }
}
