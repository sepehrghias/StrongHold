package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.HashMap;

public class KillingPit extends Building{
    private final int damage = 300;

    public KillingPit(int x, int y, Government government, int hp, String type, String name,
                      HashMap<Resource, Integer> resource) {
        super(x, y, government, hp, type, name, 600, resource);
    }

    public static KillingPit makeKillingPitByName(String name, int x ,int y, Government government, int flag) {
        if (name.equals("killing pit")) {
            HashMap<Resource, Integer> resource= new HashMap<>();
            resource.put(Resource.WOOD, 6);
            if (government.hasEnoughResources(resource) || flag == 1) {
                KillingPit killingPit = new KillingPit(x, y, government, 600, "castle building", name,
                        resource);
                return killingPit;
            }
        }
        return null;
    }

    public int getDamage () {
        return damage;
    }

    public boolean IsVisibleBuilding() {
        return false;
    }


}
