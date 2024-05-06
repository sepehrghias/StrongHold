package model.government.people.units;
import model.wartool.wartoolenum;

public enum UnitsName {
    ARCHER("archer", 75, 25, 25,  UnitsType.ARCHER, false, 40
            , "barrack"),

    CROSSBOWMEN("crossbowmen", 20, 25,  100, UnitsType.ARCHER, false
            , 20, "barrack"),

    SPEARMAN("spearman", 60, 50, 15,  UnitsType.ARMY, false, 24
            , "barrack"),

    PIKE_MAN("pikeman", 20, 50, 75,  UnitsType.COMBAT, true, 45
            , "barrack"),

    MACE_MAN("maceman", 50, 75, 50, UnitsType.ARMY, false, 52
            , "barrack"),

    SWORDSMEN("swordsmen", 10, 85, 85,  UnitsType.COMBAT, true
            , 80, "barrack"),

    KNIGHT("knight", 85, 85, 80,  UnitsType.COMBAT, false, 150
            , "barrack"),

    TUNNELER("tunneler", 75, 50, 10, UnitsType.ARMY, false, 30
            , "barrack"),

    LADDER_MAN("ladderman", 75, 0, 10,  UnitsType.ENGINEER, false
            , 15, "engineer guild"),

    BLACK_MONK("blackmonk", 25, 50, 50,  UnitsType.COMBAT, true
            , 20, "barrack"),

    //arab people
    ARCHER_BOW("archerbow", 75, 25, 25, UnitsType.ARCHER, false,
            35, "mercenary post"),

    SLAVES("slaves", 75, 10, 10,  UnitsType.COMBAT, false, 15
            , "mercenary post"),

    SLINGERS("slingers", 75, 25, 10,  UnitsType.ARCHER, false, 17
            , "mercenary post"),

    ASSASSINS("assassin", 50, 50, 50,  UnitsType.ARMY, false, 30,
            "mercenary post"),

    HORSE_ARCHERS("horsearchers", 85, 25, 50,  UnitsType.ARCHER,
            false, 35, "mercenary post"),

    ARABIAN_SWORDSMEN("arabianswordsmen", 85, 75, 75,  UnitsType.COMBAT
            , false, 70, "mercenary post"),

    FIRE_THROWERS("firethrowers", 85, 75, 25,  UnitsType.ARCHER
            , false, 60, "mercenary post"),
    ENGINEER("engineer", 40, 0, 20, UnitsType.ENGINEER
            , false, 50, "engineer guild");
    ;


    private String name;
    private int speed;
    private int attackingPower;
    private int defensingPower;
    private UnitsType unitsType;

    private boolean hasArmour;

    private int cost;
    private String building;
    UnitsName(String name, int speed, int attackingPower, int defensingPower,  UnitsType unitsType, boolean hasArmour, int cost
    ,String building) {
        this.name = name;
        this.speed = speed;
        this.attackingPower = attackingPower;
        this.defensingPower = defensingPower;
        this.unitsType = unitsType;
        this.hasArmour = hasArmour;
        this.cost = cost;
        this.building = building;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackingPower() {
        return attackingPower;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getDefensingPower() {
        return defensingPower;
    }

    public String getBuilding() {
        return building;
    }

    public UnitsType getUnitsType() {
        return unitsType;
    }
}
