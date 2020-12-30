package test.com.esr.service.game.data; 

import com.esr.gui.GameFrame;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.service.game.Game;
import com.esr.service.game.data.FloodDeck;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* FloodDeck Tester. 
* 
* @author PJW 
* @since <pre>12/25/2020</pre> 
* @version 1.0 
*/ 
public class FloodDeckTest { 

@Before
public void before() throws Exception {
    new GameFrame("Forbidden Island");
    new Controllers();
    new DataListener();
    new Game(4,2);

} 

@After
public void after() throws Exception { 
}

/**
 * Test will be run separately due to gui updater
 */

/**
* 
* Method: getNCards() 
* 
*/ 
@Test
public void testGetNCards() throws Exception { 
//TODO: Test goes here...
    FloodDeck floodDeck = new FloodDeck();
    ArrayList<Integer> firstGet = new ArrayList<>(floodDeck.getNCards());
    floodDeck.Discard();
    floodDeck.set2Norm();
    ArrayList<Integer> secondGet = new ArrayList<>(floodDeck.getNCards());
    floodDeck.Discard();
    ArrayList<Integer> thirdGet = new ArrayList<>(floodDeck.getNCards());
    assertEquals(6, firstGet.size());
    assertNotEquals(6, secondGet.size());
    assertNotSame(thirdGet, secondGet);
} 

/** 
* 
* Method: Discard() 
* 
*/ 
@Test
public void testDiscard() throws Exception { 
//TODO: Test goes here...
    FloodDeck floodDeck = new FloodDeck();
    floodDeck.set2Norm();
} 

/** 
* 
* Method: PutBack2Top() 
* 
*/ 
@Test
public void testPutBack2Top() throws Exception { 
//TODO: Test goes here...
    FloodDeck floodDeck = new FloodDeck();
    floodDeck.set2Norm();
    ArrayList<Integer> firstGet = new ArrayList<>(floodDeck.getNCards());
    floodDeck.Discard();
    floodDeck.PutBack2Top();
    ArrayList<Integer> secondGet = new ArrayList<>(floodDeck.getNCards());
    for (int i = 0; i < firstGet.size(); i++) {
        assertTrue(secondGet.contains(firstGet.get(i)));
    }
} 

/**
 *
 * Method: CheckAvailability(int n)
 *
 */
@Test
public void testCheckAvailability() throws Exception {
//TODO: Test goes here...
    FloodDeck floodDeck = new FloodDeck();
    // 6 per draw
    for (int i = 0; i < (24/6); i++) {
        floodDeck.getNCards();
        floodDeck.Discard();
    }
    assertEquals(6, floodDeck.getNCards().size());

}

} 
