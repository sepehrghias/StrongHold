package model.government.building;

import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;

import java.util.HashMap;

public class PitchDitch extends Building{
    private boolean isOnFire;

    public PitchDitch(int x, int y, Government government, HashMap<Resource, Integer> resource) {
        super(x, y, government, 0, "castle building", "pitch ditch", 0, resource);
        this.isOnFire = false;
    }

    public static PitchDitch makePitchDitchByName(String name, int x , int y, Government government, int flag) {
        HashMap<Resource, Integer> resource = new HashMap<>();
        if (name.equals("pitch ditch")) {
            PitchDitch pitchDitch = new PitchDitch(x, y, government,resource);
            return pitchDitch;
        }
        return null;
    }

    public boolean isOnFire() {
        return isOnFire;
    }

    public void setOnFire(boolean onFire) {
        isOnFire = onFire;
    }

    public int damage() {
        return 0;
    }
}
