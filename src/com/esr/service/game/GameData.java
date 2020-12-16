package com.esr.service.game;

import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.component.adventurer.*;
import com.esr.service.game.data.*;
import com.esr.utils.Map;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class GameData {

    private static BoardData board;
    private static TreasureDeck treasureDeck;
    private static FloodDeck floodDeck;
    private static WaterMeter waterMeter;
    private static Adventurer[] adventurers;
    private static ArrayList<Integer> tiles;
    private static FigurinesData figurinesData;
//    private static ArrayList<Integer> nextTileNo;

    public GameData(int numOfPlayers, int waterLevel) {
        Map.setUpMatchers();
        floodDeck = new FloodDeck();
        treasureDeck = new TreasureDeck();
        waterMeter = new WaterMeter(waterLevel);
        figurinesData = new FigurinesData();
        adventurers = new Adventurer[numOfPlayers];

        ArrayList<Integer> playerList = new ArrayList<>();
        for (int i = 0; i < 6; i++) { playerList.add(i); }
        Collections.shuffle(playerList);
        ArrayList<Integer> players = new ArrayList<>(playerList.subList(0, numOfPlayers));

        for (int i = 0; i < players.size(); i++) {
            switch (players.get(i)){
                case 0 :
                    adventurers[i] = new Diver(i);
                    break;
                case 1 :
                    adventurers[i] = new Engineer(i);
                    break;
                case 2 :
                    adventurers[i] = new Explorer(i);
                    break;
                case 3 :
                    adventurers[i] = new Messenger(i);
                    break;
                case 4 :
                    adventurers[i] = new Navigator(i);
                    break;
                case 5 :
                    adventurers[i] = new Pilot(i);
                    break;
            }
        }

        tiles = new ArrayList<>();
        for (int i = 1; i <= 24; i++) { tiles.add(i); }
        Collections.shuffle(tiles);
        board = new BoardData(players, tiles);

        for (Adventurer adventurer : adventurers) {
            adventurer.setHandCards(treasureDeck.getNNoRiseCards(2));
        }
    }

    public static void NextTile(int[] coords){
        boolean isNearY = ((adventurers[Game.getRoundNum()].getX() == coords[0]) && (Math.abs(adventurers[Game.getRoundNum()].getY() - coords[1]) == 1));
        boolean isNearX = ((adventurers[Game.getRoundNum()].getY() == coords[1]) && (Math.abs(adventurers[Game.getRoundNum()].getX() - coords[0]) == 1));
        boolean isNearDiagonally = ((Math.abs(adventurers[Game.getRoundNum()].getX() - coords[0]) == 1) && (Math.abs(adventurers[Game.getRoundNum()].getY() - coords[1]) == 1));
        if ((isNearX || isNearY) && (board.getTile(coords[0], coords[1]).isExist())){
            //can extract to a function
            adventurers[Game.getRoundNum()].setMove(coords[0],coords[1]);
            board.setCanMove(true);
            if(board.getTile(coords[0], coords[1]).getStatus() == TileStatus.Flooded){
                adventurers[Game.getRoundNum()].setShoreUp(coords[0],coords[1]);
                board.setCanShoreUp(true);
            }
            else { board.setCanShoreUp(false); }
        }
        else if ((isNearDiagonally) && (board.getTile(coords[0], coords[1]).isExist()) && (adventurers[Game.getRoundNum()].getName().equals("Explorer"))){
            adventurers[Game.getRoundNum()].setMove(coords[0],coords[1]);
            board.setCanMove(true);
            if(board.getTile(coords[0], coords[1]).getStatus() == TileStatus.Flooded){
                adventurers[Game.getRoundNum()].setShoreUp(coords[0],coords[1]);
                board.setCanShoreUp(true);
            }
            else { board.setCanShoreUp(false); }
        }
        else{
            System.out.println("This tile is unselectable");
            board.setCanMove(false);
            board.setCanShoreUp(false);
        }
    }
    public static void MoveTo(){
        board.getTile(adventurers[Game.getRoundNum()].getX(),adventurers[Game.getRoundNum()].getY()).MoveOff(adventurers[Game.getRoundNum()]);
        adventurers[Game.getRoundNum()].Move();
        board.getTile(adventurers[Game.getRoundNum()].getX(), adventurers[Game.getRoundNum()].getY()).MoveOnto(adventurers[Game.getRoundNum()].getId());
    }

    public static void ShoreUp(){
        board.getTile(adventurers[Game.getRoundNum()].getX2(),adventurers[Game.getRoundNum()].getY2()).ShoreUp();
    }

    public static BoardData getBoard() { return board; }

    public static ArrayList<Integer> getTilesArray(){ return tiles; }

    public static FloodDeck getFloodDeck() { return floodDeck; }

    public static TreasureDeck getTreasureDeck() { return treasureDeck; }

    public static String getWaterMeterImg() { return waterMeter.getImg(); }

    public static int getWaterMeterLevel(){ return waterMeter.getWaterLevel(); }

    public static Adventurer[] getAdventurers() { return adventurers; }

    public static FigurinesData getFigurinesData() { return figurinesData; }

    public static void setTiles(ArrayList<Integer> tiles) {
        GameData.tiles = tiles;
    }

}