package test.com.esr.service.game.component.adventurer;

import com.esr.service.game.component.adventurer.Diver;
import com.esr.service.game.component.adventurer.Engineer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Engineer Tester.
*
* @author PJW
* @since <pre>12, 24, 2020</pre>
* @version 1.0
*/
public class EngineerTest {

@Before
public void before() throws Exception {
}

@After
public void after() throws Exception {
}

/**
*
* Method: ShoreUp()
*
*/
@Test
public void testShoreUp() throws Exception {
//TODO: Test goes here...
    int order = 1;
    Engineer engineer = new Engineer(order);
    engineer.ShoreUp();
    Assert.assertEquals(0, engineer.getShoreUpCount());
}

/**
*
* Method: resetShoreUpCount()
*
*/
@Test
public void testResetShoreUpCount() throws Exception {
    int order = 1;
    Engineer engineer = new Engineer(order);
    engineer.ShoreUp();
    Assert.assertEquals(0, engineer.getShoreUpCount());
    engineer.resetShoreUpCount();
    Assert.assertEquals(1, engineer.getShoreUpCount());
}


@Test
public void testInfo() throws Exception {
    int order = 1;
    Engineer engineer = new Engineer(order);
    Assert.assertEquals("Engineer", engineer.getName());
    Assert.assertEquals(order, engineer.getOrder());
}
} 
