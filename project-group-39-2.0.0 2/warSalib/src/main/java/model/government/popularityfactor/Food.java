package model.government.popularityfactor;

import model.government.resource.Resource;

public class Food extends PopularityFactor{
    private Resource foodName;

    public Food() {
        type = "food";
    }


    public Resource getFoodName() {
        return foodName;
    }

}
