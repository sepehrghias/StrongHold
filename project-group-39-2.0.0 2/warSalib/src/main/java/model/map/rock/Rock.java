package model.map.rock;

import model.map.Tile;
import model.map.type.Type;

public class Rock {

    private String direction;
    private Type type;

    public Rock(String direction, Type type) {
        this.direction = direction;
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
