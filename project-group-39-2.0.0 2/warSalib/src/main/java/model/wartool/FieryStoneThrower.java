package model.wartool;

import model.government.Government;
import model.government.resource.Resource;

public class FieryStoneThrower {

    private Government government;
    private int x;
    private int y;

    private final int board=100;

    public FieryStoneThrower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBoard() {
        return board;
    }
}
