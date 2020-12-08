package com.esr.service.game.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class FloodDeck {
    private ArrayList<Integer> floodDeck;
    private ArrayList<Integer> discardPile;
    private ArrayList<Integer> displayedCards;
    private int displayNum;

    public FloodDeck() {
        floodDeck = new ArrayList<>();
        discardPile = new ArrayList<>();
        displayedCards = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            floodDeck.add(i);
        }
        Collections.shuffle(floodDeck);
    }

    public ArrayList<Integer> getNFlood(int displayNum){
        this.displayNum = displayNum;
        displayedCards.clear();
        displayedCards.addAll(floodDeck.subList(0,this.displayNum));
        return displayedCards;
    }

    public void Discard(){
        for (int i = 0; i < displayNum; i++) {
            discardPile.add(floodDeck.get(0));
            floodDeck.remove(0);
        }
    }

    public void PutBack2Top(){
        Collections.shuffle(discardPile);
        discardPile.addAll(floodDeck);
        floodDeck.clear();
        floodDeck.addAll(discardPile);
        discardPile.clear();
    }

    public void PutBack(){
        Collections.shuffle(discardPile);
        floodDeck.clear();
        floodDeck.addAll(discardPile);
        discardPile.clear();
    }
}
