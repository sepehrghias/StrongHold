package model.government.trade;

import model.government.resource.Resource;
import model.user.User;

import java.util.ArrayList;

public class Donate {
    private String message;

    private int number;

    private Resource resource;

    private User senderDonate;

    private User getterDonate;

    private boolean accept;

    public static ArrayList<Donate> donates=new ArrayList<>();

    public Donate(String message, int number, Resource resource, User senderDonate, User getterDonate) {
        this.message = message;
        this.number = number;
        this.resource = resource;
        this.senderDonate = senderDonate;
        this.getterDonate = getterDonate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public User getSenderDonate() {
        return senderDonate;
    }

    public void setSenderDonate(User senderDonate) {
        this.senderDonate = senderDonate;
    }

    public User getGetterDonate() {
        return getterDonate;
    }

    public void setGetterDonate(User getterDonate) {
        this.getterDonate = getterDonate;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
