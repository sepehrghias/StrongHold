package model.government;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.Game;
import model.government.building.Building;
import model.government.building.StockPileBuilding;
import model.government.people.People;
import model.government.people.units.Engineer;
import model.government.popularityfactor.Fear;
import model.government.popularityfactor.Religion;
import model.government.popularityfactor.Tax;
import model.government.trade.Request;
import model.government.resource.Resource;
import model.user.User;
import view.TradeMenu;

public class Government {
    private float wealth;
    private int popularity;
    private int population;

    private int populationCapacity = 50;

    private ArrayList<Engineer> engineers ;

    private int fearEffect;
    private int foodEffect;
    private int religionEffect;
    private int taxEffect;
    private float efficiency;
    private User user;

    private int xLeft;

    private int yDown;

    private ArrayList<People> unWorkedPeople;
    private HashMap<Resource, Integer> foods;

    private ArrayList<StockPileBuilding> stockPileBuildings;

    private int foodRate;

    private int taxRate;

    private int fearRate;

    Tax tax;
    Religion religion;
    //for trade to other governments
    private ArrayList<Request> requests;
    private ArrayList<People> people;
    private ArrayList<Building> buildings;
    private HashMap<Resource, Integer> resources;
    private LinkedHashMap<User, HashMap<Resource, Integer>> tradeHistory;
    private Fear fear;

    public Government(int popularity, int population, User user) {
        this.wealth = 2000;
        this.popularity = popularity;
        this.population = population;
        this.user = user;
        foods = new HashMap<>();
        religion = new Religion();
        people = new ArrayList<>();
        buildings = new ArrayList<>();
        resources = new HashMap<>();
        tradeHistory = new LinkedHashMap<>();
        stockPileBuildings = new ArrayList<>();
        unWorkedPeople = new ArrayList<>();
        engineers = new ArrayList<>();
        tax = new Tax();
        fear = new Fear();
        this.foodRate = 0;
        this.taxRate = 0;
        this.fearRate = 0;
        this.efficiency = 1;
        addToResources(Resource.STONE, 100);
        addToResources(Resource.WOOD, 100);
        addToResources(Resource.IRON, 60);
        addResourceToStockPile(Resource.STONE, 100);
        addResourceToStockPile(Resource.WOOD, 100);
        addResourceToStockPile(Resource.IRON, 60);
        addToResources(Resource.ARROW, 10);
        addResourceToStockPile(Resource.ARROW, 10);
        addToResources(Resource.STICK, 6);
        addResourceToStockPile(Resource.STICK, 6);
    }

//    public HashMap<Resource, Integer> getResources() { return resources; }

    public void addToResources(Resource resource, int number) {
        if (resources.containsKey(resource)) resources.put(resource, resources.get(resource) + number);
        else resources.put(resource, number);
    }

    public void addToUnworkedPeople (People people) {
        unWorkedPeople.add(people);
    }

    public void removeFromResources(Resource resource, int number) {
        if (resources.get(resource) < number) System.out.println("there are not enough resources in storehouse");
        else if (resources.get(resource) == number) resources.remove(resource);
        else resources.put(resource, resources.get(resource) - number);
    }
    public void removeFromFoods(Resource resource){
       foods.remove(resource);

    }
    public float getWealth() { return wealth; }

    public void setWealth(float wealth) { this.wealth = wealth; }

    public float getEfficiency() { return efficiency; }

    public void setEfficiency(float efficiency) { this.efficiency = efficiency; }

    public boolean hasEnoughResources(HashMap<Resource, Integer> resources) {
        for (Resource resource : resources.keySet()) {
            if (getResources().get(resource)==null)
                return false;
            if (getResources().get(resource) < resources.get(resource))
                return false;
        }
        return true;
    }

    public void addToTradeHistory(User user, Resource resource, int number) {
        HashMap<Resource, Integer> value = new HashMap<>();
        value.put(resource, number);
        TradeMenu.tradeList.put(user.getUserGovernment(), value);
    }

    public LinkedHashMap<User, HashMap<Resource, Integer>> getTradeHistory() { return tradeHistory; }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public User getUser() {
        return user;
    }

    public HashMap<Resource, Integer> getFoods() {
        return foods;
    }

