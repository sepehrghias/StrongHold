package model.government.trade;

import model.government.resource.Resource;
import model.user.User;

import java.util.ArrayList;

public class Request {
    private String message;
    private User senderRequest;
    private User getterRequest;
    private int number;
    private Resource resource;

    public static ArrayList<Request> requests=new ArrayList<>();

    private boolean accept;

    public Request(String message,int number,Resource resource, User senderRequest, User getterRequest) {
        this.message = message;
        this.senderRequest = senderRequest;
        this.getterRequest = getterRequest;
        this.number = number;
        this.resource = resource;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSenderRequest() {
        return senderRequest;
    }

    public void setSenderRequest(User senderRequest) {
        this.senderRequest = senderRequest;
    }

    public User getGetterRequest() {
        return getterRequest;
    }

    public void setGetterRequest(User getterRequest) {
        this.getterRequest = getterRequest;
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

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
