package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;
import model.map.type.Type;

import java.util.HashMap;

public class MineBuilding extends Building {
    //change Group by GroupOfBuilding.Mine
    private int rate;
    private Resource resource;

    public MineBuilding(int x, int y, Government government, int hp, String name, int rate, Resource resource,
                        HashMap<Resource, Integer> resources) {
        super(x, y, government, hp, "industry", name, hp, resources);
        this.rate = rate;
        this.resource = resource;
    }

    public static MineBuilding makeMineBuildingByName(String name , int x, int y, Government government, int flag) {
        HashMap<Resource, Integer> resource= new HashMap<>();
        if (name.equals("quarry") ) {
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource) || flag == 1) {
                MineBuilding mineBuilding = new MineBuilding(x, y, government, 1000, name, 200, Resource.STONE
                ,resource);
                mineBuilding.setWorkerDataBase(JobsName.QUARRY_WORKER.getJobsName(), 3);
                if (government.getUnWorkedPeople().size() >= 3) {
                    for (int i = 0; i < 3; i++) {
                        People people1 = government.getUnWorkedPeople().get(0);
                        Building.changePeople(people1, JobsName.QUARRY_WORKER);
                        Game.getMapInGame().getMap()[y][x].addPeople(people1);
                    }
                }
                return mineBuilding;
            }
        }
        if (name.equals("iron mine") ) {
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource) || flag == 1) {
                MineBuilding mineBuilding = new MineBuilding(x, y, government, 1100, name, 40, Resource.IRON,
                        resource);
                mineBuilding.setWorkerDataBase(JobsName.IRON_MINE_WORKER.getJobsName(), 2);
                if (government.getUnWorkedPeople().size() >= 2) {
                    for (int i = 0; i < 2; i++) {
                        People people1 = government.getUnWorkedPeople().get(0);
                        Building.changePeople(people1, JobsName.IRON_MINE_WORKER);
                        Game.getMapInGame().getMap()[y][x].addPeople(people1);
                    }
                }
                return mineBuilding;
            }
        }
        if (name.equals("pitch rig") ) {
            resource.put(Resource.WOOD, 20);
            if (government.hasEnoughResources(resource) || flag == 1) {
                MineBuilding mineBuilding = new MineBuilding(x, y, government, 800, name, 50, Resource.PITCH,
                        resource);
               mineBuilding.setWorkerDataBase(JobsName.PITCH_RIG_WORKER.getJobsName(), 1);
                if (government.getUnWorkedPeople().size() >= 1) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.PITCH_RIG_WORKER);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                return mineBuilding;
            }
        }
        return null;
    }


    public void makeResourceWithRate() {
        if (this.getName().equals("quarry")) {

        }
        else {
            this.getGovernment().addToResources(resource, rate);
            this.getGovernment().addResourceToStockPile(resource, rate);
        }
    }

}
