package control;

import model.Game;
import model.government.Government;
import model.government.resource.Resource;
import model.user.User;
import view.TradeMenu;
import view.enums.messages.TradeMenuMessage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class TradeControl {
    public static TradeMenuMessage trade(String itemName, int amount, int price, String message) {
        Government government = Game.getCurrentUser().getUserGovernment();

        if (amount <= 0 || price < 0)
            return TradeMenuMessage.INVALID_AMOUNT_OR_PRICE;


        else if (!government.getResources().containsKey(getResourceByName(itemName)))
            return TradeMenuMessage.ITEM_NOT_EXIST;

        HashMap<Resource, Integer> tradeListValue = new HashMap<>();
        tradeListValue.put(getResourceByName(itemName), amount);
        TradeMenu.tradeList.put(government, tradeListValue);
        return TradeMenuMessage.SUCCESS;
    }

    private static Resource getResourceByName(String name) {
        for (Resource resource : Resource.values()) {
            if (resource.getName().equals(name))
                return resource;
        }
        return null;
    }

    public static String showTradeList() {
        StringBuilder result = new StringBuilder();

        if (TradeMenu.tradeList.isEmpty())
            return String.valueOf(TradeMenuMessage.EMPTY_TRADE_LIST);

        result.append("Trade List:");
        for (Government government : TradeMenu.tradeList.keySet()) {
            result.append("\nID: ")
                    .append(government.getUser().getUsername())
                    .append("\t|\tResource: ");
            Set<Resource> set = TradeMenu.tradeList.get(government).keySet();
            Iterator<Resource> iterator = set.iterator();
            Resource resource = null;
            if (iterator.hasNext()) resource = iterator.next();

            int amount = TradeMenu.tradeList.get(government).get(resource);

            result.append(resource)
                    .append("\t|\tAmount: ")
                    .append(amount);
        }
        return result.toString();
    }

    public static TradeMenuMessage acceptTrade(String id, String message) {
        User currentUser = Game.getCurrentUser();
        if (validIdForAccept(id)) {
            if (id.equals(currentUser.getUsername()))
                return TradeMenuMessage.SAME_ID_TO_CURRENT_USERS_ID;
            User user = null;
            for (Government government : TradeMenu.tradeList.keySet())
                if (government.getUser().getUsername().equals(id)) {
                    user = government.getUser();
                    break;
                }

            Set<Resource> set = TradeMenu.tradeList.get(user.getUserGovernment()).keySet();
            Iterator<Resource> iterator = set.iterator();
            Resource resource = null;
            if (iterator.hasNext()) resource = iterator.next();

            int amount = TradeMenu.tradeList.get(user.getUserGovernment()).get(resource);

            if (!currentUser.getUserGovernment().getResources().containsKey(resource))
                return TradeMenuMessage.RESOURCE_NOT_EXIST;
            else if (currentUser.getUserGovernment().getResources().get(resource) < amount)
                return TradeMenuMessage.INSUFFICIENT_FUNDING;

            currentUser.getUserGovernment().addToTradeHistory(user, resource, -amount);
            user.getUserGovernment().addToTradeHistory(currentUser, resource, amount);
            HashMap<Resource, Integer> value = new HashMap<>();
            value.put(resource, amount);
            TradeMenu.tradeList.remove(user, value);
            currentUser.getUserGovernment().removeFromResources(resource, amount);
            user.getUserGovernment().addToResources(resource, amount);
            return TradeMenuMessage.SUCCESS;
        }
        return TradeMenuMessage.ID_NOT_EXIST;
    }
    public static String showTradeHistory() {
        StringBuilder result = new StringBuilder();
        Government government = Game.getCurrentUser().getUserGovernment();
        if (government.getTradeHistory().isEmpty())
            return String.valueOf(TradeMenuMessage.EMPTY_TRADE_HISTORY);

        result.append("Trade History:");
        for (User user : government.getTradeHistory().keySet()) {
            result.append("\nUser: ")
                    .append(user.getUsername())
                    .append("\tItem: ");
            Set<Resource> set = government.getTradeHistory().get(user).keySet();
            Iterator<Resource> iterator = set.iterator();
            Resource resource = null;
            if (iterator.hasNext()) resource = iterator.next();

            int amount = government.getTradeHistory().get(user).get(resource);

            result.append(resource)
                    .append("\tAmount: ")
                    .append(amount);
        }
        return result.toString();
    }

    private static boolean validIdForAccept (String id) {
        for (Government government : TradeMenu.tradeList.keySet())
            if (government.getUser().getUsername().equals(id))
                return true;
        return false;
    }
}
