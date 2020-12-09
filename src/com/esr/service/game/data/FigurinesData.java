package com.esr.service.game.data;

import com.esr.service.game.component.cards.TreasureFigurines;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description
 * @Author William
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class FigurinesData {
    private ArrayList<TreasureFigurines> treasureFigurines;

    public FigurinesData() {
        treasureFigurines = new ArrayList<>();
        treasureFigurines.addAll(Arrays.asList(TreasureFigurines.values()));
    }

    // later finish
    public TreasureFigurines captureFigurine(){
        return treasureFigurines.get(0);
    }
}
