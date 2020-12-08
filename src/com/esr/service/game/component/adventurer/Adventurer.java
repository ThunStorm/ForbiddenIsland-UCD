package com.esr.service.game.component.adventurer;

import com.esr.service.game.component.cards.handcards.HandCard;

import java.util.ArrayList;

public abstract class Adventurer {
    protected int id;
    protected int order;
    protected int pos;
    protected String name;
    protected ArrayList<HandCard> handCards = new ArrayList<>();

    public Adventurer(int order, int pos) {
        this.order = order;
        this.pos = pos;
    }

    public void setHandCards(ArrayList<HandCard> handCards) {
        this.handCards = handCards;
    }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public int getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public ArrayList<HandCard> getHandCards() {
        return handCards;
    }
}
