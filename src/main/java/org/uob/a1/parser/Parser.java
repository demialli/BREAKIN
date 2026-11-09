// todo: pass in command calls

package org.uob.a1.parser;

import org.uob.a1.gameobjects.Map;
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
                        return System.out.println(currentRoom.getDesc());
                    case "inventory":
                        return player.showInventory();
                    case "score":
                        return "Your current score is (" + score.getCurrentScore() + ")";
                        break;
                    case "help": // should help messages be specific to rooms? a possible attribute to add...
                        return help();
                        break;
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
                        // run move
                        break;
                    case "look":
                        // run loop
                        break;
                    case "take":
                    case "get":
                        // run take
                        break;
                    case "use": // fall through!
                    case "activate":
                        // run use
                        break;
                } // make sure you use returns...
                // oh i miss my coloured vscode comments
            
            // only valid three word command is talk to npc
            } else if (commandWords.length == 3) {
                // talk to npc
                if (currentRoom.getName() == "R2") { // change from hardcoded room later
                    // change check to currentRoom == Map.getR2()? can you compare objects like that?
                    return currentRoom.npc.getDia();
                }
                
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
        String format = "Sorry, that's not a valid command. Here's a list of valid commands:\n";
        for (String command : LEGALCOMMANDSFULL) {
            format += (command + "\n"); // ^^ for x in array
        }
    }

    public static String help() {
        String format = "Enter a command to continue the game. Here's a list of valid commands:\n";
        for (String command : LEGALCOMMANDSFULL) {
            format += (command + "\n");
        }
    }

    // kinda dont need this any more
    public void sleep() {
        Thread.sleep(1000);
    }

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