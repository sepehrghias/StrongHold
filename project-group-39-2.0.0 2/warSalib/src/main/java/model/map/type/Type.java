package model.map.type;

import javafx.scene.image.Image;

public enum Type {
    GROUND(Area.BROWNAREA, true, "G", "ground", new Image(Type.class.getResource("/images/ground.jpg").toExternalForm())),
    GROUND_WITH_GRAVEL(Area.BROWNAREA, true,"Gg", "ground with gravel", new Image(Type.class.getResource("/images/Mountain1.png").toExternalForm()))

    ,SLATE(Area.BROWNAREA, true, "Sl" , "slate", new Image(Type.class.getResource("/images/Mountain1.png").toExternalForm())),

    STONE(Area.BROWNAREA, false ,"St", "stone", new Image(Type.class.getResource("/images/stone.jpg").toExternalForm())),
    IRON_GROUND(Area.BROWNAREA, true, "Ig", "iron ground", new Image(Type.class.getResource("/images/ironGround.jpg").toExternalForm())),
    GRASS(Area.BROWNAREA, true, "Gr", "grass", new Image(Type.class.getResource("/images/grass.jpg").toExternalForm())),
    GRASSLAND(Area.BROWNAREA, true, "Gl", "grass land", new Image(Type.class.getResource("/images/grassland2.jpg").toExternalForm())),
    DENSE_GRASSLAND(Area.BROWNAREA, true, "Dg", "dense grass land", new Image(Type.class.getResource("/images/grass.jpg").toExternalForm())) ,
    OIL(Area.BLUEAREA, true, "O", "oil", new Image(Type.class.getResource("/images/ground.jpg").toExternalForm())),
    PLAIN(Area.BLUEAREA, true, "P", "plain", new Image(Type.class.getResource("/images/plain.jpg").toExternalForm())),
    SHALLOW_WATER(Area.BLUEAREA, true, "Sw", "shallow water", new Image(Type.class.getResource("/images/landstone.png").toExternalForm())),
    SEA(Area.BLUEAREA, false, "Se", "sea", new Image(Type.class.getResource("/images/sea_tile.jpg").toExternalForm()))

    ,BEACH(Area.BLUEAREA, true, "Be", "beach",new Image(Type.class.getResource("/images/Plain2.jpg").toExternalForm())),
    RIVER(Area.BLUEAREA, false, "R", "river", new Image(Type.class.getResource("/images/sea.jpg").toExternalForm())),
    BIG_POND(Area.BLUEAREA, false, "Bp", "big pound", new Image(Type.class.getResource("/images/gulf_tile.jpg").toExternalForm())),
    SMALL_POND(Area.BLUEAREA, false, "Sp", "small pound", new Image(Type.class.getResource("/images/gulf_tile.jpg").toExternalForm()));

    public enum Area {
        BLUEAREA,BROWNAREA;
    }

    private Area area;
    private String name;
    private boolean permeability;
    private String ground;

    private Image image ;

    Type(Area area, boolean permeability , String name, String ground, Image image) {
        this.area = area;
        this.permeability = permeability;
        this.name = name;
        this.ground = ground;
        this.image = image;
    }

    public Area getArea() {
        return area;
    }

    public boolean getPermeability() {
        return permeability;
    }

    public String getName() {
        return name;
    }

    public String getGround() {
        return ground;
    }

    public Image getImage() {
        return image;
    }
}
