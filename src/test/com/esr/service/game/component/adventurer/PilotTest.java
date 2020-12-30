package test.com.esr.service.game.component.adventurer; 

import com.esr.service.game.component.adventurer.Pilot;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;

/** 
* Pilot Tester. 
* 
* @author PJW 
* @since <pre>12/25/2020</pre> 
* @version 1.0 
*/ 
public class PilotTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

@Test
public void testInfo() throws Exception {
    int order = 1;
    Pilot pilot = new Pilot(order);
    assertEquals("Pilot", pilot.getName());
    assertEquals(order, pilot.getOrder());
}
} 
