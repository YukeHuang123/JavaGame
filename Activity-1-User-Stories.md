# User Stories

## Movement

1. **As a player, I want to move my character using keyboard controls so that I can navigate the game world.**
    - **Acceptance Criteria:**
        - Player can move up, down, left, and right using arrow keys or WASD.
        - Movement is smooth and responsive.
    - **Estimated Time:** 4 hours
    - **Priority:** High

2. **As a player, I want to sprint using a designated key so that I can move faster in the game.**
    - **Acceptance Criteria:**
        - Player can hold down the Shift key to sprint.
        - Sprinting speed is greater than normal walking speed.
    - **Estimated Time:** 3 hours
    - **Priority:** Medium

* As a player, I want to be able to move my character up, down, left, and right so that I can navigate the maze.
* As a player, I want to be able to run so that I can move faster.  

* As a developer, I want to be able to implement the movement system so that the player can move around the maze.
* As a developer, I want to be able to implement the running system so that the player can move faster.

## Maze/Map/Level


1. **As a player, I want to see a map so that I can orient myself within the game world.**
    - **Acceptance Criteria:**
        - Map displays current location and main paths.
        - Map updates as the player moves.
    - **Estimated Time:** 5 hours
    - **Priority:** High

2. **As a player, I want to unlock new areas of the maze as I progress so that I have a sense of exploration.**
    - **Acceptance Criteria:**
        - New areas become accessible after completing specific tasks.
        - Visual indicators show unlocked areas on the main map.
    - **Estimated Time:** 6 hours
    - **Priority:** Medium

* As a player, I want to be able to see the maze so that I can navigate it.
* As a player, I want to be able to see the map so that I can see where I am in the maze.  

* As a developer, I want to be able to implement the maze so that the player can navigate it.  
* As a developer, I want to be able to implement the map so that the player can see where they are in the maze.

## Enemies

* As a player, I want to be able to see the enemies so that I can avoid them.
* As a player, I want to be able to attack the enemies so that I can defeat them.
* As a player, I want to be able to see the health of the enemies so that I can know how close they are to dying.  

* As a developer, I want to be able to implement the enemies mechanics so that the player can interact with them.
* As a developer, I want to be able to implement the health system for the enemies so that the player can know how close they are to dying.

## Inventory system

* As a player, I want to be able to see my inventory so that I can see what items I have.
* As a player, I want to be able to pick up items so that I can use them later.
* As a player, I want to be able to use items so that I can benefit from them.
* As a player, I want to be able to drop items so that I can make space for other items.  

* As a developer, I want to be able to implement the inventory system so that the player can interact with it.
* As a developer, I want to be able to implement the item system so that the player can pick up and use items.
* As a developer, I want to be able to implement the item drop system so that the player can drop items.

## Additional Features

### Loot system

#### User Stories
* As a player, I want to be able to find chests so that I can get loot.
* As a player, I want to be able to open chests so that I can get the loot inside.
* As a player, I want to be able to see the loot I get so that I can know what I got.
* As a player, I want to be able to see the rarity of the loot so that I can know how good it is.

* As a developer, I want to be able to implement the loot system so that the player can get loot from chests.
* As a developer, I want to be able to generate loot so that the player can get different items.
* As a developer, I want to be able to control the rarity of the loot so that the player can get better items from rarer chests.

#### Estimated Time: 3 days

### Combat system

#### User Stories
* As a player, I want to be able to shoot enemies so that I can defeat them.
* As a player, I want to be able to use different weapons so that I can defeat enemies more effectively.
* As a player, I want to be able to see the health of the enemies so that I can know how close they are to dying.
* As a player, I want to be able to see my health so that I can know how close I am to dying.
* As a player, I want to be able to see the damage I deal so that I can know how effective my attacks are.

* As a developer, I want to be able to implement the combat system so that the player can fight enemies.
* As a developer, I want to be able to implement the weapon system so that the player can use different weapons.
* As a developer, I want to be able to implement the health system for the player so that they can know how close they are to dying.
* As a developer, I want to be able to implement the damage system so that the player can know how effective their attacks are.
* As a developer, I want to be able to implement the resurrection function, that players don't need to restart from the game from the intialized stage.
* As a developer, I want to be able to implement the bonus function, that players will get some suprise that can improve the experience of the game.
* As a developer, I want to add the shop system that developer can buy some weapon or items that increase health and mana.

#### Estimated Time: 5 days


## Customization System

1. **As a player, I want to change my character's skin color so that I can customize my character's appearance.**
    - **Acceptance Criteria:**
        - Players can select different skin colors from the settings menu.
        - The changed color is reflected in real-time on the character model.
    - **Estimated Time:** 4 hours
    - **Priority:** High

2. **As a player, I want to change the color of my weapons so that I can personalize my gear.**
    - **Acceptance Criteria:**
        - Players can choose from various color options for their weapons.
        - The new weapon color is displayed in-game.
    - **Estimated Time:** 3 hours
    - **Priority:** High

3. **As a player, I want to change the color of my bullets so that I can visually differentiate between different ammunition types during combat.**
    - **Acceptance Criteria:**
        - Players can select bullet color options.
        - The bullets fired match the selected color.
    - **Estimated Time:** 3 hours
    - **Priority:** Medium

4. **As a player, I want to save my customization settings so that I can quickly load them in future games.**
    - **Acceptance Criteria:**
        - Players can save their current skin, weapon, and bullet color settings.
        - Settings automatically load the next time the game is started.
    - **Estimated Time:** 5 hours
    - **Priority:** Medium

## resurrection
1. ***As a player, I want to resurrect from the place where the role died.***
    - **Acceptance Criteria:**
        - Players will continue the game immediately from where the role dies if the role has lives.
    - **Estimated Time:** 5 hours
    - **Priority:** Medium

2. ***As a player, I want to store the money and inventory and they will not clear after dying.***
    - **Acceptance Criteria:**
        - Players will continue to use the money and inventory even it resurrect.
    - **Estimated Time:** 5 hours
    - **Priority:** Medium
3. ***As a player, I want to choose where can I resurrect before the progress of the game and can go back to the previous game level.***
    - **Acceptance Criteria:**
        - Players can choose where to restart the game from where they have passed.
    - **Estimated Time:** 8 hours
    - **Priority:** Medium
4. ***As a developer, I want to set a rule that player have several lives that roles can resurrect at current place and the money and inventory will keep.***
    - **Acceptance Criteria:**
        - Players can choose where to restart the game from where they have passed.
    - **Estimated Time:** 8 hours
    - **Priority:** Medium
5. ***As a developer, I want to set a ruel that player will restart from the inital stage of this level of game if its lives have all used***
    - **Acceptance Criteria:**
        - If the role has no lives, it will recurrect from the initial stage and the money and inventory will be all cleared.
    - **Estimated Time:** 8 hours
    - **Priority:** Medium
6. ***As a developer, I want to let the player choose where to resurrect and let player choose whether they want clear the inventory.***
    - **Acceptance Criteria:**
        - if the role has lives, the game can give the player options whteher continue (resurrect at same place) or restart(rresurrect from the initial stage). Also play can choose whether clear money and inventory or not.
    - **Estimated Time:** 8 hours
    - **Priority:** Low
