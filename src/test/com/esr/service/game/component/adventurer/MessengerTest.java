package test.com.esr.service.game.component.adventurer; 

import com.esr.service.game.component.adventurer.Messenger;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;

/** 
* Messenger Tester. 
* 
* @author PJW 
* @since <pre>12/25/2020</pre> 
* @version 1.0 
*/ 
public class MessengerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

@Test
public void testInfo() throws Exception {
    int order = 1;
    Messenger messenger = new Messenger(order);
    assertEquals("Messenger", messenger.getName());
    assertEquals(order, messenger.getOrder());
}
} 
