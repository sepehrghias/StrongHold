package control;

import model.Game;
import model.government.Government;
import model.government.building.Building;
import model.government.building.CagedWarDog;
import model.government.building.Gatehouse;
import model.government.building.StockPileBuilding;
import model.government.people.People;
import model.government.people.units.State;
import model.government.people.units.Units;
import model.government.people.units.UnitsName;
import model.government.people.units.UnitsType;
import model.government.resource.Resource;
import model.map.Tile;
import model.map.type.Type;
import view.StoreMenu;
import view.enums.commands.BuildingCommands;
import view.enums.messages.BuildingMessage;

import javax.swing.plaf.ButtonUI;
import java.util.HashMap;

public class BuildingControl {
    public static BuildingMessage dropBuilding(Tile tile, String name) {
        if (isAnotherBuilding(tile))
            return BuildingMessage.EXIST;
        else if (!isAppropriateGround(tile, name))
            return BuildingMessage.BAD_GROUND;
        Building building = Building.makeBuildingByName(name,tile.getXOfTile(), tile.getYOfTile(), Game.getTurnedUserForGame().getUserGovernment(), 0);
        if (building == null)
            return BuildingMessage.NOT_ENOUGH_SOURCE;
        tile.setBuilding(building);
        Game.getTurnedUserForGame().getUserGovernment().addBuilding(building);
        if (building instanceof StockPileBuilding)
            building.getGovernment().addStockPile((StockPileBuilding) building);
        if (!building.getResourceNeedToBuild().isEmpty()) {
            for (Resource resource : building.getResourceNeedToBuild().keySet()) {
                building.getGovernment().removeFromResources(resource , building.getResourceNeedToBuild().get(resource));
                building.getGovernment().removeResourceFromStockPile(resource, building.getResourceNeedToBuild().get(resource));
            }
        }
        return BuildingMessage.SUCCESS;
    }

    private static boolean isAppropriateGround(Tile tile, String name) {
        return Building.isAppropriateGround(tile.getType(), name);
    }

    private static boolean isAnotherBuilding(Tile tile) {
        if (tile.getBuilding() != null)
            return true;
        return false;
    }

//    public static BuildingMessage selectBuilding(int x, int y) {
//        Game.setSelectedBuilding(null);
//        if (!isAppropriateCoordinate(x, y))
//            return BuildingMessage.WRONG_AMOUNT;
//        if (!isAnotherBuilding(x, y))
//            return BuildingMessage.NOT_EXIST;
//        if (!isBuildingForCurrentUser(x, y))
//            return BuildingMessage.NOT_BELONG_TO_YOU;
//        Game.setSelectedBuilding(Game.getMapInGame().getMap()[y][x].getBuilding());
//        if (Game.getSelectedBuilding().getName().equals("market")) {
//            return BuildingMessage.SELECT_MARKET;
//        }
//        return BuildingMessage.SUCCESS;
//    }

    private static boolean isBuildingForCurrentUser(int x, int y) {
        if (Game.getMapInGame().getMap()[y][x].getBuilding() == null)
            return false;
        return Game.getMapInGame().getMap()[y][x].getBuilding().getGovernment().equals(Game.getTurnedUserForGame().getUserGovernment());
    }
    public static BuildingMessage createUnit(String type) {
        if (!hasEnoughWeaponAndGold(type))
            return BuildingMessage.NOT_ENOUGH_SOURCE;
        if (Game.getSelectedBuilding().getGovernment().getUnWorkedPeople().size() < 1)
            return BuildingMessage.NOT_ENOUGH_POPULATION;
        UnitsName unitsName = getUnitNameByType(type);
        Building building = Game.getSelectedBuilding();
        Government government = building.getGovernment();
        System.out.println("xxxx+Building x and y:"+building.getXBuilding()+"  "+building.getYBuilding());
        Units units = new Units(building.getXBuilding(), building.getYBuilding(), unitsName, building.getGovernment().getUser());
        People people = government.getUnWorkedPeople().get(0);
        government.removeUnWorkedPeople(people);
        government.addToPeople(units);
        Game.getMapInGame().getMap()[building.getYBuilding()][building.getXBuilding()].addPeople(units);
        System.out.println("Fuckkk"+building.getXBuilding()+"  "+building.getYBuilding());

        return BuildingMessage.SUCCESS;
    }

    private static void removeResourceInCreate(UnitsName unitsName) {
        Government government=Game.getCurrentUser().getUserGovernment();
        Resource resource = Resource.APPLE;
        government.setWealth(government.getWealth() - unitsName.getCost());
        if(unitsName.getUnitsType().equals(UnitsType.COMBAT)) {
            if (unitsName.getName().equals("pikeman")) {
                resource = Resource.SPEAR;
            } else if (unitsName.getName().equals("balckman")) {
                resource = Resource.STICK;
            } else if (unitsName.getName().equals("slaves")) {
                resource = Resource.TORCH;
            }
            else return;
        }
        else if(unitsName.getUnitsType().equals(UnitsType.ARCHER)){
            if(unitsName.getName().equals("archer")){
                resource = Resource.ARROW;
            }
            else if(unitsName.getName().equals("crossbowmen")){
                resource = Resource.ARROW;
            }
            else if(unitsName.getName().equals("archerbow")){
                resource = Resource.ARROW;
            }
            else if(unitsName.getName().equals("slingers")){
                resource = Resource.STONE;
            }
            else if(unitsName.getName().equals("horsearchers")){
                resource = Resource.ARROW;
            }
            else if(unitsName.getName().equals("firethowers")){
                resource = Resource.FIRECRACKER;;
            } else return;
        }
        if (resource.equals(Resource.APPLE))
            return;
        government.removeFromResources(resource,1);
        government.removeResourceFromStockPile(resource, 1);
    }

