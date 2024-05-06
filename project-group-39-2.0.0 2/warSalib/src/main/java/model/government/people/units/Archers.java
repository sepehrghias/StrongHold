package model.government.people.units;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.government.resource.Resource;
import model.user.User;
import view.StartGame;

import java.util.ArrayList;

public class Archers extends Units{
    private int precision;
    private int arrowRadius;
    private int fatality;
    private Resource wartool;

    private ArrayList<Resource> inventories;
    private boolean arrowInMove;
    public Archers(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        super(xLocation, yLocation, unitsName,ownerPerson);
        if(unitsName.getName().equals("archer")){
            this.precision=60;
            this.arrowRadius=60;
            this.fatality=50;
            this.inventories=new ArrayList<>();
            this.wartool= Resource.ARROW;
            this.arrowInMove=false;
        }
        else if(unitsName.getName().equals("crossbowmen")){
            this.precision=80;
            this.arrowRadius=40;
            this.fatality=70;
            this.wartool=Resource.ARROW;
            this.arrowInMove=false;
            this.hasHorse=false;
        }
        else if(unitsName.getName().equals("archerbow")){
            this.precision=60;
            this.arrowRadius=60;
            this.fatality=50;
            this.wartool= Resource.ARROW;
            this.arrowInMove=false;
            this.hasHorse=false;
        }
        else if(unitsName.getName().equals("slingers")){
            this.precision=60;
            this.arrowRadius=40;
            this.fatality=70;
            this.wartool= Resource.STONE;
            this.arrowInMove=false;
            this.hasHorse=false;

        }
        else if(unitsName.getName().equals("horsearchers")){
            this.precision=60;
            this.arrowRadius=60;
            this.fatality=50;
            this.wartool= Resource.ARROW;
            this.arrowInMove=true;
            this.hasHorse=true;
        }
        else if(unitsName.getName().equals("firethowers")){
            this.precision=80;
            this.arrowRadius=60;
            this.fatality=80;
            this.wartool= Resource.FIRECRACKER;
            this.arrowInMove=false;
            this.hasHorse=false;
        }
    }

    public ArrayList<Resource> getInventories() {
        return inventories;
    }

    public int getPrecision() {
        return precision;
    }

    public int getArrowRadius() {
        return arrowRadius;
    }

    public int getFatality() {
        return fatality;
    }

    public Resource getWartool() {
        return wartool;
    }
}
