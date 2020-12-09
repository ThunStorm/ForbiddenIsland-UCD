package com.esr.service.game.component.adventurer;

import com.esr.service.game.component.cards.handcards.HandCard;

import java.util.ArrayList;

public abstract class Adventurer {
    protected int id;
    protected int order;
    protected int x;
    protected int y;
    protected String name;
    protected String pawnImg;
    protected ArrayList<Integer> handCards;
    protected int actionNum;
    protected static final int maxActionNum = 3;

    public Adventurer(int order, String name) {
        this.order = order;
        this.name = name;
        this.pawnImg = "/Pawns/" + this.name + ".png";
        handCards = new ArrayList<>();
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setHandCards(ArrayList<Integer> handCards) {
        this.handCards.addAll(handCards);
    }

    public String getPawnImg(){ return pawnImg; }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getHandCards() {
        return handCards;
    }
}
