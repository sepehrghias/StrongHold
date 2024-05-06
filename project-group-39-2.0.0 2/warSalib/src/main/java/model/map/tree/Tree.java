package model.map.tree;

public enum Tree {
    DATE("date tree"),
    CHERRY("cherry tree"),
    OLIVE("olive tree"),
    Desert("desert shrub"),
    COCONUT("coconut tree");

    private String type;

    Tree(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
