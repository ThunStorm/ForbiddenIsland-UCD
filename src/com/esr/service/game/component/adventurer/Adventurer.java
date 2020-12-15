package com.esr.service.game.component.adventurer;

import com.esr.service.game.component.cards.TreasureFigurines;
import com.esr.service.game.component.cards.handcards.HandCard;
import com.esr.utils.Map;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description
 * @Author William
 * @Date 2020/11/8
 * @Version 1.0
 **/
public abstract class Adventurer {
    protected int id;
    protected int order;
    protected int x;
    protected int y;
    protected int x1;
    protected int y1;
    protected String name;
    protected String pawnImg;
    protected ArrayList<Integer> handCards;
    protected ArrayList<TreasureFigurines> capturedFigurines;

    public Adventurer(int order, String name) {
        this.order = order;
        this.name = name;
        this.pawnImg = "/Pawns/" + this.name + ".png";
        this.handCards = new ArrayList<>();
        this.capturedFigurines = new ArrayList<>();
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
//        System.out.println(this.getClass().getName());
//        System.out.println(this.x + " " + this.y);
    }

    public int getPos(){
        return Map.numberMatcher.get(Arrays.toString(new int[]{this.x, this.y}));
    }

    public void setMove(int x1, int y1){
        this.x1 = x1;
        this.y1 = y1;
    }

    public void Move(){
        this.x = this.x1;
        this.y = this.y1;
    }

    public void setHandCards(ArrayList<Integer> handCards) {
        this.handCards.addAll(handCards);
    }

    public void setCapturedFigurines(ArrayList<TreasureFigurines> capturedFigurines) {
        this.capturedFigurines = capturedFigurines;
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
