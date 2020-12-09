package com.esr.gui.updater;

import com.esr.service.base.IUpdater;

import java.util.ArrayList;

/**
 * @Description
 * @Author William
 * @Date 2020/12/9
 * @Version 1.0
 **/
public class BoardUpdater implements IUpdater {
    @Override
    public void guiUpdate(ArrayList<String> img) {

    }
}


//if (iterator.hasNext()){
//        int itr = iterator.next();
////                    System.out.println(itr);
//        tileCard.setIcon(new ImageIcon(CommonUtils.getImage("/Tiles/" + itr + ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)));
//        for (Adventurer adventurer : adventurers) {
//        if((adventurer.getId() + 8) == itr){
////                            System.out.println("Name: "+adventurer.getName()+", pos: "+adventurer.getPos()+", ITR: "+itr);
//        tileCard.setIcon(new TwoLayeredIcon(new ImageIcon(CommonUtils.getImage("/Pawns/" + adventurer.getName()+ ".png", Constant.TILE_WIDTH, Constant.TILE_HEIGHT)), tileCard.getIcon()));
//        }
//        }
//        tileCards.add(tileCard);
//        }
//        else{
//        System.out.println("Error and later program this exception part");
//        }