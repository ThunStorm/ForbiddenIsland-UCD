package test.com.esr.service.game.component.adventurer; 

import com.esr.service.game.component.adventurer.Adventurer;
import com.esr.service.game.component.adventurer.Diver;
import com.esr.utils.Map;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.Arrays;

import static org.junit.Assert.*;

/** 
* Adventurer Tester. 
* 
* @author PJW 
* @since <pre>12/25/2020</pre> 
* @version 1.0 
*/ 
public class AdventurerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

/**
*
* Method: getPos()
*
*/
@Test
public void testGetPos() throws Exception {
//TODO: Test goes here...
    int order = 1;
    Adventurer diver = new Diver(order);
    Map.setUpMatchers();
    diver.setPos(1,2);
    assertEquals(3, diver.getPos());
}

/** 
* 
* Method: Move() 
* 
*/ 
@Test
public void testMove() throws Exception { 
//TODO: Test goes here...
    int order = 1;
    Adventurer diver = new Diver(order);
    diver.setPos(1, 2);
    diver.setMove(1, 3);
    diver.Move();
    assertEquals(1, diver.getX());
    assertEquals(3, diver.getY());
}

} 
