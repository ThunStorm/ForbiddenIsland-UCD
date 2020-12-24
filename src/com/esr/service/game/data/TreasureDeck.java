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
public class TreasureDeck extends Deck{
    // treasure deck implementation
    private final ArrayList<Integer> NTreasureCards;

    // init deck and shuffle the deck
    public TreasureDeck() {
        super(2);
        NTreasureCards = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck);
    }

    // get n cards
    public ArrayList<Integer> getNCards() {
        CheckAvailability(Num);
        NTreasureCards.clear();
        NTreasureCards.addAll(deck.subList(0, Num));
        deck.subList(0, Num).clear();
        return NTreasureCards;
    }

    // get n cards initially. player will not get a Water Rise!
    public ArrayList<Integer> getNNoRiseCards() {
        CheckAvailability(Num);
        NTreasureCards.clear();
        int count = 0;
        Iterator<Integer> iterator = deck.iterator();
        while (iterator.hasNext()) {
            int treasureCard = iterator.next();
            if (treasureCard >= 25 && treasureCard <= 27) {
                Discard(treasureCard);
            } else {
                NTreasureCards.add(treasureCard);
                count++;
            }
            iterator.remove();
            if (count >= Num) {
                break;
            }
        }
        deck.addAll(discardPile);
        Collections.shuffle(deck);
        return NTreasureCards;
    }

    // discard process
    public void Discard(int treasureID) {
        discardPile.add(treasureID);
    }

}
