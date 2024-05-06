package model.government.building;

import model.Game;
import model.government.Government;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.HashMap;

public class ConvertedBuilding extends Building {
    private int numberOfPrimitive;
    private int numberOfFinal;
    private Resource primitiveResource;
    private Resource finalResource;

    public ConvertedBuilding(int x, int y, Government government, int hp, String type, String name,
                             int numberOfPrimitive, int NumberOfFinal, Resource primitiveResource, Resource finalResource
    , int maxHp, HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, maxHp, resource);
        this.numberOfPrimitive = numberOfPrimitive;
        this.numberOfFinal = NumberOfFinal;
        this.primitiveResource = primitiveResource;
        this.finalResource = finalResource;
    }

    public static ConvertedBuilding makeConvertedBuildingByName(String name, int x, int y, Government government, int flag) {
        HashMap<Resource, Integer> resource = new HashMap<>();
        if (name.equals("mill")) {
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ConvertedBuilding mill = new ConvertedBuilding(x, y, government, 300,
                        "food processing building"
                        , name, 1, 20, Resource.WHEAT, Resource.FLOUR, 300,
                        resource);
                mill.setWorkerDataBase(JobsName.MILLER.getJobsName(), 3);
                if (government.getUnWorkedPeople().size() >= 3) {
                    for (int i = 0; i<3; i++) {
                        People people1 = government.getUnWorkedPeople().get(0);
                        Building.changePeople(people1, JobsName.MILLER);
                        Game.getMapInGame().getMap()[y][x].addPeople(people1);
                    }
                }
                return mill;
            }
        }
        if (name.equals("armourer")) {
            resource.put(Resource.WOOD, 20);
            if ((government.getWealth() > 100 && government.hasEnoughResources(resource)) || flag == 1) {
                ConvertedBuilding armourer = new ConvertedBuilding(x, y, government, 600, "weapon", name,
                        6, 1, Resource.IRON, Resource.ARMOUR, 600, resource);
                armourer.setWorkerDataBase(JobsName.ARMOUR.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.ARMOUR);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                government.setWealth(government.getWealth() - 100);
                return armourer;
            }
        }
        if (name.equals("blacksmith")) {
            resource.put(Resource.WOOD, 20);
            if ((government.getWealth() > 100 && government.hasEnoughResources(resource))|| flag == 1) {
                ConvertedBuilding blacksmith = new ConvertedBuilding(x, y, government, 700, "weapon", name,
                        4, 1, Resource.IRON, Resource.SWORD, 700, resource);
                blacksmith.setWorkerDataBase(JobsName.BLACKSMITH.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.BLACKSMITH);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                government.setWealth(government.getWealth() - 100);
                return blacksmith;
            }
        }
        if (name.equals("fletcher")) {
            resource.put(Resource.WOOD, 20);
            if ((government.getWealth() > 100 && government.hasEnoughResources(resource)) || flag == 1) {
                ConvertedBuilding fletcher = new ConvertedBuilding(x, y, government, 600, "weapon",
                        name, 5, 1, Resource.WOOD, Resource.ARCHER, 600, resource);
                fletcher.setWorkerDataBase(JobsName.FLETCHER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.FLETCHER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                government.setWealth(government.getWealth() - 100);
                return fletcher;
            }
        }
        if (name.equals("pole turner")) {
            resource.put(Resource.WOOD, 20);
            if ((government.getWealth() > 100 && government.hasEnoughResources(resource)) || flag == 1) {
                ConvertedBuilding poleTurner = new ConvertedBuilding(x, y, government, 500, "weapon",
                        name, 10, 1, Resource.WOOD, Resource.SPEAR, 500, resource);
                poleTurner.setWorkerDataBase(JobsName.POLE_TURNER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.POLE_TURNER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                government.setWealth(government.getWealth() - 100);
                return poleTurner;
            }
        }
        if (name.equals("bakery")) {
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ConvertedBuilding bakery = new ConvertedBuilding(x, y, government, 200,
                        "food processing building",
                        name, 10, 1, Resource.FLOUR, Resource.BREAD, 200, resource);
                bakery.setWorkerDataBase(JobsName.BAKER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.BAKER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return bakery;
            }
        }
        if (name.equals("brewery")) {
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ConvertedBuilding brewery = new ConvertedBuilding(x, y, government, 350,
                        "food processing building",
                        name, 1, 30, Resource.HOP, Resource.BEAR, 350, resource);
                brewery.setWorkerDataBase(JobsName.BREWER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.BREWER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return brewery;
            }
        }
        return null;
    }

    public void convertResourceToAnotherWithRate() {
        HashMap <Resource, Integer> resourceThatAssume = new HashMap<>();
        resourceThatAssume.put(primitiveResource, numberOfPrimitive);
        if (this.getGovernment().hasEnoughResources(resourceThatAssume)) {
            this.getGovernment().removeFromResources(primitiveResource, numberOfPrimitive);
            this.getGovernment().removeResourceFromStockPile(primitiveResource, numberOfPrimitive);
            this.getGovernment().addToResources(finalResource, numberOfFinal);
            this.getGovernment().addResourceToStockPile(finalResource, numberOfFinal);
        }
    }

    public int getNumberOfPrimitive() {
        return numberOfPrimitive;
    }

    public int getNumberOfFinal() {
        return numberOfFinal;
    }
}
