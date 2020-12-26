package test.com.esr.service.game.data; 

import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.service.game.component.adventurer.Diver;
import com.esr.service.game.component.adventurer.Pilot;
import com.esr.service.game.data.Block;
import com.esr.service.game.data.TileStatus;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* Block Tester. 
* 
* @author PJW 
* @since <pre>12/25/2020</pre> 
* @version 1.0 
*/ 
public class BlockTest { 

@Before
public void before() throws Exception {
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: MoveOnto(int playerID)
* Method: MoveOff(Adventurer adventurer)
* 
*/ 
@Test
public void testMoveOntoOff() throws Exception {
//TODO: Test goes here...
    Block block = new Block(9, true);
    Adventurer diver = new Diver(1);
    block.MoveOnto(diver.getId());
    int gottenId = block.getPlayerOnBoard().get(0);
    assertEquals(0, gottenId);
    block.MoveOff(diver);
    assertEquals(new ArrayList<Integer>(), block.getPlayerOnBoard());
} 

/** 
* 
* Method: CanPassTo(Adventurer sender, Adventurer receiver) 
* 
*/ 
@Test
public void testCanPassTo() throws Exception { 
//TODO: Test goes here...
    Block block = new Block(9, true);
    Adventurer diver = new Diver(1);
    Adventurer pilot = new Pilot(2);
    block.MoveOnto(diver.getId());
    block.MoveOnto(pilot.getId());
    assertTrue(block.CanPassTo(diver, pilot));
} 

/** 
* 
* Method: ShoreUp() 
* 
*/ 
@Test
public void testShoreUp() throws Exception { 
//TODO: Test goes here...
    Block block1 = new Block(9, true);
    block1.SinkTile();
    block1.ShoreUp();
    assertEquals(TileStatus.Normal, block1.getStatus());
    block1.SinkTile();
    block1.SinkTile();
    block1.ShoreUp();
    assertEquals(TileStatus.Sunk, block1.getStatus());
} 

/** 
* 
* Method: SinkTile() 
* 
*/ 
@Test
public void testSinkTile() throws Exception { 
//TODO: Test goes here...
    Block block1 = new Block(9, true);
    boolean oneSink = block1.SinkTile();
    assertEquals(TileStatus.Flooded, block1.getStatus());
    assertFalse(oneSink);
    boolean twiceSinks = block1.SinkTile();
    assertEquals(TileStatus.Sunk, block1.getStatus());
    assertTrue(twiceSinks);
    boolean threeSinks = block1.SinkTile();
    assertEquals(TileStatus.Sunk, block1.getStatus());
    assertTrue(threeSinks);
}
} 