    public Tax getTax() {
        return tax;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setTax(Tax tax) { this.tax = tax; }

    public Religion getReligions() {
        return religion;
    }

    public Fear getFear() {
        return fear;
    }

    public void setFear(Fear fear) { this.fear = fear; }

    public void addToRequests(Request request) {
        requests.add(request);
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public static void makeChangesCausedByFoodRate(HashMap<Integer, Resource> foods) {

    }

    public static void makeChangesCausedByFearRate(Fear fear) {

    }

    public void changePopularityByTax(Tax tax) {

    }

    public void addToPeople (People people1) {
        people.add(people1);
    }

    public int getFoodRate() { return foodRate; }

    public int getTaxRate() { return taxRate; }
    public ArrayList<People> getUnWorkedPeople() {
        return unWorkedPeople;
    }

    public void removeUnWorkedPeople(People people1) {
        Game.getMapInGame().getMap()[people1.getyLocation()][people1.getxLocation()].removePeople(people1);
        unWorkedPeople.remove(people1);
        people.remove(people1);
    }

    public HashMap<Resource, Integer> getResources() { return resources; }

    public void setFoodRate(int foodRate) { this.foodRate = foodRate; }

    public void setTaxRate(int taxRate) { this.taxRate = taxRate; }

    public int getFearRate() { return fearRate; }

    public void setFearRate(int fearRate) { this.fearRate = fearRate; }
    public void addBuilding (Building building) {
        buildings.add(building);
    }

    public ArrayList<People> getPeople() {
        return people;
    }

    public ArrayList<StockPileBuilding> getStockPileBuildings() {
        return stockPileBuildings;
    }

    public void addStockPile (StockPileBuilding stockPileBuilding) {
        stockPileBuildings.add(stockPileBuilding);
    }

    public void removeResourceFromStockPile (Resource resource, int count) {
        for (StockPileBuilding stockPileBuilding: stockPileBuildings) {
             if (stockPileBuilding.getType().equals("stock pile") &&
                     resource.getTypeOfResource().equals(Resource.TypeOfResource.INDUSTRY))
                 count = stockPileBuilding.useResource(resource, count);
             else if (stockPileBuilding.getType().equals("food stock pile") &&
                     resource.getTypeOfResource().equals(Resource.TypeOfResource.FOOD))
                 count = stockPileBuilding.useResource(resource, count);
             else if (stockPileBuilding.getType().equals("armoury") &&
                     resource.getTypeOfResource().equals(Resource.TypeOfResource.WEAPON))
                 count = stockPileBuilding.useResource(resource, count);
             if (count == 0) break;
        }
    }

    public void addResourceToStockPile (Resource resource, int count) {
        for (StockPileBuilding stockPileBuilding: stockPileBuildings) {
            if (stockPileBuilding.getType().equals("stock pile") &&
                    resource.getTypeOfResource().equals(Resource.TypeOfResource.INDUSTRY))
                stockPileBuilding.addToResources(resource, count);
            else if (stockPileBuilding.getType().equals("food stock pile") &&
                    resource.getTypeOfResource().equals(Resource.TypeOfResource.FOOD))
                stockPileBuilding.addToResources(resource, count);
            else if (stockPileBuilding.getType().equals("armoury") &&
                    resource.getTypeOfResource().equals(Resource.TypeOfResource.WEAPON))
                stockPileBuilding.addToResources(resource, count);
        }
    }

    public int getPopulationCapacity() {
        return populationCapacity;
    }

    public ArrayList<Engineer> getEngineers() {
        return engineers;
    }

    public void removeBuilding(Building building) {
        buildings.remove(building);
    }
    public void setPopulationCapacity(int populationCapacity) {
        this.populationCapacity = populationCapacity;
    }

    public int getFearEffect() { return fearEffect; }

    public int getFoodEffect() { return foodEffect; }

    public int getReligionEffect() { return religionEffect; }

    public int getTaxEffect() { return taxEffect; }

    public int getXLeft() {
        return xLeft;
    }

    public void setXLeft(int xLeft) {
        this.xLeft = xLeft;
    }

    public int getYDown() {
        return yDown;
    }

    public void setYDown(int yDown) {
        this.yDown = yDown;
    }

    public void setFearEffect(int fearEffect) { this.fearEffect = fearEffect; }

    public void setFoodEffect(int foodEffect) { this.foodEffect = foodEffect; }

    public void setReligionEffect(int religionEffect) { this.religionEffect = religionEffect; }

    public void setTaxEffect(int taxEffect) { this.taxEffect = taxEffect; }

    public int numberOfResource(Resource resource) {
        return 0;
    }
}
