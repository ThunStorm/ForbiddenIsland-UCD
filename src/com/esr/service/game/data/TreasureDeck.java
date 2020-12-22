package com.esr.service.game.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class TreasureDeck {
    private ArrayList<Integer> treasureDeck;
    private ArrayList<Integer> discardPile;
    private ArrayList<Integer> NTreasureCards;

    public TreasureDeck() {
        treasureDeck = new ArrayList<>();
        discardPile = new ArrayList<>();
        NTreasureCards = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            treasureDeck.add(i);
        }
        Collections.shuffle(treasureDeck);
    }

    public ArrayList<Integer> getNTreasureCards(int n) {
        CheckAvailability(n);
        NTreasureCards.clear();
        NTreasureCards.addAll(treasureDeck.subList(0, n));
        if (n > 0) {
            treasureDeck.subList(0, n).clear();
        }
        return NTreasureCards;
    }

    public ArrayList<Integer> getNNoRiseCards(int n) {
        CheckAvailability(n);
        NTreasureCards.clear();
        int count = 0;
        Iterator<Integer> iterator = treasureDeck.iterator();
        while (iterator.hasNext()){
            int treasureCard = iterator.next();
            if(treasureCard >= 25 && treasureCard <= 27){
                Discard(treasureCard);
            }
            else {
                NTreasureCards.add(treasureCard);
                count++;
            }
            iterator.remove();
            if (count >= n){
                break;
            }
        }
        return NTreasureCards;
    }

    public void Discard(int treasureID){
        discardPile.add(treasureID);
    }

    private void CheckAvailability(int n){
        if (treasureDeck.size() < n){
            Collections.shuffle(discardPile);
            treasureDeck.addAll(discardPile);
            discardPile.clear();
        }
    }
}
