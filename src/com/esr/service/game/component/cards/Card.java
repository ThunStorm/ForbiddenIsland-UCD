package com.esr.service.game.component.cards;

public abstract class Card {
    protected boolean isFront = false;
    protected boolean isDiscarded = false;
    protected int id;

    public Card(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isFront() {
        return isFront;
    }

    public boolean isDiscarded() {
        return isDiscarded;
    }
}
