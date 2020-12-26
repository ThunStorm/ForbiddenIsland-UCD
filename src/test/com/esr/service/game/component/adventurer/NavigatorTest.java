package test.com.esr.service.game.component.adventurer; 

import com.esr.service.game.component.adventurer.Navigator;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;

/** 
* Navigator Tester. 
* 
* @author PJW 
* @since <pre>12/25/2020</pre> 
* @version 1.0 
*/ 
public class NavigatorTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

@Test
public void testInfo() throws Exception {
    int order = 1;
    Navigator navigator = new Navigator(order);
    assertEquals("Navigator", navigator.getName());
    assertEquals(order, navigator.getOrder());
}
} 
