package test.com.esr.service.game.data; 

import com.esr.gui.GameFrame;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.service.game.data.Block;
import com.esr.service.game.data.TileStatus;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* BoardData Tester. 
* 
* @author PJW 
* @since <pre>12/25/2020</pre> 
* @version 1.0 
*/ 
public class BoardDataTest { 

@Before
public void before() throws Exception {
    new GameFrame("Forbidden Island");
    new Controllers();
    new DataListener();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: sinkTiles(ArrayList<Integer> sinkTiles) 
* 
*/ 
@Test
public void testSinkTiles() throws Exception { 
//TODO: Test goes here...
    new Game(4, 1);
    // find a normal status tile with a pawn on it
    int id = -1;
    for(Block[] blockLn : GameData.getBoard().getTileMap()){
        for (Block block : blockLn){
            if (block.getPlayerOnBoard() != null && block.getPlayerOnBoard().size() != 0 && block.getStatus() == TileStatus.Normal){
               id = block.getTileId();
               break;
            }
            if (id != -1){
                break;
            }
        }
    }
    ArrayList<Integer> sinkTiles = new ArrayList<>();
    sinkTiles.add(id);
    GameData.getBoard().sinkTiles(sinkTiles);
    assertFalse(Game.isNeed2save());
    GameData.getBoard().sinkTiles(sinkTiles);
    assertTrue(Game.isNeed2save());
}

/** 
* 
* Method: isShrinesFlooded() 
* 
*/ 
@Test
public void testIsShrinesFlooded() throws Exception { 
//TODO: Test goes here...
    new Game(4, 1);
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            if (GameData.getBoard().getTile(i,j).getTileId() == 1 || GameData.getBoard().getTile(i,j).getTileId() == 2){
                GameData.getBoard().getTile(i,j).SinkTile();
                GameData.getBoard().getTile(i,j).SinkTile();
            }
        }
    }
    assertTrue(GameData.getBoard().isShrinesFlooded());
}

} 
