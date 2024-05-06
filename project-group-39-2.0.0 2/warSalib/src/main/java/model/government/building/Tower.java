package model.government.building;

import model.government.Government;
import model.government.people.People;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Tower extends Building{
    private int defendRange;
    private final double fireRange = 1.2;

    private final int maxPeople=10;

    ArrayList<People> peopleOfTower;

    public Tower(int x, int y, Government government, int hp, String type, String name, int defendRange,
                 HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, hp, resource);
        this.defendRange = defendRange;
    }

    public static Tower makeTowerByName(String name, int x, int y, Government government, int flag) {
        HashMap<Resource, Integer> resource= new HashMap<>();
        if (name.equals("lookout tower")) {
            resource.put(Resource.STONE, 10);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Tower lookoutTower = new Tower(x, y, government, 1000, "castle building", name,
                        1000, resource);
                return lookoutTower;
            }
            else {
                System.out.println("we dont have enough resource to make it");
            }
        }
        if (name.equals("perimeter tower")) {
            resource.put(Resource.STONE, 10);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Tower perimeterTower = new Tower(x, y, government, 800, "castle building", name,
                        400, resource);
                return perimeterTower;
            }
            else {
                System.out.println("we dont have enough resource to make it");
            }
        }
        if (name.equals("defensive tower")) {
            resource.put(Resource.STONE, 15);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Tower defensiveTower = new Tower(x, y, government, 1200, "castle building", name,
                        400, resource);
                return defensiveTower;
            }
            else {
                System.out.println("we dont have enough resource to make it");
            }
        }
        if (name.equals("square tower")) {
            resource.put(Resource.STONE, 35);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Tower squareTower = new Tower(x, y, government, 1500, "castle building", name,
                        800, resource);
                return squareTower;
            }
            else {
                System.out.println("we dont have enough resource to make it");
            }
        }
        if (name.equals("circle tower")) {
            resource.put(Resource.STONE, 40);
            if (government.hasEnoughResources(resource) || flag == 1) {
                Tower circleTower = new Tower(x, y, government, 2000, "castle building", name,
                        900, resource);
                return circleTower;
            }
            else {
                System.out.println("we dont have enough resource to make it");
            }
        }
        return null;
    }

    public int addTowerDefending () {
        return 0;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public double getFireRange() {
        return fireRange;
    }

    public void addPeople(People people) {
        peopleOfTower.add(people);
    }
}
