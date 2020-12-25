## Forbidden Island - EngineerSeeRed Team

#### Software Engineering (COMP 41670)

#### Authors: 

Weiliang Li and Jingwen Ping

#### Description:

This project is to design and implement the cooperative game, Forbidden Island game. 

#### Contents of document:

1. How to play the game of Forbidden Island.
2. Some situations in game



#### How to play the game of Forbidden Island

1. Open up the project and press the run button.

2. You will see the GUI of this game.

3. Please select the Number of players (-----NUM-----) and Level of difficulty (---LEVEL---), then press start. You will hear the background music after.

   - The number of players is ranging between 2 to 4.

   + The level of difficulty is equal to the initial water meter.

4. Game Setup

   - Forbidden Island is created with the random locations of tiles. All the state of tiles are unflooded.

   - 24 Treasure Cards and 24 Flood Cards are shuffled and placed on the deck.

   - 6 top flood cards are drawn and the state of corresponding island tiles are flooded.

   - Each player is randomly allocated with a role: Engineer (Red), Explorer (Green), Diver (Black), Pilot (Blue), Messenger (Silver), Navigator (Yellow). Each player gets 2 treasure cards. (If anyone gets the Waters Rise cards, it would be put back in the Treasure Deck and assign another card. ) Some roles have special movement exceptions:

     * Engineer may shore up 2 tiles for 1 action.

     + Diver may swim to the nearest tile.

     + Explorer may swim diagonally.

     + Pilot may fly to any tile.

     + Messenger may give cards without having to be on the same tile

5. Player's turn

   - Each Player can take up to 3 actions (some exception of roles have been mentioned):
     - **Move**: Click the *adjacent tile (unsunk)* and click "Move To"
     - **Shore Up**: Click the *adjacent or on foot tile* and click "Shore Up"
     - **Give a Treasure Card**: Click the *head portrait* on player panel and the *treasure card*, then click "Pass To". Only one card can only be passed at a time.
       - If the receiver has 5 card already, it would turn to receiver's round to discard one card. Then try to give treasure card to receiver again.
     - **Capture a Treasure**: With four same treasure cards and standing on the corresponding treasure tile, click "Capture".
   - Once the actions are finished, click "next" and it would be the discard time. 2 card from the treasure deck are draw and display on the Panel (Left hand side)
     - If Waters Rise card is drawn, water meter rise 1 and the flood discards are shuffled
   - Click *the card that you want to discard* (including the cards on the treasure card panel), the rest of cards (no more than 5) would be in player panel. 
   - A number of flood cards (equal to water meter) are drawn and the state of corresponding land is changed. Then click "next" for the next player's turn.

6. Special action

   - Helicopter card can be played at any time. Click the player with *Helicopter card*, and then click "Special Actions",  then click *Helicopter card*, *the tile (unsunk) that you want to move to*, the *players that you want to move*, then click "Special Actions" again. 
   - Sandbag card can be played at any time. Click the player with *Helicopter card*, then click "Special Actions", then click *Sandbag card*, *the tile (flooded) that you want to shore up*, then click "Special Actions" again.

7. Lift off

   - If four treasure cards are collected and four players are all on *Fools’ Landing* with at least one helicopter card, click "Lift off"



#### Some situations in game

1. Player on sunk tile
   - If the tile sink when player(s) is on the tile. The player must move to the unsunk tile immediately. Follow the order shown on Log and click the *adjacent tile (unsunk)* and click "Move To". (some exception of roles have been mentioned)
2. Game failure
   - Any kind of treasure tile sinks before collection.
   -  Fools’ Landing tile sinks.
   - If any player is on a sink Island tile and cannot move to another tile.
   - If the water level reaches 5



