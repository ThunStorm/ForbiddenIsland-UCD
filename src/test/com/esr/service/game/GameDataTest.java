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
import static org.junit.Assert.*;

/** 
* GameData Tester. 
* 
* @author PJW 
* @since <pre>12/26/2020</pre> 
* @version 1.0 
*/ 
public class GameDataTest { 

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
* Method: NextTile(int[] coords) 
* 
*/ 
@Test
public void testNextTile() throws Exception { 
//TODO: Test goes here...

} 

/** 
* 
* Method: MoveTo() 
* 
*/ 
@Test
public void testMoveTo() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: ShoreUp() 
* 
*/ 
@Test
public void testShoreUp() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: PassTo() 
* 
*/ 
@Test
public void testPassTo() throws Exception { 
//TODO: Test goes here... 
} 


} 
