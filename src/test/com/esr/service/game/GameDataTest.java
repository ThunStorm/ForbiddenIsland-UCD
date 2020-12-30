package test.com.esr.service.game; 

import com.esr.gui.GameFrame;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.*;
import com.esr.service.game.data.TileStatus;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* GameData Tester. 
* 
* @author PJW 
* @since <pre>12/26/2020</pre> 
* @version 1.0 
*/
/**
 * Again, please run unit test individually
 * */
public class GameDataTest { 

@Before
/*
*   P    0     0
*   0   DEE    0
*   0    0     0
*
* */
public void before() throws Exception {
    new GameFrame("Forbidden Island");
    new Controllers();
    new DataListener();
    new Game(4,2);
    Game.setRoundNum(0);
    GameData.getAdventurers()[0] = new Pilot(0);
    GameData.getAdventurers()[1] = new Diver(1);
    GameData.getAdventurers()[2] = new Explorer(2);
    GameData.getAdventurers()[3] = new Engineer(3);
    GameData.getBoard().getTile(1, 1).ShoreUp();
    GameData.getBoard().getTile(1, 2).ShoreUp();
    GameData.getBoard().getTile(1, 3).ShoreUp();
    GameData.getBoard().getTile(2, 1).ShoreUp();
    GameData.getBoard().getTile(2, 2).ShoreUp();
    GameData.getBoard().getTile(2, 3).ShoreUp();
    GameData.getBoard().getTile(3, 1).ShoreUp();
    GameData.getBoard().getTile(3, 2).ShoreUp();
    GameData.getBoard().getTile(3, 3).ShoreUp();
    GameData.getBoard().getTile(1, 1).SinkTile();
    GameData.getBoard().getTile(1, 2).SinkTile();
    GameData.getBoard().getTile(1, 3).SinkTile();
    GameData.getBoard().getTile(2, 1).SinkTile();
    GameData.getBoard().getTile(2, 2).SinkTile();
    GameData.getBoard().getTile(2, 3).SinkTile();
    GameData.getBoard().getTile(3, 1).SinkTile();
    GameData.getBoard().getTile(3, 2).SinkTile();
    GameData.getBoard().getTile(3, 3).SinkTile();
    GameData.getAdventurers()[0].setPos(1, 1);
    GameData.getAdventurers()[1].setPos(2, 2);
    GameData.getAdventurers()[2].setPos(2, 2);
    GameData.getAdventurers()[3].setPos(2, 2);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: NextTile(int[] coords) 
* 
*/ 
@Test
public void testNextTile() throws Exception { 
//TODO: Test goes here...
    //Pilot from (1,1) to (1,2) (3,3)
    Game.setRoundNum(0);
    GameData.NextTile(new int[]{3,3});
    assertTrue(GameData.getBoard().isCanMove());
    assertFalse(GameData.getBoard().isCanShoreUp());
    GameData.NextTile(new int[]{1,2});
    assertTrue(GameData.getBoard().isCanMove());
    assertTrue(GameData.getBoard().isCanShoreUp());
    //Diver from (2,2) to (2,3) (3,3)
    Game.setRoundNum(1);
    GameData.NextTile(new int[]{2,3});
    assertTrue(GameData.getBoard().isCanMove());
    assertTrue(GameData.getBoard().isCanShoreUp());
    GameData.NextTile(new int[]{3,3});
    assertFalse(GameData.getBoard().isCanMove());
    assertFalse(GameData.getBoard().isCanShoreUp());
    //Explorer from (2,2) to (1,1) (2,3)
    Game.setRoundNum(2);
    GameData.NextTile(new int[]{1,1});
    assertTrue(GameData.getBoard().isCanMove());
    assertTrue(GameData.getBoard().isCanShoreUp());
    GameData.NextTile(new int[]{2,3});
    assertTrue(GameData.getBoard().isCanMove());
    assertTrue(GameData.getBoard().isCanShoreUp());
    // Engineer from (2,2) to (1,1)(2,3)
    Game.setRoundNum(3);
    GameData.NextTile(new int[]{1,1});
    assertFalse(GameData.getBoard().isCanMove());
    assertFalse(GameData.getBoard().isCanShoreUp());
    GameData.NextTile(new int[]{2,3});
    assertTrue(GameData.getBoard().isCanMove());
    assertTrue(GameData.getBoard().isCanShoreUp());
} 

/** 
* 
* Method: MoveTo() 
* 
*/ 
@Test
public void testMoveTo() throws Exception { 
//TODO: Test goes here...
    GameData.getBoard().getTile(1,1).MoveOff(new Diver(0));
    GameData.getBoard().getTile(1,1).MoveOff(new Engineer(0));
    GameData.getBoard().getTile(1,1).MoveOff(new Explorer(0));
    GameData.getBoard().getTile(1,1).MoveOff(new Messenger(0));
    GameData.getBoard().getTile(1,1).MoveOff(new Navigator(0));
    GameData.getBoard().getTile(3,3).MoveOff(new Diver(0));
    GameData.getBoard().getTile(3,3).MoveOff(new Engineer(0));
    GameData.getBoard().getTile(3,3).MoveOff(new Explorer(0));
    GameData.getBoard().getTile(3,3).MoveOff(new Messenger(0));
    GameData.getBoard().getTile(3,3).MoveOff(new Navigator(0));
    GameData.getBoard().getTile(3,3).MoveOff(new Pilot(0));
    Game.setRoundNum(0);
    GameData.NextTile(new int[]{3,3});
    GameData.MoveTo();
    assertEquals(5, (int) GameData.getBoard().getTile(3, 3).getPlayerOnBoard().get(0));
    assertEquals(0,GameData.getBoard().getTile(1,1).getPlayerOnBoard().size());
} 

/** 
* 
* Method: ShoreUp() 
* 
*/ 
@Test
public void testShoreUp() throws Exception { 
//TODO: Test goes here...
    Game.setRoundNum(0);
    GameData.NextTile(new int[]{1,2});
    assertSame(TileStatus.Flooded, GameData.getBoard().getTile(1,2).getStatus());
    GameData.ShoreUp();
    assertSame(TileStatus.Normal, GameData.getBoard().getTile(1,2).getStatus());
} 

/** 
* 
* Method: PassTo() 
* 
*/ 
@Test
public void testPassTo() throws Exception { 
//TODO: Test goes here...
    // Diver and Explorer have 5 hands, Engineer has 1;
    Game.setRoundNum(1);
    for (int i = 0; i < 4; i++) {
        GameData.getAdventurers()[i].getHandCards().clear();
    }
    for (int i = 0; i < 5; i++) {
        GameData.getAdventurers()[1].getHandCards().add(i);
        GameData.getAdventurers()[2].getHandCards().add(i);
    }
    GameData.getAdventurers()[3].getHandCards().add(0);
    GameData.SelectTreasureCard(true, 0);
    GameData.SelectPawn(3);
    assertTrue(GameData.PassTo());
    GameData.SelectPawn(-1);
    GameData.SelectPawn(2);
    GameData.SelectTreasureCard(true, 0);
    assertFalse(GameData.PassTo());
} 


} 
