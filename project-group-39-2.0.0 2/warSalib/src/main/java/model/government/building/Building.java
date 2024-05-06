package model.government.building;

import model.Game;
import model.government.Government;
import model.government.building.group.GroupOfBuilding;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.government.resource.Resource;
import model.map.type.Type;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.awt.datatransfer.MimeTypeParseException;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class Building extends Rectangle{
    private Government government;
    private HashMap<String, Integer> workerDataBase;
    private ArrayList<People> workerWorked;

    private HashMap<Resource, Integer> resourceNeedToBuild;
    private int hp;
    private String type;

    private int wealth;

    private boolean Fire;

    private String name;
    private int maxHP;
    private int x, y;

    private static Image image = new Image(Building.class.getResource("/images/keep1.png").toExternalForm());;

    public Building(int x, int y, Government government, int hp, String type, String name, int maxHP,
                    HashMap<Resource, Integer> resourceNeedToBuild) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.type = type;
        this.government = government;
        this.name = name;
        workerDataBase = new HashMap<>();
        workerWorked = new ArrayList<>();
        this.maxHP = maxHP;
        this.resourceNeedToBuild = resourceNeedToBuild;
        Game.getMapInGame().getMap()[x][y].setBuilding(this);
        this.setWidth(5);
        this.setHeight(5);
    }

    public static GroupOfBuilding getGroupByName(String name) {
        GroupOfBuilding[] groups = GroupOfBuilding.values();
        for (GroupOfBuilding group : groups) {
            for (String instance : group.getGroup()) {
                if (name.equals(instance))
                    return group;
            }
        }
        return null;
    }

    public static Building makeBuildingByName(String name, int x, int y, Government government, int flag) {
        GroupOfBuilding group = getGroupByName(name);
        if (group == null)
            return null;
        if (group.equals(GroupOfBuilding.CONVERTED_BUILDING))
            return ConvertedBuilding.makeConvertedBuildingByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.CAGED_WAR_DOG))
            return CagedWarDog.makeCagedWarDogByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.CHURCH))
            return Church.makeChurchByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.DRAWBRIDGE))
            return DrawBridge.makeDrawBridgeByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.GATEHOUSE))
            return Gatehouse.makeGatehouseByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.HOVEL))
            return Hovel.makeHovelByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.INN))
            return Inn.makeInnByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.KILLING_PIT))
            return KillingPit.makeKillingPitByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.MAKING_FORCE_BUILDING))
            return MakingForceBuilding.makeMakingForceBuildingByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.MARKET))
            return Market.makeMarketByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.MINE_BUILDING))
            return MineBuilding.makeMineBuildingByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.OX_TETHER))
            return OxTether.makeOxTetherByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.PITCH_DITCH))
            return PitchDitch.makePitchDitchByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.PRODUCTIVE_BUILDING))
            return ProductiveBuilding.makeProductiveBuildingByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.SIEGE_TENT))
            return SiegeTent.makeSiegeTentByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.STOCK_PILE_BUILDING))
            return StockPileBuilding.makeStockPileBuildingByName(name, x, y, government,flag);

        else if (group.equals(GroupOfBuilding.TOWER))
            return Tower.makeTowerByName(name, x, y, government, flag);

        else if (group.equals(GroupOfBuilding.WALL))
            return Wall.makeWallByName(name, x, y, government, flag);
        else return null;
    }

    public void addWorker(People people) {
        workerWorked.add(people);
    }


    public boolean IsVisibleBuilding() {
        return true;
    }

    public Government getGovernment() {
        return government;
    }

    public int getHp() {
        return hp;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getXBuilding() {
        return x;
    }

    public int getYBuilding() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public HashMap<String, Integer> getWorkerDataBase() {
        return workerDataBase;
    }

    public ArrayList<People> getWorkerWorked()
    {
        return workerWorked;
    }

    public void setWorkerDataBase(String people, int number) {
        workerDataBase.put(people, number);
    }

    public int getMaxHP() {
        return maxHP;
    }

    private int fireTurns=0;

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public HashMap<Resource, Integer> getResourceNeedToBuild() {
        return resourceNeedToBuild;
    }

    public static boolean isAppropriateGround(Type type, String name) {
        if (!type.getPermeability())
            return false;
        if (name.equals("quarry") && !(type.equals(Type.SLATE)))
            return false;
        if (name.equals("iron mine") && !(type.equals(Type.IRON_GROUND)))
            return false;
        if (name.equals("pitch rig") && !(type.equals(Type.PLAIN)))
            return false;
        if ((name.equals("apple garden") || name.equals("barley field") || name.equals("wheat field")) &&
                !(type.equals(Type.GRASS) || type.equals(Type.DENSE_GRASSLAND)))
            return false;
        return true;
    }

    public static void changePeople(People people, JobsName jobsName) {
        people.setJobsName(jobsName);
        people.getOwnerPerson().getUserGovernment().getUnWorkedPeople().remove(people);
        Game.getMapInGame().getMap()[people.getyLocation()][people.getxLocation()].removePeople(people);
    }
    public void ChangeHitPoint(int x){
        this.hp+=x;
    }

    public int getWealth() {
        return wealth;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    public static Image getImage() {
        return image;
    }

    public void setFire(boolean fire) {
        Fire = fire;
    }

    public boolean isFire() {
        return Fire;
    }

    public int getFireTurns() {
        return fireTurns;
    }

    public void setFireTurns(int fireTurns) {
        this.fireTurns = fireTurns;
    }
}
