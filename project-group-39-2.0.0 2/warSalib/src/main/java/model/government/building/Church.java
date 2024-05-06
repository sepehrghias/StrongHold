package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.people.workingpersons.WorkingPerson;
import model.government.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Church extends Building {
    private final int addToPopularity = 2;
    private ArrayList<People> people;

    public Church(int x, int y, Government government, int hp, String name, int maxHp, HashMap<Resource, Integer>
            resource) {
        super(x, y, government, hp, "town building", name, maxHp, resource);
        improvePopularity(government);
    }

    public static Church makeChurchByName(String name, int x, int y, Government government, int flag) {
        HashMap<Resource, Integer> resource = new HashMap<>();
        if (name.equals("church")) {
            if (government.getWealth() > 250 || flag == 1) {
                Church church = new Church(x, y, government, 600, name, 600, resource);
                government.setWealth(government.getWealth() - 250);
                return church;
            }
        }
        if (name.equals("cathedral")) {
            if (government.getWealth() > 1000 || flag == 1) {
                Church cathedral = new Church(x, y, government, 1000, name, 1000, resource);
                if (government.getUnWorkedPeople().get(0) != null) {
                    People people1 = government.getUnWorkedPeople().get(0);
                    Building.changePeople(people1, JobsName.PRIEST);
                    Game.getMapInGame().getMap()[y][x].addPeople(people1);
                }
                government.setWealth(government.getWealth() - 1000);
                return cathedral;
            }
        }
        return null;
    }

    private void improvePopularity(Government government) {
        government.setPopularity(government.getPopularity() + 2);
        government.setReligionEffect(government.getReligionEffect() + 2);
    }

    public void changeNormalToFightingMonk(People people) {

    }
}
