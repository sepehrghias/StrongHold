package view;

import control.GameControl;
import model.Game;
import model.government.Government;
import model.government.people.People;
import model.government.people.workingpersons.JobsName;
import model.map.GameMap;
import model.user.User;
import view.enums.commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    public static void run() {
        String input ;
        Matcher matcher ;
        Scanner scanner = Scan.getScanner();
        while (true) {
            input = scanner.nextLine();
            if (input.matches("^\\s*logout\\s*$")) {
                System.out.println("user logged out");
                break;
            }
            else if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.PROFILE_MENU)) != null) {
                System.out.println("entered profile menu");
               int flag = ProfileMenu.run();
                if (flag == 1) {
                    System.out.println("logged out");
                    break;
                }
            }
            else if ((matcher = MainMenuCommands.getMatcher(input, MainMenuCommands.START_GAME)) != null) {
                int turn= Integer.parseInt(matcher.group("turn"));
                letStartGame(turn);
                System.out.println("game started");
                EnvironmentMenu.run(scanner);
            } else System.out.println("invalid command!");

        }
    }
    private static void letStartGame(int turn){
        GameControl.setTurn(turn);
        int counter=0;
        Game.getPlayersInGame().add(Game.getCurrentUser());
        Government governmentOfCurrentUser = new Government(0 , 30, Game.getCurrentUser());
        Game.getCurrentUser().setUserGovernment(governmentOfCurrentUser);
        Game.addGovernment(governmentOfCurrentUser);
        Game.setGameStarter(Game.getCurrentUser());
        counter++;
        Scanner scanner=Scan.getScanner();
        System.out.println("add player");
        while (true){
            String add=scanner.nextLine();
            Matcher matcher;
            if((matcher = MainMenuCommands.getMatcher(add, MainMenuCommands.ADD_PLAYER)) != null){
                String username=matcher.group("username");
                if(getUserByName(username)!=null) {
                    if(counter==8){
                        System.out.println("you have maximum of players in your game");
                    }
                    Game.getPlayersInGame().add(getUserByName(username));
                    Government government = new Government(0, 30, getUserByName(username));
                    Game.addGovernment(government);
                    getUserByName(username).setUserGovernment(government);
                    counter++;
                    System.out.println("added successfully");
                }
                else
                    System.out.println("we don't have this user");

            } else if (add.matches("^\\s*make\\s+map\\s*$")){
                GameMap gameMap = new GameMap();
                for (Government government : Game.getGovernments()) {
                    createInitialPeople(government , 30);
                }
                Game.setMapInGame(gameMap);
                System.out.println("map created and set your favorite map");
                break;
            }
            else {
                System.out.println("invalid command");

            }
        }
    }

    public static void createInitialPeople(Government government,int x) {
        for (int i=0;i<x;i++) {
            People people = new People(government.getXLeft() , government.getYDown() ,
                    JobsName.UNEMPLOYED, government.getUser());
            government.addToPeople(people);
            government.addToUnworkedPeople(people);
            government.setPopulation(government.getPopulation()+1);
            Game.getMapInGame().getMap()[government.getYDown()][government.getXLeft()].getPeopleOnTile().add(people);
        }
     //   System.out.println("is  "+government.getPeople().size());

    }

    private static User getUserByName(String username){
         for (User user:Game.getPlayers()){
            if(user.getUsername().equals(username))
                return user;

        }
        return null;
    }
}
