package model.government.building;

import model.government.Government;
import model.government.resource.Resource;

import java.util.HashMap;

public class Keep extends Building{
    public Keep(int x, int y, Government government, String name) {
        super(x, y, government, 700, "castle building", name, 700, null);
    }
}
