package com.esr.service.game;

import com.esr.service.game.component.adventurer.*;
import com.esr.service.game.component.cards.Tile;
import com.esr.service.game.component.cards.TreasureFigurines;
import com.esr.service.game.data.*;
import com.esr.utils.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class GameData {
    private static BlockData[][] Board;
    private static ArrayList<Integer> Tiles;
    private static FloodDeck floodDeck;
    private static TreasureDeck treasureDeck;
    private static WaterMeter waterMeter;
    private static Adventurer[] adventurers;
    private static FigurinesData figurinesData;

    public GameData(int numOfPlayers, int waterLevel) {
        Tiles = new ArrayList<>();
        floodDeck = new FloodDeck();
        treasureDeck = new TreasureDeck();
        waterMeter = new WaterMeter(waterLevel);
        figurinesData = new FigurinesData();
        Map.setUpMatchers();
        
        adventurers = new Adventurer[numOfPlayers];

        for (int i = 0; i < 24; i++) { Tiles.add(i); }
        Collections.shuffle(Tiles);

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

        Board = new BlockData[6][6];
        int tileIdx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (Map.blankLayout.contains(i * 6 + j)) {
                    Board[i][j] = new BlockData(false);
                }
                else {
                    if (players.contains(Tiles.get(tileIdx) - 8)){
                        Board[i][j] = new BlockData(Tiles.get(tileIdx), Tiles.get(tileIdx) - 8, true);
                        adventurers[players.indexOf(Tiles.get(tileIdx) - 8)].setPos(i, j);
                    }
                    else {
                        Board[i][j] = new BlockData(Tiles.get(tileIdx), true);
                    }
//                    Tiles.add(tileID.get(tileIdx));
                    tileIdx ++;
                }
            }
        }

        for (Adventurer adventurer : adventurers) {
//            adventurer.setHandCards(treasureDeck.getNTreasureCards(2));
            adventurer.setHandCards(treasureDeck.getNNoRiseCards(2));
        }

    }

    public void setBoard(BlockData[][] board) {
        Board = board;
    }

    public void setTiles(ArrayList<Integer> tiles) {
        Tiles = tiles;
    }

    public static ArrayList<Integer> getTilesArray(){ return Tiles; }

    public static BlockData getBoard(int x, int y) {
        return Board[x][y];
    }

    public static FloodDeck getFloodDeck() {
        return floodDeck;
    }

    public static TreasureDeck getTreasureDeck() {
        return treasureDeck;
    }

    public static String getWaterMeterImg() {
        return waterMeter.getImg();
    }

    public static Adventurer[] getAdventurers() {
        return adventurers;
    }



}