package com.esr.service.game.data;

import com.esr.service.game.GameData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class FloodDeck extends Deck{
    // flood deck implementation
    private final ArrayList<Integer> displayedCards;
    private final ArrayList<Integer> removedFloodCard;
    private boolean isInit;

    // init deck: set number of flood cards will be drawn, shuffle the deck
    public FloodDeck() {
        super(6);
        displayedCards = new ArrayList<>();
        removedFloodCard = new ArrayList<>();
        isInit = true;
        for (int i = 1; i <= 24; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck);
    }

    // get n cards
    public ArrayList<Integer> getNCards() {
        if (!isInit) {
            Num = GameData.getFloodCardCount();
        }
        CheckAvailability(Num);
        displayedCards.clear();
        // after flood card removal, correct display num
        if (deck.size() + discardPile.size() < Num) {
            displayedCards.addAll(deck);
        } else {
            displayedCards.addAll(deck.subList(0, Num));
        }
        return displayedCards;
    }

    // discard process
    public void Discard(){
        int count = 0;
        Iterator<Integer> iterator = deck.iterator();
        while (iterator.hasNext()) {
            int floodCard = iterator.next();
            if (count < Num) {
                discardPile.add(floodCard);
                iterator.remove();
                count++;
            }
            if (count >= Num) {
                break;
            }
        }
    }

    // put flood card back to the top of deck
    public void PutBack2Top() {
        if (discardPile.size() != 0) {
            Collections.shuffle(discardPile);
            discardPile.addAll(deck);
            deck.clear();
            deck.addAll(discardPile);
            discardPile.clear();
        }
    }

    // remove flood card when corresponding tile is removed
    public void RemoveFloodCard(int removedTile) {
        removedFloodCard.add(removedTile);
        deck.remove((Integer) removedTile);
    }

    // first time will draw 6 cards then depends on water level
    public void set2Norm() {
        this.isInit = false;
    }

    // preserve interface for testing
    public ArrayList<Integer> getRemovedFloodCard() {
        return removedFloodCard;
    }
}
