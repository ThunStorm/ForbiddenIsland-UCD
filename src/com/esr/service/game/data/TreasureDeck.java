package com.esr.service.game.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class TreasureDeck {
    private ArrayList<Integer> treasureDeck;
    private ArrayList<Integer> discardPile;
    private ArrayList<Integer> displayedCards;
    private int displayNum = 2;

    public TreasureDeck() {
        treasureDeck = new ArrayList<>();
        discardPile = new ArrayList<>();
        displayedCards = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            treasureDeck.add(i);
        }
        Collections.shuffle(treasureDeck);
//        System.out.println(floodDeck);
    }

    public ArrayList<Integer> getNTreasure(){
        displayedCards.clear();
        displayedCards.addAll(treasureDeck.subList(0,this.displayNum));
        return displayedCards;
    }

    public void Discard(){
        for (int i = 0; i < displayNum; i++) {
            discardPile.add(treasureDeck.get(0));
            treasureDeck.remove(0);
        }
    }

    public void PutBack(){
        Collections.shuffle(discardPile);
        treasureDeck.clear();
        treasureDeck.addAll(discardPile);
        discardPile.clear();
    }
}