    public static BuildingMessage repair() {
        if (Game.getSelectedBuilding().getHp() == Game.getSelectedBuilding().getMaxHP())
            return BuildingMessage.HAS_FULL_HP;
        int count;
        if ( (count =isEnoughStone(Game.getSelectedBuilding())) == -1)
            return BuildingMessage.NOT_ENOUGH_STONE;
        if (isNearEnemy(Game.getSelectedBuilding()))
            return BuildingMessage.NEAR_ENEMY;
        Game.getSelectedBuilding().setHp(Game.getSelectedBuilding().getMaxHP());
        Game.getSelectedBuilding().getGovernment().removeResourceFromStockPile(Resource.STONE, count);
        Game.getSelectedBuilding().getGovernment().removeFromResources(Resource.STONE, count);
            return BuildingMessage.SUCCESS;
    }

    private static boolean isRelatedBuilding(String type) {
        for (UnitsName unitsName : UnitsName.values()) {
            if (unitsName.getName().equals(type)) {
                if (Game.getSelectedBuilding().getName().equals(unitsName.getBuilding()))
                    return true;
            }
        }
        return false;
    }

    private static boolean isNearEnemy(Building building) {
        int x = building.getXBuilding();
        int y = building.getYBuilding();
        for (int j = y -2; j <= y+2; j++) {
            for (int i = x - 2; i <= x+2; i++) {
                for(People people : Game.getMapInGame().getMap()[j][i].getPeopleOnTile()) {
                    if (!people.getOwnerPerson().equals(building.getGovernment().getUser()))
                        return true;
                }
            }
        }
            return false;
    }
    private static UnitsName getUnitNameByType(String type) {
        for (UnitsName unitsName:UnitsName.values()) {
            if (unitsName.getName().equals(type))
                return unitsName;
        }
        return null;
    }
    private static boolean hasEnoughWeaponAndGold(String type) {
        UnitsName unitsName = getUnitNameByType(type);
        boolean isTrue=false;
        if (unitsName==null)
            return false;
        HashMap <Resource, Integer> resourceNeedUnit = new HashMap<>();
        if(Game.getTurnedUserForGame().getUserGovernment().getWealth()>unitsName.getCost()){
            isTrue=true;
        }
        if(unitsName.getUnitsType().equals(UnitsType.COMBAT)){
            if(unitsName.getName().equals("pikeman")){
                resourceNeedUnit.put(Resource.SPEAR, 1);
            } else if (unitsName.getName().equals("balckman")) {
                resourceNeedUnit.put(Resource.STICK, 1);
            } else if (unitsName.getName().equals("slaves")) {
                resourceNeedUnit.put(Resource.TORCH, 1);
            }

        }
            else if(unitsName.getUnitsType().equals(UnitsType.ARCHER)){
                if(unitsName.getName().equals("archer")){
                    resourceNeedUnit.put(Resource.ARROW, 1);
                }
                else if(unitsName.getName().equals("crossbowmen")){
                    resourceNeedUnit.put(Resource.ARROW, 1);
                }
                else if(unitsName.getName().equals("archerbow")){
                    resourceNeedUnit.put(Resource.ARROW, 1);
                }
                else if(unitsName.getName().equals("slingers")){
                    resourceNeedUnit.put(Resource.STONE, 1);
                }
                else if(unitsName.getName().equals("horsearchers")){
                    resourceNeedUnit.put(Resource.ARROW, 1);
                }
                else if(unitsName.getName().equals("firethowers")){
                    resourceNeedUnit.put(Resource.FIRECRACKER, 1);
                }
            }
            isTrue &= Game.getSelectedBuilding().getGovernment().hasEnoughResources(resourceNeedUnit);
        return isTrue;
    }

    private static int isEnoughStone(Building building) {
        if (building.getResourceNeedToBuild().containsKey(Resource.STONE)) {
            int stoneCount = building.getResourceNeedToBuild().get(Resource.STONE) / 2;
            HashMap<Resource, Integer> stoneResource = new HashMap<>();
            stoneResource.put(Resource.STONE, stoneCount);
            if (building.getGovernment().hasEnoughResources(stoneResource))
                return stoneCount;
        }
        return -1;
    }

    public static BuildingMessage openCagedDog(String state) {
        if (Game.getSelectedBuilding() == null)
            return BuildingMessage.NOT_SELECT_BUILDING;
        if (!state.equals("open") && !state.equals("close"))
            return BuildingMessage.WRONG_AMOUNT;
        if (!(Game.getSelectedBuilding() instanceof CagedWarDog))
            return BuildingMessage.NOT_GOOD_BUILDING;
        if (state.equals("open")) {
            ((CagedWarDog) Game.getSelectedBuilding()).openDoor(true);
            return BuildingMessage.OPEN;
        }
        else {
            ((CagedWarDog) Game.getSelectedBuilding()).openDoor(false);
            return BuildingMessage.CLOSE;
        }
    }

    public static BuildingMessage changeTaxRate(int taxRate) {
        if (Game.getSelectedBuilding() == null)
            return BuildingMessage.NOT_SELECT_BUILDING;
        if (taxRate < -3  || taxRate > 8)
            return BuildingMessage.WRONG_AMOUNT;
        if (!(Game.getSelectedBuilding() instanceof Gatehouse))
            return BuildingMessage.NOT_GOOD_BUILDING;
        ((Gatehouse) Game.getSelectedBuilding()).changeTaxRate(taxRate);
        return BuildingMessage.SUCCESS;
    }
}
