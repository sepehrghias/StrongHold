package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;
import model.map.GameMap;
import model.map.type.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class StockPileBuilding extends Building {
    private HashMap<Resource, Integer> resources;

    private int capacity;

    public StockPileBuilding(int x, int y, Government government, int hp, String type, String name, int capacity,
                             HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, hp, resource);
        this.capacity = capacity;
        resources = new HashMap<>();
    }

    public static StockPileBuilding makeStockPileBuildingByName(String name, int x, int y, Government government,
                                                                int flag) {
        HashMap<Resource, Integer> resource = new HashMap<>();
        if (name.equals("stock pile")) {
            StockPileBuilding stockPile = new StockPileBuilding(x, y, government, 500, "industry",
                    "stock pile", 2000, resource);
            return stockPile;
        }
        if (name.equals("food stock pile")) {
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource) || flag == 1) {
                StockPileBuilding foodStockPile = new StockPileBuilding(x, y, government, 400,
                        "food processing building", "food stock pile", 3000, resource);
                return foodStockPile;
            }
        }
        if (name.equals("armoury")) {
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource) || flag == 1) {
                StockPileBuilding armoury = new StockPileBuilding(x, y, government, 900, "castle building",
                        "armoury", 1700, resource);
                return armoury;
            }
        }
        return null;
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

    public int getCapacity() {
        return capacity;
    }

    private static boolean isGoodPlace(String name, Type type,int x, int y, int numberOfBuilding) {
        if (name.equals("stock pile")) {
            if (Game.getMapInGame().haveBuildingsAround(name, x, y))
                return true;
            return false;
        }

        if (name.equals("food stock pile")) {
            if (Game.getMapInGame().haveBuildingsAround(name, x, y))
                return true;
            return false;
        }
        return true;
    }

    public void addToResources(Resource resource, int number) {
        if (getName().equals("stock pile")) {
            if (resource.getTypeOfResource() == Resource.TypeOfResource.INDUSTRY && capacity - number >0) {
                if (resources.containsKey(resource))
                    resources.replace(resource, resources.get(resource) + number);
                else
                    resources.put(resource, number);
                capacity -= number;
            }
        }
        if (getName().equals("food stock pile")) {
            if (resource.getTypeOfResource() == Resource.TypeOfResource.FOOD && capacity - number > 0) {
                if (resources.containsKey(resource))
                    resources.replace(resource, resources.get(resource) + number);
                else
                    resources.put(resource, number);
                capacity -= number;
            }
        }
        if (getName().equals("armoury")) {
            if (resource.getTypeOfResource() == Resource.TypeOfResource.WEAPON && capacity - number > 0) {
                if (resources.containsKey(resource))
                    resources.replace(resource, resources.get(resource) + number);
                else
                    resources.put(resource, number);
                capacity -= number;
            }
        }
    }

    public int useResource(Resource resource, int number) {
        if (resources.containsKey(resource)) {
            if (resources.get(resource) < number) {
                resources.replace(resource, 0);
                return number - resources.get(resource);
            }
            resources.replace(resource,resources.get(resource) - number);
            return 0;
        } else
            return number;
    }
}
