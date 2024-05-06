package model.government.people.units;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.government.resource.Resource;
import model.user.User;
import model.wartool.wartoolenum;
import view.StartGame;

public class Combat extends Units{
    //  private wartoolenum wartool;

    private Resource wartool;
    private wartoolenum portableProtection;
    public Combat(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        super(xLocation, yLocation, unitsName, ownerPerson);
        if(unitsName.getName().equals("pikeman")){
            this.wartool=Resource.SPEAR;
            this.portableProtection=null;
        }
        else if(unitsName.getName().equals("swordsmen")){
            this.wartool=null;
            this.portableProtection=null;
        }
        else if(unitsName.getName().equals("knight")){
            this.hasHorse=true;
            this.portableProtection=null;
        }
        else if(unitsName.getName().equals("blackmonk")){
            this.wartool=Resource.STICK;
            this.portableProtection=null;
        }
        else if(unitsName.getName().equals("slaves")){
            this.wartool=Resource.TORCH;
            this.portableProtection=null;
        }
        else if(unitsName.getName().equals("arabianswordsmen")){
            this.wartool=null;
            this.portableProtection=null;
        }
    }

    public Resource getWartool() {
        return wartool;
    }

    public void setPortableProtection(wartoolenum portableProtection) {
        this.portableProtection = portableProtection;
    }

    public wartoolenum getPortableProtection() {
        return portableProtection;
    }
}
