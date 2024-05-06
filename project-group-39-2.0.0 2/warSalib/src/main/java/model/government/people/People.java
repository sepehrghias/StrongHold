package model.government.people;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.government.people.workingpersons.JobsName;
import model.user.User;
import view.StartGame;

public class People extends Rectangle {
    protected int xLocation;
    protected int yLocation;
    protected JobsName jobsName;

    protected User ownerPerson;

    protected int toGoX;

    protected int toGoY;


    public People(int xLocation,int yLocation,JobsName jobsName,User ownerPerson)
    {   this.xLocation=xLocation;
        this.yLocation=yLocation;
        this.jobsName=jobsName;
        this.ownerPerson=ownerPerson;
        this.toGoX=xLocation;
        this.toGoY=yLocation;
        this.setHeight(25);
        this.setWidth(25);
        ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource("/images/workerDown.png").toExternalForm()));
        this.setFill(humanImage);
        Game.getMapInGame().getMap()[yLocation][xLocation].getChildren().add(this);
    }

    public People() {
    }

    public int getyLocation() {
        return yLocation;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setJobsName(JobsName jobsName) {
        this.jobsName = jobsName;
    }

    public User getOwnerPerson() {
        return ownerPerson;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public void setToGoX(int toGoX) {
        this.toGoX = toGoX;
    }

    public void setToGoY(int toGoY) {
        this.toGoY = toGoY;
    }

    public int getToGoX() {
        return toGoX;
    }

    public int getToGoY() {
        return toGoY;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public JobsName getJobsName() {
        return jobsName;
    }
}
