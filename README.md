# Forbidden Island

*COMP41670-Software Engineering (ME)-2020/21 Autumn - Forbidden Island Game Project*

#### Authors: 

Weiliang Li and Jingwen Ping

#### Version:

v1.0

## Table of Contents

- [Description](#description)
- [Player Handbook](#player-handbook)
    + [Overview](#overview)
    + [Player Manual](#player-manual)
    + [Special Situations](#special-situations)
- [Configuration](#configuration)
- [Architecture](#architecture)
- [Demo](#demo)
- [Folder Structure](#folder-structure)
    + [Root Directory](#root-directory)
    + [Source Code](#source-code)
    + [Project Implementation](#project-implementation)
    + [Test Directory](#test-directory)

## Description

This project is to design and implement a cooperative game, Forbidden Island game with Java.  

## Player Handbook

#### Overview 

Players play a team of adventurers who must work together to keep Forbidden Island from sinking, in order to buy enough time to capture its four treasures. Once players have captured them, you must make it to Fools’ Landing and escape by helicopter to win. If however, the island sinks before you can complete your tasks, the mission ends in defeat!

#### Player Manual

1. Run the game from the main.

2. You will see the GUI of this game.

3. Please select the Number of players (`-----NUM-----`) and Level of difficulty (`---LEVEL---`) in config box. Press `Start`, the game will start with background music start playing.

   - The number of players is ranging between 2 to 4.

   + The level of difficulty is equal to the initial water meter.

4. Game Setup (Initialization before playing)

   - Forbidden Island is created with random locations of tiles. All the states of tiles are unflooded.

   - 24 Treasure Cards and 24 Flood Cards are shuffled and placed on the deck.

   - 6 top flood cards are drawn and the state of corresponding island tiles are flooded.

   - Each player is randomly allocated with a role: Engineer (Red), Explorer (Green), Diver (Black), Pilot (Blue), Messenger (Silver), Navigator (Yellow). Each player gets 2 treasure cards. (If anyone gets the Waters Rise cards, it would be put back in the Treasure Deck and assign another card. ) Some roles have special movement exceptions:

     * Engineer may shore up 2 tiles for 1 action.

     + Diver may swim to the nearest tile.

     + Explorer may move and swim diagonally.

     + Pilot may fly to any tile.

     + Messenger may give cards without having to be on the same tile

5. Player's turn

   - Each Player can take up to 3 actions (some exception of roles have been mentioned):
     - **Move**: Click a  *adjacent existing* `Tile` (Up/Down/Left/Right) and click `Move To`
     - **Shore Up**: Click the *adjacent or on foot* `Tile` and click `Shore Up`
     - **Give a Treasure Card**: Click the `hand` which you would like to pass to another player, click his `head portrait` on player panel, and then click `Pass To`. Only one card can only be passed at a time.
       - If the receiver has 5 cards already, it would turn to receiver's round to require the corresponding player to `Discard` one card. Then try to give treasure card to receiver again.
     - **Capture a Treasure**: With four same treasure cards and standing on the corresponding treasure tile, click `Capture`.
   - Once the actions are finished, click `Next` to enter in stage 2 and 3. Two cards from the treasure deck are drawn and display on the Panel (Left-hand side)
     - If a Waters Rise! card is drawn, water meter rise 1, Waters Rise! is discarded immediately, the flood discards are shuffled and put back to the top of flood deck
   - A number of flood cards (equal to water meter) are drawn and the state of the corresponding land is changed. Then click `Next` for the next player's turn.
     - If the number of player's hand card reaches 5, the player is forced to discard. Click the `card` that you want to discard (**including the cards on the treasure card panel**), then use  `Discard`
   - Press `Next` to continue after all operations 

6. Special action

   - Helicopter card can be played at any time. Click `Helicopter card`, click a `Tile` as the destination tile, click all players' `head portrait` who need to be moved to the specified tile with *Helicopter card*, and then click `Special Actions`
   - Sandbag card can be played at any time. Click `Sandbag card`, then click a `Tile` (flooded) that player want to shore up, then click `Special Actions`
   - **Note:** In one's turn, if other players(not in their turn) would like to use their *Special Action Card*s, click their `head portrait` and `Special Actions` button to switch to their turn to use *Special Action Cards.* Usage is then same to above two operations

7. Lift off

   - If four treasure cards are collected and four players are all on *Fools’ Landing* with at least one *helicopter card*, click `Lift Off`
   
8.  Clear

   * If player regrets the selection of tile or cards, click `clear` to clear all the selection and reselect.

#### Special Situations

1. Player on a sunk tile

   - If a tile sink when a player is on the tile. The player must move to an unsunk tile immediately. Follow the order shown on Log and click an *adjacent unsunk*  `Tile` and click `Move To`. (some exception of roles have been mentioned)

2. Game failure

   There are four possible ways to lose:

   1. If both Temples, Caves, Palaces, or Gardens tiles sink before any adventure collect their respective treasures.

   2. The Fools’ Landing tile sinks.
   3. If any player is on a sunk Island tile and cannot swim to another tile.
   4. If the water level reaches the skull and crossbones (Level 10)

## Configuration

This project can be downloaded using `git clone` command or from [here](https://github.com/ThunStorm/FI-game-UCD/archive/master.zip "FI-game-UCD").

The project is built and run with Java 8 (JDK1.8 and JRE1.8)

## Architecture

In general, the project is implemented following the Model-View-Controller (MVC) model. 

**Model**: The model is used to store the application's dynamic data structure, independent of the user interface. In our project, action listeners, Game.java and GameData.java are the Model in the pattern.

**View:** Graphical interfaces are the Views in our project, which provides players with an intuitive visualization of data of the game.

**Controller:** GUI Updaters and button Listeners accepts input and converts it to commands for the Model and the View and make sure the synchronization of them.

Additionally, we also looked at behavioral design patterns.  We applied **Façade Pattern** and **Command Pattern** to decoupling our code and make it more flexible. In detail, for example, the game logic part as a **Receiver** will no longer couple with **Invoker** and **Client(Players)**

## Demo

![demo](/docs/FI-Demo.gif)

## Folder Structure

#### Root Directory

```
.
│ README.md					# Readme 
├─docs          				# game rules and project document                 
├─src						# source code
└─UML						# modelling diagrams
```

#### Source Code

```
...
├─src
|  ├─com					# project implementation
|  └─test					# automated tests
...
```

#### Project Implementation

```
...
com.esr
	Main.java				# main method
	├─gui					# implement graphical interface
	│  GameFrame.java			# main frame
	│  ├─console				# console panel
	│  │	ConsolePanel.java
	│  ├─game				# game panels
	│  │	BoardPanel.java
	│  │    FloodPanel.java
	│  │    GamePanel.java
	│  │    PlayerPanel.java
	│  │    TreasurePanel.java
	│  ├─listener				# add action listeners to buttons
	│  │    Controllers.java		# for controllers "Move To", "Next", etc
	│  │    DataListener.java      		# add action listerners to button in game panel
	│  ├─tools				# wheels to manipulate graphical interface
	│  │    ImageRotation.java	
	│  │    JPanelBG.java
	│  │    TwoLayeredIcon.java
	│  │    VFlowLayout.java
	│  └─updater				# updaters to read data from Game.java/GameData.java and update gui components
	│  	BoardUpdater.java
	│       ControllersUpdater.java
	│       FloodUpdater.java
	│       LogAgent.java
	│       PlayerUpdater.java
	│       TreasureUpdater.java
	│       UpdaterAgent.java
	│       WaterMeterUpdater.java        
    ├─resources					# resources files
    │  ├─audio
    │  └─image   
    ├─service					# game logic and game components
    │  ├─base					# Interfaces
    │  │	IExecuter.java
    │  │    	ITimer.java			# Timer Interface
    │  │    	IUpdater.java  			# Updater Interface for all updaters in com.esr.gui.updater 
    │  └─game					# game logic and components design
    │      │	Game.java			# Game.java involves different stages or situations in the game in time domain
    │      │	GameData.java			# GameData.java defines data of components and basic actions in game
    │      ├─component				# game component design
    │      │	├─adventurer			# adventurers implementations
    │      │  	│	Adventurer.java		# parent class of all roles of adventurers
    │      │  	│	Diver.java
    │      │  	│      	Engineer.java
    │      │  	│      	Explorer.java
    │      │  	│      	Messenger.java
    │      │  	│      	Navigator.java
    │      │  	│      	Pilot.java
    │      │  	└─cards
    │      │           	TreasureFigurines.java	# enumeration of Treasure Figurines
    │      └─data
    │      	Block.java			# data of a tile and basic methods to manipulate data
    │           BoardData.java			# an array of blocks to present data of board and basic methods
    │           Deck.java			# parent class of flood card deck and treasure card deck
    │           FloodDeck.java			# flood card deck
    │           TileStatus.java			# enumeration of status of a tile
    │           TreasureDeck.java		# treasure card deck
    │           WaterMeter.java   		# water meter 
    └─utils					# utilities
    	Audio.java				# enumeration of audios and methods to play and loop-play
        CommonUtils.java			# utility class to implement timer and method to read resources
        Constant.java       			# parameter constants
        Map.java				# used to transform coordinates
```

#### Test Directory

```
...
test.com.esr
	└─service.game				# test game
		│  GameDataTest.java		# test GameData.java
		│  GameTest.java		# test Game.java
		└─component			# test different game components
			└─adventurer					
			│	AdventurerTest.java
			│   	DiverTest.java
			│   	EngineerTest.java
			│   	ExplorerTest.java
			│   	MessengerTest.java
			│   	NavigatorTest.java
			│   	PilotTest.java        
			└─data
     				BlockTest.java
     				BoardDataTest.java
     				FloodDeckTest.java
     				TreasureDeckTest.java
				WaterMeterTest.java
```

