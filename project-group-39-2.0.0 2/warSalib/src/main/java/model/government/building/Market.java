package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Market extends Building{
    private HashMap<Resource, Integer>resources;

    public Market(int x, int y, Government government, int hp, HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, "industry", "market", hp, resource);
        resources = new HashMap<>();
        resources.put(Resource.STONE, 100);
        resources.put(Resource.IRON, 60);
        resources.put(Resource.HOP, 50);
        resources.put(Resource.WOOD, 100);
        resources.put(Resource.ARCHER, 20);
        resources.put(Resource.ARMOUR, 10);
        resources.put(Resource.SPEAR, 40);
    }
    public static Market makeMarketByName(String name, int x , int y, Government government, int flag) {
        if (name.equals("market")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Market market = new Market(x, y, government, 500, resource);
                market.setWorkerDataBase(JobsName.SHOPPER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.SHOPPER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return market;
            }
        }
        return null;
    }

    public void addResourceToMarket(Resource resource, int number) {
        resources.put(resource, number);
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

    public boolean buyResource(Resource resource, int amount) {
       if (resources.containsKey(resource)) {
           if (resources.get(resource) >= amount) {
                resources.replace(resource, resources.get(resource) - amount);
               return true;
           }
           return false;
       }
       return false;
    }

    public void sellResource(Resource resource, int number){
        if (resources.containsKey(resource)) {
            resources.replace(resource, resources.get(resource) + number);
        }
        else {
            resources.put(resource, number);
        }
    }
}
