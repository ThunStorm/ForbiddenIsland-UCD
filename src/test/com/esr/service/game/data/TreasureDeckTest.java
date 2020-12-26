package test.com.esr.service.game.data; 

import com.esr.gui.GameFrame;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.DataListener;
import com.esr.service.game.Game;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;

/** 
* TreasureDeck Tester. 
* 
* @author PJW 
* @since <pre>12/26/2020</pre> 
* @version 1.0 
*/ 
public class TreasureDeckTest { 

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


} 
