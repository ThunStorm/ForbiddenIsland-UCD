package com.esr.service.game;

import com.esr.gui.updater.LogAgent;
import com.esr.gui.updater.UpdaterAgent;
import com.esr.service.game.component.adventurer.*;
import com.esr.service.game.data.*;
import com.esr.utils.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class GameData {
    // Data layer to store all data and implement related methods
    private static final int[] SpecialActionTile = {-1, -1};
    private static BoardData board;
    private static TreasureDeck treasureDeck;
    private static FloodDeck floodDeck;
    private static WaterMeter waterMeter;
    private static Adventurer[] adventurers;
    private static ArrayList<Integer> tiles;
    private static ArrayList<Integer> displayedTreasureCard;
    private static ArrayList<Integer> cardsInRound;
    private static int selectedPawn = -1;
    private static ArrayList<Integer> selectedPawns;

    // init game: decks, water meter, adventurer, (in-round) pawn/card selection stack
    public GameData(int numOfPlayers, int waterLevel) {
        Map.setUpMatchers();
        floodDeck = new FloodDeck();
        treasureDeck = new TreasureDeck();
        waterMeter = new WaterMeter(waterLevel);
        adventurers = new Adventurer[numOfPlayers];
        displayedTreasureCard = new ArrayList<>();
        cardsInRound = new ArrayList<>();
        selectedPawns = new ArrayList<>();

        // init player's role and order
        ArrayList<Integer> playerList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            playerList.add(i);
        }
        Collections.shuffle(playerList);
        ArrayList<Integer> players = new ArrayList<>(playerList.subList(0, numOfPlayers));
        for (int i = 0; i < players.size(); i++) {
            switch (players.get(i)) {
                case 0:
                    adventurers[i] = new Diver(i);
                    break;
                case 1:
                    adventurers[i] = new Engineer(i);
                    break;
                case 2:
                    adventurers[i] = new Explorer(i);
                    break;
                case 3:
                    adventurers[i] = new Messenger(i);
                    break;
                case 4:
                    adventurers[i] = new Navigator(i);
                    break;
                case 5:
                    adventurers[i] = new Pilot(i);
                    break;
            }
        }

        // init tiles
        tiles = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            tiles.add(i);
        }
        Collections.shuffle(tiles);
        board = new BoardData(players, tiles);

        for (Adventurer adventurer : adventurers) {
            adventurer.setHandCards(treasureDeck.getNNoRiseCards());
        }
    }

    // in-round stack to store selected treasure card
    public static void SelectTreasureCard(boolean isFromHands, int index) {
        int cardInUse;
        if (isFromHands) {
            cardInUse = adventurers[Game.getRoundNum()].getHandCards().get(index);
        } else {
            cardInUse = displayedTreasureCard.get(index);
        }
        if (cardsInRound.size() < 5) {
            cardsInRound.add(cardInUse);
        }
    }

    // in-round stack to store selected pawn(s), set index to -1 to reset
    public static void SelectPawn(int index) {
        selectedPawn = index;
        selectedPawns.add(selectedPawn);
        if (index == -1) {
            cardsInRound.clear();
            selectedPawns.clear();
        }
    }

    // corresponding to BoardListener, used to check availability of movement/reinforce...
    public static void NextTile(int[] coords) {
        SpecialActionTile[0] = coords[0];
        SpecialActionTile[1] = coords[1];
        boolean isNearY = ((adventurers[Game.getRoundNum()].getX() == coords[0]) && (Math.abs(adventurers[Game.getRoundNum()].getY() - coords[1]) == 1));
        boolean isNearX = ((adventurers[Game.getRoundNum()].getY() == coords[1]) && (Math.abs(adventurers[Game.getRoundNum()].getX() - coords[0]) == 1));
        boolean isOnTile = ((adventurers[Game.getRoundNum()].getX() == coords[0]) && (adventurers[Game.getRoundNum()].getY() == coords[1]));
        boolean isNearDiagonally = ((Math.abs(adventurers[Game.getRoundNum()].getX() - coords[0]) == 1) && (Math.abs(adventurers[Game.getRoundNum()].getY() - coords[1]) == 1));

        //check whether can move to (Explorer, Pilot, and Diver in exception)
        if (((isNearX || isNearY) && (board.getTile(coords[0], coords[1]).isExist()))
                || ((isNearDiagonally) && (board.getTile(coords[0], coords[1]).isExist()) && (adventurers[Game.getRoundNum()].getName().equals("Explorer")))
                || (adventurers[Game.getRoundNum()].getName().equals("Pilot") && !isOnTile)) {
            adventurers[Game.getRoundNum()].setMove(coords[0], coords[1]);
            board.setCanMove(true);
            if (board.getTile(coords[0], coords[1]).getStatus() == TileStatus.Flooded) {
                if (!adventurers[Game.getRoundNum()].getName().equals("Pilot") || ((isNearX || isNearY) && (board.getTile(coords[0], coords[1]).isExist()))) {
                    adventurers[Game.getRoundNum()].setShoreUp(coords[0], coords[1]);
                    board.setCanShoreUp(true);
                }
            } else {
                board.setCanShoreUp(false);
            }
        }

        // the tile where pawn is on can also be shored up
        else if ((isOnTile) && (board.getTile(coords[0], coords[1]).isExist() && (board.getTile(coords[0], coords[1]).getStatus() == TileStatus.Flooded))) {
            board.setCanMove(false);
            adventurers[Game.getRoundNum()].setShoreUp(coords[0], coords[1]);
            board.setCanShoreUp(true);
        }

        // diver implementation, swim to nearest tiles without a tile path
        else if (adventurers[Game.getRoundNum()].getName().equals("Diver") && (board.getTile(coords[0], coords[1]).isExist()) && Game.isInFakeRound() && Game.isNeed2save()) {
            ArrayList<int[]> coordinates = new ArrayList<>();
            double distance = 7.0;
            coordinates.add(new int[]{0, 0});
            for (int i = 0; i < board.getTileMap().length; i++) {
                for (int j = 0; j < board.getTileMap()[i].length; j++) {
                    if (board.getTile(i, j).isExist()) {
                        if (adventurers[Game.getRoundNum()].getX() == i && adventurers[Game.getRoundNum()].getY() == j) {
                            continue;
                        }
                        double d = Math.sqrt(Math.pow(((double) i - (double) adventurers[Game.getRoundNum()].getX()), 2)
                                + Math.pow(((double) j - (double) adventurers[Game.getRoundNum()].getY()), 2));
                        if (d < distance) {
                            Iterator<int[]> iteratorCoords = coordinates.iterator();
                            while (iteratorCoords.hasNext()) {
                                int[] temp = iteratorCoords.next();
                                iteratorCoords.remove();
                            }
                            distance = d;
                            coordinates.add(new int[]{i, j});
                        } else if (d == distance) {
                            coordinates.add(new int[]{i, j});
                        }
                    }
                }
            }
            board.setCanMove(false);
            for (int[] minCoords : coordinates) {
                if ((minCoords[0] == coords[0]) && (minCoords[1] == coords[1])) {
                    adventurers[Game.getRoundNum()].setMove(coords[0], coords[1]);
                    board.setCanMove(true);
                }
            }
            board.setCanShoreUp(false);
            coordinates.clear();
        } else {
            board.setCanMove(false);
            board.setCanShoreUp(false);
        }
    }

    // after checking availability of movement, implement a move action
    public static void MoveTo() {
        board.getTile(adventurers[Game.getRoundNum()].getX(), adventurers[Game.getRoundNum()].getY()).MoveOff(adventurers[Game.getRoundNum()]);
        adventurers[Game.getRoundNum()].Move();
        board.getTile(adventurers[Game.getRoundNum()].getX(), adventurers[Game.getRoundNum()].getY()).MoveOnto(adventurers[Game.getRoundNum()].getId());
    }

    // after checking availability of reinforce, implement a move action
    public static void ShoreUp() {
        board.getTile(adventurers[Game.getRoundNum()].getX2(), adventurers[Game.getRoundNum()].getY2()).ShoreUp();
    }

    // implement pass a card to others
    public static boolean PassTo() {
        if (selectedPawn != -1 && cardsInRound.size() != 0) {
            ArrayList<Integer> subList = new ArrayList<>(cardsInRound.subList(0, 1));

            // if receiver has 5 hand, immediately turn to a fake round to allow player to discard
            if (adventurers[selectedPawn].getHandCards().size() == 5) {
                LogAgent.logMessenger("Player Has 5 Hand.\n Please Discard Card(s) Before Receiving a Card From You");
                cardsInRound.clear();
                Game.setInFakeRound(true);
                Game.setFakeRoundNum(Game.getRoundNum());
                Game.setFakeActionCount(Game.getActionCount());
                Game.setRoundNum(selectedPawn);
                Game.setActionCount(3);
                LogAgent.logMessenger("Please Select Card(s) " + adventurers[selectedPawn].getName() + " Would Like To Discard And Redo [Pass To]");
                UpdaterAgent.getPlayerUpdater().guiUpdate();
                return false;
            } else {
                // pass card to selected player
                adventurers[selectedPawn].setHandCards(subList);
                adventurers[Game.getRoundNum()].getHandCards().remove(cardsInRound.get(0));
                return true;
            }
        } else {
            LogAgent.logMessenger("Please Select A Card And A player To Pass A Card To");
            return false;
        }
    }

    // getters and setters
    public static BoardData getBoard() {
        return board;
    }

    public static ArrayList<Integer> getTilesArray() {
        return tiles;
    }

    public static FloodDeck getFloodDeck() {
        return floodDeck;
    }

    public static TreasureDeck getTreasureDeck() {
        return treasureDeck;
    }

    public static WaterMeter getWaterMeter() {
        return waterMeter;
    }

    public static String getWaterMeterImg() {
        return waterMeter.getImg();
    }

    public static Adventurer[] getAdventurers() {
        return adventurers;
    }

    public static int getFloodCardCount() {
        return waterMeter.getFloodCardCount();
    }

    public static ArrayList<Integer> getDisplayedTreasureCard() {
        return displayedTreasureCard;
    }

    public static ArrayList<Integer> getCardsInRound() {
        return cardsInRound;
    }

    public static void resetCardsInRound() {
        cardsInRound.clear();
    }

    public static int[] getSpecialActionTile() {
        return SpecialActionTile;
    }

    public static void resetSpecialActionTile() {
        SpecialActionTile[0] = -1;
        SpecialActionTile[1] = -1;
    }

    public static int getSelectedPawn() {
        return selectedPawn;
    }

    public static ArrayList<Integer> getSelectedPawns() {
        return selectedPawns;
    }

    public int getExplanation() {
        String explanation = "I realize that this class could be a utility class." +
                "However, in order to initial the Game Data in the class, " +
                "we choose not to convert the class to a utility class.";
        return 1;
    }
}