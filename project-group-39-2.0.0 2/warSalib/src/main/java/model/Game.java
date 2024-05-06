package model;

import javafx.scene.image.Image;
import model.government.Government;
import model.government.building.Building;
import model.government.resource.Resource;
import model.map.GameMap;
import model.user.User;
import model.user.copyUser;
import view.MapMenu;
import view.enums.commands.BuildingCommands;

import java.util.ArrayList;
import java.util.Map;

public class Game {
    //need to have current user
    private static User currentUser;
    private static User turnedUserForGame;
    //governments need to we have all player government
    private static GameMap mapInGame;

    private static int tileSize = 40;

    private static User GameStarter;

    private static Resource resourceInMarket;
    private static Image resourceMarket;
    private static Building selectedBuilding;
    private static MapMenu mapMenu;
    private static ArrayList<Government> governments = new ArrayList<>();
    //how many player plays in game
    private static ArrayList <User> players = new ArrayList<>();

    private static ArrayList<copyUser> copyUserArrayList=new ArrayList<>();

    public static ArrayList<Government> getGovernments() {
        return governments;
    }

    private static ArrayList<User> playersInGame=new ArrayList<>();
    public static void addGovernment (Government government) {
        governments.add(government);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Game.currentUser = currentUser;
    }

    public static ArrayList<User> getPlayers() {
        return players;
    }

    public static ArrayList<User> getPlayersInGame() {
        return playersInGame;
    }

    public static void addPlayer(User user) {
        players.add(user);
    }

    public static GameMap getMapInGame() {
        return mapInGame;
    }

    public static void setMapInGame(GameMap mapInGame) {
        Game.mapInGame = mapInGame;
    }

    public static User getTurnedUserForGame() {
        return turnedUserForGame;
    }

    public static void setTurnedUserForGame(User turnedUserForGame) {
        Game.turnedUserForGame = turnedUserForGame;
    }

    public static Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public static void setSelectedBuilding(Building selectedBuilding) {
        Game.selectedBuilding = selectedBuilding;
    }

    public static void setGameStarter(User gameStarter) {
        GameStarter = gameStarter;
    }

    public static User getGameStarter() {
        return GameStarter;
    }

    public static void setPlayers(ArrayList<User> players) {
        Game.players = players;
    }

    public static int getTileSize() {
        return tileSize;
    }

    public static void setTileSize(int tileSize) {
        Game.tileSize = tileSize;
    }
    public static ArrayList<copyUser> getCopyUserArrayList() {
        return copyUserArrayList;
    }

    public static MapMenu getMapMenu() {
        return mapMenu;
    }

    public static Resource getResourceInMarket() {
        return resourceInMarket;
    }

    public static void setResourceInMarket(Resource resourceInMarket) {
        Game.resourceInMarket = resourceInMarket;
    }

    public static void setMapMenu(MapMenu mapMenu) {
        Game.mapMenu = mapMenu;
    }

    public static void setCopyUserArrayList(ArrayList<copyUser> copyUserArrayList) {
        Game.copyUserArrayList = copyUserArrayList;
    }

    public static Image getResourceMarket() {
        return resourceMarket;
    }

    public static void setResourceMarket(Image resourceMarket) {
        Game.resourceMarket = resourceMarket;
    }
}
