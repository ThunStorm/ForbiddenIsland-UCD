package com.esr.service.game.data;

import com.esr.service.game.GameData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @Description
 * @Author William
 * @Date 2020/12/24
 * @Version 1.0
 **/
public abstract class Deck {
    // abstract class of a deck
    protected ArrayList<Integer> deck;
    protected ArrayList<Integer> discardPile;
    protected int Num;

    // init deck and discard Pile
    // in our project, we would like to add the discarded card to the bottom of deck,
    // therefore, we used ArrayList instead of Stack.
    public Deck(int num){
        deck = new ArrayList<>();
        discardPile = new ArrayList<>();
        Num = num;
    }

    // get n cards
    protected abstract ArrayList<Integer> getNCards();

    // check availability
    protected void CheckAvailability(int n) {
        if (deck.size() < n) {
            Collections.shuffle(discardPile);
            deck.addAll(discardPile);
            discardPile.clear();
        }
    }

}
