package model.government.building;

import model.Game;
import model.government.Government;
import model.government.people.People;
import model.government.people.units.Units;
import model.government.people.units.UnitsName;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;

import java.util.HashMap;

public class SiegeTent extends Building{
    public SiegeTent(int x, int y, Government government, int hp, String type, String name,
                     HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, 40, resource);
    }

    public static SiegeTent makeSiegeTentByName(String name, int x, int y, Government government, int flag) {
        HashMap<Resource, Integer> resource = new HashMap<>();
        if (name.equals("siege tent")) {
            SiegeTent siegeTent = new SiegeTent(x ,y , government, 40, "castle building" , name, resource);
            siegeTent.setWorkerDataBase(UnitsName.ENGINEER.getName(), 1);
            if (government.getUnWorkedPeople().size() >= 1) {
                Units units = new Units(x, y, UnitsName.ENGINEER, government.getUser());
                People people = government.getUnWorkedPeople().get(0);
                government.removeUnWorkedPeople(people);
                government.addToPeople(units);
                Game.getMapInGame().getMap()[y][x].addPeople(units);
            }
            return siegeTent;
        }
        return null;
    }
}
