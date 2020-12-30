package test.com.esr.service.game;

import com.esr.gui.GameFrame;
import com.esr.gui.listener.Controllers;
import com.esr.gui.listener.Controllers4TestUse;
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Game Tester.
 *
 * @author PJW
 * @since <pre>12/26/2020</pre>
 * @version 1.0
 */

/**
* Again, the test needed to be run separately
* */
public class GameTest {

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
     * Method: Stage23()
     *
     */
    @Test
    public void testStage23() throws Exception {
//TODO: Test goes here...
        new GameData(4, 2);
        Game.Stage23();
        assertEquals(3, Game.getActionCount());
        assertTrue(Game.isStage23Done());
    }

    /**
     *
     * Method: RoundEnd()
     *
     */
    @Test
    public void testRoundEnd() throws Exception {
//TODO: Test goes here...
        // test discard
        ArrayList<Integer> handCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            handCards.add(i);
        }
        Game.setRoundNum(1);
        GameData.getAdventurers()[1].getHandCards().clear();
        GameData.getAdventurers()[1].setHandCards(handCards);
        GameData.getDisplayedTreasureCard().addAll(GameData.getTreasureDeck().getNCards());
        // test discard stage
        Game.RoundEnd();
        assertEquals(1, Game.getRoundNum());
        GameData.getAdventurers()[1].getHandCards().clear();
        Game.RoundEnd();
        assertEquals(0, GameData.getDisplayedTreasureCard().size());
        assertEquals(2, Game.getRoundNum());

    }

    /**
     *
     * Method: SavePlayersRound()
     *
     */
    @Test
    public void testSavePlayersRound() throws Exception {
//TODO: Test goes here...
        int xLanding = 0;
        int yLanding = 0;
        int xSelectTile = 2;
        int ySelectTile = 2;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (GameData.getBoard().getTile(i,j).getTileId() == 14){
                    xLanding = i;
                    yLanding = j;
                    continue;
                }
                else if (i != xLanding && j != yLanding && i >= 2 && i <= 3 && j >= 2 && j <= 3)
                {
                    xSelectTile = i;
                    ySelectTile =j;
                }
                GameData.getBoard().getTile(i, j).SinkTile();
                GameData.getBoard().getTile(i, j).SinkTile();
            }
        }
        ArrayList<Integer> playerInWater = new ArrayList<>();
        playerInWater.add(5); // Pilot falls
        playerInWater.add(0); // Diver falls
        playerInWater.add(2); // Explorer falls
        playerInWater.add(1); // Engineer falls
//        int xPlayer = (xLanding >= 5) ? (xLanding - 1) : (xLanding + 1);
//        int yPlayer = (yLanding >= 5) ? (yLanding - 1) : (yLanding + 1);
        int xPlayer = xSelectTile;
        int yPlayer = ySelectTile;
        GameData.getAdventurers()[0].setPos(xPlayer,yPlayer);
        GameData.getAdventurers()[1].setPos(xPlayer,yPlayer);
        GameData.getAdventurers()[2].setPos(xPlayer,yPlayer);
        // other pawns on a diagonal tile, engineer on a nearest tile with same y;
        GameData.getAdventurers()[3].setPos(xPlayer,yLanding);
        Game.setPlayerIDinWater(playerInWater);
        Game.setNeed2save(true);
        Game.setInFakeRound(true);
        Game.setFakeRoundNum(Game.getRoundNum());
        Game.SavePlayersRound();
        // everyone have a tile to swim to
        assertEquals(2, Game.getActionCount());
        // set Engineer to diagonal tile, Engineer will have no tile to swim to, game lose
        GameData.getAdventurers()[3].setPos(xPlayer-1, yPlayer-1);
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Game.SavePlayersRound();
        assertNotNull(outContent.toString());
    }

}