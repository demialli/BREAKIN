// todo: pass in command calls

package org.uob.a1.parser;

import org.uob.a1.gameobjects.*;
import java.util.Scanner;
import java.util.Arrays;

public class Parser {

    public Parser() {}

    
    // command constants
    public static final String[] LEGALCOMMANDS = new String[]{"move", "look", "take", "get", "inventory", "use", 
                                  "activate", "talk", "score", "help", "quit", "exit"};
    public static final String[] LEGALCOMMANDSFULL = {"move <direction>", "look", "look/take <item>", "get <item>", "inventory",
                                         "use/activate <item>", "talk/talk to <npc>", "score", "help", "quit/exit"};

    
    public static String parse(Map map, String input) { // needs to be static... i think
        // TODO: work out which command was entered, execute it and return the result
        // ^^ lol completely ignored that

        Player player = map.getPlayer();
        Room currentRoom = map.getCurrentRoom();
        Score score = map.getCurrentScore();
        
        String regex = "[\\s+]"; 
        String[] commandWords = input.split(regex);

        // if first command word in acceptable list: check length of command
        if (Arrays.asList(LEGALCOMMANDS).contains(commandWords[0])) {
            String first = commandWords[0];

            // one word commands
            if (commandWords.length == 1) {
                switch(first) {
                    case "look":
                        return currentRoom.getDesc();
                    case "inventory":
                        return player.showInventory();
                    case "score":
                        return String.format("Your current score is (%d).", score.getCurrentScore());
                    case "help":
                        return help();
                    // case "quit":
                    // case "exit:" // fall through for ||
                        // break;
                    // ^^ these are handled in main
                }
                
            // two word commands
            } else if (commandWords.length == 2) { 
                // ^^ .attribute for arrays, .method() for strings!
                switch(first) {
                    case "move":
                        return move(map, player, commandWords);
                        
                    case "look":
                        return look(currentRoom, player, commandWords);
                        
                    case "take":
                    case "get":
                        return get(currentRoom, player, commandWords);
                        
                    case "use": // fall through!
                    case "activate":
                        return use(map, player, commandWords); // todo here
                        
                    case "talk":
                        return talk(map);
                } // make sure you use returns...
                // oh i miss my coloured vscode comments
            
            // only valid three word command is talk to npc
            } else if (commandWords.length == 3) {
                // talk to npc
                return talk(map);
                
            // any other length is invalid
            } else {
                return invalidCommand();
            }
        } else { // misspelling or invalid: reject output
            return invalidCommand();
        }
        return null;
    }

    public static String invalidCommand() {
        String format = "> Sorry, that's not a valid command. Here's a list of valid commands:";
        for (String command : LEGALCOMMANDSFULL) {
            format += ("\n" + "? " + command); // ^^ for x in array
        }
        return format;
    }

    public static String help() {
        String format = "> Enter a command to continue the game. Here's a list of valid commands:";
        for (String command : LEGALCOMMANDSFULL) {
            format += ("\n" + "? " + command);
        }
        return format;
    }

    private static String move(Map map, Player player, String[] commandWords) {
        String direction = commandWords[1];
        Room currentRoom = map.getCurrentRoom();
        
        if (!directionExists(direction)) {
            return invalidCommand();
        } // almost forget checks for second words
        
        Exit checkExit = player.move(direction, currentRoom);
        if (checkExit != null) {
            if (checkExit.isLocked()) {
                return "This exit is locked.";
            } else {
                map.getCurrentScore().updateMove();
                map.setCurrentRoom(checkExit.getDestination());
                String msg = map.getCurrentRoom().getDesc(); // needs to update current room directly from map
                if (map.getCurrentRoom() != map.getR3()) {
                    return String.format("You open the door and move %s to a new room.%n👁  %s", direction, msg);
                } else {
                    return msg;
                }
                
            }
        } else { // no exit
            return "You can't move here.";
        }
    }
    
    private static String look(Room currentRoom, Player player, String[] commandWords) {
        String itemName = commandWords[1];
        if (currentRoom.getItem() == null) {
            return "There are no items to look at here.";
        } else if (!currentRoom.getItem().getName().equals(itemName)){
            if (!itemExists(itemName)) {
                return invalidCommand();
            } else {
                return "That item isn't here.";
            }
        } else {
            return currentRoom.getItem().getDesc();
        }
    }
        
    private static String get(Room currentRoom, Player player, String[] commandWords) {
        String itemName = commandWords[1];
        Item item = currentRoom.getItem();
        
        if (item == null) { // no items in room
            return String.format("There are no items to %s here.", commandWords[0]);
        } else if (!item.getName().equals(itemName)) { // wrong item
            if (!itemExists(itemName)) {
                return invalidCommand();
            } else {
                return "That item isn't here.";
            }
        } else { // right item
            player.addItem(item);
            return String.format("Acquired %s!", itemName);
        }
    }

    private static String use(Map map, Player player, String[] commandWords) {
        String itemName = commandWords[1];
        Item item = player.getItem(itemName);
        Room currentRoom = map.getCurrentRoom();

        if (!itemExists(itemName)) {
            return invalidCommand(); // mistypes
        } else {
            if (item == null) {
                return "You don't have this item yet.";
            } 
            Room usedIn = item.getRoomUsedIn();
            if (usedIn != null && currentRoom != usedIn) { // avoid running for watch which can be used anywhere
                return "You can't use this here.";
            }
            if (item.getType().equals("key")) {
                Exit target = (Exit) item.getTarget();
                if (!target.isLocked()) {
                    return "You've already unlocked this.";
                }
                target.setLocked(false);
            }
            map.getCurrentScore().updateUnlock();
            return item.getUsedText();
        }
    }
    
    private static String talk(Map map) {
        Room currentRoom = map.getCurrentRoom();
        if (currentRoom == map.getR2()) {
            return currentRoom.getNpc().getDia();
        } else {
            return "There's nobody here.";
        }
    }

    private static boolean directionExists(String direction) {
        return Arrays.asList("north", "east", "south", "west").contains(direction);
    }

    private static boolean itemExists(String itemName) {
        return Arrays.asList("watch", "diary", "key").contains(itemName);
    }

    // kinda dont need this any more
    /* public void sleep() {
        Thread.sleep(1000);
    } */

}

// commands todo:
// move <direction>: move to different Room
// look: display information about current Room
// look <item>: display information of an Item in the room
// take OR get <item>: take an Item from a Room and add to Inventory
// inventory: view names of Items in Inventory
// use OR activate <item>: use an Item in a Room to change something
    // ^^ needs reply text.
// talk OR talk to <npc>: talk to an NPC
    // ^^ needs reply text.
// score: display current score
// help: display a help message
// quit OR exit: exit the game
// return a String based on the result of the commands
// must be case insensitive
// handle illegal/misspelled commands gracefully