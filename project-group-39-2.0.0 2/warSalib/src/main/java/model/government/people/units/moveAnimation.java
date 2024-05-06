package model.government.people.units;

import control.GameControl;
import control.MapControl;
import javafx.animation.Transition;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import java.awt.Rectangle;
import javafx.scene.*;
import model.map.Tile;
import view.MapMenu;
import view.StartGame;
import view.enums.messages.GameMenuMessage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class moveAnimation  extends Transition {
    private ArrayList<Units> units;

    private int desX;

    private int desY;

    private int firstX;

    private int firstY;

    private String message;

    private int flag=0;

    private int attackFlag=0;
    private static ArrayList<intPairs> moveRoad=new ArrayList<>();

    public moveAnimation( ArrayList<Units> units,int desX,int desY) {
        this.units = units;
        this.setCycleCount(-1);
        this.desX=desX;
        this.desY=desY;
        firstX=units.get(0).getxLocation();
        firstY=units.get(0).getyLocation();
        this.setCycleDuration(Duration.millis(200000));
        moveUnit(desX,desY);
        this.flag=0;
        this.attackFlag=0;

    }

    @Override
    protected void interpolate(double v) {
        String path="/images/Units"+units.get(0).getUnitsName().getName();
        if(v>=0&&v<0.25){
            path="/images/Units/"+units.get(0).getUnitsName().getName()+"1.png";
            ImagePattern humanImage=new ImagePattern(new Image(StartGame.class.getResource(path).toExternalForm()));
            for (Units units1:units){
                units1.setFill(humanImage);
            }

        }
        else if(v>=0.25&&v<0.50) {
            path = "/images/Units/" + units.get(0).getUnitsName().getName() + "2.png";
            ImagePattern humanImage = new ImagePattern(new Image(StartGame.class.getResource(path).toExternalForm()));
            for (Units units1 : units) {
                units1.setFill(humanImage);
            }
        }
        else if(v>=0.50&&v<0.75) {
            path = "/images/Units/" + units.get(0).getUnitsName().getName() + "3.png";
            ImagePattern humanImage = new ImagePattern(new Image(StartGame.class.getResource(path).toExternalForm()));
            for (Units units1 : units) {
                units1.setFill(humanImage);
            }
        }
        else if(v>=0.75&v<1) {
            path = "/images/Units/" + units.get(0).getUnitsName().getName() + "4.png";
            ImagePattern humanImage = new ImagePattern(new Image(StartGame.class.getResource(path).toExternalForm()));
            for (Units units1 : units) {
                units1.setFill(humanImage);
            }
        }
        if(moveRoad.size()==0){
            this.stop();
            checking();
            if(attackFlag==1){
                attack();
            }
            path="/images/Units/"+units.get(0).getUnitsName().getName()+"1.png";
            ImagePattern humanImage = new ImagePattern(new Image(StartGame.class.getResource(path).toExternalForm()));
            for (Units units1 : units) {
                units1.setFill(humanImage);
                String details= MapControl.showDetails(units1.getyLocation(),units1.getxLocation());
                Game.getMapInGame().getMap()[units1.getyLocation()][units1.getxLocation()].getTooltip().setText(details);
                String details2= MapControl.showDetails(firstX,firstY);
                Game.getMapInGame().getMap()[firstY][firstX].getTooltip().setText(details2);

            }
        }
        else {
            for (Units units1:units){
                Game.getMapInGame().getMap()[units1.getxLocation()][units1.getyLocation()].getChildren().remove(units1);
                Game.getMapInGame().getMap()[units1.getxLocation()][units1.getyLocation()].getPeopleOnTile().remove(units1);
                units1.setxLocation(moveRoad.get(0).getX());
                units1.setyLocation(moveRoad.get(0).getY());
                Game.getMapInGame().getMap()[units1.getxLocation()][units1.getyLocation()].getChildren().add(units1);
                Game.getMapInGame().getMap()[units1.getxLocation()][units1.getyLocation()].getPeopleOnTile().add(units1);
            }
         //   System.out.println(units);
            moveRoad.remove(moveRoad.get(0));
        }

    }

    private void checking() {
      //  System.out.println(this.message);
            if (GameControl.currentUnits.get(0).getxLocation() != this.desX || GameControl.currentUnits.get(0).getyLocation() != this.desY) {
                MapMenu.moveAlert.setContentText("path was bigger than unit speed");
            //    System.out.println("1");
            } else if (this.message.equals("can't go")) {
                MapMenu.moveAlert.setContentText("we don't have any path to this location");
            //    System.out.println("2");
            } else if (this.message.equals("can't permeability")) {
                MapMenu.moveAlert.setContentText("you don't have permeability to this location");
           //     System.out.println("3");
            } else if (this.message.equals("wrong amount")) {
                MapMenu.moveAlert.setContentText("x and y must be bigger than 0 and under than 100");
           //     System.out.println("4");
            }
            else {
                MapMenu.moveAlert.setContentText("all things okay");

            }
        this.flag=1;

    }
    private void attack(){
        ArrayList<Units> deathPersons=new ArrayList<>();
            for (Units units1 : GameControl.currentUnits) {
                for (Units units2 : MapMenu.unitToAttack) {
                    int unit1ChangeHitPoint = units2.getUnitsName().getAttackingPower() * units2.getEfficientAttackingPower() / 75;
                    int unit2ChangeHitPoint = units1.getUnitsName().getAttackingPower() * units1.getEfficientAttackingPower() / 75;
                    units1.changeHitPoint(-1 * unit1ChangeHitPoint);
                    units2.changeHitPoint(-1 * unit2ChangeHitPoint);
               //     System.out.println(unit1ChangeHitPoint);
               //     System.out.println(unit2ChangeHitPoint);
                    if (units1.getHitPoint() <= 0 && !deathPersons.contains(units1)) {
                        deathPersons.add(units1);
                    }
                    if (units2.getHitPoint() <= 0 && !deathPersons.contains(units2)) {
                        deathPersons.add(units2);
                    }

                }
            }
            //       System.out.println(deathPersons.size());
            for (Units unit : deathPersons) {
             //   System.out.println(unit.getOwnerPerson().getUsername());
             //   System.out.println(unit.getxLocation() + "   " + unit.getyLocation());
                unit.getOwnerPerson().getUserGovernment().getPeople().remove(unit);
                Game.getMapInGame().getMap()[unit.getxLocation()][unit.getyLocation()].getPeopleOnTile().remove(unit);
                unit.getOwnerPerson().getUserGovernment().setPopulation(unit.getOwnerPerson().getUserGovernment().getPopulation() - 1);
                Game.getMapInGame().getMap()[unit.getxLocation()][unit.getyLocation()].getChildren().remove(unit);
            }
            MapMenu.attackPopup.hide();

    }

    public int moveUnit(int x, int y) {
        int v = 100 * 100;
        if (x >= 100 || y >= 100 || x < 0 || y < 0) {
            this.message="wrong amount";
            return 0;
        }
        if (!Game.getMapInGame().getMap()[x][y].getType().getPermeability()) {
            this.message="can't permeability";
            return 0;

        }
        ArrayList<ArrayList<Integer>> tilesNeighbors = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            tilesNeighbors.add(new ArrayList<Integer>());
        }
        addNeighbors(tilesNeighbors);
        printShortestDistance(x,y,tilesNeighbors, (100 * GameControl.currentUnits.get(0).getxLocation()) + GameControl.currentUnits.get(0).getyLocation(), (100 * x) + y, v);
        this.message="okay";
        return 1;
    }
    private static void addNeighbors(ArrayList<ArrayList<Integer>> tileNeighbors) {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if ((x + 1) < 100 && (Game.getMapInGame().getMap()[y][x + 1].getRock() == null && Game.getMapInGame().getMap()[y][x + 1].getType().getPermeability())) {
                    tileNeighbors.get((x * 100) + y).add(((x + 1) * 100) + y);
                    tileNeighbors.get(((x+1) * 100) + y).add(((x) * 100) + y);
                }
                if ((x - 1) >= 0 && (Game.getMapInGame().getMap()[y][x - 1].getRock() == null && Game.getMapInGame().getMap()[y][x - 1].getType().getPermeability())) {
                    tileNeighbors.get((x * 100) + y).add(((x - 1) * 100) + y);
                    tileNeighbors.get(((x-1) * 100) + y).add(((x) * 100) + y);
                }
                if ((y + 1) < 100 && (Game.getMapInGame().getMap()[y + 1][x].getRock() == null && Game.getMapInGame().getMap()[y + 1][x].getType().getPermeability())) {
                    tileNeighbors.get((x * 100) + y).add(((x) * 100) + y + 1);
                    tileNeighbors.get((x * 100) + y+1).add(((x ) * 100) + y);
                }
                if ((y - 1) >= 0 && (Game.getMapInGame().getMap()[y - 1][x].getRock() == null && Game.getMapInGame().getMap()[y - 1][x].getType().getPermeability())) {
                    tileNeighbors.get((x * 100) + y).add(((x) * 100) + y - 1);
                    tileNeighbors.get((x * 100) + y-1).add(((x ) * 100) + y);
                }

            }
        }
    }
    public  void printShortestDistance(int x,int y,ArrayList<ArrayList<Integer>> neighborTiles, int tile1, int tile2, int v) {
        int counter = 0;
        int pred[] = new int[v];
        int dist[] = new int[v];
        if (!BFS(neighborTiles, tile1, tile2, v, pred, dist)) {
            this.message="can't go";
        }
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = tile2;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        int i = 0;
    //    System.out.println("Shortest path length is: " + dist[tile2]);
      //  System.out.println("Path is ::");
        for (i = path.size() - 1; i >= 0; i--) {
            intPairs intPairs=new intPairs(path.get(i)/100,path.get(i)%100);
            moveRoad.add(intPairs);
           // System.out.println("x   :  " + path.get(i)/100+"    y :   "+path.get(i)%100);
            counter++;
         //   if (counter == GameControl.currentUnits.get(0).getUnitsName().getSpeed() / 20)
           //     break;

        }
   //     if (path.size() -1> counter) {
         //   for (Units units:currentUnits){
           //     units.setToGoX(x);
             //   units.setToGoY(y);
     //       }
          //  return GameMenuMessage.BIGGERTHANSPEED;
        //  } else {
          //  return GameMenuMessage.SUCCESS;

        //}
    }
    private static boolean BFS(ArrayList<ArrayList<Integer>> tileNeighbors, int tile1,
                               int tile2, int v, int pred[], int dist[]) {

        LinkedList<Integer> queue = new LinkedList<Integer>();

        boolean visited[] = new boolean[v];


        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        visited[tile1] = true;
        dist[tile1] = 0;
        queue.add(tile1);
    // bfs Algorithm
        while (!queue.isEmpty()) {
        int u = queue.remove();
        for (int i = 0; i < tileNeighbors.get(u).size(); i++) {
            if (visited[tileNeighbors.get(u).get(i)] == false) {
                visited[tileNeighbors.get(u).get(i)] = true;
                dist[tileNeighbors.get(u).get(i)] = dist[u] + 1;
                pred[tileNeighbors.get(u).get(i)] = u;
                queue.add(tileNeighbors.get(u).get(i));

                // stopping condition (when we find
                // our destination)
                if (tileNeighbors.get(u).get(i) == tile2) {
                    return true;
                }
            }
        }
    }
        return false;
}

    public static ArrayList<intPairs> getMoveRoad() {
        return moveRoad;
    }

    public ArrayList<Units> getUnits() {
        return units;
    }

    public int getDesX() {
        return desX;
    }

    public int getDesY() {
        return desY;
    }

    public int getFirstX() {
        return firstX;
    }

    public int getFirstY() {
        return firstY;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFlag() {
        return flag;
    }

    public int getAttackFlag() {
        return attackFlag;
    }

    public void setAttackFlag(int attackFlag) {
        this.attackFlag = attackFlag;
    }
}
