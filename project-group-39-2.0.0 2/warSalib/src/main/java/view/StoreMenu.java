package view;

import control.StoreControl;
import view.enums.commands.StoreMenuCommands;
import view.enums.messages.StoreMenuMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StoreMenu {
    public static void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("\s*back\s*")) {
                System.out.println("back to building menu");
                break;
            }
            else if (input.matches("\s*show\s+price\s+list\s*"))
                showPriceList();
            else if ((matcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.BUY)) != null)
                buyFromStore(matcher);
            else if ((matcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.SELL)) != null)
                sellFromStore(matcher);
            else System.out.println("invalid");
        }
    }

    private static void showPriceList() {
        System.out.println(StoreControl.showPriceList());
    }
    private static void buyFromStore(Matcher matcher) {
//        int amount = Integer.parseInt(matcher.group("amount"));
//        String item = getItemWithoutDoubleQuotation(matcher.group("item"));
//        StoreMenuMessage message = StoreControl.buyFromStore(item, amount);
//        switch (message) {
//            case WRONG_ITEM:
//                System.out.println("you enter wrong item");
//                break;
//            case WRONG_AMOUNT:
//                System.out.println("you enter wrong amount");
//                break;
//            case DONT_HAVE_BUDGET:
//                System.out.println("your budget is less than price of items");
//                break;
//            case NOT_ENOUGH_PLACE:
//                System.out.println("yod don't have any place to hold it");
//                break;
//            case SUCCESS:
//                System.out.println("buy successfully");
//                break;
//            default:
//                System.out.println("invalid");
//                break;
//        }
    }
    private static void sellFromStore(Matcher matcher) {
//        int amount = Integer.parseInt(matcher.group("amount"));
//        String item = getItemWithoutDoubleQuotation(matcher.group("item"));
//        StoreMenuMessage message = StoreControl.sellFromStore(item, amount);
//        switch (message) {
//            case WRONG_ITEM:
//                System.out.println("you enter wrong item");
//                break;
//            case WRONG_AMOUNT:
//                System.out.println("you enter wrong amount");
//                break;
//            case DONT_HAVE_BUDGET:
//                System.out.println("buyer dosen't  have enough money");
//                break;
//            case NOT_ENOUGH_RESOURCE:
//                System.out.println("not enough resources");
//                break;
//            case SUCCESS:
//                System.out.println("sell successfully");
//                break;
//            default:
//                System.out.println("invalid");
//                break;
//        }
    }

    private static String getItemWithoutDoubleQuotation(String item) {
        if (item.startsWith("\"")) {
            return item.substring(1, item.length() - 1);
        }
        return item;
    }
}
