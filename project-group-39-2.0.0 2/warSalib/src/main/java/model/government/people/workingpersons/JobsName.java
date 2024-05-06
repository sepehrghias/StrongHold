package model.government.people.workingpersons;

import model.government.building.Building;
import model.government.building.group.GroupOfBuilding;
import model.government.resource.Resource;
import model.wartool.wartoolenum;

public enum JobsName {
    TUNNELER("tunneler",null,null),
    SHOPPER("shopper",GroupOfBuilding.MARKET.getGroup()[0],null),
    DRINK_SERVER("drinkserver",GroupOfBuilding.INN.getGroup()[0],null),
    WOODCUTTER("woodcutter",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[2],Resource.WOOD),
    POLE_TURNER("poleturner",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[3],Resource.IRON),
    FLETCHER("fletcher",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[2],Resource.WOOD),
    BLACKSMITH("blacksmith",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[1],Resource.IRON),
    ARMOUR("armour",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[0],Resource.IRON),
    BUTCHER("butcher",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[8],Resource.COW),
    BREWER("brewer",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[6],Resource.HOP),
    APPLE_FARMER("applefarmer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[0],null),
    MILLER("miller",GroupOfBuilding.CONVERTED_BUILDING.getGroup()[4],Resource.WHEAT),
    PRIEST("priest",GroupOfBuilding.CHURCH.getGroup()[0],null),
    DIARY_PRODUCER("diaryproducer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[7],Resource.COW),
    HOP_FARMER("hopfarmer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[9],null),
    WHEAT_FARMER("wheatfarmer",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[4],null),
    BAKER("baker",GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[5],Resource.FLOUR),
    HUNTER("hunter", GroupOfBuilding.PRODUCTIVE_BUILDING.getGroup()[5], Resource.MEAT),
    OX_TETHER("ox tether", GroupOfBuilding.OX_TETHER.getGroup()[0], Resource.STONE),

    IRON_MINE_WORKER("iron mine worker", GroupOfBuilding.MINE_BUILDING.getGroup()[2], Resource.IRON),
    QUARRY_WORKER("quarry worker", GroupOfBuilding.MINE_BUILDING.getGroup()[0], Resource.STONE),
    PITCH_RIG_WORKER("pitch rig worker", GroupOfBuilding.MINE_BUILDING.getGroup()[1], Resource.OIL)

    ,UNEMPLOYED("unemployed",GroupOfBuilding.HOVEL.getGroup()[0],null);

    private String jobsName;
    private String workingPlace;
    private Resource resource;
    JobsName(String jobsName,String workingPlace,Resource resource){
        this.jobsName=jobsName;
        this.workingPlace=workingPlace;
        this.resource=resource;
    }

    public Resource getResource() {
        return resource;
    }

    public String getJobsName() {
        return jobsName;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }
}
