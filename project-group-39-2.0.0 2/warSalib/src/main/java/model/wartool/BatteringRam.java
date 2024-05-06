package model.wartool;

import model.government.Government;
import model.government.people.units.Units;
import model.user.User;

public class BatteringRam {
    private Government government;

    private User user;
    private int x;
    private int y;

    private final int speed=2;

    public BatteringRam(Government government, int x, int y) {
        this.government = government;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Government getGovernment() {
        return government;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
