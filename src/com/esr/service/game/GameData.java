package com.esr.service.game;

import com.esr.service.game.component.adventurer.*;
import com.esr.service.game.data.BlockData;
import com.esr.service.game.data.FloodDeck;
import com.esr.service.game.data.TreasureDeck;
import com.esr.service.game.data.WaterMeter;
import com.esr.utils.Map;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class GameData {
    private BlockData[][] Board;
    private FloodDeck floodDeck;
    private TreasureDeck treasureDeck;
    private static WaterMeter waterMeter;
    private static Adventurer[] adventurers;

    public GameData(int numOfPlayers, int waterLevel) {
        floodDeck = new FloodDeck();
        treasureDeck = new TreasureDeck();
        waterMeter = new WaterMeter(waterLevel);
        Map.setUpMatchers();

        adventurers = new Adventurer[numOfPlayers];
        ArrayList<Integer> tileID = new ArrayList<>();
        for (int i = 0; i < 24; i++) { tileID.add(i); }
        Collections.shuffle(tileID);

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
                if (Map.blankLayout.contains(i)) {
                    Board[i][j] = new BlockData(false);
                }
                else {
                    if (players.contains(tileID.get(tileIdx))){
                        Board[i][j] = new BlockData(tileID.get(tileIdx), tileID.get(tileIdx), true);
                        adventurers[players.indexOf(tileID.get(tileIdx))].setPos(i, j);
                    }
                    else {
                        Board[i][j] = new BlockData(tileID.get(tileIdx), true);
                    }
                    tileIdx ++;
                }
            }
        }

        for (Adventurer adventurer : adventurers) {
//            adventurer.setHandCards(treasureDeck.getNTreasureCards(2));
            adventurer.setHandCards(treasureDeck.getNNoRiseCards(2));
        }

    }

    public BlockData getBoard(int x, int y) {
        return Board[x][y];
    }

    public FloodDeck getFloodDeck() {
        return floodDeck;
    }

    public TreasureDeck getTreasureDeck() {
        return treasureDeck;
    }

    public static String getWaterMeterImg() {
        return waterMeter.getImg();
    }

    public static Adventurer[] getAdventurers() {
        return adventurers;
    }


}