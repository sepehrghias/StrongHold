package control;

import model.Game;
import model.government.Government;
import model.government.people.People;
import model.government.people.units.Units;
import model.government.resource.Resource;
import view.enums.messages.GovernmentMenuMessage;

import java.util.HashMap;
import java.util.Map;

public class GovernmentControl {
    private static Government government = Game.getCurrentUser().getUserGovernment();

    public static String showPopularityFactors() {
        StringBuilder result = new StringBuilder();
        result.append("Popularity factors:\n")
                .append("foods:\n\trate: ")
                .append(government.getFoodRate())
                .append("\n\teffect: ");
        int size = government.getFoods().size();
        int difference = size - 1;
        int foodEffect = difference;
        int foodRate = government.getFoodRate();
        if (foodRate == -2) foodEffect -= 8;
        else if (foodRate == -1) foodEffect -= 4;
        else if (foodRate == 0) ;
        else if (foodRate == 1) foodEffect += 4;
        else if (foodRate == 2) foodEffect += 8;
        result.append(foodEffect)
                .append("\ntax:\n\trate: ")
                .append(government.getTaxRate())
                .append("\n\teffect: ");
        int taxEffect = 0;
        int taxRate = government.getTaxRate();
        if (taxRate == -3) taxEffect += 7;
        else if (taxRate == -2) taxEffect += 5;
        else if (taxRate == -1) taxEffect += 3;
        else if (taxRate == 0) taxEffect++;
        else if (taxRate == 1) taxEffect += 2;
        else if (taxRate == 2) taxEffect += 4;
        else if (taxRate == 3) taxEffect += 6;
        else if (taxRate == 4) taxEffect += 8;
        else if (taxRate == 5) taxEffect += 12;
        else if (taxRate == 6) taxEffect += 16;
        else if (taxRate == 7) taxEffect += 20;
        else if (taxRate == 8) taxEffect += 24;
        result.append(taxEffect)
                .append("\nfear:\n\trate: ")
                .append(government.getFearRate())
                .append("\n\teffect: ");
        int fearEffect = government.getFearRate();
        result.append(fearEffect)
                .append("\nreligion:\n\teffect: ");
        // handle effect of religion in popularity
        //--------------------------------------------------------------------------------------------//
        int religionEffect = government.getPopularity() - foodEffect - taxEffect - fearEffect;
        result.append(religionEffect);
        //--------------------------------------------------------------------------------------------//
        return result.toString();
    }

    public static String showPopularity() {
        return String.valueOf(Game.getCurrentUser().getUserGovernment().getPopularity());
    }

    public static GovernmentMenuMessage changePopulation() {
        return null;
    }

    public static void addToResources(Resource resource, int number) {
        HashMap<Resource, Integer> resources = government.getResources();
        if (resources.containsKey(resource)) resources.put(resource, resources.get(resource) + number);
        else resources.put(resource, 1);
        System.out.println("successfully added " + number + " " + resource + " to resources");
    }

