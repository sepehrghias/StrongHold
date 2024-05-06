package view;

import control.TradeControl;
import model.government.Government;
import model.government.resource.Resource;
import view.enums.commands.TradeMenuCommands;
import view.enums.messages.TradeMenuMessage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    public static LinkedHashMap<Government, HashMap<Resource, Integer>> tradeList = new LinkedHashMap<>();

    public static void run(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            Matcher matcher;
            if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE)) != null)
                trade(matcher);
            else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.SHOW_TRADE_LIST)) != null)
                showTradeList();
            else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.ACCEPT_TRADE)) != null)
                acceptTrade(matcher);
            else if ((matcher = TradeMenuCommands.getMatcher(input, TradeMenuCommands.SHOW_TRADE_HISTORY)) != null)
                showTradeHistory();
            else if (input.matches("^\\s*back\\s*$")) {
                System.out.println("back to game menu");
                break;
            }
            else System.out.println("invalid command!");
        }
    }

    private static void trade(Matcher matcher) {
        String resourceType = matcher.group("resourceType");
        int amount = Integer.parseInt(matcher.group("resourceAmount"));
        int price = Integer.parseInt(matcher.group("price"));
        String tradeMessage = matcher.group("message");
        TradeMenuMessage message = TradeControl.trade(resourceType, amount, price, tradeMessage);
        switch (message) {
            case INVALID_AMOUNT_OR_PRICE:
                System.out.println("invalid amount or price");
                break;
            case ITEM_NOT_EXIST:
                System.out.println("there is no such thing in storehouse");
                break;
            case SUCCESS:
                System.out.println("item has been successfully added to your trade list\n" +
                        resourceType + ":        amount: " + amount + " , price: " + price +
                        " , total: " + amount * price + " , message: " + message);
            default:
                System.out.println("invalid!!?");
                break;
        }
    }

    private static void showTradeList() {
        String message = TradeControl.showTradeList();
//        if (TradeControl.showTradeList().equals(TradeMenuMessage.EMPTY_TRADE_LIST))
//            System.out.println("No trades");
//        else System.out.println(TradeControl.showTradeList());
        if (message.equals(String.valueOf(TradeMenuMessage.EMPTY_TRADE_LIST)))
            System.out.println("empty trade list");
        else System.out.println(message);
    }

    private static void acceptTrade(Matcher matcher) {
        String id = matcher.group("id");
        String tradeMessage = matcher.group("message");
        TradeMenuMessage message = TradeControl.acceptTrade(id, tradeMessage);
        switch (message) {
            case ID_NOT_EXIST:
                System.out.println("there are no users with this id");
                break;
            case RESOURCE_NOT_EXIST:
                System.out.println("you don't have this item");
                break;
            case INSUFFICIENT_FUNDING:
                System.out.println("you don't have enough resources to accept this trade");
                break;
            case SUCCESS:
                System.out.println("you have successfully accepted this trade");
                break;
            default:
                System.out.println("invalid!!?");
                break;
        }
    }

    private static void showTradeHistory() {
//        TradeMenuMessage message = TradeMenuMessage.valueOf(TradeControl.showTradeHistory());
        String message = TradeControl.showTradeHistory();
        if (message.equals(String.valueOf(TradeMenuMessage.EMPTY_TRADE_HISTORY)))
            System.out.println("empty history, you don't have any trades in you history");
        else System.out.println(message);
    }
}
