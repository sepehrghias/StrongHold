package model.government.building;

import model.Game;
import model.government.Government;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Inn extends Building{
    private final Resource resource = Resource.HOP;
    private int rate;
    private int popularityRate;
    private String wineUsage;
    //resource just hop change resource to hop in constructor

    ArrayList<People> peopleOfInn;

    public Inn(int x, int y, Government government, int hp, String type, String name,
               HashMap<Resource, Integer> resource, int rate) {
        super(x, y, government, hp, type, name, 500, resource);
        this.rate = rate;
    }

    public static Inn makeInnByName(String name, int x , int y, Government government, int flag) {
        if (name.equals("inn")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 20);
            if ((government.getWealth() > 100 && government.hasEnoughResources(resource)) || flag == 1) {
                Inn inn = new Inn(x, y, government, 500, "food processing building", name, resource, 20);
                inn.setWorkerDataBase(JobsName.DRINK_SERVER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.DRINK_SERVER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return inn;
            }
        }
        return null;
    }

    public void improvePopularityBaseRate() {
        this.getGovernment().setPopularity(this.getGovernment().getPopularity() + 1);
        this.getGovernment().setFoodEffect(this.getGovernment().getFoodEffect() + 1);
    }

    public void serveBeerWithRate() {
        HashMap<Resource, Integer> ratedResource = new HashMap<>();
        ratedResource.put(Resource.BEAR, rate);
        if (this.getGovernment().hasEnoughResources(ratedResource)) {
            this.getGovernment().removeFromResources(Resource.BEAR, rate);
            this.getGovernment().removeResourceFromStockPile(Resource.BEAR, rate);
            improvePopularityBaseRate();
        }
    }

    public void addPeople(People people) {
        peopleOfInn.add(people);
    }

}
