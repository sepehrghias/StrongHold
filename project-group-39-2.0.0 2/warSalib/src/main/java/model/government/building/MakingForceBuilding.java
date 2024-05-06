package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.units.UnitsName;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class MakingForceBuilding extends Building{

    public MakingForceBuilding(int x, int y, Government government, int hp, String name,
                               HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, "castle building", name, hp, resource);
    }

    public static MakingForceBuilding makeMakingForceBuildingByName(String name, int x ,int y, Government government, int flag) {
        HashMap<Resource, Integer> resource= new HashMap<>();
        if (name.equals("barrack")) {
            resource.put(Resource.STONE, 15);
            if (government.hasEnoughResources(resource) || flag == 1) {
                MakingForceBuilding barrack = new MakingForceBuilding(x, y, government, 1000, name, resource);
                return barrack;
            }
        }
        if (name.equals("engineer guild")) {
            resource.put(Resource.WOOD, 10);
            if ((government.getWealth() > 100 && government.hasEnoughResources(resource)) || flag == 1) {
                MakingForceBuilding engineerGuild = new MakingForceBuilding(x, y, government, 800, name, resource);
                government.setWealth(government.getWealth() - 100);
                return engineerGuild;
            }
        }
        if (name.equals("mercenary post")) {
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource) || flag == 1) {
                MakingForceBuilding mercenaryPost = new MakingForceBuilding(x, y, government, 1200, name, resource);
                return mercenaryPost;
            }
        }
        return null;
    }

    public People getForceByCost() {
        return null;
    }

    public void changePeopleRoll(People people) {

    }
}
