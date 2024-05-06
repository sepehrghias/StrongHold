package control;

import model.Game;
import model.government.building.Market;
import model.government.people.People;
import model.government.resource.Resource;
import view.enums.messages.StoreMenuMessage;

import java.util.HashMap;

public class StoreControl {
    public static String showPriceList() {
        Market market = (Market) Game.getSelectedBuilding();
        String priceList = "******price list of this market******";
        if (market.getResources().isEmpty())
            return "has nothing resources";
        for (Resource resource : market.getResources().keySet()) {
            priceList +=  "\n" + "name :" + resource.getName() + "   " + "  number :"  +
                    market.getResources().get(resource) + "  cost : " +resource.getCost();
        }
        return priceList;
    }
    public static StoreMenuMessage buyFromStore(Resource resource) {
        Market market = (Market) Game.getSelectedBuilding();
        if (market.getGovernment().getWealth() < (float) (resource.getCost() ))
            return StoreMenuMessage.DONT_HAVE_BUDGET;
        market.buyResource(resource, 1);
        market.getGovernment().setWealth(market.getGovernment().getWealth() - (resource.getCost()));
        market.getGovernment().addToResources(resource, 1);
        return StoreMenuMessage.SUCCESS;
    }

    private static boolean validAmount (int amount) {
        if (amount > 0)
            return true;
        return false;
    }

    private static Resource getResourceByItemName (String item) {
        for (Resource resource : Resource.values()) {
            if (resource.getName().equals(item))
                return resource;
        }
        return null;
    }

    public static StoreMenuMessage sellFromStore(Resource resource) {
        Market market = (Market) Game.getSelectedBuilding();
        HashMap <Resource,Integer> resources = new HashMap<>();
        resources.put(resource, 1);
        if (!market.getGovernment().hasEnoughResources(resources))
            return StoreMenuMessage.NOT_ENOUGH_RESOURCE;
        market.sellResource(resource, 1);
        market.getGovernment().setWealth(market.getGovernment().getWealth() + (resource.getCost()));
        return StoreMenuMessage.SUCCESS;
    }
    public static int getAmountOfResource(Resource resource) {
        Market market = (Market) Game.getSelectedBuilding();
        return market.getResources().get(resource);
    }
}
