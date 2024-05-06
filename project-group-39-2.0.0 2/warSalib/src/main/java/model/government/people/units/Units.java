package model.government.people.units;

import control.MapControl;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.Game;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.user.User;
import model.wartool.wartoolenum;
import view.StartGame;

public class Units extends People  {
    protected UnitsName unitsName;
    protected int hitPoint;
    protected boolean hasHorse;

    protected int patrolFromX;

    protected int patrolFromY;

    protected int patrolToX;

    protected int patrolToY;
    protected State state;

    protected int efficientAttackingPower;


    public Units(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        this.jobsName = null;
        this.setHeight(40);
        this.setWidth(40);
        this.unitsName = unitsName;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.hitPoint = 100;
        this.state = State.STANDING;
        this.hasHorse = false;
        this.ownerPerson = ownerPerson;
        efficientAttackingPower=unitsName.getAttackingPower();
        this.patrolFromX=-1;
        this.patrolFromY=-1;
        this.patrolToX=-1;
        this.patrolToY=-1;
        this.toGoX=xLocation;
        this.toGoY=yLocation;
        Game.getMapInGame().getMap()[xLocation][yLocation].getChildren().add(this);
        Game.getMapInGame().getMap()[xLocation][yLocation].getPeopleOnTile().add(this);
        String details= MapControl.showDetails(yLocation,xLocation);
        Game.getMapInGame().getMap()[yLocation][xLocation].getTooltip().setText(details);
        String imagePath=unitsName.getName()+1;
        String path="/images/Units/"+imagePath+".png";
       // System.out.println(path);
     ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource(path).toExternalForm()));
        this.setFill(humanImage);
    }


    public int getHitPoint() {
        return hitPoint;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public static Units makeUnit(int x, int y, UnitsName unitsName, User user) {
        if (unitsName.getUnitsType().equals(UnitsType.ARCHER)) {
            Archers archers = new Archers(x, y, unitsName, user);

            return archers;
        } else if (unitsName.getUnitsType().equals(UnitsType.ARMY)) {
            Armys armys = new Armys(x, y, unitsName, user);
            return armys;
        } else if (unitsName.getUnitsType().equals(UnitsType.COMBAT)) {
            Combat combat = new Combat(x, y, unitsName, user);
            return combat;
        }
        return null;
    }

    public UnitsName getUnitsName() {
        return unitsName;
    }

    public void changeHitPoint(int x) {
        hitPoint += x;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void changeEfficientAttackingPower(int x){
        efficientAttackingPower+=x;
    }

    public int getEfficientAttackingPower() {
        return efficientAttackingPower;
    }

    public void setEfficientAttackingPower(int efficientAttackingPower) {
        this.efficientAttackingPower = efficientAttackingPower;
    }

    public void setPatrolFromX(int patrolFromX) {
        this.patrolFromX = patrolFromX;
    }

    public void setPatrolFromY(int patrolFromY) {
        this.patrolFromY = patrolFromY;
    }

    public void setPatrolToX(int patrolToX) {
        this.patrolToX = patrolToX;
    }

    public void setPatrolToY(int patrolToY) {
        this.patrolToY = patrolToY;
    }

    public int getPatrolFromX() {
        return patrolFromX;
    }

    public int getPatrolFromY() {
        return patrolFromY;
    }

    public int getPatrolToX() {
        return patrolToX;
    }

    public int getPatrolToY() {
        return patrolToY;
    }

    public State getState() {
        return state;
    }
}
