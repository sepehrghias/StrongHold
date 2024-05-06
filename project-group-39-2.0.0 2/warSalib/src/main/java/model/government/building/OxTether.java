package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.HashMap;

public class OxTether extends Building{
    private final Resource resource = Resource.STONE;
    private final int rate = 12;

    public OxTether(int x, int y, Government government, int hp, HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, "industry", "ox tether", hp, resource);
    }

    public static OxTether makeOxTetherByName(String name, int x , int y, Government government, int flag) {
        if (name.equals("ox tether")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 5);
            if (government.hasEnoughResources(resource) || flag == 1) {
                OxTether oxTether = new OxTether(x, y, government, 150, resource);
                oxTether.setWorkerDataBase(JobsName.OX_TETHER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.OX_TETHER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return oxTether;
            }
        }
        return null;
    }
    public void caryStoneWithRate() {
        if (hasQuarry(this.getGovernment())){
            this.getGovernment().addResourceToStockPile(Resource.STONE, rate);
            this.getGovernment().addToResources(Resource.STONE, rate);
        }
    }

    public boolean hasQuarry (Government government) {
        for (Building building : government.getBuildings()) {
            if (building.getName().equals("quarry"))
                return true;
        }
        return false;
    }
    public Resource getResource() {
        return resource;
    }

    public int getRate() {
        return rate;
    }
}
