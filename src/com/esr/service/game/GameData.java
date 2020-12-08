package com.esr.service.game;

import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.service.game.data.BlockData;
import com.esr.service.game.data.FloodDeck;
import com.esr.service.game.data.TreasureDeck;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public class GameData {
    private BlockData[][] Board;
    private FloodDeck floodDeck;
    private TreasureDeck treasureDeck;
    private Adventurer[] adventurers;

    public GameData() {
        
    }
}
