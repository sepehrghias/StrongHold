package model.wartool;

import model.user.User;

public class CataPult {
    private User user;
    private int x;
    private int y;
    private final int Efficiently=100;
    private final int board=80;

    public CataPult(int x, int y,User user) {
        this.x = x;
        this.y = y;
        this.user=user;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getEfficiently() {
        return Efficiently;
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
