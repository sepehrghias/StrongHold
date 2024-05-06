package model.government.people.units;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.user.User;
import view.StartGame;

public class Engineer extends Units{
    public Engineer(int xLocation, int yLocation, UnitsName unitsName, User ownerPerson) {
        super(xLocation, yLocation, unitsName, ownerPerson);
        ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource("/images/Units/engineer.png").toExternalForm()));
        this.setFill(humanImage);
    }

    public void setHasWork(boolean b) {
    }

    public boolean isHasWork() {
        return false;
    }
}
