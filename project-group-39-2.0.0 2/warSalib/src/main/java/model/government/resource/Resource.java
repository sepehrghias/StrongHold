package model.government.resource;

public enum Resource {
    STONE("stone",TypeOfResource.INDUSTRY , 5), IRON("iron",TypeOfResource.INDUSTRY, 10),
    PITCH("pitch",TypeOfResource.INDUSTRY, 2), WHEAT("wheat",TypeOfResource.FOOD, 5),
    FLOUR("flour" ,TypeOfResource.FOOD , 3), BREAD("bread" ,TypeOfResource.FOOD, 3),
    HOP("hop" ,TypeOfResource.FOOD, 4), BEAR( "bear" ,TypeOfResource.FOOD, 10),
    ARMOUR( "armour" ,TypeOfResource.WEAPON, 90), SWORD( "sword" ,TypeOfResource.WEAPON, 70),
    ARCHER( "archer",TypeOfResource.WEAPON, 90), SPEAR( "spear", TypeOfResource.WEAPON, 50),

    WOOD("wood"  ,TypeOfResource.INDUSTRY, 3), OIL( "oil" ,TypeOfResource.INDUSTRY, 15),
    HORSE( "horse" ,TypeOfResource.INDUSTRY, 100), APPLE(  "apple" ,TypeOfResource.FOOD, 3),

    ARROW("arrow", TypeOfResource.WEAPON, 70), FIRECRACKER("firecracker", TypeOfResource.WEAPON,
            100),

     TORCH("torch", TypeOfResource.WEAPON, 80),STICK("stick", TypeOfResource.WEAPON, 60),
    CHEESE( "cheese" ,TypeOfResource.FOOD, 8), MEAT(  "meat" ,TypeOfResource.FOOD, 20),
    GOLD(  "gold" ,TypeOfResource.INDUSTRY, 30),
    COIN(  "coin" ,TypeOfResource.INDUSTRY, 1), COW(  "cow" ,TypeOfResource.ANIMAL, 70);
    public enum TypeOfResource {
        INDUSTRY, WEAPON, FOOD,ANIMAL
    }

    private String name;
    TypeOfResource typeOfResource;

    private int cost;

    Resource(String name ,TypeOfResource typeOfResource, int cost) {
        this.name = name;
        this.typeOfResource = typeOfResource;
        this.cost = cost;
    }

    public TypeOfResource getTypeOfResource() {
        return typeOfResource;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}