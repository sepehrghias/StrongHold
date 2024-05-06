package model.wartool;

import model.user.User;

public class FixedCatapult {
    private User user;
    private final int x;
    private  final int y;
    private final int efficiently=200;
    private final int board=200;

    public FixedCatapult(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getEfficiently() {
        return efficiently;
    }

    public int getBoard() {
        return board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
