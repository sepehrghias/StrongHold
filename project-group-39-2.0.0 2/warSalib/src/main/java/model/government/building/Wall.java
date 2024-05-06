package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.HashMap;

public class Wall extends Building{
    public Wall(int x, int y, Government government, int hp, String type, String name,
                HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, hp, resource);
    }

    public static Wall makeWallByName(String name, int x, int y, Government government, int flag) {
        HashMap<Resource, Integer> resource= new HashMap<>();
        if (name.equals("great wall")) {
            resource.put(Resource.STONE, 4);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Wall greatWall = new Wall(x, y, government, 800, "castle building", "great wall",
                        resource);
                return greatWall;
            }
            else
                System.out.println("dont have enough resource to make wall");
        }
        if (name.equals("small wall")) {
            resource.put(Resource.STONE, 2);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Wall smallWall = new Wall(x, y, government, 500, "castle building", "small wall",
                        resource);
                return smallWall;
            }
            else {
                System.out.println("dont have enough resource to make wall");

            }
        }
        return null;
    }
}
