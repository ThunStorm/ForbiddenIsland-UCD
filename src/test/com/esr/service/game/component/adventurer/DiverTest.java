package test.com.esr.service.game.component.adventurer; 

import com.esr.service.game.component.adventurer.Diver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* Diver Tester. 
* 
* @author PJW
* @since <pre>12, 24, 2020</pre>
* @version 1.0 
*/ 
public class DiverTest { 

@Before
public void before() throws Exception {
} 

@After
public void after() throws Exception { 
} 

@Test
public void testInfo() throws Exception{
    int order = 1;
    Diver diver = new Diver(order);
    Assert.assertEquals("Diver", diver.getName());
    Assert.assertEquals(order, diver.getOrder());
}

} 