    public static GovernmentMenuMessage removeFromResources(Resource resource, int number) {
        HashMap<Resource, Integer> resources = government.getResources();
        if (resources.get(resource) < number)
            return GovernmentMenuMessage.NOT_ENOUGH_INVENTORY;
        else if (resources.get(resource) == number) resources.remove(resource);
        else resources.put(resource, resources.get(resource) - number);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static void changeWorkersActivities(int fearRate) {
        government.setEfficiency((float) (government.getEfficiency() + 0.1 * fearRate));
        // check here
    }

    public static void changeSoldiersMorality(int fearRate) {
        for (int i = 0; i < 200; i++)
            for (int j = 0; j < 200; j++)
                for (People people : Game.getMapInGame().getMap()[i][j].getPeopleOnTile())
                    if (people instanceof Units)
                        ((Units) people).changeEfficientAttackingPower(-5 * fearRate);
    }

    public static GovernmentMenuMessage rateFood(int rate) {
        if (!validateRateNumber("food", rate))
            return GovernmentMenuMessage.INVALID_RATE;
        government.setFoodRate(rate);
        makeChangesCausedByFoodRate(rate, government);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static GovernmentMenuMessage rateTax(int rate) {
        if (!validateRateNumber("tax", rate))
            return GovernmentMenuMessage.INVALID_RATE;
        government.setTaxRate(rate);
        makeChangesCausedByTaxRate(rate, government);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static GovernmentMenuMessage rateFear(int rate) {
        if (!validateRateNumber("fear", rate))
            return GovernmentMenuMessage.INVALID_RATE;
        government.setFearRate(rate);
        makeChangesCausedByFearRate(rate, government);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static void makeChangesCausedByFoodRate(int rate, Government government) {
        int popularity = government.getPopularity();
        int foodEffect = 0;
        if (rate == -2) {
            popularity -= 8;
            foodEffect = -8;
        }
        else if (rate == -1) {
            popularity -= 4;
            foodEffect = -4;
            // handle giving half of food to each person of your people
        } else if (rate == 0) {
            // handle giving one food to each person
        } else if (rate == 1) {
            popularity += 4;
            foodEffect = 4;
            // handle giving 1.5 food to each person
        } else {
            popularity += 8;
            foodEffect = 8;
            // handle giving 2 foods to each person
        }
        government.setPopularity(popularity);
        government.setFoodEffect(foodEffect);
    }

    public static void makeChangesCausedByTaxRate(int rate, Government government) {
        int popularity = government.getPopularity();
        float wealth = government.getWealth();
        int population = government.getPopulation();
        int taxEffect = 0;
        if (rate == -3) {
            popularity += 7;
            taxEffect = 7;
            wealth -= population * 1;
        } else if (rate == -2) {
            popularity += 5;
            taxEffect = 5;
            wealth -= population * 0.8;
        } else if (rate == -1) {
            popularity += 3;
            taxEffect = 3;
            wealth -= population * 0.6;
        } else if (rate == 0) {
            popularity++;
            taxEffect++;
        }
        else if (rate == 1) {
            popularity -= 2;
            taxEffect = -2;
            wealth += population * 0.6;
        } else if (rate == 2) {
            popularity -= 4;
            taxEffect = -4;
            wealth += population * 0.8;
        } else if (rate == 3) {
            popularity -= 6;
            taxEffect = -6;
            wealth += population * 1;
        } else if (rate == 4) {
            popularity -= 8;
            taxEffect = -8;
            wealth += population * 1.2;
        } else if (rate == 5) {
            popularity -= 12;
            taxEffect = -12;
            wealth += population * 1.4;
        } else if (rate == 6) {
            popularity -= 16;
            taxEffect = -16;
            wealth += population * 1.6;
        } else if (rate == 7) {
            popularity -= 20;
            taxEffect = -20;
            wealth += population * 1.8;
        } else {
            popularity -= 24;
            taxEffect = -24;
            wealth += population * 2;
        }
        government.setPopularity(popularity);
        government.setTaxEffect(taxEffect);
        government.setWealth(wealth);
    }

    public static void makeChangesCausedByFearRate(int rate, Government government) {
        government.setPopularity(government.getPopularity() + rate);
        government.setFearEffect(rate);
        changeWorkersActivities(rate);
        changeSoldiersMorality(rate);
    }

    public static GovernmentMenuMessage showFoodList() {
        if (government.getFoods().keySet().size() == 0)
            return GovernmentMenuMessage.EMPTY_FOOD_LIST;
        return GovernmentMenuMessage.SUCCESS;
    }

    public static GovernmentMenuMessage addToFoods(Resource food, int numberOfFoods) {
        if (food == null || !food.getTypeOfResource().equals(Resource.TypeOfResource.FOOD)) {
            return GovernmentMenuMessage.INVALID_FOOD_NAME;
        }
        int value = 0;
        for (Map.Entry<Resource, Integer> foodResources : Game.getCurrentUser().getUserGovernment().getResources().entrySet()) {
            if (food.getName().equals(foodResources.getKey())) {
                value = foodResources.getValue();
                break;
            }

        }
        if (numberOfFoods > value) {
            return GovernmentMenuMessage.NOT_ENOUGH_INVENTORY;
        }
        for (Map.Entry<Resource, Integer> foods : Game.getCurrentUser().getUserGovernment().getFoods().entrySet()) {   //is it okay?
            if (food.getName().equals(foods.getKey().getName())) {
                Game.getCurrentUser().getUserGovernment().getFoods().replace(foods.getKey(), foods.getValue(), foods.getValue() + numberOfFoods);
                return GovernmentMenuMessage.SUCCESS;
            }
        }


        return null;
    }

    public static GovernmentMenuMessage removeFromFoods(Resource food, int numberOfFoods) {
        HashMap<Resource, Integer> foods = government.getFoods();
        if (foods.get(food) < numberOfFoods)
            return GovernmentMenuMessage.NOT_ENOUGH_FOOD;
        else if (foods.get(food) == numberOfFoods) foods.remove(food);
        else foods.put(food, foods.get(food) - numberOfFoods);
        return GovernmentMenuMessage.SUCCESS;
    }

    public static boolean validateRateNumber(String type, int rate) {
        if (type.equals("food")) {
            if (rate > 2 || rate < -2) return false;
        }
        if (type.equals("tax")) {
            if (rate > 8 || rate < -3) return false;
        }
        if (type.equals("fear")){
            if (rate > 5 || rate < -5) return false;
            }
        return true;
    }
}
