package model.government.building;

import com.sun.jdi.request.BreakpointRequest;
import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.units.Units;
import model.government.people.units.UnitsName;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.HashMap;

public class ProductiveBuilding extends Building {
    private int rate;
    private Resource resourceThatMake;

    public ProductiveBuilding(int x, int y, Government government, int hp, String type, String name, int rate,
                              Resource resourceThatMake, HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, hp, resource);
        this.rate = rate;
        this.resourceThatMake = resourceThatMake;
    }

    public static ProductiveBuilding makeProductiveBuildingByName(String name, int x, int y, Government government,
                                                                  int flag) {
        HashMap<Resource, Integer> resource = new HashMap<>();
        if (name.equals("wood cutter")) {
            resource.put(Resource.WOOD, 3);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ProductiveBuilding woodCutter = new ProductiveBuilding(x, y, government, 300, "industry",
                        "wood cutter", 30, Resource.WOOD, resource);
                woodCutter.setWorkerDataBase(JobsName.WOODCUTTER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.WOODCUTTER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return woodCutter;
            }
        }
        if (name.equals("oil smelter")) {
            resource.put(Resource.IRON, 10);
            if ((government.getWealth() > 100 && government.hasEnoughResources(resource)) || flag == 1) {
                ProductiveBuilding oilSmelter = new ProductiveBuilding(x, y, government, 600, "industry",
                        "oil smelter", 50, Resource.OIL, resource);
                oilSmelter.setWorkerDataBase(UnitsName.ENGINEER.getName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    Units units = new Units(x, y, UnitsName.ENGINEER, government.getUser());
                    People people = government.getUnWorkedPeople().get(0);
                    government.removeUnWorkedPeople(people);
                    government.addToPeople(units);
                    Game.getMapInGame().getMap()[y][x].addPeople(units);
                }
                government.setWealth(government.getWealth() - 100);
                return oilSmelter;
            }
        }
        if (name.equals("stable")) {
            resource.put(Resource.WOOD, 20);
            if ((government.getWealth() > 400 && government.hasEnoughResources(resource)) || flag == 1) {
                ProductiveBuilding stable = new ProductiveBuilding(x, y, government, 400, "castle building",
                        "stable", 4, Resource.HORSE, resource);
                government.setWealth(government.getWealth() - 400);
                return stable;
            }
        }
        if (name.equals("apple garden")) {
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ProductiveBuilding appleGarden = new ProductiveBuilding(x, y, government, 300, "farm building"
                        , "apple garden", 100, Resource.APPLE, resource);
                appleGarden.setWorkerDataBase(JobsName.APPLE_FARMER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.APPLE_FARMER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return appleGarden;
            }
        }
        if (name.equals("diary product")) {
            resource.put(Resource.WOOD, 10);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ProductiveBuilding diaryProduct = new ProductiveBuilding(x, y, government, 200, "farm building"
                        , "diary product", 30, Resource.CHEESE, resource);
                diaryProduct.setWorkerDataBase(JobsName.DIARY_PRODUCER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.DIARY_PRODUCER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return diaryProduct;
            }
        }
        if (name.equals("barley field")) {
            resource.put(Resource.WOOD, 15);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ProductiveBuilding barleyField = new ProductiveBuilding(x, y, government, 100, "farm building"
                        , "barley field", 80, Resource.HOP, resource);
                barleyField.setWorkerDataBase(JobsName.HOP_FARMER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.HOP_FARMER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return barleyField;
            }
        }
        if (name.equals("hunting post")) {
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ProductiveBuilding huntingPost = new ProductiveBuilding(x, y, government, 300, "farm building"
                        , "hunting post", 20, Resource.MEAT, resource);
                huntingPost.setWorkerDataBase(JobsName.HUNTER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.HUNTER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return huntingPost;
            }
        }
        if (name.equals("wheat field")) {
            resource.put(Resource.WOOD, 15);
            if (government.hasEnoughResources(resource) || flag == 1) {
                ProductiveBuilding wheatField = new ProductiveBuilding(x, y, government, 100, "farm building"
                        , "wheat field", 70, Resource.WHEAT, resource);
                wheatField.setWorkerDataBase(JobsName.WHEAT_FARMER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.WHEAT_FARMER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return wheatField;
            }
        }
        return null;
    }

    public void makeResourceWithRate() {
        this.getGovernment().addResourceToStockPile(resourceThatMake, rate);
        this.getGovernment().addToResources(resourceThatMake, rate);
    }

    public int getRate() {
        return rate;
    }

    public Resource getResourceThatMake() {
        return resourceThatMake;
    }
}
