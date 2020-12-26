package test.com.esr.service.game; 

import com.esr.gui.GameFrame;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.service.game.Game;
import com.esr.service.game.GameData;
import com.esr.service.game.component.adventurer.Diver;
import com.esr.service.game.component.adventurer.Engineer;
import com.esr.service.game.component.adventurer.Explorer;
import com.esr.service.game.component.adventurer.Pilot;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* Game Tester. 
* 
* @author PJW 
* @since <pre>12/26/2020</pre> 
* @version 1.0 
*/ 
public class GameTest { 

@Before
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
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: Stage23() 
* 
*/ 
@Test
public void testStage23() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: RoundEnd() 
* 
*/ 
@Test
public void testRoundEnd() throws Exception { 
//TODO: Test goes here...
    // test discard
    ArrayList<Integer> handCards = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
        handCards.add(i);
    }
    Game.setRoundNum(1);
    GameData.getAdventurers()[1].getHandCards().clear();
    GameData.getAdventurers()[1].setHandCards(handCards);
    GameData.getDisplayedTreasureCard().addAll(GameData.getTreasureDeck().getNCards());
    // test discard stage
    Game.RoundEnd();
    assertEquals(1, Game.getRoundNum());
    GameData.getAdventurers()[1].getHandCards().clear();
    Game.RoundEnd();
    assertEquals(0, GameData.getDisplayedTreasureCard().size());
    assertEquals(2, Game.getRoundNum());

} 

/** 
* 
* Method: SavePlayersRound() 
* 
*/ 
@Test
public void testSavePlayersRound() throws Exception { 
//TODO: Test goes here...

} 

} 
