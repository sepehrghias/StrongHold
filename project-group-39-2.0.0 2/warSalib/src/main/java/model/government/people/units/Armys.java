package model.government.people.units;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.user.User;
import view.StartGame;

import java.util.ArrayList;

public class Armys extends Units {
    public ArrayList<SpecialWorks> specialWorks = new ArrayList<>();

    public Armys(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        super(xLocation, yLocation, unitsName,ownerPerson);
        if (unitsName.getName().equals("sperman")) {
            specialWorks.add(SpecialWorks.CANDIGMOAT);
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        } else if (unitsName.getName().equals("maceman")) {
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        }
        else if(unitsName.getName().equals("tunneler")){
            specialWorks.add(SpecialWorks.INVISIBILITY);
        }
        else if(unitsName.getName().equals("ladderman")){
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);
        }
        else if(unitsName.getName().equals("assassin")){
            specialWorks.add(SpecialWorks.INVISIBILITY);
            specialWorks.add(SpecialWorks.CANCLIMBLADDER);

        }
    }

    public ArrayList<SpecialWorks> getSpecialWorks() {
        return specialWorks;
    }
}
